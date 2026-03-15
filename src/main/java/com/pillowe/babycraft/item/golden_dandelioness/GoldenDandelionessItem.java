package com.pillowe.babycraft.item.golden_dandelioness;

import java.util.List;
import java.util.stream.Stream;

import com.pillowe.babycraft.Config;
import com.pillowe.babycraft.block.ModBlocks;
import com.pillowe.babycraft.block.babyblock.Babyblock;
import com.pillowe.babycraft.block.babyblock.BabyblockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class GoldenDandelionessItem extends Item {
    public GoldenDandelionessItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        if (setBlockBaby(level, context.getClickedPos())) {
            context.getItemInHand().shrink(1);
        }
        ;
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity target,
            InteractionHand type) {
        if (setEntityBaby(player.level(), target)) {
            itemStack.shrink(1);
            return InteractionResult.SUCCESS;
        }
        return super.interactLivingEntity(itemStack, null, target, type);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        ItemStack mainStack = player.getMainHandItem();
        ItemStack offStack = player.getOffhandItem();
        if (hand == InteractionHand.MAIN_HAND && offStack.is(Items.GOLDEN_DANDELION)) {

            double radius = Config.GOLDEN_DANDELIONESS_BLAST_RADIUS.get();
            BlockPos pos = player.blockPosition();
            AABB area = new AABB(pos).inflate(radius);
            Stream<BlockPos> blocks = BlockPos.betweenClosedStream(area);
            blocks.forEach(blockPos -> {
                setBlockBaby(level, blockPos);
            });
            List<AgeableMob> mobs = level.getEntitiesOfClass(AgeableMob.class, area);
            mobs.forEach(mob -> {
                setEntityBaby(level, mob);
            });
            mainStack.shrink(1);
            offStack.shrink(1);

            ;

        } else if (hand == InteractionHand.OFF_HAND
                && mainStack.is(Items.GOLDEN_DANDELION)) {
            double radius = Config.GOLDEN_DANDELION_BLAST_RADIUS.get();
            BlockPos pos = player.blockPosition();
            AABB area = new AABB(pos).inflate(radius);
            Stream<BlockState> blocks = level.getBlockStates(area);
            blocks.forEach(blockState -> {
                if (blockState.getBlock() instanceof Babyblock block) {
                    block.reverseFrozen();
                }
            });
            List<AgeableMob> mobs = level.getEntitiesOfClass(AgeableMob.class, area);
            int tempCount = mainStack.getCount();
            mobs.forEach(mob -> {
                mainStack.setCount(tempCount);
                mob.interact(player, InteractionHand.MAIN_HAND, mob.position());
            });
            mainStack.setCount(tempCount - 1);
            offStack.shrink(1);

        }
        return super.use(level, player, hand);
    }

    private static boolean setEntityBaby(Level level, LivingEntity target) {

        if (!level.isClientSide() && target instanceof AgeableMob ageableMob && !ageableMob.isBaby()) {
            ageableMob.setBaby(true);
            return true;
        }
        return false;
    }

    private static boolean setBlockBaby(Level level, BlockPos pos) {

        BlockState originalState = level.getBlockState(pos);
        if (!originalState.isAir()) {
            boolean placed = level.setBlock(pos, ModBlocks.BABY_BLOCK.get().defaultBlockState(), 3);
            if (placed) {
                if (level.getBlockEntity(pos) instanceof BabyblockEntity baby) {
                    baby.setAdultState(originalState);

                    return true;
                }
            }
        }
        return false;
    }

}
