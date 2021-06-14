package co.uk.mrpineapple.dynasty.core.registry;

import co.uk.mrpineapple.dynasty.common.tileentity.CoinPressTileEntity;
import co.uk.mrpineapple.dynasty.common.tileentity.GeneratorTileEntity;
import co.uk.mrpineapple.dynasty.core.Dynasty;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class TileEntityRegistry {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Dynasty.ID);

    public static final RegistryObject<TileEntityType<CoinPressTileEntity>> COIN_PRESS = register("coin_press", CoinPressTileEntity::new, () -> new Block[]{BlockRegistry.COIN_PRESS.get()});
    public static final RegistryObject<TileEntityType<GeneratorTileEntity>> GENERATOR = register("generator", GeneratorTileEntity::new, () -> new Block[]{BlockRegistry.GENERATOR.get()});

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String id, Supplier<T> factory, Supplier<Block[]> validBlocks) {
        return TILE_ENTITY.register(id, () -> TileEntityType.Builder.of(factory, validBlocks.get()).build(null));
    }
}
