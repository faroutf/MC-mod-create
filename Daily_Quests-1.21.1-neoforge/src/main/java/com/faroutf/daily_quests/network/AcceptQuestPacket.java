package com.faroutf.daily_quests.network;

import com.faroutf.daily_quests.DailyQuests;
import com.faroutf.daily_quests.quest.QuestManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AcceptQuestPacket(String questId) implements CustomPacketPayload {

    public static final Type<AcceptQuestPacket> TYPE =
        new Type<>(ResourceLocation.fromNamespaceAndPath(DailyQuests.MODID, "accept_quest"));

    public static final StreamCodec<FriendlyByteBuf, AcceptQuestPacket> STREAM_CODEC = StreamCodec.composite(
        StreamCodec.of(FriendlyByteBuf::writeUtf, FriendlyByteBuf::readUtf),
        AcceptQuestPacket::questId,
        AcceptQuestPacket::new
    );

    public static void handle(AcceptQuestPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                QuestManager.acceptQuest(player, packet.questId());
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
