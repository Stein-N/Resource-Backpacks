package net.xstopho.resource_backpacks.platform;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.item.BackpackBigItem;
import net.xstopho.resource_backpacks.item.BackpackSmallItem;
import net.xstopho.resource_backpacks.item.BackpackTravelItem;
import net.xstopho.resource_backpacks.platform.service.ItemHelper;
import net.xstopho.resource_backpacks.util.BackpackLevelOLD;

public class NeoForgeItemHelper implements ItemHelper {
    @Override
    public Item getSmallBackpack(BackpackLevelOLD level) {
        return new BackpackSmallItem(level, new Item.Properties()) {
            @Override
            public EquipmentSlot getEquipmentSlot(ItemStack stack) {
                return EquipmentSlot.CHEST;
            }
        };
    }

    @Override
    public Item getBigBackpack(BackpackLevelOLD level) {
        return new BackpackBigItem(level, new Item.Properties()) {
            @Override
            public EquipmentSlot getEquipmentSlot(ItemStack stack) {
                return EquipmentSlot.CHEST;
            }
        };
    }

    @Override
    public Item getTravelBackpack(BackpackLevelOLD level) {
        return new BackpackTravelItem(level, new Item.Properties()) {
            @Override
            public EquipmentSlot getEquipmentSlot(ItemStack stack) {
                return EquipmentSlot.CHEST;
            }
        };
    }
}
