package net.xstopho.resource_backpacks.config;

import net.xstopho.resource_config_api.builder.ConfigBuilder;

import java.util.function.Supplier;

public class BackpackConfig {

    public static final ConfigBuilder BUILDER = new ConfigBuilder();

    public static final Supplier<Boolean> ALLOW_CHESTSLOT, CHESTSLOT_KEYBIND, OPEN_BACKPACK_FROM_INVENTORY;
    public static final Supplier<Boolean> ENABLE_TRINKETS, ENABLE_CURIOS, ENABLE_ACCESSORIES;

    static {
        BUILDER.push("General");
        ALLOW_CHESTSLOT = BUILDER.comment("Enable the ability to equip the Backpack to your Chestslot.")
                .comment("When the Server you are playing on set this to false, your local setting is ignored!")
                .define("enable_chestslot", true);
        CHESTSLOT_KEYBIND = BUILDER.comment("Backpacks can be opened via a keybind when equipped in the chestslot.")
                .comment("default key is B, but can be changed in the Controls Settings.")
                .define("chestslot_keybind", true);
        OPEN_BACKPACK_FROM_INVENTORY = BUILDER.comment("The first Backpack that is found in your Inventory will be opened via the keybind.")
                .comment("Search Order -> Hotbar(left to right) -> Inventory(top left to bottom right)")
                .comment("This is Disabled by Default").define("open_backpack_from_inventory", false);
        BUILDER.pop();

        BUILDER.comment("These settings aren't implemented yet, so they have no effect on your gameplay!")
                .push("Compatibility");
        ENABLE_TRINKETS = BUILDER.comment("Enables the Compatibility with the Trinkets API.")
                .define("trinkets", false);
        ENABLE_CURIOS = BUILDER.comment("Enables the compatibility with the Curios API")
                .define("curios", false);
        ENABLE_ACCESSORIES = BUILDER.comment("Enables the compatibility with the Accessories API")
                .define("accessories", false);
        BUILDER.pop();
    }
}
