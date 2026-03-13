package com.pillowe.babycraft.client;

import com.pillowe.babycraft.BabycraftMod;
import com.pillowe.babycraft.block.babyblock.BabyblockRenderer;
import com.pillowe.babycraft.block.ModBlockEntities;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = BabycraftMod.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {

        event.registerBlockEntityRenderer(
                ModBlockEntities.BABY_BLOCK_ENTITY.get(),
                context -> new BabyblockRenderer(context));

    }

}