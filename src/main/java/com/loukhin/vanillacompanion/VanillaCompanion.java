package com.loukhin.vanillacompanion;

import com.loukhin.vanillacompanion.config.ArmorHide;
import com.loukhin.vanillacompanion.network.VanillaExtractNetwork;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class VanillaCompanion implements ClientModInitializer {
    public static ArmorHide armorHide = ArmorHide.empty();

    @Override
    public void onInitializeClient() {
        VanillaExtractNetwork.registerS2CPackets();
    }
}
