package com.itselix99.skylandsportal.mixin.client;

import com.itselix99.skylandsportal.SkylandsPortal;
import com.itselix99.skylandsportal.interfaces.SPCheckDimension;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.hud.InGameHud;
import net.modificationstation.stationapi.api.client.texture.Sprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = InGameHud.class, priority = 1100)
public class InGameHudMixin {
    @Shadow private Minecraft minecraft;

    @ModifyVariable(
            method = "renderPortalOverlay",
            at = @At("STORE"),
            index = 4
    )
    private float sp_skylandsPortalTextureMinU(float original) {
        if (((SPCheckDimension) this.minecraft.player).sp_getBlockPortalId() == SkylandsPortal.SKYLANDS_PORTAL.id) {
            Sprite sprite = SkylandsPortal.SKYLANDS_PORTAL.getAtlas().getTexture(SkylandsPortal.SKYLANDS_PORTAL.textureId).getSprite();
            return sprite.getMinU();
        } else {
            return original;
        }
    }

    @ModifyVariable(
            method = "renderPortalOverlay",
            at = @At("STORE"),
            index = 5
    )
    private float sp_skylandsPortalTextureMinV(float original) {
        if (((SPCheckDimension) this.minecraft.player).sp_getBlockPortalId() == SkylandsPortal.SKYLANDS_PORTAL.id) {
            Sprite sprite = SkylandsPortal.SKYLANDS_PORTAL.getAtlas().getTexture(SkylandsPortal.SKYLANDS_PORTAL.textureId).getSprite();
            return sprite.getMinV();
        } else {
            return original;
        }
    }

    @ModifyVariable(
            method = "renderPortalOverlay",
            at = @At("STORE"),
            index = 6
    )
    private float sp_skylandsPortalTextureMaxU(float original) {
        if (((SPCheckDimension) this.minecraft.player).sp_getBlockPortalId() == SkylandsPortal.SKYLANDS_PORTAL.id) {
            Sprite sprite = SkylandsPortal.SKYLANDS_PORTAL.getAtlas().getTexture(SkylandsPortal.SKYLANDS_PORTAL.textureId).getSprite();
            return sprite.getMaxU();
        } else {
            return original;
        }
    }

    @ModifyVariable(
            method = "renderPortalOverlay",
            at = @At("STORE"),
            index = 7
    )
    private float sp_skylandsPortalTextureMaxV(float original) {
        if (((SPCheckDimension) this.minecraft.player).sp_getBlockPortalId() == SkylandsPortal.SKYLANDS_PORTAL.id) {
            Sprite sprite = SkylandsPortal.SKYLANDS_PORTAL.getAtlas().getTexture(SkylandsPortal.SKYLANDS_PORTAL.textureId).getSprite();
            return sprite.getMaxV();
        } else {
            return original;
        }
    }
}