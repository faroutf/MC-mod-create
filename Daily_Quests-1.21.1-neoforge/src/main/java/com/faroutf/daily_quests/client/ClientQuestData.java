package com.faroutf.daily_quests.client;

import com.faroutf.daily_quests.client.gui.QuestToast;
import com.faroutf.daily_quests.quest.QuestCategory;
import com.faroutf.daily_quests.network.SyncQuestDataPacket;

import java.util.*;

public final class ClientQuestData {
    private ClientQuestData() {}

    private static long questDay = -1;
    private static final List<QuestCategory> primaryCategories = new ArrayList<>();
    private static final List<SyncQuestDataPacket.QuestProgressEntry> entries = new ArrayList<>();
    private static final Set<QuestCategory> completedCategories = EnumSet.noneOf(QuestCategory.class);
    private static boolean rewardClaimed;
    private static boolean hasData;
    private static final Set<String> previouslyCompletedQuestIds = new HashSet<>();

    public static void updateFromPacket(SyncQuestDataPacket packet) {
        // Detect new completions for toast
        if (hasData && packet.questDay() == questDay) {
            for (var entry : packet.entries()) {
                if (entry.completed() && !previouslyCompletedQuestIds.contains(entry.questId())) {
                    QuestToast.show("Quest Complete: " + entry.questId(), 0x55FF55);
                }
            }
            // Check for new category completion
            for (QuestCategory cat : packet.completedCategories()) {
                if (!completedCategories.contains(cat)) {
                    String catName = net.minecraft.network.chat.Component.translatable(
                        "category.daily_quests." + cat.getName()).getString();
                    QuestToast.show("Category Complete: " + catName, 0xFFAA00);
                }
            }
        }

        questDay = packet.questDay();
        primaryCategories.clear();
        primaryCategories.addAll(packet.primaryCategories());
        entries.clear();
        entries.addAll(packet.entries());
        completedCategories.clear();
        completedCategories.addAll(packet.completedCategories());
        rewardClaimed = packet.rewardClaimed();
        hasData = true;

        previouslyCompletedQuestIds.clear();
        for (var entry : packet.entries()) {
            if (entry.completed()) {
                previouslyCompletedQuestIds.add(entry.questId());
            }
        }
    }

    public static void clear() {
        questDay = -1;
        primaryCategories.clear();
        entries.clear();
        completedCategories.clear();
        rewardClaimed = false;
        hasData = false;
    }

    public static long getQuestDay() { return questDay; }
    public static List<QuestCategory> getPrimaryCategories() { return Collections.unmodifiableList(primaryCategories); }
    public static List<SyncQuestDataPacket.QuestProgressEntry> getEntries() { return Collections.unmodifiableList(entries); }
    public static List<SyncQuestDataPacket.QuestProgressEntry> getEntriesForCategory(QuestCategory cat) {
        return entries.stream().filter(e -> e.category() == cat).toList();
    }
    public static Set<QuestCategory> getCompletedCategories() { return Collections.unmodifiableSet(completedCategories); }
    public static boolean isRewardClaimed() { return rewardClaimed; }
    public static boolean hasData() { return hasData; }

    public static int getCompletedPrimaryCount() {
        int count = 0;
        for (QuestCategory cat : primaryCategories) {
            if (completedCategories.contains(cat)) count++;
        }
        return count;
    }

    public static boolean canClaimReward() {
        return hasData && !rewardClaimed && getCompletedPrimaryCount() >= 3;
    }
}
