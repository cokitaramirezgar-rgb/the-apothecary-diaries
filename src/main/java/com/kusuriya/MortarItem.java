package com.kusuriya;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/** Mortero: se queda en la mesa de crafteo y pierde 1 de durabilidad cada vez que se usa en una receta. */
public class MortarItem extends Item {
    public MortarItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        ItemStack copy = stack.copy();
        copy.setDamageValue(copy.getDamageValue() + 1);
        return copy.getDamageValue() >= copy.getMaxDamage() ? ItemStack.EMPTY : copy;
    }
}
