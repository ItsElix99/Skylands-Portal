package com.itselix99.skylandsportal.blocks;

import com.itselix99.skylandsportal.SkylandsPortal;
import com.itselix99.skylandsportal.events.TextureListener;
import com.itselix99.skylandsportal.portal.SkylandsPortalForcer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.dimension.PortalForcer;
import net.modificationstation.stationapi.api.block.CustomPortal;
import net.modificationstation.stationapi.api.template.block.TemplateNetherPortalBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.world.dimension.VanillaDimensions;

import java.util.Random;

public class SkylandsPortalBlock extends TemplateNetherPortalBlock implements CustomPortal {

    public SkylandsPortalBlock(Identifier identifier, int textureId) {
        super(identifier, textureId);
    }

    public int getTexture(int i, int j) {
        return TextureListener.skylandsPortal;
    }

    public boolean create(World world, int x, int y, int z) {
        byte var5 = 0;
        byte var6 = 0;
        if (world.getBlockId(x - 1, y, z) == Block.GOLD_BLOCK.id || world.getBlockId(x + 1, y, z) == Block.GOLD_BLOCK.id) {
            var5 = 1;
        }

        if (world.getBlockId(x, y, z - 1) == Block.GOLD_BLOCK.id || world.getBlockId(x, y, z + 1) == Block.GOLD_BLOCK.id) {
            var6 = 1;
        }

        if (var5 == var6) {
            return false;
        } else {
            if (world.getBlockId(x - var5, y, z - var6) == 0) {
                x -= var5;
                z -= var6;
            }

            for(int var7 = -1; var7 <= 2; ++var7) {
                for(int var8 = -1; var8 <= 3; ++var8) {
                    boolean var9 = var7 == -1 || var7 == 2 || var8 == -1 || var8 == 3;
                    if (var7 != -1 && var7 != 2 || var8 != -1 && var8 != 3) {
                        int var10 = world.getBlockId(x + var5 * var7, y + var8, z + var6 * var7);
                        if (var9) {
                            if (var10 != Block.GOLD_BLOCK.id) {
                                return false;
                            }
                            } else if (var10 != 0 && var10 != SkylandsPortal.ALT_AIR.id) {
                            return false;
                        }
                    }
                }
            }

            world.pauseTicking = true;

            for(int var11 = 0; var11 < 2; ++var11) {
                for(int var12 = 0; var12 < 3; ++var12) {
                    world.setBlock(x + var5 * var11, y + var12, z + var6 * var11, SkylandsPortal.SKYLANDS_PORTAL.id);
                }
            }

            world.pauseTicking = false;
            return true;
        }
    }

    public void neighborUpdate(World world, int x, int y, int z, int id) {
        byte var6 = 0;
        byte var7 = 1;
        if (world.getBlockId(x - 1, y, z) == this.id || world.getBlockId(x + 1, y, z) == this.id) {
            var6 = 1;
            var7 = 0;
        }

        int var8;
        for(var8 = y; world.getBlockId(x, var8 - 1, z) == this.id; --var8) {
        }

        if (world.getBlockId(x, var8 - 1, z) != Block.GOLD_BLOCK.id) {
            world.setBlock(x, y, z, 0);
        } else {
            int var9;
            for(var9 = 1; var9 < 4 && world.getBlockId(x, var8 + var9, z) == this.id; ++var9) {
            }

            if (var9 == 3 && world.getBlockId(x, var8 + var9, z) == Block.GOLD_BLOCK.id) {
                boolean var10 = world.getBlockId(x - 1, y, z) == this.id || world.getBlockId(x + 1, y, z) == this.id;
                boolean var11 = world.getBlockId(x, y, z - 1) == this.id || world.getBlockId(x, y, z + 1) == this.id;
                if (var10 && var11) {
                    world.setBlock(x, y, z, 0);
                } else if ((world.getBlockId(x + var6, y, z + var7) != Block.GOLD_BLOCK.id || world.getBlockId(x - var6, y, z - var7) != this.id) && (world.getBlockId(x - var6, y, z - var7) != Block.GOLD_BLOCK.id || world.getBlockId(x + var6, y, z + var7) != this.id)) {
                    world.setBlock(x, y, z, 0);
                }
            } else {
                world.setBlock(x, y, z, 0);
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public boolean isSideVisible(BlockView blockView, int x, int y, int z, int side) {
        if (blockView.getBlockId(x, y, z) == this.id) {
            return false;
        } else {
            boolean var6 = blockView.getBlockId(x - 1, y, z) == this.id && blockView.getBlockId(x - 2, y, z) != this.id;
            boolean var7 = blockView.getBlockId(x + 1, y, z) == this.id && blockView.getBlockId(x + 2, y, z) != this.id;
            boolean var8 = blockView.getBlockId(x, y, z - 1) == this.id && blockView.getBlockId(x, y, z - 2) != this.id;
            boolean var9 = blockView.getBlockId(x, y, z + 1) == this.id && blockView.getBlockId(x, y, z + 2) != this.id;
            boolean var10 = var6 || var7;
            boolean var11 = var8 || var9;
            if (var10 && side == 4) {
                return true;
            } else if (var10 && side == 5) {
                return true;
            } else if (var11 && side == 2) {
                return true;
            } else {
                return var11 && side == 3;
            }
        }
    }

    public int getDroppedItemCount(Random random) {
        return 0;
    }

    @Environment(EnvType.CLIENT)
    public int getRenderLayer() {
        return 1;
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (random.nextInt(100) == 0) {
            world.playSound((double)x + (double)0.5F, (double)y + (double)0.5F, (double)z + (double)0.5F, "portal.portal", 1.0F, random.nextFloat() * 0.4F + 0.8F);
        }

        for(int var6 = 0; var6 < 4; ++var6) {
            double var7 = (double)((float)x + random.nextFloat());
            double var9 = (double)((float)y + random.nextFloat());
            double var11 = (double)((float)z + random.nextFloat());
            double var13 = (double)0.0F;
            double var15 = (double)0.0F;
            double var17 = (double)0.0F;
            int var19 = random.nextInt(2) * 2 - 1;
            var13 = ((double)random.nextFloat() - (double)0.5F) * (double)0.5F;
            var15 = ((double)random.nextFloat() - (double)0.5F) * (double)0.5F;
            var17 = ((double)random.nextFloat() - (double)0.5F) * (double)0.5F;
            if (world.getBlockId(x - 1, y, z) != this.id && world.getBlockId(x + 1, y, z) != this.id) {
                var7 = (double)x + (double)0.5F + (double)0.25F * (double)var19;
                var13 = (double)(random.nextFloat() * 2.0F * (float)var19);
            } else {
                var11 = (double)z + (double)0.5F + (double)0.25F * (double)var19;
                var17 = (double)(random.nextFloat() * 2.0F * (float)var19);
            }

            world.addParticle("skylands_portal", var7, var9, var11, var13, var15, var17);
        }
    }

    public Identifier getDimension(PlayerEntity player) {
        return VanillaDimensions.SKYLANDS;
    }

    public PortalForcer getTravelAgent(PlayerEntity player) {
        return new SkylandsPortalForcer();
    }
}