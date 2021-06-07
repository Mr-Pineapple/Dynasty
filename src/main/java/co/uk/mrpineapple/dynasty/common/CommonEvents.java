package co.uk.mrpineapple.dynasty.common;

import co.uk.mrpineapple.dynasty.client.entity.render.BasicVillagerRenderer;
import co.uk.mrpineapple.dynasty.common.entity.passive.BasicVillagerEntity;
import co.uk.mrpineapple.dynasty.common.world.gen.OreGeneration;
import co.uk.mrpineapple.dynasty.core.registry.EntityRegistry;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonEvents {
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        EntityRegistry.createTradeData();
        EntityRegistry.registerBlacksmithPOI();

        OreGeneration.registerOres();
    }

    public static void attributes(final FMLCommonSetupEvent event)
    {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(EntityRegistry.BASIC_VILLAGER.get(), BasicVillagerEntity.createAttributes().build());
        });
    }


    @SubscribeEvent
    public static void renderEntities(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.BASIC_VILLAGER.get(), BasicVillagerRenderer::new);
    }
}