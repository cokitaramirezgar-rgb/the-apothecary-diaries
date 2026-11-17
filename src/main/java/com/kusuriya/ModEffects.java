package com.kusuriya;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, KusuriyaMod.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> POISON_RESISTANCE =
            EFFECTS.register("poison_resistance",
                    () -> new PoisonResistanceEffect(MobEffectCategory.BENEFICIAL, 0x88AA55));

    public static final DeferredHolder<MobEffect, MobEffect> ACONITE_POISONING =
            EFFECTS.register("aconite_poisoning",
                    () -> new AconitePoisoningEffect(MobEffectCategory.HARMFUL, 0x5B3A8C));

    /** Efecto "marcador": la lógica que anula los venenos está en ModEvents. */
    public static class PoisonResistanceEffect extends MobEffect {
        public PoisonResistanceEffect(MobEffectCategory category, int color) {
            super(category, color);
        }
    }

    /** Veneno de acónito: 1 de daño cada segundo, pero nunca mata (como el veneno vanilla). */
    public static class AconitePoisoningEffect extends MobEffect {
        public AconitePoisoningEffect(MobEffectCategory category, int color) {
            super(category, color);
        }

        @Override
        public boolean applyEffectTick(LivingEntity entity, int amplifier) {
            if (entity.getHealth() > 1.0F) {
                entity.hurt(entity.damageSources().magic(), 1.0F);
            }
            return true;
        }

        @Override
        public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
            int interval = 20 >> amplifier;
            return interval <= 0 || duration % interval == 0;
        }
    }
}
