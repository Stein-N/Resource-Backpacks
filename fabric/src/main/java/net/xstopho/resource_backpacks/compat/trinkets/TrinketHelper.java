package net.xstopho.resource_backpacks.compat.trinkets;

import dev.emi.trinkets.api.TrinketInventory;
import dev.emi.trinkets.api.TrinketsApi;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.registries.ItemRegistry;

import java.util.Map;

public class TrinketHelper {

    public static void initClient() {
        if (BackpackConstants.TRINKETS) {
            ItemRegistry.ITEMS.getEntries().forEach(itemRegistryObject -> {
                if (itemRegistryObject.get() instanceof BackpackItem) {
                    TrinketRendererRegistry.registerRenderer(itemRegistryObject.get(), BackpackTrinketRenderer::new);
                }
            });
        }
    }

    public static ItemStack getEquippedBackpack(ServerPlayer player) {
        if (BackpackConstants.TRINKETS) {
            Map<String, Map<String, TrinketInventory>> trinkets = TrinketsApi.getTrinketComponent(player).get().getInventory();
            Map<String, TrinketInventory> chestTrinkets = trinkets.get("chest");
            TrinketInventory trinketInventory = chestTrinkets.get("back");

            for (int i = 0; i < trinketInventory.getContainerSize(); i++) {
                if (trinketInventory.getItem(i).getItem() instanceof BackpackItem) {
                    return trinketInventory.getItem(i);
                }
            }
        }
        return ItemStack.EMPTY;
    }
}
