package net.xstopho.resource_backpacks.rendering.container;

import com.google.common.collect.Sets;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.xstopho.resource_backpacks.item.util.BackpackLevel;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;

import java.util.Set;

public class BackpackContainer extends AbstractContainerMenu {

    private BackpackLevel level;
    private Container backpackInventory;
    public static final Set<Item> SHULKER_BOXES = Sets.newHashSet(Items.SHULKER_BOX, Items.BLACK_SHULKER_BOX, Items.BLUE_SHULKER_BOX,
            Items.BROWN_SHULKER_BOX, Items.CYAN_SHULKER_BOX, Items.GRAY_SHULKER_BOX, Items.GREEN_SHULKER_BOX, Items.LIGHT_BLUE_SHULKER_BOX,
            Items.LIGHT_GRAY_SHULKER_BOX, Items.LIME_SHULKER_BOX, Items.MAGENTA_SHULKER_BOX, Items.ORANGE_SHULKER_BOX, Items.PINK_SHULKER_BOX,
            Items.RED_SHULKER_BOX, Items.WHITE_SHULKER_BOX, Items.YELLOW_SHULKER_BOX, Items.PURPLE_SHULKER_BOX);

    private BackpackContainer(MenuType<?> menuType, int synId, Inventory playerInventory, BackpackLevel level) {
        this(menuType, synId, playerInventory, new SimpleContainer(level.getColumns() * level.getRows()), level);
    }

    public static BackpackContainer leatherContainer(int syncId, Inventory inventory) {
        return new BackpackContainer(MenuTypeRegistry.LEATHER_BACKPACK_MENU.get(), syncId, inventory, BackpackLevel.LEATHER);
    }

    public static BackpackContainer copperContainer(int syncId, Inventory inventory) {
        return new BackpackContainer(MenuTypeRegistry.COPPER_BACKPACK_MENU.get(), syncId, inventory, BackpackLevel.COPPER);
    }

    public static BackpackContainer goldContainer(int syncId, Inventory inventory) {
        return new BackpackContainer(MenuTypeRegistry.GOLD_BACKPACK_MENU.get(), syncId, inventory, BackpackLevel.GOLD);
    }

    public static BackpackContainer ironContainer(int syncId, Inventory inventory) {
        return new BackpackContainer(MenuTypeRegistry.IRON_BACKPACK_MENU.get(), syncId, inventory, BackpackLevel.IRON);
    }

    public static BackpackContainer diamondContainer(int syncId, Inventory inventory) {
        return new BackpackContainer(MenuTypeRegistry.DIAMOND_BACKPACK_MENU.get(), syncId, inventory, BackpackLevel.DIAMOND);
    }

    public static BackpackContainer netheriteContainer(int syncId, Inventory inventory) {
        return new BackpackContainer(MenuTypeRegistry.NETHERITE_BACKPACK_MENU.get(), syncId, inventory, BackpackLevel.NETHERITE);
    }

    public BackpackContainer(MenuType<?> menuType, int syncId, Inventory playerInventory, Container backpackInventory, BackpackLevel level) {
        super(menuType, syncId);
        checkContainerSize(backpackInventory, level.getRows() * level.getColumns());

        this.backpackInventory = backpackInventory;
        this.level = level;

        backpackInventory.startOpen(playerInventory.player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.backpackInventory.stillValid(player);
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        this.backpackInventory.stopOpen(pPlayer);
    }

    public BackpackLevel getLevel() {
        return level;
    }
}