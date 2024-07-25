package net.xstopho.resource_backpacks.item.util;

import java.util.function.Supplier;

import static net.xstopho.resource_backpacks.config.BackpackConfig.*;

public enum BackpackLevel {

    LEATHER(LEATHER_ROWS, LEATHER_COLUMNS),
    COPPER(COPPER_ROWS, COPPER_COLUMNS),
    GOLD(GOLD_ROWS, GOLD_COLUMNS),
    IRON(IRON_ROWS, IRON_COLUMNS),
    DIAMOND(DIAMOND_ROWS, DIAMOND_COLUMNS),
    NETHERITE(NETHERITE_ROWS, NETHERITE_COLUMNS),
    ENDER(() -> 3, () -> 9);

    final Supplier<Integer> rows, columns;

    BackpackLevel(Supplier<Integer> rows_supplier, Supplier<Integer> columns_supplier) {
        this.rows = rows_supplier;
        this.columns = columns_supplier;
    }

    public int getColumns() {
        return Math.max(9, Math.min(50, columns.get()));
    }

    public int getRows() {
        return Math.max(1, Math.min(25, rows.get()));
    }


    public String getName() {
        return this.toString().toLowerCase();
    }
}
