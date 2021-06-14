package co.uk.mrpineapple.dynasty.common.block;

import co.uk.mrpineapple.dynasty.common.tileentity.CoinPressTileEntity;
import co.uk.mrpineapple.dynasty.common.tileentity.GeneratorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class GeneratorBlock extends Block {

    public GeneratorBlock() {
        super(Properties.copy(Blocks.STONE));
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        if(!world.isClientSide) {
            TileEntity tileEntity = world.getBlockEntity(pos);
            if(tileEntity instanceof GeneratorTileEntity) {
                GeneratorTileEntity generatorTileEntity = (GeneratorTileEntity) tileEntity;
                ItemStack itemStack = player.getItemInHand(hand);
                if(itemStack.getItem() == Items.COAL) {
                    if(generatorTileEntity.addItem(itemStack)) {
                        itemStack.shrink(1);
                    }
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onRemove(BlockState oldState, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if(oldState.getBlock() != newState.getBlock()) {
            TileEntity tileEntity = world.getBlockEntity(pos);
            if(tileEntity instanceof GeneratorTileEntity) {
                GeneratorTileEntity generatorTileEntity = (GeneratorTileEntity) tileEntity;
                InventoryHelper.dropContents(world, pos, generatorTileEntity.getGenerator());
            }
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new GeneratorTileEntity();
    }

}
