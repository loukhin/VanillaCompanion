package com.loukhin.vanillacompanion.gui;

import com.loukhin.vanillacompanion.VanillaCompanion;
import com.loukhin.vanillacompanion.config.ArmorHide;
import com.loukhin.vanillacompanion.network.VanillaExtractNetwork;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class ArmorHideWidget extends DrawableHelper implements Drawable, Element, Selectable {
    private final HashMap<String, ToggleButtonWidget> toggleButtons = new HashMap<>();
    private HandledScreen<?> parent;

    public void init(HandledScreen<?> parent) {
        this.parent = parent;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        int i = 0;
        for (String slot : ArmorHide.slotList) {
            ToggleButtonWidget button = new ToggleButtonWidget(this.parent.x, this.parent.y + 7 + (i * 16) + i * 2 + 1, 7, 16, VanillaCompanion.armorHide.state.get(slot));
            button.setTextureUV(0, 0, 7, 16, new Identifier("vanillacompanion:textures/button.png"));
            button.render(matrices, mouseX, mouseY, delta);
            toggleButtons.put(slot, button);
            i++;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int buttonNum) {
        for (String slot : ArmorHide.slotList) {
            ToggleButtonWidget button = this.toggleButtons.get(slot);
            if (button.mouseClicked(mouseX, mouseY, buttonNum)) {
                boolean newState = !VanillaCompanion.armorHide.state.get(slot);
                VanillaCompanion.armorHide.state.put(slot, newState);
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeMap(VanillaCompanion.armorHide.state, PacketByteBuf::writeString, PacketByteBuf::writeBoolean);
                ClientPlayNetworking.send(VanillaExtractNetwork.ARMOR_STATE_ID, buf);
                button.setToggled(newState);
                this.toggleButtons.put(slot, button);
                return true;
            }
        }
        return false;
    }

    @Override
    public SelectionType getType() {
        return SelectionType.NONE;
    }

    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {

    }
}
