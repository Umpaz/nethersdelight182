package com.nethersdelight.data;

import javax.annotation.Nullable;

import com.nethersdelight.core.registry.NDItems;
import com.nethersdelight.core.tag.NDTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;

public class NDItemTags extends ItemTagsProvider
{
	public NDItemTags(DataGenerator generatorIn, BlockTagsProvider blockTagProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
		super(generatorIn, blockTagProvider, modId, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.registerModTags();
		this.registerMinecraftTags();
	}

	private void registerModTags() {
		tag(NDTags.RAW_STRIDER).add(NDItems.STRIDER_SLICE.get(), NDItems.GROUND_STRIDER.get());
		tag(NDTags.MEAL_ITEM).add(Items.MUSHROOM_STEW).add(Items.RABBIT_STEW).add(Items.SUSPICIOUS_STEW)
		.add(ModItems.BACON_AND_EGGS.get());
		tag(ModTags.CABBAGE_ROLL_INGREDIENTS).add(NDItems.HOGLIN_LOIN.get());

	}
	
	private void registerMinecraftTags() {
		tag(net.minecraft.tags.ItemTags.SOUL_FIRE_BASE_BLOCKS).add(
				NDItems.SOUL_COMPOST.get(),
				NDItems.RICH_SOUL_SOIL.get()
		);
		tag(net.minecraft.tags.ItemTags.PIGLIN_FOOD).add(
				NDItems.HOGLIN_LOIN.get(),
				NDItems.HOGLIN_SIRLOIN.get()
		);
		tag(net.minecraft.tags.ItemTags.PIGLIN_LOVED).add(
				NDItems.RAW_STUFFED_HOGLIN.get(),
				NDItems.STUFFED_HOGLIN.get()
		);
	}
}