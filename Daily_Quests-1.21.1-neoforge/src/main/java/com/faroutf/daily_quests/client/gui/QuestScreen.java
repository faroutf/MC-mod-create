package com.faroutf.daily_quests.client.gui;

import com.faroutf.daily_quests.DailyQuests;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

public class QuestScreen extends Screen {

    private static final ResourceLocation BG_TEXTURE =
        ResourceLocation.fromNamespaceAndPath(DailyQuests.MODID, "textures/gui/quest_background.png");

    private static final int TEX_W = 256, TEX_H = 256;
    private static final int PANEL_W = 420;
    private static final int CONTENT_X = 18;
    private static final int ROW_H = 20;

    private int leftPos, topPos, panelH;
    private int lastVer = -1;

    public QuestScreen() {
        super(Component.translatable("screen.daily_quests.quests"));
    }

    @Override
    protected void init() {
        this.panelH = Math.min(this.height - 20, 370);
        this.leftPos = (this.width - PANEL_W) / 2;
        this.topPos = (this.height - panelH) / 2;
        rebuildButtons();
    }

    private void rebuildButtons() {
        this.clearWidgets();

        // Claim reward button
        int btnW = 150, btnH = 20;
        int btnX = leftPos + (PANEL_W - btnW) / 2;
        int btnY = topPos + panelH - btnH - 8;
        this.addRenderableWidget(Button.builder(
            Component.translatable("screen.daily_quests.claim_reward"),
            btn -> claimReward()
        ).bounds(btnX, btnY, btnW, btnH).build());

        // Per-quest accept/cancel
        int y = topPos + 30;
        for (QuestCategory cat : QuestCategory.VALUES) {
            y += 14; // header
            var entries = ClientQuestData.getEntriesForCategory(cat);
            for (var entry : entries) {
                if (entry == null || entry.completed()) { y += ROW_H; continue; }
                String qid = entry.questId();
                if (ClientQuestData.isAccepted(qid)) {
                    this.addRenderableWidget(Button.builder(
                        Component.literal("✕"), btn -> cancelQuest(qid)
                    ).bounds(leftPos + PANEL_W - 42, y, 20, 16).build());
                } else if (ClientQuestData.canAcceptMore() && ClientQuestData.canAcceptInCategory(cat)) {
                    this.addRenderableWidget(Button.builder(
                        Component.literal("+"), btn -> acceptQuest(qid)
                    ).bounds(leftPos + PANEL_W - 42, y, 20, 16).build());
                }
                y += ROW_H;
            }
            y += 6;
        }
        lastVer = ClientQuestData.getDataVersion();
    }

    @Override
    public void render(GuiGraphics g, int mx, int my, float pt) {
        // Refresh buttons if data changed
        if (ClientQuestData.getDataVersion() != lastVer) rebuildButtons();

        // Blur world, then cover with opaque overlay
        renderBackground(g, mx, my, pt);
        g.fill(0, 0, this.width, this.height, 0xF0101010);

        // Render panel from texture (9-slice style via single blit)
        g.blit(BG_TEXTURE, leftPos, topPos, 0, 0, PANEL_W, panelH, TEX_W, TEX_H);

        // Title
        g.drawCenteredString(font, Component.translatable("screen.daily_quests.quests").getString()
            + " — Day " + ClientQuestData.getQuestDay(),
            leftPos + PANEL_W / 2, topPos + 9, 0xFFFFFFFF);

        // Categories + quests
        int y = topPos + 30;
        for (QuestCategory cat : QuestCategory.VALUES) {
            y += 14;
            var entries = ClientQuestData.getEntriesForCategory(cat);
            boolean catDone = ClientQuestData.getCompletedCategories().contains(cat);

            String catName = Component.translatable("category.daily_quests." + cat.getName()).getString();
            g.drawString(font, (catDone ? "✓ " : "■ ") + catName,
                leftPos + CONTENT_X, y - 13, catDone ? 0xFF55FF55 : 0xFFFFAA00);

            for (var e : entries) {
                if (e == null) continue;
                renderQuestRow(g, leftPos + CONTENT_X, y, cat, e);
                y += ROW_H;
            }
            y += 6;
        }

        // Status bar
        int done = ClientQuestData.getCompletedPrimaryCount();
        boolean can = ClientQuestData.canClaimReward();
        boolean cl = ClientQuestData.isRewardClaimed();
        String s; int c;
        if (cl)      { s = "Reward Claimed"; c = 0xFF55FF55; }
        else if (can) { s = "Ready! (" + done + "/3) — Claim your reward below"; c = 0xFFFFFF55; }
        else         { s = "Accepted: " + ClientQuestData.getAcceptedCount() + "/3   Done: " + done + "/3"; c = 0xFFAAAAAA; }
        g.drawCenteredString(font, s, leftPos + PANEL_W / 2, topPos + panelH - 38, c);

        // Render buttons manually (no super.render which re-calls renderBackground)
        for (var r : this.renderables) r.render(g, mx, my, pt);
    }

    private void renderQuestRow(GuiGraphics g, int x, int y, QuestCategory cat,
                                 SyncQuestDataPacket.QuestProgressEntry e) {
        boolean ok = e.completed();
        boolean acc = ClientQuestData.isAccepted(e.questId());
        String desc = questDesc(cat, e);

        // Icon
        ItemStack icon = getIcon(cat);
        g.renderItem(icon, x, y);

        String line; int col;
        if (ok)       { line = desc + " (✓)";               col = 0xFF55FF55; }
        else if (acc) { line = desc + " (" + e.currentAmount() + "/" + e.requiredAmount() + ")"; col = 0xFFFFCC00; }
        else          { line = desc + " (" + e.requiredAmount() + ")";           col = 0xFFAAAAAA; }

        g.drawString(font, "  " + line, x + 18, y + 3, col);

        // Progress bar for accepted
        if (acc && !ok) {
            int bx = x + 190, bw = 120, bh = 6;
            float pct = (float)e.currentAmount() / e.requiredAmount();
            g.fill(bx, y + 6, bx + bw, y + 6 + bh, 0xFF333333);
            if (pct > 0) {
                int bc = pct > 0.66f ? 0xFF55FF55 : (pct > 0.33f ? 0xFFFFFF55 : 0xFFFF5555);
                g.fill(bx, y + 6, bx + (int)(bw * pct), y + 6 + bh, bc);
            }
        }
    }

    private ItemStack getIcon(QuestCategory cat) {
        return switch (cat) {
            case COMBAT -> new ItemStack(Items.IRON_SWORD);
            case CRAFTING -> new ItemStack(Items.CRAFTING_TABLE);
            case MINING -> new ItemStack(Items.IRON_PICKAXE);
            case FARMING -> new ItemStack(Items.WHEAT);
            case HUSBANDRY -> new ItemStack(Items.SADDLE);
        };
    }

    private String questDesc(QuestCategory cat, SyncQuestDataPacket.QuestProgressEntry e) {
        String qid = e.questId();
        String target = qid.substring(cat.getName().length() + 1);
        String key = "quest.daily_quests." + cat.getName() + "." + target;
        String t = Component.translatable(key).getString();
        return t.length() > 22 ? t.substring(0, 21) : t;
    }

    @Override public boolean isPauseScreen() { return false; }

    private void acceptQuest(String id)  { PacketDistributor.sendToServer(new AcceptQuestPacket(id)); }
    private void cancelQuest(String id)  { PacketDistributor.sendToServer(new CancelQuestPacket(id)); }
    private void claimReward()           {
        PacketDistributor.sendToServer(new ClaimRewardPacket());
        if (minecraft != null) minecraft.setScreen(null);
    }
}
