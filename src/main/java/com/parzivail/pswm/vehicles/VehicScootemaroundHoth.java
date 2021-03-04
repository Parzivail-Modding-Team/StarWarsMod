package com.parzivail.pswm.vehicles;

import com.parzivail.util.vehicle.VehicleLandBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class VehicScootemaroundHoth extends VehicleLandBase
{
	private static final int[] ridersDatawatcherIds = { 23, 24, 25, 26 };

	public VehicScootemaroundHoth(World par1World)
	{
		super(par1World);
		this.setSize(1.5F, 1.0F);
		this.vehicYOffset = 0.3F;
		this.moveModifier = 1.25F;
		this.tiltMax = 0;
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
		this.setHealth((float)this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue());
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.255D);
	}

	@Override
	protected void entityInit()
	{
		for (int ridersDatawatcherId : ridersDatawatcherIds)
		{
			this.dataWatcher.addObject(ridersDatawatcherId, "");
			this.dataWatcher.setObjectWatched(ridersDatawatcherId);
		}
		super.entityInit();
	}

	private String getEntityIdAtIndex(int i)
	{
		if (i < 0 || i >= ridersDatawatcherIds.length)
			return "";
		return this.dataWatcher.getWatchableObjectString(ridersDatawatcherIds[i]);
	}

	private EntityPlayer getRiderAtIndex(int i)
	{
		if (i < 0 || i >= ridersDatawatcherIds.length)
			return null;

		if (!getEntityIdAtIndex(i).isEmpty())
		{

			return this.worldObj.getPlayerEntityByName(getEntityIdAtIndex(i));
		}
		return null;
	}

	private void setRiderAtIndex(int i, EntityPlayer e)
	{
		if (i < 0 || i >= ridersDatawatcherIds.length)
			return;
		this.dataWatcher.updateObject(ridersDatawatcherIds[i], e.getCommandSenderName());
		this.dataWatcher.setObjectWatched(ridersDatawatcherIds[i]);
	}

	private void setRiderAtIndex(int i, String name)
	{
		if (i < 0 || i >= ridersDatawatcherIds.length)
			return;
		this.dataWatcher.updateObject(ridersDatawatcherIds[i], name);
		this.dataWatcher.setObjectWatched(ridersDatawatcherIds[i]);
	}

	private boolean addRider(EntityPlayer e)
	{
		for (int i = 0; i < ridersDatawatcherIds.length; i++)
			if (getRiderAtIndex(i) == null)
			{
				setRiderAtIndex(i, e);
				return true;
			}
		return false;
	}

	private boolean removeRider(EntityPlayer e)
	{
		for (int i = 0; i < ridersDatawatcherIds.length; i++)
			if (getRiderAtIndex(i).getCommandSenderName().equals(e.getCommandSenderName()))
			{
				setRiderAtIndex(i, "");
				return true;
			}
		return false;
	}

	@Override
	public String getCommandSenderName()
	{
		return "Rebel Scoot-'Em-Around";
	}

	@Override
	public String getMovingSound()
	{
		return "vehicle.scootemaround.move";
	}

	@Override
	public boolean interact(EntityPlayer p_70085_1_)
	{
		if (!this.worldObj.isRemote)
		{
			if (this.riddenByEntity == null)
			{
				p_70085_1_.mountEntity(this);
				return true;
			}
			//else
			//{
			//	return this.addRider(p_70085_1_);
			//}
		}
		return false;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (!worldObj.isRemote)
		{
			for (int i = 0; i < ridersDatawatcherIds.length; i++)
				if (getRiderAtIndex(i) != null)
				{
					EntityPlayer rider = getRiderAtIndex(i);
					if (rider instanceof EntityPlayerMP)
					{
						EntityPlayerMP entityPlayerMP = (EntityPlayerMP)rider;

						if (rider.isSneaking())
							removeRider(rider);
						else
						{
							setPlayerPosition(entityPlayerMP);
						}
					}
				}
		}
	}

	private void setPlayerPosition(EntityPlayerMP entityPlayerMP)
	{
		entityPlayerMP.playerNetServerHandler.setPlayerLocation(posX, posY, posZ, entityPlayerMP.getRotationYawHead(), entityPlayerMP.rotationPitch);
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		for (int i = 0; i < ridersDatawatcherIds.length; i++)
		{
			compound.setString("rider" + i, getEntityIdAtIndex(i));
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		for (int i = 0; i < ridersDatawatcherIds.length; i++)
		{
			setRiderAtIndex(i, compound.getString("rider" + i));
		}
	}

	@Override
	public void updateRiderPosition()
	{
		if (this.riddenByEntity != null)
		{
			float offset = this.vehicYOffset;
			if (!(this.riddenByEntity instanceof EntityPlayer))
				offset -= 0.5F;

			float ox = MathHelper.cos((float)Math.toRadians(this.rotationYaw + 90));
			float oz = MathHelper.sin((float)Math.toRadians(this.rotationYaw + 90));
			this.riddenByEntity.setPosition(this.posX + ox, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset() + offset - 0.1f, this.posZ + oz);
		}
	}
}
