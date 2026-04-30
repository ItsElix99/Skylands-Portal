package net.minecraft.src;

public class WorldProviderSkylands extends WorldProviderSky {
    public void registerWorldChunkManager() {
        if (SkylandsPortalConfig.overworldBiomesInSky) {
            this.worldChunkMgr = new WorldChunkManager(this.worldObj);
        } else {
            this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.sky, 0.5F, 0.0F);
        }

        this.worldType = 1;
    }

    public boolean canRespawnHere() {
        return false;
    }
}
