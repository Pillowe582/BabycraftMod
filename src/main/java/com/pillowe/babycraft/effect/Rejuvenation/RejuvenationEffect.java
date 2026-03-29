package com.pillowe.babycraft.effect.Rejuvenation;

import com.pillowe.babycraft.Config;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class RejuvenationEffect extends MobEffect {
    RandomSource random = RandomSource.create(114514);

    private enum RandomEffects {
        WINDOW,
        FPS,
        GUISCALE,
        RENDERDISTANCE,
    }

    public RejuvenationEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onEffectStarted(LivingEntity mob, int amplifier) {

        this.addAttributeModifier(Attributes.SCALE, Identifier.fromNamespaceAndPath("babycraft", "rejuvenation_scale"),
                -0.5,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.MAX_HEALTH,
                Identifier.fromNamespaceAndPath("babycraft", "rejuvenation_health"),
                -0.5,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                Identifier.fromNamespaceAndPath("babycraft", "rejuvenation_attack"),
                -0.5,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.STEP_HEIGHT,
                Identifier.fromNamespaceAndPath("babycraft", "rejuvenation_step_height"),
                -0.5,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.SAFE_FALL_DISTANCE,
                Identifier.fromNamespaceAndPath("babycraft", "rejuvenation_safe_fall_distance"),
                1,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

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
        Minecraft mc = Minecraft.getInstance();
        if (!(mob instanceof ServerPlayer)) {
            return true;
        }
        switch (effect) {
            case WINDOW:
                int windowHeight = mc.getWindow().getHeight();
                int windowWidth = mc.getWindow().getWidth();
                mc.getWindow().setWindowed(windowWidth / 2, windowHeight / 2);
                break;
            case FPS:
                int fpsLimit = mc.options.framerateLimit().get();
                if (fpsLimit == 0) {
                    fpsLimit = 240;
                }
                mc.options.framerateLimit().set(Math.max(fpsLimit / 2, 10));
                break;
            case GUISCALE:
                int guiScale = mc.options.guiScale().get();
                if (guiScale == 0) {
                    guiScale = 4;
                }
                mc.options.guiScale().set(Math.max(guiScale - 1, 1));
                break;
            case RENDERDISTANCE:
                int renderDistance = mc.options.renderDistance().get();
                mc.options.renderDistance().set(Math.max(renderDistance / 2, 2));
                break;
        }
        return true;
    }

}
