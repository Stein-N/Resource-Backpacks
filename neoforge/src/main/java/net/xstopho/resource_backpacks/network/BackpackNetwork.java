package net.xstopho.resource_backpacks.network;

import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.xstopho.resource_backpacks.network.packets.OpenBackpackPacket;

public class BackpackNetwork {

    public static void registerServer(PayloadRegistrar payload) {
        payload.playToServer(OpenBackpackPacket.PACKET_TYPE, OpenBackpackPacket.PACKET_CODEC, OpenBackpackPacket::apply);
    }
}
