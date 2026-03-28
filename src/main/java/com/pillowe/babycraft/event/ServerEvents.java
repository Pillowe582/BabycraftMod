package com.pillowe.babycraft.event;

import com.pillowe.babycraft.BabycraftMod;
import com.pillowe.babycraft.Config;
import com.pillowe.babycraft.effect.ModEffects;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = BabycraftMod.MOD_ID)
public class ServerEvents {
    @SubscribeEvent
    public static void checkMinimize(LivingIncomingDamageEvent event) {
        System.out.println("checkMinimize");
        Level level = event.getEntity().level();
        if (level.isClientSide()) {
            return;
        }
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            if (!attacker.hasEffect(ModEffects.MINIMIZE_EFFECT.getDelegate())) {
                return;
            }
            int amplifier = attacker.getEffect(ModEffects.MINIMIZE_EFFECT.getDelegate()).getAmplifier();
            System.out.println("amplifier: " + amplifier);
            if (level.getRandom().nextDouble() < Config.MINIMIZE_BASE_CHANCE.get()
                    + Config.MINIMIZE_LEVEL_CHANCE.get()
                            * amplifier) {
                System.out.println("add rejuvenation");
                event.getEntity().addEffect(
                        new MobEffectInstance(
                                ModEffects.REJUVENATION,
                                (amplifier + 1) * Config.MINIMIZE_LEVEL_DURATION.get()));

            }
        }
    }
}
