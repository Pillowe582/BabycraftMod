package com.pillowe.babycraft;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = BabycraftMod.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = BabycraftMod.MOD_ID, value = Dist.CLIENT)
public class BabycraftCilent {
    public BabycraftCilent(ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

}
