package co.uk.mrpineapple.dynasty.core;

import co.uk.mrpineapple.dynasty.client.ClientEvents;
import co.uk.mrpineapple.dynasty.common.CommonEvents;
import co.uk.mrpineapple.dynasty.core.registry.BlockRegistry;
import co.uk.mrpineapple.dynasty.core.registry.EntityRegistry;
import co.uk.mrpineapple.dynasty.core.registry.ItemRegistry;
import co.uk.mrpineapple.dynasty.core.registry.TileEntityRegistry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.language.IModInfo;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Dynasty.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
@Mod(Dynasty.ID)
public class Dynasty {
    public static final String ID = "dynasty";
    public static IModInfo modInfo;

    public Dynasty() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
        modInfo = ModLoadingContext.get().getActiveContainer().getModInfo();

        ItemRegistry.ITEMS.register(bus);
        BlockRegistry.BLOCKS.register(bus);
        TileEntityRegistry.TILE_ENTITY.register(bus);

        EntityRegistry.PROFESSIONS.register(bus);
        EntityRegistry.POI_TYPES.register(bus);
        EntityRegistry.ENTITY_TYPES.register(bus);
        
        bus.addListener(CommonEvents::onCommonSetup);
        bus.addListener(ClientEvents::onClientSetup);
    }

    @SubscribeEvent
    public static void playerLoginEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if(VersionChecker.getResult(modInfo).status.equals(VersionChecker.Status.OUTDATED)) {
            event.getPlayer().sendMessage(new StringTextComponent("There is an update available for Dynasty").withStyle(TextFormatting.DARK_RED, TextFormatting.BOLD), UUID.randomUUID());
        } else if(VersionChecker.getResult(modInfo).status.equals(VersionChecker.Status.AHEAD)) {
            event.getPlayer().sendMessage(new StringTextComponent("We're ahead of schedule").withStyle(TextFormatting.AQUA, TextFormatting.ITALIC), UUID.randomUUID());
        }
    }
}
