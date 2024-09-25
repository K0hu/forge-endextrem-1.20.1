package de.kohu.endextrem.item;

import de.kohu.endextrem.EndExtrem;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EndExtrem.MODID);

    public static final RegistryObject<Item> HELL_FEATHER = ITEMS.register("hell_feather",
            () -> new HellFeatherItem(new Item.Properties()
                    .stacksTo(4)
                    .rarity(Rarity.create("Hell", ChatFormatting.DARK_RED))
                    .fireResistant()
                    .durability(1)
                    .defaultDurability(1)));

    public static final RegistryObject<Item> DEVILS_BOOK = ITEMS.register("devils_book",
            () -> new DevilsBookItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.create("Hell", ChatFormatting.DARK_RED))
                    .fireResistant()
                    .durability(150)
                    .defaultDurability(150)));

    public static final RegistryObject<Item> DEVILS_BOOK_UP = ITEMS.register("devils_book_upgrade",
            () -> new DevilsUpgradeBookItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.create("Hell", ChatFormatting.DARK_RED))
                    .durability(150)
                    .fireResistant()
                    .defaultDurability(150)));

    public static final RegistryObject<Item> siegel = ITEMS.register("siegel",
            () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.create("Heaven", ChatFormatting.YELLOW))
                    .durability(150)
                    .defaultDurability(150)));

    public static final RegistryObject<Item> HELL_TELEPORTION = ITEMS.register("hells_branch",
            () -> new HellBranchItem(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
                    .rarity(Rarity.create("Hell", ChatFormatting.DARK_RED))
                    .durability(15)
                    .defaultDurability(15)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        eventBus.register(ModItemInit.class);
    }

}
