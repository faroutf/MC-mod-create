package com.faroutf.daily_quests.quest;

public enum QuestCategory {
    COMBAT("combat"),
    CRAFTING("crafting"),
    MINING("mining"),
    FARMING("farming"),
    HUSBANDRY("husbandry");

    private final String name;

    QuestCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static final QuestCategory[] VALUES = values();
}
