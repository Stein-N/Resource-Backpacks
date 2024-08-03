package net.xstopho.resource_backpacks.compat.curios;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.item.BackpackItem;
import net.xstopho.resource_backpacks.registries.ItemRegistry;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.util.List;

public class CurioHelper {

    public static void initClient() {
        if (BackpackConstants.CURIOS) {
            ItemRegistry.ITEMS.getEntries().forEach(itemRegistryObject -> {
                if (itemRegistryObject.get() instanceof BackpackItem) {
                    CuriosRendererRegistry.register(itemRegistryObject.get(), BackpackCurioRenderer::new);
                }
            });
        }
    }

    public static ItemStack getEquippedBackpack(ServerPlayer player) {
        if (BackpackConstants.CURIOS) {
            List<SlotResult> slotResults = CuriosApi.getCuriosInventory(player).get().findCurios("back");

            for (SlotResult result : slotResults.stream().toList()) {
                ItemStack backpack = result.stack();
                if (backpack.getItem() instanceof BackpackItem) return backpack;
            }
        }
        return ItemStack.EMPTY;
    }
}
