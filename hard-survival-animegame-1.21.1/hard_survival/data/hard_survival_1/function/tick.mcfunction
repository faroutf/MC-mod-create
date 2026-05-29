# ===================================================
# Hard Survival - Main Tick (20Hz)
# Check sleep, sync progress, handle submit, bossbar, trigger
# ===================================================

# --- Sleep detection ---
execute as @a run function hard_survival_1:player/on_wake

# --- Progress sync for active players ---
execute as @a[tag=hs_active] run function hard_survival_1:tasks/sync_progress

# --- Submit detection ---
execute as @a run function hard_survival_1:tasks/submit_check

# --- Action bar update (once per second) ---
execute if score #tick hs_temp matches 0 run function hard_survival_1:tasks/actionbar_update

# --- Trigger: hs_tasks (player runs /trigger hs_tasks) ---
execute as @a if score @s hs_tasks matches 1.. run function hard_survival_1:tasks/show_tasks

# --- Tick counter for rate-limiting ---
scoreboard players add #tick hs_temp 1
execute if score #tick hs_temp matches 20.. run scoreboard players set #tick hs_temp 0
