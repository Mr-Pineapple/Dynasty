package co.uk.mrpineapple.dynasty.client;

import co.uk.mrpineapple.dynasty.client.tileentity.renderer.CoinPressTileEntityRenderer;
import co.uk.mrpineapple.dynasty.core.registry.TileEntityRegistry;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientEvents {
    public static void onClientSetup(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(TileEntityRegistry.COIN_PRESS.get(), CoinPressTileEntityRenderer::new);
    }
}
