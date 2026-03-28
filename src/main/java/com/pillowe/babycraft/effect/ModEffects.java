package com.pillowe.babycraft.effect;

import com.pillowe.babycraft.BabycraftMod;
import com.pillowe.babycraft.effect.Minimize.MinimizeEffect;
import com.pillowe.babycraft.effect.Rejuvenation.RejuvenationEffect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT,
            BabycraftMod.MOD_ID);
    public static final Holder<MobEffect> REJUVENATION = EFFECTS.register("rejuvenation_effect",
            () -> new RejuvenationEffect(MobEffectCategory.HARMFUL, 0x6B8E23));
    public static final Holder<MobEffect> MINIMIZE_EFFECT = EFFECTS.register("minimize_effect",
            () -> new MinimizeEffect(MobEffectCategory.BENEFICIAL, 0xFF99CC));
}
