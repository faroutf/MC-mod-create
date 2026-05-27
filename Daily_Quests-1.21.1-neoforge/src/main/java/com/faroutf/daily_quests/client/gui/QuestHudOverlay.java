package com.faroutf.daily_quests.client.gui;

import com.faroutf.daily_quests.Config;
import com.faroutf.daily_quests.DailyQuests;
import com.faroutf.daily_quests.client.ClientQuestData;
import com.faroutf.daily_quests.network.SyncQuestDataPacket;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.minecraft.world.scores.DisplaySlot;
import net.minecraft.world.scores.Objective;

@EventBusSubscriber(modid = DailyQuests.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class QuestHudOverlay {
    private static final ResourceLocation HUD_LAYER =
        ResourceLocation.fromNamespaceAndPath(DailyQuests.MODID, "quest_sidebar");

    @SubscribeEvent
    public static void registerLayer(RegisterGuiLayersEvent event) {
        event.registerAboveAll(HUD_LAYER, QuestHudOverlay::renderLayer);
    }

    private static void renderLayer(GuiGraphics graphics, DeltaTracker delta) {
        if (!Config.ENABLE_SCOREBOARD_OVERLAY.get()) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.screen != null) return;
        if (!ClientQuestData.hasData()) return;

        // Check if sidebar is occupied
        Objective sidebar = mc.player.getScoreboard().getDisplayObjective(DisplaySlot.SIDEBAR);
        if (sidebar != null) return;

        // Only show accepted quests
        var accepted = ClientQuestData.getAcceptedQuestIds();
        if (accepted.isEmpty()) return;

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int x = screenWidth - 150;
        int y = 10;

        // Background
        int entryCount = accepted.size();
        int bgHeight = 22 + entryCount * 12;
        graphics.fill(x - 4, y - 4, x + 145, y + bgHeight, 0x80000000);

        // Title
        graphics.drawString(mc.font,
            "Quests " + entryCount + "/3", x, y, 0xFFFFFF);
        y += 14;

        // Only show accepted quests
        for (var entry : ClientQuestData.getEntries()) {
            if (!accepted.contains(entry.questId())) continue;

            String desc;
            // Build short description from questId
            String questId = entry.questId();
            int underscoreIdx = questId.indexOf('_');
            String target = underscoreIdx >= 0 ? questId.substring(underscoreIdx + 1) : questId;
            // Clean up target name for display
            target = target.replace('_', ' ');
            // Capitalize
            if (target.length() > 0) {
                target = Character.toUpperCase(target.charAt(0)) + target.substring(1);
            }
            // Truncate if too long
            if (target.length() > 14) target = target.substring(0, 13);

            if (entry.completed()) {
                desc = target + " (✓)";
            } else {
                desc = target + " (" + entry.currentAmount() + "/" + entry.requiredAmount() + ")";
            }

            int color = entry.completed() ? 0x55FF55 : 0xCCCCCC;
            graphics.drawString(mc.font, desc, x, y, color);
            y += 12;
        }
    }
}
