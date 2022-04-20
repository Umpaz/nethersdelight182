package com.nethersdelight.core.registry;

import java.util.List;

import com.nethersdelight.common.levelgen.feature.PropelplantFeature;
import com.nethersdelight.core.NethersDelight;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class NDBiomeFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, NethersDelight.MODID);
	
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> PROPELPLANT_CANE = FEATURES.register("propelplant_cane", () -> new PropelplantFeature(NoneFeatureConfiguration.CODEC));

	public static final class FarmersRespiteConfiguredFeatures {

		public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> PATCH_PROPELPLANT_CANE = register(new ResourceLocation(NethersDelight.MODID, "patch_propelplant_cane"), 
				NDBiomeFeatures.PROPELPLANT_CANE.get(), (FeatureConfiguration.NONE));
		
		static Holder<PlacedFeature> registerPlacement(ResourceLocation id, Holder<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
			return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, id, new PlacedFeature(Holder.hackyErase(feature), List.of(modifiers)));
		}
		protected static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(ResourceLocation id, F feature, FC featureConfig) {
			return register(BuiltinRegistries.CONFIGURED_FEATURE, id, new ConfiguredFeature<>(feature, featureConfig));
		}
		private static <V extends T, T> Holder<V> register(Registry<T> registry, ResourceLocation id, V value) {
			return (Holder<V>) BuiltinRegistries.<T>register(registry, id, value);
		}
	
	
	
		public static final class FarmersRespitePlacedFeatures {
			public static final Holder<PlacedFeature> PATCH_PROPELPLANT_CANE = registerPlacement(new ResourceLocation(NethersDelight.MODID, "patch_propelplant_cane"),
					FarmersRespiteConfiguredFeatures.PATCH_PROPELPLANT_CANE, CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());			
		}
	}
}