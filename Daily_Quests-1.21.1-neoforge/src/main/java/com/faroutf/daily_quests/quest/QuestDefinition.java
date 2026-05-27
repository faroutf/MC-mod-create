package com.faroutf.daily_quests.quest;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public record QuestDefinition(
    String questId,
    QuestCategory category,
    ResourceLocation targetId,
    int requiredAmount,
    ItemStack displayIcon,
    String descriptionKey
) {}
