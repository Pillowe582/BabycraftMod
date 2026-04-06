package com.pillowe.babycraft;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        // Babyblock Generation
        public static final ModConfigSpec.IntValue BABYBLOCK_GENERATE_COUNT = BUILDER
                        .comment("童块每区块生成尝试数")
                        .defineInRange("BabyblockGenerateCount", 256, 0, 256);

        // Babyblock Property
        public static final ModConfigSpec.DoubleValue BABYBLOCK_GROW_CHANCE = BUILDER
                        .comment("童块随机刻生长概率")
                        .defineInRange("BabyblockGrowChance", 0.0, 0.5, 1.0);

        // Dandelioness Skills
        public static final ModConfigSpec.DoubleValue GOLDEN_DANDELION_BLAST_RADIUS = BUILDER
                        .comment("金蒲公英技能范围")
                        .defineInRange("GoldenDandelionBlastRadius", 3.0, 0.0, 64.0);
        public static final ModConfigSpec.DoubleValue GOLDEN_DANDELIONESS_BLAST_RADIUS = BUILDER
                        .comment("金蒲母英技能范围")
                        .defineInRange("GoldenDandelionessBlastRadius", 3.0, 0.0, 64.0);

        // Highlight Color
        public static final ModConfigSpec.IntValue HIGHLIGHT_COLOR = BUILDER
                        .comment("童块高亮颜色")
                        .defineInRange("HighlightColor", 0xFFFFFF, 0, 0xFFFFFF);

        // Minimize Effect
        public static final ModConfigSpec.DoubleValue MINIMIZE_BASE_CHANCE = BUILDER
                        .comment("重开效果基础触发概率")
                        .defineInRange("MinimizeBaseChance", 0.4, 0.0, 1.0);
        public static final ModConfigSpec.DoubleValue MINIMIZE_LEVEL_CHANCE = BUILDER
                        .comment("重开效果每级触发概率提升")
                        .defineInRange("MinimizeLevelChance", 0.15, 0.0, 1.0);
        public static final ModConfigSpec.IntValue MINIMIZE_LEVEL_DURATION = BUILDER
                        .comment("重开效果每级持续时间（刻）")
                        .defineInRange("MinimizeLevelDuration", 240, 0, Integer.MAX_VALUE);

        // Rejuvenation Effect
        public static final ModConfigSpec.DoubleValue REJUVENATION_TRIGGER_CHANCE = BUILDER
                        .comment("返老还童效果触发概率")
                        .defineInRange("RejuvenationTriggerChance", 0.01, 0.0, 1.0);
        public static final ModConfigSpec.DoubleValue REJUVENATION_EFFECT_MOB_START_CHANCE = BUILDER
                        .comment("返老还童效果对生物触发概率")
                        .defineInRange("RejuvenationEffectMobStartChance", 0.1, 0, 1);

        public static final ModConfigSpec SPEC = BUILDER.build();

}
