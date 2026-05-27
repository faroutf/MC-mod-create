package com.faroutf.daily_quests.quest;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.common.util.INBTSerializable;

import java.util.*;

public class PlayerQuestData implements INBTSerializable<CompoundTag> {
    private long questDay = -1;
    private final Set<QuestCategory> primaryCategories = EnumSet.noneOf(QuestCategory.class);
    private final Map<QuestCategory, List<QuestProgress>> quests = new EnumMap<>(QuestCategory.class);
    private final Set<QuestCategory> completedCategories = EnumSet.noneOf(QuestCategory.class);
    private boolean rewardClaimed = false;

    public PlayerQuestData() {
        for (QuestCategory cat : QuestCategory.VALUES) {
            quests.put(cat, new ArrayList<>());
        }
    }

    // --- Getters/Setters ---
    public long getQuestDay() { return questDay; }
    public void setQuestDay(long day) { this.questDay = day; }

    public Set<QuestCategory> getPrimaryCategories() { return primaryCategories; }
    public boolean isPrimaryCategory(QuestCategory cat) { return primaryCategories.contains(cat); }

    public List<QuestProgress> getQuests(QuestCategory category) { return quests.get(category); }

    public Map<QuestCategory, List<QuestProgress>> getAllQuests() { return quests; }

    public Set<QuestCategory> getCompletedCategories() { return completedCategories; }

    public boolean isRewardClaimed() { return rewardClaimed; }
    public void setRewardClaimed(boolean claimed) { this.rewardClaimed = claimed; }

    public boolean hasQuests() { return questDay >= 0 && !quests.values().stream().allMatch(List::isEmpty); }

    public int getCompletedCategoryCount() {
        int count = 0;
        for (QuestCategory cat : primaryCategories) {
            if (completedCategories.contains(cat)) count++;
        }
        return count;
    }

    public boolean canClaimReward() {
        return !rewardClaimed && getCompletedCategoryCount() >= 3;
    }

    // --- Serialization ---
    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putLong("questDay", questDay);

        ListTag primaryTag = new ListTag();
        for (QuestCategory cat : primaryCategories) {
            primaryTag.add(StringTag.valueOf(cat.name()));
        }
        tag.put("primaryCategories", primaryTag);

        CompoundTag questsTag = new CompoundTag();
        for (var entry : quests.entrySet()) {
            ListTag catList = new ListTag();
            for (QuestProgress p : entry.getValue()) {
                CompoundTag pt = new CompoundTag();
                pt.putString("questId", p.getQuestId());
                pt.putInt("current", p.getCurrentAmount());
                pt.putInt("required", p.getRequiredAmount());
                pt.putBoolean("completed", p.isCompleted());
                catList.add(pt);
            }
            questsTag.put(entry.getKey().name(), catList);
        }
        tag.put("quests", questsTag);

        ListTag completedTag = new ListTag();
        for (QuestCategory cat : completedCategories) {
            completedTag.add(StringTag.valueOf(cat.name()));
        }
        tag.put("completedCategories", completedTag);

        tag.putBoolean("rewardClaimed", rewardClaimed);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        questDay = tag.getLong("questDay");

        primaryCategories.clear();
        for (Tag t : tag.getList("primaryCategories", Tag.TAG_STRING)) {
            primaryCategories.add(QuestCategory.valueOf(t.getAsString()));
        }

        CompoundTag questsTag = tag.getCompound("quests");
        for (QuestCategory cat : QuestCategory.VALUES) {
            List<QuestProgress> list = new ArrayList<>();
            ListTag catList = questsTag.getList(cat.name(), Tag.TAG_COMPOUND);
            for (Tag t : catList) {
                CompoundTag pt = (CompoundTag) t;
                QuestProgress p = new QuestProgress();
                p.setQuestId(pt.getString("questId"));
                p.setCurrentAmount(pt.getInt("current"));
                p.setRequiredAmount(pt.getInt("required"));
                p.setCompleted(pt.getBoolean("completed"));
                list.add(p);
            }
            quests.put(cat, list);
        }

        completedCategories.clear();
        for (Tag t : tag.getList("completedCategories", Tag.TAG_STRING)) {
            completedCategories.add(QuestCategory.valueOf(t.getAsString()));
        }

        rewardClaimed = tag.getBoolean("rewardClaimed");
    }
}
