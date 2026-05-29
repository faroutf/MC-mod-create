# ===================================================
# Player wake-up detection
# Runs as @s for each player
# ===================================================

# First: store current sleep count
execute store result score @s hs_temp run scoreboard players get @s hs_sleep

# Check if sleep count increased (player just woke up)
execute unless score @s hs_temp > @s hs_sleep_last run return 0

# Update last sleep
scoreboard players operation @s hs_sleep_last = @s hs_temp

# --- PLAYER JUST WOKE UP ---

# Check if first-time join (no previous tasks)
execute if score @s hs_new matches 1 run function hard_survival_1:tasks/first_refresh

# Check if has active tasks -> check completion
execute if score @s hs_new matches 0 run function hard_survival_1:tasks/check_complete
