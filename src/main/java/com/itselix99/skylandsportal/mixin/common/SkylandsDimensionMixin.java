package com.itselix99.skylandsportal.mixin.common;

import com.itselix99.skylandsportal.config.SkylandsPortalConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvironmentInterface;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.SkylandsDimension;
import net.modificationstation.stationapi.api.client.world.dimension.TravelMessageProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@EnvironmentInterface(value = EnvType.CLIENT, itf = TravelMessageProvider.class)
@Mixin(SkylandsDimension.class)
public class SkylandsDimensionMixin extends Dimension implements TravelMessageProvider {

    @Environment(EnvType.CLIENT)
    @Override
    public String getEnteringTranslationKey() {
        return TranslationStorage.getInstance().get("skylands.entering");
    }

    @Environment(EnvType.CLIENT)
    @Override
    public String getLeavingTranslationKey() {
        return TranslationStorage.getInstance().get("skylands.leaving");
    }


    @Inject(method = "initBiomeSource", at = @At("TAIL"))
    private void sp_overworldBiomesInSky(CallbackInfo ci) {
        if (SkylandsPortalConfig.SPConfig.overworldBiomesInSky) {
            this.biomeSource = new BiomeSource(this.world);
        }
    }
}