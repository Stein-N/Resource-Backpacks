package net.xstopho.resource_backpacks.platform.service;

import net.minecraft.world.item.Item;
import net.xstopho.resource_backpacks.util.BackpackLevelOLD;

public interface ItemHelper {
    Item getSmallBackpack(BackpackLevelOLD level);
    Item getBigBackpack(BackpackLevelOLD level);
    Item getTravelBackpack(BackpackLevelOLD level);
}
