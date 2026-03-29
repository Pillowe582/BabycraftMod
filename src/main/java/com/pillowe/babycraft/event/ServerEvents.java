package com.pillowe.babycraft.event;

import com.pillowe.babycraft.BabycraftMod;
import com.pillowe.babycraft.Config;
import com.pillowe.babycraft.effect.ModEffects;
import com.pillowe.babycraft.item.ModItems;
import com.pillowe.babycraft.potion.ModPotions;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
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

    @SubscribeEvent
    public static void addPotionRecipes(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.AWKWARD, Items.GOLDEN_DANDELION, ModPotions.REJUVENATION);
        builder.addMix(ModPotions.REJUVENATION, Items.REDSTONE, ModPotions.REJUVENATION_LONG);
        builder.addMix(Potions.AWKWARD, ModItems.GOLDEN_DANDELIONESS.get(), ModPotions.MINIMIZE_I);
        builder.addMix(ModPotions.MINIMIZE_I, Items.GLOWSTONE_DUST, ModPotions.MINIMIZE_II);
        builder.addMix(ModPotions.MINIMIZE_II, Items.GLOWSTONE_DUST, ModPotions.MINIMIZE_III);
        builder.addMix(ModPotions.MINIMIZE_III, Items.GLOWSTONE_DUST, ModPotions.MINIMIZE_IV);
        builder.addMix(ModPotions.MINIMIZE_IV, Items.GLOWSTONE_DUST, ModPotions.MINIMIZE_V);
        builder.addMix(ModPotions.MINIMIZE_I, Items.REDSTONE, ModPotions.MINIMIZE_I_LONG);
        builder.addMix(ModPotions.MINIMIZE_II, Items.REDSTONE, ModPotions.MINIMIZE_II_LONG);
        builder.addMix(ModPotions.MINIMIZE_III, Items.REDSTONE, ModPotions.MINIMIZE_III_LONG);
        builder.addMix(ModPotions.MINIMIZE_IV, Items.REDSTONE, ModPotions.MINIMIZE_IV_LONG);
        builder.addMix(ModPotions.MINIMIZE_V, Items.REDSTONE, ModPotions.MINIMIZE_V_LONG);
        builder.addMix(ModPotions.MINIMIZE_I_LONG, Items.GLOWSTONE_DUST, ModPotions.MINIMIZE_II_LONG);
        builder.addMix(ModPotions.MINIMIZE_II_LONG, Items.GLOWSTONE_DUST, ModPotions.MINIMIZE_III_LONG);
        builder.addMix(ModPotions.MINIMIZE_III_LONG, Items.GLOWSTONE_DUST, ModPotions.MINIMIZE_IV_LONG);
        builder.addMix(ModPotions.MINIMIZE_IV_LONG, Items.GLOWSTONE_DUST, ModPotions.MINIMIZE_V_LONG);

    }

}
