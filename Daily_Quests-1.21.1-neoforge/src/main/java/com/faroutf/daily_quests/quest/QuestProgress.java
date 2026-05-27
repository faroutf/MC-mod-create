package com.faroutf.daily_quests.quest;

public class QuestProgress {
    private String questId;
    private int currentAmount;
    private int requiredAmount;
    private boolean completed;

    public QuestProgress() {
        this.questId = "";
        this.currentAmount = 0;
        this.requiredAmount = 0;
        this.completed = false;
    }

    public QuestProgress(QuestDefinition definition) {
        this.questId = definition.questId();
        this.currentAmount = 0;
        this.requiredAmount = definition.requiredAmount();
        this.completed = false;
    }

    public QuestProgress(String questId, int requiredAmount) {
        this.questId = questId;
        this.currentAmount = 0;
        this.requiredAmount = requiredAmount;
        this.completed = false;
    }

    public boolean advance(int amount) {
        if (completed) return false;
        currentAmount = Math.min(currentAmount + amount, requiredAmount);
        if (currentAmount >= requiredAmount) {
            completed = true;
            return true;
        }
        return false;
    }

    public float getProgressRatio() {
        return requiredAmount > 0 ? (float) currentAmount / requiredAmount : 0f;
    }

    public String getQuestId() { return questId; }
    public int getCurrentAmount() { return currentAmount; }
    public int getRequiredAmount() { return requiredAmount; }
    public boolean isCompleted() { return completed; }

    public void setQuestId(String questId) { this.questId = questId; }
    public void setCurrentAmount(int currentAmount) { this.currentAmount = currentAmount; }
    public void setRequiredAmount(int requiredAmount) { this.requiredAmount = requiredAmount; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
