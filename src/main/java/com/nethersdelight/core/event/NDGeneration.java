package com.nethersdelight.core.event;

import com.nethersdelight.core.NethersDelight;
import com.nethersdelight.core.registry.NDBiomeFeatures.FarmersRespiteConfiguredFeatures.FarmersRespitePlacedFeatures;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NethersDelight.MODID)
public class NDGeneration
{

	public static boolean matchesKeys(ResourceLocation loc, ResourceKey<?>... keys) {
		for (ResourceKey<?> key : keys)
			if (key.location().equals(loc))
				return true;
		return false;
	}

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		BiomeGenerationSettingsBuilder generation = event.getGeneration();

		if (event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FarmersRespitePlacedFeatures.PATCH_PROPELPLANT_CANE);
		}
	}
}