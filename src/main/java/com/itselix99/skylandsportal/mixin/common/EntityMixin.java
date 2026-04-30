package com.itselix99.skylandsportal.mixin.common;

import com.itselix99.skylandsportal.SkylandsPortal;
import com.itselix99.skylandsportal.interfaces.SPCheckDimension;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject
            (
                    method = "move",
                    at = @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/block/Block;onEntityCollision(Lnet/minecraft/world/World;IIILnet/minecraft/entity/Entity;)V"
                    )
            )
    private void sp_setInSkylandsPortalBlock(double dx, double dy, double dz, CallbackInfo ci, @Local(name = "var34") int var34) {
        if (Entity.class.cast(this) instanceof PlayerEntity player) {
            ((SPCheckDimension) player).sp_setInSkylandsPortalBlock(var34 == SkylandsPortal.SKYLANDS_PORTAL.id);
        }

    }

}