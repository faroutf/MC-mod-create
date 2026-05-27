package com.faroutf.daily_quests.network;

import com.faroutf.daily_quests.DailyQuests;
import com.faroutf.daily_quests.ModAttachments;
import com.faroutf.daily_quests.quest.PlayerQuestData;
import com.faroutf.daily_quests.quest.QuestManager;
import com.faroutf.daily_quests.quest.QuestRewards;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ClaimRewardPacket() implements CustomPacketPayload {

    public static final Type<ClaimRewardPacket> TYPE =
        new Type<>(ResourceLocation.fromNamespaceAndPath(DailyQuests.MODID, "claim_reward"));

    public static final StreamCodec<FriendlyByteBuf, ClaimRewardPacket> STREAM_CODEC =
        StreamCodec.unit(new ClaimRewardPacket());

    public static void handle(ClaimRewardPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                PlayerQuestData data = player.getData(ModAttachments.PLAYER_QUEST_DATA.get());
                if (QuestManager.canClaimReward(data)) {
                    QuestRewards.grantRandomReward(player);
                    data.setRewardClaimed(true);
                    QuestManager.syncToPlayer(player);
                }
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
