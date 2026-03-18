package com.pillowe.babycraft.item.golden_dandelioness;

import java.util.List;

import com.pillowe.babycraft.Config;
import com.pillowe.babycraft.block.ModBlocks;
import com.pillowe.babycraft.block.babyblock.BabyblockEntity;
import com.pillowe.babycraft.item.ModItems;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
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
        if (player.getMainHandItem().is(ModItems.GOLDEN_DANDELIONESS.get())
                && setEntityBaby(player.level(), target, true)) {
            itemStack.shrink(1);
            return InteractionResult.SUCCESS;
        }
        return super.interactLivingEntity(itemStack, player, target, type);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide())
            return InteractionResult.SUCCESS;

        ItemStack mainStack = player.getMainHandItem();
        ItemStack offStack = player.getOffhandItem();
        if ((hand == InteractionHand.MAIN_HAND && offStack.is(Items.GOLDEN_DANDELION))
                || (hand == InteractionHand.OFF_HAND
                        && mainStack.is(Items.GOLDEN_DANDELION))) {
            player.startUsingItem(hand);
            return InteractionResult.CONSUME;
        }

        return super.use(level, player, hand);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 1145;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.BOW;
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseTicks) {

        if (!(entity instanceof Player player))
            return;

        double maxSize = 3;
        ParticleOptions particle = ParticleTypes.PAUSE_MOB_GROWTH;
        int usedTime = getUseDuration(stack, entity) - remainingUseTicks;
        if (entity.getMainHandItem().is(ModItems.GOLDEN_DANDELIONESS.get())) {
            particle = ParticleTypes.PAUSE_MOB_GROWTH;
            maxSize = Config.GOLDEN_DANDELIONESS_BLAST_RADIUS.get();
        } else if (entity.getMainHandItem().is(Items.GOLDEN_DANDELION)) {
            maxSize = Config.GOLDEN_DANDELION_BLAST_RADIUS.get();
            particle = ParticleTypes.RESET_MOB_GROWTH;
        }
        if (level.isClientSide()) {

            double progress = Math.min(usedTime / 50.0, 1.0);
            double eased = 1 - Math.pow(1 - progress, 3);
            double half = maxSize * eased;
            int pointsPerSide = 25;

            for (int i = 0; i < pointsPerSide; i++) {

                double t = i / (double) (pointsPerSide - 1); // 0 → 1
                double offset = (t - 0.5) * 2 * half; // -half → +half

                double x = entity.getX();
                double y = entity.getY() + 0.1;
                double z = entity.getZ();

                // Downside
                level.addParticle(particle, x + offset, y, z - half, 0, 0, 0);

                // Upside
                level.addParticle(particle, x + offset, y, z + half, 0, 0, 0);

                // Leftside
                level.addParticle(particle, x - half, y, z + offset, 0, 0, 0);

                // Rightside
                level.addParticle(particle, x + half, y, z + offset, 0, 0, 0);
            }
        }
        if (usedTime >= 50) {
            // Forcely stop using item
            if (!level.isClientSide()) {
                releaseUsing(stack, level, player, remainingUseTicks);
            }

            player.stopUsingItem();
        }

    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if ((getUseDuration(stack, entity) - timeLeft) < 50) {
            // Not full powered
            return false;
        }
        if (!(entity instanceof Player player))
            return false;
        if (level.isClientSide())
            return false;

        ItemStack mainStack = player.getMainHandItem();
        ItemStack offStack = player.getOffhandItem();
        BlockPos pos = player.blockPosition();
        boolean isShifting = player.isShiftKeyDown();
        if (mainStack.is(ModItems.GOLDEN_DANDELIONESS.get()) && offStack.is(Items.GOLDEN_DANDELION)) {
            // Make things degrow to babies
            double radius = Config.GOLDEN_DANDELIONESS_BLAST_RADIUS.get();
            AABB area = new AABB(pos).inflate(radius);

            if (isShifting) {
                // Make blocks degrow
                BlockPos.betweenClosedStream(area).forEach(blockPos -> {
                    setBlockBaby(level, blockPos);
                });
            } else {
                // Make mobs degrow
                List<AgeableMob> mobs = level.getEntitiesOfClass(AgeableMob.class, area);
                mobs.forEach(mob -> {
                    setEntityBaby(level, mob, true);
                });
            }

            mainStack.shrink(1);
            offStack.shrink(1);
            player.getCooldowns().addCooldown(mainStack, 20);
            player.getCooldowns().addCooldown(offStack, 20);

            ((ServerLevel) level).sendParticles(ParticleTypes.PAUSE_MOB_GROWTH, pos.getX(), pos.getY(), pos.getZ(),
                    (int) (50 * radius),
                    radius, radius, radius, 0.0);
            return true;

        } else if (offStack.is(ModItems.GOLDEN_DANDELIONESS.get())
                && mainStack.is(Items.GOLDEN_DANDELION))

        {
            // Make things grow to adults
            double radius = Config.GOLDEN_DANDELION_BLAST_RADIUS.get();
            AABB area = new AABB(pos).inflate(radius);
            if (isShifting) {
                // Make blocks grow
                BlockPos.betweenClosedStream(area).forEach(blockPos -> {
                    if (level.getBlockEntity(blockPos) instanceof BabyblockEntity block) {
                        block.setGrowState(4);
                        block.growUp();
                    }
                });
            } else {
                // Make mobs grow and love
                List<AgeableMob> mobs = level.getEntitiesOfClass(AgeableMob.class, area);
                mobs.forEach(mob -> {
                    setEntityBaby(level, mob, false);
                    if (mob instanceof Animal animal) {
                        animal.setAge(0);
                        animal.setInLove(player);
                    }
                });
            }

            mainStack.shrink(1);
            offStack.shrink(1);
            player.getCooldowns().addCooldown(mainStack, 20);
            player.getCooldowns().addCooldown(offStack, 20);

            ((ServerLevel) level).sendParticles(ParticleTypes.RESET_MOB_GROWTH, pos.getX(), pos.getY(), pos.getZ(),
                    (int) (50 * radius),
                    radius, radius, radius, 0.0);

            return true;

        }
        return false;
    }

    private static boolean setEntityBaby(Level level, LivingEntity target, boolean setBaby) {

        if (!level.isClientSide() && target instanceof AgeableMob ageableMob && ageableMob.isBaby() != setBaby) {
            ageableMob.setBaby(setBaby);
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
