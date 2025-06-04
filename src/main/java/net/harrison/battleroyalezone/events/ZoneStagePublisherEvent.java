package net.harrison.battleroyalezone.events;

import net.harrison.battleroyalezone.Battleroyalezone;
import net.harrison.battleroyalezone.config.ZoneConfig;
import net.harrison.battleroyalezone.events.customEvents.ZoneStageEvent;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = Battleroyalezone.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ZoneStagePublisherEvent {
    //private static final ZoneStagePublisherEvent INSTANCE = new ZoneStagePublisherEvent();

    public static double zoneCenterX;
    public static double zoneCenterZ;
    public static int stage;
    public static boolean isRunning = false;

    private static int IDLELeftTicks;
    private static int WARNINGLeftTicks;
    private static int SHRINKINGLeftTicks;


    //private ZoneStagePublisherEvent() {
    //}
//
    //public static ZoneStagePublisherEvent getInstance() {
    //    return INSTANCE;
    //}



    public enum ZoneState {
        IDLE,           // 空闲状态
        WARNING,        // 警告阶段
        SHRINKING,      // 缩圈阶段
    }

    private static ZoneState currentState = ZoneState.IDLE;

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (!isRunning){
            return;
        }



        if (stage < ZoneConfig.getMaxStage()) {

            switch (currentState) {
                case WARNING:
                    handleWARNINGTicks(event);
                    break;
                case SHRINKING:
                    handleSHRINKINGTicks(event);
                    break;
                case IDLE:
                    handleIDLETicks(event);
                    break;
                default:
                    break;
            }
        } else {
            handleZoneStageOver();
        }

    }

    private static void handleZoneStageOver() {
        isRunning = false;
        ZoneStagePublisherEvent.stage = 0;
    }

    private static void handleIDLETicks(TickEvent.ServerTickEvent event) {
        if (IDLELeftTicks > 0 ) {
            IDLELeftTicks--;

            if (IDLELeftTicks %20 == 0) {
                MinecraftForge.EVENT_BUS.post(new ZoneStageEvent(zoneCenterX, zoneCenterZ, stage, currentState));
            }
        } else {
            currentState = ZoneState.WARNING;
            IDLELeftTicks = (int) ((Math.random() * 10) + 11) * 20;
        }
    }

    private static void handleWARNINGTicks(TickEvent.ServerTickEvent event) {
        if (WARNINGLeftTicks > 0 ) {
            WARNINGLeftTicks--;

            if (WARNINGLeftTicks %20 == 0) {
                MinecraftForge.EVENT_BUS.post(new ZoneStageEvent(zoneCenterX, zoneCenterZ, stage, currentState));
            }
        } else {
            currentState = ZoneState.SHRINKING;
            WARNINGLeftTicks = ZoneConfig.getWarningTick(stage + 1);

        }
    }

    private static void handleSHRINKINGTicks(TickEvent.ServerTickEvent event) {
        if (SHRINKINGLeftTicks > 0) {
            SHRINKINGLeftTicks--;

            if (SHRINKINGLeftTicks % 20 == 0) {
                MinecraftForge.EVENT_BUS.post(new ZoneStageEvent(zoneCenterX, zoneCenterZ, stage, currentState));
            }
        } else {
            currentState = ZoneState.IDLE;
            SHRINKINGLeftTicks = ZoneConfig.getShrinkTick(stage + 1);
            stage++;
        }
    }

    public static void startZoneSystem(CommandSourceStack source) {
        zoneCenterX = source.getPosition().x;
        zoneCenterZ = source.getPosition().z;
        ZoneStagePublisherEvent.stage = 0;


        WARNINGLeftTicks = ZoneConfig.getWarningTick(stage);

        currentState = ZoneState.WARNING;
        isRunning = true;
    }

    public static void stopZoneSystem() {
        handleZoneStageOver();
    }



}
