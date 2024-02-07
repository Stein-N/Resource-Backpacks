package net.xstopho.resource_backpacks.features;

import com.mojang.authlib.minecraft.client.MinecraftClient;
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
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.item.BackpackItem;

public class BackpackFeature extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public BackpackFeature(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> context) {
        super(context);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, AbstractClientPlayer player,
                       float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {

        ItemStack chestSlot = player.getItemBySlot(EquipmentSlot.CHEST);

        if (chestSlot.getItem() instanceof BackpackItem) {
            poseStack.pushPose();

            poseStack.mulPose(Axis.XP.rotationDegrees(180));
            poseStack.translate(0, -0.29f, -0.25f);
        }

        if (player.isShiftKeyDown()) {
            poseStack.mulPose(Axis.XP.rotationDegrees(30));
            poseStack.translate(0,-0.1, 0);
        }

        Minecraft.getInstance().getItemRenderer().renderStatic(chestSlot, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, player.clientLevel, 0);
        poseStack.popPose();
    }

}
