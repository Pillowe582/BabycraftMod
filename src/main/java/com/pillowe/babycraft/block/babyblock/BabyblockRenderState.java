package com.pillowe.babycraft.block.babyblock;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;

public class BabyblockRenderState extends BlockEntityRenderState {
    public BlockState adultState;

    public BabyblockRenderState() {
        // Initialize with a default state
        this.adultState = Blocks.GLASS.defaultBlockState();
    }
}
