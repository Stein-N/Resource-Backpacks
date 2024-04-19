package net.xstopho.resource_backpacks.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.xstopho.resource_backpacks.container.BackpackContainer;
import net.xstopho.resource_backpacks.util.BackpackLevel;
import net.xstopho.resource_backpacks.util.BackpackLevelOLD;

public abstract class BackpackItem extends Item {

    private final BackpackLevel backpackLevel;

    public BackpackItem(BackpackLevel backpackLevel, Properties properties) {
        super(properties.stacksTo(1));
        this.backpackLevel = backpackLevel;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        player.openMenu(new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("Test");
            }

            @Override
            public AbstractContainerMenu createMenu(int syncId, Inventory inventory, Player player) {
                return new BackpackContainer(backpackLevel, syncId, inventory);
            }
        });

        return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
    }
}
