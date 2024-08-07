package net.xstopho.resource_backpacks;

import net.fabricmc.api.ModInitializer;
import net.xstopho.resource_backpacks.config.BackpackConfig;
import net.xstopho.resource_backpacks.network.BackpackNetwork;
import net.xstopho.resource_backpacks.registries.*;
import net.xstopho.resourceconfigapi.api.ConfigRegistry;

public class ResourceBackpacks implements ModInitializer {
    @Override
    public void onInitialize() {
        ConfigRegistry.register(BackpackConstants.MOD_ID, BackpackConfig.BUILDER, true);

        BackpackNetwork.initServer();

        DataComponentsRegistry.init();
        BlockEntityTypeRegistry.init();

        //ItemRegistry.init();
        BlockRegistry.init();
        MenuTypeRegistry.init();

        CreativeTabRegistry.init();
    }
}
