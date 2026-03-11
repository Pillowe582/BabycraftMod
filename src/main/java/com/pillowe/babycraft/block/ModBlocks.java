package com.pillowe.babycraft.block;

import com.besson.tutorial.item.ModItems;
import com.pillowe.babycraft.BabycraftMod;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BabycraftMod.MOD_ID);

    public static final DeferredBlock<Block> BABY_BLOCK = registerBlock("baby_block", Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS), true);

    private static <T extends Block> DeferredBlock<T> registerBlock(String name,
            Function<BlockBehaviour.Properties, T> func, Supplier<BlockBehaviour.Properties> properties,
            boolean shouldRegisterItem) {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, func, properties);
        if (shouldRegisterItem)
            ModItems.ITEMS.registerSimpleBlockItem(block);
        return block;
    }
}
