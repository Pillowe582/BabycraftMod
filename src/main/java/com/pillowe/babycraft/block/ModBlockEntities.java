package com.pillowe.babycraft.block;

import com.pillowe.babycraft.BabycraftMod;
import com.pillowe.babycraft.block.babyblock.BabyblockEntity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
                        .create(BuiltInRegistries.BLOCK_ENTITY_TYPE, BabycraftMod.MOD_ID);

        public static final Supplier<BlockEntityType<BabyblockEntity>> BABY_BLOCK_ENTITY = BLOCK_ENTITIES.register(
                        "baby_block_entity",
                        () -> new BlockEntityType<>(
                                        BabyblockEntity::new,
                                        ModBlocks.BABY_BLOCK.get()));
}
