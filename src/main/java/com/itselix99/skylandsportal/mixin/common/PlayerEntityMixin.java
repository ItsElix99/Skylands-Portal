package com.itselix99.skylandsportal.mixin.common;

import com.itselix99.skylandsportal.interfaces.SPCheckDimension;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements SPCheckDimension {
    @Unique private boolean inSkylandsPortalBlock = false;

    @Override
    public void sp_setInSkylandsPortalBlock(boolean bl) {
        this.inSkylandsPortalBlock = bl;
    }

    @Override
    public boolean sp_isInSkylandsPortalBlock() {
        return this.inSkylandsPortalBlock;
    }
}