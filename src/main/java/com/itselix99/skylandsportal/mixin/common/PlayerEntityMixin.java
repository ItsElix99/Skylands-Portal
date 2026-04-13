package com.itselix99.skylandsportal.mixin.common;

import com.itselix99.skylandsportal.interfaces.SPCheckDimension;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements SPCheckDimension {
    @Unique private int blockPortalId = -1;

    @Override
    public void sp_setBlockPortalId(int id) {
        this.blockPortalId = id;
    }

    @Override
    public int sp_getBlockPortalId() {
        return this.blockPortalId;
    }
}