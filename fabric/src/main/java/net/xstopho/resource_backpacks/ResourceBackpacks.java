package net.xstopho.resource_backpacks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.xstopho.resource_backpacks.config.BackpackConfig;
import net.xstopho.resource_backpacks.item.util.BackpackLevel;
import net.xstopho.resource_backpacks.network.BackpackNetwork;
import net.xstopho.resource_backpacks.network.packets.SyncBackpackLevelPacket;
import net.xstopho.resource_backpacks.registries.CreativeTabRegistry;
import net.xstopho.resource_backpacks.registries.DataComponentsRegistry;
import net.xstopho.resource_backpacks.registries.ItemRegistry;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resourceconfigapi.api.ConfigRegistry;

public class ResourceBackpacks implements ModInitializer {
    @Override
    public void onInitialize() {
        ConfigRegistry.register(BackpackConstants.MOD_ID, BackpackConfig.BUILDER, true);

        BackpackNetwork.initServer();

        DataComponentsRegistry.init();

        ItemRegistry.init();
        MenuTypeRegistry.init();

        CreativeTabRegistry.init();

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            BackpackConstants.LOG.info("Sync Backpack Level Settings with Client.");
            for (BackpackLevel level : BackpackLevel.values()) {
                sender.sendPacket(new SyncBackpackLevelPacket(level.getName(), level.getRows(), level.getColumns()));
            }
        });
    }
}
