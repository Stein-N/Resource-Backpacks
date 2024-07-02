package net.xstopho.resource_backpacks;

import net.fabricmc.api.ModInitializer;
import net.xstopho.resource_backpacks.registries.ItemRegistry;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;

public class ResourceBackpacks implements ModInitializer {
    @Override
    public void onInitialize() {
        ItemRegistry.init();
        MenuTypeRegistry.init();
    }
}
