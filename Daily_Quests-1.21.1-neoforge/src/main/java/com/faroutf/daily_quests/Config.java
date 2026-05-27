package com.faroutf.daily_quests;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.DoubleValue QUEST_AMOUNT_MULTIPLIER = BUILDER
            .comment("Multiplier for quest required amounts. 1.0 = default.")
            .defineInRange("questAmountMultiplier", 1.0, 0.1, 10.0);

    public static final ModConfigSpec.IntValue REWARD_XP_MIN = BUILDER
            .comment("Minimum XP granted for daily reward")
            .defineInRange("rewardXpMin", 100, 0, 10000);

    public static final ModConfigSpec.IntValue REWARD_XP_MAX = BUILDER
            .comment("Maximum XP granted for daily reward")
            .defineInRange("rewardXpMax", 200, 0, 10000);

    public static final ModConfigSpec.BooleanValue ENABLE_SCOREBOARD_OVERLAY = BUILDER
            .comment("Show quest summary on the right-side scoreboard if unoccupied")
            .define("enableScoreboardOverlay", true);

    public static final ModConfigSpec.BooleanValue ENABLE_TOAST_NOTIFICATIONS = BUILDER
            .comment("Show toast notifications when quests complete")
            .define("enableToastNotifications", true);

    static final ModConfigSpec SPEC = BUILDER.build();
}
