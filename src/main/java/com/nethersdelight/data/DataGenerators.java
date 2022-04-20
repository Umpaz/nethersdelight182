package com.nethersdelight.data;

import com.nethersdelight.core.NethersDelight;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = NethersDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators
{
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();
		if (event.includeServer()) {
			NDBlockTags blockTags = new NDBlockTags(generator, NethersDelight.MODID, helper);
			generator.addProvider(blockTags);
			generator.addProvider(new NDItemTags(generator, blockTags, NethersDelight.MODID, helper));
			generator.addProvider(new NDRecipes(generator));
		}
	}
}
