package net.xstopho.resource_backpacks.util;

public record BackpackLevelOLD(String id, boolean isFireResistant) {

    public static final BackpackLevelOLD LEATHER = new BackpackLevelOLD("leather", false);
    public static final BackpackLevelOLD COPPER = new BackpackLevelOLD("copper", false);
    public static final BackpackLevelOLD GOLD = new BackpackLevelOLD("gold", false);
    public static final BackpackLevelOLD IRON = new BackpackLevelOLD("iron", false);
    public static final BackpackLevelOLD DIAMOND = new BackpackLevelOLD("diamond", false);
    public static final BackpackLevelOLD NETHERITE = new BackpackLevelOLD("netherite", true);

}
