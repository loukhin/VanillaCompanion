package com.loukhin.vanillacompanion.mixin;

import com.loukhin.vanillacompanion.access.InventoryScreenAccess;
import com.loukhin.vanillacompanion.gui.ArmorHideWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin extends Screen implements InventoryScreenAccess {
    ArmorHideWidget armorHideWidget;

    @Override
    public ArmorHideWidget getArmorHideWidget() {
        return this.armorHideWidget;
    }

    protected InventoryScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("TAIL"), method = "init")
    public void init(CallbackInfo ci) {
        this.armorHideWidget = new ArmorHideWidget();
        this.armorHideWidget.init(((InventoryScreen)(Object)this));
        this.addDrawableChild(this.armorHideWidget);
    }

}
