package com.kusuriya;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, KusuriyaMod.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> POISON_RESISTANCE =
            EFFECTS.register("poison_resistance",
                    () -> new PoisonResistanceEffect(MobEffectCategory.BENEFICIAL, 0x88AA55));

    public static class PoisonResistanceEffect extends MobEffect {
        public PoisonResistanceEffect(MobEffectCategory category, int color) {
            super(category, color);
        }
    }
}
