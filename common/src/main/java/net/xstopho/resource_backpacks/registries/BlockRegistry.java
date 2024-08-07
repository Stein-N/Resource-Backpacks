package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.block.BackpackBlock;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.item.util.BackpackLevel;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

public class BlockRegistry {

    private static final RegistryProvider<Block> BLOCKS = RegistryProvider.get(BuiltInRegistries.BLOCK, BackpackConstants.MOD_ID);
    private static final RegistryProvider<Item> ITEMS = RegistryProvider.get(BuiltInRegistries.ITEM, BackpackConstants.MOD_ID);

    public static final RegistryObject<Block> BACKPACK_TEST = register("backpack_test");

    private static RegistryObject<Block> register(String id) {
        //Item.Properties properties = level.equals(BackpackLevel.NETHERITE) ? new Item.Properties().fireResistant() : new Item.Properties();
        RegistryObject<Block> toReturn = BLOCKS.register(id, () -> new BackpackBlock(BackpackLevel.LEATHER, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));
        ITEMS.register(id, () -> new BackpackItem(new Item.Properties(), toReturn.get(), BackpackLevel.LEATHER));
        return toReturn;
    }

    public static void init() {}
}
