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

    public IChunkProvider getChunkProvider() {
        return new ChunkProviderSkylands(this.worldObj, this.worldObj.getRandomSeed());
    }

    public boolean canRespawnHere() {
        return false;
    }
}
