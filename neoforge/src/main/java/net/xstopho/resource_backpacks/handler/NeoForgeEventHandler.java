package net.xstopho.resource_backpacks.handler;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.item.util.BackpackLevel;
import net.xstopho.resource_backpacks.network.packets.SyncBackpackLevelPacket;

@EventBusSubscriber(modid = BackpackConstants.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class NeoForgeEventHandler {

    @SubscribeEvent
    public static void registerLoginEvents(PlayerEvent.PlayerLoggedInEvent event) {
        BackpackConstants.LOG.info("Sync Backpack Level Settings with Client.");
        for (BackpackLevel level : BackpackLevel.values()) {
            PacketDistributor.sendToPlayer((ServerPlayer) event.getEntity(), new SyncBackpackLevelPacket(level.getName(), level.getRows(), level.getColumns()));
        }
    }
}
