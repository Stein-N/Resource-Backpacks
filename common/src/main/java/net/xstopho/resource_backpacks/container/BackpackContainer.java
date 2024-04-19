package net.xstopho.resource_backpacks.container;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resource_backpacks.util.BackpackLevel;

public class BackpackContainer extends AbstractContainerMenu {

    private BackpackLevel backpackLevel;

    public BackpackContainer(int id) {
        super(MenuTypeRegistry.BACKPACK_MENU.get(), id);
    }

    public BackpackContainer(int i, Inventory inventory) {
        this(i);
    }

    public BackpackContainer(BackpackLevel backpackLevel, int i, Inventory inventory) {
        this(i, inventory);
        this.backpackLevel = backpackLevel;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }
}
