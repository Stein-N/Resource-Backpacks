package net.xstopho.resource_backpacks.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.xstopho.resource_backpacks.item.BackpackItem;

import java.time.chrono.IsoEra;

public class OpenBackpackPacket {

    private final int id;

    public OpenBackpackPacket(int id) {
        this.id = id;
    }

    public static OpenBackpackPacket decode(FriendlyByteBuf buf) {
        return new OpenBackpackPacket(buf.readInt());
    }

    public static void encode(OpenBackpackPacket packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.id);
    }

    public static void handle(OpenBackpackPacket packet, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            ItemStack chestStack = player.getInventory().getArmor(EquipmentSlot.CHEST.getIndex());

            if (chestStack.getItem() instanceof BackpackItem backpackItem) {
                player.openMenu(backpackItem.getMenuProvider(chestStack));
                return;
            }

            for (ItemStack stack : player.getInventory().items) {
                if (stack.getItem() instanceof BackpackItem backpackItem) {
                    player.openMenu(backpackItem.getMenuProvider(stack));
                }
            }
        });
        context.setPacketHandled(true);
    }
}
