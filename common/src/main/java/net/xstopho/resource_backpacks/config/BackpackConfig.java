package net.xstopho.resource_backpacks.config;

import net.xstopho.resource_config_api.builder.ConfigBuilder;

import java.util.function.Supplier;

public class BackpackConfig {

    public static final ConfigBuilder BUILDER = new ConfigBuilder();

    public static final Supplier<Integer> LEATHER_ROWS, LEATHER_COLUMNS, COPPER_ROWS, COPPER_COLUMNS, GOLD_ROWS, GOLD_COLUMNS,
                                            IRON_ROWS, IRON_COLUMNS, DIAMOND_ROWS, DIAMOND_COLUMNS, NETHERITE_ROWS, NETHERITE_COLUMNS;
    public static final Supplier<Boolean> ALLOW_CHESTSLOT, ENABLE_BACKPACK_KEYBIND, OPEN_BACKPACK_FROM_INVENTORY;
    public static final Supplier<Boolean> ENABLE_TRINKETS, ENABLE_CURIOS, ENABLE_ACCESSORIES;

    static {
        BUILDER.push("General");
        ALLOW_CHESTSLOT = BUILDER.comment("Enable the ability to equip the Backpack to your Chestslot.")
                .comment("When the Server you are playing on set this to false, your local setting is ignored!")
                .define("enable_chestslot", true);
        ENABLE_BACKPACK_KEYBIND = BUILDER.comment("Backpacks can be opened via a keybind.")
                .comment("When set to false the allow_chestslot setting gets overwritten.")
                .comment("default key is B, but can be changed in the Controls Settings.")
                .define("enable_backpack_keybind", true);
        OPEN_BACKPACK_FROM_INVENTORY = BUILDER.comment("The first Backpack that is found in your Inventory will be opened via the keybind.")
                .comment("Search Order -> Inventory -> Hotbar")
                .define("open_backpack_from_inventory", false);
        BUILDER.pop();

        BUILDER.comment("Change how big the backpack inventory is.")
                .comment("Rows: 1 up to 25")
                .comment("Columns: 10 up to 50")
                .push("Leather Backpack");
        LEATHER_ROWS = BUILDER.define("rows", 3);
        LEATHER_COLUMNS = BUILDER.define("columns", 9);

        BUILDER.pop().push("Copper Backpack");
        COPPER_ROWS = BUILDER.define("rows", 3);
        COPPER_COLUMNS = BUILDER.define("columns", 10);

        BUILDER.pop().push("Gold Backpack");
        GOLD_ROWS = BUILDER.define("rows", 4);
        GOLD_COLUMNS = BUILDER.define("columns", 11);

        BUILDER.pop().push("Iron Backpack");
        IRON_ROWS = BUILDER.define("rows", 5);
        IRON_COLUMNS = BUILDER.define("columns", 12);

        BUILDER.pop().push("Diamond Backpack");
        DIAMOND_ROWS = BUILDER.define("rows", 6);
        DIAMOND_COLUMNS = BUILDER.define("columns", 12);

        BUILDER.pop().push("Netherite Backpack");
        NETHERITE_ROWS = BUILDER.define("rows", 7);
        NETHERITE_COLUMNS = BUILDER.define("columns", 13);
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
