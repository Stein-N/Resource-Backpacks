package net.xstopho.resource_backpacks.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.xstopho.resource_backpacks.network.packets.OpenBackpackPacket;

public class BackpackNetwork {

    public static void initServer() {

        // Client to Server packets
        PayloadTypeRegistry.playC2S().register(OpenBackpackPacket.PACKET_TYPE, OpenBackpackPacket.PACKET_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(OpenBackpackPacket.PACKET_TYPE, OpenBackpackPacket::apply);
    }
}
