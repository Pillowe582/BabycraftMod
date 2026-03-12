package com.pillowe.babycraft.block.babyblock;

import com.pillowe.babycraft.block.babyblock.BabyblockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
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

}
