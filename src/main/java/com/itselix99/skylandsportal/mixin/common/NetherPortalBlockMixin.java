package com.itselix99.skylandsportal.mixin.common;

import com.itselix99.skylandsportal.SkylandsPortal;
import com.itselix99.skylandsportal.interfaces.SPCheckDimension;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetherPortalBlock.class)
public class NetherPortalBlockMixin {

    @Inject(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;tickPortalCooldown()V"))
    public void sp_onEntityCollision(World world, int x, int y, int z, Entity entity, CallbackInfo ci) {
        if (entity instanceof PlayerEntity) {
            int blockId = world.getBlockId(x, y, z);

            ((SPCheckDimension) entity).sp_setBlockPortalId(blockId == SkylandsPortal.SKYLANDS_PORTAL.id ? SkylandsPortal.SKYLANDS_PORTAL.id : -1);
        }
    }
}