package net.xstopho.resource_backpacks;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.xstopho.resource_backpacks.datagen.ItemTagProv;
import net.xstopho.resource_backpacks.datagen.RecipeProv;

public class ResourceBackpacksDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(RecipeProv::new);
        pack.addProvider(ItemTagProv::new);
    }
}
