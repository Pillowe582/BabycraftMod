package com.pillowe.babycraft.event;

import com.pillowe.babycraft.BabycraftMod;
import com.pillowe.babycraft.Config;
import com.pillowe.babycraft.effect.ModEffects;
import com.pillowe.babycraft.item.ModItems;
import com.pillowe.babycraft.potion.ModPotions;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownSplashPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = BabycraftMod.MOD_ID)
public class ServerEvents {
    @SubscribeEvent
    public static void checkMinimize(LivingIncomingDamageEvent event) {
        Level level = event.getEntity().level();
        if (level.isClientSide()) {
            return;
        }
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            if (!attacker.hasEffect(ModEffects.MINIMIZE_EFFECT.getDelegate())) {
                return;
            }
            int amplifier = attacker.getEffect(ModEffects.MINIMIZE_EFFECT.getDelegate()).getAmplifier();
            if (level.getRandom().nextDouble() < Config.MINIMIZE_BASE_CHANCE.get()
                    + Config.MINIMIZE_LEVEL_CHANCE.get()
                            * amplifier) {
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

    @SubscribeEvent
    public static void onPotionSpawn(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof ThrownSplashPotion potion)) {
            return;
        }
        if (!(potion.getOwner() instanceof Witch)) {
            return;
        }
        PotionContents potionContents = potion.getItem().get(DataComponents.POTION_CONTENTS);
        if (potionContents == null || potionContents.potion().isEmpty()) {

            return;
        }
        if (potionContents.potion().get() != Potions.HEALING.getDelegate()
                && potionContents.potion().get() != Potions.REGENERATION.getDelegate()) {
            return;
        }

        if (potion.level().getRandom().nextDouble() >= 0.3D) {
            return;
        }

        double random = potion.level().getRandom().nextDouble();
        PotionContents newContents;
        if (random < 0.60D) {
            newContents = new PotionContents(ModPotions.MINIMIZE_I);
        } else if (random < 0.8D) {
            newContents = new PotionContents(ModPotions.MINIMIZE_II);
        } else if (random < 0.9D) {
            newContents = new PotionContents(ModPotions.MINIMIZE_III);
        } else if (random < 0.95D) {
            newContents = new PotionContents(ModPotions.MINIMIZE_IV);
        } else {
            newContents = new PotionContents(ModPotions.MINIMIZE_V);
        }

        ItemStack newPotion = new ItemStack(Items.SPLASH_POTION);
        newPotion.set(DataComponents.POTION_CONTENTS, newContents);
        potion.setItem(newPotion);
    }
}
