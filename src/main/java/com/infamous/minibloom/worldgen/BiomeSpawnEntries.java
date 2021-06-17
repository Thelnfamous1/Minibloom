package com.infamous.minibloom.worldgen;

import com.infamous.minibloom.Minibloom;
import com.infamous.minibloom.config.MinibloomConfig;
import com.infamous.minibloom.entities.ModEntityTypes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

@Mod.EventBusSubscriber(modid = Minibloom.MODID)
public class BiomeSpawnEntries {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void initBiomeSpawnEntries(BiomeLoadingEvent event){
        MobSpawnInfoBuilder mobSpawnInfoBuilder = event.getSpawns();
        ResourceLocation biomeRegistryName = event.getName();
        if(biomeRegistryName != null){
            handleMinibloomSpawn(biomeRegistryName, mobSpawnInfoBuilder);
        }
    }

    private static void handleMinibloomSpawn(ResourceLocation biomeRegistryName, MobSpawnInfoBuilder mobSpawnInfoBuilder) {
        if(MinibloomConfig.COMMON.MINIBLOOM_BIOMES.get().contains(biomeRegistryName.toString())){
            List<MobSpawnInfo.Spawners> creatureSpawnList = mobSpawnInfoBuilder.getSpawner(EntityClassification.CREATURE);
            MobSpawnInfo.Spawners minibloomEntry =
                    new MobSpawnInfo.Spawners(
                            ModEntityTypes.MINIBLOOM.get(),
                            MinibloomConfig.COMMON.MINIBLOOM_SPAWN_WEIGHT.get(),
                            MinibloomConfig.COMMON.MINIBLOOM_MIN_SPAWN_GROUP_SIZE.get(),
                            MinibloomConfig.COMMON.MINIBLOOM_MAX_SPAWN_GROUP_SIZE.get());
            creatureSpawnList.add(minibloomEntry);
        }
    }
}
