package co.uk.mrpineapple.dynasty.common.block;

import co.uk.mrpineapple.dynasty.common.tileentity.GeneratorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class GeneratorBlock extends Block {

    public GeneratorBlock() {
        super(Properties.copy(Blocks.STONE));
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
