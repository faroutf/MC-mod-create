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
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = DailyQuests.MODID)
public class CombatEventHandler {
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity().level().isClientSide()) return;

        if (!(event.getSource().getEntity() instanceof ServerPlayer player)) return;

        ResourceLocation entityId = BuiltInRegistries.ENTITY_TYPE.getKey(event.getEntity().getType());

        if (!QuestPools.COMBAT_TARGETS.containsKey(entityId)) return;

        QuestManager.advanceQuest(player, QuestCategory.COMBAT, entityId, 1);
    }
}
