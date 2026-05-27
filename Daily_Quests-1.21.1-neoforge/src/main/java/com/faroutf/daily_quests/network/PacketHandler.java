package com.faroutf.daily_quests.network;

import com.faroutf.daily_quests.DailyQuests;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.minecraft.server.level.ServerPlayer;

@EventBusSubscriber(modid = DailyQuests.MODID, bus = EventBusSubscriber.Bus.MOD)
public class PacketHandler {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(DailyQuests.MODID).versioned("1");

        registrar.playToClient(
            SyncQuestDataPacket.TYPE,
            SyncQuestDataPacket.STREAM_CODEC,
            SyncQuestDataPacket::handle
        );
    }

    public static void sendToPlayer(SyncQuestDataPacket packet, ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, packet);
    }
}
