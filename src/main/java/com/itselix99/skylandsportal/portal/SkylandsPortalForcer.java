package com.itselix99.skylandsportal.portal;

import com.itselix99.skylandsportal.SkylandsPortal;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.dimension.PortalForcer;

import java.util.Random;

public class SkylandsPortalForcer extends PortalForcer {
    private final Random random = new Random();

    public SkylandsPortalForcer() {
    }

    public void moveToPortal(World world, Entity entity) {
        if (!this.teleportToValidPortal(world, entity)) {
            this.createPortal(world, entity);
            this.teleportToValidPortal(world, entity);
        }
    }

    public boolean teleportToValidPortal(World world, Entity entity) {
        short var3 = 128;
        double var4 = -1.0;
        int var6 = 0;
        int var7 = 0;
        int var8 = 0;
        int var9 = MathHelper.floor(entity.x);
        int var10 = MathHelper.floor(entity.z);

        double var18;
        for (int var11 = var9 - var3; var11 <= var9 + var3; ++var11) {
            double var12 = (double) var11 + 0.5 - entity.x;

            for (int var14 = var10 - var3; var14 <= var10 + var3; ++var14) {
                double var15 = (double) var14 + 0.5 - entity.z;

                for (int var17 = 127; var17 >= 0; --var17) {
                    if (world.getBlockId(var11, var17, var14) == SkylandsPortal.SKYLANDS_PORTAL.id) {
                        while (world.getBlockId(var11, var17 - 1, var14) == SkylandsPortal.SKYLANDS_PORTAL.id) {
                            --var17;
                        }

                        var18 = (double) var17 + 0.5 - entity.y;
                        double var20 = var12 * var12 + var18 * var18 + var15 * var15;
                        if (var4 < 0.0 || var20 < var4) {
                            var4 = var20;
                            var6 = var11;
                            var7 = var17;
                            var8 = var14;
                        }
                    }
                }
            }
        }

        if (var4 >= 0.0) {
            double var22 = (double) var6 + 0.5;
            double var16 = (double) var7 + 0.5;
            var18 = (double) var8 + 0.5;
            if (world.getBlockId(var6 - 1, var7, var8) == SkylandsPortal.SKYLANDS_PORTAL.id) {
                var22 -= 0.5;
            }

            if (world.getBlockId(var6 + 1, var7, var8) == SkylandsPortal.SKYLANDS_PORTAL.id) {
                var22 += 0.5;
            }

            if (world.getBlockId(var6, var7, var8 - 1) == SkylandsPortal.SKYLANDS_PORTAL.id) {
                var18 -= 0.5;
            }

            if (world.getBlockId(var6, var7, var8 + 1) == SkylandsPortal.SKYLANDS_PORTAL.id) {
                var18 += 0.5;
            }

            entity.setPositionAndAnglesKeepPrevAngles(var22, var16, var18, entity.yaw, 0.0F);
            entity.velocityX = entity.velocityY = entity.velocityZ = 0.0;
            return true;
        } else {
            return false;
        }
    }

    public boolean createPortal(World world, Entity entity) {
        byte var3 = 16;
        double var4 = -1.0;
        int var6 = MathHelper.floor(entity.x);
        int var7 = MathHelper.floor(entity.y);
        int var8 = MathHelper.floor(entity.z);
        int var9 = var6;
        int var10 = var7;
        int var11 = var8;
        int var12 = 0;
        int var13 = this.random.nextInt(4);

        int var14;
        double var15;
        int var17;
        double var18;
        int var20;
        int var21;
        int var22;
        int var23;
        int var24;
        int var25;
        int var26;
        int var27;
        int var28;
        double var52;
        double var62;
        for (var14 = var6 - var3; var14 <= var6 + var3; ++var14) {
            var15 = (double) var14 + 0.5 - entity.x;

            for (var17 = var8 - var3; var17 <= var8 + var3; ++var17) {
                var18 = (double) var17 + 0.5 - entity.z;

                label296:
                for (var20 = 127; var20 >= 0; --var20) {
                    if (world.isAir(var14, var20, var17)) {
                        while (var20 > 0 && world.isAir(var14, var20 - 1, var17)) {
                            --var20;
                        }

                        for (var21 = var13; var21 < var13 + 4; ++var21) {
                            var22 = var21 % 2;
                            var23 = 1 - var22;
                            if (var21 % 4 >= 2) {
                                var22 = -var22;
                                var23 = -var23;
                            }

                            for (var24 = 0; var24 < 3; ++var24) {
                                for (var25 = 0; var25 < 4; ++var25) {
                                    for (var26 = -1; var26 < 4; ++var26) {
                                        var27 = var14 + (var25 - 1) * var22 + var24 * var23;
                                        var28 = var20 + var26;
                                        int var29 = var17 + (var25 - 1) * var23 - var24 * var22;
                                        if (var26 < 0 && !this.blockIsGood(world.getBlockId(var27, var28, var29), world.getBlockMeta(var27, var28, var29)) || var26 >= 0 && !world.isAir(var27, var28, var29)) {
                                            continue label296;
                                        }
                                    }
                                }
                            }

                            var52 = (double) var20 + 0.5 - entity.y;
                            var62 = var15 * var15 + var52 * var52 + var18 * var18;
                            if (var4 < 0.0 || var62 < var4) {
                                var4 = var62;
                                var9 = var14;
                                var10 = var20;
                                var11 = var17;
                                var12 = var21 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (var4 < 0.0) {
            for (var14 = var6 - var3; var14 <= var6 + var3; ++var14) {
                var15 = (double) var14 + 0.5 - entity.x;

                for (var17 = var8 - var3; var17 <= var8 + var3; ++var17) {
                    var18 = (double) var17 + 0.5 - entity.z;

                    label234:
                    for (var20 = 127; var20 >= 0; --var20) {
                        if (world.isAir(var14, var20, var17)) {
                            while (world.isAir(var14, var20 - 1, var17) && var20 > 0) {
                                --var20;
                            }

                            for (var21 = var13; var21 < var13 + 2; ++var21) {
                                var22 = var21 % 2;
                                var23 = 1 - var22;

                                for (var24 = 0; var24 < 4; ++var24) {
                                    for (var25 = -1; var25 < 4; ++var25) {
                                        var26 = var14 + (var24 - 1) * var22;
                                        var27 = var20 + var25;
                                        var28 = var17 + (var24 - 1) * var23;
                                        if (var25 < 0 && !this.blockIsGood(world.getBlockId(var26, var27, var28), world.getBlockMeta(var26, var27, var28)) || var25 >= 0 && !world.isAir(var26, var27, var28)) {
                                            continue label234;
                                        }
                                    }
                                }

                                var52 = (double) var20 + 0.5 - entity.y;
                                var62 = var15 * var15 + var52 * var52 + var18 * var18;
                                if (var4 < 0.0 || var62 < var4) {
                                    var4 = var62;
                                    var9 = var14;
                                    var10 = var20;
                                    var11 = var17;
                                    var12 = var21 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int var32 = var9;
        int var16 = var10;
        var17 = var11;
        int var36 = var12 % 2;
        int var19 = 1 - var36;
        if (var12 % 4 >= 2) {
            var36 = -var36;
            var19 = -var19;
        }

        boolean flag;
        if (var4 < 0.0) {
            if (var10 < 70) {
                var10 = 70;
            }

            if (var10 > 118) {
                var10 = 118;
            }

            var16 = var10;

            for (var20 = -1; var20 <= 1; ++var20) {
                for (var21 = 1; var21 < 3; ++var21) {
                    for (var22 = -1; var22 < 3; ++var22) {
                        var23 = var32 + (var21 - 1) * var36 + var20 * var19;
                        var24 = var16 + var22;
                        var25 = var17 + (var21 - 1) * var19 - var20 * var36;
                        flag = var22 < 0;
                        world.setBlock(var23, var24, var25, flag ? Block.GOLD_BLOCK.id : 0);
                    }
                }
            }
        }

        for (var20 = 0; var20 < 4; ++var20) {
            world.pauseTicking = true;

            for (var21 = 0; var21 < 4; ++var21) {
                for (var22 = -1; var22 < 4; ++var22) {
                    var23 = var32 + (var21 - 1) * var36;
                    var24 = var16 + var22;
                    var25 = var17 + (var21 - 1) * var19;
                    flag = var21 == 0 || var21 == 3 || var22 == -1 || var22 == 3;
                    world.setBlock(var23, var24, var25, flag ? Block.GOLD_BLOCK.id : SkylandsPortal.SKYLANDS_PORTAL.id);
                }
            }

            world.pauseTicking = false;

            for (var21 = 0; var21 < 4; ++var21) {
                for (var22 = -1; var22 < 4; ++var22) {
                    var23 = var32 + (var21 - 1) * var36;
                    var24 = var16 + var22;
                    var25 = var17 + (var21 - 1) * var19;
                    world.notifyNeighbors(var23, var24, var25, world.getBlockId(var23, var24, var25));
                }
            }
        }

        return true;
    }

    public boolean blockIsGood(int block, int meta) {
        if (block == 0) {
            return false;
        } else if (!Block.BLOCKS[block].material.isSolid()) {
            return false;
        } else {
            return meta != 0;
        }
    }
}