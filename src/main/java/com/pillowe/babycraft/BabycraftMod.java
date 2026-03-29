package com.pillowe.babycraft;

import com.pillowe.babycraft.block.ModBlocks;
import com.pillowe.babycraft.effect.ModEffects;
import com.pillowe.babycraft.item.ModItems;
import com.pillowe.babycraft.potion.ModPotions;
import com.pillowe.babycraft.recipe.ModRecipes;
import com.pillowe.babycraft.worldgen.ModFeatures;
import com.pillowe.babycraft.block.ModBlockEntities;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import org.slf4j.Logger;

@Mod(BabycraftMod.MOD_ID)
public class BabycraftMod {

    // Mod 的唯一ID
    public static final String MOD_ID = "babycraft";

    // 日志系统（用于打印日志）
    public static final Logger LOGGER = LogUtils.getLogger();

    public BabycraftMod(IEventBus modEventBus, ModContainer modContainer) {

        // 注册配置文件
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        LOGGER.info("Babycraft Mod loaded successfully!");

        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModRecipes.RECIPES.register(modEventBus);
        ModEffects.EFFECTS.register(modEventBus);
        ModPotions.POTIONS.register(modEventBus);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.GOLDEN_DANDELIONESS);
        }
    }
}