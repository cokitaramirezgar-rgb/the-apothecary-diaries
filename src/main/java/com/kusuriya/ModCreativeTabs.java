package com.kusuriya;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KusuriyaMod.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN =
            TABS.register("main", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.kusuriya"))
                    .icon(() -> ModItems.SILVER_NEEDLE.get().getDefaultInstance())
                    .displayItems((params, output) -> {
                        output.accept(ModItems.SILVER_NEEDLE.get());
                        output.accept(ModItems.GINSENG_SEEDS.get());
                        output.accept(ModItems.ACONITE_SEEDS.get());
                        output.accept(ModItems.LICORICE_SEEDS.get());
                        output.accept(ModItems.GINSENG_ROOT.get());
                        output.accept(ModItems.ACONITE_ROOT.get());
                        output.accept(ModItems.LICORICE_ROOT.get());
                        output.accept(ModItems.ANTIDOTE.get());
                    })
                    .build());
}
