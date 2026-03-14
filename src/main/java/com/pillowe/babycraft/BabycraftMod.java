package com.pillowe.babycraft;

import com.pillowe.babycraft.block.ModBlocks;
import com.pillowe.babycraft.datagen.ModDataGenerator;
import com.pillowe.babycraft.worldgen.ModFeatures;
import com.pillowe.babycraft.block.ModBlockEntities;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

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

    }
}