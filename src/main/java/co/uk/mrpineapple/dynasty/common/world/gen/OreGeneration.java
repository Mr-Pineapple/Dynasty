package co.uk.mrpineapple.dynasty.common.world.gen;

import co.uk.mrpineapple.dynasty.core.Dynasty;
import co.uk.mrpineapple.dynasty.core.registry.BlockRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.ArrayList;

@Mod.EventBusSubscriber
public class OreGeneration {

    private static final ArrayList<ConfiguredFeature<?, ?>> overworldOres = new ArrayList<ConfiguredFeature<?, ?>>();
    public static void registerOres(){
        overworldOres.add(register("copper_ore", Feature.ORE.configured(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockRegistry.COPPER_ORE.get().defaultBlockState(), 8)) //Vein Size
                .range(64).squared() //Spawn height start
                .count(64))); //Chunk spawn frequency
        /*
        overworldOres.add(register("gold/coin deposit", Feature.ORE.withConfiguration(new OreFeatureConfig(
                new BlockMatchRuleTest(Blocks.DIRT), RegistryHandlerBlocks.EARTHY_DEPOSIT.get().getDefaultState(), 4)) //Vein Size
                .range(128).square() //Spawn height start
                .func_242731_b(64))); //Chunk spawn frequency
         */
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void gen(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        for(ConfiguredFeature<?, ?> ore : overworldOres){
            if (ore != null) {
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        }
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, Dynasty.ID + ":" + name, configuredFeature);
    }

}