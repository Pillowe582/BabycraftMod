package com.pillowe.babycraft;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        // Babyblock Generation
        public static final ModConfigSpec.IntValue BABYBLOCK_GENERATE_COUNT = BUILDER
                        .comment("Babyblock spawn tries in a chunk")
                        .defineInRange("BabyblockGenerateCount", 256, 0, 256);

        // Babyblock Property
        public static final ModConfigSpec.DoubleValue BABYBLOCK_GROW_CHANCE = BUILDER
                        .comment("The chance that a baby grows up when random ticked")
                        .defineInRange("BabyblockGrowChance", 0.0, 0.5, 1.0);

        // Dandelioness Skills
        public static final ModConfigSpec.DoubleValue GOLDEN_DANDELION_BLAST_RADIUS = BUILDER
                        .comment("The radius of the golden dandelion explosion")
                        .defineInRange("GoldenDandelionBlastRadius", 3.0, 0.0, 64.0);
        public static final ModConfigSpec.DoubleValue GOLDEN_DANDELIONESS_BLAST_RADIUS = BUILDER
                        .comment("The radius of the golden dandelioness explosion")
                        .defineInRange("GoldenDandelionessBlastRadius", 3.0, 0.0, 64.0);

        // Highlight Color
        public static final ModConfigSpec.IntValue HIGHLIGHT_COLOR = BUILDER
                        .comment("The color of the highlight")
                        .defineInRange("HighlightColor", 0xFFFFFF, 0, 0xFFFFFF);

        // Minimize Effect
        public static final ModConfigSpec.DoubleValue MINIMIZE_BASE_CHANCE = BUILDER
                        .comment("The base trigger chance of the minimize effect")
                        .defineInRange("MinimizeBaseChance", 0.4, 0.0, 1.0);
        public static final ModConfigSpec.DoubleValue MINIMIZE_LEVEL_CHANCE = BUILDER
                        .comment("The triggerchance increase per level of the minimize effect")
                        .defineInRange("MinimizeLevelChance", 0.15, 0.0, 1.0);
        public static final ModConfigSpec.IntValue MINIMIZE_LEVEL_DURATION = BUILDER
                        .comment("The duration of the caused rejuvenation effect per level of the minimize effect (in ticks)")
                        .defineInRange("MinimizeLevelDuration", 240, 0, Integer.MAX_VALUE);

        // Rejuvenation Effect
        public static final ModConfigSpec.DoubleValue REJUVENATION_TRIGGER_CHANCE = BUILDER
                        .comment("The trigger chance of the rejuvenation effect per tick")
                        .defineInRange("RejuvenationTriggerChance", 0.01, 0.0, 1.0);
        public static final ModConfigSpec SPEC = BUILDER.build();

}
