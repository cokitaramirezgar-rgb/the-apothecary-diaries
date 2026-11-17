package com.kusuriya;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/** Antídoto específico: elimina un único veneno al beberlo. */
public class AntidoteItem extends DrinkableItem {
    private final Holder<MobEffect> cures;

    public AntidoteItem(Properties properties, Holder<MobEffect> cures) {
        super(properties);
        this.cures = cures;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide) {
            entity.removeEffect(cures);
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
