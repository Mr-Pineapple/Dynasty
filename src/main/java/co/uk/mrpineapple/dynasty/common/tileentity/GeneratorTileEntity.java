package co.uk.mrpineapple.dynasty.common.tileentity;

import co.uk.mrpineapple.dynasty.common.util.InventoryUtil;
import co.uk.mrpineapple.dynasty.common.util.TileEntityUtil;
import co.uk.mrpineapple.dynasty.core.registry.TileEntityRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;

import javax.annotation.Nullable;

public class GeneratorTileEntity extends BaseTileEntity implements IClearable, ITickableTileEntity, ISidedInventory {
    public static final int[] FUEL_SLOTS = new int[]{0,1,2,3,4};
    private final NonNullList<ItemStack> generator = NonNullList.withSize(5, ItemStack.EMPTY);
    private boolean hasPower;
    private int generatorTime;
    private int generatorTotalTime;

    public GeneratorTileEntity() {
        super(TileEntityRegistry.GENERATOR.get());
    }

    public boolean addItem(ItemStack stack) {
        for(int i = 0; i < this.generator.size(); i++) {
            if(this.generator.get(i).isEmpty()) {
                ItemStack fuel = stack.copy();
                fuel.setCount(1);
                this.generator.set(i, fuel);

                CompoundNBT compound = new CompoundNBT();
                this.writeGeneratorItem(compound);
                TileEntityUtil.sendUpdatePacket(this, super.save(compound));

                return true;
            }
        }
        return false;
    }

    private void resetPosition(int generatorTime) {
        this.generatorTime = 0;
        this.generatorTotalTime = generatorTime;

        CompoundNBT compound = new CompoundNBT();
        this.writeGeneratorItem(compound);
        this.writeGeneratorTime(compound);
        this.writeGeneratorTotalTime(compound);
        TileEntityUtil.sendUpdatePacket(this, super.save(compound));
    }

    @Override
    public void clearContent() {
        this.generator.clear();
    }


    @Override
    public int[] getSlotsForFace(Direction direction) {
        return FUEL_SLOTS;
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int p_180461_1_, ItemStack p_180461_2_, Direction p_180461_3_) {
        return false;
    }

    @Override
    public int getContainerSize() {
        return this.generator.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack stack : this.generator) {
            if(!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.generator.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ItemStackHelper.removeItem(this.generator, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ItemStackHelper.takeItem(this.generator, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        NonNullList<ItemStack> inventory = this.generator;
        inventory.set(index, stack);
        if(stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

        /* Send updates to client */
        CompoundNBT compound = new CompoundNBT();
        this.writeGeneratorItem(compound);
        TileEntityUtil.sendUpdatePacket(this, super.save(compound));
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return this.level.getBlockEntity(this.worldPosition) == this && player.distanceToSqr(this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 0.5, this.worldPosition.getZ() + 0.5) <= 64;
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        this.writeGeneratorItem(compound);
        return super.save(compound);
    }

    private CompoundNBT writeGeneratorItem(CompoundNBT compound) {
        InventoryUtil.saveAllItems("Generator", compound, this.generator);
        return compound;
    }
    private CompoundNBT writeGeneratorTime(CompoundNBT compound) {
        compound.putInt("GeneratorTimes", this.generatorTime);
        return compound;
    }
    private CompoundNBT writeGeneratorTotalTime(CompoundNBT compound) {
        compound.putInt("GeneratorTotalTimes", this.generatorTotalTime);
        return compound;
    }




















    @Override
    public void tick() {

    }
}
