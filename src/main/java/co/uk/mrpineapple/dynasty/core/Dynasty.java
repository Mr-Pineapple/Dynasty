package co.uk.mrpineapple.dynasty.core;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Dynasty.ID)
public class Dynasty {
    public static final String ID = "dynasty";

    public Dynasty() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
    }
}
