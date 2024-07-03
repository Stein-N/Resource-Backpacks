package net.xstopho.resource_backpacks.rendering.container;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.item.util.BackpackLevel;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;

public class BackpackContainer extends AbstractContainerMenu {

    private final BackpackLevel level;
    private final Container backpackInventory;

    private BackpackContainer(MenuType<?> menuType, int synId, Inventory playerInventory, BackpackLevel level) {
        this(menuType, synId, playerInventory, new SimpleContainer(level.getColumns() * level.getRows()), level);
    }

    public static BackpackContainer leatherContainer(int syncId, Inventory inventory) {
        return new BackpackContainer(MenuTypeRegistry.LEATHER_BACKPACK_MENU.get(), syncId, inventory, BackpackLevel.LEATHER);
    }

    public static BackpackContainer leatherContainer(int syncId, Inventory inventory, Container backpackInventory) {
        return new BackpackContainer(MenuTypeRegistry.LEATHER_BACKPACK_MENU.get(), syncId, inventory, backpackInventory, BackpackLevel.LEATHER);
    }

    public static BackpackContainer copperContainer(int syncId, Inventory inventory) {
        return new BackpackContainer(MenuTypeRegistry.COPPER_BACKPACK_MENU.get(), syncId, inventory, BackpackLevel.COPPER);
    }

    public static BackpackContainer copperContainer(int syncId, Inventory inventory, Container backpackInventory) {
        return new BackpackContainer(MenuTypeRegistry.COPPER_BACKPACK_MENU.get(), syncId, inventory, backpackInventory, BackpackLevel.COPPER);
    }

    public static BackpackContainer goldContainer(int syncId, Inventory inventory) {
        return new BackpackContainer(MenuTypeRegistry.GOLD_BACKPACK_MENU.get(), syncId, inventory, BackpackLevel.GOLD);
    }

    public static BackpackContainer goldContainer(int syncId, Inventory inventory, Container backpackInventory) {
        return new BackpackContainer(MenuTypeRegistry.GOLD_BACKPACK_MENU.get(), syncId, inventory, backpackInventory, BackpackLevel.GOLD);
    }

    public static BackpackContainer ironContainer(int syncId, Inventory inventory) {
        return new BackpackContainer(MenuTypeRegistry.IRON_BACKPACK_MENU.get(), syncId, inventory, BackpackLevel.IRON);
    }

    public static BackpackContainer ironContainer(int syncId, Inventory inventory, Container backpackInventory) {
        return new BackpackContainer(MenuTypeRegistry.IRON_BACKPACK_MENU.get(), syncId, inventory, backpackInventory, BackpackLevel.IRON);
    }

    public static BackpackContainer diamondContainer(int syncId, Inventory inventory) {
        return new BackpackContainer(MenuTypeRegistry.DIAMOND_BACKPACK_MENU.get(), syncId, inventory, BackpackLevel.DIAMOND);
    }

    public static BackpackContainer diamondContainer(int syncId, Inventory inventory, Container backpackInventory) {
        return new BackpackContainer(MenuTypeRegistry.DIAMOND_BACKPACK_MENU.get(), syncId, inventory, backpackInventory, BackpackLevel.DIAMOND);
    }

    public static BackpackContainer netheriteContainer(int syncId, Inventory inventory) {
        return new BackpackContainer(MenuTypeRegistry.NETHERITE_BACKPACK_MENU.get(), syncId, inventory, BackpackLevel.NETHERITE);
    }

    public static BackpackContainer netheriteContainer(int syncId, Inventory inventory, Container backpackInventory) {
        return new BackpackContainer(MenuTypeRegistry.NETHERITE_BACKPACK_MENU.get(), syncId, inventory, backpackInventory, BackpackLevel.NETHERITE);
    }

    public BackpackContainer(MenuType<?> menuType, int syncId, Inventory playerInventory, Container backpackInventory, BackpackLevel level) {
        super(menuType, syncId);
        checkContainerSize(backpackInventory, level.getRows() * level.getColumns());

        this.backpackInventory = backpackInventory;
        this.level = level;

        backpackInventory.startOpen(playerInventory.player);

        addBackpackSlots();
        addInventorySlots(playerInventory);
        addHotbarSlots(playerInventory);
    }

    private void addBackpackSlots() {
        int index = 0;
        for (int i = 0; i < level.getRows(); i++) {
            for (int y = 0; y < level.getColumns(); y++) {
                this.addSlot(new BackpackSlot(backpackInventory, index, 7 + y * 18, 18 + i * 18));
                index++;
            }
        }
    }

    // Slot Index 9-35
    private void addInventorySlots(Container inventory) {
        for (int i = 0; i < 3; i++) {
            for (int y = 0; y < 9; y++) {
                int index = y + i * 9 + 9;
                this.addSlot(new BackpackSlot(inventory, index, xPos() + y * 18, yPos(25) + i * 18));
            }
        }
    }

    // Slot index 0-8
    private void addHotbarSlots(Container inventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new BackpackSlot(inventory, i, xPos() + i * 18, yPos(83)));
        }
    }

    private int yPos(int y) {
        return y + level.getRows() * 18;
    }

    private int xPos() {
        if (level.getColumns() == 11) return 25;
        if (level.getColumns() == 12) return 34;
        return 43;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack returnStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            returnStack = stack.copy();
            if (index < this.backpackInventory.getContainerSize()) {
                if (!this.moveItemStackTo(stack, this.backpackInventory.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack, 0, this.backpackInventory.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) slot.setByPlayer(ItemStack.EMPTY);
            else slot.setChanged();

        }
        return returnStack;
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

    public static class BackpackSlot extends Slot {
        public BackpackSlot(Container inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public boolean mayPickup(Player playerEntity) {
            return this.canMoveStack(this.getItem());
        }

        public boolean mayPlace(ItemStack stack) {
            return this.canMoveStack(stack);
        }

        public boolean canMoveStack(ItemStack stack) {
            return stack.getItem().canFitInsideContainerItems();
        }
    }
}