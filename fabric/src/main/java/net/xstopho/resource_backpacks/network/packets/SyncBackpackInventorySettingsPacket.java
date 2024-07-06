package net.xstopho.resource_backpacks.network.packets;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.item.util.BackpackLevel;

public record SyncBackpackInventorySettingsPacket(String name, int rows, int columns) implements CustomPacketPayload{
    public static final CustomPacketPayload.Type<SyncBackpackInventorySettingsPacket> PACKET_TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, "sync_backpack_inventory_settings"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SyncBackpackInventorySettingsPacket> PACKET_CODEC;

    public static void apply(SyncBackpackInventorySettingsPacket packet, ClientPlayNetworking.Context context) {
        BackpackConstants.LOG.info("Receiving Backpack Settings from Server");
        context.client().execute(() -> {
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
        PACKET_CODEC = StreamCodec.composite(ByteBufCodecs.STRING_UTF8, SyncBackpackInventorySettingsPacket::name, ByteBufCodecs.INT, SyncBackpackInventorySettingsPacket::rows, ByteBufCodecs.INT, SyncBackpackInventorySettingsPacket::columns, SyncBackpackInventorySettingsPacket::new);
    }
}
