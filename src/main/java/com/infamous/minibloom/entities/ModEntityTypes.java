package com.infamous.minibloom.entities;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.infamous.minibloom.Minibloom.MODID;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);

    // MINIBLOOM
    public static final RegistryObject<EntityType<MinibloomEntity>> MINIBLOOM = ENTITY_TYPES.register("minibloom", () ->
            EntityType.Builder.<MinibloomEntity>create(MinibloomEntity::new, EntityClassification.CREATURE)
                    .size(1.8F, 1.8F) // Minibloom will always be a child so its hitbox dimensions will be half of these
                    .trackingRange(10)
                    .setCustomClientFactory((spawnEntity,world) -> new MinibloomEntity(world))
                    .build(new ResourceLocation(MODID, "minibloom").toString())
    );

}
