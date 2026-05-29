# ===================================================
# Sync progress for all 8 slots (tick polling)
# Reads current stat, computes progress = stat - baseline
# Skips submit tasks (progress managed by submit handler)
# Runs as @s
# ===================================================

# --- Slot 0 ---
execute if score @s hs_tid_0 matches 0 store result score @s hs_temp run scoreboard players get @s hs_0
execute if score @s hs_tid_0 matches 1 store result score @s hs_temp run scoreboard players get @s hs_1
execute if score @s hs_tid_0 matches 2 store result score @s hs_temp run scoreboard players get @s hs_2
execute if score @s hs_tid_0 matches 3 store result score @s hs_temp run scoreboard players get @s hs_3
execute if score @s hs_tid_0 matches 4 store result score @s hs_temp run scoreboard players get @s hs_4
execute if score @s hs_tid_0 matches 5 store result score @s hs_temp run scoreboard players get @s hs_5
execute if score @s hs_tid_0 matches 6 store result score @s hs_temp run scoreboard players get @s hs_6
execute if score @s hs_tid_0 matches 7 store result score @s hs_temp run scoreboard players get @s hs_7
execute if score @s hs_tid_0 matches 8 store result score @s hs_temp run scoreboard players get @s hs_8
execute if score @s hs_tid_0 matches 9 store result score @s hs_temp run scoreboard players get @s hs_9
execute if score @s hs_tid_0 matches 10 store result score @s hs_temp run scoreboard players get @s hs_10
execute if score @s hs_tid_0 matches 11 store result score @s hs_temp run scoreboard players get @s hs_11
execute if score @s hs_tid_0 matches 12 store result score @s hs_temp run scoreboard players get @s hs_12
execute if score @s hs_tid_0 matches 13 store result score @s hs_temp run scoreboard players get @s hs_13
execute if score @s hs_tid_0 matches 14 store result score @s hs_temp run scoreboard players get @s hs_14
execute if score @s hs_tid_0 matches 15 store result score @s hs_temp run scoreboard players get @s hs_15
execute if score @s hs_tid_0 matches 16 store result score @s hs_temp run scoreboard players get @s hs_16
execute if score @s hs_tid_0 matches 17 store result score @s hs_temp run scoreboard players get @s hs_17
execute if score @s hs_tid_0 matches 18 store result score @s hs_temp run scoreboard players get @s hs_18
execute if score @s hs_tid_0 matches 19 store result score @s hs_temp run scoreboard players get @s hs_19
execute if score @s hs_tid_0 matches 20 store result score @s hs_temp run scoreboard players get @s hs_20
execute if score @s hs_tid_0 matches 21 store result score @s hs_temp run scoreboard players get @s hs_21
execute if score @s hs_tid_0 matches 22 store result score @s hs_temp run scoreboard players get @s hs_22
execute if score @s hs_tid_0 matches 23 store result score @s hs_temp run scoreboard players get @s hs_23
execute if score @s hs_tid_0 matches 24 store result score @s hs_temp run scoreboard players get @s hs_24
execute if score @s hs_tid_0 matches 25 store result score @s hs_temp run scoreboard players get @s hs_25
execute if score @s hs_tid_0 matches 26 store result score @s hs_temp run scoreboard players get @s hs_26
execute if score @s hs_tid_0 matches 27 store result score @s hs_temp run scoreboard players get @s hs_27
execute if score @s hs_tid_0 matches 28 store result score @s hs_temp run scoreboard players get @s hs_28
execute if score @s hs_tid_0 matches 29 store result score @s hs_temp run scoreboard players get @s hs_29
execute if score @s hs_tid_0 matches 30 store result score @s hs_temp run scoreboard players get @s hs_30
execute if score @s hs_tid_0 matches 31 store result score @s hs_temp run scoreboard players get @s hs_31
execute if score @s hs_tid_0 matches 32 store result score @s hs_temp run scoreboard players get @s hs_32
execute if score @s hs_tid_0 matches 33 store result score @s hs_temp run scoreboard players get @s hs_33
execute if score @s hs_tid_0 matches 34 store result score @s hs_temp run scoreboard players get @s hs_34
execute if score @s hs_tid_0 matches 35 store result score @s hs_temp run scoreboard players get @s hs_35
execute if score @s hs_tid_0 matches 36 store result score @s hs_temp run scoreboard players get @s hs_36
execute if score @s hs_tid_0 matches 37 store result score @s hs_temp run scoreboard players get @s hs_37
execute if score @s hs_tid_0 matches 38 store result score @s hs_temp run scoreboard players get @s hs_38
execute if score @s hs_tid_0 matches 39 store result score @s hs_temp run scoreboard players get @s hs_39
scoreboard players operation @s hs_temp -= @s hs_base_0
execute if score @s hs_temp > @s hs_targ_0 run scoreboard players operation @s hs_temp = @s hs_targ_0
execute unless score @s hs_tid_0 matches 32..39 run scoreboard players operation @s hs_prog_0 = @s hs_temp
execute if score @s hs_prog_0 >= @s hs_targ_0 unless score @s hs_tid_0 matches -1 run function hard_survival_1:tasks/complete_notify with {slot:"0"}

# --- Slot 1 ---
execute if score @s hs_tid_1 matches 0 store result score @s hs_temp run scoreboard players get @s hs_0
execute if score @s hs_tid_1 matches 1 store result score @s hs_temp run scoreboard players get @s hs_1
execute if score @s hs_tid_1 matches 2 store result score @s hs_temp run scoreboard players get @s hs_2
execute if score @s hs_tid_1 matches 3 store result score @s hs_temp run scoreboard players get @s hs_3
execute if score @s hs_tid_1 matches 4 store result score @s hs_temp run scoreboard players get @s hs_4
execute if score @s hs_tid_1 matches 5 store result score @s hs_temp run scoreboard players get @s hs_5
execute if score @s hs_tid_1 matches 6 store result score @s hs_temp run scoreboard players get @s hs_6
execute if score @s hs_tid_1 matches 7 store result score @s hs_temp run scoreboard players get @s hs_7
execute if score @s hs_tid_1 matches 8 store result score @s hs_temp run scoreboard players get @s hs_8
execute if score @s hs_tid_1 matches 9 store result score @s hs_temp run scoreboard players get @s hs_9
execute if score @s hs_tid_1 matches 10 store result score @s hs_temp run scoreboard players get @s hs_10
execute if score @s hs_tid_1 matches 11 store result score @s hs_temp run scoreboard players get @s hs_11
execute if score @s hs_tid_1 matches 12 store result score @s hs_temp run scoreboard players get @s hs_12
execute if score @s hs_tid_1 matches 13 store result score @s hs_temp run scoreboard players get @s hs_13
execute if score @s hs_tid_1 matches 14 store result score @s hs_temp run scoreboard players get @s hs_14
execute if score @s hs_tid_1 matches 15 store result score @s hs_temp run scoreboard players get @s hs_15
execute if score @s hs_tid_1 matches 16 store result score @s hs_temp run scoreboard players get @s hs_16
execute if score @s hs_tid_1 matches 17 store result score @s hs_temp run scoreboard players get @s hs_17
execute if score @s hs_tid_1 matches 18 store result score @s hs_temp run scoreboard players get @s hs_18
execute if score @s hs_tid_1 matches 19 store result score @s hs_temp run scoreboard players get @s hs_19
execute if score @s hs_tid_1 matches 20 store result score @s hs_temp run scoreboard players get @s hs_20
execute if score @s hs_tid_1 matches 21 store result score @s hs_temp run scoreboard players get @s hs_21
execute if score @s hs_tid_1 matches 22 store result score @s hs_temp run scoreboard players get @s hs_22
execute if score @s hs_tid_1 matches 23 store result score @s hs_temp run scoreboard players get @s hs_23
execute if score @s hs_tid_1 matches 24 store result score @s hs_temp run scoreboard players get @s hs_24
execute if score @s hs_tid_1 matches 25 store result score @s hs_temp run scoreboard players get @s hs_25
execute if score @s hs_tid_1 matches 26 store result score @s hs_temp run scoreboard players get @s hs_26
execute if score @s hs_tid_1 matches 27 store result score @s hs_temp run scoreboard players get @s hs_27
execute if score @s hs_tid_1 matches 28 store result score @s hs_temp run scoreboard players get @s hs_28
execute if score @s hs_tid_1 matches 29 store result score @s hs_temp run scoreboard players get @s hs_29
execute if score @s hs_tid_1 matches 30 store result score @s hs_temp run scoreboard players get @s hs_30
execute if score @s hs_tid_1 matches 31 store result score @s hs_temp run scoreboard players get @s hs_31
execute if score @s hs_tid_1 matches 32 store result score @s hs_temp run scoreboard players get @s hs_32
execute if score @s hs_tid_1 matches 33 store result score @s hs_temp run scoreboard players get @s hs_33
execute if score @s hs_tid_1 matches 34 store result score @s hs_temp run scoreboard players get @s hs_34
execute if score @s hs_tid_1 matches 35 store result score @s hs_temp run scoreboard players get @s hs_35
execute if score @s hs_tid_1 matches 36 store result score @s hs_temp run scoreboard players get @s hs_36
execute if score @s hs_tid_1 matches 37 store result score @s hs_temp run scoreboard players get @s hs_37
execute if score @s hs_tid_1 matches 38 store result score @s hs_temp run scoreboard players get @s hs_38
execute if score @s hs_tid_1 matches 39 store result score @s hs_temp run scoreboard players get @s hs_39
scoreboard players operation @s hs_temp -= @s hs_base_1
execute if score @s hs_temp > @s hs_targ_1 run scoreboard players operation @s hs_temp = @s hs_targ_1
execute unless score @s hs_tid_1 matches 32..39 run scoreboard players operation @s hs_prog_1 = @s hs_temp
execute if score @s hs_prog_1 >= @s hs_targ_1 unless score @s hs_tid_1 matches -1 run function hard_survival_1:tasks/complete_notify with {slot:"1"}

# --- Slot 2 ---
execute if score @s hs_tid_2 matches 0 store result score @s hs_temp run scoreboard players get @s hs_0
execute if score @s hs_tid_2 matches 1 store result score @s hs_temp run scoreboard players get @s hs_1
execute if score @s hs_tid_2 matches 2 store result score @s hs_temp run scoreboard players get @s hs_2
execute if score @s hs_tid_2 matches 3 store result score @s hs_temp run scoreboard players get @s hs_3
execute if score @s hs_tid_2 matches 4 store result score @s hs_temp run scoreboard players get @s hs_4
execute if score @s hs_tid_2 matches 5 store result score @s hs_temp run scoreboard players get @s hs_5
execute if score @s hs_tid_2 matches 6 store result score @s hs_temp run scoreboard players get @s hs_6
execute if score @s hs_tid_2 matches 7 store result score @s hs_temp run scoreboard players get @s hs_7
execute if score @s hs_tid_2 matches 8 store result score @s hs_temp run scoreboard players get @s hs_8
execute if score @s hs_tid_2 matches 9 store result score @s hs_temp run scoreboard players get @s hs_9
execute if score @s hs_tid_2 matches 10 store result score @s hs_temp run scoreboard players get @s hs_10
execute if score @s hs_tid_2 matches 11 store result score @s hs_temp run scoreboard players get @s hs_11
execute if score @s hs_tid_2 matches 12 store result score @s hs_temp run scoreboard players get @s hs_12
execute if score @s hs_tid_2 matches 13 store result score @s hs_temp run scoreboard players get @s hs_13
execute if score @s hs_tid_2 matches 14 store result score @s hs_temp run scoreboard players get @s hs_14
execute if score @s hs_tid_2 matches 15 store result score @s hs_temp run scoreboard players get @s hs_15
execute if score @s hs_tid_2 matches 16 store result score @s hs_temp run scoreboard players get @s hs_16
execute if score @s hs_tid_2 matches 17 store result score @s hs_temp run scoreboard players get @s hs_17
execute if score @s hs_tid_2 matches 18 store result score @s hs_temp run scoreboard players get @s hs_18
execute if score @s hs_tid_2 matches 19 store result score @s hs_temp run scoreboard players get @s hs_19
execute if score @s hs_tid_2 matches 20 store result score @s hs_temp run scoreboard players get @s hs_20
execute if score @s hs_tid_2 matches 21 store result score @s hs_temp run scoreboard players get @s hs_21
execute if score @s hs_tid_2 matches 22 store result score @s hs_temp run scoreboard players get @s hs_22
execute if score @s hs_tid_2 matches 23 store result score @s hs_temp run scoreboard players get @s hs_23
execute if score @s hs_tid_2 matches 24 store result score @s hs_temp run scoreboard players get @s hs_24
execute if score @s hs_tid_2 matches 25 store result score @s hs_temp run scoreboard players get @s hs_25
execute if score @s hs_tid_2 matches 26 store result score @s hs_temp run scoreboard players get @s hs_26
execute if score @s hs_tid_2 matches 27 store result score @s hs_temp run scoreboard players get @s hs_27
execute if score @s hs_tid_2 matches 28 store result score @s hs_temp run scoreboard players get @s hs_28
execute if score @s hs_tid_2 matches 29 store result score @s hs_temp run scoreboard players get @s hs_29
execute if score @s hs_tid_2 matches 30 store result score @s hs_temp run scoreboard players get @s hs_30
execute if score @s hs_tid_2 matches 31 store result score @s hs_temp run scoreboard players get @s hs_31
execute if score @s hs_tid_2 matches 32 store result score @s hs_temp run scoreboard players get @s hs_32
execute if score @s hs_tid_2 matches 33 store result score @s hs_temp run scoreboard players get @s hs_33
execute if score @s hs_tid_2 matches 34 store result score @s hs_temp run scoreboard players get @s hs_34
execute if score @s hs_tid_2 matches 35 store result score @s hs_temp run scoreboard players get @s hs_35
execute if score @s hs_tid_2 matches 36 store result score @s hs_temp run scoreboard players get @s hs_36
execute if score @s hs_tid_2 matches 37 store result score @s hs_temp run scoreboard players get @s hs_37
execute if score @s hs_tid_2 matches 38 store result score @s hs_temp run scoreboard players get @s hs_38
execute if score @s hs_tid_2 matches 39 store result score @s hs_temp run scoreboard players get @s hs_39
scoreboard players operation @s hs_temp -= @s hs_base_2
execute if score @s hs_temp > @s hs_targ_2 run scoreboard players operation @s hs_temp = @s hs_targ_2
execute unless score @s hs_tid_2 matches 32..39 run scoreboard players operation @s hs_prog_2 = @s hs_temp
execute if score @s hs_prog_2 >= @s hs_targ_2 unless score @s hs_tid_2 matches -1 run function hard_survival_1:tasks/complete_notify with {slot:"2"}

# --- Slot 3 ---
execute if score @s hs_tid_3 matches 0 store result score @s hs_temp run scoreboard players get @s hs_0
execute if score @s hs_tid_3 matches 1 store result score @s hs_temp run scoreboard players get @s hs_1
execute if score @s hs_tid_3 matches 2 store result score @s hs_temp run scoreboard players get @s hs_2
execute if score @s hs_tid_3 matches 3 store result score @s hs_temp run scoreboard players get @s hs_3
execute if score @s hs_tid_3 matches 4 store result score @s hs_temp run scoreboard players get @s hs_4
execute if score @s hs_tid_3 matches 5 store result score @s hs_temp run scoreboard players get @s hs_5
execute if score @s hs_tid_3 matches 6 store result score @s hs_temp run scoreboard players get @s hs_6
execute if score @s hs_tid_3 matches 7 store result score @s hs_temp run scoreboard players get @s hs_7
execute if score @s hs_tid_3 matches 8 store result score @s hs_temp run scoreboard players get @s hs_8
execute if score @s hs_tid_3 matches 9 store result score @s hs_temp run scoreboard players get @s hs_9
execute if score @s hs_tid_3 matches 10 store result score @s hs_temp run scoreboard players get @s hs_10
execute if score @s hs_tid_3 matches 11 store result score @s hs_temp run scoreboard players get @s hs_11
execute if score @s hs_tid_3 matches 12 store result score @s hs_temp run scoreboard players get @s hs_12
execute if score @s hs_tid_3 matches 13 store result score @s hs_temp run scoreboard players get @s hs_13
execute if score @s hs_tid_3 matches 14 store result score @s hs_temp run scoreboard players get @s hs_14
execute if score @s hs_tid_3 matches 15 store result score @s hs_temp run scoreboard players get @s hs_15
execute if score @s hs_tid_3 matches 16 store result score @s hs_temp run scoreboard players get @s hs_16
execute if score @s hs_tid_3 matches 17 store result score @s hs_temp run scoreboard players get @s hs_17
execute if score @s hs_tid_3 matches 18 store result score @s hs_temp run scoreboard players get @s hs_18
execute if score @s hs_tid_3 matches 19 store result score @s hs_temp run scoreboard players get @s hs_19
execute if score @s hs_tid_3 matches 20 store result score @s hs_temp run scoreboard players get @s hs_20
execute if score @s hs_tid_3 matches 21 store result score @s hs_temp run scoreboard players get @s hs_21
execute if score @s hs_tid_3 matches 22 store result score @s hs_temp run scoreboard players get @s hs_22
execute if score @s hs_tid_3 matches 23 store result score @s hs_temp run scoreboard players get @s hs_23
execute if score @s hs_tid_3 matches 24 store result score @s hs_temp run scoreboard players get @s hs_24
execute if score @s hs_tid_3 matches 25 store result score @s hs_temp run scoreboard players get @s hs_25
execute if score @s hs_tid_3 matches 26 store result score @s hs_temp run scoreboard players get @s hs_26
execute if score @s hs_tid_3 matches 27 store result score @s hs_temp run scoreboard players get @s hs_27
execute if score @s hs_tid_3 matches 28 store result score @s hs_temp run scoreboard players get @s hs_28
execute if score @s hs_tid_3 matches 29 store result score @s hs_temp run scoreboard players get @s hs_29
execute if score @s hs_tid_3 matches 30 store result score @s hs_temp run scoreboard players get @s hs_30
execute if score @s hs_tid_3 matches 31 store result score @s hs_temp run scoreboard players get @s hs_31
execute if score @s hs_tid_3 matches 32 store result score @s hs_temp run scoreboard players get @s hs_32
execute if score @s hs_tid_3 matches 33 store result score @s hs_temp run scoreboard players get @s hs_33
execute if score @s hs_tid_3 matches 34 store result score @s hs_temp run scoreboard players get @s hs_34
execute if score @s hs_tid_3 matches 35 store result score @s hs_temp run scoreboard players get @s hs_35
execute if score @s hs_tid_3 matches 36 store result score @s hs_temp run scoreboard players get @s hs_36
execute if score @s hs_tid_3 matches 37 store result score @s hs_temp run scoreboard players get @s hs_37
execute if score @s hs_tid_3 matches 38 store result score @s hs_temp run scoreboard players get @s hs_38
execute if score @s hs_tid_3 matches 39 store result score @s hs_temp run scoreboard players get @s hs_39
scoreboard players operation @s hs_temp -= @s hs_base_3
execute if score @s hs_temp > @s hs_targ_3 run scoreboard players operation @s hs_temp = @s hs_targ_3
execute unless score @s hs_tid_3 matches 32..39 run scoreboard players operation @s hs_prog_3 = @s hs_temp
execute if score @s hs_prog_3 >= @s hs_targ_3 unless score @s hs_tid_3 matches -1 run function hard_survival_1:tasks/complete_notify with {slot:"3"}

# --- Slot 4 ---
execute if score @s hs_tid_4 matches 0 store result score @s hs_temp run scoreboard players get @s hs_0
execute if score @s hs_tid_4 matches 1 store result score @s hs_temp run scoreboard players get @s hs_1
execute if score @s hs_tid_4 matches 2 store result score @s hs_temp run scoreboard players get @s hs_2
execute if score @s hs_tid_4 matches 3 store result score @s hs_temp run scoreboard players get @s hs_3
execute if score @s hs_tid_4 matches 4 store result score @s hs_temp run scoreboard players get @s hs_4
execute if score @s hs_tid_4 matches 5 store result score @s hs_temp run scoreboard players get @s hs_5
execute if score @s hs_tid_4 matches 6 store result score @s hs_temp run scoreboard players get @s hs_6
execute if score @s hs_tid_4 matches 7 store result score @s hs_temp run scoreboard players get @s hs_7
execute if score @s hs_tid_4 matches 8 store result score @s hs_temp run scoreboard players get @s hs_8
execute if score @s hs_tid_4 matches 9 store result score @s hs_temp run scoreboard players get @s hs_9
execute if score @s hs_tid_4 matches 10 store result score @s hs_temp run scoreboard players get @s hs_10
execute if score @s hs_tid_4 matches 11 store result score @s hs_temp run scoreboard players get @s hs_11
execute if score @s hs_tid_4 matches 12 store result score @s hs_temp run scoreboard players get @s hs_12
execute if score @s hs_tid_4 matches 13 store result score @s hs_temp run scoreboard players get @s hs_13
execute if score @s hs_tid_4 matches 14 store result score @s hs_temp run scoreboard players get @s hs_14
execute if score @s hs_tid_4 matches 15 store result score @s hs_temp run scoreboard players get @s hs_15
execute if score @s hs_tid_4 matches 16 store result score @s hs_temp run scoreboard players get @s hs_16
execute if score @s hs_tid_4 matches 17 store result score @s hs_temp run scoreboard players get @s hs_17
execute if score @s hs_tid_4 matches 18 store result score @s hs_temp run scoreboard players get @s hs_18
execute if score @s hs_tid_4 matches 19 store result score @s hs_temp run scoreboard players get @s hs_19
execute if score @s hs_tid_4 matches 20 store result score @s hs_temp run scoreboard players get @s hs_20
execute if score @s hs_tid_4 matches 21 store result score @s hs_temp run scoreboard players get @s hs_21
execute if score @s hs_tid_4 matches 22 store result score @s hs_temp run scoreboard players get @s hs_22
execute if score @s hs_tid_4 matches 23 store result score @s hs_temp run scoreboard players get @s hs_23
execute if score @s hs_tid_4 matches 24 store result score @s hs_temp run scoreboard players get @s hs_24
execute if score @s hs_tid_4 matches 25 store result score @s hs_temp run scoreboard players get @s hs_25
execute if score @s hs_tid_4 matches 26 store result score @s hs_temp run scoreboard players get @s hs_26
execute if score @s hs_tid_4 matches 27 store result score @s hs_temp run scoreboard players get @s hs_27
execute if score @s hs_tid_4 matches 28 store result score @s hs_temp run scoreboard players get @s hs_28
execute if score @s hs_tid_4 matches 29 store result score @s hs_temp run scoreboard players get @s hs_29
execute if score @s hs_tid_4 matches 30 store result score @s hs_temp run scoreboard players get @s hs_30
execute if score @s hs_tid_4 matches 31 store result score @s hs_temp run scoreboard players get @s hs_31
execute if score @s hs_tid_4 matches 32 store result score @s hs_temp run scoreboard players get @s hs_32
execute if score @s hs_tid_4 matches 33 store result score @s hs_temp run scoreboard players get @s hs_33
execute if score @s hs_tid_4 matches 34 store result score @s hs_temp run scoreboard players get @s hs_34
execute if score @s hs_tid_4 matches 35 store result score @s hs_temp run scoreboard players get @s hs_35
execute if score @s hs_tid_4 matches 36 store result score @s hs_temp run scoreboard players get @s hs_36
execute if score @s hs_tid_4 matches 37 store result score @s hs_temp run scoreboard players get @s hs_37
execute if score @s hs_tid_4 matches 38 store result score @s hs_temp run scoreboard players get @s hs_38
execute if score @s hs_tid_4 matches 39 store result score @s hs_temp run scoreboard players get @s hs_39
scoreboard players operation @s hs_temp -= @s hs_base_4
execute if score @s hs_temp > @s hs_targ_4 run scoreboard players operation @s hs_temp = @s hs_targ_4
execute unless score @s hs_tid_4 matches 32..39 run scoreboard players operation @s hs_prog_4 = @s hs_temp
execute if score @s hs_prog_4 >= @s hs_targ_4 unless score @s hs_tid_4 matches -1 run function hard_survival_1:tasks/complete_notify with {slot:"4"}

# --- Slot 5 ---
execute if score @s hs_tid_5 matches 0 store result score @s hs_temp run scoreboard players get @s hs_0
execute if score @s hs_tid_5 matches 1 store result score @s hs_temp run scoreboard players get @s hs_1
execute if score @s hs_tid_5 matches 2 store result score @s hs_temp run scoreboard players get @s hs_2
execute if score @s hs_tid_5 matches 3 store result score @s hs_temp run scoreboard players get @s hs_3
execute if score @s hs_tid_5 matches 4 store result score @s hs_temp run scoreboard players get @s hs_4
execute if score @s hs_tid_5 matches 5 store result score @s hs_temp run scoreboard players get @s hs_5
execute if score @s hs_tid_5 matches 6 store result score @s hs_temp run scoreboard players get @s hs_6
execute if score @s hs_tid_5 matches 7 store result score @s hs_temp run scoreboard players get @s hs_7
execute if score @s hs_tid_5 matches 8 store result score @s hs_temp run scoreboard players get @s hs_8
execute if score @s hs_tid_5 matches 9 store result score @s hs_temp run scoreboard players get @s hs_9
execute if score @s hs_tid_5 matches 10 store result score @s hs_temp run scoreboard players get @s hs_10
execute if score @s hs_tid_5 matches 11 store result score @s hs_temp run scoreboard players get @s hs_11
execute if score @s hs_tid_5 matches 12 store result score @s hs_temp run scoreboard players get @s hs_12
execute if score @s hs_tid_5 matches 13 store result score @s hs_temp run scoreboard players get @s hs_13
execute if score @s hs_tid_5 matches 14 store result score @s hs_temp run scoreboard players get @s hs_14
execute if score @s hs_tid_5 matches 15 store result score @s hs_temp run scoreboard players get @s hs_15
execute if score @s hs_tid_5 matches 16 store result score @s hs_temp run scoreboard players get @s hs_16
execute if score @s hs_tid_5 matches 17 store result score @s hs_temp run scoreboard players get @s hs_17
execute if score @s hs_tid_5 matches 18 store result score @s hs_temp run scoreboard players get @s hs_18
execute if score @s hs_tid_5 matches 19 store result score @s hs_temp run scoreboard players get @s hs_19
execute if score @s hs_tid_5 matches 20 store result score @s hs_temp run scoreboard players get @s hs_20
execute if score @s hs_tid_5 matches 21 store result score @s hs_temp run scoreboard players get @s hs_21
execute if score @s hs_tid_5 matches 22 store result score @s hs_temp run scoreboard players get @s hs_22
execute if score @s hs_tid_5 matches 23 store result score @s hs_temp run scoreboard players get @s hs_23
execute if score @s hs_tid_5 matches 24 store result score @s hs_temp run scoreboard players get @s hs_24
execute if score @s hs_tid_5 matches 25 store result score @s hs_temp run scoreboard players get @s hs_25
execute if score @s hs_tid_5 matches 26 store result score @s hs_temp run scoreboard players get @s hs_26
execute if score @s hs_tid_5 matches 27 store result score @s hs_temp run scoreboard players get @s hs_27
execute if score @s hs_tid_5 matches 28 store result score @s hs_temp run scoreboard players get @s hs_28
execute if score @s hs_tid_5 matches 29 store result score @s hs_temp run scoreboard players get @s hs_29
execute if score @s hs_tid_5 matches 30 store result score @s hs_temp run scoreboard players get @s hs_30
execute if score @s hs_tid_5 matches 31 store result score @s hs_temp run scoreboard players get @s hs_31
execute if score @s hs_tid_5 matches 32 store result score @s hs_temp run scoreboard players get @s hs_32
execute if score @s hs_tid_5 matches 33 store result score @s hs_temp run scoreboard players get @s hs_33
execute if score @s hs_tid_5 matches 34 store result score @s hs_temp run scoreboard players get @s hs_34
execute if score @s hs_tid_5 matches 35 store result score @s hs_temp run scoreboard players get @s hs_35
execute if score @s hs_tid_5 matches 36 store result score @s hs_temp run scoreboard players get @s hs_36
execute if score @s hs_tid_5 matches 37 store result score @s hs_temp run scoreboard players get @s hs_37
execute if score @s hs_tid_5 matches 38 store result score @s hs_temp run scoreboard players get @s hs_38
execute if score @s hs_tid_5 matches 39 store result score @s hs_temp run scoreboard players get @s hs_39
scoreboard players operation @s hs_temp -= @s hs_base_5
execute if score @s hs_temp > @s hs_targ_5 run scoreboard players operation @s hs_temp = @s hs_targ_5
execute unless score @s hs_tid_5 matches 32..39 run scoreboard players operation @s hs_prog_5 = @s hs_temp
execute if score @s hs_prog_5 >= @s hs_targ_5 unless score @s hs_tid_5 matches -1 run function hard_survival_1:tasks/complete_notify with {slot:"5"}

# --- Slot 6 ---
execute if score @s hs_tid_6 matches 0 store result score @s hs_temp run scoreboard players get @s hs_0
execute if score @s hs_tid_6 matches 1 store result score @s hs_temp run scoreboard players get @s hs_1
execute if score @s hs_tid_6 matches 2 store result score @s hs_temp run scoreboard players get @s hs_2
execute if score @s hs_tid_6 matches 3 store result score @s hs_temp run scoreboard players get @s hs_3
execute if score @s hs_tid_6 matches 4 store result score @s hs_temp run scoreboard players get @s hs_4
execute if score @s hs_tid_6 matches 5 store result score @s hs_temp run scoreboard players get @s hs_5
execute if score @s hs_tid_6 matches 6 store result score @s hs_temp run scoreboard players get @s hs_6
execute if score @s hs_tid_6 matches 7 store result score @s hs_temp run scoreboard players get @s hs_7
execute if score @s hs_tid_6 matches 8 store result score @s hs_temp run scoreboard players get @s hs_8
execute if score @s hs_tid_6 matches 9 store result score @s hs_temp run scoreboard players get @s hs_9
execute if score @s hs_tid_6 matches 10 store result score @s hs_temp run scoreboard players get @s hs_10
execute if score @s hs_tid_6 matches 11 store result score @s hs_temp run scoreboard players get @s hs_11
execute if score @s hs_tid_6 matches 12 store result score @s hs_temp run scoreboard players get @s hs_12
execute if score @s hs_tid_6 matches 13 store result score @s hs_temp run scoreboard players get @s hs_13
execute if score @s hs_tid_6 matches 14 store result score @s hs_temp run scoreboard players get @s hs_14
execute if score @s hs_tid_6 matches 15 store result score @s hs_temp run scoreboard players get @s hs_15
execute if score @s hs_tid_6 matches 16 store result score @s hs_temp run scoreboard players get @s hs_16
execute if score @s hs_tid_6 matches 17 store result score @s hs_temp run scoreboard players get @s hs_17
execute if score @s hs_tid_6 matches 18 store result score @s hs_temp run scoreboard players get @s hs_18
execute if score @s hs_tid_6 matches 19 store result score @s hs_temp run scoreboard players get @s hs_19
execute if score @s hs_tid_6 matches 20 store result score @s hs_temp run scoreboard players get @s hs_20
execute if score @s hs_tid_6 matches 21 store result score @s hs_temp run scoreboard players get @s hs_21
execute if score @s hs_tid_6 matches 22 store result score @s hs_temp run scoreboard players get @s hs_22
execute if score @s hs_tid_6 matches 23 store result score @s hs_temp run scoreboard players get @s hs_23
execute if score @s hs_tid_6 matches 24 store result score @s hs_temp run scoreboard players get @s hs_24
execute if score @s hs_tid_6 matches 25 store result score @s hs_temp run scoreboard players get @s hs_25
execute if score @s hs_tid_6 matches 26 store result score @s hs_temp run scoreboard players get @s hs_26
execute if score @s hs_tid_6 matches 27 store result score @s hs_temp run scoreboard players get @s hs_27
execute if score @s hs_tid_6 matches 28 store result score @s hs_temp run scoreboard players get @s hs_28
execute if score @s hs_tid_6 matches 29 store result score @s hs_temp run scoreboard players get @s hs_29
execute if score @s hs_tid_6 matches 30 store result score @s hs_temp run scoreboard players get @s hs_30
execute if score @s hs_tid_6 matches 31 store result score @s hs_temp run scoreboard players get @s hs_31
execute if score @s hs_tid_6 matches 32 store result score @s hs_temp run scoreboard players get @s hs_32
execute if score @s hs_tid_6 matches 33 store result score @s hs_temp run scoreboard players get @s hs_33
execute if score @s hs_tid_6 matches 34 store result score @s hs_temp run scoreboard players get @s hs_34
execute if score @s hs_tid_6 matches 35 store result score @s hs_temp run scoreboard players get @s hs_35
execute if score @s hs_tid_6 matches 36 store result score @s hs_temp run scoreboard players get @s hs_36
execute if score @s hs_tid_6 matches 37 store result score @s hs_temp run scoreboard players get @s hs_37
execute if score @s hs_tid_6 matches 38 store result score @s hs_temp run scoreboard players get @s hs_38
execute if score @s hs_tid_6 matches 39 store result score @s hs_temp run scoreboard players get @s hs_39
scoreboard players operation @s hs_temp -= @s hs_base_6
execute if score @s hs_temp > @s hs_targ_6 run scoreboard players operation @s hs_temp = @s hs_targ_6
execute unless score @s hs_tid_6 matches 32..39 run scoreboard players operation @s hs_prog_6 = @s hs_temp
execute if score @s hs_prog_6 >= @s hs_targ_6 unless score @s hs_tid_6 matches -1 run function hard_survival_1:tasks/complete_notify with {slot:"6"}

# --- Slot 7 ---
execute if score @s hs_tid_7 matches 0 store result score @s hs_temp run scoreboard players get @s hs_0
execute if score @s hs_tid_7 matches 1 store result score @s hs_temp run scoreboard players get @s hs_1
execute if score @s hs_tid_7 matches 2 store result score @s hs_temp run scoreboard players get @s hs_2
execute if score @s hs_tid_7 matches 3 store result score @s hs_temp run scoreboard players get @s hs_3
execute if score @s hs_tid_7 matches 4 store result score @s hs_temp run scoreboard players get @s hs_4
execute if score @s hs_tid_7 matches 5 store result score @s hs_temp run scoreboard players get @s hs_5
execute if score @s hs_tid_7 matches 6 store result score @s hs_temp run scoreboard players get @s hs_6
execute if score @s hs_tid_7 matches 7 store result score @s hs_temp run scoreboard players get @s hs_7
execute if score @s hs_tid_7 matches 8 store result score @s hs_temp run scoreboard players get @s hs_8
execute if score @s hs_tid_7 matches 9 store result score @s hs_temp run scoreboard players get @s hs_9
execute if score @s hs_tid_7 matches 10 store result score @s hs_temp run scoreboard players get @s hs_10
execute if score @s hs_tid_7 matches 11 store result score @s hs_temp run scoreboard players get @s hs_11
execute if score @s hs_tid_7 matches 12 store result score @s hs_temp run scoreboard players get @s hs_12
execute if score @s hs_tid_7 matches 13 store result score @s hs_temp run scoreboard players get @s hs_13
execute if score @s hs_tid_7 matches 14 store result score @s hs_temp run scoreboard players get @s hs_14
execute if score @s hs_tid_7 matches 15 store result score @s hs_temp run scoreboard players get @s hs_15
execute if score @s hs_tid_7 matches 16 store result score @s hs_temp run scoreboard players get @s hs_16
execute if score @s hs_tid_7 matches 17 store result score @s hs_temp run scoreboard players get @s hs_17
execute if score @s hs_tid_7 matches 18 store result score @s hs_temp run scoreboard players get @s hs_18
execute if score @s hs_tid_7 matches 19 store result score @s hs_temp run scoreboard players get @s hs_19
execute if score @s hs_tid_7 matches 20 store result score @s hs_temp run scoreboard players get @s hs_20
execute if score @s hs_tid_7 matches 21 store result score @s hs_temp run scoreboard players get @s hs_21
execute if score @s hs_tid_7 matches 22 store result score @s hs_temp run scoreboard players get @s hs_22
execute if score @s hs_tid_7 matches 23 store result score @s hs_temp run scoreboard players get @s hs_23
execute if score @s hs_tid_7 matches 24 store result score @s hs_temp run scoreboard players get @s hs_24
execute if score @s hs_tid_7 matches 25 store result score @s hs_temp run scoreboard players get @s hs_25
execute if score @s hs_tid_7 matches 26 store result score @s hs_temp run scoreboard players get @s hs_26
execute if score @s hs_tid_7 matches 27 store result score @s hs_temp run scoreboard players get @s hs_27
execute if score @s hs_tid_7 matches 28 store result score @s hs_temp run scoreboard players get @s hs_28
execute if score @s hs_tid_7 matches 29 store result score @s hs_temp run scoreboard players get @s hs_29
execute if score @s hs_tid_7 matches 30 store result score @s hs_temp run scoreboard players get @s hs_30
execute if score @s hs_tid_7 matches 31 store result score @s hs_temp run scoreboard players get @s hs_31
execute if score @s hs_tid_7 matches 32 store result score @s hs_temp run scoreboard players get @s hs_32
execute if score @s hs_tid_7 matches 33 store result score @s hs_temp run scoreboard players get @s hs_33
execute if score @s hs_tid_7 matches 34 store result score @s hs_temp run scoreboard players get @s hs_34
execute if score @s hs_tid_7 matches 35 store result score @s hs_temp run scoreboard players get @s hs_35
execute if score @s hs_tid_7 matches 36 store result score @s hs_temp run scoreboard players get @s hs_36
execute if score @s hs_tid_7 matches 37 store result score @s hs_temp run scoreboard players get @s hs_37
execute if score @s hs_tid_7 matches 38 store result score @s hs_temp run scoreboard players get @s hs_38
execute if score @s hs_tid_7 matches 39 store result score @s hs_temp run scoreboard players get @s hs_39
scoreboard players operation @s hs_temp -= @s hs_base_7
execute if score @s hs_temp > @s hs_targ_7 run scoreboard players operation @s hs_temp = @s hs_targ_7
execute unless score @s hs_tid_7 matches 32..39 run scoreboard players operation @s hs_prog_7 = @s hs_temp
execute if score @s hs_prog_7 >= @s hs_targ_7 unless score @s hs_tid_7 matches -1 run function hard_survival_1:tasks/complete_notify with {slot:"7"}
