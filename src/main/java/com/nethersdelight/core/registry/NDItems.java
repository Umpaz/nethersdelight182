package com.nethersdelight.core.registry;

import com.nethersdelight.common.item.MagmaGelatinItem;
import com.nethersdelight.common.item.PropelpearlItem;
import com.nethersdelight.core.NethersDelight;
import com.nethersdelight.core.utility.NDFoods;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class NDItems
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NethersDelight.MODID);

	public static final RegistryObject<Item> STUFFED_HOGLIN = ITEMS.register("stuffed_hoglin",
			() -> new BlockItem(NDBlocks.STUFFED_HOGLIN.get(), new Item.Properties().stacksTo(1).tab(NethersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> HOGLIN_MOUNT = ITEMS.register("hoglin_mount",
			() -> new BlockItem(NDBlocks.HOGLIN_MOUNT.get(), new Item.Properties().tab(NethersDelight.CREATIVE_TAB)));
	
	public static final RegistryObject<Item> BLACKSTONE_STOVE = ITEMS.register("blackstone_stove",
			() -> new BlockItem(NDBlocks.BLACKSTONE_STOVE.get(), new Item.Properties().tab(NethersDelight.CREATIVE_TAB)));
	
	public static final RegistryObject<Item> CRIMSON_FUNGUS_COLONY = ITEMS.register("crimson_fungus_colony",
			() -> new BlockItem(NDBlocks.CRIMSON_FUNGUS_COLONY.get(), new Item.Properties().tab(NethersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> WARPED_FUNGUS_COLONY = ITEMS.register("warped_fungus_colony",
			() -> new BlockItem(NDBlocks.WARPED_FUNGUS_COLONY.get(), new Item.Properties().tab(NethersDelight.CREATIVE_TAB)));
	
	public static final RegistryObject<Item> RICH_SOUL_SOIL = ITEMS.register("rich_soul_soil",
			() -> new BlockItem(NDBlocks.RICH_SOUL_SOIL.get(), new Item.Properties().tab(NethersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> SOUL_COMPOST = ITEMS.register("soul_compost",
			() -> new BlockItem(NDBlocks.SOUL_COMPOST.get(), new Item.Properties().tab(NethersDelight.CREATIVE_TAB)));
	
	public static final RegistryObject<Item> MIMICARNATION = ITEMS.register("mimicarnation",
			() -> new BlockItem(NDBlocks.MIMICARNATION.get(), new Item.Properties().tab(NethersDelight.CREATIVE_TAB)));
	
	public static final RegistryObject<Item> PROPELPLANT_STEM = ITEMS.register("propelplant_stem",
			() -> new BlockItem(NDBlocks.PROPELPLANT_BERRY_STEM.get(), new Item.Properties().tab(NethersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> PROPELPLANT_CANE = ITEMS.register("propelplant_cane",
			() -> new BlockItem(NDBlocks.PROPELPLANT_CANE.get(), new Item.Properties().tab(NethersDelight.CREATIVE_TAB)));
	
	public static final RegistryObject<Item> PROPELPLANT_TORCH = ITEMS.register("propelplant_torch",
			() -> new StandingAndWallBlockItem(NDBlocks.PROPELPLANT_TORCH.get(), NDBlocks.PROPELPLANT_WALL_TORCH.get(), new Item.Properties().tab(NethersDelight.CREATIVE_TAB)));
	
	// Items
	public static final RegistryObject<Item> HOGLIN_HIDE = ITEMS.register("hoglin_hide",
			() -> new Item(new Item.Properties().tab(NethersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> RAW_STUFFED_HOGLIN = ITEMS.register("raw_stuffed_hoglin",
			() -> new Item(new Item.Properties().stacksTo(1).tab(NethersDelight.CREATIVE_TAB)));
	
	public static final RegistryObject<Item> HOGLIN_LOIN = ITEMS.register("hoglin_loin",
			() -> new Item(new Item.Properties().food(NDFoods.HOGLIN_LOIN).tab(NethersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> HOGLIN_SIRLOIN = ITEMS.register("hoglin_sirloin",
			() -> new Item(new Item.Properties().food(NDFoods.HOGLIN_LOIN).tab(NethersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> STRIDER_SLICE = ITEMS.register("strider_slice",
			() -> new Item(new Item.Properties().food(NDFoods.STRIDER_SLICE).fireResistant().tab(NethersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> GROUND_STRIDER = ITEMS.register("ground_strider",
			() -> new Item(new Item.Properties().food(NDFoods.GROUND_STRIDER).fireResistant().tab(NethersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> HOGLIN_EAR = ITEMS.register("hoglin_ear",
			() -> new Item(new Item.Properties().food(NDFoods.HOGLIN_EAR).tab(NethersDelight.CREATIVE_TAB)));

	public static final RegistryObject<Item> WARPED_MOLDY_MEAT = ITEMS.register("warped_moldy_meat",
			() -> new ConsumableItem(new Item.Properties().food(NDFoods.WARPED_MOLDY_MEAT).craftRemainder(Items.BOWL).stacksTo(16).tab(NethersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> GRILLED_STRIDER = ITEMS.register("grilled_strider",
			() -> new ConsumableItem(new Item.Properties().food(NDFoods.GRILLED_STRIDER).craftRemainder(Items.BOWL).stacksTo(16).tab(NethersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> STRIDER_MOSS_STEW = ITEMS.register("strider_moss_stew",
			() -> new ConsumableItem(new Item.Properties().food(NDFoods.STRIDER_MOSS_STEW).craftRemainder(Items.BOWL).stacksTo(16).tab(NethersDelight.CREATIVE_TAB)));
	
	public static final RegistryObject<Item> PLATE_OF_STUFFED_HOGLIN_SNOUT = ITEMS.register("plate_of_stuffed_hoglin_snout",
			() -> new ConsumableItem(new Item.Properties().food(NDFoods.PLATE_OF_STUFFED_HOGLIN_SNOUT).craftRemainder(Items.BOWL).stacksTo(16).tab(NethersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> PLATE_OF_STUFFED_HOGLIN_HAM = ITEMS.register("plate_of_stuffed_hoglin_ham",
			() -> new ConsumableItem(new Item.Properties().food(NDFoods.PLATE_OF_STUFFED_HOGLIN_HAM).craftRemainder(Items.BOWL).stacksTo(16).tab(NethersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> PLATE_OF_STUFFED_HOGLIN_ROAST = ITEMS.register("plate_of_stuffed_hoglin_roast",
			() -> new ConsumableItem(new Item.Properties().food(NDFoods.PLATE_OF_STUFFED_HOGLIN_ROAST).craftRemainder(Items.BOWL).stacksTo(16).tab(NethersDelight.CREATIVE_TAB)));

	public static final RegistryObject<Item> PROPELPEARL = ITEMS.register("propelpearl",
			() -> new PropelpearlItem(new Item.Properties().food(NDFoods.PROPELPEARL).tab(NethersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> NETHER_SKEWER = ITEMS.register("nether_skewer",
			() -> new Item(new Item.Properties().food(NDFoods.NETHER_SKEWER).tab(NethersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> MAGMA_GELATIN = ITEMS.register("magma_gelatin",
			() -> new MagmaGelatinItem(new Item.Properties().food(NDFoods.MAGMA_GELATIN).stacksTo(1).craftRemainder(Items.BUCKET).tab(NethersDelight.CREATIVE_TAB)));
		
}
