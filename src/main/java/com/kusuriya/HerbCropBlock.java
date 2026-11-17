package com.kusuriya;

import java.util.function.Supplier;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

/** Cultivo de hierba medicinal: igual que el trigo, pero con su propia semilla. */
public class HerbCropBlock extends CropBlock {
    private final Supplier<? extends ItemLike> seed;

    public HerbCropBlock(BlockBehaviour.Properties properties, Supplier<? extends ItemLike> seed) {
        super(properties);
        this.seed = seed;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return seed.get();
    }
}
