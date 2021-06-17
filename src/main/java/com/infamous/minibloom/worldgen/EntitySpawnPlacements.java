package com.infamous.minibloom.worldgen;

import com.infamous.minibloom.entities.MinibloomEntity;
import com.infamous.minibloom.entities.ModEntityTypes;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.world.gen.Heightmap;

public class EntitySpawnPlacements {

    public static void initSpawnPlacements(){
        EntitySpawnPlacementRegistry.register(ModEntityTypes.MINIBLOOM.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                MinibloomEntity::canMinibloomSpawn);

    }

}
