package co.uk.mrpineapple.dynasty.core.registry;

import co.uk.mrpineapple.dynasty.common.block.CoinPressBlock;
import co.uk.mrpineapple.dynasty.common.block.CopperOreBlock;
import co.uk.mrpineapple.dynasty.common.block.GeneratorBlock;
import co.uk.mrpineapple.dynasty.core.Dynasty;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Dynasty.ID);

    public static final RegistryObject<Block> COIN_PRESS = register("coin_press", CoinPressBlock::new);
    public static final RegistryObject<Block> GENERATOR = register("generator", GeneratorBlock::new);

    public static final RegistryObject<Block> COPPER_ORE = register("copper_ore", CopperOreBlock::new);

    public static <B extends Block> RegistryObject<B> register(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = BLOCKS.register(name, supplier);
        ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(Dynasty.TAB)));
        return block;
    }
}
