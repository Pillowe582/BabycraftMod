package com.besson.tutorial.item;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import com.besson.tutorial.item.custom.ModFuelItem;
import com.besson.tutorial.item.custom.PickaxeAxeItem;
import com.besson.tutorial.item.custom.ProspectorItem;
import com.besson.tutorial.tag.ModBlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TutorialMod.MOD_ID);

    public static final DeferredItem<Item> ICE_ETHER = ITEMS.registerSimpleItem("ice_ether");
    public static final DeferredItem<Item> RAW_ICE_ETHER = ITEMS.registerSimpleItem("raw_ice_ether");
    public static final DeferredItem<Item> CARDBOARD = ITEMS.registerSimpleItem("material/cardboard");

    public static final DeferredItem<Item> CORN = ITEMS.registerItem("corn",
            p -> new BlockItem(ModBlocks.CORN_CROP.get(), p.food(ModFoods.CORN).useItemDescriptionPrefix()));

    public static final DeferredItem<Item> STRAWBERRY = ITEMS.registerSimpleItem("strawberry",
            () -> new Item.Properties().food(ModFoods.STRAWBERRY, ModConsumables.STRAWBERRY));
    public static final DeferredItem<Item> CHEESE = ITEMS.registerSimpleItem("cheese",
            () -> new Item.Properties().food(ModFoods.CHEESE, ModConsumables.CHEESE));

    public static final DeferredItem<Item> ANTHRACITE = ITEMS.registerItem("anthracite", p -> new ModFuelItem(p, 1600));
    public static final DeferredItem<Item> ANTHRACITE2 = ITEMS.registerSimpleItem("anthracite2");

    public static final DeferredItem<Item> PROSPECTOR = ITEMS.registerItem("prospector",
            p -> new ProspectorItem(p.durability(127)));

    public static final DeferredItem<Item> FIRE_ETHER = ITEMS.registerSimpleItem("fire_ether");
    public static final DeferredItem<Item> FIRE_ETHER_SWORD = ITEMS.registerItem("fire_ether_sword",
            p -> new Item(p.sword(ModToolMaterials.FIRE_ETHER, 3, -2.4F)));
    public static final DeferredItem<Item> FIRE_ETHER_PICKAXE = ITEMS.registerItem("fire_ether_pickaxe",
            p -> new Item(p.pickaxe(ModToolMaterials.FIRE_ETHER, 1.0F, -2.8F)));
    public static final DeferredItem<Item> FIRE_ETHER_SHOVEL = ITEMS.registerItem("fire_ether_shovel",
            p -> new ShovelItem(ModToolMaterials.FIRE_ETHER, 1.5F, -3.0F, p));
    public static final DeferredItem<Item> FIRE_ETHER_AXE = ITEMS.registerItem("fire_ether_axe",
            p -> new AxeItem(ModToolMaterials.FIRE_ETHER, 5.0F, -3.0F, p));
    public static final DeferredItem<Item> FIRE_ETHER_HOE = ITEMS.registerItem("fire_ether_hoe",
            p -> new HoeItem(ModToolMaterials.FIRE_ETHER, -2, 0.0F, p));

    public static final DeferredItem<Item> PICKAXE_AXE_ITEM = ITEMS.registerItem("pickaxe_axe_item",
            p -> new Item(p.tool(ModToolMaterials.FIRE_ETHER, ModBlockTags.PICKAXE_AXE_MINEABLE, 5.0f, -2.4f, 0.0f)));
    public static final DeferredItem<Item> PICKAXE_AXE_ITEM2 = ITEMS.registerItem("pickaxe_axe_item2",
            p -> new PickaxeAxeItem(ModToolMaterials.FIRE_ETHER, 5.0F, -2.4F, p));

    public static final DeferredItem<Item> ICE_ETHER_HELMET = ITEMS.registerItem("ice_ether_helmet",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ICE_ETHER, ArmorType.HELMET)));
    public static final DeferredItem<Item> ICE_ETHER_CHESTPLATE = ITEMS.registerItem("ice_ether_chestplate",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ICE_ETHER, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> ICE_ETHER_LEGGINGS = ITEMS.registerItem("ice_ether_leggings",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ICE_ETHER, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> ICE_ETHER_BOOTS = ITEMS.registerItem("ice_ether_boots",
            p -> new Item(p.humanoidArmor(ModArmorMaterials.ICE_ETHER, ArmorType.BOOTS)));

    public static final DeferredItem<Item> STRAWBERRY_SEEDS = ITEMS.registerItem("strawberry_seeds",
            p -> new BlockItem(ModBlocks.STRAWBERRY_CROP.get(), p.useItemDescriptionPrefix()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);
    }
}
