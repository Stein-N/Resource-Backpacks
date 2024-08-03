package net.xstopho.resource_backpacks.compat.accessories;

import io.wispforest.accessories.api.AccessoriesCapability;
import io.wispforest.accessories.api.AccessoriesContainer;
import io.wispforest.accessories.api.client.AccessoriesRendererRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.registries.ItemRegistry;

public class AccessoriesHelper {
    public static void initClient() {
        if (BackpackConstants.ACCESSORIES) {
            ItemRegistry.ITEMS.getEntries().forEach(itemRegistryObject -> {
                if (itemRegistryObject.get() instanceof BackpackItem) {
                    AccessoriesRendererRegistry.registerRenderer(itemRegistryObject.get(), BackpackAccessoryRenderer::new);
                }
            });
        }
    }

    public static ItemStack getEquippedBackpack(ServerPlayer player) {
        if (BackpackConstants.ACCESSORIES) {
            AccessoriesContainer container = AccessoriesCapability.get(player).getContainers().get("back");
            NonNullList<ItemStack> accessories = container.getAccessories().getItems();

            for (ItemStack stack : accessories) {
                if (stack.getItem() instanceof BackpackItem) return stack;
            }
        }
        return ItemStack.EMPTY;
    }
}
