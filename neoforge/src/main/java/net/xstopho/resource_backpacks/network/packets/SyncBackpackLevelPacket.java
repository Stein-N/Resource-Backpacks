package net.xstopho.resource_backpacks.network.packets;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.item.util.BackpackLevel;

public record SyncBackpackLevelPacket(String name, int rows, int columns) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SyncBackpackLevelPacket> PACKET_TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, "sync_backpack_level_packet"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SyncBackpackLevelPacket> PACKET_CODEC;

    public static void apply(SyncBackpackLevelPacket packet, IPayloadContext context) {
        BackpackConstants.LOG.info("Receiving Backpack Settings from Server");
        context.enqueueWork(() -> {
            for (BackpackLevel level : BackpackLevel.values()) {
                if (level.getName().equalsIgnoreCase(packet.name())) {
                    level.setRows(packet.rows());
                    level.setColumns(packet.columns());
                }
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return PACKET_TYPE;
    }

    static {
        PACKET_CODEC = StreamCodec.composite(ByteBufCodecs.STRING_UTF8, SyncBackpackLevelPacket::name, ByteBufCodecs.INT, SyncBackpackLevelPacket::rows, ByteBufCodecs.INT, SyncBackpackLevelPacket::columns, SyncBackpackLevelPacket::new);
    }
}