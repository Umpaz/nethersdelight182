package com.nethersdelight.core.registry;

import com.nethersdelight.common.block.entity.BlackstoneStoveBlockEntity;
import com.nethersdelight.core.NethersDelight;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NDBlockEntityTypes
{
	public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, NethersDelight.MODID);

	public static final RegistryObject<BlockEntityType<BlackstoneStoveBlockEntity>> BLACKSTONE_STOVE = TILES.register("blackstone_stove",
			() -> BlockEntityType.Builder.of(BlackstoneStoveBlockEntity::new, NDBlocks.BLACKSTONE_STOVE.get()).build(null));

}
