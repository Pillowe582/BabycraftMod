package com.pillowe.babycraft.block.babyblock;

import net.minecraft.client.renderer.block.MovingBlockRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;

public class BabyblockRenderState extends BlockEntityRenderState {
    public BlockState adultState;
    public BlockPos pos;
    public int packedLight;
    public float growState;
    public final MovingBlockRenderState movingState = new MovingBlockRenderState();

    public BabyblockRenderState() {
        // Initialize with a default state
        this.adultState = Blocks.GLASS.defaultBlockState();

    }
}
