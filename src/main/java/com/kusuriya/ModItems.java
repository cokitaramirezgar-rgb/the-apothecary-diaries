package com.kusuriya;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(KusuriyaMod.MODID);

    public static final DeferredItem<Item> SILVER_NEEDLE =
            ITEMS.registerSimpleItem("silver_needle", new Item.Properties().stacksTo(1));

    // Semillas
    public static final DeferredItem<ItemNameBlockItem> GINSENG_SEEDS = ITEMS.registerItem("ginseng_seeds",
            props -> new ItemNameBlockItem(ModBlocks.GINSENG_CROP.get(), props), new Item.Properties());
    public static final DeferredItem<ItemNameBlockItem> ACONITE_SEEDS = ITEMS.registerItem("aconite_seeds",
            props -> new ItemNameBlockItem(ModBlocks.ACONITE_CROP.get(), props), new Item.Properties());
    public static final DeferredItem<ItemNameBlockItem> LICORICE_SEEDS = ITEMS.registerItem("licorice_seeds",
            props -> new ItemNameBlockItem(ModBlocks.LICORICE_CROP.get(), props), new Item.Properties());

    // Raíces
    public static final DeferredItem<Item> GINSENG_ROOT = ITEMS.registerSimpleItem("ginseng_root",
            new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(2).saturationModifier(0.3F)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100), 1.0F)
                    .build()));

    public static final DeferredItem<Item> ACONITE_ROOT = ITEMS.registerSimpleItem("aconite_root",
            new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(1).saturationModifier(0.1F).alwaysEdible()
                    .effect(() -> new MobEffectInstance(ModEffects.ACONITE_POISONING, 400), 1.0F)
                    .build()));

    public static final DeferredItem<Item> LICORICE_ROOT = ITEMS.registerSimpleItem("licorice_root",
            new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(2).saturationModifier(0.2F)
                    .build()));

    // Antídoto
    public static final DeferredItem<Item> ANTIDOTE = ITEMS.registerSimpleItem("antidote",
            new Item.Properties().stacksTo(16).food(new FoodProperties.Builder()
                    .nutrition(1).saturationModifier(0.1F).alwaysEdible()
                    .effect(() -> new MobEffectInstance(ModEffects.POISON_RESISTANCE, 3600), 1.0F)
                    .build()));
}
