package net.xstopho.resource_backpacks.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.ItemRegistry;

import java.util.concurrent.CompletableFuture;

public class  RecipeProv extends RecipeProvider {

    private static RecipeOutput craftingOutput;

    public RecipeProv(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    public void buildRecipes(RecipeOutput output) {
        craftingOutput = output;

        createBackpackCraftingRecipe(Items.CHEST, Items.LEATHER, ItemRegistry.BACKPACK_LEATHER.get());
        createBackpackCraftingRecipe(ItemRegistry.BACKPACK_LEATHER.get(), Items.COPPER_INGOT, ItemRegistry.BACKPACK_COPPER.get());
        createBackpackCraftingRecipe(ItemRegistry.BACKPACK_COPPER.get(), Items.GOLD_INGOT, ItemRegistry.BACKPACK_GOLD.get());
        createBackpackCraftingRecipe(ItemRegistry.BACKPACK_GOLD.get(), Items.IRON_INGOT, ItemRegistry.BACKPACK_IRON.get());
        createBackpackCraftingRecipe(ItemRegistry.BACKPACK_IRON.get(), Items.DIAMOND, ItemRegistry.BACKPACK_DIAMOND.get());

        createBackpackSmithingRecipe(ItemRegistry.BACKPACK_DIAMOND.get(), Items.NETHERITE_INGOT, ItemRegistry.BACKPACK_NETHERITE.get());
    }

    private void createBackpackCraftingRecipe(ItemLike input, ItemLike upgradeMaterial, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output, 1)
                .pattern("UUU").pattern("UIU").pattern("UUU")
                .define('U', upgradeMaterial).define('I', input)
                .unlockedBy(getHasName(input), has(input))
                .save(craftingOutput, location("crafting/" + getSimpleRecipeName(output)));
    }

    private void createBackpackSmithingRecipe(ItemLike input, ItemLike upgradeMaterial, ItemLike output) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(input), Ingredient.of(upgradeMaterial), RecipeCategory.MISC, output.asItem())
                .unlocks(getHasName(input), has(input))
                .save(craftingOutput, location("smithing/" + getSimpleRecipeName(output)));
    }

    private ResourceLocation location(String id) {
        return ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, id);
    }
}
