package com.nethersdelight.common.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.nethersdelight.core.registry.NDBlocks;
import com.nethersdelight.core.registry.NDItems;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.tag.ModTags;

public class PropelplantBerryCaneBlock extends Block implements BonemealableBlock {
	public static final BooleanProperty PEARL = BooleanProperty.create("pearl");
	private static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	
	public PropelplantBerryCaneBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(PEARL, false));
	}
	
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
	
	public boolean isRandomlyTicking(BlockState p_57284_) {
		return true;
	}

	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
		BlockPos belowPos = pos.below();
		BlockState belowState = level.getBlockState(belowPos);
		Block belowBlock = belowState.getBlock();
		if (state.getValue(PEARL) == false && (belowBlock instanceof PropelplantStemBlock)) {
			if (random.nextInt(16) == 0) {
				if (random.nextInt(2) > 0) {
					if (level.isEmptyBlock(pos.above())) {
						level.setBlock(pos, ((Block) NDBlocks.PROPELPLANT_CANE.get()).defaultBlockState(), 2);
						level.setBlock(pos.above(), ((Block) NDBlocks.PROPELPLANT_BERRY_CANE.get()).defaultBlockState(), 2);
					}
				}
				else {
					level.setBlock(pos, (BlockState) state.setValue(PEARL, true), 2);		    
				}
			}
		}
		if (state.getValue(PEARL) == false && (belowBlock instanceof PropelplantCaneBlock)) {
			if (random.nextInt(8) == 0) {
				level.setBlock(pos, (BlockState) state.setValue(PEARL, true), 2);		    
			}
		}
	}

	@Override
	public boolean isPathfindable(BlockState p_51143_, BlockGetter p_51144_, BlockPos p_51145_, PathComputationType p_51146_) {
	      return false;
	   }
	
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult context) {
		ItemStack itemstack = player.getItemInHand(hand);
		Item usedItem = itemstack.getItem();
		if (state.getValue(PEARL) == true)
			   if (usedItem == Items.SHEARS) {
				   int j = 1 + level.random.nextInt(2);
				   popResource(level, pos, new ItemStack(NDItems.PROPELPEARL.get(), j));
				   level.playSound((Player)null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
				   level.setBlock(pos, state.setValue(PEARL, Boolean.FALSE), 2);
				   itemstack.hurtAndBreak(1, player, (shear) -> {
		               shear.broadcastBreakEvent(hand);
		            });
		            level.gameEvent(player, GameEvent.SHEAR, pos);
		            player.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
		            return InteractionResult.sidedSuccess(level.isClientSide);
			   }
			   else {
				   level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 1.5F, false, Explosion.BlockInteraction.NONE);
				   level.removeBlock(pos, false);
				   player.swing(hand);
				   level.gameEvent(player, GameEvent.BLOCK_PRESS, pos);
			   }
		return super.use(state, level, pos, player, hand, context);
	}
	
	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		BlockPos belowPos = pos.below();
		BlockState belowState = level.getBlockState(belowPos);
		Block belowBlock = belowState.getBlock();
		if (!(belowBlock == NDBlocks.PROPELPLANT_STEM.get() || belowBlock == NDBlocks.PROPELPLANT_CANE.get())) {
			level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 1.5F, false, Explosion.BlockInteraction.NONE);
			level.removeBlock(pos, false);	
		}
	}
	
	@Override
	public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
		if (!stack.is(ModTags.KNIVES)) {
			level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 1.5F, false, Explosion.BlockInteraction.NONE);		
		}	
		else {
			dropResources(state, level, pos, blockEntity, player, stack);
		}
	}
	
	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (entity instanceof LivingEntity) {
			entity.makeStuckInBlock(state, new Vec3((double)0.8F, 0.75D, (double)0.8F));
			if (!level.isClientSide && !(entity.isCrouching())) {
				   level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 1.5F, false, Explosion.BlockInteraction.NONE);
				   level.removeBlock(pos, false);	
			}
		}
		if (entity instanceof Projectile) {
			if (!level.isClientSide) {
				level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 1.5F, false, Explosion.BlockInteraction.NONE);
				level.removeBlock(pos, false);	
			}
		}
	}
	
	  protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		   return state.is(NDBlocks.PROPELPLANT_CANE.get()) || state.is(NDBlocks.PROPELPLANT_STEM.get());
	   }
	   
	   @Override
	   public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
		   return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, pos, facingPos);
	   }

	   @Override
	   public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		   BlockPos blockpos = pos.below();
		   return this.mayPlaceOn(level.getBlockState(blockpos), level, blockpos);
	   }


	   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
	      state.add(PEARL);
	   }

	   public boolean isValidBonemealTarget(BlockGetter p_57260_, BlockPos p_57261_, BlockState state, boolean p_57263_) {
	      return state.getValue(PEARL) == false;
	   }

	   public boolean isBonemealSuccess(Level p_57265_, Random p_57266_, BlockPos p_57267_, BlockState p_57268_) {
	      return true;
	   }

	   public void performBonemeal(ServerLevel level, Random random, BlockPos pos, BlockState state) {
	      level.setBlock(pos, state.setValue(PEARL, true), 2);
	   }
	}
