package com.infamous.minibloom.client;

import com.infamous.minibloom.blocks.ModBlocks;
import com.infamous.minibloom.entities.ModEntityTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.infamous.minibloom.Minibloom.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event){
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MINIBLOOM.get(), MinibloomRenderer::new);

        RenderTypeLookup.setRenderLayer(ModBlocks.HONEY_SPROUT_BLOCK.get(), RenderType.getCutout());

    }
}
