package net.xstopho.resource_backpacks.util;

import net.minecraft.resources.ResourceLocation;
import net.xstopho.resource_backpacks.Constants;

public enum BackpackLevel {

    LEATHER(36, "leather"),
    COPPER(48, "copper"),
    GOLD(60, "gold"),
    IRON(72, "iron"),
    DIAMOND(84, "diamond"),
    NETHERITE(120, "netherite");

    final int slots;
    final ResourceLocation texture;

    BackpackLevel(int slots, String texture) {
        this.slots = slots;
        this.texture = new ResourceLocation(Constants.MOD_ID, "gui/backpack_" + texture);
    }

    public int getSlots() {
        return slots;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    @Override
    public String toString() {
        return this.toString().toLowerCase();
    }
}
