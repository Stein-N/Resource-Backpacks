package net.xstopho.resource_backpacks.item.util;

import net.minecraft.resources.ResourceLocation;
import net.xstopho.resource_backpacks.BackpackConstants;

public enum BackpackLevel {

    LEATHER(3, 11),     // 33 Slots
    COPPER(3, 12),      // 36 Slots
    GOLD(4, 12),        // 48 Slots
    IRON(5, 12),        // 60 Slots
    DIAMOND(7, 12),     // 84 Slots
    NETHERITE(8, 13);   // 104 Slots

    final int rows, columns;
    final ResourceLocation guiTexture;

    BackpackLevel(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.guiTexture = ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, "textures/gui/container/backpack_" + getName() + ".png");
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public ResourceLocation getGuiTexture() {
        return guiTexture;
    }

    public String getName() {
        return this.toString().toLowerCase();
    }
}
