package net.xstopho.resource_backpacks.rendering.features;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.rendering.BackpackRenderer;

public class BackpackFeature extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    public BackpackFeature(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> context) {
        super(context);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, AbstractClientPlayer player,
                       float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {

        ItemStack chestSlot = player.getItemBySlot(EquipmentSlot.CHEST);
        Item chestItem = chestSlot.getItem();

        if (chestItem instanceof BackpackItem) {
            BackpackRenderer.render(chestSlot, poseStack, multiBufferSource, light, player);
        }
    }
}
