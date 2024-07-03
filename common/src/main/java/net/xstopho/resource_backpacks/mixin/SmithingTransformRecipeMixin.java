package net.xstopho.resource_backpacks.mixin;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.xstopho.resource_backpacks.item.BackpackItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.annotation.Inherited;

@Mixin(SmithingTransformRecipe.class)
public abstract class SmithingTransformRecipeMixin {

    @Shadow
    public abstract ItemStack getResultItem(HolderLookup.Provider registries);

    @Inject(method = "assemble", at = @At("HEAD"), cancellable = true)
    private void onAssemble(SmithingRecipeInput input, HolderLookup.Provider registries, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack oldBackpack = input.base();

        if (oldBackpack.getItem() instanceof BackpackItem) {
            ItemStack newBackpack = this.getResultItem(registries).copy();

            if (newBackpack.getItem() instanceof BackpackItem) {
                ItemContainerContents container = oldBackpack.get(DataComponents.CONTAINER);
                newBackpack.set(DataComponents.CONTAINER, container);
                cir.setReturnValue(newBackpack);
            }
        }
    }
}
