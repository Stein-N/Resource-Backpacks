package net.xstopho.resource_backpacks.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.xstopho.resource_backpacks.components.BackpackContainerContent;
import net.xstopho.resource_backpacks.item.util.BackpackInventory;
import net.xstopho.resource_backpacks.item.util.BackpackLevel;
import net.xstopho.resource_backpacks.registries.BlockEntityTypeRegistry;
import net.xstopho.resource_backpacks.registries.DataComponentsRegistry;
import net.xstopho.resource_backpacks.rendering.container.BackpackContainer;

public class BackpackBlockEntity extends BaseContainerBlockEntity {

    private Component component;
    private BackpackLevel level;

    private NonNullList<ItemStack> items;

    public BackpackBlockEntity(BackpackLevel level, Component component, BackpackContainerContent content, BlockPos pos, BlockState state) {
        this(pos, state);
        this.level = level;
        this.component = component;
        this.items = NonNullList.withSize(content.getSize(), ItemStack.EMPTY);
        setComponents(DataComponentMap.builder().set(DataComponentsRegistry.BACKPACK_CONTAINER.get(), content).build());
    }

    public BackpackBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityTypeRegistry.BACKPACK_BLOCK_ENTITY.get(), pos, blockState);
    }

    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return switch(level) {
            case LEATHER -> BackpackContainer.leatherContainer(i, inventory, new BackpackInventory(this, level.getRows() * level.getColumns()));
            case COPPER -> BackpackContainer.copperContainer(i, inventory, new BackpackInventory(this, level.getRows() * level.getColumns()));
            case GOLD -> BackpackContainer.goldContainer(i, inventory, new BackpackInventory(this, level.getRows() * level.getColumns()));
            case IRON -> BackpackContainer.ironContainer(i, inventory, new BackpackInventory(this, level.getRows() * level.getColumns()));
            case DIAMOND -> BackpackContainer.diamondContainer(i, inventory, new BackpackInventory(this, level.getRows() * level.getColumns()));
            case NETHERITE -> BackpackContainer.netheriteContainer(i, inventory, new BackpackInventory(this, level.getRows() * level.getColumns()));
            case ENDER -> BackpackContainer.enderContainer(i, inventory, inventory.player.getEnderChestInventory());
        };
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.loadWithComponents(tag, registries);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
    }

    @Override
    protected Component getDefaultName() {
        return component;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return components().get(DataComponentsRegistry.BACKPACK_CONTAINER.get()).getItems();
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.items.addAll(nonNullList);
    }

    @Override
    public int getContainerSize() {
        return items.size();
    }
}
