package com.faroutf.daily_quests.event;

import com.faroutf.daily_quests.DailyQuests;
import com.faroutf.daily_quests.quest.QuestCategory;
import com.faroutf.daily_quests.quest.QuestManager;
import com.faroutf.daily_quests.quest.QuestPools;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = DailyQuests.MODID)
public class MiningEventHandler {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getPlayer().level().isClientSide()) return;
        if (!(event.getPlayer() instanceof ServerPlayer player)) return;
        if (player.isCreative()) return;

        Block block = event.getState().getBlock();
        ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(block);

        if (!QuestPools.MINING_TARGETS.containsKey(blockId)) return;

        QuestManager.advanceQuest(player, QuestCategory.MINING, blockId, 1);
    }
}
