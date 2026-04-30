package com.itselix99.skylandsportal.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LightningEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SkylandsLightningEntity extends LightningEntity {
    private int ambientTick;
    private int remainingActions;

    public SkylandsLightningEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
        this.setPositionAndAnglesKeepPrevAngles(x, y, z, 0.0F, 0.0F);
        this.ambientTick = 2;
        this.remainingActions = this.random.nextInt(3) + 1;
    }

    public void tick() {
        if (this.ambientTick == 2) {
            this.world.playSound(this.x, this.y, this.z, "ambient.weather.thunder", 10000.0F, 0.8F + this.random.nextFloat() * 0.2F);
            this.world.playSound(this.x, this.y, this.z, "random.explode", 2.0F, 0.5F + this.random.nextFloat() * 0.2F);
        }

        --this.ambientTick;
        if (this.ambientTick < 0) {
            if (this.remainingActions == 0) {
                this.markDead();
            } else if (this.ambientTick < -this.random.nextInt(10)) {
                --this.remainingActions;
                this.ambientTick = 1;
            }
        }

        if (this.ambientTick >= 0) {
            this.world.lightningTicksLeft = 2;
        }

    }

    protected void initDataTracker() {
    }

    protected void readNbt(NbtCompound nbt) {
    }

    protected void writeNbt(NbtCompound nbt) {
    }

    @Environment(EnvType.CLIENT)
    public boolean shouldRender(Vec3d pos) {
        return this.ambientTick >= 0;
    }
}