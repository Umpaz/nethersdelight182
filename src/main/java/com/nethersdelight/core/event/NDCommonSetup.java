package com.nethersdelight.core.event;

import com.nethersdelight.core.registry.NDItems;

import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class NDCommonSetup
{
	public static void init(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			registerCompostables();
		});

	}

	public static void registerCompostables() {
		//ComposterBlock.COMPOSTABLES.put(NDItems.ROSE_HIP_PIE_SLICE.get(), 0.85F);

	}
}
