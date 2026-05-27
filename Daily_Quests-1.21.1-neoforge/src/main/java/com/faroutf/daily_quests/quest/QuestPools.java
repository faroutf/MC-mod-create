package com.faroutf.daily_quests.quest;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.*;

public final class QuestPools {
    private QuestPools() {}

    public record PoolEntry(ResourceLocation targetId, int minAmount, int maxAmount, ItemStack displayIcon) {}

    public static final Map<ResourceLocation, int[]> COMBAT_TARGETS = new LinkedHashMap<>();
    public static final Map<ResourceLocation, int[]> CRAFTING_TARGETS = new LinkedHashMap<>();
    public static final Map<ResourceLocation, int[]> MINING_TARGETS = new LinkedHashMap<>();
    public static final Map<ResourceLocation, int[]> FARMING_HARVEST_TARGETS = new LinkedHashMap<>();
    public static final Map<ResourceLocation, int[]> FARMING_PLANT_TARGETS = new LinkedHashMap<>();
    public static final Map<ResourceLocation, int[]> HUSBANDRY_TARGETS = new LinkedHashMap<>();

    static {
        // COMBAT: hostile mobs, common 5-10, uncommon 3-7, rare 1-3
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("zombie"), new int[]{5, 10});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("skeleton"), new int[]{5, 10});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("creeper"), new int[]{5, 10});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("spider"), new int[]{5, 10});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("drowned"), new int[]{5, 10});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("husk"), new int[]{5, 10});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("stray"), new int[]{5, 10});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("slime"), new int[]{3, 7});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("enderman"), new int[]{3, 7});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("witch"), new int[]{3, 7});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("cave_spider"), new int[]{3, 7});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("phantom"), new int[]{3, 7});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("blaze"), new int[]{1, 3});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("wither_skeleton"), new int[]{1, 3});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("guardian"), new int[]{1, 3});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("hoglin"), new int[]{1, 3});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("piglin"), new int[]{3, 7});
        COMBAT_TARGETS.put(ResourceLocation.withDefaultNamespace("piglin_brute"), new int[]{1, 3});

        // CRAFTING: materials/blocks/consumables only, no tools/equipment
        CRAFTING_TARGETS.put(ResourceLocation.withDefaultNamespace("oak_planks"), new int[]{16, 48});
        CRAFTING_TARGETS.put(ResourceLocation.withDefaultNamespace("stick"), new int[]{8, 32});
        CRAFTING_TARGETS.put(ResourceLocation.withDefaultNamespace("torch"), new int[]{8, 32});
        CRAFTING_TARGETS.put(ResourceLocation.withDefaultNamespace("crafting_table"), new int[]{1, 4});
        CRAFTING_TARGETS.put(ResourceLocation.withDefaultNamespace("furnace"), new int[]{1, 4});
        CRAFTING_TARGETS.put(ResourceLocation.withDefaultNamespace("chest"), new int[]{2, 8});
        CRAFTING_TARGETS.put(ResourceLocation.withDefaultNamespace("ladder"), new int[]{8, 24});
        CRAFTING_TARGETS.put(ResourceLocation.withDefaultNamespace("glass"), new int[]{8, 32});
        CRAFTING_TARGETS.put(ResourceLocation.withDefaultNamespace("bricks"), new int[]{4, 16});
        CRAFTING_TARGETS.put(ResourceLocation.withDefaultNamespace("paper"), new int[]{3, 12});
        CRAFTING_TARGETS.put(ResourceLocation.withDefaultNamespace("bread"), new int[]{1, 6});
        CRAFTING_TARGETS.put(ResourceLocation.withDefaultNamespace("bookshelf"), new int[]{1, 4});
        CRAFTING_TARGETS.put(ResourceLocation.withDefaultNamespace("fence"), new int[]{8, 24});
        CRAFTING_TARGETS.put(ResourceLocation.withDefaultNamespace("beacon"), new int[]{1, 1});
        CRAFTING_TARGETS.put(ResourceLocation.withDefaultNamespace("conduit"), new int[]{1, 1});
        CRAFTING_TARGETS.put(ResourceLocation.withDefaultNamespace("target"), new int[]{1, 4});

        // MINING: ores with both regular and deepslate variants
        MINING_TARGETS.put(ResourceLocation.withDefaultNamespace("coal_ore"), new int[]{8, 16});
        MINING_TARGETS.put(ResourceLocation.withDefaultNamespace("deepslate_coal_ore"), new int[]{8, 16});
        MINING_TARGETS.put(ResourceLocation.withDefaultNamespace("copper_ore"), new int[]{8, 16});
        MINING_TARGETS.put(ResourceLocation.withDefaultNamespace("deepslate_copper_ore"), new int[]{8, 16});
        MINING_TARGETS.put(ResourceLocation.withDefaultNamespace("iron_ore"), new int[]{6, 12});
        MINING_TARGETS.put(ResourceLocation.withDefaultNamespace("deepslate_iron_ore"), new int[]{6, 12});
        MINING_TARGETS.put(ResourceLocation.withDefaultNamespace("gold_ore"), new int[]{4, 8});
        MINING_TARGETS.put(ResourceLocation.withDefaultNamespace("deepslate_gold_ore"), new int[]{4, 8});
        MINING_TARGETS.put(ResourceLocation.withDefaultNamespace("redstone_ore"), new int[]{4, 8});
        MINING_TARGETS.put(ResourceLocation.withDefaultNamespace("deepslate_redstone_ore"), new int[]{4, 8});
        MINING_TARGETS.put(ResourceLocation.withDefaultNamespace("lapis_ore"), new int[]{4, 8});
        MINING_TARGETS.put(ResourceLocation.withDefaultNamespace("deepslate_lapis_ore"), new int[]{4, 8});
        MINING_TARGETS.put(ResourceLocation.withDefaultNamespace("diamond_ore"), new int[]{1, 3});
        MINING_TARGETS.put(ResourceLocation.withDefaultNamespace("deepslate_diamond_ore"), new int[]{1, 3});
        MINING_TARGETS.put(ResourceLocation.withDefaultNamespace("emerald_ore"), new int[]{1, 2});
        MINING_TARGETS.put(ResourceLocation.withDefaultNamespace("deepslate_emerald_ore"), new int[]{1, 2});

        // FARMING harvest: mature crops
        FARMING_HARVEST_TARGETS.put(ResourceLocation.withDefaultNamespace("wheat"), new int[]{16, 48});
        FARMING_HARVEST_TARGETS.put(ResourceLocation.withDefaultNamespace("carrots"), new int[]{8, 32});
        FARMING_HARVEST_TARGETS.put(ResourceLocation.withDefaultNamespace("potatoes"), new int[]{8, 32});
        FARMING_HARVEST_TARGETS.put(ResourceLocation.withDefaultNamespace("beetroots"), new int[]{8, 32});
        FARMING_HARVEST_TARGETS.put(ResourceLocation.withDefaultNamespace("melon"), new int[]{4, 12});
        FARMING_HARVEST_TARGETS.put(ResourceLocation.withDefaultNamespace("pumpkin"), new int[]{4, 12});

        // FARMING plant: seeds/plantables
        FARMING_PLANT_TARGETS.put(ResourceLocation.withDefaultNamespace("wheat_seeds"), new int[]{8, 24});
        FARMING_PLANT_TARGETS.put(ResourceLocation.withDefaultNamespace("carrot"), new int[]{8, 24});
        FARMING_PLANT_TARGETS.put(ResourceLocation.withDefaultNamespace("potato"), new int[]{8, 24});
        FARMING_PLANT_TARGETS.put(ResourceLocation.withDefaultNamespace("beetroot_seeds"), new int[]{8, 24});
        FARMING_PLANT_TARGETS.put(ResourceLocation.withDefaultNamespace("melon_seeds"), new int[]{4, 12});
        FARMING_PLANT_TARGETS.put(ResourceLocation.withDefaultNamespace("pumpkin_seeds"), new int[]{4, 12});

        // HUSBANDRY: breedable farm animals
        HUSBANDRY_TARGETS.put(ResourceLocation.withDefaultNamespace("cow"), new int[]{1, 3});
        HUSBANDRY_TARGETS.put(ResourceLocation.withDefaultNamespace("sheep"), new int[]{1, 3});
        HUSBANDRY_TARGETS.put(ResourceLocation.withDefaultNamespace("pig"), new int[]{1, 3});
        HUSBANDRY_TARGETS.put(ResourceLocation.withDefaultNamespace("chicken"), new int[]{1, 3});
        HUSBANDRY_TARGETS.put(ResourceLocation.withDefaultNamespace("rabbit"), new int[]{1, 3});
        HUSBANDRY_TARGETS.put(ResourceLocation.withDefaultNamespace("goat"), new int[]{1, 2});
        HUSBANDRY_TARGETS.put(ResourceLocation.withDefaultNamespace("llama"), new int[]{1, 2});
        HUSBANDRY_TARGETS.put(ResourceLocation.withDefaultNamespace("horse"), new int[]{1, 2});
    }

    public static Map<ResourceLocation, int[]> getPool(QuestCategory category) {
        return switch (category) {
            case COMBAT -> COMBAT_TARGETS;
            case CRAFTING -> CRAFTING_TARGETS;
            case MINING -> MINING_TARGETS;
            case FARMING -> FARMING_HARVEST_TARGETS;
            case HUSBANDRY -> HUSBANDRY_TARGETS;
        };
    }

    public static ResourceLocation getTargetIdForCategory(QuestCategory category, ResourceLocation targetId) {
        return switch (category) {
            case COMBAT -> targetId;
            case CRAFTING -> targetId;
            case MINING -> targetId;
            case FARMING -> targetId;
            case HUSBANDRY -> targetId;
        };
    }

    public static boolean isTargetInPool(QuestCategory category, ResourceLocation targetId) {
        return getPool(category).containsKey(targetId);
    }

    public static ItemStack getDisplayIcon(QuestCategory category, ResourceLocation targetId) {
        return switch (category) {
            case COMBAT -> new ItemStack(Items.IRON_SWORD);
            case CRAFTING -> new ItemStack(Items.CRAFTING_TABLE);
            case MINING -> new ItemStack(Items.IRON_PICKAXE);
            case FARMING -> {
                if (FARMING_HARVEST_TARGETS.containsKey(targetId))
                    yield new ItemStack(Items.WHEAT);
                yield new ItemStack(Items.WHEAT_SEEDS);
            }
            case HUSBANDRY -> new ItemStack(Items.WHEAT);
        };
    }

    public static List<ResourceLocation> pickRandom(QuestCategory category, int count, RandomSource random) {
        Map<ResourceLocation, int[]> pool = getPool(category);
        List<ResourceLocation> all = new ArrayList<>(pool.keySet());
        Collections.shuffle(all, new java.util.Random(random.nextLong()));
        List<ResourceLocation> result = new ArrayList<>();
        for (int i = 0; i < Math.min(count, all.size()); i++) {
            result.add(all.get(i));
        }
        return result;
    }

    public static int[] getAmountRange(QuestCategory category, ResourceLocation targetId) {
        return getPool(category).get(targetId);
    }

    public static int rollAmount(QuestCategory category, ResourceLocation targetId, RandomSource random) {
        int[] range = getAmountRange(category, targetId);
        if (range == null) return 1;
        return range[0] + random.nextInt(range[1] - range[0] + 1);
    }
}
