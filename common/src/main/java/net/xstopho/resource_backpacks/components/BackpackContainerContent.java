package net.xstopho.resource_backpacks.components;

import com.google.common.collect.Iterables;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Stream;

public class BackpackContainerContent {
    private static final int NO_SLOT = -1;
    private static final int MAX_SIZE = 1250;
    public static final BackpackContainerContent EMPTY = new BackpackContainerContent(NonNullList.create());
    public static final Codec<BackpackContainerContent> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, BackpackContainerContent> STREAM_CODEC;
    private final NonNullList<ItemStack> items;
    private final int hashCode;

    private BackpackContainerContent(NonNullList<ItemStack> items) {
        if (items.size() > MAX_SIZE) {
            throw new IllegalArgumentException("Got " + items.size() + " items, but maximum is 1250");
        } else {
            this.items = items;
            this.hashCode = ItemStack.hashStackList(items);
        }
    }

    private BackpackContainerContent(int size) {
        this(NonNullList.withSize(size, ItemStack.EMPTY));
    }

    private BackpackContainerContent(List<ItemStack> items) {
        this(items.size());

        for(int i = 0; i < items.size(); ++i) {
            this.items.set(i, items.get(i));
        }

    }

    private static BackpackContainerContent fromSlots(List<BackpackContainerContent.Slot> slots) {
        OptionalInt optionalint = slots.stream().mapToInt(BackpackContainerContent.Slot::index).max();
        if (optionalint.isEmpty()) {
            return EMPTY;
        } else {
            BackpackContainerContent backpackContainerContents = new BackpackContainerContent(optionalint.getAsInt() + 1);

            for (Slot slot : slots) {
                backpackContainerContents.items.set(slot.index(), slot.item());
            }

            return backpackContainerContents;
        }
    }

    public static BackpackContainerContent fromItems(List<ItemStack> items) {
        int i = findLastNonEmptySlot(items);
        if (i == NO_SLOT) {
            return EMPTY;
        } else {
            BackpackContainerContent backpackContainerContents = new BackpackContainerContent(i + 1);

            for(int j = 0; j <= i; ++j) {
                backpackContainerContents.items.set(j, items.get(j).copy());
            }

            return backpackContainerContents;
        }
    }

    private static int findLastNonEmptySlot(List<ItemStack> items) {
        for(int i = items.size() - 1; i >= 0; --i) {
            if (!items.get(i).isEmpty()) {
                return i;
            }
        }

        return NO_SLOT;
    }

    private List<BackpackContainerContent.Slot> asSlots() {
        List<BackpackContainerContent.Slot> list = new ArrayList<>();

        for(int i = 0; i < this.items.size(); ++i) {
            ItemStack itemstack = this.items.get(i);
            if (!itemstack.isEmpty()) {
                list.add(new BackpackContainerContent.Slot(i, itemstack));
            }
        }

        return list;
    }

    public void copyInto(NonNullList<ItemStack> list) {
        for(int i = 0; i < list.size(); ++i) {
            ItemStack itemstack = i < this.items.size() ? this.items.get(i) : ItemStack.EMPTY;
            list.set(i, itemstack.copy());
        }

    }

    public ItemStack copyOne() {
        return this.items.isEmpty() ? ItemStack.EMPTY : this.items.getFirst().copy();
    }

    public Stream<ItemStack> stream() {
        return this.items.stream().map(ItemStack::copy);
    }

    public Stream<ItemStack> nonEmptyStream() {
        return this.items.stream().filter((p_331322_) -> !p_331322_.isEmpty()).map(ItemStack::copy);
    }

    public Iterable<ItemStack> nonEmptyItems() {
        return Iterables.filter(this.items, (p_331420_) -> !p_331420_.isEmpty());
    }

    public Iterable<ItemStack> nonEmptyItemsCopy() {
        return Iterables.transform(this.nonEmptyItems(), ItemStack::copy);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else {
            if (other instanceof BackpackContainerContent backpackContainerContent) {
                return ItemStack.listMatches(this.items, backpackContainerContent.items);
            }

            return false;
        }
    }

    public int hashCode() {
        return this.hashCode;
    }

    public NonNullList<ItemStack> getItems() {
        return items;
    }

    public int getSize() {
        return this.items.size();
    }

    static {
        CODEC = BackpackContainerContent.Slot.CODEC.sizeLimitedListOf(MAX_SIZE).xmap(BackpackContainerContent::fromSlots, BackpackContainerContent::asSlots);
        STREAM_CODEC = ItemStack.OPTIONAL_STREAM_CODEC.apply(ByteBufCodecs.list(MAX_SIZE)).map(BackpackContainerContent::new, (p_331691_) -> p_331691_.items);
    }

     record Slot(int index, ItemStack item) {
        public static final Codec<BackpackContainerContent.Slot> CODEC = RecordCodecBuilder.create((p_331695_) -> p_331695_.group(Codec.intRange(0, MAX_SIZE).fieldOf("slot").forGetter(Slot::index), ItemStack.CODEC.fieldOf("item").forGetter(Slot::item)).apply(p_331695_, Slot::new));
    }
}
