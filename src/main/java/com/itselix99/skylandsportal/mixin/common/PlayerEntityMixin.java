package com.itselix99.skylandsportal.mixin.common;

import com.itselix99.skylandsportal.interfaces.SPCheckDimension;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements SPCheckDimension {
    @Unique private boolean nether;
    @Unique private boolean skylandsPortalOverlay;

    @Override
    public void sp_setNether(boolean nether) {
        this.nether = nether;
    }

    @Override
    public boolean sp_isNether() {
        return this.nether;
    }

    @Override
    public void sp_setSkylandsPortalOverlay(boolean bl) {
        this.skylandsPortalOverlay = bl;
    }

    @Override
    public boolean sp_getSkylandsPortalOverlay() {
        return this.skylandsPortalOverlay;
    }
}