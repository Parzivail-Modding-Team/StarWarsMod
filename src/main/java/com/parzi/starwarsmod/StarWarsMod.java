package com.parzi.starwarsmod;

import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;

import com.parzi.starwarsmod.achievement.StarWarsAchievements;
import com.parzi.starwarsmod.commands.CommandFlySpeed;
import com.parzi.starwarsmod.commands.CommandForcePoints;
import com.parzi.starwarsmod.commands.CommandSWDim;
import com.parzi.starwarsmod.handlers.GuiHandler;
import com.parzi.starwarsmod.handlers.InputHandler;
import com.parzi.starwarsmod.items.crafting.ItemLightsaberCrystal;
import com.parzi.starwarsmod.items.weapons.ItemBlasterHeavy;
import com.parzi.starwarsmod.items.weapons.ItemBlasterPistol;
import com.parzi.starwarsmod.items.weapons.ItemBlasterRifle;
import com.parzi.starwarsmod.items.weapons.ItemEwokSpear;
import com.parzi.starwarsmod.items.weapons.ItemGaffiStick;
import com.parzi.starwarsmod.items.weapons.ItemGamorreanAx;
import com.parzi.starwarsmod.items.weapons.ItemLightsaber;
import com.parzi.starwarsmod.items.weapons.ItemLightsaberOff;
import com.parzi.starwarsmod.items.weapons.ItemSequelBlasterPistol;
import com.parzi.starwarsmod.items.weapons.ItemSequelBlasterRifle;
import com.parzi.starwarsmod.items.weapons.ItemSequelLightsaber;
import com.parzi.starwarsmod.items.weapons.ItemSequelLightsaberOff;
import com.parzi.starwarsmod.items.weapons.ItemWookieeBowcaster;
import com.parzi.starwarsmod.network.CreateBlasterBolt;
import com.parzi.starwarsmod.network.JediRobesBuy;
import com.parzi.starwarsmod.network.JediRobesSetElementInArmorInv;
import com.parzi.starwarsmod.network.TeleportPlayerNetwork;
import com.parzi.starwarsmod.network.TogglePlayerLightsaber;
import com.parzi.starwarsmod.network.TogglePlayerSequelLightsaber;
import com.parzi.starwarsmod.registry.BlockRegister;
import com.parzi.starwarsmod.registry.EntityRegister;
import com.parzi.starwarsmod.registry.ItemRegister;
import com.parzi.starwarsmod.registry.MaterialRegister;
import com.parzi.starwarsmod.registry.RecipeRegister;
import com.parzi.starwarsmod.registry.WorldRegister;
import com.parzi.starwarsmod.rendering.helper.PGui;
import com.parzi.starwarsmod.tabs.SequelStarWarsTab;
import com.parzi.starwarsmod.tabs.StarWarsTab;
import com.parzi.starwarsmod.utils.Lumberjack;
import com.parzi.starwarsmod.utils.PlayerHelper;
import com.parzi.starwarsmod.utils.RenderHelper;
import com.parzi.starwarsmod.utils.WorldUtils;
import com.parzi.starwarsmod.world.provider.WorldProviderDagobah;
import com.parzi.starwarsmod.world.provider.WorldProviderEndor;
import com.parzi.starwarsmod.world.provider.WorldProviderHoth;
import com.parzi.starwarsmod.world.provider.WorldProviderKashyyyk;
import com.parzi.starwarsmod.world.provider.WorldProviderTatooine;
import com.parzi.starwarsmod.world.provider.WorldProviderYavinFour;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = StarWarsMod.MODID, version = StarWarsMod.VERSION, useMetadata = true)
public class StarWarsMod
{
	public static final String MODID = "starwarsmod";
	public static final String VERSION = "1.2.0";
	public static final String DEV_VER = "dev9";

	public static String ONLINE_VERSION = "";

	public static boolean IS_DEV_ENVIRONVENT = false;

	public static boolean IS_SEQUEL_RELEASE = false;

	public static Configuration config;

	public static Random rngChromium = new Random();
	public static Random rngTitanium = new Random();
	public static Random rngGeneral = new Random();

	@Mod.Instance(StarWarsMod.MODID)
	public static StarWarsMod instance;

	@SidedProxy(clientSide = "com.parzi.starwarsmod.StarWarsClientProxy", serverSide = "com.parzi.starwarsmod.StarWarsCommonProxy")
	public static StarWarsCommonProxy proxy;
	public static SimpleNetworkWrapper network;

	public static CreativeTabs StarWarsTab;
	public static CreativeTabs SequelStarWarsTab;

	public static ItemGaffiStick gaffiStick;
	public static ItemLightsaber lightsaber;
	public static ItemLightsaberOff lightsaberOff;
	public static ItemSequelLightsaber sequelLightsaber;
	public static ItemSequelLightsaberOff sequelLightsaberOff;
	public static ItemBlasterPistol blasterPistol;
	public static ItemBlasterRifle blasterRifle;
	public static ItemSequelBlasterRifle sequelBlasterRifle;
	public static ItemSequelBlasterPistol sequelBlasterPistol;
	public static ItemBlasterHeavy blasterHeavy;
	public static ItemEwokSpear ewokSpear;
	public static ItemWookieeBowcaster bowcaster;
	public static ItemGamorreanAx gamorreanAx;
	public static ItemLightsaberCrystal lightsaberCrystal;

	public static Item customTest;

	public static Item hiltMetelCompound;
	public static Item hiltMetelAlloy;
	public static Item plasmaEmitter;
	public static Item containmentField;
	public static Item blasterXWingBolt;
	public static Item blasterTIEBolt;
	public static Item blasterBolt;
	public static Item blasterRifleBolt;
	public static Item banthaHorn;
	public static Item droidCaller;
	public static Item droidHacker;
	public static Item imperialCredit;
	public static Item silverImperialCredit;
	public static Item goldImperialCredit;
	public static Item waterDroplet;

	public static Item binoculars;
	public static Item binocularsHoth;

	public static Item debugLootGen;
	public static Item debugLandspeederNPC;

	public static Item chromiumDust;
	public static Item titaniumDust;
	public static Item titaniumChromiumDust;
	public static Item titaniumChromiumIngot;

	public static Item hyperdriveMotivator;
	public static Item hyperdriveEngine;
	public static Item hyperdriveEarth;
	public static Item hyperdriveTatooine;
	public static Item hyperdriveHoth;
	public static Item hyperdriveKashyyyk;
	public static Item hyperdriveYavin4;
	public static Item hyperdriveEndor;
	public static Item hyperdriveDagobah;

	public static Item spawnSpeederBike;
	public static Item spawnJakkuSpeeder;
	public static Item spawnHothSpeederBike;
	public static Item spawnLandspeeder;
	public static Item spawnTie;
	public static Item spawnTieInterceptor;
	public static Item spawnAwing;
	public static Item spawnXwing;
	public static Item spawnAstromech;
	public static Item spawnAstromech2;
	public static Item spawnProtocol;
	public static Item spawnProtocol2;
	public static Item spawnProbe;
	public static Item spawnMouse;
	public static Item spawnGonk;
	public static Item spawnSurgical;
	public static Item spawnTreadwell;

	public static Item jediRobes;
	public static Item lightJediRobes;

	public static Item recordTheme;
	public static Item recordThrone;
	public static Item recordBinary;
	public static Item recordImperial;
	public static Item recordCantina;

	public static Item endorHelmet;
	public static Item endorChest;
	public static Item endorLegs;
	public static Item endorBoots;

	public static Item rebelPilotHelmet;
	public static Item rebelPilotChest;
	public static Item rebelPilotLegs;
	public static Item rebelPilotBoots;

	public static Item stormtrooperHelmet;
	public static Item stormtrooperChest;
	public static Item stormtrooperLegs;
	public static Item stormtrooperBoots;

	public static Item stormtrooperNewHelmet;
	public static Item stormtrooperNewChest;
	public static Item stormtrooperNewLegs;
	public static Item stormtrooperNewBoots;

	public static Item stormtrooperSilverNewHelmet;
	public static Item stormtrooperSilverNewChest;
	public static Item stormtrooperSilverNewLegs;
	public static Item stormtrooperSilverNewBoots;

	public static Item snowtrooperHelmet;
	public static Item snowtrooperChest;
	public static Item snowtrooperLegs;
	public static Item snowtrooperBoots;

	public static Item scoutTrooperHelmet;
	public static Item scoutTrooperChest;
	public static Item scoutTrooperLegs;
	public static Item scoutTrooperBoots;

	public static Item sandtrooperHelmet;
	public static Item sandtrooperChest;
	public static Item sandtrooperLegs;
	public static Item sandtrooperBoots;

	public static Item bobaHelmet;
	public static Item bobaChest;
	public static Item bobaJetpack;
	public static Item bobaJetpackChest;
	public static Item bobaLegs;
	public static Item bobaBoots;

	public static Item tiePilotHelmet;
	public static Item tiePilotChest;
	public static Item tiePilotLegs;
	public static Item tiePilotBoots;

	public static Item fleetHelmet;
	public static Item fleetChest;
	public static Item fleetLegs;
	public static Item fleetBoots;

	public static Item atatPilotHelmet;
	public static Item atatPilotChest;
	public static Item atatPilotLegs;
	public static Item atatPilotBoots;

	public static Item hothHelmet;
	public static Item hothChest;
	public static Item hothLegs;
	public static Item hothBoots;

	public static Item leiasBuns;

	public static ItemFood banthaChop;
	public static ItemFood banthaChopCooked;
	public static ItemFood banthaMilk;
	public static ItemFood acidBeets;
	public static ItemFood banthaPlatter;
	public static ItemFood canron;
	public static ItemFood chasuka;
	public static ItemFood dewbackRibs;
	public static ItemFood gorrnar;

	public static BiomeGenBase biomeTatooine;
	public static BiomeGenBase biomeHoth;
	public static BiomeGenBase biomeKashyyyk;
	public static BiomeGenBase biomeYavin4;
	public static BiomeGenBase biomeEndor;
	public static BiomeGenBase biomeEndorPlains;
	public static BiomeGenBase biomeDagobah;

	public static int dimTatooineId;
	public static int dimHothId;
	public static int dimKashyyykId;
	public static int dimYavin4Id;
	public static int dimDagobahId;
	public static int dimEndorId;
	public static int dimEndorPlainsId;

	public static boolean enableFlyCommand;
	public static boolean enableDimCommand;
	public static boolean enableCreditsOverlay;
	public static boolean enableLightsaberStrobe;
	public static boolean enableBlasterFire;
	public static boolean enableBuckets;
	public static boolean enableLightsaber;

	public static int lightsaberDamage;
	public static boolean isOverlayOnscreen = false;

	@SideOnly(Side.CLIENT)
	public static RenderHelper renderHelper;
	@SideOnly(Side.CLIENT)
	public static PlayerHelper playerHelper;
	@SideOnly(Side.CLIENT)
	public static PGui pgui;

	public static Block blockMV;
	public static Block blockTable;
	public static Block blockChromiumOre;
	public static Block blockTitaniumOre;
	public static Block blockTitaniumChromiumBlock;
	public static Block blockEndorBaseWall;
	public static Block blockEndorBaseWallStairs;
	public static Block blockTatooineSand;
	public static Block blockTatooineSandstone;
	public static Block blockSpaceLamp;
	public static Block blockDagobahMud;

	public static Item.ToolMaterial materialGaffi;
	public static Item.ToolMaterial materialEwok;
	public static Item.ToolMaterial materialGamorrean;
	public static Item.ToolMaterial materialPlasma;
	public static Item.ToolMaterial materialPlasmaOff;

	public static ArmorMaterial jediRobesMat;
	public static ArmorMaterial endorArmorMat;
	public static ArmorMaterial fleetArmorMat;
	public static ArmorMaterial rebelPilotArmorMat;
	public static ArmorMaterial stormtrooperArmorMat;
	public static ArmorMaterial stormtrooperNewArmorMat;
	public static ArmorMaterial snowtrooperArmorMat;
	public static ArmorMaterial scoutTrooperArmorMat;
	public static ArmorMaterial tiePilotArmorMat;
	public static ArmorMaterial atatPilotArmorMat;
	public static ArmorMaterial hothArmorMat;
	public static ArmorMaterial sandtrooperArmorMat;
	public static ArmorMaterial bobaArmorMat;
	public static ArmorMaterial leiaBunsArmorMat;

	@SideOnly(Side.CLIENT)
	public static KeyBinding keyShootVehicle;

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		Lumberjack.info("========== Begin Star Wars Mod init() ==========");

		Lumberjack.info("This is Parzi's Star Wars Mod v" + StarWarsMod.VERSION);

		if (Calendar.getInstance().get(Calendar.MONTH) >= 11 && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) >= 18 && Calendar.getInstance().get(Calendar.YEAR) >= 2015) IS_SEQUEL_RELEASE = true;

		InputStream in = null;
		try
		{
			in = new URL("https://raw.githubusercontent.com/Parzivail-Modding-Team/ParziStarWarsMod/master/VERSION.md").openStream();
			StarWarsMod.ONLINE_VERSION = IOUtils.toString(in).replace("\n", "");
		}
		catch (Exception e)
		{
			Lumberjack.warn("Couldn't check version!");
		}
		finally
		{
			if (in != null) IOUtils.closeQuietly(in);
		}

		instance = this;

		proxy.doSidedThings();

		StarWarsTab = new StarWarsTab();

		if (IS_SEQUEL_RELEASE)
		{
			Lumberjack.log("Sequel update! Suck it, JJ!");
			SequelStarWarsTab = new SequelStarWarsTab();
		}

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

		MaterialRegister.registerAll();

		ItemRegister.registerAll();

		BlockRegister.registerAll();

		RecipeRegister.registerAll();

		StarWarsAchievements.registerAll();

		try
		{
			WorldRegister.registerAll();

			EntityRegister.registerAll();
		}
		catch (Exception e)
		{
			Lumberjack.log(Level.FATAL, "World failed to report for duty! Cause: unable to register dimension provider. See trace below for details:");
			e.printStackTrace();
		}

		proxy.registerRendering();

		Lumberjack.info("=========== End Star Wars Mod init() ===========");
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		network = NetworkRegistry.INSTANCE.newSimpleChannel(StarWarsMod.MODID);
		network.registerMessage(JediRobesBuy.Handler.class, JediRobesBuy.class, 0, Side.SERVER);
		network.registerMessage(JediRobesSetElementInArmorInv.Handler.class, JediRobesSetElementInArmorInv.class, 1, Side.SERVER);
		network.registerMessage(TeleportPlayerNetwork.Handler.class, TeleportPlayerNetwork.class, 2, Side.SERVER);
		network.registerMessage(CreateBlasterBolt.Handler.class, CreateBlasterBolt.class, 3, Side.SERVER);
		network.registerMessage(TogglePlayerLightsaber.Handler.class, TogglePlayerLightsaber.class, 4, Side.SERVER);
		network.registerMessage(TogglePlayerSequelLightsaber.Handler.class, TogglePlayerSequelLightsaber.class, 5, Side.SERVER);

		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();

		dimTatooineId = 2;
		dimHothId = 3;
		dimKashyyykId = 4;
		dimYavin4Id = 5;
		dimEndorId = 6;
		dimEndorPlainsId = 7;
		dimDagobahId = 8;

		WorldUtils.registerDimension(StarWarsMod.dimTatooineId, WorldProviderTatooine.class);
		WorldUtils.registerDimension(StarWarsMod.dimHothId, WorldProviderHoth.class);
		WorldUtils.registerDimension(StarWarsMod.dimKashyyykId, WorldProviderKashyyyk.class);
		WorldUtils.registerDimension(StarWarsMod.dimYavin4Id, WorldProviderYavinFour.class);
		WorldUtils.registerDimension(StarWarsMod.dimEndorId, WorldProviderEndor.class);
		WorldUtils.registerDimension(StarWarsMod.dimDagobahId, WorldProviderDagobah.class);

		enableCreditsOverlay = config.get("gui", "enableCreditsOverlay", true).getBoolean();
		lightsaberDamage = config.get("items", "lightsaberDamage", 26).getInt();
		enableLightsaber = config.get("items", "enableLightsaberRecipe", true).getBoolean();
		enableBlasterFire = config.get("items", "enableBlasterFire", true).getBoolean();
		enableLightsaberStrobe = config.get("items", "enableLightsaberAnimation", true).getBoolean();
		enableLightsaberStrobe = config.get("items", "enableGettingThatDumbFreeBucketFromWaterDroplets", true).getBoolean();
		enableLightsaber = config.get("items", "enableLightsaberRecipe", true).getBoolean();

		config.save();

		FMLCommonHandler.instance().bus().register(new InputHandler());

		Lumberjack.info("Configuration loaded!");
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		if (IS_DEV_ENVIRONVENT)
		{
			event.registerServerCommand(new CommandFlySpeed());
			event.registerServerCommand(new CommandSWDim());
		}

		event.registerServerCommand(new CommandForcePoints());
	}

	@EventHandler
	public void serverUnloaded(FMLServerStoppedEvent event)
	{
		WorldUtils.unregisterDimension(StarWarsMod.dimTatooineId);
		WorldUtils.unregisterDimension(StarWarsMod.dimHothId);
		WorldUtils.unregisterDimension(StarWarsMod.dimKashyyykId);
		WorldUtils.unregisterDimension(StarWarsMod.dimYavin4Id);
		WorldUtils.unregisterDimension(StarWarsMod.dimEndorId);
		WorldUtils.unregisterDimension(StarWarsMod.dimDagobahId);
	}
}
/*
 * Location: C:\Users\Colby\Downloads\Parzi's Star Wars Mod
 * v1.2.0-dev7.jar!\com\parzi\starwarsmod\StarWarsMod.class Java compiler
 * version: 6 (50.0) JD-Core Version: 0.7.1
 */