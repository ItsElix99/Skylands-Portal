package com.itselix99.skylandsportal.event;

import com.itselix99.skylandsportal.SkylandsPortal;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.ItemStack;
import paulevs.bhcreative.api.CreativeTab;
import paulevs.bhcreative.api.SimpleTab;
import paulevs.bhcreative.registry.TabRegistryEvent;

import static com.itselix99.skylandsportal.SkylandsPortal.NAMESPACE;

public class CreativeListener {
    public static CreativeTab SPBlocks;

    @EventListener
    public void onTabInit(TabRegistryEvent event) {
        SPBlocks = new SimpleTab(NAMESPACE.id("skylands_portal"), new ItemStack(SkylandsPortal.MAGIC_WAND));
        event.register(SPBlocks);

        SPBlocks.addItem(new ItemStack(SkylandsPortal.GOLDEN_STICK));
        SPBlocks.addItem(new ItemStack(SkylandsPortal.MAGIC_WAND));
    }
}