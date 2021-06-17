package com.infamous.minibloom;

import com.infamous.minibloom.blocks.ModBlocks;
import com.infamous.minibloom.config.MinibloomConfig;
import com.infamous.minibloom.entities.EntityTypeAttributes;
import com.infamous.minibloom.entities.ModEntityTypes;
import com.infamous.minibloom.items.ModItems;
import com.infamous.minibloom.particles.ModParticleTypes;
import com.infamous.minibloom.worldgen.BiomeSpawnEntries;
import com.infamous.minibloom.worldgen.EntitySpawnPlacements;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("minibloom")
public class Minibloom
{
    // Directly reference a log4j logger.
    public static final String MODID = "minibloom";
    public static final Logger LOGGER = LogManager.getLogger();

    public Minibloom() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MinibloomConfig.COMMON_SPEC);
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModParticleTypes.PARTICLES.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event)
    {

        DeferredWorkQueue.runLater(() -> {
            EntityTypeAttributes.initEntityTypeAttributes();
            EntitySpawnPlacements.initSpawnPlacements();
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
    }

    private void processIMC(final InterModProcessEvent event)
    {
    }
}
