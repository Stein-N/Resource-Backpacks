package net.xstopho.resource_backpacks.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.ItemRegistry;

import java.util.concurrent.CompletableFuture;

public class Recipes extends RecipeProvider {

    public Recipes(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    public void buildRecipes(RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.BACKPACK_LEATHER.get(), 1)
                .pattern("LLL").pattern("LCL").pattern("LLL")
                .define('L', Items.LEATHER).define('C', Items.CHEST)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .unlockedBy(getHasName(Items.CHEST), has(Items.CHEST))
                .save(output, location("crafting/" + getSimpleRecipeName(ItemRegistry.BACKPACK_LEATHER.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.BACKPACK_COPPER.get(), 1)
                .pattern("LLL").pattern("LCL").pattern("LLL")
                .define('L', Items.COPPER_INGOT).define('C', ItemRegistry.BACKPACK_LEATHER.get())
                .unlockedBy(getHasName(ItemRegistry.BACKPACK_LEATHER.get()), has(ItemRegistry.BACKPACK_LEATHER.get()))
                .save(output, location("crafting/" + getSimpleRecipeName(ItemRegistry.BACKPACK_COPPER.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.BACKPACK_GOLD.get(), 1)
                .pattern("LLL").pattern("LCL").pattern("LLL")
                .define('L', Items.GOLD_INGOT).define('C', ItemRegistry.BACKPACK_COPPER.get())
                .unlockedBy(getHasName(ItemRegistry.BACKPACK_COPPER.get()), has(ItemRegistry.BACKPACK_COPPER.get()))
                .save(output, location("crafting/" + getSimpleRecipeName(ItemRegistry.BACKPACK_GOLD.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.BACKPACK_IRON.get(), 1)
                .pattern("LLL").pattern("LCL").pattern("LLL")
                .define('L', Items.IRON_INGOT).define('C', ItemRegistry.BACKPACK_GOLD.get())
                .unlockedBy(getHasName(ItemRegistry.BACKPACK_GOLD.get()), has(ItemRegistry.BACKPACK_GOLD.get()))
                .save(output, location("crafting/" + getSimpleRecipeName(ItemRegistry.BACKPACK_IRON.get())));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.BACKPACK_DIAMOND.get(), 1)
                .pattern("LLL").pattern("LCL").pattern("LLL")
                .define('L', Items.DIAMOND).define('C', ItemRegistry.BACKPACK_IRON.get())
                .unlockedBy(getHasName(ItemRegistry.BACKPACK_IRON.get()), has(ItemRegistry.BACKPACK_IRON.get()))
                .save(output, location("backpacks/" + getSimpleRecipeName(ItemRegistry.BACKPACK_DIAMOND.get())));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ItemRegistry.BACKPACK_DIAMOND.get()), Ingredient.of(Items.NETHERITE_INGOT), RecipeCategory.TOOLS, ItemRegistry.BACKPACK_NETHERITE.get())
                .unlocks(getHasName(ItemRegistry.BACKPACK_DIAMOND.get()), has(ItemRegistry.BACKPACK_DIAMOND.get()))
                .save(output, location("smithing/" + getSimpleRecipeName(ItemRegistry.BACKPACK_NETHERITE.get())));
    }

    private ResourceLocation location(String id) {
        return ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, id);
    }
}
