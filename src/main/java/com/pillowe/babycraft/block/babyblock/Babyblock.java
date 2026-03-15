package com.pillowe.babycraft.block.babyblock;

import com.pillowe.babycraft.Config;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Babyblock extends Block implements EntityBlock {
    public static final VoxelShape[] SHAPE = new VoxelShape[] {
            createScaledShape(0.3),
            createScaledShape(0.45),
            createScaledShape(0.6),
            createScaledShape(0.75),
            createScaledShape(0.9) };
    private static boolean isFrozen = false;

    public Babyblock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BabyblockEntity(pos, state);
    }

    @Override
    public void spawnDestroyParticles(Level level, Player player, BlockPos pos, BlockState state) {
        if (level.getBlockEntity(pos) instanceof BabyblockEntity be) {
            BlockState adultState = be.getAdultState();
            level.levelEvent(player, 2001, pos, getId(adultState));
            return;
        }
        super.spawnDestroyParticles(level, player, pos, state);
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
        if (level.getBlockEntity(pos) instanceof BabyblockEntity be) {
            int stage = be.getGrowState();
            stage = Math.min(stage, SHAPE.length - 1);
            return SHAPE[stage];
        }

        return SHAPE[0];
    }

    @Override
    public VoxelShape getShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context) {

        if (level.getBlockEntity(pos) instanceof BabyblockEntity be) {
            int stage = be.getGrowState();
            stage = Math.min(stage, SHAPE.length - 1);
            return SHAPE[stage];
        }

        return SHAPE[0];
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Apply grow chance when random ticked
        if (isFrozen || random.nextFloat() >= Config.BABYBLOCK_GROW_CHANCE.get()) {
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

    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos,
            Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (itemStack.is(Items.GOLDEN_DANDELION)) {
            if (!level.isClientSide()) {
                isFrozen = isFrozen ? false : true;
                System.out.println("Frozen: " + isFrozen);
                itemStack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }

        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    public static VoxelShape createScaledShape(double scale) {
        // Make collision box scale with growstate
        double half = 8 * scale;

        double minX = 8 - half;
        double maxX = 8 + half;

        double minZ = 8 - half;
        double maxZ = 8 + half;

        double maxY = 16 * scale;

        return Block.box(minX, 0, minZ, maxX, maxY, maxZ);
    }

}
