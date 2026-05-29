# ===================================================
# Show task list to player (triggered by /trigger hs_tasks)
# Runs as @s
# ===================================================

# Reset trigger
scoreboard players set @s hs_tasks 0

# Show day and progress header
tellraw @s {"text":"","extra":[
  {"text":"§6════ 每日任务 §r§7(Day "},{"score":{"name":"@s","objective":"hs_day"},"color":"white"},{"text":") §6════","color":"gold"}
]}
tellraw @s {"text":"","extra":[
  {"text":"  完成进度: "},{"score":{"name":"@s","objective":"hs_done"},"color":"green"},{"text":" / 8 §7(需要≥5才能存活)","color":"gray"}
]}

# Show each slot
execute if score @s hs_tid_0 matches -1 run tellraw @s {"text":"  §81. [空]","color":"dark_gray"}
execute unless score @s hs_tid_0 matches -1 run function hard_survival_1:tasks/show_slot with {slot:"0"}

execute if score @s hs_tid_1 matches -1 run tellraw @s {"text":"  §82. [空]","color":"dark_gray"}
execute unless score @s hs_tid_1 matches -1 run function hard_survival_1:tasks/show_slot with {slot:"1"}

execute if score @s hs_tid_2 matches -1 run tellraw @s {"text":"  §83. [空]","color":"dark_gray"}
execute unless score @s hs_tid_2 matches -1 run function hard_survival_1:tasks/show_slot with {slot:"2"}

execute if score @s hs_tid_3 matches -1 run tellraw @s {"text":"  §84. [空]","color":"dark_gray"}
execute unless score @s hs_tid_3 matches -1 run function hard_survival_1:tasks/show_slot with {slot:"3"}

execute if score @s hs_tid_4 matches -1 run tellraw @s {"text":"  §85. [空]","color":"dark_gray"}
execute unless score @s hs_tid_4 matches -1 run function hard_survival_1:tasks/show_slot with {slot:"4"}

execute if score @s hs_tid_5 matches -1 run tellraw @s {"text":"  §86. [空]","color":"dark_gray"}
execute unless score @s hs_tid_5 matches -1 run function hard_survival_1:tasks/show_slot with {slot:"5"}

execute if score @s hs_tid_6 matches -1 run tellraw @s {"text":"  §87. [空]","color":"dark_gray"}
execute unless score @s hs_tid_6 matches -1 run function hard_survival_1:tasks/show_slot with {slot:"6"}

execute if score @s hs_tid_7 matches -1 run tellraw @s {"text":"  §88. [空]","color":"dark_gray"}
execute unless score @s hs_tid_7 matches -1 run function hard_survival_1:tasks/show_slot with {slot:"7"}

tellraw @s {"text":"§6═══════════════════════","color":"gold"}
tellraw @s {"text":"§7提示: 把提交杖放副手，主手持物品右键提交","color":"gray"}
