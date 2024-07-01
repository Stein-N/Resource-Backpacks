package net.xstopho.resource_backpacks.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.xstopho.resource_backpacks.item.util.BackpackLevel;

public class BackpackItem extends Item implements Equipable {

    private final BackpackLevel level;

    public BackpackItem(Properties pProperties, BackpackLevel level) {
        super(pProperties.stacksTo(1));
        this.level = level;
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }

    public BackpackLevel getLevel() {
        return level;
    }
}
