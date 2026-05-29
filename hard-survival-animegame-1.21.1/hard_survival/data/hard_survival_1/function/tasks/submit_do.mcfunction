# ===================================================
# Execute item submission (macro)
# Parameters: item (minecraft:id), slot (0-7)
# Clears all items of type, credits up to remaining target
# Runs as @s
# ===================================================

# Clear all matching items, store count
$execute store result score @s hs_temp run clear @s $(item) 0

# Compute remaining needed
$scoreboard players operation @s hs_need = @s hs_targ_$(slot)
$scoreboard players operation @s hs_need -= @s hs_prog_$(slot)

# Cap credited amount at needed
execute if score @s hs_temp > @s hs_need run scoreboard players operation @s hs_temp = @s hs_need

# Credit progress
$scoreboard players operation @s hs_prog_$(slot) += @s hs_temp

# Notify player
$tellraw @s {"text":"","extra":[{"text":"[§6提交§r] ","color":"gold"},{"text":"已提交 ","color":"white"},{"score":{"name":"@s","objective":"hs_temp"},"color":"yellow"},{"text":" 个物品 (","color":"white"},{"score":{"name":"@s","objective":"hs_prog_$(slot)"},"color":"green"},{"text":"/","color":"white"},{"score":{"name":"@s","objective":"hs_targ_$(slot)"},"color":"green"},{"text":")","color":"white"}]}

# Check if task complete
$execute if score @s hs_prog_$(slot) >= @s hs_targ_$(slot) run function hard_survival_1:tasks/complete_notify
