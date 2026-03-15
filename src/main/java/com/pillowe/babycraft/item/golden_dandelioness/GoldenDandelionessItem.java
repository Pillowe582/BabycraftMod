package com.pillowe.babycraft.item.golden_dandelioness;

import com.pillowe.babycraft.block.ModBlocks;
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
        BlockPos pos = context.getClickedPos();
        BlockState originalState = level.getBlockState(pos);
        if (!originalState.isAir()) {
            boolean placed = level.setBlock(pos, ModBlocks.BABY_BLOCK.get().defaultBlockState(), 3);
            if (placed) {
                if (level.getBlockEntity(pos) instanceof BabyblockEntity baby) {
                    baby.setAdultState(originalState);
                    context.getItemInHand().shrink(1);
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity target,
            InteractionHand type) {
        if (!player.level().isClientSide() && target instanceof AgeableMob ageableMob && !ageableMob.isBaby()) {
            ageableMob.setBaby(true);
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
        if (hand == InteractionHand.MAIN_HAND && player.getOffhandItem().is(Items.GOLDEN_DANDELION)) {
            System.out.println("useMainHand");
        } else if (hand == InteractionHand.OFF_HAND
                && player.getMainHandItem().is(Items.GOLDEN_DANDELION)) {
            System.out.println("useOffHand");
        }
        return super.use(level, player, hand);
    }

}
