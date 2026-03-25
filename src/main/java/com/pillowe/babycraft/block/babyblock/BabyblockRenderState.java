package com.pillowe.babycraft.block.babyblock;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.MovingBlockRenderState;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
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

    public final BlockModelRenderState blockModel = new BlockModelRenderState();
    public List<BlockStateModelPart> parts = new ArrayList<>();

    public BabyblockRenderState() {
        // Initialize with a default state
        this.adultState = Blocks.GLASS.defaultBlockState();

    }
}
