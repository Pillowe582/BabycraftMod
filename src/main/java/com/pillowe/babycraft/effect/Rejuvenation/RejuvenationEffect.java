package com.pillowe.babycraft.effect.Rejuvenation;

import com.pillowe.babycraft.BabycraftMod;
import com.pillowe.babycraft.Config;
import com.pillowe.babycraft.effect.ModEffects;

import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.vault.VaultBlockEntity.Server;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;

public class RejuvenationEffect extends MobEffect {
    RandomSource random = RandomSource.create(114514);

    private enum RandomEffects {
        WINDOW
    }

    public RejuvenationEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onEffectStarted(LivingEntity mob, int amplifier) {
        AttributeInstance attribute = mob.getAttribute(Attributes.SCALE);
        if (attribute != null) {
            attribute.setBaseValue(0.5);
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        if (random.nextDouble() < Config.REJUVENATION_TRIGGER_CHANCE.get()) {
            return true;
        }
        return false;

    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        RandomEffects effects[] = RandomEffects.values();
        RandomEffects effect = effects[random.nextInt(effects.length)];
        switch (effect) {
            case WINDOW:
                if (!(mob instanceof ServerPlayer)) {
                    return true;
                }
                Minecraft mc = Minecraft.getInstance();
                int windowHeight = mc.getWindow().getHeight();
                int windowWidth = mc.getWindow().getWidth();
                mc.getWindow().setWindowed(windowWidth / 2, windowHeight / 2);
                break;
        }
        return true;
    }

}
