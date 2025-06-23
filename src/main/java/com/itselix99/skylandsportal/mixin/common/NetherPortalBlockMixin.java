package com.itselix99.skylandsportal.mixin.common;

import com.itselix99.skylandsportal.intefaces.CheckDimension;
import net.minecraft.block.Block;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.block.TranslucentBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetherPortalBlock.class)
public class NetherPortalBlockMixin extends TranslucentBlock {

    public NetherPortalBlockMixin(int id, int textureId, Material material, boolean transparent) {
        super(id, textureId, material, transparent);
    }

    @Inject(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;tickPortalCooldown()V"))
    public void onEntityCollision(World world, int x, int y, int z, Entity entity, CallbackInfo ci) {
        if (entity instanceof PlayerEntity) {
            ((CheckDimension) entity).slr_setNether(world.getBlockId(x, y, z) == Block.NETHER_PORTAL.id);
        }
    }
}