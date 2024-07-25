package net.xstopho.resource_backpacks;

import com.misterpemodder.shulkerboxtooltip.api.neoforge.ShulkerBoxTooltipPlugin;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.xstopho.resource_backpacks.compat.shulkerboxtooltip.BackpackTooltipPlugin;
import net.xstopho.resource_backpacks.config.BackpackConfig;
import net.xstopho.resource_backpacks.registries.CreativeTabRegistry;
import net.xstopho.resource_backpacks.registries.DataComponentsRegistry;
import net.xstopho.resource_backpacks.registries.ItemRegistry;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resourceconfigapi.api.ConfigRegistry;
import top.theillusivec4.curios.api.CuriosCapability;

@Mod(BackpackConstants.MOD_ID)
public class ResourceBackpacks {

    public ResourceBackpacks(IEventBus eventBus) {
        ConfigRegistry.register(BackpackConstants.MOD_ID, BackpackConfig.BUILDER, true);

        DataComponentsRegistry.init();

        ItemRegistry.init();
        MenuTypeRegistry.init();

        CreativeTabRegistry.init();

        if (BackpackConstants.CURIOS) {
            eventBus.addListener(this::registerCapabilities);
        }

        if (ModList.get().isLoaded("shulkerboxtooltip")) {
            ModLoadingContext.get().registerExtensionPoint(ShulkerBoxTooltipPlugin.class,
                    () -> new ShulkerBoxTooltipPlugin(BackpackTooltipPlugin::new));
        }
    }

    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(CuriosCapability.ITEM,
                (stack, context) -> () -> stack, ItemRegistry.BACKPACK_LEATHER.get(), ItemRegistry.BACKPACK_COPPER.get(),
                ItemRegistry.BACKPACK_GOLD.get(), ItemRegistry.BACKPACK_IRON.get(), ItemRegistry.BACKPACK_DIAMOND.get(),
                ItemRegistry.BACKPACK_NETHERITE.get());
    }
}
