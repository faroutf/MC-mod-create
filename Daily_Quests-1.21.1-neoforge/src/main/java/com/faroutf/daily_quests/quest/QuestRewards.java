package com.faroutf.daily_quests.quest;

import com.faroutf.daily_quests.Config;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public final class QuestRewards {
    private QuestRewards() {}

    private record Tier(String name, int weight, List<ItemStack> items) {}

    private static final List<Tier> TIERS = List.of(
        new Tier("coal", 50, List.of(
            new ItemStack(Items.COAL, 4),
            new ItemStack(Items.COPPER_INGOT, 2),
            new ItemStack(Items.COAL, 8),
            new ItemStack(Items.COPPER_INGOT, 6)
        )),
        new Tier("iron", 35, List.of(
            new ItemStack(Items.IRON_INGOT, 2),
            new ItemStack(Items.GOLD_INGOT, 1),
            new ItemStack(Items.REDSTONE, 4),
            new ItemStack(Items.LAPIS_LAZULI, 4),
            new ItemStack(Items.IRON_INGOT, 6),
            new ItemStack(Items.GOLD_INGOT, 4)
        )),
        new Tier("diamond", 15, List.of(
            new ItemStack(Items.DIAMOND, 1),
            new ItemStack(Items.EMERALD, 1),
            new ItemStack(Items.DIAMOND, 2),
            new ItemStack(Items.EMERALD, 2),
            new ItemStack(Items.ANCIENT_DEBRIS, 1)
        ))
    );

    public static void grantRandomReward(ServerPlayer player) {
        RandomSource random = player.getRandom();

        Tier selected = selectWeightedRandom(random);
        if (selected == null) return;

        List<ItemStack> items = selected.items();
        ItemStack reward = items.get(random.nextInt(items.size())).copy();

        if (!player.getInventory().add(reward)) {
            player.drop(reward, false);
        }

        int xpMin = Config.REWARD_XP_MIN.get();
        int xpMax = Config.REWARD_XP_MAX.get();
        int xp = xpMin + random.nextInt(Math.max(1, xpMax - xpMin + 1));
        player.giveExperiencePoints(xp);
    }

    private static Tier selectWeightedRandom(RandomSource random) {
        int totalWeight = TIERS.stream().mapToInt(Tier::weight).sum();
        int roll = random.nextInt(totalWeight);
        int cumulative = 0;
        for (Tier tier : TIERS) {
            cumulative += tier.weight();
            if (roll < cumulative) return tier;
        }
        return TIERS.get(TIERS.size() - 1);
    }
}
