# ===================================================
# Detect right-click submit and route to item handler
# Runs as @s
# ===================================================

# Check if submit score increased
execute store result score @s hs_temp run scoreboard players get @s hs_submit
execute unless score @s hs_temp > @s hs_submit_last run return 0

# Update last submit counter
scoreboard players operation @s hs_submit_last = @s hs_temp

# Only process if player has active tasks
execute unless entity @s[tag=hs_active] run return 0

# Check mainhand item against all submit types
execute if items entity @s weapon.mainhand minecraft:iron_ingot run function hard_survival_1:tasks/submit_iron
execute if items entity @s weapon.mainhand minecraft:gold_ingot run function hard_survival_1:tasks/submit_gold
execute if items entity @s weapon.mainhand minecraft:diamond run function hard_survival_1:tasks/submit_diamond
execute if items entity @s weapon.mainhand minecraft:emerald run function hard_survival_1:tasks/submit_emerald
execute if items entity @s weapon.mainhand minecraft:coal run function hard_survival_1:tasks/submit_coal
execute if items entity @s weapon.mainhand minecraft:lapis_lazuli run function hard_survival_1:tasks/submit_lapis
execute if items entity @s weapon.mainhand minecraft:copper_ingot run function hard_survival_1:tasks/submit_copper
execute if items entity @s weapon.mainhand minecraft:amethyst_shard run function hard_survival_1:tasks/submit_amethyst
