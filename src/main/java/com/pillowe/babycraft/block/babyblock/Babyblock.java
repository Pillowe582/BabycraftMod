package com.pillowe.babycraft.block.babyblock;

import com.pillowe.babycraft.Config;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Babyblock extends Block implements EntityBlock {
    public static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 8, 12);

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
    protected boolean propagatesSkylightDown(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getCollisionShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context) {

        return SHAPE;
    }

    @Override
    public VoxelShape getShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context) {

        return SHAPE;
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return true;
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
