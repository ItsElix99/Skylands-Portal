package com.itselix99.skylandsportal.config;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;
import net.glasslauncher.mods.gcapi3.api.ConfigRoot;

public class SkylandsPortalConfig {
    @ConfigRoot(value = "skylandsportal", visibleName = "Skylands Portal")
    public static final SPConfig SPConfig = new SPConfig();

    public static class SPConfig {
        @ConfigEntry(name = "Overworld Biomes in Skylands", multiplayerSynced = true)
        public Boolean overworldBiomesInSky = false;
    }
}