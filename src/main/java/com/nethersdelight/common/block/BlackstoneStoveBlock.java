package com.nethersdelight.common.block;

import java.util.Optional;
import java.util.Random;

import javax.annotation.Nullable;

import com.nethersdelight.common.block.entity.BlackstoneStoveBlockEntity;
import com.nethersdelight.core.registry.NDBlockEntityTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FireChargeItem;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolActions;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.utility.ItemUtils;
import vectorwing.farmersdelight.common.utility.MathUtils;

public class BlackstoneStoveBlock extends BaseEntityBlock
{
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public static final BooleanProperty SOUL = BooleanProperty.create("soul");
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public BlackstoneStoveBlock(BlockBehaviour.Properties builder) {
		super(builder);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, false).setValue(SOUL, false));
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		ItemStack heldStack = player.getItemInHand(handIn);
		Item heldItem = heldStack.getItem();

		if (state.getValue(LIT)) {
			if (heldStack.canPerformAction(ToolActions.SHOVEL_DIG)) {
				extinguish(state, level, pos);
				heldStack.hurtAndBreak(1, player, action -> action.broadcastBreakEvent(handIn));
				return InteractionResult.SUCCESS;
			} else if (heldItem == Items.WATER_BUCKET) {
				if (!level.isClientSide()) {
					level.playSound(null, pos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);
				}
				extinguish(state, level, pos);
				if (!player.isCreative()) {
					player.setItemInHand(handIn, new ItemStack(Items.BUCKET));
				}
				return InteractionResult.SUCCESS;
			} else if (!state.getValue(SOUL) && (heldItem == Items.SOUL_SAND || heldItem == Items.SOUL_SOIL)) {
				if (!level.isClientSide()) {
					level.playSound(null, pos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);
				}
				soulLight(state, level, pos);
				if (!player.isCreative()) {
					heldStack.shrink(1);
				}
				return InteractionResult.SUCCESS;
			}
		} else {
			if (heldItem instanceof FlintAndSteelItem) {
				level.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, MathUtils.RAND.nextFloat() * 0.4F + 0.8F);
				level.setBlock(pos, state.setValue(BlockStateProperties.LIT, Boolean.TRUE), 11);
				heldStack.hurtAndBreak(1, player, action -> action.broadcastBreakEvent(handIn));
				return InteractionResult.SUCCESS;
			} else if (heldItem instanceof FireChargeItem) {
				level.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, (MathUtils.RAND.nextFloat() - MathUtils.RAND.nextFloat()) * 0.2F + 1.0F);
				level.setBlock(pos, state.setValue(BlockStateProperties.LIT, Boolean.TRUE), 11);
				if (!player.isCreative()) {
					heldStack.shrink(1);
				}
				return InteractionResult.SUCCESS;
			}
		}

		BlockEntity tileEntity = level.getBlockEntity(pos);
		if (tileEntity instanceof BlackstoneStoveBlockEntity stoveEntity) {
			int stoveSlot = stoveEntity.getNextEmptySlot();
			if (stoveSlot < 0 || stoveEntity.isStoveBlockedAbove()) {
				return InteractionResult.PASS;
			}
			Optional<CampfireCookingRecipe> recipe = stoveEntity.getMatchingRecipe(new SimpleContainer(heldStack), stoveSlot);
			if (recipe.isPresent()) {
				if (!level.isClientSide && stoveEntity.addItem(player.getAbilities().instabuild ? heldStack.copy() : heldStack, recipe.get(), stoveSlot)) {
					return InteractionResult.SUCCESS;
				}
				return InteractionResult.CONSUME;
			}
		}

		return InteractionResult.PASS;
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	public void extinguish(BlockState state, Level level, BlockPos pos) {
		level.setBlock(pos, state.setValue(LIT, false).setValue(SOUL, false), 2);
		double x = (double) pos.getX() + 0.5D;
		double y = pos.getY();
		double z = (double) pos.getZ() + 0.5D;
		level.playLocalSound(x, y, z, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F, false);
	}
	
	public void soulLight(BlockState state, Level level, BlockPos pos) {
		level.setBlock(pos, state.setValue(SOUL, true), 2);
		
		if (level.isClientSide()) {
		double x = (double) pos.getX() + 0.5D;
		double y = pos.getY();
		double z = (double) pos.getZ() + 0.5D;
		Direction direction = (Direction) state.getValue(BlackstoneStoveBlock.FACING);
		Axis direction$axis = direction.getAxis();
		Random rand = new Random();

		level.playLocalSound(x, y, z, SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 0.2F, 2.6F, false);
		level.playLocalSound(x, y, z, SoundEvents.SOUL_ESCAPE, SoundSource.BLOCKS, 1.5F, 2.6F, false);
		double d4 = rand.nextDouble() * 0.6D - 0.3D;
		double d5 = direction$axis == Axis.X ? (double) direction.getStepX() * 0.55D : d4;
		double d6 = rand.nextDouble() * 6.0D / 16.0D;
		double d7 = direction$axis == Axis.Z ? (double) direction.getStepZ() * 0.55D : d4;
		
		level.addParticle(ParticleTypes.EXPLOSION, x + d5, y + d6, z + d7, 0.0D, 0.0D, 0.0D);
		for (int i = 0; i < 7; ++i) {
			Random rand1 = new Random();
			double d0 = rand1.nextDouble() * 0.6D - 0.3D;
			double d1 = direction$axis == Axis.X ? (double) direction.getStepX() * 0.55D : d0;
			double d2 = rand1.nextDouble() * 6.0D / 16.0D;
			double d3 = direction$axis == Axis.Z ? (double) direction.getStepZ() * 0.55D : d0;
			level.addParticle(ParticleTypes.SOUL, x + d1, y + d2, z + d3, 0.0D, 0.0D, 0.0D);
	
			}
		}
	}		

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(LIT, true);
	}

	@Override
	public void stepOn(Level world, BlockPos pos, BlockState state, Entity entityIn) {
		boolean isLit = world.getBlockState(pos).getValue(BlackstoneStoveBlock.LIT);
		boolean isSoul = world.getBlockState(pos).getValue(BlackstoneStoveBlock.SOUL);
		if (isLit && !entityIn.fireImmune() && entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entityIn)) {
			entityIn.hurt(DamageSource.HOT_FLOOR, 1.0F);
		}
		if (isSoul && !entityIn.fireImmune() && entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entityIn)) {
			entityIn.hurt(DamageSource.HOT_FLOOR, 2.0F);
		}

		super.stepOn(world, pos, state, entityIn);
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity tileEntity = level.getBlockEntity(pos);
			if (tileEntity instanceof BlackstoneStoveBlockEntity) {
				ItemUtils.dropItems(level, pos, ((BlackstoneStoveBlockEntity) tileEntity).getInventory());
			}

			super.onRemove(state, level, pos, newState, isMoving);
		}
	}

	@Override
	protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(LIT, FACING, SOUL);
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level level, BlockPos pos, Random rand) {
		if (stateIn.getValue(CampfireBlock.LIT)) {
			double x = (double) pos.getX() + 0.5D;
			double y = pos.getY();
			double z = (double) pos.getZ() + 0.5D;
			if (rand.nextInt(10) == 0) {
				level.playLocalSound(x, y, z, ModSounds.BLOCK_STOVE_CRACKLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
			}

			Direction direction = stateIn.getValue(HorizontalDirectionalBlock.FACING);
			Direction.Axis direction$axis = direction.getAxis();
			double horizontalOffset = rand.nextDouble() * 0.6D - 0.3D;
			double xOffset = direction$axis == Direction.Axis.X ? (double) direction.getStepX() * 0.52D : horizontalOffset;
			double yOffset = rand.nextDouble() * 6.0D / 16.0D;
			double zOffset = direction$axis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52D : horizontalOffset;
			if (stateIn.getValue(BlackstoneStoveBlock.SOUL)) {
				level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x + xOffset, y + yOffset, z + zOffset, 0.0D, 0.0D, 0.0D);
			}
			else {
				level.addParticle(ParticleTypes.SMOKE, x + xOffset, y + yOffset, z + zOffset, 0.0D, 0.0D, 0.0D);
				level.addParticle(ParticleTypes.FLAME, x + xOffset, y + yOffset, z + zOffset, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return NDBlockEntityTypes.BLACKSTONE_STOVE.get().create(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
		if (state.getValue(LIT)) {
			return createTickerHelper(blockEntityType, NDBlockEntityTypes.BLACKSTONE_STOVE.get(), level.isClientSide
					? BlackstoneStoveBlockEntity::animationTick
					: BlackstoneStoveBlockEntity::cookingTick);
		}
		return null;
	}

	@Override
	public BlockState rotate(BlockState pState, Rotation pRot) {
		return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState pState, Mirror pMirror) {
		return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
	}
}