package com.kusuriya;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;

/**
 * Aguja de plata: con la aguja en la mano principal y un alimento o poción en la otra mano,
 * clic derecho para comprobar si contiene veneno (efectos de la etiqueta kusuriya:poisons).
 */
public class SilverNeedleItem extends Item {
    public SilverNeedleItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack needle = player.getItemInHand(hand);
        if (hand != InteractionHand.MAIN_HAND) {
            return InteractionResultHolder.pass(needle);
        }
        ItemStack target = player.getOffhandItem();
        if (!level.isClientSide) {
            if (target.isEmpty()) {
                player.displayClientMessage(Component.translatable("message.kusuriya.needle_empty"), true);
            } else if (isPoisonous(target)) {
                player.displayClientMessage(Component.translatable("message.kusuriya.needle_poison"), true);
                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.PLAYERS, 1.0F, 1.0F);
            } else {
                player.displayClientMessage(Component.translatable("message.kusuriya.needle_safe"), true);
                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
            player.getCooldowns().addCooldown(this, 10);
        }
        return InteractionResultHolder.sidedSuccess(needle, level.isClientSide());
    }

    private static boolean isPoisonous(ItemStack stack) {
        FoodProperties food = stack.get(DataComponents.FOOD);
        if (food != null) {
            for (FoodProperties.PossibleEffect possible : food.effects()) {
                if (possible.effect().getEffect().is(ModEvents.POISONS)) {
                    return true;
                }
            }
        }
        PotionContents potion = stack.get(DataComponents.POTION_CONTENTS);
        if (potion != null) {
            for (MobEffectInstance instance : potion.getAllEffects()) {
                if (instance.getEffect().is(ModEvents.POISONS)) {
                    return true;
                }
            }
        }
        return false;
    }
}
