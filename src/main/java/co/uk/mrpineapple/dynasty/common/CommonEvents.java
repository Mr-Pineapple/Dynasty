package co.uk.mrpineapple.dynasty.common;

import co.uk.mrpineapple.dynasty.core.registry.EntityRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonEvents {
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        EntityRegistry.createTradeData();
        EntityRegistry.registerBlacksmithPOI();
    }
}