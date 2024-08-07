package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.block.BackpackBlock;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.item.util.BackpackLevel;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

public class ItemRegistry {

    public static final RegistryProvider<Item> ITEMS = RegistryProvider.get(BuiltInRegistries.ITEM, BackpackConstants.MOD_ID);

    public static final RegistryObject<Item> BACKPACK_LEATHER = register(BackpackLevel.LEATHER);
    public static final RegistryObject<Item> BACKPACK_COPPER = register(BackpackLevel.COPPER);
    public static final RegistryObject<Item> BACKPACK_GOLD = register(BackpackLevel.GOLD);
    public static final RegistryObject<Item> BACKPACK_IRON = register(BackpackLevel.IRON);
    public static final RegistryObject<Item> BACKPACK_DIAMOND = register(BackpackLevel.DIAMOND);
    public static final RegistryObject<Item> BACKPACK_NETHERITE = register(BackpackLevel.NETHERITE);
    public static final RegistryObject<Item> BACKPACK_ENDER = register(BackpackLevel.ENDER);

    private static RegistryObject<Item> register(BackpackLevel level) {
        Item.Properties properties = level.equals(BackpackLevel.NETHERITE) ? new Item.Properties().fireResistant() : new Item.Properties();
        return ITEMS.register("backpack_" + level.getName(), () -> new BackpackItem(properties, new BackpackBlock(level, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)), level));
    }

    public static void init() {};
}
