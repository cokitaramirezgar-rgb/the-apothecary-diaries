package com.kusuriya;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(KusuriyaMod.MODID);

    public static final DeferredItem<Item> SILVER_NEEDLE =
            ITEMS.registerSimpleItem("silver_needle", new Item.Properties().stacksTo(1));
}
