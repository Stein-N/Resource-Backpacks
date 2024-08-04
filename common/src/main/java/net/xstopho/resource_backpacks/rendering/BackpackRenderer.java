package net.xstopho.resource_backpacks.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class BackpackRenderer {

    public static void render(ItemStack stack, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, LivingEntity player) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotationDegrees(180));
        poseStack.translate(0, -0.358, -0.20);

        if (player.isShiftKeyDown()) {
            poseStack.mulPose(Axis.XP.rotationDegrees(29));
            poseStack.translate(0, -0.115, -0.11);
        }

        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, player.level(), 0);
        poseStack.popPose();
    }
}
