package net.xstopho.resource_backpacks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.xstopho.resource_backpacks.config.Config;
import net.xstopho.resource_backpacks.registries.ItemRegistry;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.simpleconfig.api.SimpleConfigRegistry;

public class ResourceBackpacks implements ModInitializer {

    @Override
    public void onInitialize() {
        SimpleConfigRegistry.INSTANCE.register(Constants.MOD_ID, Config.BUILDER);

        MenuTypeRegistry.init();
        ItemRegistry.init();
    }
}
