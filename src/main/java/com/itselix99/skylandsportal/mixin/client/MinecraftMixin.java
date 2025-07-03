package com.itselix99.skylandsportal.mixin.client;

import com.itselix99.skylandsportal.SkylandsPortal;
import com.itselix99.skylandsportal.block.SkylandsPortalBlock;
import com.itselix99.skylandsportal.interfaces.CheckDimension;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.InteractionManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.PortalForcer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin implements Runnable {
    @Shadow public ClientPlayerEntity player;

    @Shadow public World world;

    @Shadow public InteractionManager interactionManager;

    @WrapOperation(
            method = "changeDimension",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/player/ClientPlayerEntity;dimensionId:I",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 0
            )
    )
    private int dimensionCheck(ClientPlayerEntity player, Operation<Integer> original) {
        int dimensionId = original.call(player);
        if (!((CheckDimension) player).sp_getNether()) {
            return 1;
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

    @Inject(
            method = "changeDimension",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/dimension/PortalForcer;moveToPortal(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void testing(CallbackInfo ci) {
        if (this.player.dimensionId == 1) {
            double var1 = this.player.x * 8.0F;
            double var3 = this.player.z * 8.0F;
            Vec3i var11 = this.world.getSpawnPos();
            var1 = (double)var11.x + 0.5D;
            this.player.y = (double)(var11.y + 1);
            var3 = (double)var11.z + 0.5D;
            while(this.player.y > 0.0D) {
                this.player.setPosition(var1, this.player.y, var3);
                if (this.world.getEntityCollisions(this.player, this.player.boundingBox).isEmpty()) {
                    break;
                }
                ++this.player.y;
            }
            this.player.velocityX = this.player.velocityY = this.player.velocityZ = 0.0D;
            this.player.pitch = 0.0F;
            this.interactionManager.preparePlayer(this.player);
            if (this.player.isAlive()) {
                this.world.updateEntity(this.player, false);
            }
        }

        ((SkylandsPortalBlock) SkylandsPortal.SKYLANDS_PORTAL).getTravelAgent(this.player).moveToPortal(this.world, this.player);
    }
}