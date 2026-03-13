package com.pillowe.babycraft.block.babyblock;

import com.pillowe.babycraft.block.ModBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.HolderLookup;

public class BabyblockEntity extends BlockEntity {
    private BlockState adultState;

    public BabyblockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BABY_BLOCK_ENTITY.get(), pos, state);
    }

    public void setAdultState(BlockState state) {
        this.adultState = state;
        this.setChanged(); // Mark the block entity as changed
        if (level != null && !level.isClientSide()) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    public BlockState getAdultState() {
        return net.minecraft.world.level.block.Blocks.GLASS.defaultBlockState();
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

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
