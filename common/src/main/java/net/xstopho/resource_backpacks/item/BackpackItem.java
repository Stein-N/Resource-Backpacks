package net.xstopho.resource_backpacks.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.xstopho.resource_backpacks.item.util.BackpackLevel;
import net.xstopho.resource_backpacks.rendering.container.BackpackContainer;

public class BackpackItem extends Item implements Equipable {

    private final BackpackLevel level;

    public BackpackItem(Properties pProperties, BackpackLevel level) {
        super(pProperties.stacksTo(1));
        this.level = level;
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
            case LEATHER -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.leatherContainer(i, inventory), stack.getHoverName());
            case COPPER -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.copperContainer(i, inventory), stack.getHoverName());
            case GOLD -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.goldContainer(i, inventory), stack.getHoverName());
            case IRON -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.ironContainer(i, inventory), stack.getHoverName());
            case DIAMOND -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.diamondContainer(i, inventory), stack.getHoverName());
            case NETHERITE -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.netheriteContainer(i, inventory), stack.getHoverName());
        };
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.CHEST;
    }

    public BackpackLevel getLevel() {
        return level;
    }
}
