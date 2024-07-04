package net.xstopho.resource_backpacks.item.util;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.api.ImplementedInventory;
import net.xstopho.resource_backpacks.components.BackpackContainerContent;
import net.xstopho.resource_backpacks.registries.DataComponentsRegistry;

public class BackpackInventory implements ImplementedInventory {
    private final ItemStack stack;
    private final NonNullList<ItemStack> items;

    public BackpackInventory(ItemStack stack, int inventorySize) {
        this.items = NonNullList.withSize(inventorySize, ItemStack.EMPTY);
        this.stack = stack;
        BackpackContainerContent container = stack.get(DataComponentsRegistry.BACKPACK_CONTAINER.get());
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
        this.stack.set(DataComponentsRegistry.BACKPACK_CONTAINER.get(), BackpackContainerContent.fromItems(items));
    }
}
