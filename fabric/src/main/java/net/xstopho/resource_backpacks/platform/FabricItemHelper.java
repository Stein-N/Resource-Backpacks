package net.xstopho.resource_backpacks.platform;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.xstopho.resource_backpacks.item.BackpackBigItem;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.item.BackpackSmallItem;
import net.xstopho.resource_backpacks.item.BackpackTravelItem;
import net.xstopho.resource_backpacks.platform.service.ItemHelper;

public class FabricItemHelper implements ItemHelper {
    @Override
    public Item getSmallBackpack() {
        return new BackpackSmallItem(new FabricItemSettings().equipmentSlot(stack -> EquipmentSlot.CHEST));
    }

    @Override
    public Item getBigBackpack() {
        return new BackpackBigItem(new FabricItemSettings().equipmentSlot(stack -> EquipmentSlot.CHEST));
    }

    @Override
    public Item getTravelBackpack() {
        return new BackpackTravelItem(new FabricItemSettings().equipmentSlot(stack -> EquipmentSlot.CHEST));
    }
}
