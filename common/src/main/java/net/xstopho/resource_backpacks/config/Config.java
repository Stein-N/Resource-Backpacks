package net.xstopho.resource_backpacks.config;

import net.xstopho.simpleconfig.builder.SimpleConfigBuilder;

import java.util.function.Supplier;

public class Config {

    public static final SimpleConfigBuilder BUILDER = new SimpleConfigBuilder();

    public static final Supplier<Integer> BACKPACK_LEATHER_ROWS, BACKPACK_COPPER_ROWS, BACKPACK_GOLD_ROWS,
                                            BACKPACK_IRON_ROWS, BACKPACK_DIAMOND_ROWS, BACKPACK_NETHERITE_ROWS;

    static {
        BUILDER.comment("Define the amount of Rows a Backpack Inventory have.")
                .push("Backpack Inventory Settings");

        BACKPACK_LEATHER_ROWS = BUILDER.define("leather", 3);
        BACKPACK_COPPER_ROWS = BUILDER.define("copper", 4);
        BACKPACK_GOLD_ROWS = BUILDER.define("gold", 5);
        BACKPACK_IRON_ROWS = BUILDER.define("iron", 6);
        BACKPACK_DIAMOND_ROWS = BUILDER.define("diamond", 8);
        BACKPACK_NETHERITE_ROWS = BUILDER.define("netherite", 10);

        BUILDER.pop();
    }
}
