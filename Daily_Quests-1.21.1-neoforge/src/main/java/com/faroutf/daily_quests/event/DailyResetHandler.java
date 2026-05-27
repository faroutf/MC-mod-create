package com.faroutf.daily_quests.event;

import com.faroutf.daily_quests.DailyQuests;
import com.faroutf.daily_quests.ModAttachments;
import com.faroutf.daily_quests.quest.PlayerQuestData;
import com.faroutf.daily_quests.quest.QuestManager;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = DailyQuests.MODID, bus = EventBusSubscriber.Bus.GAME)
public class DailyResetHandler {
    private static int tickCounter = 0;

    // Sync data immediately when player logs in
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        PlayerQuestData data = player.getData(ModAttachments.PLAYER_QUEST_DATA.get());
        long currentDay = player.serverLevel().getDayTime() / 24000;

        if (data.getQuestDay() != currentDay || !data.hasQuests()) {
            QuestManager.clearDefinitions();
            QuestManager.generateDailyQuests(player, data);
            sendQuestSummaryChat(player, data);
        } else {
            // Same day reconnect — just sync existing data to client
            QuestManager.syncToPlayer(player);
        }
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        if (++tickCounter < 20) return;
        tickCounter = 0;

        MinecraftServer server = event.getServer();
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            PlayerQuestData data = player.getData(ModAttachments.PLAYER_QUEST_DATA.get());
            long currentDay = player.serverLevel().getDayTime() / 24000;

            if (data.getQuestDay() != currentDay) {
                QuestManager.clearDefinitions();
                QuestManager.generateDailyQuests(player, data);
                sendQuestSummaryChat(player, data);
            }
        }
    }

    private static void sendQuestSummaryChat(ServerPlayer player, PlayerQuestData data) {
        player.sendSystemMessage(Component.translatable("chat.daily_quests.daily_title"));
        player.sendSystemMessage(Component.literal("Day " + data.getQuestDay()));

        for (var entry : data.getAllQuests().entrySet()) {
            var cat = entry.getKey();
            var quests = entry.getValue();
            if (quests.isEmpty()) continue;

            boolean isPrimary = data.isPrimaryCategory(cat);
            String prefix = isPrimary ? " §6★ " : "   ";
            player.sendSystemMessage(Component.literal(prefix +
                Component.translatable("category.daily_quests." + cat.getName()).getString()));

            for (var q : quests) {
                var def = QuestManager.getDefinitionById(q.getQuestId());
                if (def != null) {
                    player.sendSystemMessage(Component.literal("     " +
                        Component.translatable(def.descriptionKey()).getString() +
                        " (0/" + q.getRequiredAmount() + ")"));
                }
            }
        }

        player.sendSystemMessage(Component.translatable("chat.daily_quests.category_complete"));
    }
}
