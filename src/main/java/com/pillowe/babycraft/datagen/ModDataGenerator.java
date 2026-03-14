package com.pillowe.babycraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

import com.pillowe.babycraft.BabycraftMod;

@EventBusSubscriber(modid = BabycraftMod.MOD_ID)
public class ModDataGenerator {
    // idk but there needs both server and cilent

    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event) {

        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(true, new ModDatapackProvider(output, lookupProvider));
    }

    @SubscribeEvent
    public static void gatherCilentData(GatherDataEvent.Client event) {

        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        generator.addProvider(true, new ModDatapackProvider(output, lookupProvider));
    }

}
