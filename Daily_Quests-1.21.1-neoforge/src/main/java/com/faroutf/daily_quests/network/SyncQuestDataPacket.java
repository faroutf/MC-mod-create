package com.faroutf.daily_quests.network;

import com.faroutf.daily_quests.DailyQuests;
import com.faroutf.daily_quests.client.ClientQuestData;
import com.faroutf.daily_quests.quest.QuestCategory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public record SyncQuestDataPacket(
    long questDay,
    List<QuestCategory> primaryCategories,
    List<QuestProgressEntry> entries,
    Set<QuestCategory> completedCategories,
    boolean rewardClaimed
) implements CustomPacketPayload {

    public static final Type<SyncQuestDataPacket> TYPE =
        new Type<>(ResourceLocation.fromNamespaceAndPath(DailyQuests.MODID, "sync_quest_data"));

    public record QuestProgressEntry(
        String questId,
        QuestCategory category,
        int currentAmount,
        int requiredAmount,
        boolean completed
    ) {
        private static final StreamCodec<FriendlyByteBuf, QuestProgressEntry> STREAM_CODEC = StreamCodec.composite(
            StreamCodec.of(FriendlyByteBuf::writeUtf, FriendlyByteBuf::readUtf),
            QuestProgressEntry::questId,
            StreamCodec.of(
                (buf, cat) -> buf.writeByte(cat.ordinal()),
                buf -> QuestCategory.VALUES[buf.readByte()]
            ),
            QuestProgressEntry::category,
            StreamCodec.of(FriendlyByteBuf::writeVarInt, FriendlyByteBuf::readVarInt),
            QuestProgressEntry::currentAmount,
            StreamCodec.of(FriendlyByteBuf::writeVarInt, FriendlyByteBuf::readVarInt),
            QuestProgressEntry::requiredAmount,
            StreamCodec.of(FriendlyByteBuf::writeBoolean, FriendlyByteBuf::readBoolean),
            QuestProgressEntry::completed,
            QuestProgressEntry::new
        );
    }

    private static final StreamCodec<FriendlyByteBuf, QuestCategory> CATEGORY_CODEC = StreamCodec.of(
        (buf, cat) -> buf.writeByte(cat.ordinal()),
        buf -> QuestCategory.VALUES[buf.readByte()]
    );

    private static final StreamCodec<FriendlyByteBuf, Set<QuestCategory>> CATEGORY_SET_CODEC = StreamCodec.of(
        (buf, set) -> {
            buf.writeVarInt(set.size());
            for (QuestCategory cat : set) buf.writeByte(cat.ordinal());
        },
        buf -> {
            int size = buf.readVarInt();
            Set<QuestCategory> set = EnumSet.noneOf(QuestCategory.class);
            for (int i = 0; i < size; i++) set.add(QuestCategory.VALUES[buf.readByte()]);
            return set;
        }
    );

    public static final StreamCodec<FriendlyByteBuf, SyncQuestDataPacket> STREAM_CODEC = StreamCodec.composite(
        StreamCodec.of(FriendlyByteBuf::writeVarLong, FriendlyByteBuf::readVarLong),
        SyncQuestDataPacket::questDay,
        createListCodec(CATEGORY_CODEC),
        SyncQuestDataPacket::primaryCategories,
        createListCodec(QuestProgressEntry.STREAM_CODEC),
        SyncQuestDataPacket::entries,
        CATEGORY_SET_CODEC,
        SyncQuestDataPacket::completedCategories,
        StreamCodec.of(FriendlyByteBuf::writeBoolean, FriendlyByteBuf::readBoolean),
        SyncQuestDataPacket::rewardClaimed,
        SyncQuestDataPacket::new
    );

    private static <T> StreamCodec<FriendlyByteBuf, List<T>> createListCodec(StreamCodec<FriendlyByteBuf, T> elemCodec) {
        return StreamCodec.of(
            (buf, list) -> {
                buf.writeVarInt(list.size());
                for (T item : list) elemCodec.encode(buf, item);
            },
            buf -> {
                int size = buf.readVarInt();
                List<T> list = new ArrayList<>(size);
                for (int i = 0; i < size; i++) list.add(elemCodec.decode(buf));
                return list;
            }
        );
    }

    public static void handle(SyncQuestDataPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> ClientQuestData.updateFromPacket(packet));
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
