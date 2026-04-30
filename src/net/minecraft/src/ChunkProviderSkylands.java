package net.minecraft.src;

import java.util.Random;

public class ChunkProviderSkylands extends ChunkProviderSky {
    private World worldObj;
    private Random rand;
    private NoiseGeneratorOctaves field_28082_o;
    private double[] stoneNoise = new double[256];

    public ChunkProviderSkylands(World var1, long var2) {
        super(var1, var2);
        this.worldObj = var1;
        this.rand = new Random(var2);
        this.field_28082_o = new NoiseGeneratorOctaves(this.rand, 4);
    }

    public void replaceBlocksForBiome(int var1, int var2, byte[] var3, BiomeGenBase[] var4) {
        double var5 = 1.0D / 32.0D;
        this.stoneNoise = this.field_28082_o.generateNoiseOctaves(this.stoneNoise, var1 * 16, var2 * 16, 0, 16, 16, 1, var5 * 2.0D, var5 * 2.0D, var5 * 2.0D);

        for(int var7 = 0; var7 < 16; ++var7) {
            for(int var8 = 0; var8 < 16; ++var8) {
                BiomeGenBase var9 = var4[var8 + var7 * 16];
                int var10 = (int)(this.stoneNoise[var7 + var8 * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int var11 = -1;
                byte var12 = var9.topBlock;
                byte var13 = var9.fillerBlock;
                this.worldObj.getClass();

                for(int var14 = 127; var14 >= 0; --var14) {
                    int var10000 = var8 * 16 + var7;
                    this.worldObj.getClass();
                    int var15 = var10000 * 128 + var14;
                    byte var16 = var3[var15];
                    if(var16 == 0) {
                        var11 = -1;
                    } else if(var16 == Block.stone.blockID) {
                        if(var11 == -1) {
                            if(var10 <= 0) {
                                var12 = 0;
                                var13 = (byte)Block.stone.blockID;
                            }

                            var11 = var10;
                            if(var14 >= 0) {
                                var3[var15] = var12;
                            } else {
                                var3[var15] = var13;
                            }
                        } else if(var11 > 0) {
                            --var11;
                            var3[var15] = var13;
                            if(var11 == 0 && var13 == Block.sand.blockID) {
                                var11 = this.rand.nextInt(4);
                                var13 = (byte)Block.sandStone.blockID;
                            }
                        }
                    }
                }
            }
        }

    }

    public void populate(IChunkProvider var1, int var2, int var3) {
        if (SkylandsPortalConfig.overworldBiomesInSky) {
            BlockSand.fallInstantly = true;
            int var4 = var2 * 16;
            int var5 = var3 * 16;
            BiomeGenBase var6 = this.worldObj.getWorldChunkManager().getBiomeGenAt(var4 + 16, var5 + 16);
            this.rand.setSeed(this.worldObj.getRandomSeed());
            long var7 = this.rand.nextLong() / 2L * 2L + 1L;
            long var9 = this.rand.nextLong() / 2L * 2L + 1L;
            this.rand.setSeed((long)var2 * var7 + (long)var3 * var9 ^ this.worldObj.getRandomSeed());
            boolean var11 = false;

            int var12;
            int var13;
            int var14;
            Random var10000;
            if(!var11 && this.rand.nextInt(4) == 0) {
                var12 = var4 + this.rand.nextInt(16) + 8;
                var10000 = this.rand;
                this.worldObj.getClass();
                var13 = var10000.nextInt(128);
                var14 = var5 + this.rand.nextInt(16) + 8;
                (new WorldGenLakes(Block.waterStill.blockID)).generate(this.worldObj, this.rand, var12, var13, var14);
            }

            if(!var11 && this.rand.nextInt(8) == 0) {
                var12 = var4 + this.rand.nextInt(16) + 8;
                var10000 = this.rand;
                Random var10001 = this.rand;
                this.worldObj.getClass();
                var13 = var10000.nextInt(var10001.nextInt(128 - 8) + 8);
                var14 = var5 + this.rand.nextInt(16) + 8;
                this.worldObj.getClass();
                if(var13 < 63 || this.rand.nextInt(10) == 0) {
                    (new WorldGenLakes(Block.lavaStill.blockID)).generate(this.worldObj, this.rand, var12, var13, var14);
                }
            }

            for(var12 = 0; var12 < 8; ++var12) {
                var13 = var4 + this.rand.nextInt(16) + 8;
                var10000 = this.rand;
                this.worldObj.getClass();
                var14 = var10000.nextInt(128);
                int var15 = var5 + this.rand.nextInt(16) + 8;
                if((new WorldGenDungeons()).generate(this.worldObj, this.rand, var13, var14, var15)) {
                }
            }

            var6.func_35477_a(this.worldObj, this.rand, var4, var5);
            SpawnerAnimals.func_35957_a(this.worldObj, var6, var4 + 8, var5 + 8, 16, 16, this.rand);
            BlockSand.fallInstantly = false;
        } else {
            super.populate(var1, var2, var3);
        }
    }
}