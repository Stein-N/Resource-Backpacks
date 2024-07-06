package net.xstopho.resource_backpacks.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.xstopho.resource_backpacks.network.packets.OpenBackpackPacket;
import net.xstopho.resource_backpacks.network.packets.SyncBackpackLevelPacket;

public class BackpackNetwork {

    public static void initClient() {
        ClientPlayNetworking.registerGlobalReceiver(SyncBackpackLevelPacket.PACKET_TYPE, SyncBackpackLevelPacket::apply);
    }

    public static void initServer() {
        // Server to Client packets
        PayloadTypeRegistry.playS2C().register(SyncBackpackLevelPacket.PACKET_TYPE, SyncBackpackLevelPacket.PACKET_CODEC);

        // Client to Server packets
        PayloadTypeRegistry.playC2S().register(OpenBackpackPacket.PACKET_TYPE, OpenBackpackPacket.PACKET_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(OpenBackpackPacket.PACKET_TYPE, OpenBackpackPacket::apply);
    }
}
