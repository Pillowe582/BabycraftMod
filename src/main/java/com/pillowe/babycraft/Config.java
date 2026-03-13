package com.pillowe.babycraft;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        public static final ModConfigSpec.DoubleValue BABYBLOCK_GENERATE_CHANCE = BUILDER
                        .comment("The chance that a block will spawn as a baby")
                        .defineInRange("BabyblockGenerateChance", 0.0, 0.0001, 1.0);
        public static final ModConfigSpec.DoubleValue BABYBLOCK_GROW_CHANCE = BUILDER
                        .comment("The chance that a baby grows up when random ticked")
                        .defineInRange("BabyblockGrowChance", 0.0, 0.05, 1.0);

        public static final ModConfigSpec.ConfigValue<String> BABYBLOCK_DEFAULT_MATERIAL = BUILDER
                        .comment("The default block ID to use for baby block rendering when no adult state is set")
                        .define("BabyblockDefaultMaterial", "minecraft:glass");

        public static final ModConfigSpec SPEC = BUILDER.build();

        public static String getDefaultMaterial() {
                return BABYBLOCK_DEFAULT_MATERIAL.get();
        }
}
