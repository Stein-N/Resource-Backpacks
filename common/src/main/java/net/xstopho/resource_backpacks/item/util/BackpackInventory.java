package net.xstopho.resource_backpacks.item.util;

import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.xstopho.resource_backpacks.api.ImplementedInventory;
import net.xstopho.resource_backpacks.components.BackpackContainerContent;
import net.xstopho.resource_backpacks.registries.DataComponentsRegistry;

public class BackpackInventory implements ImplementedInventory {
    private ItemStack stack;
    private BlockEntity entity;
    private final NonNullList<ItemStack> items;

    public BackpackInventory(ItemStack stack, int inventorySize) {
        this.items = NonNullList.withSize(inventorySize, ItemStack.EMPTY);
        this.stack = stack;
        BackpackContainerContent container = stack.get(DataComponentsRegistry.BACKPACK_CONTAINER.get());
        if (container != null) {
            container.copyInto(items);
        }
    }

    public BackpackInventory(BlockEntity entity, int inventorySize) {
        this.items = NonNullList.withSize(inventorySize, ItemStack.EMPTY);
        this.entity = entity;
        BackpackContainerContent container = entity.components().get(DataComponentsRegistry.BACKPACK_CONTAINER.get());
        if (container != null) {
            container.copyInto(items);
        }
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void setChanged() {
        if (stack != null) this.stack.set(DataComponentsRegistry.BACKPACK_CONTAINER.get(), BackpackContainerContent.fromItems(items));
        if (entity != null) this.entity.setComponents(DataComponentMap.builder().set(DataComponentsRegistry.BACKPACK_CONTAINER.get(), BackpackContainerContent.fromItems(items)).build());
    }
}
