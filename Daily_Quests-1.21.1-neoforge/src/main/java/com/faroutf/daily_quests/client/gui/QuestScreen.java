package com.faroutf.daily_quests.client.gui;

import com.faroutf.daily_quests.client.ClientQuestData;
import com.faroutf.daily_quests.network.ClaimRewardPacket;
import com.faroutf.daily_quests.network.SyncQuestDataPacket;
import com.faroutf.daily_quests.quest.QuestCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

public class QuestScreen extends Screen {
    private static final int WIDTH = 430;
    private static final int HEIGHT = 300;
    private static final int CATEGORY_HEIGHT = 24;
    private static final int QUEST_ROW_HEIGHT = 18;
    private static final int CATEGORY_COUNT = 5;

    private int leftPos, topPos;
    private Button claimButton;

    public QuestScreen() {
        super(Component.translatable("screen.daily_quests.quests"));
    }

    @Override
    protected void init() {
        this.leftPos = (this.width - WIDTH) / 2;
        this.topPos = (this.height - HEIGHT) / 2;

        int buttonX = leftPos + WIDTH / 2 - 75;
        int buttonY = topPos + HEIGHT - 30;
        this.claimButton = Button.builder(
            Component.translatable("screen.daily_quests.claim_reward"),
            btn -> claimReward()
        ).bounds(buttonX, buttonY, 150, 20).build();
        this.addRenderableWidget(claimButton);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics, mouseX, mouseY, partialTick);

        // Panel background
        graphics.fill(leftPos, topPos, leftPos + WIDTH, topPos + HEIGHT, 0xC0101010);
        graphics.fill(leftPos, topPos, leftPos + WIDTH, topPos + HEIGHT, 0x80FFFFFF);

        // Title
        int titleY = topPos + 6;
        graphics.drawCenteredString(font,
            Component.translatable("screen.daily_quests.quests").getString() + " — Day " + ClientQuestData.getQuestDay(),
            leftPos + WIDTH / 2, titleY, 0xFFFFFF);

        // Category sections
        int y = topPos + 24;
        for (QuestCategory cat : QuestCategory.VALUES) {
            y = renderCategory(graphics, cat, leftPos + 8, y);
            y += 4;
        }

        // Bottom status
        int completed = ClientQuestData.getCompletedPrimaryCount();
        boolean canClaim = ClientQuestData.canClaimReward();
        boolean claimed = ClientQuestData.isRewardClaimed();

        String statusText;
        int color;
        if (claimed) {
            statusText = Component.translatable("screen.daily_quests.reward_claimed").getString();
            color = 0x55FF55;
        } else if (canClaim) {
            statusText = "Ready to claim!" + " (" + completed + "/3)";
            color = 0xFFFF55;
        } else {
            statusText = Component.translatable("screen.daily_quests.categories_progress", completed).getString();
            color = 0xAAAAAA;
        }
        graphics.drawCenteredString(font, statusText, leftPos + WIDTH / 2, topPos + HEIGHT - 40, color);

        claimButton.active = canClaim;

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    private int renderCategory(GuiGraphics graphics, QuestCategory cat, int x, int y) {
        List<SyncQuestDataPacket.QuestProgressEntry> entries = ClientQuestData.getEntriesForCategory(cat);
        boolean isPrimary = ClientQuestData.getPrimaryCategories().contains(cat);
        boolean catComplete = ClientQuestData.getCompletedCategories().contains(cat);

        // Category header
        String catName = Component.translatable("category.daily_quests." + cat.getName()).getString();
        String header = (isPrimary ? "★ " : "  ") + catName;
        int headerColor = catComplete ? 0x55FF55 : (isPrimary ? 0xFFAA00 : 0xAAAAAA);
        graphics.drawString(font, header, x, y, headerColor);
        y += 14;

        // Quest rows
        for (SyncQuestDataPacket.QuestProgressEntry entry : entries) {
            if (entry == null) continue;

            String desc = questDescription(cat, entry);
            String progress = entry.completed() ? " ✓" : " (" + entry.currentAmount() + "/" + entry.requiredAmount() + ")";
            boolean questComplete = entry.completed();

            int textColor = questComplete ? 0x55FF55 : 0xCCCCCC;
            graphics.drawString(font, "  " + desc + progress, x + 8, y, textColor);

            if (!questComplete && entry.requiredAmount() > 0) {
                // Progress bar
                int barX = x + 220;
                int barY = y + 4;
                int barW = 100;
                int barH = 6;
                float ratio = (float) entry.currentAmount() / entry.requiredAmount();
                int fillW = (int) (barW * ratio);

                graphics.fill(barX, barY, barX + barW, barY + barH, 0xFF333333);
                int barColor = ratio > 0.66f ? 0xFF55FF55 : (ratio > 0.33f ? 0xFFFFFF55 : 0xFFFF5555);
                if (fillW > 0) {
                    graphics.fill(barX, barY, barX + fillW, barY + barH, barColor);
                }
            }

            y += QUEST_ROW_HEIGHT;
        }

        return y;
    }

    private String questDescription(QuestCategory cat, SyncQuestDataPacket.QuestProgressEntry entry) {
        // Build description key: quest.daily_quests.<category>.<target>
        String questId = entry.questId();
        // The questId format is: <category>_<target>
        String targetPart = questId.substring(cat.getName().length() + 1);
        String key = "quest.daily_quests." + cat.getName() + "." + targetPart;
        return Component.translatable(key).getString();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void claimReward() {
        PacketDistributor.sendToServer(new ClaimRewardPacket());
        if (minecraft != null) {
            minecraft.setScreen(null);
        }
    }
}
