package com.infamous.minibloom.particles;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.infamous.minibloom.Minibloom.MODID;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID);

    // SPAWN EGGS
    public static final RegistryObject<BasicParticleType> FLOWER_POLLEN = PARTICLES.register("flower_pollen",
            () -> new BasicParticleType(true));
}