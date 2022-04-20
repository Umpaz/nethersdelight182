package com.nethersdelight.common.block;

import java.util.Random;

import com.nethersdelight.core.registry.NDBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.PlantType;

public class RichSoulSoilBlock extends Block
{

	public RichSoulSoilBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isClientSide) {
			BlockPos abovePos = pos.above();
			BlockState aboveState = worldIn.getBlockState(abovePos);
			Block aboveBlock = aboveState.getBlock();

			// Convert fungus to colonies
			if (aboveBlock == Blocks.CRIMSON_FUNGUS) {
				worldIn.setBlockAndUpdate(pos.above(), NDBlocks.CRIMSON_FUNGUS_COLONY.get().defaultBlockState());
			}
			if (aboveBlock == Blocks.WARPED_FUNGUS) {
				worldIn.setBlockAndUpdate(pos.above(), NDBlocks.WARPED_FUNGUS_COLONY.get().defaultBlockState());
			}
			if (aboveBlock == Blocks.NETHER_WART && rand.nextInt(3) == 0) {
				int i = aboveState.getValue(NetherWartBlock.AGE);
				if (i < 3)
				worldIn.setBlockAndUpdate(pos.above(), aboveState.setValue(NetherWartBlock.AGE, i + 1));	
			}
			if (aboveBlock == Blocks.AIR && rand.nextInt(50) == 0 && worldIn.getMaxLocalRawBrightness(pos.above()) < 1) {
				worldIn.setBlockAndUpdate(pos.above(), NDBlocks.MIMICARNATION.get().defaultBlockState());
			}

		}
	}
	
	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return true;
	}
	
	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
		net.minecraftforge.common.PlantType plantType = plantable.getPlantType(world, pos.relative(facing));
		return plantType != PlantType.CROP;
	}
}