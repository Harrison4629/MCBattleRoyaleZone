package net.harrison.battleroyalezone;

import net.harrison.battleroyalezone.events.ZoneStagePublisherEvent;
import net.harrison.battleroyalezone.init.ModCommands;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Battleroyalezone.MODID)
public class Battleroyalezone {

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
    public void onServerStarting(ServerStartingEvent event) {
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
