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

    public PlayerEntityMixin(World world) {
        super(world);
    }

    @Override
    public void slr_setNether(boolean nether) {
        this.nether = nether;
    }

    @Override
    public boolean slr_getNether() {
        return this.nether;
    }
}