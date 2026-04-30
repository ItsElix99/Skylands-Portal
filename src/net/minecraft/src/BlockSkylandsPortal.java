package net.minecraft.src;

import java.util.Random;

public class BlockSkylandsPortal extends BlockPortal {
    public BlockSkylandsPortal(int var1, int var2) {
        super(var1, var2);
    }

    public boolean tryToCreatePortal(World var1, int var2, int var3, int var4) {
        byte var5 = 0;
        byte var6 = 0;
        if(var1.getBlockId(var2 - 1, var3, var4) == Block.blockGold.blockID || var1.getBlockId(var2 + 1, var3, var4) == Block.blockGold.blockID) {
            var5 = 1;
        }

        if(var1.getBlockId(var2, var3, var4 - 1) == Block.blockGold.blockID || var1.getBlockId(var2, var3, var4 + 1) == Block.blockGold.blockID) {
            var6 = 1;
        }

        if(var5 == var6) {
            return false;
        } else {
            if(var1.getBlockId(var2 - var5, var3, var4 - var6) == 0) {
                var2 -= var5;
                var4 -= var6;
            }

            int var7;
            int var8;
            for(var7 = -1; var7 <= 2; ++var7) {
                for(var8 = -1; var8 <= 3; ++var8) {
                    boolean var9 = var7 == -1 || var7 == 2 || var8 == -1 || var8 == 3;
                    if(var7 != -1 && var7 != 2 || var8 != -1 && var8 != 3) {
                        int var10 = var1.getBlockId(var2 + var5 * var7, var3 + var8, var4 + var6 * var7);
                        if(var9) {
                            if(var10 != Block.blockGold.blockID) {
                                return false;
                            }
                        } else if(var10 != 0 && var10 != mod_SkylandsPortal.altAir.blockID) {
                            return false;
                        }
                    }
                }
            }

            var1.editingBlocks = true;

            for(var7 = 0; var7 < 2; ++var7) {
                for(var8 = 0; var8 < 3; ++var8) {
                    var1.setBlockWithNotify(var2 + var5 * var7, var3 + var8, var4 + var6 * var7, mod_SkylandsPortal.skylandsPortal.blockID);
                }
            }

            var1.editingBlocks = false;
            return true;
        }
    }

    public boolean isValidPortalFrame(World world, int x, int y, int z) {
        byte offsetX = 0;
        byte offsetZ = 0;

        if (world.getBlockId(x - 1, y, z) == Block.blockGold.blockID || world.getBlockId(x + 1, y, z) == Block.blockGold.blockID) {
            offsetX = 1;
        }

        if (world.getBlockId(x, y, z - 1) == Block.blockGold.blockID || world.getBlockId(x, y, z + 1) == Block.blockGold.blockID) {
            offsetZ = 1;
        }

        if (offsetX == offsetZ) {
            return false;
        }

        if (world.getBlockId(x - offsetX, y, z - offsetZ) == 0) {
            x -= offsetX;
            z -= offsetZ;
        }

        for (int dx = -1; dx <= 2; dx++) {
            for (int dy = -1; dy <= 3; dy++) {
                boolean isFrame = dx == -1 || dx == 2 || dy == -1 || dy == 3;
                int checkX = x + offsetX * dx;
                int checkY = y + dy;
                int checkZ = z + offsetZ * dx;

                int blockId = world.getBlockId(checkX, checkY, checkZ);

                if (isFrame) {
                    if (blockId != Block.blockGold.blockID) {
                        return false;
                    }
                } else {
                    if (blockId != 0 && blockId != mod_SkylandsPortal.altAir.blockID) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {
        byte var6 = 0;
        byte var7 = 1;
        if(var1.getBlockId(var2 - 1, var3, var4) == this.blockID || var1.getBlockId(var2 + 1, var3, var4) == this.blockID) {
            var6 = 1;
            var7 = 0;
        }

        int var8;
        for(var8 = var3; var1.getBlockId(var2, var8 - 1, var4) == this.blockID; --var8) {
        }

        if(var1.getBlockId(var2, var8 - 1, var4) != Block.blockGold.blockID) {
            var1.setBlockWithNotify(var2, var3, var4, 0);
        } else {
            int var9;
            for(var9 = 1; var9 < 4 && var1.getBlockId(var2, var8 + var9, var4) == this.blockID; ++var9) {
            }

            if(var9 == 3 && var1.getBlockId(var2, var8 + var9, var4) == Block.blockGold.blockID) {
                boolean var10 = var1.getBlockId(var2 - 1, var3, var4) == this.blockID || var1.getBlockId(var2 + 1, var3, var4) == this.blockID;
                boolean var11 = var1.getBlockId(var2, var3, var4 - 1) == this.blockID || var1.getBlockId(var2, var3, var4 + 1) == this.blockID;
                if(var10 && var11) {
                    var1.setBlockWithNotify(var2, var3, var4, 0);
                } else if((var1.getBlockId(var2 + var6, var3, var4 + var7) != Block.blockGold.blockID || var1.getBlockId(var2 - var6, var3, var4 - var7) != this.blockID) && (var1.getBlockId(var2 - var6, var3, var4 - var7) != Block.blockGold.blockID || var1.getBlockId(var2 + var6, var3, var4 + var7) != this.blockID)) {
                    var1.setBlockWithNotify(var2, var3, var4, 0);
                }
            } else {
                var1.setBlockWithNotify(var2, var3, var4, 0);
            }
        }
    }

    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5) {
        if(var5.ridingEntity == null && var5.riddenByEntity == null) {
            if(var5 instanceof EntityPlayerSP) {
                EntityPlayerSP entityplayersp = (EntityPlayerSP)var5;
                ((PlayerBaseSAPI)((PlayerBaseSAPI)PlayerAPI.getPlayerBase(entityplayersp, PlayerBaseSAPI.class))).portal = this.getDimNumber();
            }

            var5.setInPortal();
        }

    }

    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
        if(var5.nextInt(100) == 0) {
            var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "portal.portal", 1.0F, var5.nextFloat() * 0.4F + 0.8F);
        }

        for(int var6 = 0; var6 < 4; ++var6) {
            double var7 = (double)((float)var2 + var5.nextFloat());
            double var9 = (double)((float)var3 + var5.nextFloat());
            double var11 = (double)((float)var4 + var5.nextFloat());
            double var13 = 0.0D;
            double var15 = 0.0D;
            double var17 = 0.0D;
            int var19 = var5.nextInt(2) * 2 - 1;
            var13 = ((double)var5.nextFloat() - 0.5D) * 0.5D;
            var15 = ((double)var5.nextFloat() - 0.5D) * 0.5D;
            var17 = ((double)var5.nextFloat() - 0.5D) * 0.5D;
            if(var1.getBlockId(var2 - 1, var3, var4) != this.blockID && var1.getBlockId(var2 + 1, var3, var4) != this.blockID) {
                var7 = (double)var2 + 0.5D + 0.25D * (double)var19;
                var13 = (double)(var5.nextFloat() * 2.0F * (float)var19);
            } else {
                var11 = (double)var4 + 0.5D + 0.25D * (double)var19;
                var17 = (double)(var5.nextFloat() * 2.0F * (float)var19);
            }

            EntityPortalFX skylandsPortalParticle = new EntitySkylandsPortalFX(var1, var7, var9, var11, var13, var15, var17);
            ModLoader.getMinecraftInstance().effectRenderer.addEffect(skylandsPortalParticle);
        }

    }

    public int getDimNumber() {
        return 1;
    }
}