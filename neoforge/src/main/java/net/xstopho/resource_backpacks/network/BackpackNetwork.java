package net.xstopho.resource_backpacks.network;

import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.xstopho.resource_backpacks.network.packets.OpenBackpackPacket;
import net.xstopho.resource_backpacks.network.packets.SyncBackpackLevelPacket;

public class BackpackNetwork {

    public static void registerClient(PayloadRegistrar payload) {
        payload.playToClient(SyncBackpackLevelPacket.PACKET_TYPE, SyncBackpackLevelPacket.PACKET_CODEC, SyncBackpackLevelPacket::apply);
    }

    public static void registerServer(PayloadRegistrar payload) {
        payload.playToServer(OpenBackpackPacket.PACKET_TYPE, OpenBackpackPacket.PACKET_CODEC, OpenBackpackPacket::apply);
    }
}
