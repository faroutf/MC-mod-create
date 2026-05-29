# Show task list to player (triggered by /trigger hs_tasks)
# Runs as @s

# Reset trigger
scoreboard players set @s hs_tasks 0

# Check if player has active tasks
execute unless score @s hs_targ_0 matches 1.. run tellraw @s {"text":"§6◆ 暂无每日任务，起床后自动刷新","color":"gold"}
execute unless score @s hs_targ_0 matches 1.. run return 0

# Header
tellraw @s {"text":"§6◆ ====== 每日任务 (Day ","extra":[{"score":{"name":"@s","objective":"hs_day"}},{"text":") ======","color":"gold"}]}

# --- Slot 0 ---
execute if score @s hs_targ_0 matches 1.. unless score @s hs_prog_0 >= @s hs_targ_0 run tellraw @s {"text":"","extra":[{"text":"§e◔ 1. ID:"},{"score":{"name":"@s","objective":"hs_tid_0"},"color":"white"},{"text":" §7进度: "},{"score":{"name":"@s","objective":"hs_prog_0"},"color":"white"},{"text":"§7/"},{"score":{"name":"@s","objective":"hs_targ_0"},"color":"green"}]}
execute if score @s hs_targ_0 matches 1.. if score @s hs_prog_0 >= @s hs_targ_0 run tellraw @s {"text":"","extra":[{"text":"§a☑ 1. ID:"},{"score":{"name":"@s","objective":"hs_tid_0"},"color":"green"},{"text":" §a完成: "},{"score":{"name":"@s","objective":"hs_prog_0"},"color":"green"},{"text":"§a/"},{"score":{"name":"@s","objective":"hs_targ_0"},"color":"green"}]}

# --- Slot 1 ---
execute if score @s hs_targ_1 matches 1.. unless score @s hs_prog_1 >= @s hs_targ_1 run tellraw @s {"text":"","extra":[{"text":"§e◔ 2. ID:"},{"score":{"name":"@s","objective":"hs_tid_1"},"color":"white"},{"text":" §7进度: "},{"score":{"name":"@s","objective":"hs_prog_1"},"color":"white"},{"text":"§7/"},{"score":{"name":"@s","objective":"hs_targ_1"},"color":"green"}]}
execute if score @s hs_targ_1 matches 1.. if score @s hs_prog_1 >= @s hs_targ_1 run tellraw @s {"text":"","extra":[{"text":"§a☑ 2. ID:"},{"score":{"name":"@s","objective":"hs_tid_1"},"color":"green"},{"text":" §a完成: "},{"score":{"name":"@s","objective":"hs_prog_1"},"color":"green"},{"text":"§a/"},{"score":{"name":"@s","objective":"hs_targ_1"},"color":"green"}]}

# --- Slot 2 ---
execute if score @s hs_targ_2 matches 1.. unless score @s hs_prog_2 >= @s hs_targ_2 run tellraw @s {"text":"","extra":[{"text":"§e◔ 3. ID:"},{"score":{"name":"@s","objective":"hs_tid_2"},"color":"white"},{"text":" §7进度: "},{"score":{"name":"@s","objective":"hs_prog_2"},"color":"white"},{"text":"§7/"},{"score":{"name":"@s","objective":"hs_targ_2"},"color":"green"}]}
execute if score @s hs_targ_2 matches 1.. if score @s hs_prog_2 >= @s hs_targ_2 run tellraw @s {"text":"","extra":[{"text":"§a☑ 3. ID:"},{"score":{"name":"@s","objective":"hs_tid_2"},"color":"green"},{"text":" §a完成: "},{"score":{"name":"@s","objective":"hs_prog_2"},"color":"green"},{"text":"§a/"},{"score":{"name":"@s","objective":"hs_targ_2"},"color":"green"}]}

# --- Slot 3 ---
execute if score @s hs_targ_3 matches 1.. unless score @s hs_prog_3 >= @s hs_targ_3 run tellraw @s {"text":"","extra":[{"text":"§e◔ 4. ID:"},{"score":{"name":"@s","objective":"hs_tid_3"},"color":"white"},{"text":" §7进度: "},{"score":{"name":"@s","objective":"hs_prog_3"},"color":"white"},{"text":"§7/"},{"score":{"name":"@s","objective":"hs_targ_3"},"color":"green"}]}
execute if score @s hs_targ_3 matches 1.. if score @s hs_prog_3 >= @s hs_targ_3 run tellraw @s {"text":"","extra":[{"text":"§a☑ 4. ID:"},{"score":{"name":"@s","objective":"hs_tid_3"},"color":"green"},{"text":" §a完成: "},{"score":{"name":"@s","objective":"hs_prog_3"},"color":"green"},{"text":"§a/"},{"score":{"name":"@s","objective":"hs_targ_3"},"color":"green"}]}

# --- Slot 4 ---
execute if score @s hs_targ_4 matches 1.. unless score @s hs_prog_4 >= @s hs_targ_4 run tellraw @s {"text":"","extra":[{"text":"§e◔ 5. ID:"},{"score":{"name":"@s","objective":"hs_tid_4"},"color":"white"},{"text":" §7进度: "},{"score":{"name":"@s","objective":"hs_prog_4"},"color":"white"},{"text":"§7/"},{"score":{"name":"@s","objective":"hs_targ_4"},"color":"green"}]}
execute if score @s hs_targ_4 matches 1.. if score @s hs_prog_4 >= @s hs_targ_4 run tellraw @s {"text":"","extra":[{"text":"§a☑ 5. ID:"},{"score":{"name":"@s","objective":"hs_tid_4"},"color":"green"},{"text":" §a完成: "},{"score":{"name":"@s","objective":"hs_prog_4"},"color":"green"},{"text":"§a/"},{"score":{"name":"@s","objective":"hs_targ_4"},"color":"green"}]}

# --- Slot 5 ---
execute if score @s hs_targ_5 matches 1.. unless score @s hs_prog_5 >= @s hs_targ_5 run tellraw @s {"text":"","extra":[{"text":"§e◔ 6. ID:"},{"score":{"name":"@s","objective":"hs_tid_5"},"color":"white"},{"text":" §7进度: "},{"score":{"name":"@s","objective":"hs_prog_5"},"color":"white"},{"text":"§7/"},{"score":{"name":"@s","objective":"hs_targ_5"},"color":"green"}]}
execute if score @s hs_targ_5 matches 1.. if score @s hs_prog_5 >= @s hs_targ_5 run tellraw @s {"text":"","extra":[{"text":"§a☑ 6. ID:"},{"score":{"name":"@s","objective":"hs_tid_5"},"color":"green"},{"text":" §a完成: "},{"score":{"name":"@s","objective":"hs_prog_5"},"color":"green"},{"text":"§a/"},{"score":{"name":"@s","objective":"hs_targ_5"},"color":"green"}]}

# --- Slot 6 ---
execute if score @s hs_targ_6 matches 1.. unless score @s hs_prog_6 >= @s hs_targ_6 run tellraw @s {"text":"","extra":[{"text":"§e◔ 7. ID:"},{"score":{"name":"@s","objective":"hs_tid_6"},"color":"white"},{"text":" §7进度: "},{"score":{"name":"@s","objective":"hs_prog_6"},"color":"white"},{"text":"§7/"},{"score":{"name":"@s","objective":"hs_targ_6"},"color":"green"}]}
execute if score @s hs_targ_6 matches 1.. if score @s hs_prog_6 >= @s hs_targ_6 run tellraw @s {"text":"","extra":[{"text":"§a☑ 7. ID:"},{"score":{"name":"@s","objective":"hs_tid_6"},"color":"green"},{"text":" §a完成: "},{"score":{"name":"@s","objective":"hs_prog_6"},"color":"green"},{"text":"§a/"},{"score":{"name":"@s","objective":"hs_targ_6"},"color":"green"}]}

# --- Slot 7 ---
execute if score @s hs_targ_7 matches 1.. unless score @s hs_prog_7 >= @s hs_targ_7 run tellraw @s {"text":"","extra":[{"text":"§e◔ 8. ID:"},{"score":{"name":"@s","objective":"hs_tid_7"},"color":"white"},{"text":" §7进度: "},{"score":{"name":"@s","objective":"hs_prog_7"},"color":"white"},{"text":"§7/"},{"score":{"name":"@s","objective":"hs_targ_7"},"color":"green"}]}
execute if score @s hs_targ_7 matches 1.. if score @s hs_prog_7 >= @s hs_targ_7 run tellraw @s {"text":"","extra":[{"text":"§a☑ 8. ID:"},{"score":{"name":"@s","objective":"hs_tid_7"},"color":"green"},{"text":" §a完成: "},{"score":{"name":"@s","objective":"hs_prog_7"},"color":"green"},{"text":"§a/"},{"score":{"name":"@s","objective":"hs_targ_7"},"color":"green"}]}

# Footer
tellraw @s {"text":"","extra":[{"text":"§6完成: "},{"score":{"name":"@s","objective":"hs_done"},"color":"yellow"},{"text":"§6/8  §7(需≥5存活)","color":"white"}]}
tellraw @s {"text":"§7提示: 副手提交杖 + 主手物品右键提交","color":"gray"}
