package net.xstopho.resource_backpacks.compat.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class BackpackCurioRenderer implements ICurioRenderer {
    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack itemStack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource multiBufferSource, int i, float v, float v1, float v2, float v3, float v4, float v5) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotationDegrees(180));
        poseStack.translate(0, -0.29, -0.25);

        if (slotContext.entity().isShiftKeyDown()) {
            poseStack.mulPose(Axis.XP.rotationDegrees(29));
            poseStack.translate(0, -0.1, -0.072);
        }
        Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemDisplayContext.FIXED, i, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, slotContext.entity().level(), 0);
        poseStack.popPose();
    }
}
