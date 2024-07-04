package net.xstopho.resource_backpacks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.xstopho.resource_backpacks.config.BackpackConfig;
import net.xstopho.resource_backpacks.network.OpenBackpackPacket;
import net.xstopho.resource_backpacks.registries.DataComponentsRegistry;
import net.xstopho.resource_backpacks.registries.ItemRegistry;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resource_config_api.api.ConfigRegistry;

public class ResourceBackpacks implements ModInitializer {
    @Override
    public void onInitialize() {
        ConfigRegistry.register(BackpackConstants.MOD_ID, BackpackConfig.BUILDER);

        DataComponentsRegistry.init();

        ItemRegistry.init();
        MenuTypeRegistry.init();

        initServerPackets();
    }

    private void initServerPackets() {
        PayloadTypeRegistry.playC2S().register(OpenBackpackPacket.PACKET_TYPE, OpenBackpackPacket.PACKET_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(OpenBackpackPacket.PACKET_TYPE, OpenBackpackPacket::apply);
    }
}
