package com.adamcalculator.rpbuttonlib.client.forge;


import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ForgeEvent {
    @SubscribeEvent
    public static void onEvent(EntityJoinLevelEvent event) {
    }
}
