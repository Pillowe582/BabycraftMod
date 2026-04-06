package com.pillowe.babycraft.effect.Rejuvenation;

import com.pillowe.babycraft.BabycraftMod;
import com.pillowe.babycraft.effect.ModEffects;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Mob;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber(modid = BabycraftMod.MOD_ID)
public class RejuvenationEffectMob {
    @SubscribeEvent
    public static void onRejuvenationEntityJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Mob mob && !event.getLevel().isClientSide()) {
            Holder<MobEffect> effect = ModEffects.REJUVENATION.getDelegate();
            mob.goalSelector.addGoal(0, new RejuvenationEffectGoal(mob, effect));
        }
    }
}
