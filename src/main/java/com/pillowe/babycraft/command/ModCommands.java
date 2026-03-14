package com.pillowe.babycraft.command;

import com.mojang.brigadier.CommandDispatcher;
import com.pillowe.babycraft.BabycraftMod;
import com.pillowe.babycraft.block.babyblock.BabyblockRenderer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;

@EventBusSubscriber(modid = BabycraftMod.MOD_ID)
public class ModCommands {
    @SubscribeEvent
    public static void onRegisterCilentCommands(RegisterClientCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(
                Commands.literal("babycraft")
                        .requires(Commands.hasPermission(
                                Commands.LEVEL_ALL))

                        .then(Commands.literal("showMeBabies")
                                .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                .then(Commands.literal("true")
                                        .executes(context -> {
                                            BabyblockRenderer.highlighted = true;
                                            net.minecraft.client.Minecraft.getInstance().levelRenderer.allChanged();
                                            return 1;
                                        }))
                                .then(Commands.literal("false")
                                        .executes(context -> {
                                            BabyblockRenderer.highlighted = false;
                                            net.minecraft.client.Minecraft.getInstance().levelRenderer.allChanged();
                                            return 1;
                                        }))));
    }
}
