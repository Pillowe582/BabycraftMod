package com.pillowe.babycraft.worldgen;

import com.pillowe.babycraft.BabycraftMod;
import java.util.List;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> BABY_BLOCK_PLACED_KEY = registerKey("baby_block_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        // Get the ConfiguredFeature registry lookup
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // Register placed feature
        register(context, BABY_BLOCK_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.BABY_BLOCK_KEY),
                List.of(
                        // Try to generate per chunk (Count)
                        CountPlacement.of(100),
                        // Ensure random distribution in chunk (InSquare)
                        InSquarePlacement.spread(),
                        // Height range: from bedrock (-64) to above sea level (80)
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(200)),
                        // Only place if there's space/conditions are met
                        BiomeFilter.biome()));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
                Identifier.fromNamespaceAndPath(BabycraftMod.MOD_ID, name));
    }

    private static void register(
            BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> configuration,
            List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
