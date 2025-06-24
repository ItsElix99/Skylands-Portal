package com.itselix99.skylandsportal.mixin.client;

import com.itselix99.skylandsportal.intefaces.CheckDimension;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ClientPlayerEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin implements Runnable {

    @WrapOperation(
            method = "changeDimension",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/player/ClientPlayerEntity;dimensionId:I",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 0
            )
    )
    private int redirectDimensionCheck(ClientPlayerEntity player, Operation<Integer> original) {
        int dimensionId = original.call(player);
        if (dimensionId == 1 && !((CheckDimension) player).sp_getNether()) {
            return -1;
        }
        return dimensionId;
    }


    @WrapOperation(
            method = "changeDimension",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/player/ClientPlayerEntity;dimensionId:I",
                    opcode = Opcodes.PUTFIELD,
                    ordinal = 1
            )
    )
    private void setDimensionId(ClientPlayerEntity player, int value, Operation<Void> original) {
        original.call(player, ((CheckDimension) player).sp_getNether() ? value : 1);
    }
}