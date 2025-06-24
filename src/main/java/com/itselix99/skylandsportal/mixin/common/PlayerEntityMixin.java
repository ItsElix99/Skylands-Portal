package com.itselix99.skylandsportal.mixin.common;

import com.itselix99.skylandsportal.intefaces.CheckDimension;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements CheckDimension {
    @Unique private boolean nether;
    @Unique private boolean skylandsPortalOverlay;

    public PlayerEntityMixin(World world) {
        super(world);
    }

    @Override
    public void sp_setNether(boolean nether) {
        this.nether = nether;
    }

    @Override
    public boolean sp_getNether() {
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