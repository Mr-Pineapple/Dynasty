package co.uk.mrpineapple.dynasty.common.tileentity;

import co.uk.mrpineapple.dynasty.core.registry.TileEntityRegistry;

public class GeneratorTileEntity extends BaseTileEntity {
    public GeneratorTileEntity() {
        super(TileEntityRegistry.GENERATOR.get());
    }
}
