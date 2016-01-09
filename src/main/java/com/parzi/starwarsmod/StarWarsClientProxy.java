package com.parzi.starwarsmod;

import com.parzi.starwarsmod.entities.EntityBlasterHeavyBolt;
import com.parzi.starwarsmod.entities.EntityBlasterPistolBolt;
import com.parzi.starwarsmod.entities.EntityBlasterProbeBolt;
import com.parzi.starwarsmod.entities.EntityBlasterRifleBolt;
import com.parzi.starwarsmod.entities.EntityDestruction;
import com.parzi.starwarsmod.entities.EntitySpeederBlasterRifleBolt;
import com.parzi.starwarsmod.entities.EntityTIEBolt;
import com.parzi.starwarsmod.entities.EntityXWingBolt;
import com.parzi.starwarsmod.font.FontManager;
import com.parzi.starwarsmod.mobs.MobBantha;
import com.parzi.starwarsmod.mobs.MobBith;
import com.parzi.starwarsmod.mobs.MobDewback;
import com.parzi.starwarsmod.mobs.MobDroidAstromech;
import com.parzi.starwarsmod.mobs.MobDroidAstromech2;
import com.parzi.starwarsmod.mobs.MobDroidAstromechBb8;
import com.parzi.starwarsmod.mobs.MobDroidAstromechImperial;
import com.parzi.starwarsmod.mobs.MobDroidAstromechImperial2;
import com.parzi.starwarsmod.mobs.MobDroidGNK;
import com.parzi.starwarsmod.mobs.MobDroidMouse;
import com.parzi.starwarsmod.mobs.MobDroidProbe;
import com.parzi.starwarsmod.mobs.MobDroidProtocol;
import com.parzi.starwarsmod.mobs.MobDroidProtocol2;
import com.parzi.starwarsmod.mobs.MobDroidSurgical;
import com.parzi.starwarsmod.mobs.MobDroidTreadwell;
import com.parzi.starwarsmod.mobs.MobEwok;
import com.parzi.starwarsmod.mobs.MobGamorrean;
import com.parzi.starwarsmod.mobs.MobJawa;
import com.parzi.starwarsmod.mobs.MobSandtrooper;
import com.parzi.starwarsmod.mobs.MobTatooineCommoner;
import com.parzi.starwarsmod.mobs.MobTauntaun;
import com.parzi.starwarsmod.mobs.MobTusken;
import com.parzi.starwarsmod.mobs.MobWampa;
import com.parzi.starwarsmod.mobs.MobWookiee;
import com.parzi.starwarsmod.models.ModelDSTurret;
import com.parzi.starwarsmod.models.mobs.ModelBantha;
import com.parzi.starwarsmod.models.mobs.ModelBith;
import com.parzi.starwarsmod.models.mobs.ModelDewback;
import com.parzi.starwarsmod.models.mobs.ModelDroidAstromech;
import com.parzi.starwarsmod.models.mobs.ModelDroidAstromech2;
import com.parzi.starwarsmod.models.mobs.ModelDroidBb8;
import com.parzi.starwarsmod.models.mobs.ModelDroidMouse;
import com.parzi.starwarsmod.models.mobs.ModelDroidProbe;
import com.parzi.starwarsmod.models.mobs.ModelDroidProtocol;
import com.parzi.starwarsmod.models.mobs.ModelDroidSurgical;
import com.parzi.starwarsmod.models.mobs.ModelDroidTreadwell;
import com.parzi.starwarsmod.models.mobs.ModelGNK;
import com.parzi.starwarsmod.models.mobs.ModelGamorrean;
import com.parzi.starwarsmod.models.mobs.ModelSmallBiped;
import com.parzi.starwarsmod.models.mobs.ModelTauntaun;
import com.parzi.starwarsmod.models.mobs.ModelWampa;
import com.parzi.starwarsmod.models.mobs.ModelWookiee;
import com.parzi.starwarsmod.models.vehicles.ModelAWing;
import com.parzi.starwarsmod.models.vehicles.ModelJakkuSpeeder;
import com.parzi.starwarsmod.models.vehicles.ModelLandspeeder;
import com.parzi.starwarsmod.models.vehicles.ModelSpeederBike;
import com.parzi.starwarsmod.models.vehicles.ModelTIE;
import com.parzi.starwarsmod.models.vehicles.ModelTIEInterceptor;
import com.parzi.starwarsmod.models.vehicles.ModelXWing;
import com.parzi.starwarsmod.registry.KeybindRegistry;
import com.parzi.starwarsmod.registry.RegisterGuiOverlays;
import com.parzi.starwarsmod.rendering.RenderBantha;
import com.parzi.starwarsmod.rendering.RenderBasket;
import com.parzi.starwarsmod.rendering.RenderBith;
import com.parzi.starwarsmod.rendering.RenderBlasterBolt;
import com.parzi.starwarsmod.rendering.RenderBlockMudTable;
import com.parzi.starwarsmod.rendering.RenderBlockTatooineTable;
import com.parzi.starwarsmod.rendering.RenderCommoner;
import com.parzi.starwarsmod.rendering.RenderDSTurret;
import com.parzi.starwarsmod.rendering.RenderDewback;
import com.parzi.starwarsmod.rendering.RenderDroidAstromech;
import com.parzi.starwarsmod.rendering.RenderDroidAstromech2;
import com.parzi.starwarsmod.rendering.RenderDroidBb8;
import com.parzi.starwarsmod.rendering.RenderDroidMouse;
import com.parzi.starwarsmod.rendering.RenderDroidProbe;
import com.parzi.starwarsmod.rendering.RenderDroidProtocol;
import com.parzi.starwarsmod.rendering.RenderDroidSurgical;
import com.parzi.starwarsmod.rendering.RenderDroidTreadwell;
import com.parzi.starwarsmod.rendering.RenderEwok;
import com.parzi.starwarsmod.rendering.RenderGNK;
import com.parzi.starwarsmod.rendering.RenderGamorrean;
import com.parzi.starwarsmod.rendering.RenderHangingBucket;
import com.parzi.starwarsmod.rendering.RenderHangingCauldron;
import com.parzi.starwarsmod.rendering.RenderHuman;
import com.parzi.starwarsmod.rendering.RenderJawa;
import com.parzi.starwarsmod.rendering.RenderLightsaber;
import com.parzi.starwarsmod.rendering.RenderLightsaberKylo;
import com.parzi.starwarsmod.rendering.RenderLightsaberKyloOff;
import com.parzi.starwarsmod.rendering.RenderLightsaberOff;
import com.parzi.starwarsmod.rendering.RenderMV;
import com.parzi.starwarsmod.rendering.RenderTauntaun;
import com.parzi.starwarsmod.rendering.RenderTusken;
import com.parzi.starwarsmod.rendering.RenderWampa;
import com.parzi.starwarsmod.rendering.RenderWookiee;
import com.parzi.starwarsmod.rendering.helper.PSWMEntityRenderer;
import com.parzi.starwarsmod.rendering.vehicles.RenderAWing;
import com.parzi.starwarsmod.rendering.vehicles.RenderJakkuSpeeder;
import com.parzi.starwarsmod.rendering.vehicles.RenderLandspeeder;
import com.parzi.starwarsmod.rendering.vehicles.RenderSpeederBike;
import com.parzi.starwarsmod.rendering.vehicles.RenderTIE;
import com.parzi.starwarsmod.rendering.vehicles.RenderTIEInterceptor;
import com.parzi.starwarsmod.rendering.vehicles.RenderXWing;
import com.parzi.starwarsmod.tileentities.TileEntityBasket;
import com.parzi.starwarsmod.tileentities.TileEntityHangingBucket;
import com.parzi.starwarsmod.tileentities.TileEntityHangingCauldron;
import com.parzi.starwarsmod.tileentities.TileEntityMV;
import com.parzi.starwarsmod.tileentities.TileEntityMudTable;
import com.parzi.starwarsmod.tileentities.TileEntityTatooineTable;
import com.parzi.starwarsmod.vehicles.VehicAWing;
import com.parzi.starwarsmod.vehicles.VehicHothSpeederBike;
import com.parzi.starwarsmod.vehicles.VehicJakkuSpeeder;
import com.parzi.starwarsmod.vehicles.VehicLandspeeder;
import com.parzi.starwarsmod.vehicles.VehicSpeederBike;
import com.parzi.starwarsmod.vehicles.VehicTIE;
import com.parzi.starwarsmod.vehicles.VehicTIEInterceptor;
import com.parzi.starwarsmod.vehicles.VehicXWing;
import com.parzi.starwarsmod.weaponry.WeaponDSTurret;
import com.parzi.util.ui.Lumberjack;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.client.MinecraftForgeClient;

public class StarWarsClientProxy extends StarWarsCommonProxy
{
	@Override
	public void doSidedThings()
	{
		Minecraft.getMinecraft().entityRenderer = new PSWMEntityRenderer(Minecraft.getMinecraft(), Minecraft.getMinecraft().getResourceManager());

		StarWarsMod.mc = Minecraft.getMinecraft();

		StarWarsMod.clientHandler.init();

		if (Minecraft.getMinecraft().getSession().getUsername().equalsIgnoreCase("StarWarsMod") || Minecraft.getMinecraft().getSession().getUsername().equalsIgnoreCase("weaston"))
		{
			Resources.IS_DEV_ENVIRONVENT = true;
			Lumberjack.info("This is a development environment! Debug mechanics implemented.");
			Lumberjack.info("Development version " + Resources.DEV_VER);
		}

		KeybindRegistry.registerAll();

		FontManager.registerAll();

		if (StarWarsMod.beshOverride)
			Minecraft.getMinecraft().fontRenderer = FontManager.aurebesh;

		Lumberjack.log("Client proxy loaded!");
	}

	@Override
	public void registerRendering()
	{

		RenderingRegistry.registerEntityRenderingHandler(MobWookiee.class, new RenderWookiee(new ModelWookiee(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobJawa.class, new RenderJawa(new ModelSmallBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobEwok.class, new RenderEwok(new ModelSmallBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobTauntaun.class, new RenderTauntaun(new ModelTauntaun(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobBantha.class, new RenderBantha(new ModelBantha(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobWampa.class, new RenderWampa(new ModelWampa(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobTusken.class, new RenderTusken(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobDroidGNK.class, new RenderGNK(new ModelGNK(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobGamorrean.class, new RenderGamorrean(new ModelGamorrean(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobDewback.class, new RenderDewback(new ModelDewback(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobTatooineCommoner.class, new RenderCommoner(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobBith.class, new RenderBith(new ModelBith(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobSandtrooper.class, new RenderHuman(new ModelBiped(), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(MobDroidAstromech.class, new RenderDroidAstromech(new ModelDroidAstromech(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobDroidAstromech2.class, new RenderDroidAstromech2(new ModelDroidAstromech2(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobDroidAstromechImperial.class, new RenderDroidAstromech(new ModelDroidAstromech(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobDroidAstromechImperial2.class, new RenderDroidAstromech2(new ModelDroidAstromech2(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobDroidAstromechBb8.class, new RenderDroidBb8(new ModelDroidBb8(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobDroidProbe.class, new RenderDroidProbe(new ModelDroidProbe(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobDroidProtocol.class, new RenderDroidProtocol(new ModelDroidProtocol(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobDroidProtocol2.class, new RenderDroidProtocol(new ModelDroidProtocol(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobDroidSurgical.class, new RenderDroidSurgical(new ModelDroidSurgical(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobDroidTreadwell.class, new RenderDroidTreadwell(new ModelDroidTreadwell(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(MobDroidMouse.class, new RenderDroidMouse(new ModelDroidMouse(), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(VehicHothSpeederBike.class, new RenderSpeederBike(new ModelSpeederBike(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(VehicSpeederBike.class, new RenderSpeederBike(new ModelSpeederBike(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(VehicLandspeeder.class, new RenderLandspeeder(new ModelLandspeeder(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(VehicJakkuSpeeder.class, new RenderJakkuSpeeder(new ModelJakkuSpeeder(), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(VehicTIE.class, new RenderTIE(new ModelTIE(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(VehicTIEInterceptor.class, new RenderTIEInterceptor(new ModelTIEInterceptor(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(VehicXWing.class, new RenderXWing(new ModelXWing(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(VehicAWing.class, new RenderAWing(new ModelAWing(), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(WeaponDSTurret.class, new RenderDSTurret(new ModelDSTurret(), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(EntityBlasterPistolBolt.class, new RenderBlasterBolt(StarWarsMod.blasterBolt));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlasterRifleBolt.class, new RenderBlasterBolt(StarWarsMod.blasterRifleBolt));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlasterHeavyBolt.class, new RenderBlasterBolt(StarWarsMod.blasterRifleBolt));
		RenderingRegistry.registerEntityRenderingHandler(EntityBlasterProbeBolt.class, new RenderBlasterBolt(StarWarsMod.blasterRifleBolt));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpeederBlasterRifleBolt.class, new RenderBlasterBolt(StarWarsMod.blasterRifleBolt));
		RenderingRegistry.registerEntityRenderingHandler(EntityXWingBolt.class, new RenderBlasterBolt(StarWarsMod.blasterRifleBolt));
		RenderingRegistry.registerEntityRenderingHandler(EntityTIEBolt.class, new RenderBlasterBolt(StarWarsMod.blasterTIEBolt));
		RenderingRegistry.registerEntityRenderingHandler(EntityXWingBolt.class, new RenderBlasterBolt(StarWarsMod.blasterXWingBolt));
		RenderingRegistry.registerEntityRenderingHandler(EntityDestruction.class, new RenderBlasterBolt(StarWarsMod.spawnAstromechBb8));

		MinecraftForgeClient.registerItemRenderer(StarWarsMod.lightsaber, new RenderLightsaber());
		MinecraftForgeClient.registerItemRenderer(StarWarsMod.lightsaberOff, new RenderLightsaberOff());
		MinecraftForgeClient.registerItemRenderer(StarWarsMod.sequelLightsaber, new RenderLightsaberKylo());
		MinecraftForgeClient.registerItemRenderer(StarWarsMod.sequelLightsaberOff, new RenderLightsaberKyloOff());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMV.class, new RenderMV());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTatooineTable.class, new RenderBlockTatooineTable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMudTable.class, new RenderBlockMudTable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHangingCauldron.class, new RenderHangingCauldron());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHangingBucket.class, new RenderHangingBucket());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBasket.class, new RenderBasket());

		RegisterGuiOverlays.registerAll();

		Lumberjack.log("Rendering registered!");
	}
}
/*
 * Location: C:\Users\Colby\Downloads\Parzi's Star Wars Mod
 * v1.2.0-dev7.jar!\com\parzi\starwarsmod\StarWarsClientProxy.class Java
 * compiler version: 6 (50.0) JD-Core Version: 0.7.1
 */