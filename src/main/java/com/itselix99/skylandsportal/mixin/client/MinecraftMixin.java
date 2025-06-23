package com.itselix99.skylandsportal.mixin.client;

import com.itselix99.skylandsportal.intefaces.CheckDimension;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ClientPlayerEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin implements Runnable {
    @Shadow public ClientPlayerEntity player;

    @Inject(
            method = "changeDimension",
            at = @At("HEAD")
    )
    private void setOverworldDimension(CallbackInfo ci) {
        if (this.player.dimensionId == 1) {
            this.player.dimensionId = 0;
        }
    }


    @Inject(
            method = "changeDimension",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;remove(Lnet/minecraft/entity/Entity;)V"
            )
    )
    private void setDimensionId(CallbackInfo ci) {
        if (!((CheckDimension) this.player).slr_getNether()) {
            this.player.dimensionId = 1;
        }
    }
}