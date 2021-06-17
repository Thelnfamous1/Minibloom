package com.infamous.minibloom.client;

import com.infamous.minibloom.entities.MinibloomEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.infamous.minibloom.Minibloom.MODID;

@OnlyIn(Dist.CLIENT)
public class MinibloomRenderer extends MobRenderer<MinibloomEntity, MinibloomModel<MinibloomEntity>> {
    private static final ResourceLocation MINIBLOOM_TEXTURE = new ResourceLocation(MODID, "textures/entity/minibloom.png");
    private static final ResourceLocation MINIBLOOM_SLEEP_TEXTURE = new ResourceLocation(MODID, "textures/entity/minibloom_sleep.png");

    public MinibloomRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MinibloomModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(MinibloomEntity entity) {
        if(entity.isSleeping()){
            return MINIBLOOM_SLEEP_TEXTURE;
        }
        return MINIBLOOM_TEXTURE;
    }
}