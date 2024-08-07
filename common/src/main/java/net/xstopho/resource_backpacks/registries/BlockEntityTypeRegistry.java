package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.block.entities.BackpackBlockEntity;
import net.xstopho.resourcelibrary.registration.RegistryObject;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

public class BlockEntityTypeRegistry {

    private static final RegistryProvider<BlockEntityType<?>> BLOCK_ENTITY_TYPES = RegistryProvider.get(BuiltInRegistries.BLOCK_ENTITY_TYPE, BackpackConstants.MOD_ID);

    public static final RegistryObject<BlockEntityType<BackpackBlockEntity>> BACKPACK_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("backpack_block_entity",
            () -> BlockEntityType.Builder.of(BackpackBlockEntity::new, BlockRegistry.BACKPACK_TEST.get()).build(null));

    public static void init() {}
}
