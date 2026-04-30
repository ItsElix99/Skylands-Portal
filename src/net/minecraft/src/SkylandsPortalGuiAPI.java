package net.minecraft.src;

public class SkylandsPortalGuiAPI {
    public static SettingBoolean overworldBiomeInSky;

    public static SettingInt skylandsPortalBlockId;
    public static SettingInt altAirBlockId;

    public static SettingInt goldenStickItemId;
    public static SettingInt magicWandItemId;

    public SkylandsPortalGuiAPI() {
        ModSettings skylandsPortalSettings = new ModSettings("skylandsportal");
        ModSettingScreen skylandsPortalSettingScreen = new ModSettingScreen("Skylands Portal");

        skylandsPortalSettings.append(overworldBiomeInSky = new SettingBoolean("overworldBiomesInSky", false));
        skylandsPortalSettings.append(altAirBlockId = new SettingInt("altAirBlockId", 200, 110, 255));
        skylandsPortalSettings.append(skylandsPortalBlockId = new SettingInt("skylandsPortalBlockId", 201, 110, 255));
        skylandsPortalSettings.append(goldenStickItemId = new SettingInt("goldenStickItemId", 10000, 113, 31999));
        skylandsPortalSettings.append(magicWandItemId = new SettingInt("magicWandItemId", 10001, 113, 31999));

        WidgetBoolean overworldBiomeInSkyWidget = new WidgetBoolean(overworldBiomeInSky, "Overworld Biomes In Sky");
        overworldBiomeInSkyWidget.addCallback(new ModAction(this, "setOverworldBiomesInSky"));

        WidgetInt altAirWidget = new WidgetInt(altAirBlockId, "Alt Air Id");
        altAirWidget.addCallback(new ModAction(this, "setAltAirBlockId"));

        WidgetInt skylandsPortalWidget = new WidgetInt(skylandsPortalBlockId, "Sky Portal Id");
        skylandsPortalWidget.addCallback(new ModAction(this, "setSkylandsPortalBlockId"));

        WidgetInt goldenStickWidget = new WidgetInt(goldenStickItemId, "Golden Stick Id");
        goldenStickWidget.addCallback(new ModAction(this, "setGoldenStickItemId"));

        WidgetInt magicWandWidget = new WidgetInt(magicWandItemId, "Magic Wand Id");
        magicWandWidget.addCallback(new ModAction(this, "setMagicWandItemId"));

        skylandsPortalSettingScreen.append(overworldBiomeInSkyWidget);
        skylandsPortalSettingScreen.append(altAirWidget);
        skylandsPortalSettingScreen.append(skylandsPortalWidget);
        skylandsPortalSettingScreen.append(goldenStickWidget);
        skylandsPortalSettingScreen.append(magicWandWidget);
        skylandsPortalSettings.load();

        overworldBiomeInSky.set(SkylandsPortalConfig.overworldBiomesInSky);
        altAirBlockId.set(SkylandsPortalConfig.altAirBlockId);
        skylandsPortalBlockId.set(SkylandsPortalConfig.skylandsPortalBlockId);
        goldenStickItemId.set(SkylandsPortalConfig.goldenStickItemId);
        magicWandItemId.set(SkylandsPortalConfig.magicWandItemId);
    }

    private void setOverworldBiomesInSky() {
        SkylandsPortalConfig.overworldBiomesInSky = overworldBiomeInSky.get();
        SkylandsPortalConfig.saveConfig();
    }

    private void setAltAirBlockId() {
        SkylandsPortalConfig.altAirBlockId = altAirBlockId.get();
        SkylandsPortalConfig.saveConfig();
    }

    private void setSkylandsPortalBlockId() {
        SkylandsPortalConfig.skylandsPortalBlockId = skylandsPortalBlockId.get();
        SkylandsPortalConfig.saveConfig();
    }

    private void setGoldenStickItemId() {
        SkylandsPortalConfig.goldenStickItemId = goldenStickItemId.get();
        SkylandsPortalConfig.saveConfig();
    }

    private void setMagicWandItemId() {
        SkylandsPortalConfig.magicWandItemId = magicWandItemId.get();
        SkylandsPortalConfig.saveConfig();
    }
}