package co.uk.mrpineapple.dynasty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class CopperOreBlock extends Block {

    public CopperOreBlock() {
        super(Properties.copy(Blocks.IRON_ORE).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    }

    protected int xpOnDrop(Random random) { return MathHelper.nextInt(random, 0, 0);}

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.xpOnDrop(RANDOM) : 0;
    }
}

