package net.xstopho.resource_backpacks.item;

import net.minecraft.world.item.Item;

public class BackpackItem extends Item {
    public BackpackItem(Properties properties) {
        super(properties.stacksTo(1));
    }
}
