package com.faroutf.daily_quests.client.gui;

import com.faroutf.daily_quests.Config;
import com.faroutf.daily_quests.DailyQuests;
import com.faroutf.daily_quests.client.ClientQuestData;
import com.faroutf.daily_quests.network.SyncQuestDataPacket;
import com.faroutf.daily_quests.quest.QuestCategory;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.minecraft.world.scores.DisplaySlot;
import net.minecraft.world.scores.Objective;

import java.util.List;

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

        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int x = screenWidth - 130;
        int y = 10;

        // Background
        graphics.fill(x - 4, y - 4, x + 125, y + 75, 0x80000000);

        // Title
        graphics.drawString(mc.font,
            Component.translatable("screen.daily_quests.quests"), x, y, 0xFFFFFF);
        y += 14;

        // Category summaries
        for (QuestCategory cat : QuestCategory.VALUES) {
            List<SyncQuestDataPacket.QuestProgressEntry> entries = ClientQuestData.getEntriesForCategory(cat);
            if (entries.isEmpty()) continue;

            boolean catComplete = ClientQuestData.getCompletedCategories().contains(cat);

            String catName = Component.translatable("category.daily_quests." + cat.getName()).getString();
            String shortName = catName.length() > 8 ? catName.substring(0, 7) : catName;

            int doneCount = (int) entries.stream().filter(SyncQuestDataPacket.QuestProgressEntry::completed).count();
            String line = shortName + " " + doneCount + "/" + entries.size();

            graphics.drawString(mc.font, line, x, y, catComplete ? 0x55FF55 : 0xAAAAAA);
            y += 11;
        }

        // Progress
        y += 2;
        int completed = ClientQuestData.getCompletedPrimaryCount();
        int color = completed >= 3 ? 0x55FF55 : 0xAAAAAA;
        graphics.drawString(mc.font, completed + "/3", x, y, color);
    }
}
