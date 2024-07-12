package net.xstopho.resource_backpacks;

import net.xstopho.resourcelibrary.service.CoreServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackpackConstants {
    public static final String MOD_ID = "resource_backpacks";
    public static final String MOD_NAME = "Resource Backpacks";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);


    public static final boolean ACCESSORIES = CoreServices.isModLoaded("accessories");
    public static final boolean TRINKETS = CoreServices.isModLoaded("trinkets");
    public static final boolean CURIOS = CoreServices.isModLoaded("curios");

    public static boolean noTrinketMod() {
        return !(BackpackConstants.ACCESSORIES || BackpackConstants.CURIOS || BackpackConstants.TRINKETS);
    }
}
