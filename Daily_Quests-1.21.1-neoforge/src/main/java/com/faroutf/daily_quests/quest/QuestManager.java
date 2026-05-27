package com.faroutf.daily_quests.quest;

import com.faroutf.daily_quests.Config;
import com.faroutf.daily_quests.DailyQuests;
import com.faroutf.daily_quests.ModAttachments;
import com.faroutf.daily_quests.network.PacketHandler;
import com.faroutf.daily_quests.network.SyncQuestDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

import java.util.*;

public final class QuestManager {
    private QuestManager() {}

    private static final Map<String, QuestDefinition> definitionRegistry = new HashMap<>();

    public static void generateDailyQuests(ServerPlayer player, PlayerQuestData data) {
        long currentDay = player.serverLevel().getDayTime() / 24000;
        data.setQuestDay(currentDay);
        data.getPrimaryCategories().clear();
        data.getCompletedCategories().clear();
        data.setRewardClaimed(false);

        RandomSource random = player.getRandom();

        // Select 3 primary categories randomly
        List<QuestCategory> shuffled = new ArrayList<>(List.of(QuestCategory.VALUES));
        Collections.shuffle(shuffled, new java.util.Random(random.nextLong()));
        for (int i = 0; i < 3; i++) {
            data.getPrimaryCategories().add(shuffled.get(i));
        }

        // Generate 3 quests for EACH of the 5 categories
        for (QuestCategory cat : QuestCategory.VALUES) {
            List<QuestProgress> quests = new ArrayList<>();
            List<ResourceLocation> targets = QuestPools.pickRandom(cat, 3, random);

            for (ResourceLocation targetId : targets) {
                int amount = QuestPools.rollAmount(cat, targetId, random);
                amount = (int) Math.max(1, amount * Config.QUEST_AMOUNT_MULTIPLIER.get());

                String questId = cat.getName() + "_" + targetId.getPath();
                QuestProgress progress = new QuestProgress(questId, amount);
                quests.add(progress);

                ItemStack icon = QuestPools.getDisplayIcon(cat, targetId);
                String descKey = "quest.daily_quests." + cat.getName() + "." + targetId.getPath();
                QuestDefinition def = new QuestDefinition(questId, cat, targetId, amount, icon, descKey);
                definitionRegistry.put(questId, def);
            }
            data.getAllQuests().put(cat, quests);
        }

        DailyQuests.LOGGER.debug("Generated daily quests for {} on day {}", player.getName().getString(), currentDay);
        syncToPlayer(player);
    }

    /**
     * Advance quest progress for a player. Called by event handlers.
     * @return true if the quest was newly completed
     */
    public static boolean advanceQuest(ServerPlayer player, QuestCategory category, ResourceLocation targetId, int amount) {
        PlayerQuestData data = player.getData(ModAttachments.PLAYER_QUEST_DATA.get());
        if (!data.hasQuests()) return false;

        List<QuestProgress> quests = data.getQuests(category);
        if (quests == null || quests.isEmpty()) return false;

        boolean anyCompleted = false;

        for (QuestProgress progress : quests) {
            if (progress.isCompleted()) continue;

            QuestDefinition def = definitionRegistry.get(progress.getQuestId());
            if (def == null || !def.targetId().equals(targetId)) continue;

            if (progress.advance(amount)) {
                anyCompleted = true;
                DailyQuests.LOGGER.debug("Player {} completed quest {}", player.getName().getString(), progress.getQuestId());

                // Check if this category is now complete (any 1 quest done)
                checkCategoryComplete(data, category);
            }
            break; // Only advance the first matching non-completed quest
        }

        if (anyCompleted) {
            syncToPlayer(player);
        }
        return anyCompleted;
    }

    private static void checkCategoryComplete(PlayerQuestData data, QuestCategory category) {
        List<QuestProgress> quests = data.getQuests(category);
        if (quests == null) return;

        for (QuestProgress p : quests) {
            if (p.isCompleted()) {
                data.getCompletedCategories().add(category);
                return;
            }
        }
    }

    public static int getCompletedPrimaryCount(PlayerQuestData data) {
        int count = 0;
        for (QuestCategory cat : data.getPrimaryCategories()) {
            if (data.getCompletedCategories().contains(cat)) count++;
        }
        return count;
    }

    public static boolean canClaimReward(PlayerQuestData data) {
        return !data.isRewardClaimed() && getCompletedPrimaryCount(data) >= 3;
    }

    public static QuestDefinition getDefinitionById(String questId) {
        return definitionRegistry.get(questId);
    }

    public static void clearDefinitions() {
        definitionRegistry.clear();
    }

    public static void syncToPlayer(ServerPlayer player) {
        PlayerQuestData data = player.getData(ModAttachments.PLAYER_QUEST_DATA.get());
        SyncQuestDataPacket packet = buildSyncPacket(data);
        PacketHandler.sendToPlayer(packet, player);
    }

    public static SyncQuestDataPacket buildSyncPacket(PlayerQuestData data) {
        List<QuestCategory> primary = new ArrayList<>(data.getPrimaryCategories());
        List<SyncQuestDataPacket.QuestProgressEntry> entries = new ArrayList<>();

        for (QuestCategory cat : QuestCategory.VALUES) {
            List<QuestProgress> quests = data.getQuests(cat);
            if (quests != null) {
                for (QuestProgress p : quests) {
                    entries.add(new SyncQuestDataPacket.QuestProgressEntry(
                        p.getQuestId(), cat,
                        p.getCurrentAmount(), p.getRequiredAmount(), p.isCompleted()
                    ));
                }
            }
        }

        Set<QuestCategory> completed = EnumSet.copyOf(data.getCompletedCategories());

        return new SyncQuestDataPacket(
            data.getQuestDay(), primary, entries, completed, data.isRewardClaimed()
        );
    }
}
