package com.parzivail.pswm.registry;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.armor.*;
import com.parzivail.pswm.force.ItemHolocron;
import com.parzivail.pswm.items.*;
import com.parzivail.pswm.items.ItemPlasmaEmitter;
import com.parzivail.pswm.items.crafting.*;
import com.parzivail.pswm.items.hyperdrive.*;
import com.parzivail.pswm.items.ingot.*;
import com.parzivail.pswm.items.lightsaber.*;
import com.parzivail.pswm.items.weapons.*;
import com.parzivail.pswm.vehicles.*;
import com.parzivail.util.ui.Lumberjack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegister
{
	public static void registerAll()
	{
		if (Resources.IS_SEQUEL_RELEASE)
		{
			StarWarsItems.stormtrooperNewHelmet = new ArmorSequelStormtrooper(StarWarsMod.stormtrooperNewArmorMat, 1, 0);
			GameRegistry.registerItem(StarWarsItems.stormtrooperNewHelmet, "stormtrooperNewHelmet");

			StarWarsItems.stormtrooperNewChest = new ArmorSequelStormtrooper(StarWarsMod.stormtrooperNewArmorMat, 1, 1);
			GameRegistry.registerItem(StarWarsItems.stormtrooperNewChest, "stormtrooperNewChest");

			StarWarsItems.stormtrooperNewLegs = new ArmorSequelStormtrooper(StarWarsMod.stormtrooperNewArmorMat, 1, 2);
			GameRegistry.registerItem(StarWarsItems.stormtrooperNewLegs, "stormtrooperNewLegs");

			StarWarsItems.stormtrooperNewBoots = new ArmorSequelStormtrooper(StarWarsMod.stormtrooperNewArmorMat, 1, 3);
			GameRegistry.registerItem(StarWarsItems.stormtrooperNewBoots, "stormtrooperNewBoots");

			StarWarsItems.stormtrooperSilverNewHelmet = new ArmorSequelStormtrooperSilver(StarWarsMod.stormtrooperNewArmorMat, 1, 0);
			GameRegistry.registerItem(StarWarsItems.stormtrooperSilverNewHelmet, "stormtrooperSilverNewHelmet");

			StarWarsItems.stormtrooperSilverNewChest = new ArmorSequelStormtrooperSilver(StarWarsMod.stormtrooperNewArmorMat, 1, 1);
			GameRegistry.registerItem(StarWarsItems.stormtrooperSilverNewChest, "stormtrooperSilverNewChest");

			StarWarsItems.stormtrooperSilverNewLegs = new ArmorSequelStormtrooperSilver(StarWarsMod.stormtrooperNewArmorMat, 1, 2);
			GameRegistry.registerItem(StarWarsItems.stormtrooperSilverNewLegs, "stormtrooperSilverNewLegs");

			StarWarsItems.stormtrooperSilverNewBoots = new ArmorSequelStormtrooperSilver(StarWarsMod.stormtrooperNewArmorMat, 1, 3);
			GameRegistry.registerItem(StarWarsItems.stormtrooperSilverNewBoots, "stormtrooperSilverNewBoots");

			StarWarsItems.spawnJakkuSpeeder = new ItemSpawnJakkuSpeeder();
			GameRegistry.registerItem(StarWarsItems.spawnJakkuSpeeder, "spawnJakkuSpeeder");

			StarWarsItems.sequelBlasterRifle = new ItemSequelBlasterRifle();
			GameRegistry.registerItem(StarWarsItems.sequelBlasterRifle, "sequelBlasterRifle");

			StarWarsItems.sequelBlasterPistol = new ItemSequelBlasterPistol();
			GameRegistry.registerItem(StarWarsItems.sequelBlasterPistol, "sequelBlasterPistol");

			StarWarsItems.spawnAstromechBb8 = new ItemSpawnAstromechBb8();
			GameRegistry.registerItem(StarWarsItems.spawnAstromechBb8, "spawnAstromechBb8");
		}

		StarWarsItems.recordDrift = new ItemMusicDisc("Drift");
		GameRegistry.registerItem(StarWarsItems.recordDrift, "recordDrift");

		StarWarsItems.slopeWizard = new ItemSlopeWizard();
		GameRegistry.registerItem(StarWarsItems.slopeWizard, "slopeWizard");

		StarWarsItems.hothSign = new ItemHothSign();
		GameRegistry.registerItem(StarWarsItems.hothSign, "hothSign");


		StarWarsItems.hyperdriveEarth = new ItemHyperdriveEarth();
		GameRegistry.registerItem(StarWarsItems.hyperdriveEarth, "hyperdriveEarth");

		StarWarsItems.hyperdriveTatooine = new ItemHyperdriveTatooine();
		GameRegistry.registerItem(StarWarsItems.hyperdriveTatooine, "hyperdriveTatooine");

		StarWarsItems.hyperdriveHoth = new ItemHyperdriveHoth();
		GameRegistry.registerItem(StarWarsItems.hyperdriveHoth, "hyperdriveHoth");

		StarWarsItems.hyperdriveSpace = new ItemHyperdriveSpace();
		GameRegistry.registerItem(StarWarsItems.hyperdriveSpace, "hyperdriveSpace");

		StarWarsItems.hyperdriveYavin4 = new ItemHyperdriveYavinFour();
		GameRegistry.registerItem(StarWarsItems.hyperdriveYavin4, "hyperdriveYavinFour");

		StarWarsItems.hyperdriveEndor = new ItemHyperdriveEndor();
		GameRegistry.registerItem(StarWarsItems.hyperdriveEndor, "hyperdriveEndor");

		StarWarsItems.hyperdriveDagobah = new ItemHyperdriveDagobah();
		GameRegistry.registerItem(StarWarsItems.hyperdriveDagobah, "hyperdriveDagobah");

		StarWarsItems.hyperdriveIlum = new ItemHyperdriveIlum();
		GameRegistry.registerItem(StarWarsItems.hyperdriveIlum, "hyperdriveIlum");

		StarWarsItems.hyperdriveKashyyyk = new ItemHyperdriveKashyyyk();
		GameRegistry.registerItem(StarWarsItems.hyperdriveKashyyyk, "hyperdriveKashyyyk");

		StarWarsItems.ewokSpear = new ItemEwokSpear();
		GameRegistry.registerItem(StarWarsItems.ewokSpear, "ewokSpear");

		StarWarsItems.gamorreanAx1 = new ItemGamorreanAx1();
		GameRegistry.registerItem(StarWarsItems.gamorreanAx1, "gamorreanAx");

		StarWarsItems.gamorreanAx2 = new ItemGamorreanAx2();
		GameRegistry.registerItem(StarWarsItems.gamorreanAx2, "gamorreanAx2");

		StarWarsItems.gamorreanAx3 = new ItemGamorreanAx3();
		GameRegistry.registerItem(StarWarsItems.gamorreanAx3, "gamorreanAx3");

		StarWarsItems.gaffiStick = new ItemGaffiStick();
		GameRegistry.registerItem(StarWarsItems.gaffiStick, "gaffiStick");

		StarWarsItems.vibroLance = new ItemVibroLance();
		GameRegistry.registerItem(StarWarsItems.vibroLance, "vibroLance");

		StarWarsItems.blasterPistol = new ItemBlasterPistol();
		GameRegistry.registerItem(StarWarsItems.blasterPistol, "blasterPistol");

		StarWarsItems.blasterRifle = new ItemBlasterRifle();
		GameRegistry.registerItem(StarWarsItems.blasterRifle, "blasterRifle");

		StarWarsItems.bowcaster = new ItemWookieeBowcaster();
		GameRegistry.registerItem(StarWarsItems.bowcaster, "bowcaster");

		StarWarsItems.blasterHeavy = new ItemBlasterHeavy();
		GameRegistry.registerItem(StarWarsItems.blasterHeavy, "blasterHeavy");

		StarWarsItems.powerpack = new ItemPowerpack();
		GameRegistry.registerItem(StarWarsItems.powerpack, "powerpack");

		for (int i = 0; i < StarWarsItems.lightsaberNew.length; i++)
		{
			StarWarsItems.lightsaberNew[i] = new ItemLightsaber(i);
			GameRegistry.registerItem(StarWarsItems.lightsaberNew[i], "lightsaberNew" + i);
		}

		StarWarsItems.lightsaberCrystal = new ItemLightsaberCrystal();
		GameRegistry.registerItem(StarWarsItems.lightsaberCrystal, "lightsaberCrystal");

		StarWarsItems.hiltMetelCompound = new ItemHiltMetalCompound();
		GameRegistry.registerItem(StarWarsItems.hiltMetelCompound, "hiltMetalCompound");

		StarWarsItems.hiltMetelAlloy = new ItemHiltMetalAlloy();
		GameRegistry.registerItem(StarWarsItems.hiltMetelAlloy, "hiltMetalAlloy");

		StarWarsItems.airsealGel = new ItemAirsealGel();
		GameRegistry.registerItem(StarWarsItems.airsealGel, "airsealGel");

		StarWarsItems.apexSeal = new ItemApexSeal();
		GameRegistry.registerItem(StarWarsItems.apexSeal, "apexSeal");

		StarWarsItems.cyclingFieldEnergizer = new ItemCyclingFieldEnergizer();
		GameRegistry.registerItem(StarWarsItems.cyclingFieldEnergizer, "cyclingFieldEnergizer");

		StarWarsItems.diatiumPowerCell = new ItemDiatiumPowerCell();
		GameRegistry.registerItem(StarWarsItems.diatiumPowerCell, "diatiumPowerCell");

		StarWarsItems.energyGate = new ItemEnergyGate();
		GameRegistry.registerItem(StarWarsItems.energyGate, "energyGate");

		StarWarsItems.energyModulationCircuit = new ItemEnergyModulationCircuit();
		GameRegistry.registerItem(StarWarsItems.energyModulationCircuit, "energyModulationCircuit");

		StarWarsItems.focusingCrystal = new ItemFocusingCrystal();
		GameRegistry.registerItem(StarWarsItems.focusingCrystal, "focusingCrystal");

		StarWarsItems.inertPowerInsulator = new ItemInertPowerInsulator();
		GameRegistry.registerItem(StarWarsItems.inertPowerInsulator, "inertPowerInsulator");

		StarWarsItems.magneticStabilizingRing = new ItemMagneticStabilizingRing();
		GameRegistry.registerItem(StarWarsItems.magneticStabilizingRing, "magneticStabilizingRing");

		StarWarsItems.powerVortexRing = new ItemPowerVortexRing();
		GameRegistry.registerItem(StarWarsItems.powerVortexRing, "powerVortexRing");

		StarWarsItems.chromiumDust = new ItemChromiumDust();
		GameRegistry.registerItem(StarWarsItems.chromiumDust, "chromiumDust");

		StarWarsItems.titaniumDust = new ItemTitaniumDust();
		GameRegistry.registerItem(StarWarsItems.titaniumDust, "titaniumDust");

		StarWarsItems.titaniumChromiumDust = new ItemTitaniumChromiumDust();
		GameRegistry.registerItem(StarWarsItems.titaniumChromiumDust, "titaniumChromiumDust");

		StarWarsItems.titaniumChromiumIngot = new ItemTitaniumChromiumIngot();
		GameRegistry.registerItem(StarWarsItems.titaniumChromiumIngot, "titaniumChromiumIngot");

		StarWarsItems.ingotThorolide = new ItemThorolideIngot();
		GameRegistry.registerItem(StarWarsItems.ingotThorolide, "ingotThorolide");

		StarWarsItems.ingotCortosis = new ItemCortosisIngot();
		GameRegistry.registerItem(StarWarsItems.ingotCortosis, "ingotCortosis");

		StarWarsItems.ingotDiatium = new ItemDiatiumIngot();
		GameRegistry.registerItem(StarWarsItems.ingotDiatium, "ingotDiatium");

		StarWarsItems.ingotExonium = new ItemExoniumIngot();
		GameRegistry.registerItem(StarWarsItems.ingotExonium, "ingotExonium");

		StarWarsItems.ingotHelicite = new ItemHeliciteIngot();
		GameRegistry.registerItem(StarWarsItems.ingotHelicite, "ingotHelicite");

		StarWarsItems.ingotIonite = new ItemIoniteIngot();
		GameRegistry.registerItem(StarWarsItems.ingotIonite, "ingotIonite");

		StarWarsItems.ingotKelerium = new ItemKeleriumIngot();
		GameRegistry.registerItem(StarWarsItems.ingotKelerium, "ingotKelerium");

		StarWarsItems.ingotRubindum = new ItemRubindumIngot();
		GameRegistry.registerItem(StarWarsItems.ingotRubindum, "ingotRubindum");

		StarWarsItems.binoculars = new ItemBinocularsTatooine();
		GameRegistry.registerItem(StarWarsItems.binoculars, "binoculars");

		StarWarsItems.binocularsHoth = new ItemBinocularsHoth();
		GameRegistry.registerItem(StarWarsItems.binocularsHoth, "binocularsHoth");

		StarWarsItems.banthaMilk = new ItemBanthaMilk();
		GameRegistry.registerItem(StarWarsItems.banthaMilk, "banthaMilk");

		StarWarsItems.acidBeets = new ItemAcidBeets();
		GameRegistry.registerItem(StarWarsItems.acidBeets, "acidBeets");

		StarWarsItems.banthaPlatter = new ItemBanthaPlatter();
		GameRegistry.registerItem(StarWarsItems.banthaPlatter, "banthaPlatter");

		StarWarsItems.canron = new ItemCanron();
		GameRegistry.registerItem(StarWarsItems.canron, "canron");

		StarWarsItems.chasuka = new ItemChasuka();
		GameRegistry.registerItem(StarWarsItems.chasuka, "chasuka");

		StarWarsItems.dewbackRibs = new ItemDewbackRibs();
		GameRegistry.registerItem(StarWarsItems.dewbackRibs, "dewbackRibs");

		StarWarsItems.gorrnar = new ItemGorrnar();
		GameRegistry.registerItem(StarWarsItems.gorrnar, "gorrnar");

		StarWarsItems.banthaChop = new ItemBanthaChop();
		GameRegistry.registerItem(StarWarsItems.banthaChop, "banthaChop");

		StarWarsItems.banthaChopCooked = new ItemBanthaChopCooked();
		GameRegistry.registerItem(StarWarsItems.banthaChopCooked, "banthaChopCooked");

		StarWarsItems.waterDroplet = new ItemWaterDroplet();
		GameRegistry.registerItem(StarWarsItems.waterDroplet, "waterDroplet");

		StarWarsItems.holocron = new ItemHolocron();
		GameRegistry.registerItem(StarWarsItems.holocron, "holocron");

		StarWarsItems.questContainer = new ItemQuestLog();
		GameRegistry.registerItem(StarWarsItems.questContainer, "questContainer");

		StarWarsItems.idScanner = new ItemIDScanner();
		GameRegistry.registerItem(StarWarsItems.idScanner, "idScanner");

		StarWarsItems.hydrospanner = new ItemHydrospanner();
		GameRegistry.registerItem(StarWarsItems.hydrospanner, "hydrospanner");

		StarWarsItems.banthaHorn = new ItemBanthaHorn();
		GameRegistry.registerItem(StarWarsItems.banthaHorn, "banthaHorn");

		StarWarsItems.wampaHorn = new ItemWampaHorn();
		GameRegistry.registerItem(StarWarsItems.wampaHorn, "wampaHorn");

		StarWarsItems.tieSchematics = new ItemTieSchematics();
		GameRegistry.registerItem(StarWarsItems.tieSchematics, "tieSchematics");

		StarWarsItems.xwingSchematics = new ItemXWingSchematics();
		GameRegistry.registerItem(StarWarsItems.xwingSchematics, "xwingSchematics");

		StarWarsItems.reactorCore = new ItemReactorCore();
		GameRegistry.registerItem(StarWarsItems.reactorCore, "reactorCore");

		StarWarsItems.dataPad = new ItemDataPad();
		GameRegistry.registerItem(StarWarsItems.dataPad, "dataPad");

		StarWarsItems.rebelDataDrive = new ItemRebelDataDrive();
		GameRegistry.registerItem(StarWarsItems.rebelDataDrive, "rebelDataDrive");

		StarWarsItems.droidCaller = new ItemDroidCaller();
		GameRegistry.registerItem(StarWarsItems.droidCaller, "droidCaller");

		StarWarsItems.droidHacker = new ItemDroidHacker();
		GameRegistry.registerItem(StarWarsItems.droidHacker, "droidHacker");

		StarWarsItems.plasmaEmitter = new ItemPlasmaEmitter();
		GameRegistry.registerItem(StarWarsItems.plasmaEmitter, "plasmaEmitter");

		StarWarsItems.containmentField = new ItemContainmentField();
		GameRegistry.registerItem(StarWarsItems.containmentField, "containmentField");

		StarWarsItems.hyperdriveEngine = new ItemHyperdriveEngine();
		GameRegistry.registerItem(StarWarsItems.hyperdriveEngine, "hyperdriveEngine");

		StarWarsItems.hyperdriveMotivator = new ItemHyperdriveMotivator();
		GameRegistry.registerItem(StarWarsItems.hyperdriveMotivator, "hyperdriveMotivator");

		StarWarsItems.sithArtifact = new ItemSithArtifact();
		GameRegistry.registerItem(StarWarsItems.sithArtifact, "sithArtifact");

		StarWarsItems.imperialCredit = new ItemImperialCredit();
		GameRegistry.registerItem(StarWarsItems.imperialCredit, "imperialCredit");

		StarWarsItems.silverImperialCredit = new ItemSilverImperialCredit();
		GameRegistry.registerItem(StarWarsItems.silverImperialCredit, "silverImperialCredit");

		StarWarsItems.goldImperialCredit = new ItemGoldImperialCredit();
		GameRegistry.registerItem(StarWarsItems.goldImperialCredit, "goldImperialCredit");

		StarWarsItems.endorHelmet = new ArmorEndor(StarWarsMod.endorArmorMat, 1, 0);
		GameRegistry.registerItem(StarWarsItems.endorHelmet, "endorHelmet");

		StarWarsItems.endorChest = new ArmorEndor(StarWarsMod.endorArmorMat, 1, 1);
		GameRegistry.registerItem(StarWarsItems.endorChest, "endorChest");

		StarWarsItems.endorLegs = new ArmorEndor(StarWarsMod.endorArmorMat, 1, 2);
		GameRegistry.registerItem(StarWarsItems.endorLegs, "endorLegs");

		StarWarsItems.endorBoots = new ArmorEndor(StarWarsMod.endorArmorMat, 1, 3);
		GameRegistry.registerItem(StarWarsItems.endorBoots, "endorBoots");

		StarWarsItems.hothHelmet = new ArmorHoth(StarWarsMod.hothArmorMat, 1, 0);
		GameRegistry.registerItem(StarWarsItems.hothHelmet, "hothHelmet");

		StarWarsItems.hothChest = new ArmorHoth(StarWarsMod.hothArmorMat, 1, 1);
		GameRegistry.registerItem(StarWarsItems.hothChest, "hothChest");

		StarWarsItems.hothLegs = new ArmorHoth(StarWarsMod.hothArmorMat, 1, 2);
		GameRegistry.registerItem(StarWarsItems.hothLegs, "hothLegs");

		StarWarsItems.hothBoots = new ArmorHoth(StarWarsMod.hothArmorMat, 1, 3);
		GameRegistry.registerItem(StarWarsItems.hothBoots, "hothBoots");

		StarWarsItems.rebelPilotHelmet = new ArmorRebelPilot(StarWarsMod.rebelPilotArmorMat, 1, 0);
		GameRegistry.registerItem(StarWarsItems.rebelPilotHelmet, "rebelPilotHelmet");

		StarWarsItems.rebelPilotChest = new ArmorRebelPilot(StarWarsMod.rebelPilotArmorMat, 1, 1);
		GameRegistry.registerItem(StarWarsItems.rebelPilotChest, "rebelPilotChest");

		StarWarsItems.rebelPilotLegs = new ArmorRebelPilot(StarWarsMod.rebelPilotArmorMat, 1, 2);
		GameRegistry.registerItem(StarWarsItems.rebelPilotLegs, "rebelPilotLegs");

		StarWarsItems.rebelPilotBoots = new ArmorRebelPilot(StarWarsMod.rebelPilotArmorMat, 1, 3);
		GameRegistry.registerItem(StarWarsItems.rebelPilotBoots, "rebelPilotBoots");

		StarWarsItems.rebelYPilotHelmet = new ArmorRebelYPilot(StarWarsMod.rebelPilotArmorMat, 1, 0);
		GameRegistry.registerItem(StarWarsItems.rebelYPilotHelmet, "rebelYPilotHelmet");

		StarWarsItems.rebelYPilotChest = new ArmorRebelYPilot(StarWarsMod.rebelPilotArmorMat, 1, 1);
		GameRegistry.registerItem(StarWarsItems.rebelYPilotChest, "rebelYPilotChest");

		StarWarsItems.rebelYPilotLegs = new ArmorRebelYPilot(StarWarsMod.rebelPilotArmorMat, 1, 2);
		GameRegistry.registerItem(StarWarsItems.rebelYPilotLegs, "rebelYPilotLegs");

		StarWarsItems.rebelYPilotBoots = new ArmorRebelYPilot(StarWarsMod.rebelPilotArmorMat, 1, 3);
		GameRegistry.registerItem(StarWarsItems.rebelYPilotBoots, "rebelYPilotBoots");

		StarWarsItems.rebelAPilotHelmet = new ArmorRebelAPilot(StarWarsMod.rebelPilotArmorMat, 1, 0);
		GameRegistry.registerItem(StarWarsItems.rebelAPilotHelmet, "rebelAPilotHelmet");

		StarWarsItems.rebelAPilotChest = new ArmorRebelAPilot(StarWarsMod.rebelPilotArmorMat, 1, 1);
		GameRegistry.registerItem(StarWarsItems.rebelAPilotChest, "rebelAPilotChest");

		StarWarsItems.rebelAPilotLegs = new ArmorRebelAPilot(StarWarsMod.rebelPilotArmorMat, 1, 2);
		GameRegistry.registerItem(StarWarsItems.rebelAPilotLegs, "rebelAPilotLegs");

		StarWarsItems.rebelAPilotBoots = new ArmorRebelAPilot(StarWarsMod.rebelPilotArmorMat, 1, 3);
		GameRegistry.registerItem(StarWarsItems.rebelAPilotBoots, "rebelAPilotBoots");

		StarWarsItems.bobaHelmet = new ArmorBoba(StarWarsMod.bobaArmorMat, 1, 0);
		GameRegistry.registerItem(StarWarsItems.bobaHelmet, "bobaHelmet");

		StarWarsItems.bobaChest = new ArmorBoba(StarWarsMod.bobaArmorMat, 1, 1);
		GameRegistry.registerItem(StarWarsItems.bobaChest, "bobaChest");

		StarWarsItems.bobaJetpackChest = new ArmorBobaJetpack(StarWarsMod.bobaArmorMat, 1, 1);
		GameRegistry.registerItem(StarWarsItems.bobaJetpackChest, "bobaJetpackChest");

		StarWarsItems.bobaJetpack = new ItemBobaJetpack();
		GameRegistry.registerItem(StarWarsItems.bobaJetpack, "bobaJetpack");

		StarWarsItems.bobaLegs = new ArmorBoba(StarWarsMod.bobaArmorMat, 1, 2);
		GameRegistry.registerItem(StarWarsItems.bobaLegs, "bobaLegs");

		StarWarsItems.bobaBoots = new ArmorBoba(StarWarsMod.bobaArmorMat, 1, 3);
		GameRegistry.registerItem(StarWarsItems.bobaBoots, "bobaBoots");

		StarWarsItems.stormtrooperHelmet = new ArmorStormtrooper(StarWarsMod.stormtrooperArmorMat, 1, 0);
		GameRegistry.registerItem(StarWarsItems.stormtrooperHelmet, "stormtrooperHelmet");

		StarWarsItems.stormtrooperChest = new ArmorStormtrooper(StarWarsMod.stormtrooperArmorMat, 1, 1);
		GameRegistry.registerItem(StarWarsItems.stormtrooperChest, "stormtrooperChest");

		StarWarsItems.stormtrooperLegs = new ArmorStormtrooper(StarWarsMod.stormtrooperArmorMat, 1, 2);
		GameRegistry.registerItem(StarWarsItems.stormtrooperLegs, "stormtrooperLegs");

		StarWarsItems.stormtrooperBoots = new ArmorStormtrooper(StarWarsMod.stormtrooperArmorMat, 1, 3);
		GameRegistry.registerItem(StarWarsItems.stormtrooperBoots, "stormtrooperBoots");

		StarWarsItems.scoutTrooperHelmet = new ArmorScoutTrooper(StarWarsMod.scoutTrooperArmorMat, 1, 0);
		GameRegistry.registerItem(StarWarsItems.scoutTrooperHelmet, "scoutTrooperHelmet");

		StarWarsItems.scoutTrooperChest = new ArmorScoutTrooper(StarWarsMod.scoutTrooperArmorMat, 1, 1);
		GameRegistry.registerItem(StarWarsItems.scoutTrooperChest, "scoutTrooperChest");

		StarWarsItems.scoutTrooperLegs = new ArmorScoutTrooper(StarWarsMod.scoutTrooperArmorMat, 1, 2);
		GameRegistry.registerItem(StarWarsItems.scoutTrooperLegs, "scoutTrooperLegs");

		StarWarsItems.scoutTrooperBoots = new ArmorScoutTrooper(StarWarsMod.scoutTrooperArmorMat, 1, 3);
		GameRegistry.registerItem(StarWarsItems.scoutTrooperBoots, "scoutTrooperBoots");

		StarWarsItems.sandtrooperHelmet = new ArmorSandtrooper(StarWarsMod.sandtrooperArmorMat, 1, 0);
		GameRegistry.registerItem(StarWarsItems.sandtrooperHelmet, "sandtrooperHelmet");

		StarWarsItems.sandtrooperChest = new ArmorSandtrooper(StarWarsMod.sandtrooperArmorMat, 1, 1);
		GameRegistry.registerItem(StarWarsItems.sandtrooperChest, "sandtrooperChest");

		StarWarsItems.sandtrooperLegs = new ArmorSandtrooper(StarWarsMod.sandtrooperArmorMat, 1, 2);
		GameRegistry.registerItem(StarWarsItems.sandtrooperLegs, "sandtrooperLegs");

		StarWarsItems.sandtrooperBoots = new ArmorSandtrooper(StarWarsMod.sandtrooperArmorMat, 1, 3);
		GameRegistry.registerItem(StarWarsItems.sandtrooperBoots, "sandtrooperBoots");

		StarWarsItems.snowtrooperHelmet = new ArmorSnowtrooper(StarWarsMod.snowtrooperArmorMat, 1, 0);
		GameRegistry.registerItem(StarWarsItems.snowtrooperHelmet, "snowtrooperHelmet");

		StarWarsItems.snowtrooperChest = new ArmorSnowtrooper(StarWarsMod.snowtrooperArmorMat, 1, 1);
		GameRegistry.registerItem(StarWarsItems.snowtrooperChest, "snowtrooperChest");

		StarWarsItems.snowtrooperLegs = new ArmorSnowtrooper(StarWarsMod.snowtrooperArmorMat, 1, 2);
		GameRegistry.registerItem(StarWarsItems.snowtrooperLegs, "snowtrooperLegs");

		StarWarsItems.snowtrooperBoots = new ArmorSnowtrooper(StarWarsMod.snowtrooperArmorMat, 1, 3);
		GameRegistry.registerItem(StarWarsItems.snowtrooperBoots, "snowtrooperBoots");

		StarWarsItems.tiePilotHelmet = new ArmorTiePilot(StarWarsMod.tiePilotArmorMat, 1, 0);
		GameRegistry.registerItem(StarWarsItems.tiePilotHelmet, "tiePilotHelmet");

		StarWarsItems.tiePilotChest = new ArmorTiePilot(StarWarsMod.tiePilotArmorMat, 1, 1);
		GameRegistry.registerItem(StarWarsItems.tiePilotChest, "tiePilotChest");

		StarWarsItems.tiePilotLegs = new ArmorTiePilot(StarWarsMod.tiePilotArmorMat, 1, 2);
		GameRegistry.registerItem(StarWarsItems.tiePilotLegs, "tiePilotLegs");

		StarWarsItems.tiePilotBoots = new ArmorTiePilot(StarWarsMod.tiePilotArmorMat, 1, 3);
		GameRegistry.registerItem(StarWarsItems.tiePilotBoots, "tiePilotBoots");

		StarWarsItems.atstPilotHelmet = new ArmorAtstPilot(StarWarsMod.atatPilotArmorMat, 1, 0);
		GameRegistry.registerItem(StarWarsItems.atstPilotHelmet, "atstPilotHelmet");

		StarWarsItems.atstPilotChest = new ArmorAtstPilot(StarWarsMod.atatPilotArmorMat, 1, 1);
		GameRegistry.registerItem(StarWarsItems.atstPilotChest, "atstPilotChest");

		StarWarsItems.atstPilotLegs = new ArmorAtstPilot(StarWarsMod.atatPilotArmorMat, 1, 2);
		GameRegistry.registerItem(StarWarsItems.atstPilotLegs, "atstPilotLegs");

		StarWarsItems.atstPilotBoots = new ArmorAtstPilot(StarWarsMod.atatPilotArmorMat, 1, 3);
		GameRegistry.registerItem(StarWarsItems.atstPilotBoots, "atstPilotBoots");

		StarWarsItems.atatPilotHelmet = new ArmorAtatPilot(StarWarsMod.atatPilotArmorMat, 1, 0);
		GameRegistry.registerItem(StarWarsItems.atatPilotHelmet, "atatPilotHelmet");

		StarWarsItems.atatPilotChest = new ArmorAtatPilot(StarWarsMod.atatPilotArmorMat, 1, 1);
		GameRegistry.registerItem(StarWarsItems.atatPilotChest, "atatPilotChest");

		StarWarsItems.atatPilotLegs = new ArmorAtatPilot(StarWarsMod.atatPilotArmorMat, 1, 2);
		GameRegistry.registerItem(StarWarsItems.atatPilotLegs, "atatPilotLegs");

		StarWarsItems.atatPilotBoots = new ArmorAtatPilot(StarWarsMod.atatPilotArmorMat, 1, 3);
		GameRegistry.registerItem(StarWarsItems.atatPilotBoots, "atatPilotBoots");

		StarWarsItems.shadowtrooperHelmet = new ArmorShadowtrooper(StarWarsMod.shadowtrooperArmorMat, 1, 0);
		GameRegistry.registerItem(StarWarsItems.shadowtrooperHelmet, "shadowtrooperHelmet");

		StarWarsItems.shadowtrooperChest = new ArmorShadowtrooper(StarWarsMod.shadowtrooperArmorMat, 1, 1);
		GameRegistry.registerItem(StarWarsItems.shadowtrooperChest, "shadowtrooperChest");

		StarWarsItems.shadowtrooperLegs = new ArmorShadowtrooper(StarWarsMod.shadowtrooperArmorMat, 1, 2);
		GameRegistry.registerItem(StarWarsItems.shadowtrooperLegs, "shadowtrooperLegs");

		StarWarsItems.shadowtrooperBoots = new ArmorShadowtrooper(StarWarsMod.shadowtrooperArmorMat, 1, 3);
		GameRegistry.registerItem(StarWarsItems.shadowtrooperBoots, "shadowtrooperBoots");

		StarWarsItems.jediRobes = new ArmorJediRobes();
		GameRegistry.registerItem(StarWarsItems.jediRobes, "newJediRobes");

		StarWarsItems.leiasBuns = new ArmorAddonBuns(StarWarsMod.leiaBunsArmorMat, 1, 0);
		GameRegistry.registerItem(StarWarsItems.leiasBuns, "leiasBuns");

		StarWarsItems.spawnAstromech = new ItemSpawnAstromech();
		GameRegistry.registerItem(StarWarsItems.spawnAstromech, "spawnAstromech");

		StarWarsItems.spawnAstromech2 = new ItemSpawnAstromech2();
		GameRegistry.registerItem(StarWarsItems.spawnAstromech2, "spawnAstromech2");

		StarWarsItems.spawnProtocol = new ItemSpawnProtocol();
		GameRegistry.registerItem(StarWarsItems.spawnProtocol, "spawnProtocol");

		StarWarsItems.spawnProtocol2 = new ItemSpawnProtocol2();
		GameRegistry.registerItem(StarWarsItems.spawnProtocol2, "spawnProtocol2");

		StarWarsItems.spawnGonk = new ItemSpawnGonk();
		GameRegistry.registerItem(StarWarsItems.spawnGonk, "spawnGonk");

		StarWarsItems.spawnSurgical = new ItemSpawnSurgical();
		GameRegistry.registerItem(StarWarsItems.spawnSurgical, "spawnSurgical");

		StarWarsItems.spawnAstromechImperial = new ItemSpawnAstromechImperial();
		GameRegistry.registerItem(StarWarsItems.spawnAstromechImperial, "spawnAstromechImperial");

		StarWarsItems.spawnAstromechImperial2 = new ItemSpawnAstromechImperial2();
		GameRegistry.registerItem(StarWarsItems.spawnAstromechImperial2, "spawnAstromechImperial2");

		StarWarsItems.spawnMouse = new ItemSpawnMouse();
		GameRegistry.registerItem(StarWarsItems.spawnMouse, "spawnMouse");

		StarWarsItems.spawnProbe = new ItemSpawnProbe();
		GameRegistry.registerItem(StarWarsItems.spawnProbe, "spawnProbe");

		StarWarsItems.spawnScootemaround = new ItemSpawnScootemaround();
		GameRegistry.registerItem(StarWarsItems.spawnScootemaround, "spawnScootemaround");

		StarWarsItems.spawnScootemaroundHoth = new ItemSpawnScootemaroundHoth();
		GameRegistry.registerItem(StarWarsItems.spawnScootemaroundHoth, "spawnScootemaroundHoth");

		StarWarsItems.spawnLandspeeder = new ItemSpawnLandspeeder();
		GameRegistry.registerItem(StarWarsItems.spawnLandspeeder, "spawnLandspeeder");

		StarWarsItems.spawnSpeederBike = new ItemSpawnSpeederBike();
		GameRegistry.registerItem(StarWarsItems.spawnSpeederBike, "spawnSpeederBike");

		StarWarsItems.spawnHothSpeederBike = new ItemSpawnHothSpeederBike();
		GameRegistry.registerItem(StarWarsItems.spawnHothSpeederBike, "spawnHothSpeederBike");

		StarWarsItems.spawnAtst = new ItemSpawnATST();
		GameRegistry.registerItem(StarWarsItems.spawnAtst, "spawnAtst");

		StarWarsItems.spawnSkyhopper = new ItemSpawnSkyhopper();
		GameRegistry.registerItem(StarWarsItems.spawnSkyhopper, "spawnSkyhopper");

		StarWarsItems.spawnXwing = new ItemSpawnXWing();
		GameRegistry.registerItem(StarWarsItems.spawnXwing, "spawnXWing");

		StarWarsItems.spawnYwing = new ItemSpawnYWing();
		GameRegistry.registerItem(StarWarsItems.spawnYwing, "spawnYWing");

		StarWarsItems.spawnAwing = new ItemSpawnAWing();
		GameRegistry.registerItem(StarWarsItems.spawnAwing, "spawnAWing");

		StarWarsItems.spawnSnowspeeder = new ItemSpawnSnowspeeder();
		GameRegistry.registerItem(StarWarsItems.spawnSnowspeeder, "spawnSnowspeeder");

		StarWarsItems.spawnTie = new ItemSpawnTIE();
		GameRegistry.registerItem(StarWarsItems.spawnTie, "spawnTIE");

		StarWarsItems.spawnTieInterceptor = new ItemSpawnTIEInterceptor();
		GameRegistry.registerItem(StarWarsItems.spawnTieInterceptor, "spawnTIEInterceptor");

		StarWarsItems.spawnTieBomber = new ItemSpawnTIEBomber();
		GameRegistry.registerItem(StarWarsItems.spawnTieBomber, "spawnTIEBomber");

		StarWarsItems.spawnTieAdvanced = new ItemSpawnTIEAdvanced();
		GameRegistry.registerItem(StarWarsItems.spawnTieAdvanced, "spawnTIEAdvanced");

		Lumberjack.info("Items, reporting for duty!");
	}
}
