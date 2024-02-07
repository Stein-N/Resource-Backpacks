package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.xstopho.resource_backpacks.Constants;
import net.xstopho.resource_backpacks.platform.Services;
import net.xstopho.resource_backpacks.platform.service.ItemHelper;
import net.xstopho.stophoslib.registration.RegistryObject;
import net.xstopho.stophoslib.registration.RegistryProvider;

public class ItemRegistry {

    public static final RegistryProvider<Item> ITEMS = RegistryProvider.get(Registries.ITEM, Constants.MOD_ID);

    public static final RegistryObject<Item> BACKPACK = ITEMS.register("backpack", ItemRegistry::getBackpackItem);

    private static Item getBackpackItem() {
        return Services.load(ItemHelper.class).getBackpack();
    }

    public static void init() {}
}
