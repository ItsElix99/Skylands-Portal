package net.minecraft.src;

public class ItemMagicWand extends ItemFlintAndSteel {
    public ItemMagicWand(int var1) {
        super(var1);
    }

    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7) {
        if(var7 == 0) {
            --var5;
        }

        if(var7 == 1) {
            ++var5;
        }

        if(var7 == 2) {
            --var6;
        }

        if(var7 == 3) {
            ++var6;
        }

        if(var7 == 4) {
            --var4;
        }

        if(var7 == 5) {
            ++var4;
        }

        if (var2.dimension != -1) {
            int var8 = var3.getBlockId(var4, var5, var6);
            int var9 = var3.getBlockId(var4, var5 - 1, var6);
            boolean var10 = ((BlockSkylandsPortal) mod_SkylandsPortal.skylandsPortal).isValidPortalFrame(var3, var4, var5, var6);

            if (!var10) {
                return false;
            }

            if (var8 == 0 && var9 == Block.blockGold.blockID) {
                var3.setBlockWithNotify(var4, var5, var6, mod_SkylandsPortal.altAir.blockID);
            }

            var1.damageItem(65, var2);
        }

        return true;
    }
}
