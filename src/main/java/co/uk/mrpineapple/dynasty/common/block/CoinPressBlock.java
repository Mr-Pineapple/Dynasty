package co.uk.mrpineapple.dynasty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ISidedInventoryProvider;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;

public class CoinPressBlock extends Block implements ISidedInventoryProvider {
    public CoinPressBlock() {
        super(Properties.copy(Blocks.STONE));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return null;
    }

    @Override
    public ISidedInventory getContainer(BlockState state, IWorld world, BlockPos pos) {
        if(!world.isClientSide()) {
            TileEntity tileEntity = world.getBlockEntity(pos);
            if(tileEntity instanceof FurnaceTileEntity) {
                return (FurnaceTileEntity) tileEntity;
            }
        }
        return null;
    }
}
