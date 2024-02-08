package net.xstopho.resource_backpacks.features;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.xstopho.resource_backpacks.item.BackpackBigItem;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.item.BackpackSmallItem;
import net.xstopho.resource_backpacks.item.BackpackTravelItem;

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
            poseStack.pushPose();

            if (chestItem instanceof BackpackSmallItem) render(player, poseStack, -0.29, -0.25, -0.072);
            if (chestItem instanceof BackpackBigItem) render(player, poseStack, -0.29, -0.25, -0.072);
            if (chestItem instanceof BackpackTravelItem) render(player, poseStack, -0.4, -0.31, -0.138);


            popPose(chestSlot, light, poseStack, multiBufferSource, player.clientLevel);
        }
    }


    void render(AbstractClientPlayer player, PoseStack poseStack, double y, double z, double shiftZ) {
        poseStack.mulPose(Axis.XP.rotationDegrees(180));
        poseStack.translate(0, y, z);

        shiftDown(player, poseStack, shiftZ);
    }

    void shiftDown(AbstractClientPlayer player, PoseStack poseStack, double z) {
        if (player.isShiftKeyDown()) {
            poseStack.mulPose(Axis.XP.rotationDegrees(29));
            poseStack.translate(0, -0.1, z);
        }
    }

    void popPose(ItemStack stack, int light, PoseStack poseStack, MultiBufferSource source, Level level) {
        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, poseStack, source, level, 0);
        poseStack.popPose();
    }

}
