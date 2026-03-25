package com.pillowe.babycraft.block.babyblock;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;// It was moved into level
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import org.jetbrains.annotations.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.pillowe.babycraft.Config;

public class BabyblockRenderer implements BlockEntityRenderer<BabyblockEntity, BabyblockRenderState> {
    public static boolean highlighted = false;
    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();
    private final BlockModelResolver blockResolver;

    public BabyblockRenderer(BlockEntityRendererProvider.Context context) {
        // Constructor can be empty or used for initialization if needed
        this.blockResolver = context.blockModelResolver();
    }

    @Override
    public BabyblockRenderState createRenderState() {
        // Create the render state
        return new BabyblockRenderState();

    }

    @Override
    public void extractRenderState(BabyblockEntity blockEntity,
            BabyblockRenderState renderState,
            float partialTicks,
            Vec3 cameraPos,
            @Nullable ModelFeatureRenderer.CrumblingOverlay overlay) {

        // Extract the adult state from block entity
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTicks, cameraPos,
                overlay); // This is mandatory, or die alive no render
        renderState.adultState = blockEntity.getAdultState();
        renderState.growState = blockEntity.getGrowState();
        renderState.pos = blockEntity.getBlockPos();
        Level level = blockEntity.getLevel();

        // Set the moving block state
        renderState.movingState.blockState = renderState.adultState;
        renderState.movingState.blockPos = blockEntity.getBlockPos();
        renderState.movingState.biome = level.getBiome(blockEntity.getBlockPos());
        renderState.movingState.lightEngine = level.getLightEngine();
        renderState.movingState.cardinalLighting = CardinalLighting.DEFAULT;
        renderState.movingState.randomSeedPos = renderState.pos;

        // 新API：使用BlockModelResolver更新块模型状态
        if (highlighted) {
            blockResolver.update(renderState.blockModel, Blocks.WHITE_CONCRETE.defaultBlockState(),
                    BLOCK_DISPLAY_CONTEXT);
        } else {
            blockResolver.update(renderState.blockModel, renderState.adultState, BLOCK_DISPLAY_CONTEXT);
        }

    }

    @Override
    public void submit(BabyblockRenderState state,
            PoseStack poseStack,
            SubmitNodeCollector collector,
            CameraRenderState cameraState) {
        // Submit the adult block
        if (state.adultState == null || state.adultState.isAir()) {
            return;
        }

        // Cal the grow state
        float scaleMultiplier = 0.3f + 0.15f * state.growState;
        // Matrix transform to get a smaller one
        poseStack.pushPose();
        poseStack.translate(0.5, 0.0001, 0.5);
        poseStack.scale(scaleMultiplier, scaleMultiplier, scaleMultiplier);
        poseStack.translate(-0.5, 0, -0.5);

        if (highlighted) {
            state.blockModel.submit(
                    poseStack,
                    collector,
                    state.packedLight,
                    255,
                    Config.HIGHLIGHT_COLOR.get());
        } else {
            collector.submitMovingBlock(poseStack, state.movingState);
        }
        poseStack.popPose();
    }
}
