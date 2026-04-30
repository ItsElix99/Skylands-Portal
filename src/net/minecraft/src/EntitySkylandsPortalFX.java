package net.minecraft.src;

public class EntitySkylandsPortalFX extends EntityPortalFX {

    public EntitySkylandsPortalFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
        super(var1, var2, var4, var6, var8, var10, var12);
        float var14 = this.rand.nextFloat() * 0.6F + 0.4F;
        this.particleRed = 0.39F * var14;
        this.particleGreen = 0.71F * var14;
        this.particleBlue = 0.98F * var14;
    }
}