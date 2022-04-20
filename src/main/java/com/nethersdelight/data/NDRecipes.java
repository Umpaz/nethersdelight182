package com.nethersdelight.data;

import java.util.function.Consumer;

import javax.annotation.ParametersAreNonnullByDefault;

import com.nethersdelight.core.NethersDelight;
import com.nethersdelight.core.registry.NDItems;
import com.nethersdelight.core.tag.NDTags;
import com.nethersdelight.data.builder.NDCookingPotRecipeBuilder;
import com.nethersdelight.data.builder.NDCuttingBoardRecipeBuilder;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class NDRecipes extends RecipeProvider
{
	public NDRecipes(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		recipesCrafted(consumer);
		recipesSmelted(consumer);
		recipesCooked(consumer);
		recipesCut(consumer);
	}

	private void recipesCrafted(Consumer<FinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapeless(NDItems.SOUL_COMPOST.get())
		.requires(Items.SOUL_SOIL)
		.requires(Items.TWISTING_VINES)
		.requires(Items.TWISTING_VINES)
		.requires(Items.BONE_MEAL)
		.requires(Items.BONE_MEAL)
		.requires(Items.WARPED_ROOTS)
		.requires(Items.WARPED_ROOTS)
		.requires(Items.WARPED_ROOTS)
		.requires(Items.WARPED_ROOTS)
		.unlockedBy("has_soil", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SOUL_SOIL))
		.save(consumer, new ResourceLocation(NethersDelight.MODID, "soul_compost_from_warped_roots"));
		ShapelessRecipeBuilder.shapeless(NDItems.SOUL_COMPOST.get())
		.requires(Items.SOUL_SOIL)
		.requires(NDItems.HOGLIN_HIDE.get())
		.requires(NDItems.HOGLIN_HIDE.get())
		.requires(Items.TWISTING_VINES)
		.requires(Items.TWISTING_VINES)
		.requires(Items.BONE_MEAL)
		.requires(Items.BONE_MEAL)
		.requires(Items.BONE_MEAL)
		.requires(Items.BONE_MEAL)
		.unlockedBy("has_soil", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SOUL_SOIL))
		.save(consumer, new ResourceLocation(NethersDelight.MODID, "soul_compost_from_hoglin_hide"));
		ShapelessRecipeBuilder.shapeless(NDItems.WARPED_MOLDY_MEAT.get())
		.requires(Items.WARPED_ROOTS)
		.requires(Items.WARPED_ROOTS)
		.requires(NDItems.HOGLIN_SIRLOIN.get())
		.requires(Items.BOWL)
		.unlockedBy("has_sirloin", InventoryChangeTrigger.TriggerInstance.hasItems(NDItems.HOGLIN_SIRLOIN.get()))
		.save(consumer);
		
		
		ShapedRecipeBuilder.shaped(NDItems.BLACKSTONE_STOVE.get())
		.pattern("nnn")
		.pattern("b b")
		.pattern("bcb")
		.define('n', Items.NETHER_BRICK)
		.define('b', Items.POLISHED_BLACKSTONE_BRICKS)
		.define('c', Items.CAMPFIRE)
		.unlockedBy("has_nether_brick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_BRICK))
		.save(consumer);
		ShapedRecipeBuilder.shaped(NDItems.NETHER_SKEWER.get())
		.pattern("ps")
		.pattern("b ")
		.define('p', NDItems.PROPELPEARL.get())
		.define('s', NDTags.RAW_STRIDER)
		.define('b', Items.BLAZE_ROD)
		.unlockedBy("has_propelpearl", InventoryChangeTrigger.TriggerInstance.hasItems(NDItems.PROPELPEARL.get()))
		.save(consumer);
		ShapedRecipeBuilder.shaped(NDItems.RAW_STUFFED_HOGLIN.get())
		.pattern("wfc")
		.pattern("l#l")
		.pattern("hsh")
		.define('w', Items.WARPED_ROOTS)
		.define('f', Items.CRIMSON_FUNGUS)
		.define('c', Items.CRIMSON_ROOTS)
		.define('l', NDItems.HOGLIN_LOIN.get())
		.define('#', NDItems.HOGLIN_HIDE.get())
		.define('h', ModItems.HAM.get())
		.define('s', ModItems.NETHER_SALAD.get())
		.unlockedBy("has_hoglin_hide", InventoryChangeTrigger.TriggerInstance.hasItems(NDItems.HOGLIN_HIDE.get()))
		.save(consumer);
		ShapedRecipeBuilder.shaped(NDItems.HOGLIN_MOUNT.get())
		.pattern("php")
		.pattern("pwp")
		.pattern("ggg")
		.define('p', ItemTags.PLANKS)
		.define('h', NDItems.HOGLIN_HIDE.get())
		.define('w', ItemTags.WOOL)
		.define('g', Items.GOLD_NUGGET)
		.unlockedBy("has_hoglin_hide", InventoryChangeTrigger.TriggerInstance.hasItems(NDItems.HOGLIN_HIDE.get()))
		.save(consumer);
		ShapedRecipeBuilder.shaped(NDItems.PROPELPLANT_STEM.get())
		.pattern("p")
		.pattern("c")
		.define('p', NDItems.PROPELPLANT_CANE.get())
		.define('c', Items.CRIMSON_ROOTS)
		.unlockedBy("has_propelplant", InventoryChangeTrigger.TriggerInstance.hasItems(NDItems.PROPELPLANT_CANE.get()))
		.save(consumer);
		ShapedRecipeBuilder.shaped(NDItems.PROPELPLANT_TORCH.get())
		.pattern("p")
		.pattern("c")
		.define('p', NDItems.PROPELPLANT_CANE.get())
		.define('c', Items.CRIMSON_ROOTS)
		.unlockedBy("has_propelplant", InventoryChangeTrigger.TriggerInstance.hasItems(NDItems.PROPELPLANT_CANE.get()))
		.save(consumer);
	}
	
	private void recipesSmelted(Consumer<FinishedRecipe> consumer) {
		foodSmeltingRecipes("hoglin_sirloin", NDItems.HOGLIN_LOIN.get(), NDItems.HOGLIN_SIRLOIN.get(), 0.35F, consumer);
	}
	
	private void recipesCooked(Consumer<FinishedRecipe> consumer) {
		NDCookingPotRecipeBuilder.cookingPotRecipe(NDItems.STUFFED_HOGLIN.get(), 1, 200, 0.35F)
		.addIngredient(ModItems.NETHER_SALAD.get())
		.addIngredient(NDItems.RAW_STUFFED_HOGLIN.get())
		.addIngredient(ModItems.NETHER_SALAD.get())
		.build(consumer);
		NDCookingPotRecipeBuilder.cookingPotRecipe(NDItems.GRILLED_STRIDER.get(), 1, 200, 0.35F)
		.addIngredient(NDTags.RAW_STRIDER)
		.addIngredient(Items.WARPED_FUNGUS)
		.addIngredient(Items.CRIMSON_FUNGUS)
		.addIngredient(Items.WARPED_ROOTS)
		.addIngredient(Items.CRIMSON_ROOTS)
		.build(consumer);
		NDCookingPotRecipeBuilder.cookingPotRecipe(NDItems.MAGMA_GELATIN.get(), 1, 200, 0.35F, Items.LAVA_BUCKET)
		.addIngredient(Items.MAGMA_CREAM)
		.addIngredient(Items.MAGMA_CREAM)
		.addIngredient(Items.MAGMA_CREAM)
		.addIngredient(NDItems.PROPELPEARL.get())
		.build(consumer);
		NDCookingPotRecipeBuilder.cookingPotRecipe(NDItems.STRIDER_MOSS_STEW.get(), 1, 200, 0.35F)
		.addIngredient(Items.WARPED_FUNGUS)
		.addIngredient(Items.CRIMSON_FUNGUS)
		.addIngredient(Items.CRIMSON_ROOTS)
		.addIngredient(Items.WARPED_FUNGUS)
		.addIngredient(NDTags.RAW_STRIDER)
		.build(consumer);
	}
	
	public void recipesCut(Consumer<FinishedRecipe> consumer) {
		NDCuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDItems.STRIDER_SLICE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), NDItems.GROUND_STRIDER.get(), 2)
		.build(consumer);
		NDCuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDItems.CRIMSON_FUNGUS_COLONY.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.CRIMSON_FUNGUS, 5)
		.build(consumer);
		NDCuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDItems.WARPED_FUNGUS_COLONY.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.WARPED_FUNGUS, 5)
		.build(consumer);
		NDCuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDItems.HOGLIN_HIDE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.LEATHER, 4)
		.build(consumer);
		NDCuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(NDItems.PROPELPLANT_CANE.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.GUNPOWDER)
		.build(consumer);
	}
	
	private void foodSmeltingRecipes(String name, ItemLike ingredient, ItemLike result, float experience, Consumer<FinishedRecipe> consumer) {
		String namePrefix = new ResourceLocation(NethersDelight.MODID, name).toString();
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient),
				result, experience, 200)
		.unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
		.save(consumer);
		SimpleCookingRecipeBuilder.cooking(Ingredient.of(ingredient),
				result, experience, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE)
		.unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
		.save(consumer, namePrefix + "_from_campfire_cooking");
		SimpleCookingRecipeBuilder.cooking(Ingredient.of(ingredient),
				result, experience, 100, RecipeSerializer.SMOKING_RECIPE)
		.unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ingredient))
		.save(consumer, namePrefix + "_from_smoking");
	}
}