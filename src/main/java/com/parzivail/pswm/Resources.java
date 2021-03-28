package com.parzivail.pswm;

import com.parzivail.pswm.dimension.PlanetInformation;
import com.parzivail.util.ui.Lumberjack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Resources
{
	public static HashMap<Integer, ResourceLocation> planetTextures = new HashMap<>();
	public static ArrayList<PlanetInformation> planetInformation = new ArrayList<>();

	public static final int[] PANEL_LIGHT_COLORS = { 0xF8E443, 0x0050A1, 0x4ACBFF, 0xD1A250, 0x42A904, 0xFB4B33 };

	public static class ConfigOptions
	{
		public static final String CAT_CORE = "core";
		public static final String CAT_DIM = "dimensions";
		public static final String CAT_GUI = "gui";
		public static final String CAT_ITEMS = "functionality";
		public static final String CAT_BIOMES = "biomes";

		public static int biomeTatooineId;
		public static int biomeHothId;
		public static int biomeKashyyykId;
		public static int biomeYavin4Id;
		public static int biomeEndorId;
		public static int biomeDagobahId;
		public static int biomeIlumId;
		public static int biomeMustafarId;
		public static int biomeSpaceId;

		public static int dimTatooineId;
		public static int dimHothId;
		public static int dimKashyyykId;
		public static int dimYavin4Id;
		public static int dimDagobahId;
		public static int dimMustafarId;
		public static int dimEndorId;
		public static int dimIlumId;
		public static int dimAlderaanId = 100;
		public static int dimBespinId = 101;
		public static int dimCoruscantId = 102;
		public static int dimDathomirId = 103;
		public static int dimGeonosisId = 104;
		public static int dimRylothId = 105;
		public static int dimKaminoId = 106;
		public static int dimKesselId = 107;
		public static int dimMandaloreId = 108;
		public static int dimMonCalamariId = 109;
		public static int dimNabooId = 110;
		public static int dimSullustId = 111;
		public static int dimUtapauId = 112;
		public static int dimJakkuId = 113;
		public static int dimTakodanaId = 114;
		public static int dimDQarId = 115;
		public static int dimAhchToId = 116;
		public static int dimDeathStarId = 117;
		public static int dimSpaceId = 118;

		public static int lightsaberDamage;

		public static boolean enableCreditsOverlay;
		public static boolean enableBuckets;
		public static boolean enableLightsaberHum;
		public static boolean beshOverride;
		public static boolean enableTabOriginal = true;
		public static boolean enableTabSequel = true;
		public static boolean enableBetaFeatures;
		public static boolean enableLightsaberLight;

		public static Configuration config;
		public static File configFile;

		public static void loadConfigOptions()
		{
			enableTabOriginal = config.get(CAT_CORE, "Enable Original Trilogy Tab", true, "Whether or not the Original Trilogy tab is enabled").setRequiresMcRestart(true).getBoolean();
			enableTabSequel = config.get(CAT_CORE, "Enable Sequel Trilogy Tab", true, "Whether or not the Sequel Trilogy tab is enabled").setRequiresMcRestart(true).getBoolean();
			enableBetaFeatures = config.get(CAT_CORE, "Enable Debug Mode", false, "Development purposes only. Do not use!").setRequiresMcRestart(true).getBoolean();
			beshOverride = config.get(CAT_CORE, "Use Aurebesh Font", false, "Enable for a fun time!").setRequiresMcRestart(true).getBoolean();

			dimTatooineId = config.get(CAT_DIM, "tatooine", 2).setRequiresMcRestart(true).getInt();
			dimHothId = config.get(CAT_DIM, "hoth", 3).setRequiresMcRestart(true).getInt();
			dimKashyyykId = config.get(CAT_DIM, "kashyyyk", 4).setRequiresMcRestart(true).getInt();
			dimYavin4Id = config.get(CAT_DIM, "yavin", 5).setRequiresMcRestart(true).getInt();
			dimEndorId = config.get(CAT_DIM, "endor", 6).setRequiresMcRestart(true).getInt();
			dimIlumId = config.get(CAT_DIM, "ilum", 7).setRequiresMcRestart(true).getInt();
			dimDagobahId = config.get(CAT_DIM, "dagobah", 8).setRequiresMcRestart(true).getInt();
			dimMustafarId = config.get(CAT_DIM, "mustafar", 9).setRequiresMcRestart(true).getInt();
			dimSpaceId = config.get(CAT_DIM, "space", 118).setRequiresMcRestart(true).getInt();

			biomeDagobahId = config.get(CAT_BIOMES, "dagobah", 195).setRequiresMcRestart(true).getInt();
			biomeTatooineId = config.get(CAT_BIOMES, "tatooine", 196).setRequiresMcRestart(true).getInt();
			biomeHothId = config.get(CAT_BIOMES, "hoth", 197).setRequiresMcRestart(true).getInt();
			biomeKashyyykId = config.get(CAT_BIOMES, "kashyyyk", 198).setRequiresMcRestart(true).getInt();
			biomeYavin4Id = config.get(CAT_BIOMES, "yavin", 199).setRequiresMcRestart(true).getInt();
			biomeEndorId = config.get(CAT_BIOMES, "endor", 200).setRequiresMcRestart(true).getInt();
			biomeIlumId = config.get(CAT_BIOMES, "ilum", 201).setRequiresMcRestart(true).getInt();
			biomeMustafarId = config.get(CAT_BIOMES, "mustafar", 202).setRequiresMcRestart(true).getInt();
			biomeSpaceId = config.get(CAT_BIOMES, "space", 203).setRequiresMcRestart(true).getInt();

			enableCreditsOverlay = config.get(CAT_GUI, "Enable GUI Overlay", true, "Whether or not the PSWM overlay is visible").getBoolean();

			enableLightsaberHum = config.get(CAT_ITEMS, "Enable Lightsaber Idle Sound", true, "Whether or not lightsabers hum when idle").getBoolean();
			enableBuckets = true;

			enableLightsaberLight = config.get(CAT_ITEMS, "Enable Lightsaber Lighting", true, "Whether or not lightsabers are a light source").getBoolean();

			config.getCategory(CAT_CORE).setComment("Core options for the mod");
			config.getCategory(CAT_DIM).setComment("Dimension IDs");
			config.getCategory(CAT_BIOMES).setComment("Biome IDs");
			config.getCategory(CAT_GUI).setComment("GUI-related options");
			config.getCategory(CAT_ITEMS).setComment("Item-related options");

			planetTextures.clear();
			planetTextures.put(dimAlderaanId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetAlderaan.png"));
			planetTextures.put(dimBespinId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetBespin.png"));
			planetTextures.put(dimHothId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetHoth.png"));
			planetTextures.put(0, new ResourceLocation(MODID + ":" + "textures/models/planets/planetEarth.png"));
			planetTextures.put(dimCoruscantId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetCoruscant.png"));
			planetTextures.put(dimDagobahId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetDagobah.png"));
			planetTextures.put(dimDathomirId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetDathomir.png"));
			planetTextures.put(dimEndorId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetEndor.png"));
			planetTextures.put(dimGeonosisId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetGeonosis.png"));
			planetTextures.put(dimTatooineId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetTatooine.png"));
			planetTextures.put(dimRylothId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetRyloth.png"));
			planetTextures.put(dimIlumId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetIlum.png"));
			planetTextures.put(dimKaminoId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetKamino.png"));
			planetTextures.put(dimKashyyykId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetKashyyyk.png"));
			planetTextures.put(dimKesselId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetKessel.png"));
			planetTextures.put(dimMandaloreId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetMandalore.png"));
			planetTextures.put(dimMonCalamariId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetMonCalamari.png"));
			planetTextures.put(dimMustafarId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetMustafar.png"));
			planetTextures.put(dimNabooId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetNaboo.png"));
			planetTextures.put(dimSullustId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetSullust.png"));
			planetTextures.put(dimUtapauId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetUtapau.png"));
			planetTextures.put(dimYavin4Id, new ResourceLocation(MODID + ":" + "textures/models/planets/planetYavin4.png"));
			planetTextures.put(dimJakkuId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetJakku.png"));
			planetTextures.put(dimTakodanaId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetTakodana.png"));
			planetTextures.put(dimDQarId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetDQar.png"));
			planetTextures.put(dimAhchToId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetAhchTo.png"));
			planetTextures.put(dimDeathStarId, new ResourceLocation(MODID + ":" + "textures/models/planets/planetDeathStar.png"));
			planetTextures.put(dimSpaceId, new ResourceLocation(MODID + ":" + "textures/models/planets/space.png"));

			planetInformation = new ArrayList<>();

			Lumberjack.info("Configuration loaded!");
		}
	}

	// Core
	public static final String MODID = "starwarsmod";
	public static final String VERSION = "1.3.4";
	public static final String VERSION_MAJOR = "1.3";
	public static String ONLINE_VERSION = "";

	public static boolean IS_DEV_ENVIRONVENT = false;

	public static boolean IS_SEQUEL_RELEASE = true;

	public static final String[] checkCompatList = {}; // mod IDs
	public static final String remoteVersionLink = "https://raw.githubusercontent.com/Parzivail-Modding-Team/ParziStarWarsMod/master/VERSION.md";

	private static int guiCounter = 0;
	public static final int GUI_MV = guiCounter++;
	public static final int GUI_ROBES = guiCounter++;
	public static final int GUI_JEDI_SITH = guiCounter++;
	public static final int GUI_HOLOTABLE = guiCounter++;
	public static final int GUI_QUESTNPC = guiCounter++;
	public static final int GUI_QUEST = guiCounter++;
	public static final int GUI_LSFORGE = guiCounter++;
	public static final int GUI_SCANNER = guiCounter++;
	public static final int GUI_HYPERDRIVE = guiCounter++;
	public static final int GUI_ANTENNA = guiCounter++;
	public static final int GUI_CRYSTALCOMPRESSOR = guiCounter++;
	public static final int GUI_QUARTERMASTER = guiCounter++;
	public static final int GUI_QUARTERMASTER_EMPIRE = guiCounter++;
	public static final int GUI_JAWA = guiCounter++;
	public static final int GUI_MERCHANT = guiCounter++;
	public static final int GUI_WEAPONS_DEALER = guiCounter++;
	public static final int GUI_CORELLIAN = guiCounter++;
	public static final int GUI_BARTENDER = guiCounter++;

	// TIE Overlays
	public static final ResourceLocation tieOverlay = new ResourceLocation(Resources.MODID, "textures/gui/tie/tie.png");
	public static final ResourceLocation tieBackOverlay = new ResourceLocation(Resources.MODID, "textures/gui/tie/tieBack.png");
	public static final ResourceLocation tiePitch = new ResourceLocation(Resources.MODID, "textures/gui/tie/tiePitch.png");

	// X-Wing Overlays
	public static final ResourceLocation xwingOverlay = new ResourceLocation(Resources.MODID, "textures/gui/xwing/xwing.png");
	public static final ResourceLocation xwingOverlayBack1 = new ResourceLocation(Resources.MODID, "textures/gui/xwing/xwingBack1.png");
	public static final ResourceLocation xwingOverlayBack2 = new ResourceLocation(Resources.MODID, "textures/gui/xwing/xwingBack2.png");

	// A-Wing Overlays
	public static final ResourceLocation awingOverlay = new ResourceLocation(Resources.MODID, "textures/gui/awing/awing.png");
	public static final ResourceLocation awingBack = new ResourceLocation(Resources.MODID, "textures/gui/awing/awingBack.png");
	public static final ResourceLocation awingBack2 = new ResourceLocation(Resources.MODID, "textures/gui/awing/awingBack2.png");
	public static final ResourceLocation awingPitch1 = new ResourceLocation(Resources.MODID, "textures/gui/awing/pitch1.png");
	public static final ResourceLocation awingPitch2 = new ResourceLocation(Resources.MODID, "textures/gui/awing/pitch2.png");

	public static final ResourceLocation snowspeederOverlay = new ResourceLocation(Resources.MODID, "textures/gui/snowspeeder/snowspeeder.png");

	public static final ResourceLocation skyhopperOverlay = new ResourceLocation(Resources.MODID, "textures/gui/skyhopper/skyhopper.png");
	public static final ResourceLocation skyhoppeBack = new ResourceLocation(Resources.MODID, "textures/gui/skyhopper/skyhopperBack.png");
	public static final ResourceLocation skyhoppeBack2 = new ResourceLocation(Resources.MODID, "textures/gui/skyhopper/skyhopperBack2.png");

	public static final ResourceLocation atstOverlay = new ResourceLocation(Resources.MODID, "textures/gui/atst/atst.png");

	public static final ResourceLocation ywingOverlay = new ResourceLocation(Resources.MODID, "textures/gui/ywing/ywing.png");

	// Planet Textures
	public static final ResourceLocation earthTexture = new ResourceLocation(Resources.MODID, "textures/gui/planets/earth.png");
	public static final ResourceLocation endorTexture = new ResourceLocation(Resources.MODID, "textures/gui/planets/endor.png");
	public static final ResourceLocation hothTexture = new ResourceLocation(Resources.MODID, "textures/gui/planets/hoth.png");
	public static final ResourceLocation kashyyykTexture = new ResourceLocation(Resources.MODID, "textures/gui/planets/kashyyyk.png");
	public static final ResourceLocation yavinTexture = new ResourceLocation(Resources.MODID, "textures/gui/planets/yavin.png");
	public static final ResourceLocation tatooineTexture = new ResourceLocation(Resources.MODID, "textures/gui/planets/tatooine.png");

	// Force Textures
	public static final ResourceLocation capeTexture = new ResourceLocation(Resources.MODID, "textures/force/cloak.png");

	// Misc
	public static final char[] randomCharArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!?$".toCharArray();

	// NBT Keys
	public static final String nbtLevel = "level";
	public static final String nbtActive = "active";
	public static final String nbtIsRunning = "isRunning";
	public static final String nbtActiveHealth = "activeHealth";
	public static final String nbtActiveLevel = "activeLevel";
	public static final String nbtIsUsingDuration = "isDuration";

	public static final String nbtMaster = "master";
	public static final String nbtEntityTarget = "target";
	public static final String nbtXp = "xp";
	public static final String nbtMaxXp = "maxxp";
	public static final String nbtSide = "side";
	public static final String nbtPowers = "powers";
	public static final String nbtRemainingPts = "points";

	public static final String nbtQuests = "quests";
	public static final String nbtOwner = "owner";

	public static final String nbtWield = "wield";

	public static final String nbtShotsLeft = "shotsLeft";
	public static final String nbtCooldown = "cooldown";
	public static final String nbtTicksSince = "ticksSince";

	public static final String speciesHuman = "Human";
	public static final String speciesBith = "Bith";

	public static final String allegianceNone = "None";
	public static final String allegianceJedi = "Jedi";
	public static final String allegianceSith = "Sith";
	public static final String allegianceNewRepublic = "New Republic";
	public static final String allegianceRebel = "Rebel Alliance";
	public static final String allegianceImperial = "Galactic Empire";

	public static final String allegianceNoneFmt = "none";
	public static final String allegianceJediFmt = "jedi";
	public static final String allegianceSithFmt = "sith";
	public static final String allegianceNewRepublicFmt = "newrepublic";
	public static final String allegianceRebelFmt = "rebelalliance";
	public static final String allegianceImperialFmt = "galacticempire";
	public static final String allegianceJawaFmt = "jawa";
	public static final String allegianceCorellianFmt = "corellian";
	public static final String allegianceWeaponDealerFmt = "weapondealer";
	public static final String allegianceBartenderFmt = "bartender";
	public static final String allegianceMerchantFmt = "merchant";

	public static final String skinDefault = "default";

	//                                      0             1            2             3               4              5              6               7           8            9             10            11               12
	public static final String[] armors = { "rebelPilot", "rebelHoth", "rebelEndor", "stormtrooper", "sandtrooper", "snowtrooper", "scoutTrooper", "tiePilot", "atatPilot", "aWingPilot", "yWingPilot", "shadowtrooper", "robes" };
}
