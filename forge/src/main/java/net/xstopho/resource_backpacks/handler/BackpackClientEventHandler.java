package net.xstopho.resource_backpacks.handler;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resource_backpacks.rendering.container.BackpackContainerScreen;


@Mod.EventBusSubscriber(modid = BackpackConstants.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BackpackClientEventHandler {

    @SubscribeEvent
    public static void registerClientComponents(FMLClientSetupEvent event) {
        MenuScreens.register(MenuTypeRegistry.LEATHER_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.COPPER_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.GOLD_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.IRON_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.DIAMOND_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.NETHERITE_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.ENDER_BACKPACK_MENU.get(), BackpackContainerScreen::new);
    }

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(KeyMappingRegistry.OPEN_BACKPACK);
    }
}
