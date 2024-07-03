package net.xstopho.resource_backpacks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.entity.player.Player;
import net.xstopho.resource_backpacks.network.OpenBackpackPacket;
import net.xstopho.resource_backpacks.registries.MenuTypeRegistry;
import net.xstopho.resource_backpacks.rendering.container.BackpackContainerScreen;
import net.xstopho.resource_backpacks.util.BackpackKeyMappings;

public class ResourceBackpacksClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MenuScreens.register(MenuTypeRegistry.LEATHER_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.COPPER_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.GOLD_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.IRON_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.DIAMOND_BACKPACK_MENU.get(), BackpackContainerScreen::new);
        MenuScreens.register(MenuTypeRegistry.NETHERITE_BACKPACK_MENU.get(), BackpackContainerScreen::new);

        registerKeyMappings();
    }

    private void registerKeyMappings() {
        KeyMapping openBackpack = KeyBindingHelper.registerKeyBinding(BackpackKeyMappings.OPEN_BACKPACK);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Player player = client.player;
            if (player != null) {
                if (openBackpack.consumeClick()) ClientPlayNetworking.send(new OpenBackpackPacket(1));
            }
        });
    }
}
