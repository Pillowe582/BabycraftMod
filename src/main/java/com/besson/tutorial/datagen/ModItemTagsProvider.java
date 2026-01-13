package com.besson.tutorial.datagen;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.item.ModItems;
import com.besson.tutorial.tag.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, TutorialMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModItemTags.SUGAR_TAG)
                .add(Items.BEETROOT)
                .add(ModItems.STRAWBERRY.get());

        tag(ModItemTags.FIRE_ETHER_TOOL_MATERIALS)
                .add(ModItems.FIRE_ETHER.get());

        tag(ModItemTags.ICE_ETHER_ARMOR_MATERIALS)
                .add(ModItems.ICE_ETHER.get());
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.ICE_ETHER_HELMET.get())
                .add(ModItems.ICE_ETHER_CHESTPLATE.get())
                .add(ModItems.ICE_ETHER_LEGGINGS.get())
                .add(ModItems.ICE_ETHER_BOOTS.get());

        tag(ModItemTags.ICE_ETHER_ARMOR)
                .add(ModItems.ICE_ETHER_HELMET.get())
                .add(ModItems.ICE_ETHER_CHESTPLATE.get())
                .add(ModItems.ICE_ETHER_LEGGINGS.get())
                .add(ModItems.ICE_ETHER_BOOTS.get());
    }
}
