package net.minecraft.src;

public class EntitySkylandsLightningBolt extends EntityLightningBolt {
    private int lightningState;
    private int boltLivingTime;

    public EntitySkylandsLightningBolt(World var1, double var2, double var4, double var6) {
        super(var1, var2, var4, var6);
        this.lightningState = 2;
        this.boltLivingTime = this.rand.nextInt(3) + 1;
    }

    public void onUpdate() {
        if (this.lightningState == 2) {
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "ambient.weather.thunder", 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
        }

        --this.lightningState;
        if (this.lightningState < 0) {
            if (this.boltLivingTime == 0) {
                this.setEntityDead();
            } else if (this.lightningState < -this.rand.nextInt(10)) {
                --this.boltLivingTime;
                this.lightningState = 1;
            }
        }

        if (this.lightningState >= 0) {
            this.worldObj.field_27172_i = 2;
        }

    }

    public boolean isInRangeToRenderVec3D(Vec3D var1) {
        return this.lightningState >= 0;
    }
}