package com.pillowe.babycraft.effect.Rejuvenation;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class RejuvenationEffect extends MobEffect {
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

}
