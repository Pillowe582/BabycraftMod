package com.pillowe.babycraft.block.babyblock;

import com.mojang.serialization.Codec;
import com.pillowe.babycraft.block.ModBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.HolderLookup;

public class BabyblockEntity extends BlockEntity {
    private BlockState adultState;
    private int growState = 0;
    private boolean isFrozen = false;

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
        return adultState;
    }

    public int growUp() {
        if (growState >= 4) {
            BlockState adultState = getAdultState();
            if (adultState != null && !adultState.isAir()) {
                level.setBlock(getBlockPos(), adultState, 3);
            }
            return growState;
        }
        growState += 1;
        if (level != null && !level.isClientSide()) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }

        return growState;
    }

    public int getGrowState() {
        return growState;
    }

    public void setGrowState(int state) {
        growState = state;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void tickGrow(ServerLevel level, BlockPos pos) {

        if (isFrozen) {
            return;
        }

        growUp();

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
        output.store("grow_state", Codec.INT, growState);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        adultState = input.read(
                "adult_state",
                BlockState.CODEC).orElse(null);
        growState = input.read("grow_state", Codec.INT).orElse(1);
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
