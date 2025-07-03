package com.itselix99.skylandsportal.block;

import com.itselix99.skylandsportal.event.TextureListener;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class AltAirBlock extends TemplateBlock {

    public AltAirBlock(Identifier identifier, int tex, Material material) {
        super(identifier, tex, material);
    }

    public int getTexture(int side) {
        return TextureListener.altAir;
    }

    public Box getCollisionShape(World world, int x, int y, int z) {
        return null;
    }

    public boolean isOpaque() {
        return false;
    }

    public boolean isFullCube() {
        return false;
    }

    public void onPlaced(World world, int x, int y, int z){
        world.scheduleBlockUpdate(x, y, z, this.id, 20);
    }

    public void onTick(World world, int x, int y, int z, Random random) {
        world.setBlock(x, y, z, 0);
    }

    public int getDroppedItemCount(Random random) {
        return 0;
    }

    public boolean hasCollision() {
        return false;
    }
}