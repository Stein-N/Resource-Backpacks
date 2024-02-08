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

    private static final ItemHelper helper = Services.load(ItemHelper.class);

    public static final RegistryObject<Item> BACKPACK_SMALL = ITEMS.register("backpack_small", helper::getSmallBackpack);
    public static final RegistryObject<Item> BACKPACK_BIG = ITEMS.register("backpack_big", helper::getBigBackpack);
    public static final RegistryObject<Item> BACKPACK_TRAVEL = ITEMS.register("backpack_travel", helper::getTravelBackpack);

    public static void init() {}
}
