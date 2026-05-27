package com.faroutf.daily_quests.event;

import com.faroutf.daily_quests.DailyQuests;
import com.faroutf.daily_quests.quest.QuestCategory;
import com.faroutf.daily_quests.quest.QuestManager;
import com.faroutf.daily_quests.quest.QuestPools;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.animal.Animal;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = DailyQuests.MODID)
public class HusbandryEventHandler {
    @SubscribeEvent
    public static void onBabySpawn(net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent event) {
        if (event.getChild().level().isClientSide()) return;

        ServerPlayer player = findBreeder(event);
        if (player == null) return;

        ResourceLocation entityId = BuiltInRegistries.ENTITY_TYPE.getKey(event.getChild().getType());

        if (!QuestPools.HUSBANDRY_TARGETS.containsKey(entityId)) return;

        QuestManager.advanceQuest(player, QuestCategory.HUSBANDRY, entityId, 1);
    }

    private static ServerPlayer findBreeder(net.neoforged.neoforge.event.entity.living.BabyEntitySpawnEvent event) {
        // Check if the child is near any online player (within 5 blocks)
        for (var player : event.getChild().level().players()) {
            if (player instanceof ServerPlayer sp) {
                if (sp.distanceToSqr(event.getChild()) < 25) { // 5^2
                    return sp;
                }
            }
        }
        return null;
    }
}
