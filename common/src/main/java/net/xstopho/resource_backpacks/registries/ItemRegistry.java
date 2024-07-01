package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.item.util.BackpackLevel;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

public class ItemRegistry {

    private static final RegistryProvider<Item> ITEMS = RegistryProvider.get(BuiltInRegistries.ITEM, BackpackConstants.MOD_ID);



    private static RegistryObject<Item> register(String id, BackpackLevel level) {
        return ITEMS.register(id, () -> new BackpackItem(new Item.Properties(), level));
    }

    public static void init() {};
}
