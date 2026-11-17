package com.kusuriya;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = KusuriyaMod.MODID)
public class ModEvents {
    /** Efectos que la Resistencia a venenos anula (ver data/kusuriya/tags/mob_effect/poisons.json). */
    public static final TagKey<MobEffect> POISONS = TagKey.create(Registries.MOB_EFFECT,
            ResourceLocation.fromNamespaceAndPath(KusuriyaMod.MODID, "poisons"));

    /** Mientras tengas Resistencia a venenos, cualquier veneno de la etiqueta se elimina al instante. */
    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Pre event) {
        if (!(event.getEntity() instanceof LivingEntity living) || living.level().isClientSide) {
            return;
        }
        if (!living.hasEffect(ModEffects.POISON_RESISTANCE)) {
            return;
        }
        List<Holder<MobEffect>> toRemove = new ArrayList<>();
        for (MobEffectInstance instance : living.getActiveEffects()) {
            if (instance.getEffect().is(POISONS)) {
                toRemove.add(instance.getEffect());
            }
        }
        for (Holder<MobEffect> effect : toRemove) {
            living.removeEffect(effect);
        }
    }

    /** Tolerancia al estilo Maomao: sobrevivir al veneno de acónito te da 5 minutos de resistencia. */
    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event) {
        MobEffectInstance instance = event.getEffectInstance();
        LivingEntity living = event.getEntity();
        if (instance == null || living.level().isClientSide) {
            return;
        }
        if (instance.getEffect().value() == ModEffects.ACONITE_POISONING.get()) {
            living.addEffect(new MobEffectInstance(ModEffects.POISON_RESISTANCE, 6000, 0));
        }
    }

    /** Provisional: 5% de probabilidad de encontrar una semilla medicinal al romper hierba. */
    @SubscribeEvent
    public static void onBlockDrops(BlockDropsEvent event) {
        BlockState state = event.getState();
        if (!state.is(Blocks.SHORT_GRASS) && !state.is(Blocks.TALL_GRASS)) {
            return;
        }
        ServerLevel level = event.getLevel();
        if (level.random.nextFloat() >= 0.05F) {
            return;
        }
        Item seed = switch (level.random.nextInt(3)) {
            case 0 -> ModItems.GINSENG_SEEDS.get();
            case 1 -> ModItems.ACONITE_SEEDS.get();
            default -> ModItems.LICORICE_SEEDS.get();
        };
        BlockPos pos = event.getPos();
        event.getDrops().add(new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                new ItemStack(seed)));
    }
}
