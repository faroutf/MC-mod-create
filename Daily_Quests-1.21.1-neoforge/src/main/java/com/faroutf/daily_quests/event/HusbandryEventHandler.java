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
import net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent;

@EventBusSubscriber(modid = DailyQuests.MODID)
public class HusbandryEventHandler {
    @SubscribeEvent
    public static void onBabySpawn(BabyEntitySpawnEvent event) {
        if (event.getChild() == null) return;
        if (event.getChild().level().isClientSide()) return;

        if (!(event.getCausedByPlayer() instanceof ServerPlayer player)) return;

        ResourceLocation entityId = BuiltInRegistries.ENTITY_TYPE.getKey(event.getChild().getType());

        if (!QuestPools.HUSBANDRY_TARGETS.containsKey(entityId)) return;

        QuestManager.advanceQuest(player, QuestCategory.HUSBANDRY, entityId, 1);
    }
}
