package com.parzi.starwarsmod.handlers;

import java.util.Arrays;
import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.parzi.starwarsmod.StarWarsMod;
import com.parzi.starwarsmod.armor.ArmorJediRobes;
import com.parzi.starwarsmod.armor.ArmorLightJediRobes;
import com.parzi.starwarsmod.items.ItemBinoculars;
import com.parzi.starwarsmod.items.ItemBinocularsTatooine;
import com.parzi.starwarsmod.network.CreateBlasterBoltSpeeder;
import com.parzi.starwarsmod.network.JediRobesSetElementInArmorInv;
import com.parzi.starwarsmod.utils.Text;
import com.parzi.starwarsmod.utils.TextUtils;
import com.parzi.starwarsmod.vehicles.VehicHothSpeederBike;
import com.parzi.starwarsmod.vehicles.VehicSpeederBike;
import com.parzi.starwarsmod.vehicles.VehicTIE;
import com.parzi.starwarsmod.vehicles.VehicXWing;
import com.parzi.starwarsmod.vehicles.VehicleAirBase;
import com.parzi.starwarsmod.vehicles.VehicleBase;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;

public class StarWarsEventHandler
{
	public static Minecraft mc = Minecraft.getMinecraft();
	public static MinecraftServer mcServer = MinecraftServer.getServer();
	public static FakePlayer fakePlayer;

	@SubscribeEvent
	public void onBlockBroken(BlockEvent.BreakEvent breakEvent)
	{
		if (breakEvent.getPlayer().inventory.armorInventory[2] != null && (breakEvent.getPlayer().inventory.armorInventory[2].getItem() instanceof ArmorJediRobes || breakEvent.getPlayer().inventory.armorInventory[2].getItem() instanceof ArmorLightJediRobes) && Arrays.asList(ArmorJediRobes.earthMatter).contains(breakEvent.block) && breakEvent.world.rand.nextInt(ArmorJediRobes.chanceElement / 10) == 0) StarWarsMod.network.sendToServer(new JediRobesSetElementInArmorInv("earth", breakEvent.getPlayer().inventory.armorInventory[2].stackTagCompound.getInteger("earth") + 1, breakEvent.getPlayer().dimension, breakEvent.getPlayer().getCommandSenderName()));
	}

	@SubscribeEvent
	public void onDrawHand(RenderHandEvent renderHandEvent)
	{
		renderHandEvent.setCanceled(StarWarsMod.isOverlayOnscreen);
	}

	@SubscribeEvent
	public void onRenderStuff(RenderLivingEvent.Pre event)
	{
		if (mc.thePlayer.ridingEntity instanceof VehicleAirBase) StarWarsMod.renderHelper.setCameraMode(1);

		if (!StarWarsMod.renderHelper.isFirstPerson() && (mc.thePlayer.ridingEntity instanceof VehicXWing || mc.thePlayer.ridingEntity instanceof VehicTIE))
		{
			if (fakePlayer == null) fakePlayer = FakePlayerFactory.get(mcServer.worldServerForDimension(mc.thePlayer.worldObj.provider.dimensionId), new GameProfile(UUID.randomUUID(), "cameraEvent"));
			// offset slightly so not looking at inside of player model:
			Vec3 vec3 = mc.thePlayer.getLookVec();
			double dx = vec3.xCoord * -10;
			double dy = vec3.yCoord * -10;
			double dz = vec3.zCoord * -10;

			// set position and angles; note that posY is not altered but
			// camera still correct:
			mc.renderViewEntity.setLocationAndAngles(mc.thePlayer.posX + dx, mc.thePlayer.posY + dy, mc.thePlayer.posZ + dz, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);

			// set previous values to prevent camera from freaking out:
			fakePlayer.prevRotationPitch = mc.thePlayer.prevRotationPitch;
			fakePlayer.prevRotationYaw = mc.thePlayer.prevRotationYaw;
			fakePlayer.rotationYawHead = mc.thePlayer.rotationYawHead;
			fakePlayer.prevPosX = mc.thePlayer.prevPosX + dx;
			fakePlayer.prevPosY = mc.thePlayer.prevPosY + dy;
			fakePlayer.prevPosZ = mc.thePlayer.prevPosZ + dz;

			fakePlayer.prevCameraPitch = mc.thePlayer.prevCameraPitch;
			fakePlayer.prevRenderYawOffset = mc.thePlayer.prevRenderYawOffset;

			mc.renderViewEntity = fakePlayer;
		}
		else
		{
			mc.renderViewEntity = mc.thePlayer;
		}
	}
	
	@SubscribeEvent
	public void onWorldUnload(WorldEvent.Unload worldUnloadEvent)
	{
		fakePlayer = null;
	}

	@SubscribeEvent
	public void onFOVCheck(FOVUpdateEvent fovUpdateEvent)
	{
		ItemStack item = fovUpdateEvent.entity.inventory.getCurrentItem();
		if (item != null && (item.getItem() instanceof ItemBinoculars || item.getItem() instanceof com.parzi.starwarsmod.items.ItemBinocularsHoth) && ItemBinoculars.getEnabled(item) && mc.gameSettings.thirdPersonView == 0) fovUpdateEvent.newfov = fovUpdateEvent.fov / ItemBinoculars.getZoom(item);
	}

	@SubscribeEvent
	public void onPlayerLogIn(EntityJoinWorldEvent logInEvent)
	{
		if (StarWarsMod.VERSION != StarWarsMod.ONLINE_VERSION && logInEvent.entity instanceof EntityPlayerSP) ((EntityPlayerSP)logInEvent.entity).addChatMessage(new ChatComponentText("New version of Parzi's Star Wars Mod available: " + TextUtils.addEffect(StarWarsMod.ONLINE_VERSION, Text.COLOR_YELLOW) + "! Current: " + TextUtils.addEffect(StarWarsMod.VERSION, Text.COLOR_YELLOW)));

		if (logInEvent.entity instanceof EntityPlayer)
		{
			if (logInEvent.world.provider.dimensionId == -100) logInEvent.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onFogify(EntityViewRenderEvent.FogDensity fogDensity)
	{
		if (fogDensity.entity.worldObj.provider.getDimensionName() == "Dagobah")
		{
			fogDensity.density = 0.075F;
			fogDensity.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onMobHit(AttackEntityEvent attackEntityEvent)
	{
		if (attackEntityEvent.entityPlayer.inventory.armorInventory[2] != null && (attackEntityEvent.entityPlayer.inventory.armorInventory[2].getItem() instanceof ArmorJediRobes || attackEntityEvent.entityPlayer.inventory.armorInventory[2].getItem() instanceof ArmorLightJediRobes) && attackEntityEvent.entity.worldObj.rand.nextInt(ArmorJediRobes.chanceElement / 50) == 0) StarWarsMod.network.sendToServer(new JediRobesSetElementInArmorInv("animals", attackEntityEvent.entityPlayer.inventory.armorInventory[2].stackTagCompound.getInteger("animals") + 1, attackEntityEvent.entityPlayer.dimension, attackEntityEvent.entityPlayer.getCommandSenderName()));
	}

	@SubscribeEvent
	public void onMouseMoved(MouseEvent mouseEvent)
	{
		if (mc.thePlayer.ridingEntity instanceof VehicleBase)
		{
			int limit = 5;

			VehicleBase vehicle = (VehicleBase)mc.thePlayer.ridingEntity;

			int n = mouseEvent.dx;
			if (n > limit) n = limit;
			if (n < -limit) n = -limit;

			vehicle.mouseDxOverAFewTicks[vehicle.mouseDxOverAFewTicks.length - 1] = n;
		}
	}

	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent playerInteractEvent)
	{
		if (playerInteractEvent.entityPlayer.ridingEntity != null && (playerInteractEvent.entityPlayer.ridingEntity instanceof VehicSpeederBike || playerInteractEvent.entityPlayer.ridingEntity instanceof VehicHothSpeederBike) && playerInteractEvent.action == net.minecraftforge.event.entity.player.PlayerInteractEvent.Action.RIGHT_CLICK_AIR && playerInteractEvent.entityPlayer.inventory.getCurrentItem() == null)
		{
			StarWarsMod.network.sendToServer(new CreateBlasterBoltSpeeder(playerInteractEvent.entityPlayer.getCommandSenderName(), playerInteractEvent.world.provider.dimensionId));
			mc.thePlayer.playSound(StarWarsMod.MODID + ":" + "item.blasterRifle.use", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(playerInteractEvent.world.rand, -0.2D, 0.2D));
		}
	}

	@SubscribeEvent
	public void onRenderBar(RenderGameOverlayEvent event)
	{
		StarWarsMod.isOverlayOnscreen = false;
		ItemStack item = StarWarsMod.playerHelper.getHeldItem();
		if (item != null && StarWarsMod.renderHelper.isFirstPerson()) if (item.getItem() instanceof ItemBinoculars && ItemBinoculars.getEnabled(item))
		{
			StarWarsMod.isOverlayOnscreen = ItemBinoculars.getEnabled(item);
			ResourceLocation guiTexture;
			if (item.getItem() instanceof ItemBinocularsTatooine)
				guiTexture = new ResourceLocation(StarWarsMod.MODID, "textures/gui/binoc_style/binoc_style_" + ItemBinoculars.getZoom(item) + ".png");
			else
				guiTexture = new ResourceLocation(StarWarsMod.MODID, "textures/gui/binoc_hoth/binoc_hoth_" + ItemBinoculars.getZoom(item) + ".png");
			StarWarsMod.pgui.renderOverlay(guiTexture);
		}
		if (event.isCancelable() && (event.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS || event.type == RenderGameOverlayEvent.ElementType.CHAT || event.type == RenderGameOverlayEvent.ElementType.HELMET)) event.setCanceled(StarWarsMod.isOverlayOnscreen);
	}
}
/*
 * Location: C:\Users\Colby\Downloads\Parzi's Star Wars Mod
 * v1.2.0-dev7.jar!\com\parzi\starwarsmod\handlers\StarWarsEventHandler.class
 * Java compiler version: 6 (50.0) JD-Core Version: 0.7.1
 */