package net.xstopho.resource_backpacks.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.xstopho.resource_backpacks.registries.ItemRegistry;

import java.util.concurrent.CompletableFuture;

public class ItemTagProv extends FabricTagProvider.ItemTagProvider {

    public ItemTagProv(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(BackpackItemTags.BACKPACKS)
                .add(ItemRegistry.BACKPACK_LEATHER.get(), ItemRegistry.BACKPACK_COPPER.get(), ItemRegistry.BACKPACK_GOLD.get(),
                        ItemRegistry.BACKPACK_IRON.get(), ItemRegistry.BACKPACK_DIAMOND.get(), ItemRegistry.BACKPACK_NETHERITE.get());

        getOrCreateTagBuilder(BackpackItemTags.BACKPACK_LEATHER_INGREDIENT).add(Items.LEATHER, Items.RABBIT_HIDE);

        getOrCreateTagBuilder(BackpackItemTags.BACKPACK_LEATHER).add(ItemRegistry.BACKPACK_LEATHER.get());
        getOrCreateTagBuilder(BackpackItemTags.BACKPACK_COPPER).add(ItemRegistry.BACKPACK_COPPER.get());
        getOrCreateTagBuilder(BackpackItemTags.BACKPACK_GOLD).add(ItemRegistry.BACKPACK_GOLD.get());
        getOrCreateTagBuilder(BackpackItemTags.BACKPACK_IRON).add(ItemRegistry.BACKPACK_IRON.get());
        getOrCreateTagBuilder(BackpackItemTags.BACKPACK_DIAMOND).add(ItemRegistry.BACKPACK_DIAMOND.get());
        getOrCreateTagBuilder(BackpackItemTags.BACKPACK_NETHERITE).add(ItemRegistry.BACKPACK_NETHERITE.get());
    }
}
