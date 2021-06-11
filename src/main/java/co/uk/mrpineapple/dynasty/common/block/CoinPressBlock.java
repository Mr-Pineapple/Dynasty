package co.uk.mrpineapple.dynasty.common.block;

import co.uk.mrpineapple.dynasty.common.tileentity.CoinPressTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class CoinPressBlock extends Block {
    public CoinPressBlock() {
        super(Properties.copy(Blocks.STONE));
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        if(!world.isClientSide) {
            TileEntity tileEntity = world.getBlockEntity(pos);
            if(tileEntity instanceof CoinPressTileEntity) {
                CoinPressTileEntity coinPressTileEntity = (CoinPressTileEntity) tileEntity;
                ItemStack itemStack = player.getItemInHand(hand);
                if(itemStack.getItem() == Items.GOLD_INGOT) {
                    if(coinPressTileEntity.addItem(itemStack, 0, 600)) {
                        itemStack.shrink(1);
                    }
                }
                if(itemStack.isEmpty() && player.isCrouching()) {
                    coinPressTileEntity.removeItem(player);
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CoinPressTileEntity();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
