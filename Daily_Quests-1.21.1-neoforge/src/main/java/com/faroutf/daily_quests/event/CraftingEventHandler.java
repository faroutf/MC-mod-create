package com.faroutf.daily_quests.event;

import com.faroutf.daily_quests.DailyQuests;
import com.faroutf.daily_quests.quest.QuestCategory;
import com.faroutf.daily_quests.quest.QuestManager;
import com.faroutf.daily_quests.quest.QuestPools;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = DailyQuests.MODID)
public class CraftingEventHandler {
    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(event.getCrafting().getItem());

        if (!QuestPools.CRAFTING_TARGETS.containsKey(itemId)) return;

        QuestManager.advanceQuest(player, QuestCategory.CRAFTING, itemId, event.getCrafting().getCount());
    }
}
