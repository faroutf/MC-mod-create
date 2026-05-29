# ===================================================
# Re-roll until unique ID found (macro)
# Parameters: slot
# ===================================================
$execute store result score #roll hs_temp run random value 0..39

# Check if already selected - if so, recurse
$execute if score #roll hs_temp matches 0 if score #id_0 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 1 if score #id_1 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 2 if score #id_2 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 3 if score #id_3 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 4 if score #id_4 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 5 if score #id_5 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 6 if score #id_6 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 7 if score #id_7 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 8 if score #id_8 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 9 if score #id_9 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 10 if score #id_10 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 11 if score #id_11 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 12 if score #id_12 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 13 if score #id_13 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 14 if score #id_14 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 15 if score #id_15 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 16 if score #id_16 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 17 if score #id_17 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 18 if score #id_18 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 19 if score #id_19 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 20 if score #id_20 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 21 if score #id_21 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 22 if score #id_22 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 23 if score #id_23 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 24 if score #id_24 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 25 if score #id_25 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 26 if score #id_26 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 27 if score #id_27 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 28 if score #id_28 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 29 if score #id_29 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 30 if score #id_30 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 31 if score #id_31 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 32 if score #id_32 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 33 if score #id_33 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 34 if score #id_34 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 35 if score #id_35 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 36 if score #id_36 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 37 if score #id_37 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 38 if score #id_38 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}
$execute if score #roll hs_temp matches 39 if score #id_39 hs_flag matches 1 run function hard_survival_1:tasks/pick_reroll with {slot:$(slot)}

# If not selected, mark it
$execute if score #roll hs_temp matches 0 if score #id_0 hs_flag matches 0 run scoreboard players set #id_0 hs_flag 1
$execute if score #roll hs_temp matches 1 if score #id_1 hs_flag matches 0 run scoreboard players set #id_1 hs_flag 1
$execute if score #roll hs_temp matches 2 if score #id_2 hs_flag matches 0 run scoreboard players set #id_2 hs_flag 1
$execute if score #roll hs_temp matches 3 if score #id_3 hs_flag matches 0 run scoreboard players set #id_3 hs_flag 1
$execute if score #roll hs_temp matches 4 if score #id_4 hs_flag matches 0 run scoreboard players set #id_4 hs_flag 1
$execute if score #roll hs_temp matches 5 if score #id_5 hs_flag matches 0 run scoreboard players set #id_5 hs_flag 1
$execute if score #roll hs_temp matches 6 if score #id_6 hs_flag matches 0 run scoreboard players set #id_6 hs_flag 1
$execute if score #roll hs_temp matches 7 if score #id_7 hs_flag matches 0 run scoreboard players set #id_7 hs_flag 1
$execute if score #roll hs_temp matches 8 if score #id_8 hs_flag matches 0 run scoreboard players set #id_8 hs_flag 1
$execute if score #roll hs_temp matches 9 if score #id_9 hs_flag matches 0 run scoreboard players set #id_9 hs_flag 1
$execute if score #roll hs_temp matches 10 if score #id_10 hs_flag matches 0 run scoreboard players set #id_10 hs_flag 1
$execute if score #roll hs_temp matches 11 if score #id_11 hs_flag matches 0 run scoreboard players set #id_11 hs_flag 1
$execute if score #roll hs_temp matches 12 if score #id_12 hs_flag matches 0 run scoreboard players set #id_12 hs_flag 1
$execute if score #roll hs_temp matches 13 if score #id_13 hs_flag matches 0 run scoreboard players set #id_13 hs_flag 1
$execute if score #roll hs_temp matches 14 if score #id_14 hs_flag matches 0 run scoreboard players set #id_14 hs_flag 1
$execute if score #roll hs_temp matches 15 if score #id_15 hs_flag matches 0 run scoreboard players set #id_15 hs_flag 1
$execute if score #roll hs_temp matches 16 if score #id_16 hs_flag matches 0 run scoreboard players set #id_16 hs_flag 1
$execute if score #roll hs_temp matches 17 if score #id_17 hs_flag matches 0 run scoreboard players set #id_17 hs_flag 1
$execute if score #roll hs_temp matches 18 if score #id_18 hs_flag matches 0 run scoreboard players set #id_18 hs_flag 1
$execute if score #roll hs_temp matches 19 if score #id_19 hs_flag matches 0 run scoreboard players set #id_19 hs_flag 1
$execute if score #roll hs_temp matches 20 if score #id_20 hs_flag matches 0 run scoreboard players set #id_20 hs_flag 1
$execute if score #roll hs_temp matches 21 if score #id_21 hs_flag matches 0 run scoreboard players set #id_21 hs_flag 1
$execute if score #roll hs_temp matches 22 if score #id_22 hs_flag matches 0 run scoreboard players set #id_22 hs_flag 1
$execute if score #roll hs_temp matches 23 if score #id_23 hs_flag matches 0 run scoreboard players set #id_23 hs_flag 1
$execute if score #roll hs_temp matches 24 if score #id_24 hs_flag matches 0 run scoreboard players set #id_24 hs_flag 1
$execute if score #roll hs_temp matches 25 if score #id_25 hs_flag matches 0 run scoreboard players set #id_25 hs_flag 1
$execute if score #roll hs_temp matches 26 if score #id_26 hs_flag matches 0 run scoreboard players set #id_26 hs_flag 1
$execute if score #roll hs_temp matches 27 if score #id_27 hs_flag matches 0 run scoreboard players set #id_27 hs_flag 1
$execute if score #roll hs_temp matches 28 if score #id_28 hs_flag matches 0 run scoreboard players set #id_28 hs_flag 1
$execute if score #roll hs_temp matches 29 if score #id_29 hs_flag matches 0 run scoreboard players set #id_29 hs_flag 1
$execute if score #roll hs_temp matches 30 if score #id_30 hs_flag matches 0 run scoreboard players set #id_30 hs_flag 1
$execute if score #roll hs_temp matches 31 if score #id_31 hs_flag matches 0 run scoreboard players set #id_31 hs_flag 1
$execute if score #roll hs_temp matches 32 if score #id_32 hs_flag matches 0 run scoreboard players set #id_32 hs_flag 1
$execute if score #roll hs_temp matches 33 if score #id_33 hs_flag matches 0 run scoreboard players set #id_33 hs_flag 1
$execute if score #roll hs_temp matches 34 if score #id_34 hs_flag matches 0 run scoreboard players set #id_34 hs_flag 1
$execute if score #roll hs_temp matches 35 if score #id_35 hs_flag matches 0 run scoreboard players set #id_35 hs_flag 1
$execute if score #roll hs_temp matches 36 if score #id_36 hs_flag matches 0 run scoreboard players set #id_36 hs_flag 1
$execute if score #roll hs_temp matches 37 if score #id_37 hs_flag matches 0 run scoreboard players set #id_37 hs_flag 1
$execute if score #roll hs_temp matches 38 if score #id_38 hs_flag matches 0 run scoreboard players set #id_38 hs_flag 1
$execute if score #roll hs_temp matches 39 if score #id_39 hs_flag matches 0 run scoreboard players set #id_39 hs_flag 1

# Store task ID
$scoreboard players operation @s hs_tid_$(slot) = #roll hs_temp
