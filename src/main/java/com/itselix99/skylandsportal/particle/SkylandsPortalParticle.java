package com.itselix99.skylandsportal.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.render.Tessellator;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class SkylandsPortalParticle extends Particle {
    private final float initialScale;
    private final double initialX;
    private final double initialY;
    private final double initialZ;

    public SkylandsPortalParticle(World world, double d, double e, double f, double g, double h, double i) {
        super(world, d, e, f, g, h, i);
        this.velocityX = g;
        this.velocityY = h;
        this.velocityZ = i;
        this.initialX = this.x = d;
        this.initialY = this.y = e;
        this.initialZ = this.z = f;
        float var14 = this.random.nextFloat() * 0.6F + 0.4F;
        this.initialScale = this.scale = this.random.nextFloat() * 0.2F + 0.5F;
        this.red = 0.39F * var14;
        this.green = 0.71F * var14;
        this.blue = 0.98F * var14;
        this.maxParticleAge = (int)(Math.random() * (double)10.0F) + 40;
        this.noClip = true;
        this.textureId = (int)(Math.random() * (double)8.0F);
    }

    public void render(Tessellator tessellator, float partialTicks, float horizontalSize, float verticalSize, float depthSize, float widthOffset, float heightOffset) {
        float var8 = ((float)this.particleAge + partialTicks) / (float)this.maxParticleAge;
        var8 = 1.0F - var8;
        var8 *= var8;
        var8 = 1.0F - var8;
        this.scale = this.initialScale * var8;
        super.render(tessellator, partialTicks, horizontalSize, verticalSize, depthSize, widthOffset, heightOffset);
    }

    public float getBrightnessAtEyes(float tickDelta) {
        float var2 = super.getBrightnessAtEyes(tickDelta);
        float var3 = (float)this.particleAge / (float)this.maxParticleAge;
        var3 *= var3;
        var3 *= var3;
        return var2 * (1.0F - var3) + var3;
    }

    public void tick() {
        this.prevX = this.x;
        this.prevY = this.y;
        this.prevZ = this.z;
        float var1 = (float)this.particleAge / (float)this.maxParticleAge;
        float var2 = var1;
        var1 = -var1 + var1 * var1 * 2.0F;
        var1 = 1.0F - var1;
        this.x = this.initialX + this.velocityX * (double)var1;
        this.y = this.initialY + this.velocityY * (double)var1 + (double)(1.0F - var2);
        this.z = this.initialZ + this.velocityZ * (double)var1;
        if (this.particleAge++ >= this.maxParticleAge) {
            this.markDead();
        }

    }
}