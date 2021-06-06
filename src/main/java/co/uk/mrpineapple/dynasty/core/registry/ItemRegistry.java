package co.uk.mrpineapple.dynasty.core.registry;

import co.uk.mrpineapple.dynasty.core.Dynasty;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Dynasty.ID);

    public static final RegistryObject<Item> GOLD_COIN = register("gold_coin");

    public static RegistryObject<Item> register(String name) {
        RegistryObject<Item> item = ITEMS.register(name, () -> new Item(new Item.Properties()));
        return item;
    }

}
