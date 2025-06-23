package com.itselix99.skylandsportal.events;

import com.itselix99.skylandsportal.SkylandsPortal;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.client.texture.atlas.ExpandableAtlas;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class TextureListener {
    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

    public static int skylandsPortal;
    public static int altAir;
    public static int goldenStick;
    public static int magicStick;

    @Environment(EnvType.CLIENT)
    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        ExpandableAtlas terrainAtlas = Atlases.getTerrain();
        ExpandableAtlas items = Atlases.getGuiItems();

        skylandsPortal = terrainAtlas.addTexture(Identifier.of(MOD_ID, "block/skylands_portal")).index;
        altAir = terrainAtlas.addTexture(Identifier.of(MOD_ID, "block/alt_air")).index;

        goldenStick = items.addTexture(Identifier.of(MOD_ID, "item/golden_stick")).index;
        magicStick = items.addTexture(Identifier.of(MOD_ID, "item/magic_stick")).index;

        SkylandsPortal.GOLDEN_STICK.setTextureId(goldenStick);
        SkylandsPortal.MAGIC_WAND.setTextureId(magicStick);
    }
}