package net.xstopho.resource_backpacks.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.xstopho.resource_backpacks.item.util.BackpackInventory;
import net.xstopho.resource_backpacks.item.util.BackpackLevel;
import net.xstopho.resource_backpacks.rendering.container.BackpackContainer;

public class BackpackItem extends Item {

    private final BackpackLevel level;
    private final int rows, columns;

    public BackpackItem(Properties pProperties, BackpackLevel level) {
        super(pProperties.stacksTo(1));
        this.level = level;
        this.rows = level.getRows();
        this.columns = level.getColumns();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pPlayer.level().isClientSide) {
            return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
        }
        else {
            pPlayer.startUsingItem(pUsedHand);
            ItemStack stack = pPlayer.getItemInHand(pUsedHand);
            pPlayer.openMenu(getMenuProvider(stack));
            return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
        }
    }

    private MenuProvider getMenuProvider(ItemStack stack) {
        return switch(getLevel()) {
            case LEATHER -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.leatherContainer(i, inventory, new BackpackInventory(stack, this.rows * this.columns)), stack.getHoverName());
            case COPPER -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.copperContainer(i, inventory, new BackpackInventory(stack, this.rows * this.columns)), stack.getHoverName());
            case GOLD -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.goldContainer(i, inventory, new BackpackInventory(stack, this.rows * this.columns)), stack.getHoverName());
            case IRON -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.ironContainer(i, inventory, new BackpackInventory(stack, this.rows * this.columns)), stack.getHoverName());
            case DIAMOND -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.diamondContainer(i, inventory, new BackpackInventory(stack, this.rows * this.columns)), stack.getHoverName());
            case NETHERITE -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.netheriteContainer(i, inventory, new BackpackInventory(stack, this.rows * this.columns)), stack.getHoverName());
        };
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }

    public BackpackLevel getLevel() {
        return level;
    }
}
