package com.itselix99.skylandsportal.mixin.server;

import com.itselix99.skylandsportal.intefaces.CheckDimension;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.PlayerManager;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    @WrapOperation(
            method = "changePlayerDimension",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/player/ServerPlayerEntity;dimensionId:I",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 1
            )
    )
    private int redirectDimensionCheck(ServerPlayerEntity player, Operation<Integer> original) {
        int dimensionId = original.call(player);
        if (!((CheckDimension) player).sp_getNether()) {
            return 1;
        }
        return dimensionId;
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
        original.call(player, ((CheckDimension) player).sp_getNether() ? value : 1);
    }
}