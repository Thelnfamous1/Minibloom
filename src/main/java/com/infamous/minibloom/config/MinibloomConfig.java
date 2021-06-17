package com.infamous.minibloom.config;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class MinibloomConfig {

    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Double> MINIBLOOM_HONEY_SPROUT_CHANCE;
        public final ForgeConfigSpec.ConfigValue<Integer> MINIBLOOM_HONEY_SPROUT_COOLDOWN;
        public final ForgeConfigSpec.ConfigValue<Double> MINIBLOOM_FERTILIZE_CHANCE;
        public final ForgeConfigSpec.ConfigValue<Integer> MINIBLOOM_FERTILIZE_COOLDOWN;
        public final ForgeConfigSpec.ConfigValue<Integer> MINIBLOOM_SPAWN_WEIGHT;
        public final ForgeConfigSpec.ConfigValue<Integer> MINIBLOOM_MIN_SPAWN_GROUP_SIZE;
        public final ForgeConfigSpec.ConfigValue<Integer> MINIBLOOM_MAX_SPAWN_GROUP_SIZE;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> MINIBLOOM_BIOMES;

        public Common(ForgeConfigSpec.Builder builder){

            builder.comment("Mob Configuration").push("mob_configuration");
            MINIBLOOM_HONEY_SPROUT_CHANCE = builder
                    .comment("Chance for a minibloom to spawn a honey sprout beneath it every 1/20th of a second (every tick) [default:0.01]")
                    .defineInRange("minibloomHoneySproutChance", 0.01D, 0, 1.0D);
            MINIBLOOM_HONEY_SPROUT_COOLDOWN = builder
                    .comment("How long in seconds a minibloom waits before attempting to spawn a honey sprout again [default:300]")
                    .defineInRange("minibloomHoneySproutCooldown", 300, 0, Integer.MAX_VALUE);
            MINIBLOOM_FERTILIZE_CHANCE = builder
                    .comment("Chance for a minibloom to fertilize the area beneath it every 1/20th of a second (every tick) [default:0.01]")
                    .defineInRange("minibloomFertilizeChance", 0.01D, 0, 1.0D);
            MINIBLOOM_FERTILIZE_COOLDOWN = builder
                    .comment("How long in seconds a minibloom waits before attempting to fertilize again [default:300]")
                    .defineInRange("minibloomFertilizeCooldown", 300, 0, Integer.MAX_VALUE);
            MINIBLOOM_SPAWN_WEIGHT = builder
                    .comment("Spawn weight for miniblooms, determines how likely they are to spawn compared to other mobs in the biome [default:8]")
                    .defineInRange("minibloomSpawnWeight", 8, 1, 100);
            MINIBLOOM_MIN_SPAWN_GROUP_SIZE = builder
                    .comment("Minimum spawn group size for miniblooms [default:4]")
                    .defineInRange("minibloomMinSpawnGroupSize", 4, 1, 100);
            MINIBLOOM_MAX_SPAWN_GROUP_SIZE = builder
                    .comment("Maximum spawn group size for miniblooms [default:4]")
                    .defineInRange("minibloomMaxSpawnGroupSize", 4, 1, 100);
            MINIBLOOM_BIOMES = builder
                    .comment("The biomes in which a minibloom can spawn [default: minecraft:flower_forest, minecraft:sunflower_plains]" )
                    .defineList("minibloomBiomes", Lists.newArrayList(
                            "minecraft:flower_forest",
                            "minecraft:sunflower_plains"
                            ),
                            (itemRaw) -> itemRaw instanceof String);
            builder.pop();
        }
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }
}