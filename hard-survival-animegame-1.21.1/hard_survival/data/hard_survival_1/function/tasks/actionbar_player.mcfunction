# Update action bar with per-player daily progress
# Runs as @s
execute store result score #adone hs_temp run scoreboard players get @s hs_done
title @s actionbar {"text":"","extra":[
  {"text":"§6◆ Daily Tasks §7| §aDone: "},
  {"score":{"name":"@s","objective":"hs_done"},"color":"green"},
  {"text":"/5 §7| "},
  {"text":"§e/trigger hs_tasks §7for details","color":"gray"}
]}
