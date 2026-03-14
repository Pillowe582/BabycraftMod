package com.pillowe.babycraft.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.pillowe.babycraft.BabycraftMod;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BabycraftMod.MOD_ID);

    public static final DeferredItem<Item> BABY_BLOCK_ITEM = ITEMS.registerItem("golden_dandelioness",
            properties -> new Item(properties));

}
