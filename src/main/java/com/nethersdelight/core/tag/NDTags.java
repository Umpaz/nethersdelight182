package com.nethersdelight.core.tag;

import com.nethersdelight.core.NethersDelight;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class NDTags {

	public NDTags() {
		super();
	}
	
	// Blocks in which Fungus Colonies can keep growing on. These blocks cannot form new colonies.
	public static final TagKey<Block> FUNGUS_COLONY_GROWABLE_ON = modBlockTag("fungus_colony_growable_on");

	public static final TagKey<Block> SOUL_COMPOST_ACTIVATORS = modBlockTag("soul_compost_activators");
		
	public static final TagKey<Block> SOUL_COMPOST_FLAMES = modBlockTag("soul_compost_flames");

	// Strider Meat
	public static final TagKey<Item> RAW_STRIDER = modItemTag("raw_strider");
	
	public static final TagKey<Item> MEAL_ITEM = modItemTag("meal_item");

	private static TagKey<Block> modBlockTag(String path) {
		return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(NethersDelight.MODID + ":" + path));
	}
	
	private static TagKey<Item> modItemTag(String path) {
		return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(NethersDelight.MODID + ":" + path));
	}
}