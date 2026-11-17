package com.kusuriya;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(KusuriyaMod.MODID);

    public static final DeferredBlock<HerbCropBlock> GINSENG_CROP = BLOCKS.registerBlock("ginseng_crop",
            props -> new HerbCropBlock(props, ModItems.GINSENG_SEEDS),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT));

    public static final DeferredBlock<HerbCropBlock> ACONITE_CROP = BLOCKS.registerBlock("aconite_crop",
            props -> new HerbCropBlock(props, ModItems.ACONITE_SEEDS),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT));

    public static final DeferredBlock<HerbCropBlock> LICORICE_CROP = BLOCKS.registerBlock("licorice_crop",
            props -> new HerbCropBlock(props, ModItems.LICORICE_SEEDS),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT));
}
