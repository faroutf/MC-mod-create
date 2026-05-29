# ===================================================
# Check if player completed 5/8 tasks
# Called on wake-up for non-new players
# Runs as @s
# ===================================================

# Check if hs_done >= 5 (completed the daily)
execute if score @s hs_done >= 5 run tellraw @s {"text":"","extra":[{"text":"[§6每日任务§r] ","color":"gold"},{"text":"昨日完成了 ","color":"white"},{"score":{"name":"@s","objective":"hs_done"},"color":"green"},{"text":"/8 个任务，活下来了！","color":"white"}]}

# If completed >= 5, refresh. Otherwise kill.
execute if score @s hs_done >= 5 run function hard_survival_1:tasks/refresh
execute unless score @s hs_done >= 5 run function hard_survival_1:player/kill
