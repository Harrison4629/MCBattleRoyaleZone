package net.harrison.battleroyalezone.init;

import net.harrison.battleroyalezone.Battleroyalezone;
import net.harrison.battleroyalezone.networking.s2cpacket.AlarmSoundS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id () {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Battleroyalezone.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;


        registerC2SPackets(net);

        registerS2CPackets(net);
    }

    private static void registerS2CPackets(SimpleChannel net) {
        net.messageBuilder(AlarmSoundS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(AlarmSoundS2CPacket::new)
                .encoder(AlarmSoundS2CPacket::toBytes)
                .consumerMainThread(AlarmSoundS2CPacket::handle)
                .add();

    }

    private static void registerC2SPackets(SimpleChannel net) {
    }

    public static <MSG> void sendToServer(MSG msg) {
        INSTANCE.sendToServer(msg);
    }

    public static <MSG> void sendToPlayer(MSG msg, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), msg );
    }
}
