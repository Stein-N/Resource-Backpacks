package net.xstopho.resource_backpacks.util;

import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class BackpackKeyMappings {

    private static final String KEYMAPPING_CATEGORY = "category.resource_backpacks.controls";

    public static final KeyMapping OPEN_BACKPACK = new KeyMapping("key.resource_backpacks.open_backpack", GLFW.GLFW_KEY_B, KEYMAPPING_CATEGORY);
}
