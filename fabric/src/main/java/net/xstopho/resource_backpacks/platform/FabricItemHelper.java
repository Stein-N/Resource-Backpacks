package net.xstopho.resource_backpacks.platform;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.xstopho.resource_backpacks.item.BackpackBigItem;
import net.xstopho.resource_backpacks.item.BackpackSmallItem;
import net.xstopho.resource_backpacks.item.BackpackTravelItem;
import net.xstopho.resource_backpacks.platform.service.ItemHelper;
import net.xstopho.resource_backpacks.util.BackpackLevelOLD;

public class FabricItemHelper implements ItemHelper {
    @Override
    public Item getSmallBackpack(BackpackLevelOLD level) {
        return new BackpackSmallItem(level, props());
    }

    @Override
    public Item getBigBackpack(BackpackLevelOLD level) {
        return new BackpackBigItem(level, props());
    }

    @Override
    public Item getTravelBackpack(BackpackLevelOLD level) {
        return new BackpackTravelItem(level, props());
    }

    FabricItemSettings props() {
        return new FabricItemSettings().equipmentSlot(stack -> EquipmentSlot.CHEST);
    }
}
