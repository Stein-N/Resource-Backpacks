package net.xstopho.resource_backpacks;

import net.neoforged.fml.common.Mod;
import net.xstopho.resource_backpacks.registries.ItemRegistry;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public ResourceBackpacks() {
        ItemRegistry.init();
    }
}
