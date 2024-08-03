package net.xstopho.resource_backpacks.compat.accessories;

import com.mojang.blaze3d.vertex.PoseStack;
import io.wispforest.accessories.api.client.AccessoryRenderer;
import io.wispforest.accessories.api.client.SimpleAccessoryRenderer;
import io.wispforest.accessories.api.slot.SlotReference;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.rendering.BackpackRenderer;

public class BackpackAccessoryRenderer implements SimpleAccessoryRenderer {

    @Override
    public <M extends LivingEntity> void align(ItemStack itemStack, SlotReference slotReference, EntityModel<M> entityModel, PoseStack poseStack) {
        if (!(entityModel instanceof HumanoidModel<? extends LivingEntity> model)) return;

        AccessoryRenderer.translateToChest(poseStack, model, slotReference.entity());
    }

    @Override
    public <M extends LivingEntity> void render(ItemStack stack, SlotReference reference, PoseStack poseStack, EntityModel<M> model, MultiBufferSource multiBufferSource,
                                                int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        BackpackRenderer.render(stack, poseStack, multiBufferSource, light, reference.entity());
    }
}
