package com.faroutf.daily_quests.client.gui;

import com.faroutf.daily_quests.client.ClientQuestData;
import com.faroutf.daily_quests.network.AcceptQuestPacket;
import com.faroutf.daily_quests.network.CancelQuestPacket;
import com.faroutf.daily_quests.network.ClaimRewardPacket;
import com.faroutf.daily_quests.network.SyncQuestDataPacket;
import com.faroutf.daily_quests.quest.QuestCategory;
import com.faroutf.daily_quests.quest.QuestManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

public class QuestScreen extends Screen {
    private static final int WIDTH = 420;
    private static final int QUEST_ROW_HEIGHT = 22;

    private int leftPos, topPos, panelHeight;
    private int lastDataVersion = -1;

    public QuestScreen() {
        super(Component.translatable("screen.daily_quests.quests"));
    }

    @Override
    protected void init() {
        this.panelHeight = Math.min(this.height - 20, 370);
        this.leftPos = (this.width - WIDTH) / 2;
        this.topPos = (this.height - panelHeight) / 2;
        rebuildButtons();
    }

    private void rebuildButtons() {
        this.clearWidgets();

        // Claim reward button at bottom center — vanilla stone-button style
        int btnW = 150;
        int btnX = leftPos + (WIDTH - btnW) / 2;
        int btnY = topPos + panelHeight - 26;
        this.addRenderableWidget(Button.builder(
            Component.translatable("screen.daily_quests.claim_reward"),
            btn -> claimReward()
        ).bounds(btnX, btnY, btnW, 20).build());

        // Per-quest accept/cancel buttons
        int y = topPos + 26;
        for (QuestCategory cat : QuestCategory.VALUES) {
            y += 16; // category header
            List<SyncQuestDataPacket.QuestProgressEntry> entries = ClientQuestData.getEntriesForCategory(cat);
            for (SyncQuestDataPacket.QuestProgressEntry entry : entries) {
                if (entry == null || entry.completed()) {
                    y += QUEST_ROW_HEIGHT;
                    continue;
                }

                String questId = entry.questId();
                int smallBtnW = 24;
                int smallBtnX = leftPos + WIDTH - smallBtnW - 20;

                if (ClientQuestData.isAccepted(questId)) {
                    this.addRenderableWidget(Button.builder(
                        Component.literal("✕"), btn -> cancelQuest(questId)
                    ).bounds(smallBtnX, y, smallBtnW, 18).build());
                } else if (ClientQuestData.canAcceptMore() && ClientQuestData.canAcceptInCategory(cat)) {
                    this.addRenderableWidget(Button.builder(
                        Component.literal("✓"), btn -> acceptQuest(questId)
                    ).bounds(smallBtnX, y, smallBtnW, 18).build());
                }
                y += QUEST_ROW_HEIGHT;
            }
            y += 4;
        }
        lastDataVersion = ClientQuestData.getDataVersion();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        // Data changed? Rebuild interactable widgets
        if (ClientQuestData.getDataVersion() != lastDataVersion) {
            rebuildButtons();
        }

        // 1. Vanilla blur on world
        renderBackground(graphics, mouseX, mouseY, partialTick);

        // 2. Opaque black covers blur completely
        graphics.fill(0, 0, this.width, this.height, 0xF0101010);

        // 3. Panel: vanilla-style container border (light grey outer, black middle, dark inner)
        int x0 = leftPos, y0 = topPos;
        int x1 = leftPos + WIDTH, y1 = topPos + panelHeight;
        graphics.fill(x0 - 2, y0 - 2, x1 + 2, y1 + 2, 0xFFC6C6C6); // outer border
        graphics.fill(x0 - 1, y0 - 1, x1 + 1, y1 + 1, 0xFF000000); // black edge
        graphics.fill(x0, y0, x1, y1, 0xFF1E1E1E);                // dark interior

        // 4. Title centered
        graphics.drawCenteredString(font,
            Component.translatable("screen.daily_quests.quests").getString() + " — Day " + ClientQuestData.getQuestDay(),
            leftPos + WIDTH / 2, topPos + 8, 0xFFFFFFFF);

        // 5. Category rows with item icons
        int y = topPos + 26;
        for (QuestCategory cat : QuestCategory.VALUES) {
            y += 16;
            List<SyncQuestDataPacket.QuestProgressEntry> entries = ClientQuestData.getEntriesForCategory(cat);
            boolean catComplete = ClientQuestData.getCompletedCategories().contains(cat);

            String catName = Component.translatable("category.daily_quests." + cat.getName()).getString();
            int hdrColor = catComplete ? 0xFF55FF55 : 0xFFFFAA00;
            graphics.drawString(font, (catComplete ? "✓ " : "  ") + catName, leftPos + 12, y - 14, hdrColor);

            for (SyncQuestDataPacket.QuestProgressEntry entry : entries) {
                if (entry == null) continue;
                renderQuestRow(graphics, leftPos + 12, y, cat, entry);
                y += QUEST_ROW_HEIGHT;
            }
            y += 4;
        }

        // 6. Bottom status text
        int completed = ClientQuestData.getCompletedPrimaryCount();
        boolean canClaim = ClientQuestData.canClaimReward();
        boolean claimed = ClientQuestData.isRewardClaimed();
        String status;
        int sc;
        if (claimed) { status = "✓ Reward Claimed!"; sc = 0xFF55FF55; }
        else if (canClaim) { status = "★ Ready! (" + completed + "/3)"; sc = 0xFFFFFF55; }
        else { status = "Accepted: " + ClientQuestData.getAcceptedCount() + "/3  |  Done: " + completed + "/3"; sc = 0xFFAAAAAA; }
        graphics.drawCenteredString(font, status, leftPos + WIDTH / 2, topPos + panelHeight - 36, sc);

        // 7. Render widgets (buttons) manually — NOT super.render() which calls renderBackground again
        for (var renderable : this.renderables) {
            renderable.render(graphics, mouseX, mouseY, partialTick);
        }
    }

    private void renderQuestRow(GuiGraphics graphics, int x, int y, QuestCategory cat,
                                 SyncQuestDataPacket.QuestProgressEntry entry) {
        boolean questComplete = entry.completed();
        boolean isAccepted = ClientQuestData.isAccepted(entry.questId());
        String desc = questDescription(cat, entry);

        // Item icon
        ItemStack icon = getQuestIcon(cat, entry.questId());
        if (!icon.isEmpty()) {
            graphics.renderItem(icon, x, y);
        }

        // Text + progress
        String line;
        int textColor;
        if (questComplete) {
            line = desc + " (✓)";
            textColor = 0xFF55FF55;
        } else if (isAccepted) {
            line = desc + " (" + entry.currentAmount() + "/" + entry.requiredAmount() + ")";
            textColor = 0xFFFFCC00;
            // Progress bar
            int barX = x + 200;
            int barW = 100;
            float ratio = (float) entry.currentAmount() / entry.requiredAmount();
            graphics.fill(barX, y + 5, barX + barW, y + 11, 0xFF333333);
            if (ratio > 0) {
                int barC = ratio > 0.66f ? 0xFF55FF55 : (ratio > 0.33f ? 0xFFFFFF55 : 0xFFFF5555);
                graphics.fill(barX, y + 5, barX + (int)(barW * ratio), y + 11, barC);
            }
        } else {
            line = desc + " (" + entry.requiredAmount() + ")";
            textColor = 0xFFAAAAAA;
        }

        graphics.drawString(font, " " + line, x + 18, y + 2, textColor);
    }

    private ItemStack getQuestIcon(QuestCategory cat, String questId) {
        var def = QuestManager.getDefinitionById(questId);
        if (def != null && !def.displayIcon().isEmpty()) {
            return def.displayIcon();
        }
        return switch (cat) {
            case COMBAT -> new ItemStack(Items.IRON_SWORD);
            case CRAFTING -> new ItemStack(Items.CRAFTING_TABLE);
            case MINING -> new ItemStack(Items.IRON_PICKAXE);
            case FARMING -> new ItemStack(Items.WHEAT);
            case HUSBANDRY -> new ItemStack(Items.SADDLE);
        };
    }

    private String questDescription(QuestCategory cat, SyncQuestDataPacket.QuestProgressEntry entry) {
        String questId = entry.questId();
        String targetPart = questId.substring(cat.getName().length() + 1);
        String key = "quest.daily_quests." + cat.getName() + "." + targetPart;
        String translated = Component.translatable(key).getString();
        // Truncate long names to fit
        if (translated.length() > 24) translated = translated.substring(0, 23);
        return translated;
    }

    @Override
    public boolean isPauseScreen() { return false; }

    private void acceptQuest(String questId) {
        PacketDistributor.sendToServer(new AcceptQuestPacket(questId));
    }
    private void cancelQuest(String questId) {
        PacketDistributor.sendToServer(new CancelQuestPacket(questId));
    }
    private void claimReward() {
        PacketDistributor.sendToServer(new ClaimRewardPacket());
        if (minecraft != null) minecraft.setScreen(null);
    }
}
