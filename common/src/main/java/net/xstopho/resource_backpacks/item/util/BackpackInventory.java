package net.xstopho.resource_backpacks.item.util;

import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.xstopho.resource_backpacks.api.ImplementedInventory;

public class BackpackInventory implements ImplementedInventory {
    private final ItemStack stack;
    private final NonNullList<ItemStack> items;

    public BackpackInventory(ItemStack stack, int inventorySize) {
        this.items = NonNullList.withSize(inventorySize, ItemStack.EMPTY);
        this.stack = stack;
        ItemContainerContents container = stack.get(DataComponents.CONTAINER);
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
        this.stack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(items));
    }
}
