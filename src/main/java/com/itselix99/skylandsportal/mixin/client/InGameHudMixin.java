package com.itselix99.skylandsportal.mixin.client;

import com.itselix99.skylandsportal.intefaces.CheckDimension;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InGameHud.class)
public class InGameHudMixin extends DrawContext {
    @Shadow private Minecraft minecraft;

    @WrapOperation(
            method = "renderPortalOverlay",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/opengl/GL11;glColor4f(FFFF)V",
                    ordinal = 0
            )
    )
    private void renderSkylandsPortalOverlay(float red, float green, float blue, float alpha, Operation<Void> original) {
        if (!((CheckDimension) this.minecraft.player).slr_getNether()) {
            original.call(0.39F, 0.71F, 0.98F, alpha);
        } else {
            original.call(red, green, blue, alpha);
        }
    }
}