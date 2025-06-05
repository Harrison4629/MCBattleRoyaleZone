package net.harrison.battleroyalezone;

import net.harrison.battleroyalezone.events.ZoneStagePublisherEvent;
import net.harrison.battleroyalezone.init.ModCommands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Battleroyalezone.MODID)
public class Battleroyalezone {

    private static final String SCOREBOARD_OBJECTIVE_NAME = "BattleroyaleZone";
    private static final Component SCOREBOARD_DISPLAY_NAME = Component.literal("§eZone Status");

    public static final String MODID = "battleroyalezone";

    public Battleroyalezone() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();





        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
    }


    @SubscribeEvent
    public void onServerStarting(ServerStartedEvent event) {
        MinecraftServer server = event.getServer();

        Scoreboard scoreboard = server.getScoreboard();
        Objective zoneObjective = scoreboard.getObjective(SCOREBOARD_OBJECTIVE_NAME);

        if (zoneObjective == null) {
            scoreboard.addObjective(
                    SCOREBOARD_OBJECTIVE_NAME,
                    ObjectiveCriteria.DUMMY,
                    SCOREBOARD_DISPLAY_NAME,
                    ObjectiveCriteria.RenderType.INTEGER
            );
        }
    }

    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event) {
        ZoneStagePublisherEvent.stopZoneSystem();
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        ModCommands.register(event.getDispatcher());
    }

}
