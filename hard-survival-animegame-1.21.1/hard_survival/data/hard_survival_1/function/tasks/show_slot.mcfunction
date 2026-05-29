# ===================================================
# Show one task slot in chat (macro)
# Parameters: slot (0-7)
# Reads task name from pool storage based on task ID
# Runs as @s
# ===================================================

# Determine completion icon and progress
$execute unless score @s hs_prog_$(slot) >= @s hs_targ_$(slot) run tellraw @s {"text":"","extra":[
  {"text":"  §e◔ "},{"text":"[任务ID: "},{"score":{"name":"@s","objective":"hs_tid_$(slot)"},"color":"yellow"},{"text":"] "},
  {"score":{"name":"@s","objective":"hs_prog_$(slot)"},"color":"white"},
  {"text":"/"},{"score":{"name":"@s","objective":"hs_targ_$(slot)"},"color":"green"}
]}

$execute if score @s hs_prog_$(slot) >= @s hs_targ_$(slot) run tellraw @s {"text":"","extra":[
  {"text":"  §a☑ §a§m"},{"text":"[任务ID: "},{"score":{"name":"@s","objective":"hs_tid_$(slot)"},"color":"green"},{"text":"] "},
  {"score":{"name":"@s","objective":"hs_prog_$(slot)"},"color":"green"},
  {"text":"/"},{"score":{"name":"@s","objective":"hs_targ_$(slot)"},"color":"green"}
]}
