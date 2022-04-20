package com.nethersdelight.data;

import javax.annotation.Nullable;

import com.nethersdelight.core.registry.NDBlocks;
import com.nethersdelight.core.tag.NDTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import vectorwing.farmersdelight.common.tag.ModTags;

public class NDBlockTags extends BlockTagsProvider
{
	public NDBlockTags(DataGenerator generatorIn, String modId, @Nullable ExistingFileHelper existingFileHelper) {
		super(generatorIn, modId, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.registerModTags();
		this.registerMinecraftTags();
		this.registerForgeTags();

		this.registerBlockMineables();
	}

	protected void registerBlockMineables() {
		tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE).add(
				NDBlocks.BLACKSTONE_STOVE.get()
		);
		tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_SHOVEL).add(
				NDBlocks.SOUL_COMPOST.get(),
				NDBlocks.RICH_SOUL_SOIL.get()
		);
		tag(ModTags.MINEABLE_WITH_KNIFE).add(
				NDBlocks.PROPELPLANT_BERRY_CANE.get(),
				NDBlocks.PROPELPLANT_BERRY_STEM.get(),
				NDBlocks.PROPELPLANT_CANE.get(),
				NDBlocks.PROPELPLANT_STEM.get());
		tag(ModTags.HEAT_SOURCES).add(
				NDBlocks.BLACKSTONE_STOVE.get()
				);
	}

	protected void registerMinecraftTags() {
		tag(net.minecraft.tags.BlockTags.SMALL_FLOWERS).add(
				NDBlocks.MIMICARNATION.get()
		);
		tag(net.minecraft.tags.BlockTags.FLOWER_POTS).add(
				NDBlocks.POTTED_MIMICARNATION.get()
		);
		tag(net.minecraft.tags.BlockTags.HOGLIN_REPELLENTS).add(
				NDBlocks.WARPED_FUNGUS_COLONY.get(),
				NDBlocks.HOGLIN_MOUNT.get(),
				NDBlocks.STUFFED_HOGLIN.get()
		);
		tag(net.minecraft.tags.BlockTags.SOUL_FIRE_BASE_BLOCKS).add(
				NDBlocks.SOUL_COMPOST.get(),
				NDBlocks.RICH_SOUL_SOIL.get()
		);
		tag(net.minecraft.tags.BlockTags.SOUL_SPEED_BLOCKS).add(
				NDBlocks.SOUL_COMPOST.get(),
				NDBlocks.RICH_SOUL_SOIL.get()
		);
		tag(net.minecraft.tags.BlockTags.WALL_POST_OVERRIDE).add(
				NDBlocks.PROPELPLANT_TORCH.get()
		);
	}

	protected void registerForgeTags() {
		tag(net.minecraft.tags.BlockTags.DIRT).add(
				NDBlocks.RICH_SOUL_SOIL.get());
	}

	protected void registerModTags() {
		tag(NDTags.SOUL_COMPOST_ACTIVATORS).add(
				Blocks.CRIMSON_FUNGUS,
				Blocks.WARPED_FUNGUS,
				Blocks.SOUL_SAND,
				Blocks.SOUL_SOIL,
				Blocks.BONE_BLOCK,
				Blocks.NETHER_WART,
				NDBlocks.SOUL_COMPOST.get(),
				NDBlocks.RICH_SOUL_SOIL.get(),
				NDBlocks.CRIMSON_FUNGUS_COLONY.get(),
				NDBlocks.WARPED_FUNGUS_COLONY.get());
		tag(NDTags.SOUL_COMPOST_FLAMES).add(
				Blocks.LANTERN,
				Blocks.SOUL_LANTERN,
				Blocks.FIRE,
				Blocks.SOUL_FIRE);
		tag(NDTags.FUNGUS_COLONY_GROWABLE_ON).add(NDBlocks.RICH_SOUL_SOIL.get());
	}
}