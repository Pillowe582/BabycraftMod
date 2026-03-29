package com.pillowe.babycraft.potion;

import com.pillowe.babycraft.BabycraftMod;
import com.pillowe.babycraft.effect.ModEffects;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {
        public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION,
                        BabycraftMod.MOD_ID);

        public static final Holder<Potion> REJUVENATION = POTIONS.register("babycraft_rejuvenation",
                        registryName -> new Potion(registryName.getPath(),
                                        new MobEffectInstance(ModEffects.REJUVENATION.getDelegate(), 1200, 0)));

        public static final Holder<Potion> REJUVENATION_LONG = POTIONS.register("babycraft_rejuvenation_long",
                        registryName -> new Potion(registryName.getPath(),
                                        new MobEffectInstance(ModEffects.REJUVENATION.getDelegate(), 3600, 0)));

        public static final Holder<Potion> MINIMIZE_I = POTIONS.register("babycraft_minimize_i",
                        registryName -> new Potion(registryName.getPath(),
                                        new MobEffectInstance(ModEffects.MINIMIZE_EFFECT.getDelegate(), 3600, 0)));

        public static final Holder<Potion> MINIMIZE_I_LONG = POTIONS.register("babycraft_minimize_i_long",
                        registryName -> new Potion(registryName.getPath(),
                                        new MobEffectInstance(ModEffects.MINIMIZE_EFFECT.getDelegate(), 9600, 0)));

        public static final Holder<Potion> MINIMIZE_II = POTIONS.register("babycraft_minimize_ii",
                        registryName -> new Potion(registryName.getPath(),
                                        new MobEffectInstance(ModEffects.MINIMIZE_EFFECT.getDelegate(), 3600, 1)));

        public static final Holder<Potion> MINIMIZE_II_LONG = POTIONS.register("babycraft_minimize_ii_long",
                        registryName -> new Potion(registryName.getPath(),
                                        new MobEffectInstance(ModEffects.MINIMIZE_EFFECT.getDelegate(), 9600, 1)));

        public static final Holder<Potion> MINIMIZE_III = POTIONS.register("babycraft_minimize_iii",
                        registryName -> new Potion(registryName.getPath(),
                                        new MobEffectInstance(ModEffects.MINIMIZE_EFFECT.getDelegate(), 3600, 2)));

        public static final Holder<Potion> MINIMIZE_III_LONG = POTIONS.register("babycraft_minimize_iii_long",
                        registryName -> new Potion(registryName.getPath(),
                                        new MobEffectInstance(ModEffects.MINIMIZE_EFFECT.getDelegate(), 9600, 2)));

        public static final Holder<Potion> MINIMIZE_IV = POTIONS.register("babycraft_minimize_iv",
                        registryName -> new Potion(registryName.getPath(),
                                        new MobEffectInstance(ModEffects.MINIMIZE_EFFECT.getDelegate(), 3600, 3)));

        public static final Holder<Potion> MINIMIZE_IV_LONG = POTIONS.register("babycraft_minimize_iv_long",
                        registryName -> new Potion(registryName.getPath(),
                                        new MobEffectInstance(ModEffects.MINIMIZE_EFFECT.getDelegate(), 9600, 3)));

        public static final Holder<Potion> MINIMIZE_V = POTIONS.register("babycraft_minimize_v",
                        registryName -> new Potion(registryName.getPath(),
                                        new MobEffectInstance(ModEffects.MINIMIZE_EFFECT.getDelegate(), 3600, 4)));

        public static final Holder<Potion> MINIMIZE_V_LONG = POTIONS.register("babycraft_minimize_v_long",
                        registryName -> new Potion(registryName.getPath(),
                                        new MobEffectInstance(ModEffects.MINIMIZE_EFFECT.getDelegate(), 9600, 4)));
}
