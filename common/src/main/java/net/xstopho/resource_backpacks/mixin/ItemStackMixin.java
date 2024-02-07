package net.xstopho.resource_backpacks.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.item.BackpackItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ItemStack) {
            ItemStack thisStack = (ItemStack) (Object) this;
            ItemStack chestStack = (ItemStack) obj;

            Item thisStackItem = thisStack.getItem();
            Item chestStackItem = chestStack.getItem();

            if (thisStackItem instanceof BackpackItem && chestStackItem instanceof BackpackItem) {
                return true;
            }
        }

        return super.equals(obj);
    }
}
