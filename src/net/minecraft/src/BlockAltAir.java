package net.minecraft.src;

import java.util.Random;

public class BlockAltAir extends Block {
    protected BlockAltAir(int var1, int var2, Material var3) {
        super(var1, var2, var3);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
        return null;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void onBlockAdded(World var1, int var2, int var3, int var4){
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 20);
    }

    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
        var1.setBlock(var2, var3, var4, 0);
    }

    public int quantityDropped(Random var1) {
        return 0;
    }

    public boolean isCollidable() {
        return false;
    }
}