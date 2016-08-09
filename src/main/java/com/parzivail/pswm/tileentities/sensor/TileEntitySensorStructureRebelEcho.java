package com.parzivail.pswm.tileentities.sensor;

import com.parzivail.pswm.mobs.trooper.*;
import net.minecraft.entity.EntityLiving;

public class TileEntitySensorStructureRebelEcho extends TileEntitySensorPeoplePlace
{
	public TileEntitySensorStructureRebelEcho()
	{
		this.rX = 30;
		this.rY = 30;
		this.rZ = 30;
		this.entityMax = 5;
	}

	public MobTrooper getNewEntity()
	{
		switch (this.worldObj.rand.nextInt(5))
		{
			case 0:
				return new MobRebelPilot(this.worldObj);
			case 1:
				return new MobRebelPilotA(this.worldObj);
			case 2:
				return new MobRebelPilotY(this.worldObj);
			case 3:
				return new MobRebelTechnician(this.worldObj);
			case 4:
				return new MobRebelWorker(this.worldObj);
			default:
				return null;
		}
	}

	@Override
	public Class<? extends EntityLiving> getEntityNeedleClass()
	{
		return MobTrooper.class;
	}
}
