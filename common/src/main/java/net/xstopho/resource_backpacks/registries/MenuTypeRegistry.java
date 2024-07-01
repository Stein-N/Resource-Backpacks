package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resourcelibrary.registration.RegistryProvider;

public class MenuTypeRegistry {

    private static final RegistryProvider<MenuType<?>> MENU_TYPES = RegistryProvider.get(BuiltInRegistries.MENU, BackpackConstants.MOD_ID);

    private static <T extends AbstractContainerMenu> MenuType<T> register(MenuType.MenuSupplier<T> supplier) {
        return new MenuType<>(supplier, FeatureFlags.DEFAULT_FLAGS);
    }

    public static void init() {};
}
