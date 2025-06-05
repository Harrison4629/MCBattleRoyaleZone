package net.harrison.battleroyalezone.networking.s2cpacket;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

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
        this.soundType = ForgeRegistries.SOUND_EVENTS.getValue(id);
        this.volume = buf.readFloat();
        this.pitch = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        ResourceLocation id = ForgeRegistries.SOUND_EVENTS.getKey(this.soundType);
        if (id != null) {
            buf.writeResourceLocation(id);
        }
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
