package net.xstopho.resource_backpacks.config;

import net.xstopho.resource_config_api.builder.ConfigBuilder;

import java.util.function.Supplier;

public class BackpackConfig {

    public static final ConfigBuilder BUILDER = new ConfigBuilder();

    public static Supplier<Integer> LEATHER_ROWS, LEATHER_COLUMNS, COPPER_ROWS, COPPER_COLUMNS, GOLD_ROWS, GOLD_COLUMNS,
                                    IRON_ROWS, IRON_COLUMNS, DIAMOND_ROWS, DIAMOND_COLUMNS, NETHERITE_ROWS, NETHERITE_COLUMNS;

    static {
        BUILDER.comment("Change how big the backpack inventory is.")
                .comment("Rows: 1 up to 25")
                .comment("Columns: 9 up to 50")
                .comment("max slots: 1250")
                .comment("Local Settings get overwritten with the server settings.")
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
    }
}
