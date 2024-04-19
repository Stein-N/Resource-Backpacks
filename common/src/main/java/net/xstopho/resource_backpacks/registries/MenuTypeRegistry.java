package net.xstopho.resource_backpacks.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.xstopho.resource_backpacks.Constants;
import net.xstopho.resource_backpacks.container.BackpackContainer;
import net.xstopho.stophoslib.registration.RegistryObject;
import net.xstopho.stophoslib.registration.RegistryProvider;

public class MenuTypeRegistry {

    static final RegistryProvider<MenuType<?>> MENUS = RegistryProvider.get(Registries.MENU, Constants.MOD_ID);

    public static final RegistryObject<MenuType<BackpackContainer>> BACKPACK_MENU = MENUS.register("backpack_menu", () -> getMenu(BackpackContainer::new));

    private static <T extends AbstractContainerMenu> MenuType<T> getMenu(MenuType.MenuSupplier<T> supplier) {
        return new MenuType<>(supplier, FeatureFlags.DEFAULT_FLAGS);
    }

    public static void init() {
    }
}
