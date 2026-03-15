package com.pillowe.babycraft.recipe.golden_dandelioness;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;

import com.mojang.serialization.MapCodec;
import com.pillowe.babycraft.item.ModItems;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;

public class GoldenDandelionessRecipe extends CustomRecipe {

    public static final MapCodec<GoldenDandelionessRecipe> MAP_CODEC = MapCodec.unit(GoldenDandelionessRecipe::new);
    public static final StreamCodec<RegistryFriendlyByteBuf, GoldenDandelionessRecipe> STREAM_CODEC = StreamCodec
            .unit(new GoldenDandelionessRecipe());
    public static final RecipeSerializer<GoldenDandelionessRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC,
            STREAM_CODEC);

    public GoldenDandelionessRecipe() {
    }

    public boolean matches(CraftingInput input, Level level) {

        boolean hasDandelion = false;
        boolean hasCoal = false;
        boolean hasPowder = false;
        boolean hasWaterBucket = false;
        boolean hasLightning = false;
        boolean hasSpiderEye = false;
        boolean hasFlower = false;
        boolean hasSlowPotion = false;
        boolean hasFurnace = false;
        Holder<Enchantment> channeling = level.registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(Enchantments.CHANNELING);

        for (int i = 0; i < input.size(); i++) {

            ItemStack stack = input.getItem(i);

            if (stack.isEmpty())
                continue;

            Item item = stack.getItem();

            if (item == Items.GOLDEN_DANDELION) {
                hasDandelion = true;
            }

            else if (item == Items.COAL) {
                hasCoal = true;
            }

            else if (item == Items.BLAZE_POWDER) {
                hasPowder = true;
            }

            else if (item == Items.WATER_BUCKET) {
                hasWaterBucket = true;
            }

            else if (item == Items.FERMENTED_SPIDER_EYE) {
                hasSpiderEye = true;
            }

            else if (item == Items.BLAST_FURNACE) {
                hasFurnace = true;
            }

            else if (stack.is(ItemTags.FLOWERS)) {
                hasFlower = true;
            }

            // Check if this book of item has channeling
            else if (EnchantmentHelper.getTagEnchantmentLevel(
                    level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT)
                            .getOrThrow(Enchantments.CHANNELING),
                    stack) > 0
                    ||
                    stack.getOrDefault(DataComponents.STORED_ENCHANTMENTS, ItemEnchantments.EMPTY)
                            .getLevel(channeling) > 0) {
                hasLightning = true;
            }

            // Check if this potion is slowness
            else if (item == Items.POTION) {

                PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);

                if (contents != null && contents.potion().get() == Potions.SLOWNESS) {
                    hasSlowPotion = true;
                }
            }

            else {
                return false;
            }
        }

        return hasDandelion &&
                hasCoal &&
                hasPowder &&
                hasWaterBucket &&
                hasLightning &&
                hasSpiderEye &&
                hasFlower &&
                hasSlowPotion &&
                hasFurnace;
    }

    public ItemStack assemble(CraftingInput input) {
        return new ItemStack(ModItems.GOLDEN_DANDELIONESS.get());
    }

    public RecipeSerializer<GoldenDandelionessRecipe> getSerializer() {
        return SERIALIZER;
    }

}
