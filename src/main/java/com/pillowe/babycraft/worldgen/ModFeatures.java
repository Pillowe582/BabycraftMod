package com.pillowe.babycraft.worldgen;

import net.minecraft.core.registries.Registries;
import com.pillowe.babycraft.BabycraftMod;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE,
            BabycraftMod.MOD_ID);

    public static final DeferredHolder<Feature<?>, BabyblockFeature> BABY_BLOCK_FEATURE = FEATURES.register(
            "baby_block_feature",
            () -> new BabyblockFeature(NoneFeatureConfiguration.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
