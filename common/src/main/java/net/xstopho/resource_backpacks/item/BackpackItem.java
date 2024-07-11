package net.xstopho.resource_backpacks.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.components.BackpackContainerContent;
import net.xstopho.resource_backpacks.config.BackpackConfig;
import net.xstopho.resource_backpacks.item.util.BackpackInventory;
import net.xstopho.resource_backpacks.item.util.BackpackLevel;
import net.xstopho.resource_backpacks.registries.DataComponentsRegistry;
import net.xstopho.resource_backpacks.rendering.container.BackpackContainer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BackpackItem extends Item implements Equipable {

    private final BackpackLevel level;

    public BackpackItem(Properties properties, BackpackLevel level) {
        super(properties.stacksTo(1).component(DataComponentsRegistry.BACKPACK_CONTAINER.get(), BackpackContainerContent.EMPTY));
        this.level = level;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (!player.level().isClientSide) {
            player.startUsingItem(usedHand);
            ItemStack stack = player.getItemInHand(usedHand);
            player.openMenu(getMenuProvider(stack));
        }
        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }

    public MenuProvider getMenuProvider(ItemStack stack) {
        return switch(getLevel()) {
            case LEATHER -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.leatherContainer(i, inventory, new BackpackInventory(stack, level.getRows() * level.getColumns())), stack.getHoverName());
            case COPPER -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.copperContainer(i, inventory, new BackpackInventory(stack, level.getRows() * level.getColumns())), stack.getHoverName());
            case GOLD -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.goldContainer(i, inventory, new BackpackInventory(stack, level.getRows() * level.getColumns())), stack.getHoverName());
            case IRON -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.ironContainer(i, inventory, new BackpackInventory(stack, level.getRows() * level.getColumns())), stack.getHoverName());
            case DIAMOND -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.diamondContainer(i, inventory, new BackpackInventory(stack, level.getRows() * level.getColumns())), stack.getHoverName());
            case NETHERITE -> new SimpleMenuProvider((i, inventory, player) -> BackpackContainer.netheriteContainer(i, inventory, new BackpackInventory(stack, level.getRows() * level.getColumns())), stack.getHoverName());
        };
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }

    public BackpackLevel getLevel() {
        return level;
    }

    @Override
    public @NotNull EquipmentSlot getEquipmentSlot() {
        if (noTrinketMod()) return EquipmentSlot.CHEST;
        return EquipmentSlot.MAINHAND;
    }

    private boolean noTrinketMod() {
        return !(BackpackConstants.ACCESSORIES || BackpackConstants.CURIOS ||
                BackpackConstants.TRINKETS);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
