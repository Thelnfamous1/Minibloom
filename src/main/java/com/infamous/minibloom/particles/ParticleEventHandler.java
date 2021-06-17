package com.infamous.minibloom.particles;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.infamous.minibloom.Minibloom.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleEventHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onParticleFactory(ParticleFactoryRegisterEvent event){
        Minecraft.getInstance().particles.registerFactory(ModParticleTypes.FLOWER_POLLEN.get(), FlowerPollenParticle.Factory::new);
    }
}