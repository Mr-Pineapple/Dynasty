package co.uk.mrpineapple.dynasty.core.registry;

import co.uk.mrpineapple.dynasty.common.block.CoinPressBlock;
import co.uk.mrpineapple.dynasty.core.Dynasty;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Dynasty.ID);

    public static final RegistryObject<Block> COIN_PRESS = BLOCKS.register("coin_press", CoinPressBlock::new);
}
