# ===================================================
# Debug function - diagnose system state
# Usage: /function hard_survival_1:debug
# ===================================================

tellraw @s {"text":"§6══════ Hard Survival Debug ══════","color":"gold"}

# --- 1. Check if system started ---
tellraw @s {"text":"","extra":[{"text":"[1] System started: "},{"storage":"hard_survival_1:flags","path":"started","color":"white"}]}

# --- 2. Check scoreboard objectives ---
tellraw @s {"text":"[2] Checking scoreboards...","color":"yellow"}
execute if score @s hs_day matches 0.. run tellraw @s {"text":"  hs_day: OK","color":"green"}
execute unless score @s hs_day matches 0.. run tellraw @s {"text":"  hs_day: MISSING - 系统未初始化！","color":"red"}

execute if score @s hs_tid_0 matches 0.. run tellraw @s {"text":"  hs_tid_0: OK (has score)","color":"green"}
execute unless score @s hs_tid_0 matches 0.. run tellraw @s {"text":"  hs_tid_0: NO SCORE - 任务未分配！","color":"red"}

execute if score @s hs_targ_0 matches 1.. run tellraw @s {"text":"  hs_targ_0: OK (has tasks)","color":"green"}
execute unless score @s hs_targ_0 matches 1.. run tellraw @s {"text":"  hs_targ_0: NO TASKS - 需要先起床","color":"yellow"}

# --- 3. Check trigger state ---
tellraw @s {"text":"[3] Trigger check...","color":"yellow"}
execute store result score #dt hs_temp run scoreboard players get @s hs_tasks

tellraw @s {"text":"","extra":[{"text":"  hs_tasks value: "},{"score":{"name":"@s","objective":"hs_tasks"},"color":"white"}]}

# --- 4. Player state ---
tellraw @s {"text":"[4] Player state:","color":"yellow"}
tellraw @s {"text":"","extra":[{"text":"  hs_new: "},{"score":{"name":"@s","objective":"hs_new"},"color":"white"},{"text":" (1=等待首次起床)","color":"gray"}]}
tellraw @s {"text":"","extra":[{"text":"  hs_day: "},{"score":{"name":"@s","objective":"hs_day"},"color":"white"}]}
tellraw @s {"text":"","extra":[{"text":"  hs_done: "},{"score":{"name":"@s","objective":"hs_done"},"color":"white"}]}
tellraw @s {"text":"","extra":[{"text":"  hs_sleep: "},{"score":{"name":"@s","objective":"hs_sleep"},"color":"white"}]}

execute if entity @s[tag=hs_active] run tellraw @s {"text":"  hs_active tag: YES","color":"green"}
execute unless entity @s[tag=hs_active] run tellraw @s {"text":"  hs_active tag: NO","color":"yellow"}

# --- 5. Test simple tellraw ---
tellraw @s {"text":"[5] Simple tellraw test: 如果你看到这条消息，tellraw 本身工作正常","color":"green"}

# --- 6. Show all 8 slot task IDs (if any) ---
tellraw @s {"text":"[6] Task slots:","color":"yellow"}
execute if score @s hs_targ_0 matches 1.. run tellraw @s {"text":"","extra":[{"text":"  Slot0: tid="},{"score":{"name":"@s","objective":"hs_tid_0"}},{"text":" targ="},{"score":{"name":"@s","objective":"hs_targ_0"}},{"text":" prog="},{"score":{"name":"@s","objective":"hs_prog_0"}}]}
execute if score @s hs_targ_1 matches 1.. run tellraw @s {"text":"","extra":[{"text":"  Slot1: tid="},{"score":{"name":"@s","objective":"hs_tid_1"}},{"text":" targ="},{"score":{"name":"@s","objective":"hs_targ_1"}},{"text":" prog="},{"score":{"name":"@s","objective":"hs_prog_1"}}]}
execute if score @s hs_targ_2 matches 1.. run tellraw @s {"text":"","extra":[{"text":"  Slot2: tid="},{"score":{"name":"@s","objective":"hs_tid_2"}},{"text":" targ="},{"score":{"name":"@s","objective":"hs_targ_2"}},{"text":" prog="},{"score":{"name":"@s","objective":"hs_prog_2"}}]}
execute if score @s hs_targ_3 matches 1.. run tellraw @s {"text":"","extra":[{"text":"  Slot3: tid="},{"score":{"name":"@s","objective":"hs_tid_3"}},{"text":" targ="},{"score":{"name":"@s","objective":"hs_targ_3"}},{"text":" prog="},{"score":{"name":"@s","objective":"hs_prog_3"}}]}
execute if score @s hs_targ_4 matches 1.. run tellraw @s {"text":"","extra":[{"text":"  Slot4: tid="},{"score":{"name":"@s","objective":"hs_tid_4"}},{"text":" targ="},{"score":{"name":"@s","objective":"hs_targ_4"}},{"text":" prog="},{"score":{"name":"@s","objective":"hs_prog_4"}}]}
execute if score @s hs_targ_5 matches 1.. run tellraw @s {"text":"","extra":[{"text":"  Slot5: tid="},{"score":{"name":"@s","objective":"hs_tid_5"}},{"text":" targ="},{"score":{"name":"@s","objective":"hs_targ_5"}},{"text":" prog="},{"score":{"name":"@s","objective":"hs_prog_5"}}]}
execute if score @s hs_targ_6 matches 1.. run tellraw @s {"text":"","extra":[{"text":"  Slot6: tid="},{"score":{"name":"@s","objective":"hs_tid_6"}},{"text":" targ="},{"score":{"name":"@s","objective":"hs_targ_6"}},{"text":" prog="},{"score":{"name":"@s","objective":"hs_prog_6"}}]}
execute if score @s hs_targ_7 matches 1.. run tellraw @s {"text":"","extra":[{"text":"  Slot7: tid="},{"score":{"name":"@s","objective":"hs_tid_7"}},{"text":" targ="},{"score":{"name":"@s","objective":"hs_targ_7"}},{"text":" prog="},{"score":{"name":"@s","objective":"hs_prog_7"}}]}

# --- 7. Direct call test ---
tellraw @s {"text":"[7] 正在直接调用 show_tasks...","color":"yellow"}
function hard_survival_1:tasks/show_tasks

tellraw @s {"text":"§6══════ Debug Complete ══════","color":"gold"}
