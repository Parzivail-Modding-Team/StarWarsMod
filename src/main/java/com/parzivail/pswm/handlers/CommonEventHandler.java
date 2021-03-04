package com.parzivail.pswm.handlers;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.achievement.StarWarsAchievements;
import com.parzivail.pswm.entities.EntityBlasterBoltBase;
import com.parzivail.pswm.force.Cron;
import com.parzivail.pswm.force.powers.*;
import com.parzivail.pswm.gui.GuiVehicle;
import com.parzivail.pswm.items.ItemQuestLog;
import com.parzivail.pswm.items.weapons.ItemLightsaber;
import com.parzivail.pswm.network.*;
import com.parzivail.pswm.quest.QuestStats;
import com.parzivail.pswm.registry.KeybindRegistry;
import com.parzivail.pswm.sound.SoundSFoil;
import com.parzivail.pswm.utils.BlasterBoltType;
import com.parzivail.pswm.utils.BlasterPosition;
import com.parzivail.pswm.utils.EntityCooldownEntry;
import com.parzivail.pswm.vehicles.*;
import com.parzivail.util.entity.EntityUtils;
import com.parzivail.util.math.AnimationManager;
import com.parzivail.util.ui.GuiManager;
import com.parzivail.util.ui.GuiToast;
import com.parzivail.util.ui.LangUtils;
import com.parzivail.util.vehicle.VehicleAirBase;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

//import static com.parzivail.pswm.utils.ForceUtils.activePower;

public class CommonEventHandler
{
	private long lastTimeXpGive = 0;
	private boolean shouldPowerSync = false;

	@SubscribeEvent
	public void logOut(PlayerLoggedInEvent event)
	{
		this.resetRobes(event);
	}

	@SubscribeEvent
	public void logOut(PlayerLoggedOutEvent event)
	{
		this.resetRobes(event);
	}

	@SubscribeEvent
	public void logOut(PlayerRespawnEvent event)
	{
		this.resetRobes(event);

		if (ClientEventHandler.playerRespawnItems.get(event.player.getCommandSenderName()) != null)
		{
			ArrayList<ItemStack> itemStacks = ClientEventHandler.playerRespawnItems.get(event.player.getCommandSenderName());
			for (ItemStack stack : itemStacks)
			{
				event.player.inventory.addItemStackToInventory(stack);
			}
			ClientEventHandler.playerRespawnItems.remove(event.player.getCommandSenderName());
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onKeyInput(InputEvent.KeyInputEvent event)
	{
		if (KeybindRegistry.keyShootVehicle.isPressed())
		{
			if (StarWarsMod.mc.thePlayer.ridingEntity != null)
			{
				Entity targetted = EntityUtils.rayTrace(100, StarWarsMod.mc.thePlayer, new Entity[] { StarWarsMod.mc.thePlayer.ridingEntity });

				byte pos = (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicXWing) ? BlasterPosition.getNextXwingPosition() : BlasterPosition.getNextPosition();

				if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicXWing && !((VehicXWing)StarWarsMod.mc.thePlayer.ridingEntity).getHasAstro())
					targetted = null;
				else if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicYWing && !((VehicYWing)StarWarsMod.mc.thePlayer.ridingEntity).getHasAstro())
					targetted = null;

				if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicSpeederBike || StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicHothSpeederBike)
				{
					StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(StarWarsMod.mc.thePlayer, BlasterBoltType.SPEEDER, null, pos));
					StarWarsMod.mc.thePlayer.playSound(Resources.MODID + ":" + "fx.shoot.bike", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(StarWarsMod.mc.thePlayer.worldObj.rand, -0.2D, 0.2D));
				}
				else if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicXWing)
				{
					StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(StarWarsMod.mc.thePlayer, BlasterBoltType.XWING, targetted, pos));
					StarWarsMod.mc.thePlayer.playSound(Resources.MODID + ":" + "vehicle.xwing.fire", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(StarWarsMod.mc.thePlayer.worldObj.rand, -0.2D, 0.2D));
				}
				else if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicYWing)
				{
					StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(StarWarsMod.mc.thePlayer, BlasterBoltType.YWING, targetted, pos));
					StarWarsMod.mc.thePlayer.playSound(Resources.MODID + ":" + "vehicle.xwing.fire", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(StarWarsMod.mc.thePlayer.worldObj.rand, -0.2D, 0.2D));
				}
				else if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicAWing)
				{
					StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(StarWarsMod.mc.thePlayer, BlasterBoltType.AWING, targetted, pos));
					StarWarsMod.mc.thePlayer.playSound(Resources.MODID + ":" + "vehicle.xwing.fire", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(StarWarsMod.mc.thePlayer.worldObj.rand, -0.2D, 0.2D));
				}
				else if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicSnowspeeder)
				{
					StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(StarWarsMod.mc.thePlayer, BlasterBoltType.SNOWSPEEDER, targetted, pos));
					StarWarsMod.mc.thePlayer.playSound(Resources.MODID + ":" + "vehicle.xwing.fire", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(StarWarsMod.mc.thePlayer.worldObj.rand, -0.2D, 0.2D));
				}
				else if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicSkyhopper)
				{
					StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(StarWarsMod.mc.thePlayer, BlasterBoltType.SKYHOPPER, targetted, pos));
					StarWarsMod.mc.thePlayer.playSound(Resources.MODID + ":" + "vehicle.xwing.fire", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(StarWarsMod.mc.thePlayer.worldObj.rand, -0.2D, 0.2D));
				}
				else if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicATST)
				{
					StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(StarWarsMod.mc.thePlayer, BlasterBoltType.ATST, null, pos));
					StarWarsMod.mc.thePlayer.playSound(Resources.MODID + ":" + "fx.shoot.bike", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(StarWarsMod.mc.thePlayer.worldObj.rand, -0.2D, 0.2D));
				}
				else if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicTIE || StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicTIEInterceptor || StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicTIEAdvanced || StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicTIEBomber)
				{
					StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(StarWarsMod.mc.thePlayer, BlasterBoltType.TIE, targetted, pos));
					StarWarsMod.mc.thePlayer.playSound(Resources.MODID + ":" + "vehicle.tie.fire", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(StarWarsMod.mc.thePlayer.worldObj.rand, -0.2D, 0.2D));
				}
				GuiVehicle.isFiring = true;
				GuiVehicle.blipFrame = GuiVehicle.blipMax;
			}
			else if (StarWarsMod.mc.thePlayer.inventory.getCurrentItem() != null && StarWarsMod.mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemLightsaber)
			{
				ItemStack s = StarWarsMod.mc.thePlayer.inventory.getCurrentItem();
				EntityPlayer entityPlayer = StarWarsMod.mc.thePlayer;
				if (s.stackTagCompound.getString(ItemLightsaber.nbtHilt).equals("ezra") && s.stackTagCompound.getInteger(ItemLightsaber.nbtBlasterTimeout) == 0 && !s.stackTagCompound.getBoolean(ItemLightsaber.nbtBladeOn))
				{
					entityPlayer.addStat(StarWarsAchievements.ezraBlaster, 1);
					entityPlayer.playSound(Resources.MODID + ":" + "fx.shoot.dl44", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(StarWarsMod.rngGeneral, -0.2D, 0.2D));
					s.stackTagCompound.setInteger(ItemLightsaber.nbtBlasterTimeout, 40);
					StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(entityPlayer, BlasterBoltType.EZRA, null, (byte)0));
				}
			}
		}

		if (KeybindRegistry.keyShootProton.isPressed())
		{
			if (StarWarsMod.mc.thePlayer.ridingEntity != null && StarWarsMod.shipSpecialWeaponCooldown == 0)
			{
				if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicXWing)
				{
					Entity targetted = EntityUtils.rayTrace(100, StarWarsMod.mc.thePlayer, new Entity[] { StarWarsMod.mc.thePlayer.ridingEntity });

					if (!((VehicXWing)StarWarsMod.mc.thePlayer.ridingEntity).getHasAstro())
						targetted = null;

					StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(StarWarsMod.mc.thePlayer, BlasterBoltType.PROTON, targetted, BlasterPosition.BOTH_SIDES));
					StarWarsMod.mc.thePlayer.playSound(Resources.MODID + ":" + "vehicle.xwing.proton", 1.0F, 1.0F);
					StarWarsMod.shipSpecialWeaponCooldown = 200;

					ItemQuestLog.addStat(StarWarsMod.mc.thePlayer, QuestStats.PROTONS_SHOT);
					if (ItemQuestLog.getQuestContainer(StarWarsMod.mc.thePlayer) != null)
						StarWarsMod.network.sendToServer(new MessageSetQuestLogNbt(StarWarsMod.mc.thePlayer, ItemQuestLog.getQuestContainer(StarWarsMod.mc.thePlayer).stackTagCompound));
				}
				else if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicTIEBomber || StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicYWing)
				{
					StarWarsMod.network.sendToServer(new MessageCreateBlasterBolt(StarWarsMod.mc.thePlayer, BlasterBoltType.BOMB, null, BlasterPosition.BOTH_SIDES));
					StarWarsMod.mc.thePlayer.playSound(Resources.MODID + ":" + "vehicle.xwing.proton", 1.0F, 1.0F);
					StarWarsMod.shipSpecialWeaponCooldown = 200;

					String stat = StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicTIEBomber ? QuestStats.BOMBS_DROPPED_EMPIRE : QuestStats.BOMBS_DROPPED_REBEL;

					ItemQuestLog.addStat(StarWarsMod.mc.thePlayer, stat);
					if (ItemQuestLog.getQuestContainer(StarWarsMod.mc.thePlayer) != null)
						StarWarsMod.network.sendToServer(new MessageSetQuestLogNbt(StarWarsMod.mc.thePlayer, ItemQuestLog.getQuestContainer(StarWarsMod.mc.thePlayer).stackTagCompound));
				}
			}
		}

		if (KeybindRegistry.keySFoil.isPressed())
			if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicXWing)
			{
				VehicXWing xwing = (VehicXWing)StarWarsMod.mc.thePlayer.ridingEntity;
				if (xwing.getSFoil() <= 0.45f && !(xwing.isOpening || xwing.isClosing))
				{
					xwing.isOpening = true;
					Minecraft.getMinecraft().getSoundHandler().playSound(new SoundSFoil(StarWarsMod.mc.thePlayer, true));
				}
				if (xwing.getSFoil() >= 0.55f && !(xwing.isOpening || xwing.isClosing))
				{
					xwing.isClosing = true;
					Minecraft.getMinecraft().getSoundHandler().playSound(new SoundSFoil(StarWarsMod.mc.thePlayer, false));
				}
			}
			else if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicSkyhopper)
			{
				VehicSkyhopper skyh = (VehicSkyhopper)StarWarsMod.mc.thePlayer.ridingEntity;
				if (skyh.getWing() <= 0.45f && !(skyh.isOpening || skyh.isClosing))
				{
					skyh.isOpening = true;
					Minecraft.getMinecraft().getSoundHandler().playSound(new SoundSFoil(StarWarsMod.mc.thePlayer, false));
				}
				if (skyh.getWing() >= 0.55f && !(skyh.isOpening || skyh.isClosing))
				{
					skyh.isClosing = true;
					Minecraft.getMinecraft().getSoundHandler().playSound(new SoundSFoil(StarWarsMod.mc.thePlayer, true));
				}
			}

		if (KeybindRegistry.keyDebug != null && KeybindRegistry.keyDebug.isPressed())
		{
		}

		if (KeybindRegistry.keyShipHoverMode.isPressed())
		{
			boolean hover = false;
			if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicleAirBase)
				hover = !((VehicleAirBase)StarWarsMod.mc.thePlayer.ridingEntity).getHoverMode();
			StarWarsMod.network.sendToServer(new MessageShipHoverMode(StarWarsMod.mc.thePlayer, hover));
			GuiToast.makeText(LangUtils.translate("hover.mode.0", hover ? LangUtils.translate("activated") : LangUtils.translate("deactivated")), GuiToast.TIME_SHORT).show();
			StarWarsMod.mc.thePlayer.playSound(Resources.MODID + ":" + "vehicle.repulsor." + (hover ? "on" : "off"), 1, 1);
		}

		if (KeybindRegistry.keyLSForge.isPressed() && StarWarsMod.mc.thePlayer.capabilities.isCreativeMode)
			StarWarsMod.mc.thePlayer.openGui(StarWarsMod.instance, Resources.GUI_LSFORGE, null, 0, 0, 0);

		if (KeybindRegistry.keyShipHyperdrive.isPressed() && StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicleAirBase && !(StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicSnowspeeder))
			StarWarsMod.mc.thePlayer.openGui(StarWarsMod.instance, Resources.GUI_HYPERDRIVE, null, 0, 0, 0);

		if (KeybindRegistry.keyLSToggle.isPressed())
		{
			EntityPlayer player = StarWarsMod.mc.thePlayer;
			ItemStack stack = player.inventory.getCurrentItem();
			if (stack != null && stack.stackTagCompound != null && stack.stackTagCompound.getInteger(ItemLightsaber.nbtBladeTimeout) <= 0)
			{
				if (!stack.stackTagCompound.getBoolean(ItemLightsaber.nbtBladeWaterproof) && player.isInsideOfMaterial(Material.water))
				{
					stack.stackTagCompound.setInteger(ItemLightsaber.nbtBladeTimeout, 10);
					player.playSound(Resources.MODID + ":" + "item.lightsaber.fizz", 1.0F, 1.0F);
				}
				else
				{
					if (stack.stackTagCompound.getBoolean(ItemLightsaber.nbtBladeOn))
						player.playSound(Resources.MODID + ":" + "item.lightsaber.close", 1.0F, 1.0F);
					else
						player.playSound(Resources.MODID + ":" + "item.lightsaber.open", 1.0F, 1.0F);
					stack.stackTagCompound.setBoolean(ItemLightsaber.nbtBladeOn, !stack.stackTagCompound.getBoolean(ItemLightsaber.nbtBladeOn));
					stack.stackTagCompound.setInteger(ItemLightsaber.nbtBladeTimeout, 10);
					StarWarsMod.network.sendToServer(new MessageSetPlayerHolding(player, stack));
				}
			}
		}

		if (KeybindRegistry.keyQuest != null && KeybindRegistry.keyQuest.isPressed())
			StarWarsMod.mc.thePlayer.openGui(StarWarsMod.instance, Resources.GUI_QUEST, null, 0, 0, 0);

		if (KeybindRegistry.keyRobeGui.isPressed())
			if (Cron.getHolocron(StarWarsMod.mc.thePlayer) != null)
				StarWarsMod.mc.thePlayer.openGui(StarWarsMod.instance, Resources.GUI_ROBES, null, 0, 0, 0);

		if (KeybindRegistry.keyRobePowerNext.isPressed())
		{
			if (Cron.getHolocron(StarWarsMod.mc.thePlayer) != null)
			{
				String current = Cron.getActive(StarWarsMod.mc.thePlayer).name;
				ArrayList<String> powers = Cron.getPowersAvailableAtLevel(Cron.getSide(StarWarsMod.mc.thePlayer), Cron.getLevel(StarWarsMod.mc.thePlayer));
				int index = Arrays.asList(powers.toArray()).indexOf(current);
				do
				{
					index++;
					if (index >= powers.size())
						index = 0;
				}
				while (Cron.getLevelOf(StarWarsMod.mc.thePlayer, powers.get(index)) == 0 && !powers.get(index).equals(current));
				if (index < powers.size() - 1 && !powers.get(index).equals(current))
				{
					if (index < 0)
						index = powers.size() - 1;
					PowerBase selectedPower = Cron.initNewPower(StarWarsMod.mc.thePlayer, powers.get(index));
					//activePower = selectedPower;
					StarWarsMod.network.sendToServer(new MessageHolocronSetActive(StarWarsMod.mc.thePlayer, selectedPower.serialize()));
				}
			}
		}

		if (KeybindRegistry.keyRobePowerPrev.isPressed())
		{
			if (Cron.getHolocron(StarWarsMod.mc.thePlayer) != null)
			{
				String current = Cron.getActive(StarWarsMod.mc.thePlayer).name;
				ArrayList<String> powers = Cron.getPowersAvailableAtLevel(Cron.getSide(StarWarsMod.mc.thePlayer), Cron.getLevel(StarWarsMod.mc.thePlayer));
				int index = Arrays.asList(powers.toArray()).indexOf(current);
				do
				{
					index--;
					if (index < 0)
						index = powers.size() - 1;
				}
				while (Cron.getLevelOf(StarWarsMod.mc.thePlayer, powers.get(index)) == 0 && !powers.get(index).equals(current));
				if (index > -1 && !powers.get(index).equals(current))
				{
					PowerBase selectedPower = Cron.initNewPower(StarWarsMod.mc.thePlayer, powers.get(index));
					//activePower = selectedPower;
					StarWarsMod.network.sendToServer(new MessageHolocronSetActive(StarWarsMod.mc.thePlayer, selectedPower.serialize()));
				}
			}
		}

		if (KeybindRegistry.keyRobePower.isPressed())
		{
			ItemStack cron;
			if ((cron = Cron.getHolocron(StarWarsMod.mc.thePlayer)) != null)
			{
				PowerBase powerBase = Cron.getActive(cron);
				if (powerBase != null && Cron.getXP(StarWarsMod.mc.thePlayer) - powerBase.getCost() >= 0 && !Cron.isCooling(powerBase.name))
				{
					if (powerBase.recharge <= 0)
					{
						boolean coolFlag = true;
						switch (powerBase.name)
						{
							case "defend":
								PowerDefend powerDefend = (PowerDefend)powerBase;
								if (powerDefend.isRunning)
								{
									powerDefend.isRunning = false;
									powerDefend.health = 0;
									powerDefend.recharge = powerDefend.rechargeTime;
								}
								else
								{
									powerDefend.run(StarWarsMod.mc.thePlayer);
									coolFlag = false;
								}
								break;
							case "deflect":
								PowerDeflect powerDeflect = (PowerDeflect)powerBase;
								if (!powerDeflect.isRunning)
								{
									powerDeflect.isRunning = true;
									powerDeflect.recharge = 0;
									coolFlag = false;
								}
								break;
							case "lightning":
								//if (!powerBase.isRunning)
								//{
								//	powerBase.isRunning = true;
								//	powerBase.recharge = 0;
								//	coolFlag = false;
								//}
								break;
							case "grab":
								if (powerBase.isRunning)
								{
									powerBase.isRunning = false;
									powerBase.recharge = powerBase.rechargeTime;
								}
								else
								{
									powerBase.run(StarWarsMod.mc.thePlayer);
									coolFlag = false;
								}
								break;
							default:
								powerBase.run(StarWarsMod.mc.thePlayer);
								powerBase.recharge = powerBase.rechargeTime;
								break;
						}

						if (coolFlag)
							coolPower(powerBase);

						StarWarsMod.network.sendToServer(new MessageHolocronSetActive(StarWarsMod.mc.thePlayer, powerBase.serialize()));

						StarWarsMod.network.sendToServer(new MessageRobesIntNBT(StarWarsMod.mc.thePlayer, Resources.nbtXp, Cron.getXP(StarWarsMod.mc.thePlayer) - powerBase.getCost()));
					}
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.NORMAL,
	                receiveCanceled = true)
	public void onEvent(TickEvent.PlayerTickEvent event)
	{
		if (!Resources.ConfigOptions.enableLightsaberLight)
			return;
		if (event.phase == TickEvent.Phase.START && !event.player.worldObj.isRemote)
		{
			if (event.player.getCurrentEquippedItem() != null)
			{
				if (ItemLightsaber.isOn(event.player.getCurrentEquippedItem()))
				{
					int blockX = MathHelper.floor_double(event.player.posX);
					int blockY = MathHelper.floor_double(event.player.posY - 0.2D - event.player.getYOffset());
					int blockZ = MathHelper.floor_double(event.player.posZ);

					if (event.player.worldObj.getBlock(blockX, blockY, blockZ) == Blocks.air)
						event.player.worldObj.setBlock(blockX, blockY, blockZ, StarWarsMod.blockMovingLightSource);
					else if (event.player.worldObj.getBlock(blockX + (int)event.player.getLookVec().xCoord, blockY + (int)event.player.getLookVec().yCoord, blockZ + (int)event.player.getLookVec().zCoord) == Blocks.air)
						event.player.worldObj.setBlock(blockX, blockY, blockZ, StarWarsMod.blockMovingLightSource);
				}
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onTick(TickEvent.ClientTickEvent event)
	{
		GuiManager.tick();

		AnimationManager.tick();

		ClientEventHandler.soundManager.tick();

		if (StarWarsMod.shipSpecialWeaponCooldown > 0)
		{
			StarWarsMod.shipSpecialWeaponCooldown--;
			if (StarWarsMod.shipSpecialWeaponCooldown == 1 && StarWarsMod.mc.thePlayer != null)
				if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicXWing)
					GuiToast.makeText("Proton torpedoes reloaded!", GuiToast.TIME_SHORT).show();
				else if (StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicTIEBomber || StarWarsMod.mc.thePlayer.ridingEntity instanceof VehicYWing)
					GuiToast.makeText("Bombs reloaded!", GuiToast.TIME_SHORT).show();
		}

		if (StarWarsMod.mc.theWorld == null || StarWarsMod.mc.thePlayer == null)
			return;

		updateForcePowers();
	}

	/**
	 * Updates the force powers employed by the (client) ship
	 */
	@SideOnly(Side.CLIENT)
	private void updateForcePowers()
	{
		PowerBase power = Cron.getActive(StarWarsMod.mc.thePlayer);

		if (power != null)
		{
			shouldPowerSync = false;

			switch (power.name)
			{
				case "deflect":
					updateDeflect((PowerDeflect)power);
					break;
				case "grab":
					updateGrab((PowerGrab)power);
					break;
				case "lightning":
					//updateLightning((PowerLightning)power);
					break;
				case "defend":
					updateDefend((PowerDefend)power);
			}

			if (power.isDurationBased && power.isRunning)
			{
				power.duration++;
				if (power.duration >= power.getDuration() || KeybindRegistry.keyRobePower.getIsKeyPressed())
				{
					power.duration = 0;
					power.isRunning = false;
					power.recharge = power.rechargeTime;
					if (power instanceof ICanHaveEntityTarget)
						((ICanHaveEntityTarget)power).setEntityTargetId(-1);
					coolPower(power);
				}

				shouldPowerSync = true;
			}

			if (shouldPowerSync)
				StarWarsMod.network.sendToServer(new MessageHolocronSetActive(StarWarsMod.mc.thePlayer, power.serialize()));
		}

		if (Cron.getHolocron(StarWarsMod.mc.thePlayer) != null)
		{
			NBTTagCompound powers = Cron.getPowers(StarWarsMod.mc.thePlayer);

			tickCoolingPowers(powers);
			addPlayerForceXp();
		}
	}

	@SideOnly(Side.CLIENT)
	private void updateDefend(PowerDefend power)
	{
		if (!power.isRunning)
			return;

		if (power.health <= 0)
		{
			power.isRunning = false;
			power.health = 0;
			power.recharge = power.rechargeTime;
			shouldPowerSync = true;
			coolPower(power);
		}
	}

	/**
	 * Updates lightning
	 *
	 * @param power The lightning power NBT Compound
	 */
	@SideOnly(Side.CLIENT)
	private void updateLightning(PowerLightning power)
	{
		if (!power.isRunning)
			return;
		Entity e = EntityUtils.rayTrace(power.getRange(), StarWarsMod.mc.thePlayer, new Entity[0]);

		int oldId = power.getEntityTargetId();
		if (e != null)
		{
			power.setEntityTargetId(e.getEntityId());

			if (power.isRunning)
				StarWarsMod.network.sendToServer(new MessageEntityHurt(e, power.getDamage()));
		}
		else
			power.setEntityTargetId(-1);
		shouldPowerSync = oldId != power.getEntityTargetId();
		//Lumberjack.log(power.getDurationForLevel(power.currentLevel));
	}

	private void coolPower(PowerBase power)
	{
		if (!Cron.isCooling(power.name))
			Cron.coolingPowers.add(power);
	}

	/**
	 * Updates entities caught in the deflect field
	 *
	 * @param power1 The deflect power NBT Compound
	 */
	@SideOnly(Side.CLIENT)
	private void updateDeflect(PowerDeflect power1)
	{
		if (power1.isRunning)
			StarWarsMod.mc.theWorld.getEntitiesWithinAABB(Entity.class, StarWarsMod.mc.thePlayer.boundingBox.expand(3, 3, 3)).stream().filter(entityObj -> entityObj instanceof EntityArrow || entityObj instanceof EntityBlasterBoltBase).forEach(entityObj ->
			{
				Entity entity = (Entity)entityObj;
				StarWarsMod.network.sendToServer(new MessageEntityReverse(entity));
			});
	}

	/**
	 * Updates grabbed entities
	 *
	 * @param power1 The grab power NBT Compound
	 */
	@SideOnly(Side.CLIENT)
	private void updateGrab(PowerGrab power1)
	{
		if (power1.isRunning)
		{
			Entity e = StarWarsMod.mc.thePlayer.worldObj.getEntityByID(power1.getEntityTargetId());

			if (e == null)
			{
				power1.isRunning = false;
				power1.recharge = power1.rechargeTime;
				shouldPowerSync = true;
				return;
			}

			if (Cron.distanceToEntity == -1)
				Cron.distanceToEntity = (float)Vec3.createVectorHelper(StarWarsMod.mc.thePlayer.posX, StarWarsMod.mc.thePlayer.posY, StarWarsMod.mc.thePlayer.posZ).distanceTo(Vec3.createVectorHelper(e.posX, e.posY, e.posZ));

			Vec3 look = StarWarsMod.mc.thePlayer.getLookVec();
			look.xCoord *= Cron.distanceToEntity;
			look.yCoord *= Cron.distanceToEntity;
			look.zCoord *= Cron.distanceToEntity;
			look.xCoord += StarWarsMod.mc.thePlayer.posX;
			look.yCoord += StarWarsMod.mc.thePlayer.posY;
			look.zCoord += StarWarsMod.mc.thePlayer.posZ;
			e.fallDistance = 0.0f;
			e.onGround = false;
			e.isAirBorne = true;
			e.timeUntilPortal = 5;
			e.setVelocity(0, 0, 0);
			e.setLocationAndAngles(look.xCoord, look.yCoord, look.zCoord, StarWarsMod.mc.thePlayer.rotationYawHead, StarWarsMod.mc.thePlayer.rotationPitch);
			StarWarsMod.network.sendToServer(new MessageEntityGrab(e, StarWarsMod.mc.thePlayer, Cron.distanceToEntity));
		}
	}

	/**
	 * Updates the ship's Force XP and adds more as required
	 */
	@SideOnly(Side.CLIENT)
	private void addPlayerForceXp()
	{
		if (Cron.getHolocron(StarWarsMod.mc.thePlayer) != null)
		{
			ItemStack robes = Cron.getHolocron(StarWarsMod.mc.thePlayer);
			int level = Cron.getLevel(robes);
			int xp = Cron.getXP(robes);
			int maxxp = Cron.getMaxXP(robes);

			if (System.currentTimeMillis() >= lastTimeXpGive + 1000)
			{
				lastTimeXpGive = System.currentTimeMillis();

				double percent = 1 + 0.1f * Math.floor(level / 10);

				if (percent > 6)
					percent = 6;

				int addition = (int)(maxxp / 100 * percent);

				int total = Math.min(xp + addition, maxxp);

				StarWarsMod.network.sendToServer(new MessageRobesIntNBT(StarWarsMod.mc.thePlayer, Resources.nbtXp, total));
			}
		}
	}

	/**
	 * Ticks down all of the cooling powers
	 *
	 * @param powers The NBT serialized tag of powers to update as needed
	 */
	@SideOnly(Side.CLIENT)
	private void tickCoolingPowers(NBTTagCompound powers)
	{
		ArrayList<PowerBase> q = new ArrayList<>();
		for (PowerBase b : Cron.coolingPowers)
		{
			b.recharge--;
			if (b.recharge <= 0)
			{
				b.recharge = 0;
				q.add(b);

				b.duration = 0;
				b.isRunning = false;

				PowerBase active = Cron.getActive(StarWarsMod.mc.thePlayer);
				if (active != null && active.name.equals(b.name))
					StarWarsMod.network.sendToServer(new MessageHolocronSetActive(StarWarsMod.mc.thePlayer, b.serialize()));
			}

			powers.setTag(b.name, b.serialize());
		}

		Cron.coolingPowers.removeAll(q);
	}

	@SubscribeEvent
	public void onTick(TickEvent.ServerTickEvent event)
	{
		updateServerEffectMovement();

		WorldServer server = MinecraftServer.getServer().worldServers[0];

		if (server != null && server.provider.dimensionId == Resources.ConfigOptions.dimIlumId && !server.getWorldInfo().isRaining())
			server.getWorldInfo().setRaining(true);

	}

	/**
	 * Updates entities who have been slowed or disabled
	 */
	private void updateServerEffectMovement()
	{
		Iterator<EntityCooldownEntry> i = Cron.entitiesWithEffects.iterator();
		while (i.hasNext())
		{
			EntityCooldownEntry entry = i.next();

			if (entry.effect.equals("disable"))
			{
				entry.entity.motionX = 0;
				entry.entity.motionY = 0;
				entry.entity.motionZ = 0;
			}
			else if (entry.effect.equals("slow"))
			{
				entry.entity.motionX = Math.min(Math.max(entry.entity.motionX, -0.005d), 0.005d);
				entry.entity.motionY = Math.min(Math.max(entry.entity.motionY, -0.005d), 0.005d);
				entry.entity.motionZ = Math.min(Math.max(entry.entity.motionZ, -0.005d), 0.005d);
			}

			entry.cooldownLeft--;

			if (entry.cooldownLeft <= 0)
				i.remove();
		}
	}

	/**
	 * Resets the active robe power
	 */
	private void resetRobes(PlayerEvent event)
	{
		if (event.player.worldObj.isRemote)
			StarWarsMod.network.sendToServer(new MessageHolocronSetActive(event.player, new NBTTagCompound()));
	}
}