package com.nethersdelight.common.levelgen.feature;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.mojang.serialization.Codec;
import com.nethersdelight.common.block.PropelplantBerryCaneBlock;
import com.nethersdelight.core.registry.NDBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class PropelplantFeature extends Feature<NoneFeatureConfiguration> {
	public PropelplantFeature(Codec<NoneFeatureConfiguration> config) {
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		Random rand = level.getRandom();
		BlockState propelplantBush = NDBlocks.PROPELPLANT_BERRY_STEM.get().defaultBlockState();
		BlockState propelplantStem = NDBlocks.PROPELPLANT_STEM.get().defaultBlockState();
		BlockState propelplantCane = NDBlocks.PROPELPLANT_CANE.get().defaultBlockState();
		BlockState propelplantBerryCane = NDBlocks.PROPELPLANT_BERRY_CANE.get().defaultBlockState();

		HashMap<BlockPos, BlockState> blocks = new HashMap<>();
		int i = 0;
		if (rand.nextInt(4) > 2) {
		for (int x = -1; x <= 1; ++x) {
			for (int z = -2; z <= 2; ++z) {
				if (Math.abs(x) < 2 || Math.abs(z) < 2) {
					for (int y = -1; y <= 1; ++y) {
						BlockPos blockpos = pos.offset(x, y, z);
						BlockPos below = blockpos.below();
						BlockState belowState = level.getBlockState(below);
						if (canGrowPropelplant(belowState)) {
							BlockPos above = blockpos.above();
							BlockPos evenMoreAbove = blockpos.above(2);
							if (level.isEmptyBlock(blockpos) && !level.isOutsideBuildHeight(above)) {
								Integer random = rand.nextInt(9);
								if (random < 3) {
									blocks.put(blockpos, propelplantBush);
								}
								if (random < 6 && random > 2 && level.isEmptyBlock(above)) {
									blocks.put(blockpos, propelplantStem);
									blocks.put(above, propelplantBerryCane.setValue(PropelplantBerryCaneBlock.PEARL, rand.nextBoolean()));
								} else if (level.isEmptyBlock(above) && level.isEmptyBlock(evenMoreAbove)) {
									blocks.put(blockpos, propelplantStem);
									blocks.put(above, propelplantCane);
									blocks.put(evenMoreAbove, propelplantBerryCane.setValue(PropelplantBerryCaneBlock.PEARL, rand.nextBoolean()));
								}
								++i;
							}
						}
					}
				}
			}
		}	
	}
			
		for (Map.Entry<BlockPos, BlockState> entry : blocks.entrySet()) {
			BlockPos entryPos = entry.getKey();
			BlockState entryState = entry.getValue();
			level.setBlock(entryPos, entryState, 19);
		}
		return i > 0;
	}
	public static boolean canGrowPropelplant(BlockState state)
	{
		return state.is(Blocks.NETHERRACK) || state.is(BlockTags.NYLIUM) || state.is(NDBlocks.RICH_SOUL_SOIL.get());
	}
}