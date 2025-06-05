package net.harrison.battleroyalezone.networking.s2cpacket;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class AlarmSoundS2CPacket {
    private final SoundEvent soundType;
    private final Float volume;
    private final Float pitch;

    public AlarmSoundS2CPacket(SoundEvent soundType, Float volume, Float pitch) {
        this.soundType = soundType;
        this.volume = volume;
        this.pitch = pitch;
    }

    public AlarmSoundS2CPacket(FriendlyByteBuf buf) {
        ResourceLocation id = buf.readResourceLocation();
        this.soundType = BuiltInRegistries.SOUND_EVENT.get(id);
        this.volume = buf.readFloat();
        this.pitch = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        ResourceLocation id = BuiltInRegistries.SOUND_EVENT.getKey(this.soundType);
        buf.writeResourceLocation(id);
        buf.writeFloat(this.volume);
        buf.writeFloat(this.pitch);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null) {
                mc.player.playSound(this.soundType, this.volume, this.pitch);
            }
        });
    }
}
