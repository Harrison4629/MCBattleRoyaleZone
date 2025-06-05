package net.harrison.battleroyalezone.events.customEvents;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.eventbus.api.Event;

public class ZoneStageEvent extends Event {
    private final MinecraftServer server;
    private final double centerX;
    private final double centerZ;
    private final ZoneStateEnum state;
    private final int stage;
    private final int StateLeftTicks;
    private boolean running;

    public ZoneStageEvent(MinecraftServer server, boolean running, double centerX, double centerZ,
                          int stage, ZoneStateEnum state, int StateLeftTicks) {
        this.running = running;
        this.centerX = centerX;
        this.centerZ = centerZ;
        this.stage = stage;
        this.state = state;
        this.StateLeftTicks = StateLeftTicks;
        this.server = server;
    }

    public MinecraftServer getServer() {
        return this.server;
    }

    public double getCenterX() {
        return this.centerX;
    }

    public double getCenterZ() {
        return this.centerZ;
    }

    public ZoneStateEnum getState() {
        return this.state;
    }

    public int getStage() {
        return this.stage;
    }

    public int getStateLeftTicks() {
        return this.StateLeftTicks;
    }

    public boolean getRunningState() {
        return this.running;
    }
}
