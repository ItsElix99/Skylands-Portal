package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import sapi.IInterceptBlockSet;
import sapi.Loc;
import sapi.SAPI;

import javax.imageio.ImageIO;

import static net.minecraft.src.Block.soundGlassFootstep;

public class mod_SkylandsPortal extends BaseMod implements IInterceptBlockSet {
    public static Block skylandsPortal;
    public static Block altAir;

    public static Item goldenStick;
    public static Item magicWand;

    public mod_SkylandsPortal() {
        SkylandsPortalConfig.loadConfig();

        if(isGuiAPILoaded()) {
            new SkylandsPortalGuiAPI();
        }

        ModLoader.getMinecraftInstance().entityRenderer = new SPEntityRendererProxy(ModLoader.getMinecraftInstance());
        skylandsPortal = new BlockSkylandsPortal(SkylandsPortalConfig.skylandsPortalBlockId, ModLoader.addOverride("/terrain.png", "/skylandsportal/alt_air.png")).setHardness(-1.0F).setStepSound(soundGlassFootstep).setLightValue(12.0F / 16.0F).setBlockName("skylandsPortal");
        altAir = new BlockAltAir(SkylandsPortalConfig.altAirBlockId, ModLoader.addOverride("/terrain.png", "/skylandsportal/alt_air.png"), Material.air);
        ModLoader.RegisterBlock(skylandsPortal);
        ModLoader.RegisterBlock(altAir);
        ModLoader.AddName(skylandsPortal, "Skylands Portal");

        goldenStick = new Item(SkylandsPortalConfig.goldenStickItemId).setIconIndex(ModLoader.addOverride("/gui/items.png", "/skylandsportal/golden_stick.png")).setItemName("goldenStick");
        magicWand = new ItemMagicWand(SkylandsPortalConfig.magicWandItemId).setIconIndex(ModLoader.addOverride("/gui/items.png", "/skylandsportal/magic_wand.png")).setItemName("magicWand");
        ModLoader.AddName(goldenStick, "Golden Stick");
        ModLoader.AddName(magicWand, "Magic Wand of Skylands");

        ModLoader.AddShapelessRecipe(new ItemStack(goldenStick, 1), Item.stick, Item.ingotGold);
        ModLoader.AddRecipe(new ItemStack(magicWand, 1), "odo", "oso", "oso", 's', new ItemStack(goldenStick), 'd', new ItemStack(Item.diamond));

        try {
            ModLoader.getMinecraftInstance().renderEngine.registerTextureFX(new TextureSkylandsPortalFX(skylandsPortal.blockIndexInTexture, 1, 0, ImageIO.read(Minecraft.class.getResource("/skylandsportal/skylands_portal.png")), 1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        new DimensionSkylands();
        SAPI.interceptAdd(this);
        ModLoader.SetInGameHook(this, true, false);
    }

    public String Version() {
        return "v1.1.1-b1.8.1";
    }

    public boolean OnTickInGame(float ticks, Minecraft game) {
        float var16 = game.thePlayer.prevTimeInPortal + (game.thePlayer.timeInPortal - game.thePlayer.prevTimeInPortal) * ticks;
        if(var16 > 0.0F && game.thePlayer.portal == 1) {
            GL11.glEnable(GL11.GL_BLEND);
            ScaledResolution var19 = new ScaledResolution(game.gameSettings, game.displayWidth, game.displayHeight);
            int var23 = var19.getScaledWidth();
            int var22 = var19.getScaledHeight();
            this.renderSkylandsPortalOverlay(game, var16, var23, var22);
            GL11.glDisable(GL11.GL_BLEND);
        }
        return true;
    }

    private void renderSkylandsPortalOverlay(Minecraft mc, float var1, int var2, int var3) {
        if(var1 < 1.0F) {
            var1 *= var1;
            var1 *= var1;
            var1 = var1 * 0.8F + 0.2F;
        }

        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, var1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/terrain.png"));
        float var4 = (float)(mod_SkylandsPortal.skylandsPortal.blockIndexInTexture % 16) / 16.0F;
        float var5 = (float)(mod_SkylandsPortal.skylandsPortal.blockIndexInTexture / 16) / 16.0F;
        float var6 = (float)(mod_SkylandsPortal.skylandsPortal.blockIndexInTexture % 16 + 1) / 16.0F;
        float var7 = (float)(mod_SkylandsPortal.skylandsPortal.blockIndexInTexture / 16 + 1) / 16.0F;
        Tessellator var8 = Tessellator.instance;
        var8.startDrawingQuads();
        var8.addVertexWithUV(0.0D, (double)var3, -90.0D, (double)var4, (double)var7);
        var8.addVertexWithUV((double)var2, (double)var3, -90.0D, (double)var6, (double)var7);
        var8.addVertexWithUV((double)var2, 0.0D, -90.0D, (double)var6, (double)var5);
        var8.addVertexWithUV(0.0D, 0.0D, -90.0D, (double)var4, (double)var5);
        var8.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public boolean canIntercept(World var1, Loc var2, int var3) {
        if (var1.worldProvider.worldType != -1 && var3 == altAir.blockID) {
            return true;
        }

        return false;
    }

    public int intercept(World var1, Loc var2, int var3) {
        if (var3 == altAir.blockID) {
            if (var1.getBlockId(var2.x(), var2.y() - 1, var2.z()) == Block.blockGold.blockID && ((BlockSkylandsPortal) skylandsPortal).tryToCreatePortal(var1, var2.x(), var2.y(), var2.z())) {
                if (var1.worldProvider.worldType == 0 && var1.getSavedLightValue(EnumSkyBlock.Sky, var2.x(), var2.y(), var2.z()) > 12) {
                    var1.entityJoinedWorld(new EntitySkylandsLightningBolt(var1, var2.x(), var2.y(), var2.z()));
                }

                return skylandsPortal.blockID;
            }
        }

        return var3;
    }

    private static boolean isGuiAPILoaded() {
        try {
            Class.forName("GuiApiHelper");
            return true;
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("net.minecraft.src.GuiApiHelper");
                return true;
            } catch (ClassNotFoundException e1) {
                return false;
            }
        }
    }
}