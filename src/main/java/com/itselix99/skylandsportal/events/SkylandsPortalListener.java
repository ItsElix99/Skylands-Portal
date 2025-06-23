package com.itselix99.skylandsportal.events;

import com.itselix99.skylandsportal.SkylandsPortal;
import com.itselix99.skylandsportal.blocks.SkylandsPortalBlock;
import com.itselix99.skylandsportal.entity.SkylandsLightningEntity;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.event.world.BlockSetEvent;

public class SkylandsPortalListener {
    @EventListener
    public static void blockSet(BlockSetEvent event) {
        if ((event.blockState.getBlock().id == SkylandsPortal.ALT_AIR.id) && event.world.getBlockId(event.x, event.y - 1, event.z) == Block.GOLD_BLOCK.id && ((SkylandsPortalBlock) SkylandsPortal.SKYLANDS_PORTAL).create(event.world, event.x, event.y, event.z)) {
            event.cancel();
            event.world.spawnGlobalEntity(new SkylandsLightningEntity(event.world, event.x, event.y, event.z));
            event.world.setBlock(event.x, event.y, event.z, SkylandsPortal.SKYLANDS_PORTAL.id);
        }
    }
}