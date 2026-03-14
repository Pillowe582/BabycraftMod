package com.pillowe.babycraft;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        public static final ModConfigSpec.IntValue BABYBLOCK_GENERATE_COUNT = BUILDER
                        .comment("Babyblock spawn tries in a chunk")
                        .defineInRange("BabyblockGenerateCount", 256, 0, 256);
        public static final ModConfigSpec.DoubleValue BABYBLOCK_GROW_CHANCE = BUILDER
                        .comment("The chance that a baby grows up when random ticked")
                        .defineInRange("BabyblockGrowChance", 0.0, 0.5, 1.0);

        public static final ModConfigSpec SPEC = BUILDER.build();

}
