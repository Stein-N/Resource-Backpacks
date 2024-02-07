package net.xstopho.resource_backpacks.platform;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.platform.service.ItemHelper;

public class ForgeItemHelper implements ItemHelper {
    @Override
    public Item getSmallBackpack() {
        return null;
    }

    @Override
    public Item getBigBackpack() {
        return null;
    }

    @Override
    public Item getTravelBackpack() {
        return null;
    }

    @Override
    public Item getBackpack() {
        return new BackpackItem(new Item.Properties()) {
            @Override
            public EquipmentSlot getEquipmentSlot(ItemStack stack) {
                return EquipmentSlot.CHEST;
            }
        };
    }
}
