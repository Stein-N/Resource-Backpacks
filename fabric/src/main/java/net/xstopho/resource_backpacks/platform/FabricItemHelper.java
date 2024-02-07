package net.xstopho.resource_backpacks.platform;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.platform.service.ItemHelper;

public class FabricItemHelper implements ItemHelper {
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
        return  new BackpackItem(new FabricItemSettings().maxCount(1).equipmentSlot(stack -> EquipmentSlot.CHEST));
    }
}
