package com.parzivail.pswm.world;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.items.ItemQuestLog;
import com.parzivail.util.vehicle.VehicleAirBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TransferDim extends Teleporter
{
	private WorldServer worldserver;

	public TransferDim(WorldServer worldserver)
	{
		super(worldserver);
		this.worldserver = worldserver;
	}

	@Override
	public boolean placeInExistingPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
	{
		return false;
	}

	@Override
	public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
	{
	}

	@Override
	public void removeStalePortalLocations(long par1)
	{
	}

	public void teleport(Entity entity)
	{
		if (entity == null)
			return;

		if (entity.ridingEntity != null)
		{
			teleportInternal(entity.ridingEntity);
			entity.mountEntity(null);
		}
		teleportInternal(entity);
	}

	public void putInRightPlace(EntityLivingBase entity)
	{
		if (entity instanceof VehicleAirBase)
		{
			VehicleAirBase vehicleAirBase = (VehicleAirBase)entity;
			vehicleAirBase.setRealYaw(0);
			vehicleAirBase.setRealPitch(0);
		}

		if (worldserver.provider.dimensionId == Resources.ConfigOptions.dimTatooineId)
		{
			entity.setPosition(0.5f, worldserver.getHeightValue(0, 0), 0.5f);
			if (entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)entity;
				if (ItemQuestLog.getSide(player).equals(Resources.allegianceImperialFmt))
				{
					entity.setPosition(-219.5f, 66, 246.5f);
				}
			}
		}
		else if (worldserver.provider.dimensionId == Resources.ConfigOptions.dimSpaceId)
		{
			entity.setPosition(9.5f, 157, 45.5f);

			if (entity instanceof VehicleAirBase)
			{
				VehicleAirBase vehicleAirBase = (VehicleAirBase)entity;
				vehicleAirBase.setRealYaw(90);
			}
		}
		else if (worldserver.provider.dimensionId == Resources.ConfigOptions.dimYavin4Id)
		{
			entity.setPosition(270.5f, 54, 248.5f);
		}
		else if (worldserver.provider.dimensionId == Resources.ConfigOptions.dimIlumId)
		{
			entity.setPosition(13.5f, 208, 162.5f);
		}
		else if (worldserver.provider.dimensionId == Resources.ConfigOptions.dimDagobahId)
		{
			entity.setPosition(55.5f, 68, 19.5f);
		}
		else if (worldserver.provider.dimensionId == Resources.ConfigOptions.dimEndorId)
		{
			if (entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)entity;
				if (ItemQuestLog.getSide(player).equals(Resources.allegianceImperialFmt))
				{
					entity.setPosition(508.5f, 63, 80.5f);
				}
				else if (ItemQuestLog.getSide(player).equals(Resources.allegianceRebelFmt))
				{
					entity.setPosition(-346.5f, 62, 41.5f);
				}
			}
		}
		else if (worldserver.provider.dimensionId == Resources.ConfigOptions.dimHothId)
		{
			if (entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)entity;
				if (ItemQuestLog.getSide(player).equals(Resources.allegianceImperialFmt))
				{
					entity.setPosition(-310.5f, 63, 6.5f);
				}
				else if (ItemQuestLog.getSide(player).equals(Resources.allegianceRebelFmt))
				{
					entity.setPosition(403.5f, 66, 77.5f);
				}
			}
		}
	}

	private void teleportInternal(Entity entity)
	{
		double dx = this.worldserver.getSpawnPoint().posX;
		double dz = this.worldserver.getSpawnPoint().posZ;
		double dy = 250.0D;
		while (this.worldserver.getBlock((int)dx, (int)dy - 1, (int)dz).equals(Blocks.air) && dy > 0.0D)
			dy -= 1.0D;
		if (dy == 0.0D)
			dy = 128.0D;
		dx += 0.5D;
		dy += 1.0D;
		dz += 0.5D;
		entity.setPosition(dx, dy, dz);
		if (entity instanceof EntityLivingBase)
			putInRightPlace((EntityLivingBase)entity);
		entity.motionX = entity.motionY = entity.motionZ = 0.0D;
		entity.setPosition(dx, dy, dz);
		if (entity instanceof EntityLivingBase)
			putInRightPlace((EntityLivingBase)entity);
		entity.setPosition(dx, dy, dz);
		if (entity instanceof EntityLivingBase)
			putInRightPlace((EntityLivingBase)entity);

		if (entity instanceof EntityPlayerMP && entity.worldObj.provider.dimensionId != this.worldserver.provider.dimensionId)
			MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)entity, this.worldserver.provider.dimensionId, this);
		else if (entity.worldObj.provider.dimensionId != this.worldserver.provider.dimensionId)
			transferEntityToDimension(MinecraftServer.getServer().getConfigurationManager(), entity, this.worldserver.provider.dimensionId, this);
	}

	private void transferEntityToDimension(ServerConfigurationManager manager, Entity entity, int newDimension, Teleporter teleporter)
	{
		int j = entity.dimension;
		WorldServer worldserver = manager.getServerInstance().worldServerForDimension(entity.dimension);
		entity.dimension = newDimension;
		WorldServer worldserver1 = manager.getServerInstance().worldServerForDimension(entity.dimension);
		worldserver.removeEntity(entity);
		entity.isDead = false;
		manager.transferEntityToWorld(entity, j, worldserver, worldserver1, teleporter);
	}
}