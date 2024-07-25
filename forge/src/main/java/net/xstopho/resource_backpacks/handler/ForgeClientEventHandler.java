package net.xstopho.resource_backpacks.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.ResourceBackpacks;
import net.xstopho.resource_backpacks.network.packets.OpenBackpackPacket;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;

@Mod.EventBusSubscriber(modid = BackpackConstants.MOD_ID, value = Dist.CLIENT)
public class ForgeClientEventHandler {

    @SubscribeEvent
    public static void registerClientTickEvents(TickEvent.ClientTickEvent.Post event) {
        if (KeyMappingRegistry.OPEN_BACKPACK.consumeClick()) {
            ResourceBackpacks.NETWORK.send(new OpenBackpackPacket(1), PacketDistributor.SERVER.noArg());
        }
    }
}
