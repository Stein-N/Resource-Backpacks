package net.xstopho.resource_backpacks.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.SimpleChannel;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.ResourceBackpacks;
import net.xstopho.resource_backpacks.network.packets.OpenBackpackPacket;

public class BackpackNetwork {

    public static SimpleChannel setupPackets() {
        SimpleChannel channel = ChannelBuilder.named(of("network")).acceptedVersions(Channel.VersionTest.exact(1)).networkProtocolVersion(1).simpleChannel();
        ResourceBackpacks.NETWORK = channel;

        channel.messageBuilder(OpenBackpackPacket.class, 0, NetworkDirection.PLAY_TO_SERVER).decoder(OpenBackpackPacket::decode).encoder(OpenBackpackPacket::encode).consumerNetworkThread(OpenBackpackPacket::handle).add();

        return channel;
    }

    public static ResourceLocation of(String id) {
        return ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, id);
    }
}
