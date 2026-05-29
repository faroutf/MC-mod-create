# ===================================================
# Hard Survival - Start Command
# Activates the daily quest system
# Usage: /function hard_survival_1:start
# ===================================================

# Guard: already started?
execute if data storage hard_survival_1:flags started run tellraw @s {"text":"[HardSurvival] 系统已启动，无需重复运行","color":"red"}
execute if data storage hard_survival_1:flags started run return 0

tellraw @a {"text":"§6[HardSurvival] 正在启动每日任务系统...","color":"gold"}

# --- Player tracking scoreboards ---
scoreboard objectives add hs_sleep minecraft.custom:minecraft.sleep_in_bed
scoreboard objectives add hs_sleep_last dummy
scoreboard objectives add hs_submit minecraft.used:minecraft.carrot_on_a_stick
scoreboard objectives add hs_done dummy
scoreboard objectives add hs_temp dummy
scoreboard objectives add hs_need dummy
scoreboard objectives add hs_day dummy
scoreboard objectives add hs_new dummy
scoreboard objectives add hs_roll dummy
scoreboard objectives add hs_flag dummy
scoreboard objectives add hs_submit_last dummy
scoreboard objectives add hs_tasks trigger

# --- Per-slot tracking ---
scoreboard objectives add hs_tid_0 dummy
scoreboard objectives add hs_tid_1 dummy
scoreboard objectives add hs_tid_2 dummy
scoreboard objectives add hs_tid_3 dummy
scoreboard objectives add hs_tid_4 dummy
scoreboard objectives add hs_tid_5 dummy
scoreboard objectives add hs_tid_6 dummy
scoreboard objectives add hs_tid_7 dummy
scoreboard objectives add hs_targ_0 dummy
scoreboard objectives add hs_targ_1 dummy
scoreboard objectives add hs_targ_2 dummy
scoreboard objectives add hs_targ_3 dummy
scoreboard objectives add hs_targ_4 dummy
scoreboard objectives add hs_targ_5 dummy
scoreboard objectives add hs_targ_6 dummy
scoreboard objectives add hs_targ_7 dummy
scoreboard objectives add hs_prog_0 dummy
scoreboard objectives add hs_prog_1 dummy
scoreboard objectives add hs_prog_2 dummy
scoreboard objectives add hs_prog_3 dummy
scoreboard objectives add hs_prog_4 dummy
scoreboard objectives add hs_prog_5 dummy
scoreboard objectives add hs_prog_6 dummy
scoreboard objectives add hs_prog_7 dummy
scoreboard objectives add hs_base_0 dummy
scoreboard objectives add hs_base_1 dummy
scoreboard objectives add hs_base_2 dummy
scoreboard objectives add hs_base_3 dummy
scoreboard objectives add hs_base_4 dummy
scoreboard objectives add hs_base_5 dummy
scoreboard objectives add hs_base_6 dummy
scoreboard objectives add hs_base_7 dummy

# --- Task objectives (0-11: kill, 12-21: mine, 22-31: craft, 32-39: submit) ---
scoreboard objectives add hs_0 minecraft.killed:minecraft.zombie
scoreboard objectives add hs_1 minecraft.killed:minecraft.skeleton
scoreboard objectives add hs_2 minecraft.killed:minecraft.spider
scoreboard objectives add hs_3 minecraft.killed:minecraft.creeper
scoreboard objectives add hs_4 minecraft.killed:minecraft.enderman
scoreboard objectives add hs_5 minecraft.killed:minecraft.witch
scoreboard objectives add hs_6 minecraft.killed:minecraft.drowned
scoreboard objectives add hs_7 minecraft.killed:minecraft.husk
scoreboard objectives add hs_8 minecraft.killed:minecraft.stray
scoreboard objectives add hs_9 minecraft.killed:minecraft.blaze
scoreboard objectives add hs_10 minecraft.killed:minecraft.wither_skeleton
scoreboard objectives add hs_11 minecraft.killed:minecraft.slime
scoreboard objectives add hs_12 minecraft.mined:minecraft.stone
scoreboard objectives add hs_13 minecraft.mined:minecraft.iron_ore
scoreboard objectives add hs_14 minecraft.mined:minecraft.coal_ore
scoreboard objectives add hs_15 minecraft.mined:minecraft.gold_ore
scoreboard objectives add hs_16 minecraft.mined:minecraft.diamond_ore
scoreboard objectives add hs_17 minecraft.mined:minecraft.redstone_ore
scoreboard objectives add hs_18 minecraft.mined:minecraft.lapis_ore
scoreboard objectives add hs_19 minecraft.mined:minecraft.copper_ore
scoreboard objectives add hs_20 minecraft.mined:minecraft.sand
scoreboard objectives add hs_21 minecraft.mined:minecraft.gravel
scoreboard objectives add hs_22 minecraft.crafted:minecraft.iron_pickaxe
scoreboard objectives add hs_23 minecraft.crafted:minecraft.iron_sword
scoreboard objectives add hs_24 minecraft.crafted:minecraft.iron_chestplate
scoreboard objectives add hs_25 minecraft.crafted:minecraft.bread
scoreboard objectives add hs_26 minecraft.crafted:minecraft.torch
scoreboard objectives add hs_27 minecraft.crafted:minecraft.chest
scoreboard objectives add hs_28 minecraft.crafted:minecraft.furnace
scoreboard objectives add hs_29 minecraft.crafted:minecraft.crafting_table
scoreboard objectives add hs_30 minecraft.crafted:minecraft.iron_block
scoreboard objectives add hs_31 minecraft.crafted:minecraft.stone_bricks
scoreboard objectives add hs_32 dummy
scoreboard objectives add hs_33 dummy
scoreboard objectives add hs_34 dummy
scoreboard objectives add hs_35 dummy
scoreboard objectives add hs_36 dummy
scoreboard objectives add hs_37 dummy
scoreboard objectives add hs_38 dummy
scoreboard objectives add hs_39 dummy

# --- Store task pool (clear first for idempotent re-runs) ---
data remove storage hard_survival_1:pool tasks
data modify storage hard_survival_1:pool tasks append value {id:0,type:0,name:'{"text":"击杀僵尸"}',target:"minecraft:zombie",obj:"hs_0"}
data modify storage hard_survival_1:pool tasks append value {id:1,type:0,name:'{"text":"击杀骷髅"}',target:"minecraft:skeleton",obj:"hs_1"}
data modify storage hard_survival_1:pool tasks append value {id:2,type:0,name:'{"text":"击杀蜘蛛"}',target:"minecraft:spider",obj:"hs_2"}
data modify storage hard_survival_1:pool tasks append value {id:3,type:0,name:'{"text":"击杀苦力怕"}',target:"minecraft:creeper",obj:"hs_3"}
data modify storage hard_survival_1:pool tasks append value {id:4,type:0,name:'{"text":"击杀末影人"}',target:"minecraft:enderman",obj:"hs_4"}
data modify storage hard_survival_1:pool tasks append value {id:5,type:0,name:'{"text":"击杀女巫"}',target:"minecraft:witch",obj:"hs_5"}
data modify storage hard_survival_1:pool tasks append value {id:6,type:0,name:'{"text":"击杀溺尸"}',target:"minecraft:drowned",obj:"hs_6"}
data modify storage hard_survival_1:pool tasks append value {id:7,type:0,name:'{"text":"击杀尸壳"}',target:"minecraft:husk",obj:"hs_7"}
data modify storage hard_survival_1:pool tasks append value {id:8,type:0,name:'{"text":"击杀流浪者"}',target:"minecraft:stray",obj:"hs_8"}
data modify storage hard_survival_1:pool tasks append value {id:9,type:0,name:'{"text":"击杀烈焰人"}',target:"minecraft:blaze",obj:"hs_9"}
data modify storage hard_survival_1:pool tasks append value {id:10,type:0,name:'{"text":"击杀凋零骷髅"}',target:"minecraft:wither_skeleton",obj:"hs_10"}
data modify storage hard_survival_1:pool tasks append value {id:11,type:0,name:'{"text":"击杀史莱姆"}',target:"minecraft:slime",obj:"hs_11"}
data modify storage hard_survival_1:pool tasks append value {id:12,type:1,name:'{"text":"挖掘石头"}',target:"minecraft:stone",obj:"hs_12"}
data modify storage hard_survival_1:pool tasks append value {id:13,type:1,name:'{"text":"挖掘铁矿石"}',target:"minecraft:iron_ore",obj:"hs_13"}
data modify storage hard_survival_1:pool tasks append value {id:14,type:1,name:'{"text":"挖掘煤矿石"}',target:"minecraft:coal_ore",obj:"hs_14"}
data modify storage hard_survival_1:pool tasks append value {id:15,type:1,name:'{"text":"挖掘金矿石"}',target:"minecraft:gold_ore",obj:"hs_15"}
data modify storage hard_survival_1:pool tasks append value {id:16,type:1,name:'{"text":"挖掘钻石矿石"}',target:"minecraft:diamond_ore",obj:"hs_16"}
data modify storage hard_survival_1:pool tasks append value {id:17,type:1,name:'{"text":"挖掘红石矿石"}',target:"minecraft:redstone_ore",obj:"hs_17"}
data modify storage hard_survival_1:pool tasks append value {id:18,type:1,name:'{"text":"挖掘青金石矿石"}',target:"minecraft:lapis_ore",obj:"hs_18"}
data modify storage hard_survival_1:pool tasks append value {id:19,type:1,name:'{"text":"挖掘铜矿石"}',target:"minecraft:copper_ore",obj:"hs_19"}
data modify storage hard_survival_1:pool tasks append value {id:20,type:1,name:'{"text":"挖掘沙子"}',target:"minecraft:sand",obj:"hs_20"}
data modify storage hard_survival_1:pool tasks append value {id:21,type:1,name:'{"text":"挖掘沙砾"}',target:"minecraft:gravel",obj:"hs_21"}
data modify storage hard_survival_1:pool tasks append value {id:22,type:2,name:'{"text":"制作铁镐"}',target:"minecraft:iron_pickaxe",obj:"hs_22"}
data modify storage hard_survival_1:pool tasks append value {id:23,type:2,name:'{"text":"制作铁剑"}',target:"minecraft:iron_sword",obj:"hs_23"}
data modify storage hard_survival_1:pool tasks append value {id:24,type:2,name:'{"text":"制作铁胸甲"}',target:"minecraft:iron_chestplate",obj:"hs_24"}
data modify storage hard_survival_1:pool tasks append value {id:25,type:2,name:'{"text":"制作面包"}',target:"minecraft:bread",obj:"hs_25"}
data modify storage hard_survival_1:pool tasks append value {id:26,type:2,name:'{"text":"制作火把"}',target:"minecraft:torch",obj:"hs_26"}
data modify storage hard_survival_1:pool tasks append value {id:27,type:2,name:'{"text":"制作箱子"}',target:"minecraft:chest",obj:"hs_27"}
data modify storage hard_survival_1:pool tasks append value {id:28,type:2,name:'{"text":"制作熔炉"}',target:"minecraft:furnace",obj:"hs_28"}
data modify storage hard_survival_1:pool tasks append value {id:29,type:2,name:'{"text":"制作工作台"}',target:"minecraft:crafting_table",obj:"hs_29"}
data modify storage hard_survival_1:pool tasks append value {id:30,type:2,name:'{"text":"制作铁块"}',target:"minecraft:iron_block",obj:"hs_30"}
data modify storage hard_survival_1:pool tasks append value {id:31,type:2,name:'{"text":"制作石砖"}',target:"minecraft:stone_bricks",obj:"hs_31"}
data modify storage hard_survival_1:pool tasks append value {id:32,type:3,name:'{"text":"提交铁锭"}',submit_item:"minecraft:iron_ingot",obj:"hs_32"}
data modify storage hard_survival_1:pool tasks append value {id:33,type:3,name:'{"text":"提交金锭"}',submit_item:"minecraft:gold_ingot",obj:"hs_33"}
data modify storage hard_survival_1:pool tasks append value {id:34,type:3,name:'{"text":"提交钻石"}',submit_item:"minecraft:diamond",obj:"hs_34"}
data modify storage hard_survival_1:pool tasks append value {id:35,type:3,name:'{"text":"提交绿宝石"}',submit_item:"minecraft:emerald",obj:"hs_35"}
data modify storage hard_survival_1:pool tasks append value {id:36,type:3,name:'{"text":"提交煤炭"}',submit_item:"minecraft:coal",obj:"hs_36"}
data modify storage hard_survival_1:pool tasks append value {id:37,type:3,name:'{"text":"提交青金石"}',submit_item:"minecraft:lapis_lazuli",obj:"hs_37"}
data modify storage hard_survival_1:pool tasks append value {id:38,type:3,name:'{"text":"提交铜锭"}',submit_item:"minecraft:copper_ingot",obj:"hs_38"}
data modify storage hard_survival_1:pool tasks append value {id:39,type:3,name:'{"text":"提交紫水晶碎片"}',submit_item:"minecraft:amethyst_shard",obj:"hs_39"}

# --- Initialize players ---
scoreboard players set @a hs_new 1
scoreboard players set @a hs_sleep_last 0
scoreboard players set @a hs_submit_last 0
scoreboard players set @a hs_day 0
scoreboard players enable @a hs_tasks

# --- Give submit tool ---
function hard_survival_1:player/give_submit_tool

# --- Set started flag ---
data modify storage hard_survival_1:flags started set value true

tellraw @a {"text":"[HardSurvival] 日常任务系统已启动！醒来后开始刷新任务。","color":"green"}
tellraw @a {"text":"§7提示: 使用 /trigger hs_tasks 查看任务 /function hard_survival_1:debug 诊断","color":"gray"}
