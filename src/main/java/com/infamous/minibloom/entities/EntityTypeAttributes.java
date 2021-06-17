package com.infamous.minibloom.entities;

import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;

public class EntityTypeAttributes {

    public static void initEntityTypeAttributes() {
        GlobalEntityTypeAttributes.put(ModEntityTypes.MINIBLOOM.get(), MinibloomEntity.setCustomAttributes().create());
    }
}
