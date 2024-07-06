package net.xstopho.resource_backpacks.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.xstopho.resource_backpacks.network.packets.OpenBackpackPacket;
import net.xstopho.resource_backpacks.network.packets.SyncBackpackInventorySettingsPacket;

public class BackpackNetwork {

    public static void initClient() {
        ClientPlayNetworking.registerGlobalReceiver(SyncBackpackInventorySettingsPacket.PACKET_TYPE, SyncBackpackInventorySettingsPacket::apply);
    }

    public static void initServer() {
        // Server to Client packets
        PayloadTypeRegistry.playS2C().register(SyncBackpackInventorySettingsPacket.PACKET_TYPE, SyncBackpackInventorySettingsPacket.PACKET_CODEC);

        // Client to Server packets
        PayloadTypeRegistry.playC2S().register(OpenBackpackPacket.PACKET_TYPE, OpenBackpackPacket.PACKET_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(OpenBackpackPacket.PACKET_TYPE, OpenBackpackPacket::apply);
    }
}
