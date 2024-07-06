package net.xstopho.resource_backpacks.compat.shulkerboxtooltip;

import com.misterpemodder.shulkerboxtooltip.api.ShulkerBoxTooltipApi;
import com.misterpemodder.shulkerboxtooltip.api.provider.PreviewProviderRegistry;
import net.minecraft.resources.ResourceLocation;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.ItemRegistry;

public class BackpackTooltipPlugin implements ShulkerBoxTooltipApi {
    @Override
    public void registerProviders(PreviewProviderRegistry registry) {
        registry.register(location("backpack"), new BackpackPreviewProvider(), ItemRegistry.BACKPACK_LEATHER.get(), ItemRegistry.BACKPACK_COPPER.get(), ItemRegistry.BACKPACK_GOLD.get(),
                ItemRegistry.BACKPACK_IRON.get(), ItemRegistry.BACKPACK_DIAMOND.get(), ItemRegistry.BACKPACK_NETHERITE.get());
    }

    private ResourceLocation location(String id) {
        return ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, id);
    }
}
