package net.xstopho.resource_backpacks.compat.accessories;

import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.AccessoriesContainer;
import io.wispforest.accessories.api.client.AccessoriesRendererRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.registries.ItemRegistry;
import net.xstopho.resourcelibrary.service.CoreServices;

public class AccessoriesHelper {
    public static void initClient() {
        if (CoreServices.isModLoaded("accessories")) {
            AccessoriesRendererRegistry.registerRenderer(ItemRegistry.BACKPACK_LEATHER.get(), BackpackAccessoryRenderer::new);
            AccessoriesRendererRegistry.registerRenderer(ItemRegistry.BACKPACK_COPPER.get(), BackpackAccessoryRenderer::new);
            AccessoriesRendererRegistry.registerRenderer(ItemRegistry.BACKPACK_GOLD.get(), BackpackAccessoryRenderer::new);
            AccessoriesRendererRegistry.registerRenderer(ItemRegistry.BACKPACK_IRON.get(), BackpackAccessoryRenderer::new);
            AccessoriesRendererRegistry.registerRenderer(ItemRegistry.BACKPACK_DIAMOND.get(), BackpackAccessoryRenderer::new);
            AccessoriesRendererRegistry.registerRenderer(ItemRegistry.BACKPACK_NETHERITE.get(), BackpackAccessoryRenderer::new);
        }
    }

    public static ItemStack getEquippedBackpack(ServerPlayer player) {
        AccessoriesContainer container = AccessoriesCapability.get(player).getContainers().get("back");
        NonNullList<ItemStack> accessories = container.getAccessories().getItems();

        for (ItemStack stack : accessories) {
            if (stack.getItem() instanceof BackpackItem) return stack;
        }

        return ItemStack.EMPTY;
    }
}
