package com.nethersdelight.client;

import com.nethersdelight.client.renderer.BlackstoneStoveRenderer;
import com.nethersdelight.core.NethersDelight;
import com.nethersdelight.core.registry.NDBlockEntityTypes;
import com.nethersdelight.core.registry.NDBlocks;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = NethersDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NDClientSetup {
	
	public static void init(final FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(NDBlocks.STUFFED_HOGLIN.get(), RenderType.cutout());
	}
	
	@SubscribeEvent
	public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
		ItemBlockRenderTypes.setRenderLayer(NDBlocks.CRIMSON_FUNGUS_COLONY.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NDBlocks.WARPED_FUNGUS_COLONY.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NDBlocks.MIMICARNATION.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NDBlocks.POTTED_MIMICARNATION.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NDBlocks.STUFFED_HOGLIN.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NDBlocks.HOGLIN_MOUNT.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(NDBlocks.PROPELPLANT_BERRY_CANE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NDBlocks.PROPELPLANT_BERRY_STEM.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NDBlocks.PROPELPLANT_CANE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NDBlocks.PROPELPLANT_STEM.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(NDBlocks.PROPELPLANT_TORCH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(NDBlocks.PROPELPLANT_WALL_TORCH.get(), RenderType.cutout());

		event.registerBlockEntityRenderer(NDBlockEntityTypes.BLACKSTONE_STOVE.get(), BlackstoneStoveRenderer::new);
	}
}

