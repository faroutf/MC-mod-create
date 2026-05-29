# ===================================================
# Refresh daily tasks - generate 8 unique random tasks
# Runs as @s
# ===================================================

# Reset temp flags
scoreboard players set #id_0 hs_flag 0
scoreboard players set #id_1 hs_flag 0
scoreboard players set #id_2 hs_flag 0
scoreboard players set #id_3 hs_flag 0
scoreboard players set #id_4 hs_flag 0
scoreboard players set #id_5 hs_flag 0
scoreboard players set #id_6 hs_flag 0
scoreboard players set #id_7 hs_flag 0
scoreboard players set #id_8 hs_flag 0
scoreboard players set #id_9 hs_flag 0
scoreboard players set #id_10 hs_flag 0
scoreboard players set #id_11 hs_flag 0
scoreboard players set #id_12 hs_flag 0
scoreboard players set #id_13 hs_flag 0
scoreboard players set #id_14 hs_flag 0
scoreboard players set #id_15 hs_flag 0
scoreboard players set #id_16 hs_flag 0
scoreboard players set #id_17 hs_flag 0
scoreboard players set #id_18 hs_flag 0
scoreboard players set #id_19 hs_flag 0
scoreboard players set #id_20 hs_flag 0
scoreboard players set #id_21 hs_flag 0
scoreboard players set #id_22 hs_flag 0
scoreboard players set #id_23 hs_flag 0
scoreboard players set #id_24 hs_flag 0
scoreboard players set #id_25 hs_flag 0
scoreboard players set #id_26 hs_flag 0
scoreboard players set #id_27 hs_flag 0
scoreboard players set #id_28 hs_flag 0
scoreboard players set #id_29 hs_flag 0
scoreboard players set #id_30 hs_flag 0
scoreboard players set #id_31 hs_flag 0
scoreboard players set #id_32 hs_flag 0
scoreboard players set #id_33 hs_flag 0
scoreboard players set #id_34 hs_flag 0
scoreboard players set #id_35 hs_flag 0
scoreboard players set #id_36 hs_flag 0
scoreboard players set #id_37 hs_flag 0
scoreboard players set #id_38 hs_flag 0
scoreboard players set #id_39 hs_flag 0

# --- Select 8 unique task IDs ---
# Slot 0: first pick, always unique
execute store result score #roll hs_temp run random value 0..39
execute if score #roll hs_temp matches 0 run scoreboard players set #id_0 hs_flag 1
execute if score #roll hs_temp matches 1 run scoreboard players set #id_1 hs_flag 1
execute if score #roll hs_temp matches 2 run scoreboard players set #id_2 hs_flag 1
execute if score #roll hs_temp matches 3 run scoreboard players set #id_3 hs_flag 1
execute if score #roll hs_temp matches 4 run scoreboard players set #id_4 hs_flag 1
execute if score #roll hs_temp matches 5 run scoreboard players set #id_5 hs_flag 1
execute if score #roll hs_temp matches 6 run scoreboard players set #id_6 hs_flag 1
execute if score #roll hs_temp matches 7 run scoreboard players set #id_7 hs_flag 1
execute if score #roll hs_temp matches 8 run scoreboard players set #id_8 hs_flag 1
execute if score #roll hs_temp matches 9 run scoreboard players set #id_9 hs_flag 1
execute if score #roll hs_temp matches 10 run scoreboard players set #id_10 hs_flag 1
execute if score #roll hs_temp matches 11 run scoreboard players set #id_11 hs_flag 1
execute if score #roll hs_temp matches 12 run scoreboard players set #id_12 hs_flag 1
execute if score #roll hs_temp matches 13 run scoreboard players set #id_13 hs_flag 1
execute if score #roll hs_temp matches 14 run scoreboard players set #id_14 hs_flag 1
execute if score #roll hs_temp matches 15 run scoreboard players set #id_15 hs_flag 1
execute if score #roll hs_temp matches 16 run scoreboard players set #id_16 hs_flag 1
execute if score #roll hs_temp matches 17 run scoreboard players set #id_17 hs_flag 1
execute if score #roll hs_temp matches 18 run scoreboard players set #id_18 hs_flag 1
execute if score #roll hs_temp matches 19 run scoreboard players set #id_19 hs_flag 1
execute if score #roll hs_temp matches 20 run scoreboard players set #id_20 hs_flag 1
execute if score #roll hs_temp matches 21 run scoreboard players set #id_21 hs_flag 1
execute if score #roll hs_temp matches 22 run scoreboard players set #id_22 hs_flag 1
execute if score #roll hs_temp matches 23 run scoreboard players set #id_23 hs_flag 1
execute if score #roll hs_temp matches 24 run scoreboard players set #id_24 hs_flag 1
execute if score #roll hs_temp matches 25 run scoreboard players set #id_25 hs_flag 1
execute if score #roll hs_temp matches 26 run scoreboard players set #id_26 hs_flag 1
execute if score #roll hs_temp matches 27 run scoreboard players set #id_27 hs_flag 1
execute if score #roll hs_temp matches 28 run scoreboard players set #id_28 hs_flag 1
execute if score #roll hs_temp matches 29 run scoreboard players set #id_29 hs_flag 1
execute if score #roll hs_temp matches 30 run scoreboard players set #id_30 hs_flag 1
execute if score #roll hs_temp matches 31 run scoreboard players set #id_31 hs_flag 1
execute if score #roll hs_temp matches 32 run scoreboard players set #id_32 hs_flag 1
execute if score #roll hs_temp matches 33 run scoreboard players set #id_33 hs_flag 1
execute if score #roll hs_temp matches 34 run scoreboard players set #id_34 hs_flag 1
execute if score #roll hs_temp matches 35 run scoreboard players set #id_35 hs_flag 1
execute if score #roll hs_temp matches 36 run scoreboard players set #id_36 hs_flag 1
execute if score #roll hs_temp matches 37 run scoreboard players set #id_37 hs_flag 1
execute if score #roll hs_temp matches 38 run scoreboard players set #id_38 hs_flag 1
execute if score #roll hs_temp matches 39 run scoreboard players set #id_39 hs_flag 1
scoreboard players operation @s hs_tid_0 = #roll hs_temp

# Slot 1
function hard_survival_1:tasks/pick_slot with {slot_num:1}

# Slot 2
function hard_survival_1:tasks/pick_slot with {slot_num:2}

# Slot 3
function hard_survival_1:tasks/pick_slot with {slot_num:3}

# Slot 4
function hard_survival_1:tasks/pick_slot with {slot_num:4}

# Slot 5
function hard_survival_1:tasks/pick_slot with {slot_num:5}

# Slot 6
function hard_survival_1:tasks/pick_slot with {slot_num:6}

# Slot 7
function hard_survival_1:tasks/pick_slot with {slot_num:7}

# --- Now generate random amounts and set baselines ---
function hard_survival_1:tasks/set_amounts

# --- Reset progress, mark active, set day ---
scoreboard players set @s hs_prog_0 0
scoreboard players set @s hs_prog_1 0
scoreboard players set @s hs_prog_2 0
scoreboard players set @s hs_prog_3 0
scoreboard players set @s hs_prog_4 0
scoreboard players set @s hs_prog_5 0
scoreboard players set @s hs_prog_6 0
scoreboard players set @s hs_prog_7 0
scoreboard players set @s hs_done 0
scoreboard players set @s hs_new 0
tag @s add hs_active
scoreboard players add @s hs_day 1

# --- Set baselines (record current stat values) ---
function hard_survival_1:tasks/set_baselines

# --- Announce ---
tellraw @s {"text":"","extra":[{"text":"[§6每日任务§r] ","color":"gold"},{"text":"新的一天！任务已刷新。完成其中5个即可存活。","color":"white"}]}
tellraw @s {"text":"§7提示：用侧边栏查看任务进度，/trigger 可查看详情","color":"gray"}
