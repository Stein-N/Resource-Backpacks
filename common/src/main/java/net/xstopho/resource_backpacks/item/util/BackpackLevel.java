package net.xstopho.resource_backpacks.item.util;

import net.minecraft.resources.ResourceLocation;
import net.xstopho.resource_backpacks.BackpackConstants;

public enum BackpackLevel {

    LEATHER(3, 11),
    COPPER(3, 12),
    GOLD(4, 12),
    IRON(5, 12),
    DIAMOND(7, 12),
    NETHERITE(10, 12);

    final int rows, colums;
    final ResourceLocation guiTexture;

    BackpackLevel(int rows, int colums) {
        this.rows = rows;
        this.colums = colums;
        this.guiTexture = ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, "gui/backpack_" + this.toString().toLowerCase());
    }

    public int getColums() {
        return colums;
    }

    public int getRows() {
        return rows;
    }

    public ResourceLocation getGuiTexture() {
        return guiTexture;
    }
}
