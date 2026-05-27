package com.faroutf.daily_quests.client.gui;

import com.faroutf.daily_quests.Config;
import com.faroutf.daily_quests.DailyQuests;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

import java.util.ArrayDeque;
import java.util.Queue;

@EventBusSubscriber(modid = DailyQuests.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class QuestToast {
    private static final Queue<ToastEntry> pendingToasts = new ArrayDeque<>();
    private static ToastEntry activeToast;
    private static int displayTicks;
    private static final int DURATION = 100;
    private static final ResourceLocation TOAST_LAYER =
        ResourceLocation.fromNamespaceAndPath(DailyQuests.MODID, "quest_toast");

    private record ToastEntry(String message, int color) {}

    public static void show(String message, int color) {
        if (!Config.ENABLE_TOAST_NOTIFICATIONS.get()) return;
        pendingToasts.add(new ToastEntry(message, color));
    }

    @SubscribeEvent
    public static void registerLayer(RegisterGuiLayersEvent event) {
        event.registerAboveAll(TOAST_LAYER, QuestToast::renderToastLayer);
    }

    private static void renderToastLayer(GuiGraphics graphics, DeltaTracker delta) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        // Progress or grab next toast
        if (activeToast != null) {
            displayTicks++;
            if (displayTicks > DURATION) {
                activeToast = null;
                displayTicks = 0;
            }
        }
        if (activeToast == null && !pendingToasts.isEmpty()) {
            activeToast = pendingToasts.poll();
            displayTicks = 0;
        }
        if (activeToast == null) return;

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;

        // Fade animation
        float alpha = 1.0f;
        if (displayTicks < 10) {
            alpha = displayTicks / 10f;
        } else if (displayTicks > DURATION - 10) {
            alpha = (DURATION - displayTicks) / 10f;
        }
        alpha = Math.max(0, Math.min(1, alpha));

        // Scale up for larger font
        float scale = 1.5f;
        String text = activeToast.message();
        int textWidth = (int)(mc.font.width(text) * scale);

        // Semi-transparent background
        int padding = 16;
        int x1 = centerX - textWidth / 2 - padding;
        int y1 = centerY - 20;
        int x2 = centerX + textWidth / 2 + padding;
        int y2 = centerY + 20;

        int bgAlpha = (int)(alpha * 0xC0);
        graphics.fill(x1, y1, x2, y2, (bgAlpha << 24) | 0x000000);
        graphics.fill(x1, y1, x2, y2, ((int)(alpha * 0x30) << 24) | (activeToast.color() & 0x00FFFFFF));

        // Draw text with scale
        int textColor = ((int)(alpha * 0xFF) << 24) | activeToast.color();
        var pose = graphics.pose();
        pose.pushPose();
        pose.translate(centerX, centerY - 5, 0);
        pose.scale(scale, scale, 1);
        graphics.drawCenteredString(mc.font, text, 0, 0, textColor);
        pose.popPose();
    }
}
