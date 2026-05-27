package com.faroutf.daily_quests.client.gui;

import com.faroutf.daily_quests.client.ClientQuestData;
import com.faroutf.daily_quests.network.AcceptQuestPacket;
import com.faroutf.daily_quests.network.CancelQuestPacket;
import com.faroutf.daily_quests.network.ClaimRewardPacket;
import com.faroutf.daily_quests.network.SyncQuestDataPacket;
import com.faroutf.daily_quests.quest.QuestCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

public class QuestScreen extends Screen {
    private static final int WIDTH = 440;
    private static final int HEIGHT = 400;
    private static final int QUEST_ROW_HEIGHT = 20;

    private int leftPos, topPos;

    public QuestScreen() {
        super(Component.translatable("screen.daily_quests.quests"));
    }

    @Override
    protected void init() {
        this.leftPos = (this.width - WIDTH) / 2;
        this.topPos = (this.height - HEIGHT) / 2;

        // Claim button at the bottom, below the panel
        int buttonX = leftPos + WIDTH / 2 - 75;
        int buttonY = topPos + HEIGHT + 6;
        Button claimButton = Button.builder(
            Component.translatable("screen.daily_quests.claim_reward"),
            btn -> claimReward()
        ).bounds(buttonX, buttonY, 150, 20).build();
        this.addRenderableWidget(claimButton);

        // Accept/cancel buttons per quest row
        int y = topPos + 24;
        for (QuestCategory cat : QuestCategory.VALUES) {
            y += 16; // category header
            for (SyncQuestDataPacket.QuestProgressEntry entry : ClientQuestData.getEntriesForCategory(cat)) {
                if (entry == null) continue;
                String questId = entry.questId();

                if (entry.completed()) {
                    y += QUEST_ROW_HEIGHT;
                    continue;
                }

                if (ClientQuestData.isAccepted(questId)) {
                    // Cancel button
                    int btnX = leftPos + WIDTH - 55;
                    Button cancelBtn = Button.builder(
                        Component.literal("X"),
                        btn -> cancelQuest(questId)
                    ).bounds(btnX, y - 1, 16, 16).build();
                    this.addRenderableWidget(cancelBtn);
                } else {
                    // Accept button (only if can accept more)
                    boolean canAccept = ClientQuestData.canAcceptMore()
                        && ClientQuestData.canAcceptInCategory(cat);
                    if (canAccept) {
                        int btnX = leftPos + WIDTH - 55;
                        Button acceptBtn = Button.builder(
                            Component.literal("+"),
                            btn -> acceptQuest(questId)
                        ).bounds(btnX, y - 1, 16, 16).build();
                        this.addRenderableWidget(acceptBtn);
                    }
                }

                y += QUEST_ROW_HEIGHT;
            }
            y += 4; // gap between categories
        }
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
        int y = topPos + 26;
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
            statusText = "Ready to claim! (" + completed + "/3)";
            color = 0xFFFF55;
        } else {
            statusText = "Accepted: " + ClientQuestData.getAcceptedCount() + "/3 | " +
                Component.translatable("screen.daily_quests.categories_progress", completed).getString();
            color = 0xAAAAAA;
        }
        graphics.drawCenteredString(font, statusText, leftPos + WIDTH / 2, topPos + HEIGHT - 14, color);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    private int renderCategory(GuiGraphics graphics, QuestCategory cat, int x, int y) {
        List<SyncQuestDataPacket.QuestProgressEntry> entries = ClientQuestData.getEntriesForCategory(cat);
        boolean catComplete = ClientQuestData.getCompletedCategories().contains(cat);

        // Category header
        String catName = Component.translatable("category.daily_quests." + cat.getName()).getString();
        String marker = catComplete ? " ✓" : "";
        int headerColor = catComplete ? 0x55FF55 : 0xFFAA00;
        graphics.drawString(font, catName + marker, x, y, headerColor);
        y += 14;

        // Quest rows
        for (SyncQuestDataPacket.QuestProgressEntry entry : entries) {
            if (entry == null) continue;

            boolean questComplete = entry.completed();
            boolean isAccepted = ClientQuestData.isAccepted(entry.questId());
            String desc = questDescription(cat, entry);

            String progress;
            int textColor;
            if (questComplete) {
                progress = " (✓)";
                textColor = 0x55FF55;
            } else if (isAccepted) {
                progress = " (" + entry.currentAmount() + "/" + entry.requiredAmount() + ")";
                textColor = 0xFFCC00;
            } else {
                progress = " (" + entry.requiredAmount() + ")";
                textColor = 0x888888;
            }

            graphics.drawString(font, "  " + desc + progress, x + 8, y, textColor);

            // Progress bar for accepted quests
            if (isAccepted && !questComplete && entry.requiredAmount() > 0) {
                int barX = x + 200;
                int barY = y + 4;
                int barW = 110;
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
        String questId = entry.questId();
        String targetPart = questId.substring(cat.getName().length() + 1);
        String key = "quest.daily_quests." + cat.getName() + "." + targetPart;
        return Component.translatable(key).getString();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void acceptQuest(String questId) {
        PacketDistributor.sendToServer(new AcceptQuestPacket(questId));
    }

    private void cancelQuest(String questId) {
        PacketDistributor.sendToServer(new CancelQuestPacket(questId));
    }

    private void claimReward() {
        PacketDistributor.sendToServer(new ClaimRewardPacket());
        if (minecraft != null) {
            minecraft.setScreen(null);
        }
    }
}
