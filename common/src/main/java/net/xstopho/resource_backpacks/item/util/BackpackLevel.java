package net.xstopho.resource_backpacks.item.util;

import static net.xstopho.resource_backpacks.config.BackpackConfig.*;

public enum BackpackLevel {

    LEATHER(LEATHER_ROWS.get(), LEATHER_COLUMNS.get()),
    COPPER(COPPER_ROWS.get(), COPPER_COLUMNS.get()),
    GOLD(GOLD_ROWS.get(), GOLD_COLUMNS.get()),
    IRON(IRON_ROWS.get(), IRON_COLUMNS.get()),
    DIAMOND(DIAMOND_ROWS.get(), DIAMOND_COLUMNS.get()),
    NETHERITE(NETHERITE_ROWS.get(), NETHERITE_COLUMNS.get());

    final int rows, columns;

    BackpackLevel(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int getColumns() {
        return Math.max(9, Math.min(50, columns));
    }

    public int getRows() {
        return Math.max(1, Math.min(25, rows));
    }

    public String getName() {
        return this.toString().toLowerCase();
    }
}
