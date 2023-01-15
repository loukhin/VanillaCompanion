package com.loukhin.vanillacompanion.network;

import com.loukhin.vanillacompanion.VanillaCompanion;
import com.loukhin.vanillacompanion.config.ArmorHide;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.Map;

public class VanillaExtractNetwork {
    public static final Identifier ARMOR_STATE_ID = new Identifier("vanillaextract", "armor_state");

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(ARMOR_STATE_ID, (client, handler, buf, responseSender) -> {
            Map<String, Boolean> hideSettings = buf.readMap(PacketByteBuf::readString, PacketByteBuf::readBoolean);
            VanillaCompanion.armorHide = new ArmorHide(hideSettings);
        });
    }
}
