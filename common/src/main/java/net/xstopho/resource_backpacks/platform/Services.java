package net.xstopho.resource_backpacks.platform;

import net.xstopho.stophoslib.StophoLibConstants;

import java.util.ServiceLoader;

public class Services {

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        StophoLibConstants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
