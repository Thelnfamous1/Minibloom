package com.infamous.minibloom.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.infamous.minibloom.Minibloom.MODID;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    // BUTTERCUP
    public static final RegistryObject<HoneySproutBlock> HONEY_SPROUT_BLOCK = BLOCKS.register("honey_sprout", () ->
            new HoneySproutBlock(Effects.REGENERATION, 160,
                    Block.Properties.create(Material.PLANTS)
                            .doesNotBlockMovement()
                            .hardnessAndResistance(0.0F)
                            .sound(SoundType.PLANT)));

}
