package com.faroutf.daily_quests;

import com.faroutf.daily_quests.quest.PlayerQuestData;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
        DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, DailyQuests.MODID);

    public static final Supplier<AttachmentType<PlayerQuestData>> PLAYER_QUEST_DATA =
        ATTACHMENT_TYPES.register("player_quest_data",
            () -> AttachmentType.serializable(PlayerQuestData::new).build());
}
