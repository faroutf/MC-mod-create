# Daily Quests Mod — 开发报告

**日期**：2026-05-27  
**模组**：Daily Quests (daily_quests) — NeoForge 1.21.1  
**作者**：faroutf  

---

## 一、项目概况

实现了完整的 Minecraft 每日任务系统模组，包含 22 个 Java 源文件，全部 BUILD SUCCESSFUL。

### 提交记录（15 次）

| 提交 | 说明 |
|---|---|
| `8d57afe` | 导入 NeoForge 1.21.1 模板 |
| `0862622` | Phase 1: 核心数据层（枚举/Record/Attachment/NBT 持久化） |
| `a221148` | Phase 2: 任务生成（5板×3任务池/日期刷新） |
| `a5d7ca0` | Phase 3: 5 个事件处理器（战斗/合成/挖矿/种植/畜牧） |
| `f6f2ea6` | Phase 4: 网络层（S2C 数据包/客户端缓存） |
| `70d5070` | Phase 5: 奖励系统（三级矿物池/C2S 领取） |
| `668a443` | Phase 6: 命令（/dailyquests show/progress/reset） |
| `639f881` | Phase 7: GUI（K 键任务面板/进度条） |
| `f51e707` | Phase 8: 润色（Toast/记分板/翻译） |
| `14d36a6` | 模组描述和作者信息 |
| `35e7bf0` | 后端：任务接取/取消机制（最多 3 个/每板块 1 个） |
| `7519e81` | 前端：GUI 交互修复/接取按钮/记分板/Toast |
| `e6e4bab` | 修复：面板自适应高度/按钮可点击/模糊白膜 |
| `f34be79` | 修复：消除高斯模糊/按钮数据刷新 |
| `96a2580` | 重写：仿原版容器界面风格 |
| `9bba81d` | 修复：玩家登录时立即同步任务数据 |
| `49bf9bb` | 重写：原版配色面板/scissor 裁剪滚动条 |
| `0bc5c0d` | 清理误提交的 .idea 文件 |
| `13f70f4` | Bug 修复：死亡保留任务数据/畜牧繁殖触发 |

---

## 二、功能清单

### 2.1 每日任务系统
- **5 个板块**：战斗(Combat)、合成(Crafting)、挖矿(Mining)、种植(Farming)、畜牧(Husbandry)
- 每板块每日随机生成 **3 个任务**（共 15 个），从 85+ 条目池中无放回抽取
- 数量按稀有度随机：普通 5-16、罕见 1-3
- **按游戏日刷新**（玩家起床/日期变化自动刷新）

### 2.2 任务接取机制
- 玩家**手动接取**任务（最多 3 个同时接取）
- 每板块**最多接取 1 个**任务
- 可**取消接取**，取消后进度清零
- **仅已接取任务追踪进度**

### 2.3 完成条件与奖励
- 完成 **3 个板块各 1 个任务** = 每日完成
- 奖励：三级矿物池加权随机（Coal 50% / Iron 35% / Diamond 15%）+ 100-200 经验值
- 奖励可配置（经验值范围、任务数量乘数）

### 2.4 事件监听
| 板块 | 监听事件 | 检测方式 |
|---|---|---|
| 战斗 | `LivingDeathEvent` | 击杀者为玩家 → 验证 EntityType |
| 合成 | `PlayerEvent.ItemCraftedEvent` | 验证合成结果 Item |
| 挖矿 | `BlockEvent.BreakEvent` | 验证破坏方块（矿石+深板岩变种） |
| 种植 | `BreakEvent` + `EntityPlaceEvent` | CropBlock.isMaxAge() 检查成熟度 |
| 畜牧 | `BabyEntitySpawnEvent` | getCausedByPlayer() 获取繁殖者 |

### 2.5 UI 系统
- **K 键**打开任务面板（可配置按键）
- 面板：原版容器配色、物品图标、彩色进度条、右侧滚动条
- **记分板**：右侧显示已接取任务进度 `任务名 (2/5)` 或完成 `(✓)`
- **Toast**：屏幕正中 1.5 倍字体淡入淡出提示
- **聊天**：每日任务摘要

### 2.6 命令
```
/dailyquests              — 显示当天任务详情
/dailyquests show         — 同上
/dailyquests progress     — 简要完成状态
/dailyquests reset        — 管理员重置任务
/dailyquests reroll       — 管理员重新随机任务
```

### 2.7 配置项
```properties
questAmountMultiplier = 1.0    # 任务数量乘数（0.1-10.0）
rewardXpMin = 100              # 经验奖励下限
rewardXpMax = 200              # 经验奖励上限
enableScoreboardOverlay = true # 记分板显示开关
enableToastNotifications = true # Toast 通知开关
```

---

## 三、技术架构

```
src/main/java/com/faroutf/daily_quests/
├── DailyQuests.java              # 主类（@Mod 入口）
├── Config.java                   # 配置（ModConfigSpec）
├── ModAttachments.java           # AttachmentType 注册
├── quest/ (6 files)
│   ├── QuestCategory.java        # 5板块枚举
│   ├── QuestDefinition.java      # 任务模板 Record
│   ├── QuestProgress.java        # 单任务进度追踪
│   ├── PlayerQuestData.java      # 玩家数据（INBTSerializable → NBT）
│   ├── QuestManager.java         # 核心逻辑（生成/推进/接取/取消/同步）
│   ├── QuestPools.java           # 静态目标池（85+条目）
│   └── QuestRewards.java         # 三级加权随机奖励
├── event/ (6 files)
│   ├── DailyResetHandler.java    # 日期检测+登录同步
│   ├── CombatEventHandler.java   # LivingDeathEvent
│   ├── CraftingEventHandler.java # ItemCraftedEvent
│   ├── MiningEventHandler.java   # BlockEvent.BreakEvent
│   ├── FarmingEventHandler.java  # BreakEvent + EntityPlaceEvent
│   └── HusbandryEventHandler.java # BabyEntitySpawnEvent
├── network/ (4 files)
│   ├── PacketHandler.java        # 数据包注册
│   ├── SyncQuestDataPacket.java  # S2C（StreamCodec）
│   ├── AcceptQuestPacket.java    # C2S 接取
│   ├── CancelQuestPacket.java    # C2S 取消
│   └── ClaimRewardPacket.java    # C2S 领取奖励
├── command/ (1 file)
│   └── DailyQuestsCommand.java   # Brigadier 命令树
└── client/ (5 files)
    ├── ClientQuestData.java      # 客户端数据缓存+版本追踪
    ├── KeyBindings.java          # K 键绑定
    └── gui/
        ├── QuestScreen.java      # 任务面板（滚动条/scissor裁剪）
        ├── QuestToast.java       # 居中淡入淡出 Toast
        └── QuestHudOverlay.java  # 右侧记分板
```

---

## 四、关键设计决策

1. **AttachmentType.serializable() + copyOnDeath()** — NeoForge 数据持久化方案，死亡时复制数据
2. **CustomPacketPayload + StreamCodec** — NeoForge 1.21.1 网络包标准
3. **RegisterGuiLayersEvent** — 自定义 GUI 层注册（Toast + 记分板）
4. **Scissor 裁剪** — 内容溢出时 GPU 级别裁剪+自定义滚动条
5. **定义注册表分离** — QuestDefinition 为服务端静态 Map，客户端不依赖

---

## 五、测试状态

- [x] 模组加载、编译通过（BUILD SUCCESSFUL）
- [x] K 键打开任务面板，内容清晰可读
- [x] 任务接取/取消按钮正常工作
- [x] 领取奖励按钮正常工作
- [x] 记分板显示已接取任务进度
- [x] Toast 通知居中显示
- [x] 死亡后任务数据保留
- [x] 畜牧繁殖任务正确触发
- [x] 面板滚动条正常
- [ ] 全面游戏内测试（待继续）
