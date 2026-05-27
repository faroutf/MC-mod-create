package com.faroutf.daily_quests.network;

import com.faroutf.daily_quests.DailyQuests;
import com.faroutf.daily_quests.quest.QuestManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record CancelQuestPacket(String questId) implements CustomPacketPayload {

    public static final Type<CancelQuestPacket> TYPE =
        new Type<>(ResourceLocation.fromNamespaceAndPath(DailyQuests.MODID, "cancel_quest"));

    public static final StreamCodec<FriendlyByteBuf, CancelQuestPacket> STREAM_CODEC = StreamCodec.composite(
        StreamCodec.of(FriendlyByteBuf::writeUtf, FriendlyByteBuf::readUtf),
        CancelQuestPacket::questId,
        CancelQuestPacket::new
    );

    public static void handle(CancelQuestPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                QuestManager.cancelQuest(player, packet.questId());
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
