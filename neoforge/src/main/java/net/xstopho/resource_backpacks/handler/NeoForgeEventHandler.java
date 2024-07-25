package net.xstopho.resource_backpacks.handler;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.xstopho.resource_backpacks.BackpackConstants;

@EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class NeoForgeEventHandler {

    @SubscribeEvent
    public static void registerLoginEvents(PlayerEvent.PlayerLoggedInEvent event) {

    }
}
