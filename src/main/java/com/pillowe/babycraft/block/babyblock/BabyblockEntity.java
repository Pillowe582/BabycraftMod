package com.pillowe.babycraft.block.babyblock;

import com.pillowe.babycraft.block.ModBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class BabyblockEntity extends BlockEntity {
    private BlockState adultState;

    public BabyblockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BABY_BLOCK_ENTITY.get(), pos, state);
    }

    public void setAdultState(BlockState state) {
        this.adultState = state;
    }

    public BlockState getAdultState() {
        return adultState;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);

        if (adultState != null) {
            output.store(
                    "adult_state",
                    BlockState.CODEC,
                    adultState);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        adultState = input.read(
                "adult_state",
                BlockState.CODEC).orElse(null);
    }
}
