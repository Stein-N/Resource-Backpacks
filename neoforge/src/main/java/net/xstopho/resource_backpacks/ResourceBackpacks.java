package net.xstopho.resource_backpacks;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.xstopho.resource_backpacks.registries.ItemRegistry;

@Mod(Constants.MOD_ID)
public class ResourceBackpacks {

    public ResourceBackpacks(IEventBus eventBus) {
        ItemRegistry.init();
    }
}