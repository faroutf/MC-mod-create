package com.faroutf.daily_quests.client;

import com.faroutf.daily_quests.DailyQuests;
import com.faroutf.daily_quests.client.gui.QuestScreen;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = DailyQuests.MODID, value = Dist.CLIENT)
public class KeyBindings {
    public static final KeyMapping OPEN_QUEST_SCREEN = new KeyMapping(
        "key.daily_quests.open_quests",
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_K,
        "key.categories.daily_quests"
    );

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_QUEST_SCREEN);
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        while (OPEN_QUEST_SCREEN.consumeClick()) {
            Minecraft.getInstance().setScreen(new QuestScreen());
        }
    }
}
