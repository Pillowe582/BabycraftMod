package com.pillowe.babycraft.event;

import com.pillowe.babycraft.block.ModBlocks;
import com.pillowe.babycraft.block.babyblock.BabyblockEntity;
import com.pillowe.babycraft.Config;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.block.state.BlockState;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ChunkEvent;

@EventBusSubscriber
public class WorldGenEvents {

    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event) {

        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }

        LevelChunk chunk = (LevelChunk) event.getChunk();

        RandomSource random = level.getRandom();

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        int minY = level.dimensionType().minY();

        // Choose some blocks in the chunk.
        for (int i = 0; i < level.dimensionType().height() * Config.BABYBLOCK_GENERATE_CHANCE.get(); i++) {
            pos.setX(chunk.getPos().getMinBlockX() + random.nextInt(16));
            pos.setY(random.nextInt(level.dimensionType().height()) + minY);
            pos.setZ(chunk.getPos().getMinBlockZ() + random.nextInt(16));
            BlockState adultState = level.getBlockState(pos);

            // If not air, spawn a baby block.
            if (adultState.getBlock() != Blocks.AIR) {
                // Get the adult state of the block.

                level.setBlock(pos, ModBlocks.BABY_BLOCK.get().defaultBlockState(), 3);

                if (level.getBlockEntity(pos) instanceof BabyblockEntity baby) {

                    baby.setAdultState(adultState);
                    baby.setChanged();
                }
            }

        }
        System.out.println("Loaded a new chunk");

    }
}