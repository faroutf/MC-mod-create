# ===================================================
# First-time task refresh (no kill check)
# Runs as @s on first wake-up
# ===================================================
tellraw @s {"text":"","extra":[{"text":"[§6每日任务§r] ","color":"gold"},{"text":"欢迎！首次起床，任务系统开启。","color":"white"}]}
tellraw @s {"text":"§7提示：完成8个任务中的任意5个即可存活到下一天。","color":"gray"}
tellraw @s {"text":"§7§o未完成5个任务的惩罚：死亡。","color":"dark_gray"}

# Enable trigger for task viewing
scoreboard players enable @s hs_tasks

# Set new flag to 0 (no longer first join)
scoreboard players set @s hs_new 0

# Generate first set of tasks
function hard_survival_1:tasks/refresh
