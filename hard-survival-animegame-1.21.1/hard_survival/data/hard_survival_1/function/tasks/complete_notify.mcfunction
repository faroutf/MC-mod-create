# Mark task complete, increment counter, notify player
scoreboard players add @s hs_done 1
tellraw @s {"text":"","extra":[{"text":"[§6✓§r] 任务完成！已完成: ","color":"gold"},{"score":{"name":"@s","objective":"hs_done"},"color":"green"},{"text":"/8","color":"white"}]}
execute if score @s hs_done >= 5 run tellraw @s {"text":"§a§l★ 你已完成5个日常任务！今天安全了。","color":"green"}
