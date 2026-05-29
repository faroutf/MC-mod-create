# ===================================================
# Set random target amounts based on task type
# Kill=10..100, Mine=16..64, Craft=1..8, Submit=16..64
# Runs as @s
# ===================================================

# Slot 0
execute if score @s hs_tid_0 matches 0..11 run execute store result score @s hs_targ_0 run random value 10..100
execute if score @s hs_tid_0 matches 12..21 run execute store result score @s hs_targ_0 run random value 16..64
execute if score @s hs_tid_0 matches 22..31 run execute store result score @s hs_targ_0 run random value 1..8
execute if score @s hs_tid_0 matches 32..39 run execute store result score @s hs_targ_0 run random value 16..64

# Slot 1
execute if score @s hs_tid_1 matches 0..11 run execute store result score @s hs_targ_1 run random value 10..100
execute if score @s hs_tid_1 matches 12..21 run execute store result score @s hs_targ_1 run random value 16..64
execute if score @s hs_tid_1 matches 22..31 run execute store result score @s hs_targ_1 run random value 1..8
execute if score @s hs_tid_1 matches 32..39 run execute store result score @s hs_targ_1 run random value 16..64

# Slot 2
execute if score @s hs_tid_2 matches 0..11 run execute store result score @s hs_targ_2 run random value 10..100
execute if score @s hs_tid_2 matches 12..21 run execute store result score @s hs_targ_2 run random value 16..64
execute if score @s hs_tid_2 matches 22..31 run execute store result score @s hs_targ_2 run random value 1..8
execute if score @s hs_tid_2 matches 32..39 run execute store result score @s hs_targ_2 run random value 16..64

# Slot 3
execute if score @s hs_tid_3 matches 0..11 run execute store result score @s hs_targ_3 run random value 10..100
execute if score @s hs_tid_3 matches 12..21 run execute store result score @s hs_targ_3 run random value 16..64
execute if score @s hs_tid_3 matches 22..31 run execute store result score @s hs_targ_3 run random value 1..8
execute if score @s hs_tid_3 matches 32..39 run execute store result score @s hs_targ_3 run random value 16..64

# Slot 4
execute if score @s hs_tid_4 matches 0..11 run execute store result score @s hs_targ_4 run random value 10..100
execute if score @s hs_tid_4 matches 12..21 run execute store result score @s hs_targ_4 run random value 16..64
execute if score @s hs_tid_4 matches 22..31 run execute store result score @s hs_targ_4 run random value 1..8
execute if score @s hs_tid_4 matches 32..39 run execute store result score @s hs_targ_4 run random value 16..64

# Slot 5
execute if score @s hs_tid_5 matches 0..11 run execute store result score @s hs_targ_5 run random value 10..100
execute if score @s hs_tid_5 matches 12..21 run execute store result score @s hs_targ_5 run random value 16..64
execute if score @s hs_tid_5 matches 22..31 run execute store result score @s hs_targ_5 run random value 1..8
execute if score @s hs_tid_5 matches 32..39 run execute store result score @s hs_targ_5 run random value 16..64

# Slot 6
execute if score @s hs_tid_6 matches 0..11 run execute store result score @s hs_targ_6 run random value 10..100
execute if score @s hs_tid_6 matches 12..21 run execute store result score @s hs_targ_6 run random value 16..64
execute if score @s hs_tid_6 matches 22..31 run execute store result score @s hs_targ_6 run random value 1..8
execute if score @s hs_tid_6 matches 32..39 run execute store result score @s hs_targ_6 run random value 16..64

# Slot 7
execute if score @s hs_tid_7 matches 0..11 run execute store result score @s hs_targ_7 run random value 10..100
execute if score @s hs_tid_7 matches 12..21 run execute store result score @s hs_targ_7 run random value 16..64
execute if score @s hs_tid_7 matches 22..31 run execute store result score @s hs_targ_7 run random value 1..8
execute if score @s hs_tid_7 matches 32..39 run execute store result score @s hs_targ_7 run random value 16..64
