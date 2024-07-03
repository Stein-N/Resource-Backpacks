package net.xstopho.resource_backpacks.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.config.BackpackConfig;
import net.xstopho.resource_backpacks.item.BackpackItem;
import org.jetbrains.annotations.NotNull;

public record OpenBackpackPacket(int id) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<OpenBackpackPacket> PACKET_TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, "open_backpack_packet"));
    public static final StreamCodec<RegistryFriendlyByteBuf, OpenBackpackPacket> PACKET_CODEC;

    public static void apply(OpenBackpackPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player instanceof ServerPlayer serverPlayer) {
                Inventory inventory = serverPlayer.getInventory();
                ItemStack chestStack = inventory.getArmor(EquipmentSlot.CHEST.getIndex());

                if (BackpackConfig.CHESTSLOT_KEYBIND.get()) {
                    if (chestStack.getItem() instanceof BackpackItem backpack) {
                        serverPlayer.openMenu(backpack.getMenuProvider(chestStack));
                        return;
                    }
                }

                if (BackpackConfig.OPEN_BACKPACK_FROM_INVENTORY.get()) {
                    for (ItemStack stack : inventory.items) {
                        if (stack.getItem() instanceof BackpackItem backpackItem) {
                            serverPlayer.openMenu(backpackItem.getMenuProvider(stack));
                            return;
                        }
                    }
                }
            }
        });
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return PACKET_TYPE;
    }

    static {
        PACKET_CODEC = StreamCodec.composite(ByteBufCodecs.INT, OpenBackpackPacket::id, OpenBackpackPacket::new);
    }
}
