package com.parzivail.pswm.handlers;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.Resources.ConfigOptions;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.entities.EntityBlasterBoltBase;
import com.parzivail.pswm.force.Cron;
import com.parzivail.pswm.force.ItemHolocron;
import com.parzivail.pswm.force.powers.PowerBase;
import com.parzivail.pswm.gui.AnimationHyperspace;
import com.parzivail.pswm.gui.GuiBinocs;
import com.parzivail.pswm.gui.GuiBlaster;
import com.parzivail.pswm.gui.GuiVehicle;
import com.parzivail.pswm.items.ItemBinoculars;
import com.parzivail.pswm.items.ItemBinocularsHoth;
import com.parzivail.pswm.items.ItemQuestLog;
import com.parzivail.pswm.items.weapons.ItemLightsaber;
import com.parzivail.pswm.network.MessageCreateBlasterBolt;
import com.parzivail.pswm.network.MessageHolocronSetActive;
import com.parzivail.pswm.rendering.IHandlesRender;
import com.parzivail.pswm.rendering.RenderLightsaber;
import com.parzivail.pswm.rendering.force.ModelJediCloak;
import com.parzivail.pswm.rendering.force.RenderSithLightning;
import com.parzivail.pswm.sound.SoundManager;
import com.parzivail.pswm.utils.BlasterBoltType;
import com.parzivail.pswm.utils.BlasterPosition;
import com.parzivail.pswm.vehicles.*;
import com.parzivail.util.entity.EntityUtils;
import com.parzivail.util.entity.PlayerHelper;
import com.parzivail.util.math.AnimationManager;
import com.parzivail.util.ui.*;
import com.parzivail.util.vehicle.VehicleAirBase;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.world.WorldEvent;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ClientEventHandler
{
	public static boolean cursorOpen = true;
	public static boolean isCursorAnim = false;

	static HashMap<String, ArrayList<ItemStack>> playerRespawnItems = new HashMap<>();

	@SideOnly(Side.CLIENT)
	public static PlayerHelper playerHelper;
	@SideOnly(Side.CLIENT)
	public static RenderHelper renderHelper;

	@SideOnly(Side.CLIENT)
	private static GuiBlaster guiBlaster;
	@SideOnly(Side.CLIENT)
	public static GuiVehicle guiVehicle;
	@SideOnly(Side.CLIENT)
	private static GuiBinocs guiBinocs;
	@SideOnly(Side.CLIENT)
	private static RenderSithLightning renderSithLightning;
	@SideOnly(Side.CLIENT)
	private static RenderLightsaber renderLightsaber;

	@SideOnly(Side.CLIENT)
	private static ModelJediCloak modelCloak;

	@SideOnly(Side.CLIENT)
	static SoundManager soundManager;

	@SideOnly(Side.CLIENT)
	public void init()
	{
		renderHelper = new RenderHelper(Minecraft.getMinecraft());
		playerHelper = new PlayerHelper(Minecraft.getMinecraft());
		soundManager = new SoundManager();
		soundManager.init();
		renderSithLightning = new RenderSithLightning();
		modelCloak = new ModelJediCloak();
		guiVehicle = new GuiVehicle();
		guiBinocs = new GuiBinocs();
		guiBlaster = new GuiBlaster();
		renderLightsaber = new RenderLightsaber();
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onDrawHand(RenderHandEvent renderHandEvent)
	{
		renderHandEvent.setCanceled(StarWarsMod.isOverlayOnscreen);
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onFogify(EntityViewRenderEvent.FogDensity fogDensity)
	{
		if (fogDensity.entity.dimension == ConfigOptions.dimDagobahId)
		{
			FloatBuffer fogColor = BufferUtils.createFloatBuffer(4);
			fogColor.put(0.6f).put(0.7f).put(0.6f).put(1.0f).flip();
			GL11.glFog(GL11.GL_FOG_COLOR, fogColor);
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onFOVCheck(FOVUpdateEvent fovUpdateEvent)
	{
		ItemStack item = fovUpdateEvent.entity.inventory.getCurrentItem();
		if (item != null && (item.getItem() instanceof ItemBinoculars || item.getItem() instanceof ItemBinocularsHoth) && ItemBinoculars.getEnabled(item) && StarWarsMod.mc.gameSettings.thirdPersonView == 0)
			fovUpdateEvent.newfov = fovUpdateEvent.fov / ItemBinoculars.getZoom(item);
	}

	@SubscribeEvent
	public void onDrop(PlayerDropsEvent event)
	{
		Iterator<EntityItem> i = event.drops.iterator();

		ArrayList<ItemStack> s = new ArrayList<>();
		while (i.hasNext())
		{
			EntityItem n = i.next();
			if (n.getEntityItem().getItem() instanceof ItemQuestLog || n.getEntityItem().getItem() instanceof ItemHolocron)
			{
				s.add(n.getEntityItem());
				i.remove();
			}
		}
		playerRespawnItems.put(event.entityPlayer.getCommandSenderName(), s);
		//Lumberjack.log(s);
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onLivingHurt(LivingHurtEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer entityPlayer = (EntityPlayer)event.entityLiving;

			PowerBase active = Cron.getActive(entityPlayer);

			if (active == null)
				return;

			if (active.name.equals("defend") && active.health > 0)
			{
				if (active.health > event.ammount)
				{
					active.health -= event.ammount;
					event.setCanceled(true);
				}
				else
				{
					event.ammount -= active.health;
					active.health = 0;
				}

				if (event.source.getEntity() != null)
					EntityBlasterBoltBase.deflectFX(event.source.getEntity());

				StarWarsMod.network.sendToServer(new MessageHolocronSetActive(entityPlayer, active.serialize()));
			}

			if (active.name.equals("deflect") && active.isRunning)
				if (event.source.isProjectile())
				{
					if (event.source.getEntity() != null)
						EntityBlasterBoltBase.deflectFX(event.source.getEntity());
					event.setCanceled(true);
				}
		}
	}

	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load loadEvent)
	{
		//File dir = StarWarsMod.instance.preInitEvent.getModConfigurationDirectory();

		//try
		//{
		//	StarWarsMod.saveNbtMappings(new File(dir, loadEvent.world.getSaveHandler().loadWorldInfo().getWorldName() + "-map.nbt"));
		//}
		//catch (NullPointerException e)
		//{
		//	Lumberjack.debug("Couldn't save NBT map. Probably connecting to a server.");
		//}
	}

	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent playerInteractEvent)
	{
		if (playerInteractEvent.entityPlayer.ridingEntity != null && playerInteractEvent.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR && playerInteractEvent.entityPlayer.inventory.getCurrentItem() == null)
		{
			Entity targetted = EntityUtils.rayTrace(100, playerInteractEvent.entityPlayer, new Entity[] { playerInteractEvent.entityPlayer.ridingEntity });

			byte pos = (playerInteractEvent.entityPlayer.ridingEntity instanceof VehicXWing) ? BlasterPosition.getNextXwingPosition() : BlasterPosition.getNextPosition();

			if (playerInteractEvent.entityPlayer.ridingEntity instanceof VehicXWing && !((VehicXWing)playerInteractEvent.entityPlayer.ridingEntity).getHasAstro())
				targetted = null;
			else if (playerInteractEvent.entityPlayer.ridingEntity instanceof VehicYWing && !((VehicYWing)playerInteractEvent.entityPlayer.ridingEntity).getHasAstro())
				targetted = null;

			if (playerInteractEvent.entityPlayer.ridingEntity instanceof VehicSpeederBike || playerInteractEvent.entityPlayer.ridingEntity instanceof VehicHothSpeederBike)
			{
				StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(playerInteractEvent.entityPlayer, BlasterBoltType.SPEEDER, null, pos));
				playerInteractEvent.entityPlayer.playSound(Resources.MODID + ":" + "fx.shoot.bike", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(playerInteractEvent.world.rand, -0.2D, 0.2D));
			}
			else if (playerInteractEvent.entityPlayer.ridingEntity instanceof VehicXWing)
			{
				StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(playerInteractEvent.entityPlayer, BlasterBoltType.XWING, targetted, pos));
				playerInteractEvent.entityPlayer.playSound(Resources.MODID + ":" + "vehicle.xwing.fire", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(playerInteractEvent.entityPlayer.worldObj.rand, -0.2D, 0.2D));
			}
			else if (playerInteractEvent.entityPlayer.ridingEntity instanceof VehicYWing)
			{
				StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(playerInteractEvent.entityPlayer, BlasterBoltType.YWING, targetted, pos));
				playerInteractEvent.entityPlayer.playSound(Resources.MODID + ":" + "vehicle.xwing.fire", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(playerInteractEvent.entityPlayer.worldObj.rand, -0.2D, 0.2D));
			}
			else if (playerInteractEvent.entityPlayer.ridingEntity instanceof VehicAWing)
			{
				StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(playerInteractEvent.entityPlayer, BlasterBoltType.AWING, targetted, pos));
				playerInteractEvent.entityPlayer.playSound(Resources.MODID + ":" + "vehicle.xwing.fire", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(playerInteractEvent.entityPlayer.worldObj.rand, -0.2D, 0.2D));
			}
			else if (playerInteractEvent.entityPlayer.ridingEntity instanceof VehicSnowspeeder)
			{
				StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(playerInteractEvent.entityPlayer, BlasterBoltType.SNOWSPEEDER, targetted, pos));
				playerInteractEvent.entityPlayer.playSound(Resources.MODID + ":" + "vehicle.xwing.fire", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(playerInteractEvent.entityPlayer.worldObj.rand, -0.2D, 0.2D));
			}
			else if (playerInteractEvent.entityPlayer.ridingEntity instanceof VehicSkyhopper)
			{
				StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(playerInteractEvent.entityPlayer, BlasterBoltType.SKYHOPPER, targetted, pos));
				playerInteractEvent.entityPlayer.playSound(Resources.MODID + ":" + "vehicle.xwing.fire", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(playerInteractEvent.entityPlayer.worldObj.rand, -0.2D, 0.2D));
			}
			else if (playerInteractEvent.entityPlayer.ridingEntity instanceof VehicATST)
			{
				StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(playerInteractEvent.entityPlayer, BlasterBoltType.ATST, null, pos));
				playerInteractEvent.entityPlayer.playSound(Resources.MODID + ":" + "fx.shoot.bike", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(playerInteractEvent.entityPlayer.worldObj.rand, -0.2D, 0.2D));
			}
			else if (playerInteractEvent.entityPlayer.ridingEntity instanceof VehicTIE || playerInteractEvent.entityPlayer.ridingEntity instanceof VehicTIEInterceptor || playerInteractEvent.entityPlayer.ridingEntity instanceof VehicTIEAdvanced || playerInteractEvent.entityPlayer.ridingEntity instanceof VehicTIEBomber)
			{
				StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(playerInteractEvent.entityPlayer, BlasterBoltType.TIE, targetted, pos));
				playerInteractEvent.entityPlayer.playSound(Resources.MODID + ":" + "vehicle.tie.fire", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(playerInteractEvent.world.rand, -0.2D, 0.2D));
			}
		}
	}

	@SubscribeEvent
	public void onPlayerLogIn(EntityJoinWorldEvent logInEvent)
	{
		if (logInEvent.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)logInEvent.entity;
			if (!Resources.VERSION.equalsIgnoreCase(Resources.ONLINE_VERSION) && !StarWarsMod.hasShownNeedUpdate && logInEvent.world.isRemote)
			{
				player.addChatMessage(new ChatComponentText(LangUtils.translate("pswm.newversion", TextUtils.addEffect(Resources.ONLINE_VERSION, TextEffects.COLOR_YELLOW), TextUtils.addEffect(Resources.VERSION, TextEffects.COLOR_YELLOW))));
				StarWarsMod.hasShownNeedUpdate = true;
			}

			ItemStack qlog = ItemQuestLog.getQuestContainer(player);
			if (qlog != null && ItemQuestLog.getInHyperspace(qlog))
			{
				if (player.worldObj.isRemote)
					new AnimationHyperspace(3500, true).start();
				ItemQuestLog.setInHyperspace(qlog, false);
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onRender(RenderLivingEvent.Pre event)
	{
		if (StarWarsMod.mc.thePlayer != null && StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicATST)
		{
			if (ClientEventHandler.renderHelper.isFirstPerson())
			{
				GFX.changeCameraDist(4);

				//event.setCanceled(event.entity == StarWarsMod.mc.thePlayer.ridingEntity);
			}
			else
			{
				GFX.changeCameraDist(10);

				event.setCanceled(event.entity.ridingEntity instanceof VehicATST);
			}
		}
		else if (StarWarsMod.mc.thePlayer != null && StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicYWing)
		{
			if (ClientEventHandler.renderHelper.isFirstPerson())
			{
				GFX.changeCameraDist(4);

				//event.setCanceled(event.entity == StarWarsMod.mc.thePlayer.ridingEntity);
			}
			else
			{
				GFX.changeCameraDist(25);

				event.setCanceled(event.entity.ridingEntity instanceof VehicleAirBase);
			}
		}
		else if (StarWarsMod.mc.thePlayer != null && StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicleAirBase)
		{
			if (ClientEventHandler.renderHelper.isFirstPerson())
			{
				GFX.changeCameraDist(4);

				if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicSkyhopper || StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicSnowspeeder)
					event.setCanceled(event.entity == StarWarsMod.mc.thePlayer.ridingEntity);
			}
			else
			{
				GFX.changeCameraDist(15);

				event.setCanceled(event.entity.ridingEntity instanceof VehicleAirBase);
			}
		}
		else
			GFX.changeCameraDist(4);

		if (event.entity instanceof EntityPlayer && event.entity.ridingEntity instanceof VehicleAirBase)
		{
			if (event.isCancelable())
				event.setCanceled(true);
			return;
		}

		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;

			for (int i = 0; i < 9; i++)
			{
				ItemStack inv = player.inventory.mainInventory[i];
				if (inv != null && inv.getItem() instanceof ItemLightsaber && i != player.inventory.currentItem)
				{
					IHandlesRender lsRenderer = RenderLightsaber.getHiltRendererForStack(inv);

					GL11.glPushMatrix();
					GL11.glDisable(GL11.GL_CULL_FACE);

					GL11.glScalef(0.5f, -0.5f, 0.5f);
					GL11.glTranslatef(MathHelper.cos((float)Math.toRadians(player.renderYawOffset)), 0, MathHelper.sin((float)Math.toRadians(player.renderYawOffset)));
					GL11.glRotatef(-player.renderYawOffset, 0, 1, 0);
					GL11.glTranslatef(-1.45f, 1.6f, 0.275f);
					if (player.isSneaking())
						GL11.glTranslatef(0, -0.1f, -0.5f);
					GL11.glScalef(0.6f, 0.6f, 0.6f);
					GL11.glRotatef(180, 0, 1, 0);

					String s = inv.stackTagCompound.getString(ItemLightsaber.nbtHilt);
					switch (s)
					{
						case "dooku":
							GL11.glTranslatef(0, 0.075f, 0);
							GL11.glRotatef(-90, 0, 1, 0);
							break;
						case "ezra":
							GL11.glRotatef(180, 0, 1, 0);
							GL11.glTranslatef(0, 0.075f, 0);
							break;
						case "kanan":
							GL11.glTranslatef(0, 0.16f, 0);
							GL11.glRotatef(90, 0, 1, 0);
							break;
						case "padawan":
							GL11.glTranslatef(0, 0.3f, 0);
							break;
						case "shoto":
							GL11.glRotatef(180, 0, 1, 0);
							GL11.glTranslatef(0, 0.27f, 0);
							break;
						case "vader2":
							P3D.glScalef(1.2f);
							GL11.glTranslatef(0, 0.1f, 0);
							break;
						case "luke1":
							P3D.glScalef(1.1f);
							GL11.glTranslatef(0, 0.2f, 0);
							break;
						case "luke2":
							P3D.glScalef(1.1f);
							GL11.glTranslatef(0, 0.25f, 0);
							break;
						case "crossguard":
							GL11.glTranslatef(0, 0.24f, 0);
							GL11.glScalef(1.2f, 1.2f, 1.2f);
							GL11.glRotatef(90, 0, 1, 0);
							break;
						case "malgus":
							GL11.glScalef(0.85f, 0.85f, 0.85f);
							GL11.glTranslatef(0, 0.29f, 0);
							GL11.glRotatef(90, 0, 1, 0);
							break;
						case "obiwan":
							GL11.glScalef(0.9f, 0.9f, 0.9f);
							GL11.glTranslatef(0, 0.27f, 0);
							break;
						case "quigon":
							GL11.glScalef(0.9f, 0.9f, 0.9f);
							GL11.glTranslatef(0, 0.34f, 0);
							break;
						case "revan":
							GL11.glScalef(0.9f, 0.9f, 0.9f);
							GL11.glTranslatef(0, 0.31f, 0);
							break;
						case "starkiller":
							GL11.glTranslatef(0, 0.3f, 0);
							GL11.glScalef(1.1f, 1.1f, 1.1f);
							break;
						case "doubleSith":
							P3D.glScalef(1.4f);
							GL11.glTranslatef(-0.02f, -0.7f, 0.065f);
							GL11.glRotatef(-90, 0, 1, 0);
							break;
						case "maul":
							P3D.glScalef(0.95f);
							GL11.glTranslatef(0, -0.45f, 0.05f);
							break;
					}

					renderLightsaber.renderHiltItem(lsRenderer, inv.stackTagCompound.getInteger(ItemLightsaber.nbtHiltSkin) == 1);

					GL11.glEnable(GL11.GL_CULL_FACE);
					GL11.glPopMatrix();
					break;
				}
			}
		}

	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onRenderGui(RenderGameOverlayEvent.Pre event)
	{
		StarWarsMod.isOverlayOnscreen = false;
		if (ClientEventHandler.renderHelper.isFirstPerson())
		{
			GuiBinocs.onRenderGui(event);

			guiVehicle.onRenderGui(event);
		}

		GuiBlaster.onRenderGui(event);

		if (event.isCancelable() && (event.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS || event.type == RenderGameOverlayEvent.ElementType.CHAT || event.type == RenderGameOverlayEvent.ElementType.HELMET || event.type == RenderGameOverlayEvent.ElementType.HOTBAR || event.type == RenderGameOverlayEvent.ElementType.HEALTH || event.type == RenderGameOverlayEvent.ElementType.HEALTHMOUNT || event.type == RenderGameOverlayEvent.ElementType.EXPERIENCE || event.type == RenderGameOverlayEvent.ElementType.FOOD || event.type == RenderGameOverlayEvent.ElementType.ARMOR || event.type == RenderGameOverlayEvent.ElementType.JUMPBAR))
			event.setCanceled(StarWarsMod.isOverlayOnscreen);

		GuiManager.render(event);

		AnimationManager.render(event);

	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onRenderPlayerSpecial(RenderPlayerEvent.Specials.Post event)
	{
		ClientEventHandler.modelCloak.renderCloak(event);
	}

	@SubscribeEvent
	public void onXpPickup(PlayerPickupXpEvent event)
	{
		ItemStack holocron = Cron.getHolocron(event.entityPlayer);
		if (holocron != null)
		{
			int currentLevels = Cron.getLevel(holocron);
			if (StarWarsMod.rngGeneral.nextInt(100) <= Cron.getPercentForLevel(currentLevels))
				Cron.getHolocron(event.entityPlayer).stackTagCompound.setInteger(Resources.nbtLevel, currentLevels + 1);
			int newLevels = currentLevels + 1;
			//Lumberjack.log("%s %s", newLevels, currentLevels);
			if (newLevels % 10 == 0 && currentLevels % 10 != 0)
			{
				Cron.getHolocron(event.entityPlayer).stackTagCompound.setInteger(Resources.nbtRemainingPts, Cron.getPoints(event.entityPlayer) + 1);
				Cron.getHolocron(event.entityPlayer).stackTagCompound.setInteger(Resources.nbtLevel, currentLevels + 1);
				Cron.getHolocron(event.entityPlayer).stackTagCompound.setInteger(Resources.nbtMaxXp, (int)((Math.floor(newLevels / Cron.POINTS_PER_LEVEL) + 1) * 100));
				event.entityPlayer.addChatMessage(new ChatComponentText("[Holocron] Level up! You gained an upgrade point."));
				event.entityPlayer.addChatMessage(new ChatComponentText(String.format("[Holocron] You are now level %s and have %s upgrade points.", (int)Math.floor(Cron.getLevel(holocron) / Cron.POINTS_PER_LEVEL), Cron.getPoints(holocron))));
				if (newLevels == 350 && currentLevels == 349)
				{
					event.entityPlayer.addChatMessage(new ChatComponentText(String.format("[Holocron] %s", TextUtils.makeItalic(TextUtils.addEffect("You hear a dark whisper. Do you answer?", TextEffects.COLOR_DARK_GRAY)))));
					StarWarsMod.proxy.showJediSithGui(event);
				}
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void renderWorldLastEvent(RenderWorldLastEvent event)
	{
		ClientEventHandler.renderSithLightning.onWorldRender(event);
	}
}
