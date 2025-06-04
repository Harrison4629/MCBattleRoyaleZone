package net.harrison.battleroyalezone.init;

import com.mojang.brigadier.CommandDispatcher;
import net.harrison.battleroyalezone.config.ZoneConfig;
import net.harrison.battleroyalezone.events.ZoneStagePublisherEvent;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class ModCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("brzone")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("start")
                        .executes(context -> {
                            ZoneStagePublisherEvent.startZoneSystem(context.getSource());
                    return 1;
                    })
                )

                .then(Commands.literal("info")
                        .executes(context -> {
                            context.getSource().sendSuccess(Component.literal(ZoneConfig.getInfo()), true);
                            return 1;
                        })
                )

                .then(Commands.literal("stop")
                        .executes(context -> {
                            ZoneStagePublisherEvent.stopZoneSystem();
                            return 1;
                        })
                )

        );











    }
}
