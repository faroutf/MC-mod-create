package com.faroutf.daily_quests.command;

import com.faroutf.daily_quests.DailyQuests;
import com.faroutf.daily_quests.ModAttachments;
import com.faroutf.daily_quests.quest.PlayerQuestData;
import com.faroutf.daily_quests.quest.QuestCategory;
import com.faroutf.daily_quests.quest.QuestManager;
import com.faroutf.daily_quests.quest.QuestProgress;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = DailyQuests.MODID)
public class DailyQuestsCommand {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(
            Commands.literal("dailyquests")
                .then(Commands.literal("show")
                    .executes(ctx -> showQuests(ctx.getSource())))
                .then(Commands.literal("progress")
                    .executes(ctx -> showProgress(ctx.getSource())))
                .then(Commands.literal("reset")
                    .requires(src -> src.hasPermission(2))
                    .executes(ctx -> resetQuests(ctx.getSource())))
                .then(Commands.literal("reroll")
                    .requires(src -> src.hasPermission(2))
                    .executes(ctx -> rerollQuests(ctx.getSource())))
                .executes(ctx -> showQuests(ctx.getSource()))
        );
    }

    private static int showQuests(CommandSourceStack source) {
        if (!(source.getEntity() instanceof ServerPlayer player)) {
            source.sendFailure(Component.literal("This command can only be used by a player"));
            return 0;
        }

        PlayerQuestData data = player.getData(ModAttachments.PLAYER_QUEST_DATA.get());
        if (!data.hasQuests()) {
            source.sendSuccess(() -> Component.translatable("command.daily_quests.no_data"), false);
            return 0;
        }

        source.sendSuccess(() -> Component.translatable("chat.daily_quests.daily_title"), false);
        source.sendSuccess(() -> Component.literal("Day " + data.getQuestDay()), false);

        for (var entry : data.getAllQuests().entrySet()) {
            QuestCategory cat = entry.getKey();
            boolean isPrimary = data.isPrimaryCategory(cat);
            boolean catComplete = data.getCompletedCategories().contains(cat);

            String status = catComplete ? " §a✓ " : " §7○ ";
            String prefix = isPrimary ? " §6★ " : "   ";
            source.sendSuccess(() -> Component.literal(prefix + status +
                Component.translatable("category.daily_quests." + cat.getName()).getString()), false);

            for (QuestProgress p : entry.getValue()) {
                var def = QuestManager.getDefinitionById(p.getQuestId());
                if (def != null) {
                    String progress = p.isCompleted() ? " §a✓" : " §7(" + p.getCurrentAmount() + "/" + p.getRequiredAmount() + ")";
                    source.sendSuccess(() -> Component.literal("      " +
                        Component.translatable(def.descriptionKey()).getString() + progress), false);
                }
            }
        }

        int completed = data.getCompletedCategoryCount();
        source.sendSuccess(() -> Component.translatable("command.daily_quests.progress", completed), false);

        return 1;
    }

    private static int showProgress(CommandSourceStack source) {
        if (!(source.getEntity() instanceof ServerPlayer player)) {
            source.sendFailure(Component.literal("This command can only be used by a player"));
            return 0;
        }

        PlayerQuestData data = player.getData(ModAttachments.PLAYER_QUEST_DATA.get());
        if (!data.hasQuests()) {
            source.sendSuccess(() -> Component.translatable("command.daily_quests.no_data"), false);
            return 0;
        }

        int completed = data.getCompletedCategoryCount();
        boolean canClaim = data.canClaimReward();
        String claimStatus = canClaim ? " §a可领取！使用 /dailyquests show 查看详情" : " §7（还需完成 " + (3 - completed) + " 个板块）";
        source.sendSuccess(() -> Component.literal(
            Component.translatable("command.daily_quests.progress", completed).getString() + claimStatus), false);

        return 1;
    }

    private static int resetQuests(CommandSourceStack source) {
        if (!(source.getEntity() instanceof ServerPlayer player)) return 0;

        PlayerQuestData data = player.getData(ModAttachments.PLAYER_QUEST_DATA.get());
        QuestManager.clearDefinitions();
        QuestManager.generateDailyQuests(player, data);
        source.sendSuccess(() -> Component.translatable("command.daily_quests.reset"), true);

        return 1;
    }

    private static int rerollQuests(CommandSourceStack source) {
        if (!(source.getEntity() instanceof ServerPlayer player)) return 0;

        PlayerQuestData data = player.getData(ModAttachments.PLAYER_QUEST_DATA.get());
        QuestManager.clearDefinitions();
        QuestManager.generateDailyQuests(player, data);
        source.sendSuccess(() -> Component.literal("Daily quests have been rerolled."), true);

        return 1;
    }
}
