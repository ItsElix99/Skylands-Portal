package com.itselix99.skylandsportal.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class SkylandsPortalParticle extends PortalParticle {

    public SkylandsPortalParticle(World world, double d, double e, double f, double g, double h, double i) {
        super(world, d, e, f, g, h, i);
        float var14 = this.random.nextFloat() * 0.6F + 0.4F;
        this.red = 0.39F * var14;
        this.green = 0.71F * var14;
        this.blue = 0.98F * var14;
    }
}