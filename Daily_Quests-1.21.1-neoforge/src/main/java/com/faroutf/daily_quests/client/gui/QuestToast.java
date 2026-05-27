package com.faroutf.daily_quests.client.gui;

import com.faroutf.daily_quests.Config;
import com.faroutf.daily_quests.DailyQuests;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
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
        if (mc.player == null || mc.screen != null) return;

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
        int centerX = screenWidth / 2;

        float alpha = 1.0f;
        if (displayTicks < 10) {
            alpha = displayTicks / 10f;
        } else if (displayTicks > DURATION - 10) {
            alpha = (DURATION - displayTicks) / 10f;
        }
        alpha = Math.max(0, Math.min(1, alpha));

        int bgColor = ((int)(alpha * 0x80) << 24) | 0x000000;
        String text = activeToast.message();
        int textWidth = mc.font.width(text);
        int padding = 12;

        int x1 = centerX - textWidth / 2 - padding;
        int y1 = 30;
        int x2 = centerX + textWidth / 2 + padding;
        int y2 = 50;

        graphics.fill(x1, y1, x2, y2, bgColor);
        graphics.fill(x1, y1, x2, y2, ((int)(alpha * 0x20) << 24) | (activeToast.color() & 0x00FFFFFF));

        int textColor = ((int)(alpha * 0xFF) << 24) | activeToast.color();
        graphics.drawCenteredString(mc.font, text, centerX, y1 + padding / 2, textColor);
    }
}
