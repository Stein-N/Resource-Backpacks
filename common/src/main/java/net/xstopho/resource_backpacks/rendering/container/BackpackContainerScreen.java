package net.xstopho.resource_backpacks.rendering.container;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;

public class BackpackContainerScreen extends AbstractContainerScreen<BackpackContainer> {

    private final ResourceLocation texture;
    private final int rows, columns;

    public BackpackContainerScreen(BackpackContainer menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.texture = menu.getLevel().getGuiTexture();
        this.rows = menu.getLevel().getRows();
        this.columns = menu.getLevel().getColumns();

        this.imageWidth = getWidth();
        this.imageHeight = getHeight();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, this.texture);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(this.texture, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
    }

    private int getWidth() {
        return 12 + (this.columns * 18);
    }

    private int getHeight() {
        return 107 + (this.rows * 18);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (KeyMappingRegistry.OPEN_BACKPACK.matches(keyCode, scanCode)) this.onClose();

        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
