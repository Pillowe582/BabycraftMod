package com.pillowe.babycraft.block.babyblock;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.phys.Vec3;

import org.jetbrains.annotations.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;

public class BabyblockRenderer implements BlockEntityRenderer<BabyblockEntity, BabyblockRenderState> {
    public static boolean highlighted = false;

    public BabyblockRenderer(BlockEntityRendererProvider.Context context) {
        // Constructor can be empty or used for initialization if needed
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
        BlockEntityRenderer.super.extractRenderState(blockEntity,
                renderState,
                partialTicks,
                cameraPos,
                overlay); // This is mandatory, or die alive no render
        renderState.adultState = blockEntity.getAdultState();
        renderState.growState = blockEntity.getGrowState();
        renderState.pos = blockEntity.getBlockPos();

        // Make it light as common blocks
        if (blockEntity.getLevel() != null) {
            renderState.packedLight = LevelRenderer.getLightCoords(blockEntity.getLevel(), renderState.pos);
        } else {
            renderState.packedLight = 15728880;
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
        poseStack.translate(0.5, 0, 0.5);
        poseStack.scale(scaleMultiplier, scaleMultiplier, scaleMultiplier);
        poseStack.translate(-0.5, 0, -0.5);

        collector.submitBlock(poseStack,
                state.adultState,
                state.packedLight,
                OverlayTexture.NO_OVERLAY,
                highlighted ? 255 : 0);

        poseStack.popPose();
    }
}
