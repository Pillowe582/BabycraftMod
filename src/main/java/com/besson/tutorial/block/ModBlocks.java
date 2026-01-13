package com.besson.tutorial.block;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.custom.CornCrop;
import com.besson.tutorial.block.custom.StrawberryCrop;
import com.besson.tutorial.item.ModItems;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TutorialMod.MOD_ID);

    public static final DeferredBlock<Block> ICE_ETHER_BLOCK = registerBlock("ice_ether_block", Block::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE), true);
    public static final DeferredBlock<Block> RAW_ICE_ETHER_BLOCK = registerBlock("raw_ice_ether_block",
            properties -> new Block(properties.strength(1.0f, 3.0f)), true);
    public static final DeferredBlock<Block> ICE_ETHER_ORE = registerBlock("ice_ether_ore",
            properties -> new Block(properties.strength(1.0f, 3.0f).requiresCorrectToolForDrops()), true);

    public static final DeferredBlock<StairBlock> ICE_ETHER_STAIRS = registerBlock("ice_ether_stairs",
            p -> new StairBlock(ICE_ETHER_BLOCK.get().defaultBlockState(), p), () -> BlockBehaviour.Properties.ofFullCopy(ICE_ETHER_BLOCK.get()), true);
    public static final DeferredBlock<SlabBlock> ICE_ETHER_SLAB = registerBlock("ice_ether_slab",
            SlabBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ICE_ETHER_BLOCK.get()), true);
    public static final DeferredBlock<ButtonBlock> ICE_ETHER_BUTTON = registerBlock("ice_ether_button",
            p -> new ButtonBlock(BlockSetType.STONE, 20, p), () -> BlockBehaviour.Properties.ofFullCopy(ICE_ETHER_BLOCK.get()), true);
    public static final DeferredBlock<PressurePlateBlock> ICE_ETHER_PRESSURE_PLATE = registerBlock("ice_ether_pressure_plate",
            p -> new PressurePlateBlock(BlockSetType.STONE, p), () -> BlockBehaviour.Properties.ofFullCopy(ICE_ETHER_BLOCK.get()), true);
    public static final DeferredBlock<FenceBlock> ICE_ETHER_FENCE = registerBlock("ice_ether_fence",
            FenceBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ICE_ETHER_BLOCK.get()), true);
    public static final DeferredBlock<FenceGateBlock> ICE_ETHER_FENCE_GATE = registerBlock("ice_ether_fence_gate",
            p -> new FenceGateBlock(WoodType.ACACIA, p), () -> BlockBehaviour.Properties.ofFullCopy(ICE_ETHER_BLOCK.get()), true);
    public static final DeferredBlock<WallBlock> ICE_ETHER_WALL = registerBlock("ice_ether_wall",
            WallBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(ICE_ETHER_BLOCK.get()), true);
    public static final DeferredBlock<DoorBlock> ICE_ETHER_DOOR = registerBlock("ice_ether_door",
            p -> new DoorBlock(BlockSetType.IRON, p), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR), true);
    public static final DeferredBlock<TrapDoorBlock> ICE_ETHER_TRAPDOOR = registerBlock("ice_ether_trapdoor",
            p -> new TrapDoorBlock(BlockSetType.IRON, p), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_TRAPDOOR), true);

    public static final DeferredBlock<StrawberryCrop> STRAWBERRY_CROP = registerBlock("strawberry_crop", StrawberryCrop::new,
            () -> BlockBehaviour.Properties.of()
                    .noCollision()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY), false);

    public static final DeferredBlock<CornCrop> CORN_CROP = registerBlock("corn_crop", CornCrop::new,
            () -> BlockBehaviour.Properties.of()
                    .noCollision()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY), false);

    private static <T extends Block>DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> func, Supplier<BlockBehaviour.Properties> properties, boolean shouldRegisterItem) {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, func, properties);
        if (shouldRegisterItem) ModItems.ITEMS.registerSimpleBlockItem(block);
        return block;
    }

    private static <T extends Block>DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> func, boolean shouldRegisterItem) {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, func);
        if (shouldRegisterItem) ModItems.ITEMS.registerSimpleBlockItem(block);
        return block;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        TutorialMod.LOGGER.info("Registering Mod Blocks for " + TutorialMod.MOD_ID);
    }
}
