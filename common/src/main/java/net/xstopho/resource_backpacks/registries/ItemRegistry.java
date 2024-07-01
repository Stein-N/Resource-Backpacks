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

    public static final RegistryObject<Item> BACKPACK_LEATHER = register("backpack_leather", BackpackLevel.LEATHER);
    public static final RegistryObject<Item> BACKPACK_COPPER = register("backpack_copper", BackpackLevel.COPPER);
    public static final RegistryObject<Item> BACKPACK_GOLD = register("backpack_gold", BackpackLevel.GOLD);
    public static final RegistryObject<Item> BACKPACK_IRON = register("backpack_iron", BackpackLevel.IRON);
    public static final RegistryObject<Item> BACKPACK_DIAMOND = register("backpack_diamond", BackpackLevel.DIAMOND);
    public static final RegistryObject<Item> BACKPACK_NETHERITE = register("backpack_netherite", BackpackLevel.NETHERITE);

    private static RegistryObject<Item> register(String id, BackpackLevel level) {
        return ITEMS.register(id, () -> new BackpackItem(new Item.Properties(), level));
    }

    public static void init() {};
}
