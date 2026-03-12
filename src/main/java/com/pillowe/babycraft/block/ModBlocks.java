package com.pillowe.babycraft.block;

import com.pillowe.babycraft.BabycraftMod;
import com.pillowe.babycraft.block.babyblock.Babyblock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BabycraftMod.MOD_ID);

    public static final DeferredBlock<Block> BABY_BLOCK = BLOCKS.registerBlock("baby_block", Babyblock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS));

}
