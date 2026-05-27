package com.faroutf.daily_quests.event;

import com.faroutf.daily_quests.DailyQuests;
import com.faroutf.daily_quests.quest.QuestCategory;
import com.faroutf.daily_quests.quest.QuestManager;
import com.faroutf.daily_quests.quest.QuestPools;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = DailyQuests.MODID)
public class FarmingEventHandler {

    // Harvest mature crops
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getPlayer().level().isClientSide()) return;
        if (!(event.getPlayer() instanceof ServerPlayer player)) return;
        if (player.isCreative()) return;

        BlockState state = event.getState();

        // Check if it's a mature crop
        if (state.getBlock() instanceof CropBlock crop) {
            if (!crop.isMaxAge(state)) return;
        } else {
            // Non-crop blocks (melon, pumpkin, etc.) - check if in harvest pool
            ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(state.getBlock());
            if (!QuestPools.FARMING_HARVEST_TARGETS.containsKey(blockId)) return;
            QuestManager.advanceQuest(player, QuestCategory.FARMING, blockId, 1);
            return;
        }

        // Map crop to its base ID (e.g., wheat[age=7] -> wheat)
        String blockPath = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath();
        // Remove "_crop" suffix if present, or use the base name
        String cropName = cropNameFromPath(blockPath);
        ResourceLocation cropId = ResourceLocation.withDefaultNamespace(cropName);

        if (QuestPools.FARMING_HARVEST_TARGETS.containsKey(cropId)) {
            QuestManager.advanceQuest(player, QuestCategory.FARMING, cropId, 1);
        }
    }

    // Plant seeds/plantables
    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        Entity placer = event.getEntity();
        if (placer == null || placer.level().isClientSide()) return;
        if (!(placer instanceof ServerPlayer player)) return;

        var item = event.getState().getBlock().asItem();
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);

        if (QuestPools.FARMING_PLANT_TARGETS.containsKey(itemId)) {
            QuestManager.advanceQuest(player, QuestCategory.FARMING, itemId, 1);
        }
    }

    private static String cropNameFromPath(String path) {
        // Handle common cases: "wheat" -> "wheat", "carrots" -> "carrots", etc.
        // The block registry uses "wheat", "carrots", "potatoes", "beetroots"
        return path;
    }
}
