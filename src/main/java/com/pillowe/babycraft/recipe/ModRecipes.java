package com.pillowe.babycraft.recipe;

import com.pillowe.babycraft.BabycraftMod;
import com.pillowe.babycraft.recipe.golden_dandelioness.GoldenDandelionessRecipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(
            Registries.RECIPE_SERIALIZER,
            BabycraftMod.MOD_ID);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<GoldenDandelionessRecipe>> GOLDEN_DANDELIONESS = RECIPES
            .register(
                    "golden_dandelioness",
                    () -> GoldenDandelionessRecipe.SERIALIZER);
}
