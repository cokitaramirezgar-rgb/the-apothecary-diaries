package com.kusuriya;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(KusuriyaMod.MODID)
public class KusuriyaMod {
    public static final String MODID = "kusuriya";

    public KusuriyaMod(IEventBus modBus, ModContainer container) {
        ModItems.ITEMS.register(modBus);
        ModEffects.EFFECTS.register(modBus);
        ModCreativeTabs.TABS.register(modBus);
    }
}
