package net.xstopho.resource_backpacks;

import net.fabricmc.api.ModInitializer;
import net.xstopho.resource_backpacks.registries.ItemRegistry;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resource_config_api.api.ConfigRegistry;

public class ResourceBackpacks implements ModInitializer {
    @Override
    public void onInitialize() {
        ConfigRegistry.register(BackpackConstants.MOD_ID, BackpackConfig.BUILDER);

        ItemRegistry.init();
        MenuTypeRegistry.init();
    }
}
