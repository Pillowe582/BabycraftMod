package com.pillowe.babycraft.worldgen;

import com.mojang.serialization.Codec;

import com.pillowe.babycraft.block.ModBlocks;
import com.pillowe.babycraft.block.babyblock.BabyblockEntity;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BabyblockFeature extends Feature<NoneFeatureConfiguration> {
    public BabyblockFeature(Codec<NoneFeatureConfiguration> codec) {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        BlockState originalState = level.getBlockState(pos);
        if (originalState.isAir() || originalState.is(Blocks.BEDROCK)) {
            return false;
        }
        if (!level.getFluidState(pos).isEmpty()) {
            return false;
        }
        boolean placed = level.setBlock(pos, ModBlocks.BABY_BLOCK.get().defaultBlockState(), 3);
        if (placed) {
            if (level.getBlockEntity(pos) instanceof BabyblockEntity baby) {
                baby.setAdultState(originalState);
            }
        }
        return true;
    }
}
