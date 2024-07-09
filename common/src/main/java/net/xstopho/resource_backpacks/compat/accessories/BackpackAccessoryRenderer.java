package net.xstopho.resource_backpacks.compat.accessories;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.wispforest.accessories.api.client.AccessoryRenderer;
import io.wispforest.accessories.api.client.SimpleAccessoryRenderer;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class BackpackAccessoryRenderer implements SimpleAccessoryRenderer {

    @Override
    public <M extends LivingEntity> void align(ItemStack itemStack, SlotReference slotReference, EntityModel<M> entityModel, PoseStack poseStack) {
        if (!(entityModel instanceof HumanoidModel<? extends LivingEntity> model)) return;

        AccessoryRenderer.translateToChest(poseStack, model, slotReference.entity());
    }

    @Override
    public <M extends LivingEntity> void render(ItemStack stack, SlotReference reference, PoseStack matrices, EntityModel<M> model, MultiBufferSource multiBufferSource, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        matrices.pushPose();
        matrices.mulPose(Axis.XP.rotationDegrees(180));
        matrices.translate(0, -0.29, -0.25);

        if (reference.entity().isShiftKeyDown()) {
            matrices.mulPose(Axis.XP.rotationDegrees(29));
            matrices.translate(0, -0.1, -0.072);
        }
        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, matrices, multiBufferSource, reference.entity().level(), 0);
        matrices.popPose();
    }
}
