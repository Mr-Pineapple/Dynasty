package co.uk.mrpineapple.dynasty.common.tileentity;

import co.uk.mrpineapple.dynasty.common.util.InventoryUtil;
import co.uk.mrpineapple.dynasty.common.util.TileEntityUtil;
import co.uk.mrpineapple.dynasty.core.registry.ItemRegistry;
import co.uk.mrpineapple.dynasty.core.registry.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class CoinPressTileEntity extends BaseTileEntity implements IClearable, ITickableTileEntity, ISidedInventory {
    public static final int[] PRESS_SLOT = new int[]{0};
    private final NonNullList<ItemStack> press = NonNullList.withSize(1, ItemStack.EMPTY);
    private int pressTime;
    private int pressTotalTime;

    public CoinPressTileEntity() {
        super(TileEntityRegistry.COIN_PRESS.get());
    }

    public void removeItem(PlayerEntity player) {
        if(!this.press.get(0).isEmpty()) {
            double posX = worldPosition.getX() + 0.3 + 0.4;
            double posY = worldPosition.getY() + 1.0;
            double posZ = worldPosition.getZ() + 0.3 + 0.4;

            ItemStack stack = this.press.get(0).copy();
            if(player.inventory.getFreeSlot() != -1) {
                player.inventory.add(stack);
            } else if(player.inventory.contains(stack)) {
                player.inventory.add(stack);
            } else {
                level.addFreshEntity(new ItemEntity(level, worldPosition.getX() + 0.5, worldPosition.getY() + 1.125, worldPosition.getZ() + 0.5, stack));
            }

            this.press.set(0, ItemStack.EMPTY);

            CompoundNBT compound = new CompoundNBT();
            this.writePressItem(compound);
            TileEntityUtil.sendUpdatePacket(this, super.save(compound));
        }
    }

    public boolean addItem(ItemStack stack, int position, int pressTime) {
        if(this.press.get(position).isEmpty()) {
            ItemStack copy = stack.copy();
            copy.setCount(1);
            this.press.set(position, copy);
            this.resetPosition(pressTime);

            World world = this.getLevel();
            if(world != null) {
                level.playSound(null, this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 1.0, this.worldPosition.getZ() + 0.5, SoundEvents.ANVIL_BREAK, SoundCategory.BLOCKS, 0.75F, world.random.nextFloat() * 0.2F + 0.9F);
            }

            return true;
        }
        return false;
    }

    private void resetPosition(int cookTime) {
        this.pressTime = 0;
        this.pressTotalTime = cookTime;

        CompoundNBT compound = new CompoundNBT();
        this.writePressItem(compound);
        this.writePressTimes(compound);
        this.writePressTotalTimes(compound);
        TileEntityUtil.sendUpdatePacket(this, super.save(compound));
    }

    @Override
    public void clearContent() {
        this.press.clear();
    }

    @Override
    public void tick() {
        if(!this.level.isClientSide) {
            boolean canPress = this.canPress();
            if(canPress) {
                this.pressItem();
            }
        }
    }

    private boolean canPress() {
        return !this.press.get(0).isEmpty() && this.pressTime != this.pressTotalTime;
    }

    private void pressItem() {
        if(!this.press.get(0).isEmpty()) {
            if(this.pressTime < this.pressTotalTime) {
                this.pressTime++;
                System.out.println("Add to pressTime");
                if(this.pressTime == this.pressTotalTime) {
                    System.out.println("pressTime = pressTotalTime");
                    this.press.set(0, new ItemStack(ItemRegistry.GOLD_COIN.get()).copy());

                    CompoundNBT compound = new CompoundNBT();
                    this.writePressItem(compound);
                    this.writePressTimes(compound);
                    TileEntityUtil.sendUpdatePacket(this, super.save(compound));
                }
            }
        }

    }

    boolean isPowered() {
        return true;
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        if(compound.contains("Press", Constants.NBT.TAG_LIST)) {
            this.press.clear();
            InventoryUtil.loadAllItems("Press", compound, this.press);
        }
        if(compound.contains("PressTimes", Constants.NBT.TAG_INT)) {
            this.pressTime = compound.getInt("PressTimes");
        }
        if(compound.contains("PressTotalTimes", Constants.NBT.TAG_INT)) {
            this.pressTotalTime = compound.getInt("PressTotalTimes");
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        this.writePressItem(compound);
        this.writePressTimes(compound);
        this.writePressTotalTimes(compound);
        return super.save(compound);
    }

    private CompoundNBT writePressItem(CompoundNBT compound) {
        InventoryUtil.saveAllItems("Press", compound, this.press);
        return compound;
    }
    private CompoundNBT writePressTimes(CompoundNBT compound) {
        compound.putInt("CookingTimes", this.pressTime);
        return compound;
    }
    private CompoundNBT writePressTotalTimes(CompoundNBT compound) {
        compound.putInt("PressTotalTimes", this.pressTotalTime);
        return compound;
    }

    @Override
    public int[] getSlotsForFace(Direction p_180463_1_) {
        return PRESS_SLOT;
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        if(!this.getItem(index).isEmpty()) {
            return false;
        }
        return stack.getItem() == Items.GOLD_INGOT;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return false;
    }

    @Override
    public int getContainerSize() {
        return this.press.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack stack : this.press) {
            if(!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.press.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ItemStackHelper.removeItem(this.press, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ItemStackHelper.takeItem(this.press, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        NonNullList<ItemStack> inventory = this.press;
        inventory.set(index, stack);
        if(stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

        /* Send updates to client */
        CompoundNBT compound = new CompoundNBT();
        this.writePressItem(compound);
        TileEntityUtil.sendUpdatePacket(this, super.save(compound));
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return this.level.getBlockEntity(this.worldPosition) == this && player.distanceToSqr(this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 0.5, this.worldPosition.getZ() + 0.5) <= 64;
    }
}
