package net.harrison.battleroyalezone.events.customEvents;

import net.harrison.battleroyalezone.events.ZoneStagePublisherEvent;
import net.minecraftforge.eventbus.api.Event;

public class ZoneStageEvent extends Event {
    private final double centerX;
    private final double centerZ;
    private final ZoneStagePublisherEvent.ZoneState state;
    private final int stage;

    public ZoneStageEvent(double centerX, double centerZ, int stage, ZoneStagePublisherEvent.ZoneState state) {
        this.centerX = centerX;
        this.centerZ = centerZ;
        this.stage = stage;
        this.state = state;
    }

    public double getCenterX() {
        return this.centerX;
    }

    public double getCenterZ() {
        return this.centerZ;
    }

    public ZoneStagePublisherEvent.ZoneState getState() {
        return this.state;
    }

    public int getStage() {
        return this.stage;
    }
}
