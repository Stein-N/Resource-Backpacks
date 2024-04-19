package net.xstopho.resource_backpacks;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.fml.common.Mod;
import net.xstopho.resource_backpacks.registries.ItemRegistry;

@Mod(Constants.MOD_ID)
public class ResourceBackpacks {
    
    public ResourceBackpacks() {

        ItemRegistry.init();
    }
}