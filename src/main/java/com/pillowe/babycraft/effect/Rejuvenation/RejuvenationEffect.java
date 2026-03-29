package com.pillowe.babycraft.effect.Rejuvenation;

import com.pillowe.babycraft.Config;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
        FOV,
        SENSETIVITY,
        TITLE,
    }

    public RejuvenationEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onEffectStarted(LivingEntity mob, int amplifier) {
        makeParticle(mob, 0xFFFFFF);
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
                makeParticle(mob, 0xFF6666);
                int windowHeight = mc.getWindow().getHeight();
                int windowWidth = mc.getWindow().getWidth();
                mc.getWindow().setWindowed(windowWidth / 2, windowHeight / 2);
                break;
            case FPS:
                makeParticle(mob, 0xFFB266);
                int fpsLimit = mc.options.framerateLimit().get();
                if (fpsLimit == 0) {
                    fpsLimit = 240;
                }
                mc.options.framerateLimit().set(Math.max(fpsLimit / 2, 10));
                break;
            case GUISCALE:
                makeParticle(mob, 0xFFFF66);
                int guiScale = mc.options.guiScale().get();
                if (guiScale == 0) {
                    guiScale = 4;
                }
                mc.options.guiScale().set(Math.max(guiScale - 1, 1));
                break;
            case RENDERDISTANCE:
                makeParticle(mob, 0x66FF66);
                int renderDistance = mc.options.renderDistance().get();
                mc.options.renderDistance().set(Math.max(renderDistance / 2, 2));
                break;
            case FOV:
                makeParticle(mob, 0x66FFFF);
                int fov = mc.options.fov().get();
                mc.options.fov().set(Math.max(fov - 20, 30));
                break;
            case SENSETIVITY:
                makeParticle(mob, 0x6666FF);
                double sensitivity = mc.options.sensitivity().get();
                mc.options.sensitivity().set(Math.max(sensitivity - 0.2, 0));
                break;
            case TITLE:
                makeParticle(mob, 0xB266FF);
                mc.getWindow().setTitle("ᵐᶦⁿᵉᶜʳᵃᶠᵗ");
                break;
        }
        return true;
    }

    public static void makeParticle(LivingEntity mob, int color) {
        int x = (int) mob.getX();
        int y = (int) mob.getY();
        int z = (int) mob.getZ();
        DustParticleOptions particle = new DustParticleOptions(color, 1);

        ServerLevel serverLevel = (ServerLevel) mob.level();
        serverLevel.sendParticles(particle, x, y, z, 250, 1, 1, 1, 0);
        serverLevel.playSound(null, mob.getX(), mob.getY(), mob.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS,
                1.0f, 1.0f);
    }

}
