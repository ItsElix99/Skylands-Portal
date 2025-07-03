package com.itselix99.skylandsportal.event;

import com.itselix99.skylandsportal.SkylandsPortal;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.client.texture.atlas.ExpandableAtlas;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

import static com.itselix99.skylandsportal.SkylandsPortal.NAMESPACE;

public class TextureListener {
    public static int skylandsPortal;
    public static int altAir;
    public static int goldenStick;
    public static int magicStick;

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        ExpandableAtlas terrainAtlas = Atlases.getTerrain();
        ExpandableAtlas items = Atlases.getGuiItems();

        skylandsPortal = terrainAtlas.addTexture(NAMESPACE.id("block/skylands_portal")).index;
        altAir = terrainAtlas.addTexture(NAMESPACE.id("block/alt_air")).index;

        goldenStick = items.addTexture(NAMESPACE.id("item/golden_stick")).index;
        magicStick = items.addTexture(NAMESPACE.id("item/magic_wand")).index;

        SkylandsPortal.GOLDEN_STICK.setTextureId(goldenStick);
        SkylandsPortal.MAGIC_WAND.setTextureId(magicStick);
    }
}