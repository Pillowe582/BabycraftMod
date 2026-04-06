package com.pillowe.babycraft.effect.Rejuvenation;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

import com.pillowe.babycraft.Config;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

public class RejuvenationEffectGoal extends Goal {
    private final Mob mob;
    private final Holder<MobEffect> effect;
    private int timer = 0;

    public RejuvenationEffectGoal(Mob mob, Holder<MobEffect> effect) {
        this.mob = mob;
        this.effect = effect;
        this.setFlags(EnumSet.of(Goal.Flag.TARGET, Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return mob.hasEffect(effect)
                && mob.getRandom().nextDouble() < Config.REJUVENATION_EFFECT_MOB_START_CHANCE.get();
    }

    @Override
    public boolean canContinueToUse() {
        return mob.hasEffect(effect) && this.timer > 0;
    }

    @Override
    public void start() {
        this.timer = mob.getRandom().nextIntBetweenInclusive(Config.REJUVENATION_EFFECT_MOB_LAST_TIME_MIN.get(),
                Config.REJUVENATION_EFFECT_MOB_LAST_TIME_MAX.get());
        RejuvenationEffectPlayer.makeParticle(mob, mob.getRandom().nextInt(0xFFFFFF));
    }

    @Override
    public void tick() {
        mob.getJumpControl().jump();
        float spinSpeed = 45.0F;
        float newYaw = mob.getYRot() + spinSpeed;
        mob.setYRot(newYaw);
        mob.setYBodyRot(newYaw);
        mob.setYHeadRot(newYaw);

        if (timer > 0) {
            timer--;
        }
    }

}