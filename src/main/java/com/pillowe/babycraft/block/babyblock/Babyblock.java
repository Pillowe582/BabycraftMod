package com.pillowe.babycraft.block.babyblock;

import com.pillowe.babycraft.Config;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class Babyblock extends Block implements EntityBlock {
    public Babyblock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BabyblockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE; // Set origin block invisible, in case blocking babyblock
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Apply grow chance when random ticked
        if (random.nextFloat() >= Config.BABYBLOCK_GROW_CHANCE.get()) {
            return;
        }
        // Grow up
        if (level.getBlockEntity(pos) instanceof BabyblockEntity babyblockEntity) {
            int growState = babyblockEntity.growUp();
            if (growState >= 4) {
                BlockState adultState = babyblockEntity.getAdultState();
                if (adultState != null && !adultState.isAir()) {
                    level.setBlock(pos, adultState, 3);
                }
            }
        }
    }

}
