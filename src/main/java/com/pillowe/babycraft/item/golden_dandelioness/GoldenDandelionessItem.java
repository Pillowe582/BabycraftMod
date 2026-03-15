package com.pillowe.babycraft.item.golden_dandelioness;

import com.pillowe.babycraft.block.ModBlocks;
import com.pillowe.babycraft.block.babyblock.BabyblockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class GoldenDandelionessItem extends Item {
    public GoldenDandelionessItem(Properties properties) {
        super(properties);
    }

    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
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

}
