package com.itselix99.skylandsportal.item;

import com.itselix99.skylandsportal.SkylandsPortal;
import com.itselix99.skylandsportal.block.SkylandsPortalBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateFlintAndSteelItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class MagicWandItem extends TemplateFlintAndSteelItem {

    public MagicWandItem(Identifier identifier) {
        super(identifier);
    }

    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {
        if (side == 0) {
            --y;
        }

        if (side == 1) {
            ++y;
        }

        if (side == 2) {
            --z;
        }

        if (side == 3) {
            ++z;
        }

        if (side == 4) {
            --x;
        }

        if (side == 5) {
            ++x;
        }

        int var8 = world.getBlockId(x, y, z);
        int var9 = world.getBlockId(x, y - 1, z);
        boolean var10 = ((SkylandsPortalBlock) SkylandsPortal.SKYLANDS_PORTAL).isValidPortalFrame(world, x, y, z);

        if (!var10) {
            return false;
        }

        if (var8 == 0 && var9 == Block.GOLD_BLOCK.id) {
            world.setBlock(x, y, z, SkylandsPortal.ALT_AIR.id);
        }

        stack.damage(65, user);
        return true;
    }
}