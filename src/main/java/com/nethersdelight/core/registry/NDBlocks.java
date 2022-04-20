package com.nethersdelight.core.registry;

import java.util.function.ToIntFunction;

import com.nethersdelight.common.block.BlackstoneStoveBlock;
import com.nethersdelight.common.block.FungusColonyBlock;
import com.nethersdelight.common.block.HoglinMountBlock;
import com.nethersdelight.common.block.MimicarnationBlock;
import com.nethersdelight.common.block.PropelplantBerryCaneBlock;
import com.nethersdelight.common.block.PropelplantBerryStemBlock;
import com.nethersdelight.common.block.PropelplantCaneBlock;
import com.nethersdelight.common.block.PropelplantStemBlock;
import com.nethersdelight.common.block.RichSoulSoilBlock;
import com.nethersdelight.common.block.SoulCompostBlock;
import com.nethersdelight.common.block.StuffedHoglinBlock;
import com.nethersdelight.core.NethersDelight;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NDBlocks
{
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NethersDelight.MODID);

	//Blocks

	// Workstations
	public static final RegistryObject<Block> BLACKSTONE_STOVE = BLOCKS.register("blackstone_stove", () -> new BlackstoneStoveBlock
			(BlockBehaviour.Properties.copy(Blocks.POLISHED_BLACKSTONE_BRICKS).lightLevel(litBlockEmission(13, 9))));

	public static final RegistryObject<Block> CRIMSON_FUNGUS_COLONY = BLOCKS.register("crimson_fungus_colony", () -> new FungusColonyBlock
			(BlockBehaviour.Properties.copy(Blocks.CRIMSON_FUNGUS), Items.CRIMSON_FUNGUS.delegate));
	public static final RegistryObject<Block> WARPED_FUNGUS_COLONY = BLOCKS.register("warped_fungus_colony", () -> new FungusColonyBlock
			(BlockBehaviour.Properties.copy(Blocks.WARPED_FUNGUS), Items.WARPED_FUNGUS.delegate));
	
	public static final RegistryObject<Block> STUFFED_HOGLIN = BLOCKS.register("stuffed_hoglin", () -> new StuffedHoglinBlock
			(BlockBehaviour.Properties.copy(Blocks.CAKE).noOcclusion()));
	public static final RegistryObject<Block> HOGLIN_MOUNT = BLOCKS.register("hoglin_mount", () -> new HoglinMountBlock
			(BlockBehaviour.Properties.copy(Blocks.BROWN_WOOL).noOcclusion().lightLevel((light) -> {
				return 1;
			}))); 
	
	public static final RegistryObject<Block> RICH_SOUL_SOIL = BLOCKS.register("rich_soul_soil", () -> new RichSoulSoilBlock
			(BlockBehaviour.Properties.copy(Blocks.SOUL_SOIL)));
	public static final RegistryObject<Block> SOUL_COMPOST = BLOCKS.register("soul_compost", () -> new SoulCompostBlock
			(BlockBehaviour.Properties.copy(Blocks.SOUL_SOIL)));
	
	public static final RegistryObject<Block> MIMICARNATION = BLOCKS.register("mimicarnation", () -> new MimicarnationBlock
			(MobEffects.INVISIBILITY, 8, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).lightLevel((light) -> {
				return 8;
			}))); 
	public static final RegistryObject<Block> POTTED_MIMICARNATION = BLOCKS.register("potted_mimicarnation", () -> new FlowerPotBlock
			(MIMICARNATION.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion().lightLevel((light) -> {
				return 8;
			}))); 
	
	public static final RegistryObject<Block> PROPELPLANT_STEM = BLOCKS.register("propelplant_stem", () -> new PropelplantStemBlock
			(BlockBehaviour.Properties.copy(Blocks.CRIMSON_FUNGUS).sound(SoundType.GRASS).strength(0.1F))); 
	public static final RegistryObject<Block> PROPELPLANT_BERRY_STEM = BLOCKS.register("propelplant_berry_stem", () -> new PropelplantBerryStemBlock
			(BlockBehaviour.Properties.copy(NDBlocks.PROPELPLANT_STEM.get()).lightLevel(propelplantBlockEmission(9))));
	
	public static final RegistryObject<Block> PROPELPLANT_CANE = BLOCKS.register("propelplant_cane", () -> new PropelplantCaneBlock
			(BlockBehaviour.Properties.copy(NDBlocks.PROPELPLANT_STEM.get())));
	public static final RegistryObject<Block> PROPELPLANT_BERRY_CANE = BLOCKS.register("propelplant_berry_cane", () -> new PropelplantBerryCaneBlock
			(BlockBehaviour.Properties.copy(NDBlocks.PROPELPLANT_STEM.get()).lightLevel(propelplantBlockEmission(9))));
	
	public static final RegistryObject<Block> PROPELPLANT_TORCH = BLOCKS.register("propelplant_torch", () -> new TorchBlock
			(BlockBehaviour.Properties.copy(NDBlocks.PROPELPLANT_STEM.get()).lightLevel((light) -> {
				return 12;
			}), ParticleTypes.FLAME)); 
	
	public static final RegistryObject<Block> PROPELPLANT_WALL_TORCH = BLOCKS.register("propelplant_wall_torch", () -> new WallTorchBlock
			(BlockBehaviour.Properties.copy(NDBlocks.PROPELPLANT_STEM.get()).dropsLike(PROPELPLANT_TORCH.get()).lightLevel((light) -> {
				return 12;
			}), ParticleTypes.FLAME)); 
	
	
	private static ToIntFunction<BlockState> litBlockEmission(int light, int soul) {
		return (state) -> {
			if (state.getValue(BlackstoneStoveBlock.SOUL)) {
	        	 return soul;
	         }
	         if (state.getValue(BlackstoneStoveBlock.SOUL) == false && state.getValue(BlackstoneStoveBlock.LIT)) {
	        	 return light;
	         }
	         else
	        	 return 0;
		};
	}
	
	private static ToIntFunction<BlockState> propelplantBlockEmission(int pearl) {
		return (state) -> {
			if (state.getValue(PropelplantBerryStemBlock.PEARL)) {
	        	 return pearl;
			}
	         else
	        	 return 0;
		};
	}
}
