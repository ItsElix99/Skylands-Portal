package com.itselix99.skylandsportal.mixin.server;

import com.itselix99.skylandsportal.intefaces.CheckDimension;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.PlayerManager;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    @Inject(
            method = "changePlayerDimension",
            at = @At("HEAD")
    )
    private void setOverworldDimension(ServerPlayerEntity player, CallbackInfo ci) {
        if (player.dimensionId == 1) {
            player.dimensionId = 0;
        }
    }

    @WrapOperation(
            method = "changePlayerDimension",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/player/ServerPlayerEntity;dimensionId:I",
                    opcode = Opcodes.PUTFIELD,
                    ordinal = 0
            )
    )
    private void setDimensionId(ServerPlayerEntity player, int value, Operation<Void> original) {
        original.call(player, ((CheckDimension) player).slr_getNether() ? value : 1);
    }
}