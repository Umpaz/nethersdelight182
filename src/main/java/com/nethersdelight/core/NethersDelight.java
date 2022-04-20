package com.nethersdelight.core;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nethersdelight.client.NDClientSetup;
import com.nethersdelight.core.event.NDCommonSetup;
import com.nethersdelight.core.registry.NDBiomeFeatures;
import com.nethersdelight.core.registry.NDBlockEntityTypes;
import com.nethersdelight.core.registry.NDBlocks;
import com.nethersdelight.core.registry.NDItems;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(NethersDelight.MODID)
@Mod.EventBusSubscriber(modid = NethersDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NethersDelight
{
	public static final String MODID = "nethersdelight";
	public static final CreativeModeTab CREATIVE_TAB = new CreativeModeTab(NethersDelight.MODID)
	{
		@Nonnull
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(NDBlocks.BLACKSTONE_STOVE.get());
		}
	};

	public static final Logger LOGGER = LogManager.getLogger();
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

	public NethersDelight() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		modEventBus.addListener(NDCommonSetup::init);
		modEventBus.addListener(NDClientSetup::init);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NDConfiguration.COMMON_CONFIG);

		NDItems.ITEMS.register(modEventBus);
		NDBlocks.BLOCKS.register(modEventBus);
		NDBiomeFeatures.FEATURES.register(modEventBus);
		NDBlockEntityTypes.TILES.register(modEventBus);

		MinecraftForge.EVENT_BUS.register(this);
	}
}
