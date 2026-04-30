package net.minecraft.src;

import net.minecraft.client.Minecraft;

import java.io.*;
import java.util.Properties;

public class SkylandsPortalConfig {
	private static final File configFile = new File(Minecraft.getMinecraftDir(), "config/SkylandsPortal.cfg");
	private static final LinkedProperties props = new LinkedProperties();

	public static boolean overworldBiomesInSky = false;
	public static int altAirBlockId = 200;
	public static int skylandsPortalBlockId = 201;
	public static int goldenStickItemId = 10000;
	public static int magicWandItemId = 10001;

	public static void loadConfig() {
		try {
			if (!configFile.exists()) {
				configFile.getParentFile().mkdirs();
				saveConfig();
			}

			FileInputStream in = new FileInputStream(configFile);
			props.load(in);
			in.close();

			overworldBiomesInSky = Boolean.parseBoolean(props.getProperty("overworldBiomesInSky", "false"));
			altAirBlockId = Integer.parseInt(props.getProperty("altAirBlockId", "200"));
			skylandsPortalBlockId = Integer.parseInt(props.getProperty("skylandsPortalBlockId", "201"));
			goldenStickItemId = Integer.parseInt(props.getProperty("goldenStickItemId", "10000"));
			magicWandItemId = Integer.parseInt(props.getProperty("magicWandItemId", "10001"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveConfig() {
		try {
			props.clear();

			props.setProperty("overworldBiomesInSky", String.valueOf(overworldBiomesInSky));
			props.setProperty("altAirBlockId", String.valueOf(altAirBlockId));
			props.setProperty("skylandsPortalBlockId", String.valueOf(skylandsPortalBlockId));
			props.setProperty("goldenStickItemId", String.valueOf(goldenStickItemId));
			props.setProperty("magicWandItemId", String.valueOf(magicWandItemId));

			FileOutputStream out = new FileOutputStream(configFile);
			storeWithoutTimestamp(props, out, "Skylands Portal Config");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void storeWithoutTimestamp(Properties props, OutputStream out, String comment) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
		if (comment != null && !comment.isEmpty()) {
			writer.write("# " + comment);
			writer.newLine();
		}

		for (Object keyObj : props.keySet()) {
			String key = (String) keyObj;
			String value = props.getProperty(key);

			if (key.startsWith("#")) {
				writer.newLine();
				writer.write(key);
			} else {
				writer.write(key + "=" + value);
			}
			writer.newLine();
		}
		writer.flush();
	}
}