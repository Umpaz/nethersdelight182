package com.nethersdelight.integration.jei;

import com.nethersdelight.core.NethersDelight;
import com.nethersdelight.integration.jei.resource.CompositionDummy;

import mezz.jei.api.recipe.RecipeType;

public class NDRecipeTypes {
	public static final RecipeType<CompositionDummy> COMPOSITION = RecipeType.create(NethersDelight.MODID, "composition", CompositionDummy.class);
}