package net.xstopho.resource_backpacks.platform;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.item.BackpackBigItem;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.item.BackpackSmallItem;
import net.xstopho.resource_backpacks.item.BackpackTravelItem;
import net.xstopho.resource_backpacks.platform.service.ItemHelper;

public class ForgeItemHelper implements ItemHelper {
    @Override
    public Item getSmallBackpack() {
        return new BackpackSmallItem(new Item.Properties()) {
            @Override
            public EquipmentSlot getEquipmentSlot(ItemStack stack) {
                return EquipmentSlot.CHEST;
            }
        };
    }

    @Override
    public Item getBigBackpack() {
        return new BackpackBigItem(new Item.Properties()) {
            @Override
            public EquipmentSlot getEquipmentSlot(ItemStack stack) {
                return EquipmentSlot.CHEST;
            }
        };
    }

    @Override
    public Item getTravelBackpack() {
        return new BackpackTravelItem(new Item.Properties()) {
            @Override
            public EquipmentSlot getEquipmentSlot(ItemStack stack) {
                return EquipmentSlot.CHEST;
            }
        };
    }
}
