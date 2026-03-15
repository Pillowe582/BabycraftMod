package com.pillowe.babycraft.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.pillowe.babycraft.BabycraftMod;
import com.pillowe.babycraft.item.golden_dandelioness.GoldenDandelionessItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BabycraftMod.MOD_ID);

    public static final DeferredItem<Item> GOLDEN_DANDELIONESS = ITEMS.registerItem("golden_dandelioness",
            GoldenDandelionessItem::new);

}
