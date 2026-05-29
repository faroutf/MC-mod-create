# ===================================================
# Kill player for incomplete daily tasks
# Runs as @s
# ===================================================
tellraw @s {"text":"§c☠ 你未完成5个每日任务，受到惩罚！","color":"red"}
tellraw @a {"text":"§c","extra":[{"selector":"@s"},{"text":" 未能完成每日任务，已处决！","color":"red"}]}
kill @s

# Give submit tool back after death
scoreboard players set @s hs_new 1
