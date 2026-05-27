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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

public class QuestScreen extends Screen {

    private static final int PANEL_W = 420;
    private static final int PANEL_H_MAX = 370;

    private int leftPos, topPos, panelW, panelH;
    // visible content area inside the panel (below title, above buttons)
    private int viewX, viewY, viewW, viewH;
    private double scrollOff;
    private int contentHeight;
    private int lastVer = -1;
    private boolean isScrolling;

    public QuestScreen() {
        super(Component.translatable("screen.daily_quests.quests"));
    }

    @Override
    protected void init() {
        // Panel fits screen
        this.panelH = Math.min(this.height - 20, PANEL_H_MAX);
        this.panelW = Math.min(this.width - 20, PANEL_W);
        this.leftPos = (this.width - panelW) / 2;
        this.topPos = (this.height - panelH) / 2;

        // Viewport: inside panel, between title area and button area
        this.viewX = leftPos + 10;
        this.viewY = topPos + 26;
        this.viewW = panelW - 28;
        this.viewH = panelH - 66; // title ~26px top, button area ~40px bottom

        rebuildButtons();
    }

    private void rebuildButtons() {
        this.clearWidgets();
        this.scrollOff = Math.max(0, Math.min(scrollOff, Math.max(0, contentHeight - viewH)));

        // Claim button at panel bottom
        int btnW = 150, btnH = 20;
        int btnX = leftPos + (panelW - btnW) / 2;
        int btnY = topPos + panelH - btnH - 10;
        this.addRenderableWidget(Button.builder(
            Component.translatable("screen.daily_quests.claim_reward"),
            btn -> claimReward()
        ).bounds(btnX, btnY, btnW, btnH).build());

        // Accept/cancel buttons — positioned within content area, offset by scroll
        int y = viewY + 4 - (int)scrollOff;
        for (QuestCategory cat : QuestCategory.VALUES) {
            y += 14; // header
            var entries = ClientQuestData.getEntriesForCategory(cat);
            for (var entry : entries) {
                if (entry == null || entry.completed()) { y += 20; continue; }
                String qid = entry.questId();
                int by = y;
                if (by >= viewY && by <= viewY + viewH) {
                    if (ClientQuestData.isAccepted(qid)) {
                        this.addRenderableWidget(Button.builder(
                            Component.literal("✕"), btn -> cancelQuest(qid)
                        ).bounds(leftPos + panelW - 48, by, 20, 16).build());
                    } else if (ClientQuestData.canAcceptMore() && ClientQuestData.canAcceptInCategory(cat)) {
                        this.addRenderableWidget(Button.builder(
                            Component.literal("+"), btn -> acceptQuest(qid)
                        ).bounds(leftPos + panelW - 48, by, 20, 16).build());
                    }
                }
                y += 20;
            }
            y += 6;
        }
        lastVer = ClientQuestData.getDataVersion();
    }

    @Override
    public void render(GuiGraphics g, int mx, int my, float pt) {
        if (ClientQuestData.getDataVersion() != lastVer) {
            int oldMax = Math.max(0, contentHeight - viewH);
            rebuildButtons();
            this.scrollOff = Math.max(0, Math.min(scrollOff, Math.max(0, contentHeight - viewH)));
        }

        renderBackground(g, mx, my, pt);
        g.fill(0, 0, this.width, this.height, 0xF0101010);

        // Panel: vanilla inventory colours (exact match to container/inventory.png)
        int x0 = leftPos, y0 = topPos, x1 = leftPos + panelW, y1 = topPos + panelH;
        g.fill(x0 - 2, y0 - 2, x1 + 2, y1 + 2, 0xFFC6C6C6); // outer border
        g.fill(x0 - 1, y0 - 1, x1 + 1, y1 + 1, 0xFF000000); // black edge
        g.fill(x0, y0, x1, y1, 0xFF8B8B8B);                  // main panel (vanilla stone grey)

        // Title bar — darker strip at top
        g.fill(x0, y0, x1, y0 + 17, 0xFFC6C6C6);
        g.fill(x0, y0, x1, y0 + 16, 0xFF555555);
        g.drawString(font, Component.translatable("screen.daily_quests.quests").getString()
            + " — Day " + ClientQuestData.getQuestDay(),
            leftPos + 8, topPos + 5, 0xFFFFFFFF);

        // Content area background (darker slots bg)
        g.fill(viewX - 2, viewY, viewX + viewW + 2, viewY + viewH, 0xFF373737);

        // Enable scissor for content clipping
        g.enableScissor(viewX, viewY, viewX + viewW, viewY + viewH);

        // Measure + render content
        int y = viewY + 4 - (int)scrollOff;
        int measuredH = viewY + 4;
        for (QuestCategory cat : QuestCategory.VALUES) {
            y += 14;
            var entries = ClientQuestData.getEntriesForCategory(cat);
            boolean catDone = ClientQuestData.getCompletedCategories().contains(cat);

            String cn = Component.translatable("category.daily_quests." + cat.getName()).getString();
            g.drawString(font, (catDone ? "✓ " : "■ ") + cn,
                viewX + 8, y - 12, catDone ? 0xFF55FF55 : 0xFFFFAA00);

            for (var e : entries) {
                if (e == null) continue;
                renderQuestRow(g, viewX + 8, y, cat, e);
                y += 20;
            }
            y += 6;
        }
        measuredH = y + (int)scrollOff;
        this.contentHeight = measuredH - viewY;

        g.disableScissor();

        // Scrollbar
        if (contentHeight > viewH) {
            int sbX = leftPos + panelW - 8;
            int sbY = viewY;
            int sbH = viewH;
            int sbThumbH = Math.max(15, (int)((float)viewH / contentHeight * sbH));
            int sbThumbY = sbY + (int)((float)scrollOff / (contentHeight - viewH) * (sbH - sbThumbH));
            g.fill(sbX + 1, sbY, sbX + 5, sbY + sbH, 0xFF000000);
            g.fill(sbX, sbY + sbThumbY, sbX + 6, sbY + sbThumbY + sbThumbH, 0xFF8B8B8B);
            g.fill(sbX, sbY + sbThumbY, sbX + 6, sbY + sbThumbY + 1, 0xFFFFFFFF);
        }

        // Status
        int done = ClientQuestData.getCompletedPrimaryCount();
        boolean can = ClientQuestData.canClaimReward();
        boolean cl = ClientQuestData.isRewardClaimed();
        String s; int c;
        if (cl)      { s = "Reward Claimed"; c = 0xFF55FF55; }
        else if (can) { s = "Ready! (" + done + "/3) — Claim your reward below"; c = 0xFFFFFF55; }
        else         { s = "Accepted: " + ClientQuestData.getAcceptedCount() + "/3   Done: " + done + "/3"; c = 0xFFAAAAAA; }
        g.drawCenteredString(font, s, leftPos + panelW / 2, topPos + panelH - 38, c);

        for (var r : this.renderables) r.render(g, mx, my, pt);
    }

    private void renderQuestRow(GuiGraphics g, int x, int y, QuestCategory cat,
                                 SyncQuestDataPacket.QuestProgressEntry e) {
        boolean ok = e.completed();
        boolean acc = ClientQuestData.isAccepted(e.questId());
        String desc = questDesc(cat, e);

        ItemStack icon = getIcon(cat);
        g.renderItem(icon, x, y);

        String line; int col;
        if (ok)       { line = desc + " (✓)";               col = 0xFF55FF55; }
        else if (acc) { line = desc + " (" + e.currentAmount() + "/" + e.requiredAmount() + ")"; col = 0xFFFFCC00; }
        else          { line = desc + " (" + e.requiredAmount() + ")";           col = 0xFFAAAAAA; }

        g.drawString(font, "  " + line, x + 20, y + 3, col);

        if (acc && !ok) {
            int bx = x + 190, bw = 105, bh = 6;
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
        return t.length() > 20 ? t.substring(0, 19) : t;
    }

    // --- Scroll handling ---
    @Override
    public boolean mouseScrolled(double mx, double my, double scrollX, double scrollY) {
        if (contentHeight > viewH) {
            this.scrollOff = Math.max(0, Math.min(scrollOff - scrollY * 16, contentHeight - viewH));
            rebuildButtons();
            return true;
        }
        return super.mouseScrolled(mx, my, scrollX, scrollY);
    }

    @Override
    public boolean mouseDragged(double mx, double my, int button, double dx, double dy) {
        if (isScrolling) {
            float sbH = viewH;
            float sbThumbH = Math.max(15, (float)viewH / contentHeight * sbH);
            float moveRange = sbH - sbThumbH;
            float pct = (float)((my - viewY - sbThumbH / 2) / moveRange);
            this.scrollOff = Math.max(0, Math.min(pct * (contentHeight - viewH), contentHeight - viewH));
            rebuildButtons();
            return true;
        }
        return super.mouseDragged(mx, my, button, dx, dy);
    }

    @Override
    public boolean mouseClicked(double mx, double my, int button) {
        // Scrollbar click detection
        int sbX = leftPos + panelW - 8;
        if (contentHeight > viewH && mx >= sbX && mx <= sbX + 6 && my >= viewY && my <= viewY + viewH) {
            isScrolling = true;
            float sbH = viewH;
            float sbThumbH = Math.max(15, (float)viewH / contentHeight * sbH);
            float moveRange = sbH - sbThumbH;
            float pct = (float)((my - viewY - sbThumbH / 2) / moveRange);
            this.scrollOff = Math.max(0, Math.min(pct * (contentHeight - viewH), contentHeight - viewH));
            rebuildButtons();
            return true;
        }
        return super.mouseClicked(mx, my, button);
    }

    @Override
    public boolean mouseReleased(double mx, double my, int button) {
        isScrolling = false;
        return super.mouseReleased(mx, my, button);
    }

    @Override public boolean isPauseScreen() { return false; }

    private void acceptQuest(String id)  { PacketDistributor.sendToServer(new AcceptQuestPacket(id)); }
    private void cancelQuest(String id)  { PacketDistributor.sendToServer(new CancelQuestPacket(id)); }
    private void claimReward()           {
        PacketDistributor.sendToServer(new ClaimRewardPacket());
        if (minecraft != null) minecraft.setScreen(null);
    }
}
