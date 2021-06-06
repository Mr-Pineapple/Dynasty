package co.uk.mrpineapple.dynasty.core.registry;

import co.uk.mrpineapple.dynasty.common.util.ModVillagerTrades;
import co.uk.mrpineapple.dynasty.core.Dynasty;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Blocks;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;

public class EntityRegistry {
    public static final DeferredRegister<PointOfInterestType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, Dynasty.ID);
    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, Dynasty.ID);

    public static final RegistryObject<PointOfInterestType> BLACKSMITH_POI = POI_TYPES.register("blacksmith", () -> new PointOfInterestType("blacksmith", PointOfInterestType.getBlockStates(Blocks.ANVIL), 1, 1));
    public static final RegistryObject<VillagerProfession> BLACKSMITH = PROFESSIONS.register("blacksmith", () -> new VillagerProfession("blacksmith", BLACKSMITH_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_WEAPONSMITH));

    public static void registerBlacksmithPOI() {
        try {
            ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "registerBlockStates", PointOfInterestType.class).invoke(null, BLACKSMITH_POI.get());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void createTradeData() {
        VillagerTrades.ITrade[] blacksmithLevel1 = new VillagerTrades.ITrade[]{
                new ModVillagerTrades.ItemsForGoldCoinsTrade(Items.IRON_SWORD,3,1,4),
        };

        VillagerTrades.TRADES.put(BLACKSMITH.get(), new Int2ObjectOpenHashMap<>(ImmutableMap.of(1, blacksmithLevel1)));
    }

}
