package net.xstopho.resource_backpacks.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.ItemRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagProv extends ItemTagsProvider {


    public ItemTagProv(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, BackpackConstants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BackpackItemTags.BACKPACKS)
                .add(ItemRegistry.BACKPACK_LEATHER.get(), ItemRegistry.BACKPACK_COPPER.get(), ItemRegistry.BACKPACK_GOLD.get(),
                        ItemRegistry.BACKPACK_IRON.get(), ItemRegistry.BACKPACK_DIAMOND.get(), ItemRegistry.BACKPACK_NETHERITE.get(),
                        ItemRegistry.BACKPACK_ENDER.get());

        this.tag(BackpackItemTags.BACKPACK_LEATHER_INGREDIENT).add(Items.LEATHER, Items.RABBIT_HIDE);

        this.tag(BackpackItemTags.BACKPACK_LEATHER).add(ItemRegistry.BACKPACK_LEATHER.get());
        this.tag(BackpackItemTags.BACKPACK_COPPER).add(ItemRegistry.BACKPACK_COPPER.get());
        this.tag(BackpackItemTags.BACKPACK_GOLD).add(ItemRegistry.BACKPACK_GOLD.get());
        this.tag(BackpackItemTags.BACKPACK_IRON).add(ItemRegistry.BACKPACK_IRON.get());
        this.tag(BackpackItemTags.BACKPACK_DIAMOND).add(ItemRegistry.BACKPACK_DIAMOND.get());
        this.tag(BackpackItemTags.BACKPACK_NETHERITE).add(ItemRegistry.BACKPACK_NETHERITE.get());
        this.tag(BackpackItemTags.BACKPACK_ENDER).add(ItemRegistry.BACKPACK_ENDER.get());
    }
}
