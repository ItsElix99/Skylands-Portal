package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SPEntityRendererProxy extends EntityRendererProxy {
    private Minecraft mc;

    public SPEntityRendererProxy(Minecraft minecraft) {
        super(minecraft);
        this.mc = minecraft;
    }

    public void renderWorld(float var1, long var2) {
        if (this.mc.thePlayer.dimension == 1) {
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            if (this.mc.renderViewEntity == null) {
                this.mc.renderViewEntity = this.mc.thePlayer;
            }

            this.getMouseOver(var1);
            EntityLiving var4 = this.mc.renderViewEntity;
            RenderGlobal var5 = this.mc.renderGlobal;
            EffectRenderer var6 = this.mc.effectRenderer;
            double var7 = var4.lastTickPosX + (var4.posX - var4.lastTickPosX) * (double) var1;
            double var9 = var4.lastTickPosY + (var4.posY - var4.lastTickPosY) * (double) var1;
            double var11 = var4.lastTickPosZ + (var4.posZ - var4.lastTickPosZ) * (double) var1;
            IChunkProvider var13 = this.mc.theWorld.getIChunkProvider();
            int var16;
            if (var13 instanceof ChunkProviderLoadOrGenerate) {
                ChunkProviderLoadOrGenerate var14 = (ChunkProviderLoadOrGenerate) var13;
                int var15 = MathHelper.floor_float((float) ((int) var7)) >> 4;
                var16 = MathHelper.floor_float((float) ((int) var11)) >> 4;
                var14.setCurrentChunkOver(var15, var16);
            }

            for (int var18 = 0; var18 < 2; ++var18) {
                if (this.mc.gameSettings.anaglyph) {
                    anaglyphField = var18;
                    if (anaglyphField == 0) {
                        GL11.glColorMask(false, true, true, false);
                    } else {
                        GL11.glColorMask(true, false, false, false);
                    }
                }

                GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
                try {
                    Method updateFogColor = EntityRenderer.class.getDeclaredMethod("g", float.class);
                    updateFogColor.setAccessible(true);
                    updateFogColor.invoke(this, var1);
                } catch (Exception e) {
                    try {
                        Method updateFogColor = EntityRenderer.class.getDeclaredMethod("updateFogColor", float.class);
                        updateFogColor.setAccessible(true);
                        updateFogColor.invoke(this, var1);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_COLOR_BUFFER_BIT);
                GL11.glEnable(GL11.GL_CULL_FACE);

                try {
                    Method setupCameraTransform = EntityRenderer.class.getDeclaredMethod("a", float.class, int.class);
                    setupCameraTransform.setAccessible(true);
                    setupCameraTransform.invoke(this, var1, var18);
                } catch (Exception e) {
                    try {
                        Method setupCameraTransform = EntityRenderer.class.getDeclaredMethod("setupCameraTransform", float.class, int.class);
                        setupCameraTransform.setAccessible(true);
                        setupCameraTransform.invoke(this, var1, var18);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                ClippingHelperImpl.getInstance();
                if (this.mc.gameSettings.renderDistance < 2) {
                    try {
                        Method setupFog = EntityRenderer.class.getDeclaredMethod("a", int.class, float.class);
                        setupFog.setAccessible(true);
                        setupFog.invoke(this, -1, var1);
                    } catch (Exception e) {
                        try {
                            Method setupFog = EntityRenderer.class.getDeclaredMethod("setupFog", int.class, float.class);
                            setupFog.setAccessible(true);
                            setupFog.invoke(this, -1, var1);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    this.renderSky(var1, var5);
                }

                GL11.glEnable(GL11.GL_FOG);
                try {
                    Method setupFog = EntityRenderer.class.getDeclaredMethod("a", int.class, float.class);
                    setupFog.setAccessible(true);
                    setupFog.invoke(this, 1, var1);
                } catch (Exception e) {
                    try {
                        Method setupFog = EntityRenderer.class.getDeclaredMethod("setupFog", int.class, float.class);
                        setupFog.setAccessible(true);
                        setupFog.invoke(this, 1, var1);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                if (this.mc.gameSettings.ambientOcclusion) {
                    GL11.glShadeModel(GL11.GL_SMOOTH);
                }

                Frustrum var19 = new Frustrum();
                var19.setPosition(var7, var9, var11);
                this.mc.renderGlobal.clipRenderersByFrustrum(var19, var1);
                if (var18 == 0) {
                    while (!this.mc.renderGlobal.updateRenderers(var4, false) && var2 != 0L) {
                        long var20 = var2 - System.nanoTime();
                        if (var20 < 0L || var20 > 1000000000L) {
                            break;
                        }
                    }
                }

                try {
                    Method setupFog = EntityRenderer.class.getDeclaredMethod("a", int.class, float.class);
                    setupFog.setAccessible(true);
                    setupFog.invoke(this, 0, var1);
                } catch (Exception e) {
                    try {
                        Method setupFog = EntityRenderer.class.getDeclaredMethod("setupFog", int.class, float.class);
                        setupFog.setAccessible(true);
                        setupFog.invoke(this, 0, var1);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                GL11.glEnable(GL11.GL_FOG);
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/terrain.png"));
                RenderHelper.disableStandardItemLighting();
                var5.sortAndRender(var4, 0, (double) var1);
                GL11.glShadeModel(GL11.GL_FLAT);
                EntityPlayer var21;
                if (this.field_35823_q == 0) {
                    RenderHelper.enableStandardItemLighting();
                    var5.renderEntities(var4.getPosition(var1), var19, var1);
                    this.func_35806_b((double) var1);
                    var6.func_1187_b(var4, var1);
                    RenderHelper.disableStandardItemLighting();
                    try {
                        Method setupFog = EntityRenderer.class.getDeclaredMethod("a", int.class, float.class);
                        setupFog.setAccessible(true);
                        setupFog.invoke(this, 0, var1);
                    } catch (Exception e) {
                        try {
                            Method setupFog = EntityRenderer.class.getDeclaredMethod("setupFog", int.class, float.class);
                            setupFog.setAccessible(true);
                            setupFog.invoke(this, 0, var1);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    var6.renderParticles(var4, var1);
                    this.func_35810_a((double) var1);
                    if (this.mc.objectMouseOver != null && var4.isInsideOfMaterial(Material.water) && var4 instanceof EntityPlayer) {
                        var21 = (EntityPlayer) var4;
                        GL11.glDisable(GL11.GL_ALPHA_TEST);
                        var5.drawBlockBreaking(var21, this.mc.objectMouseOver, 0, var21.inventory.getCurrentItem(), var1);
                        var5.drawSelectionBox(var21, this.mc.objectMouseOver, 0, var21.inventory.getCurrentItem(), var1);
                        GL11.glEnable(GL11.GL_ALPHA_TEST);
                    }
                }

                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_CULL_FACE);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glDepthMask(true);
                try {
                    Method setupFog = EntityRenderer.class.getDeclaredMethod("a", int.class, float.class);
                    setupFog.setAccessible(true);
                    setupFog.invoke(this, 0, var1);
                } catch (Exception e) {
                    try {
                        Method setupFog = EntityRenderer.class.getDeclaredMethod("setupFog", int.class, float.class);
                        setupFog.setAccessible(true);
                        setupFog.invoke(this, 0, var1);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/terrain.png"));
                if (this.mc.gameSettings.fancyGraphics) {
                    if (this.mc.gameSettings.ambientOcclusion) {
                        GL11.glShadeModel(GL11.GL_SMOOTH);
                    }

                    GL11.glColorMask(false, false, false, false);
                    var16 = var5.sortAndRender(var4, 1, (double) var1);
                    if (this.mc.gameSettings.anaglyph) {
                        if (anaglyphField == 0) {
                            GL11.glColorMask(false, true, true, true);
                        } else {
                            GL11.glColorMask(true, false, false, true);
                        }
                    } else {
                        GL11.glColorMask(true, true, true, true);
                    }

                    if (var16 > 0) {
                        var5.renderAllRenderLists(1, (double) var1);
                    }

                    GL11.glShadeModel(GL11.GL_FLAT);
                } else {
                    var5.sortAndRender(var4, 1, (double) var1);
                }

                GL11.glDepthMask(true);
                GL11.glEnable(GL11.GL_CULL_FACE);
                GL11.glDisable(GL11.GL_BLEND);

                double cameraZoom = 1.0D;
                try {
                    Field cameraZoom1 = EntityRenderer.class.getDeclaredField("Q");
                    cameraZoom1.setAccessible(true);
                    cameraZoom = (double) cameraZoom1.get(this);
                } catch (Exception e) {
                    try {
                        Field cameraZoom1 = EntityRenderer.class.getDeclaredField("cameraZoom");
                        cameraZoom1.setAccessible(true);
                        cameraZoom = (double) cameraZoom1.get(this);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                if (cameraZoom == 1.0D && var4 instanceof EntityPlayer && this.mc.objectMouseOver != null && !var4.isInsideOfMaterial(Material.water)) {
                    var21 = (EntityPlayer) var4;
                    GL11.glDisable(GL11.GL_ALPHA_TEST);
                    var5.drawBlockBreaking(var21, this.mc.objectMouseOver, 0, var21.inventory.getCurrentItem(), var1);
                    var5.drawSelectionBox(var21, this.mc.objectMouseOver, 0, var21.inventory.getCurrentItem(), var1);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                }

                this.renderRainSnow(var1);
                GL11.glDisable(GL11.GL_FOG);
                GL11.glPushMatrix();
                try {
                    Method setupFog = EntityRenderer.class.getDeclaredMethod("a", int.class, float.class);
                    setupFog.setAccessible(true);
                    setupFog.invoke(this, 0, var1);
                } catch (Exception e) {
                    try {
                        Method setupFog = EntityRenderer.class.getDeclaredMethod("setupFog", int.class, float.class);
                        setupFog.setAccessible(true);
                        setupFog.invoke(this, 0, var1);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                GL11.glEnable(GL11.GL_FOG);
                var5.renderClouds(var1);
                GL11.glDisable(GL11.GL_FOG);
                try {
                    Method setupFog = EntityRenderer.class.getDeclaredMethod("a", int.class, float.class);
                    setupFog.setAccessible(true);
                    setupFog.invoke(this, 1, var1);
                } catch (Exception e) {
                    try {
                        Method setupFog = EntityRenderer.class.getDeclaredMethod("setupFog", int.class, float.class);
                        setupFog.setAccessible(true);
                        setupFog.invoke(this, 1, var1);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                GL11.glPopMatrix();
                if (cameraZoom == 1.0D) {
                    GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
                    try {
                        Method func_4135_b = EntityRenderer.class.getDeclaredMethod("b", float.class, int.class);
                        func_4135_b.setAccessible(true);
                        func_4135_b.invoke(this, var1, var18);
                    } catch (Exception e) {
                        try {
                            Method func_4135_b = EntityRenderer.class.getDeclaredMethod("func_4135_b", float.class, int.class);
                            func_4135_b.setAccessible(true);
                            func_4135_b.invoke(this, var1, var18);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }

                if (!this.mc.gameSettings.anaglyph) {
                    return;
                }
            }

            GL11.glColorMask(true, true, true, false);
        } else {
            super.renderWorld(var1, var2);
        }
    }

    public void renderSky(float var1, RenderGlobal renderGlobal) {
        if(!this.mc.theWorld.worldProvider.isNether) {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            Vec3D var2 = this.mc.theWorld.getSkyColor(this.mc.renderViewEntity, var1);
            float var3 = (float)var2.xCoord;
            float var4 = (float)var2.yCoord;
            float var5 = (float)var2.zCoord;
            float var7;
            float var8;
            if(this.mc.gameSettings.anaglyph) {
                float var6 = (var3 * 30.0F + var4 * 59.0F + var5 * 11.0F) / 100.0F;
                var7 = (var3 * 30.0F + var4 * 70.0F) / 100.0F;
                var8 = (var3 * 30.0F + var5 * 70.0F) / 100.0F;
                var3 = var6;
                var4 = var7;
                var5 = var8;
            }

            GL11.glColor3f(var3, var4, var5);
            Tessellator var16 = Tessellator.instance;
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_FOG);
            GL11.glColor3f(var3, var4, var5);
            try {
                Field glSkyList = RenderGlobal.class.getDeclaredField("x");
                glSkyList.setAccessible(true);
                GL11.glCallList((int) glSkyList.get(renderGlobal));
            } catch (Exception e) {
                try {
                    Field glSkyList = RenderGlobal.class.getDeclaredField("glSkyList");
                    glSkyList.setAccessible(true);
                    GL11.glCallList((int) glSkyList.get(renderGlobal));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            GL11.glDisable(GL11.GL_FOG);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            RenderHelper.disableStandardItemLighting();
            float[] var17 = this.mc.theWorld.worldProvider.calcSunriseSunsetColors(this.mc.theWorld.getCelestialAngle(var1), var1);
            float var9;
            float var10;
            float var11;
            float var12;
            if(var17 != null) {
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glShadeModel(GL11.GL_SMOOTH);
                GL11.glPushMatrix();
                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(MathHelper.sin(this.mc.theWorld.func_35456_d(var1)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
                var8 = var17[0];
                var9 = var17[1];
                var10 = var17[2];
                float var13;
                if(this.mc.gameSettings.anaglyph) {
                    var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
                    var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
                    var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
                    var8 = var11;
                    var9 = var12;
                    var10 = var13;
                }

                var16.startDrawing(6);
                var16.setColorRGBA_F(var8, var9, var10, var17[3]);
                var16.addVertex(0.0D, 100.0D, 0.0D);
                byte var19 = 16;
                var16.setColorRGBA_F(var17[0], var17[1], var17[2], 0.0F);

                for(int var20 = 0; var20 <= var19; ++var20) {
                    var13 = (float)var20 * (float)Math.PI * 2.0F / (float)var19;
                    float var14 = MathHelper.sin(var13);
                    float var15 = MathHelper.cos(var13);
                    var16.addVertex((double)(var14 * 120.0F), (double)(var15 * 120.0F), (double)(-var15 * 40.0F * var17[3]));
                }

                var16.draw();
                GL11.glPopMatrix();
                GL11.glShadeModel(GL11.GL_FLAT);
            }

            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            GL11.glPushMatrix();
            var7 = 1.0F - this.mc.theWorld.getRainStrength(var1);
            var8 = 0.0F;
            var9 = 0.0F;
            var10 = 0.0F;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, var7);
            GL11.glTranslatef(var8, var9, var10);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(this.mc.theWorld.getCelestialAngle(var1) * 360.0F, 1.0F, 0.0F, 0.0F);
            var11 = 30.0F;
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/terrain/sun.png"));
            var16.startDrawingQuads();
            var16.addVertexWithUV((double)(-var11), 100.0D, (double)(-var11), 0.0D, 0.0D);
            var16.addVertexWithUV((double)var11, 100.0D, (double)(-var11), 1.0D, 0.0D);
            var16.addVertexWithUV((double)var11, 100.0D, (double)var11, 1.0D, 1.0D);
            var16.addVertexWithUV((double)(-var11), 100.0D, (double)var11, 0.0D, 1.0D);
            var16.draw();
            var11 = 20.0F;
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/terrain/moon.png"));
            var16.startDrawingQuads();
            var16.addVertexWithUV((double)(-var11), -100.0D, (double)var11, 1.0D, 1.0D);
            var16.addVertexWithUV((double)var11, -100.0D, (double)var11, 0.0D, 1.0D);
            var16.addVertexWithUV((double)var11, -100.0D, (double)(-var11), 0.0D, 0.0D);
            var16.addVertexWithUV((double)(-var11), -100.0D, (double)(-var11), 1.0D, 0.0D);
            var16.draw();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            var12 = this.mc.theWorld.getStarBrightness(var1) * var7;
            if(var12 > 0.0F) {
                GL11.glColor4f(var12, var12, var12, var12);
                try {
                    Field starGLCallList = RenderGlobal.class.getDeclaredField("w");
                    starGLCallList.setAccessible(true);
                    GL11.glCallList((int) starGLCallList.get(renderGlobal));
                } catch (Exception e) {
                    try {
                        Field starGLCallList = RenderGlobal.class.getDeclaredField("starGLCallList");
                        starGLCallList.setAccessible(true);
                        GL11.glCallList((int) starGLCallList.get(renderGlobal));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_FOG);
            GL11.glPopMatrix();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glColor3f(0.0F, 0.0F, 0.0F);
            if(this.mc.theWorld.worldProvider.func_28112_c()) {
                GL11.glColor3f(var3 * 0.2F + 0.04F, var4 * 0.2F + 0.04F, var5 * 0.6F + 0.1F);
            } else {
                GL11.glColor3f(var3, var4, var5);
            }

            GL11.glPushMatrix();
            try {
                Field glSkyList2 = RenderGlobal.class.getDeclaredField("y");
                glSkyList2.setAccessible(true);
                GL11.glCallList((int) glSkyList2.get(renderGlobal));
            } catch (Exception e) {
                try {
                    Field glSkyList2 = RenderGlobal.class.getDeclaredField("glSkyList2");
                    glSkyList2.setAccessible(true);
                    GL11.glCallList((int) glSkyList2.get(renderGlobal));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDepthMask(true);
        }
    }
}