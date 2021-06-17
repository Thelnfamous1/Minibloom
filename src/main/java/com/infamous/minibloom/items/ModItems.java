package com.infamous.minibloom.items;

import com.infamous.minibloom.Minibloom;
import com.infamous.minibloom.blocks.ModBlocks;
import com.infamous.minibloom.entities.ModEntityTypes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.infamous.minibloom.Minibloom.MODID;

public class ModItems {

    public static final ITag.INamedTag<Item> MINIBLOOM_BREEDING_ITEMS = ItemTags.makeWrapperTag(Minibloom.MODID + ":" + "minibloom_breeding_items");


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // SPAWN EGGS
    public static final RegistryObject<ModSpawnEggItem> MINBLOOM_SPAWN_EGG = ITEMS.register("minibloom_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.MINIBLOOM,
                    0xedcd19, 0xfef8ba,
                    new Item.Properties().group(ItemGroup.MISC)));

    // BLOCK ITEMS
    public static final RegistryObject<BlockItem> HONEY_SPROUT = ITEMS.register("honey_sprout",
            () -> new BlockItem(ModBlocks.HONEY_SPROUT_BLOCK.get(),
                    new Item.Properties().group(ItemGroup.DECORATIONS)));
}