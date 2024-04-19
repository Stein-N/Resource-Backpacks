package net.xstopho.resource_backpacks.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.xstopho.resource_backpacks.container.BackpackContainer;

public class BackpackScreen extends AbstractContainerScreen<BackpackContainer> {
    public BackpackScreen(BackpackContainer abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {

    }
}
