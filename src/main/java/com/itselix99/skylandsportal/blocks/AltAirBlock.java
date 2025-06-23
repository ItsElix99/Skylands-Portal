package com.itselix99.skylandsportal.blocks;

import com.itselix99.skylandsportal.events.TextureListener;
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

    public int getDroppedItemCount(Random random) {
        return 0;
    }

    public boolean hasCollision() {
        return false;
    }
}