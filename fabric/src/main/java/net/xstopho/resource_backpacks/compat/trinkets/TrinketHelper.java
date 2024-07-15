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
            TrinketRendererRegistry.registerRenderer(ItemRegistry.BACKPACK_LEATHER.get(), new BackpackTrinketRenderer());
            TrinketRendererRegistry.registerRenderer(ItemRegistry.BACKPACK_COPPER.get(), new BackpackTrinketRenderer());
            TrinketRendererRegistry.registerRenderer(ItemRegistry.BACKPACK_GOLD.get(), new BackpackTrinketRenderer());
            TrinketRendererRegistry.registerRenderer(ItemRegistry.BACKPACK_IRON.get(), new BackpackTrinketRenderer());
            TrinketRendererRegistry.registerRenderer(ItemRegistry.BACKPACK_DIAMOND.get(), new BackpackTrinketRenderer());
            TrinketRendererRegistry.registerRenderer(ItemRegistry.BACKPACK_NETHERITE.get(), new BackpackTrinketRenderer());
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
