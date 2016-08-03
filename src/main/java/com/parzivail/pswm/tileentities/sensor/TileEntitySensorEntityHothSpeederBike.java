package com.parzivail.pswm.tileentities.sensor;

import com.parzivail.pswm.tileentities.TileEntitySensor;
import com.parzivail.pswm.vehicles.VehicHothSpeederBike;
import com.parzivail.util.vehicle.VehicleBase;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class TileEntitySensorEntityHothSpeederBike extends TileEntitySensor
{
	public Class<? extends Entity> needle;
	private int rX;
	private int rY;
	private int rZ;

	public TileEntitySensorEntityHothSpeederBike()
	{
		this.needle = VehicHothSpeederBike.class;
		this.rX = 2;
		this.rY = 4;
		this.rZ = 2;
	}

	@Override
	public boolean checkCondition()
	{
		return this.worldObj != null && this.worldObj.getEntitiesWithinAABB(needle, this.bb.expand(rX, rY, rZ)).isEmpty();
	}

	@Override
	public void runConditional()
	{
		if (!worldObj.isRemote)
		{
			try
			{
				int l = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);

				Object c = this.needle.getConstructor(World.class).newInstance(this.worldObj);
				Entity entity = this.needle.cast(c);
				entity.setPositionAndRotation(this.xCoord, this.yCoord + 1, this.zCoord, l * 90, 0);
				if (entity instanceof VehicleBase)
				{
					VehicleBase vehicleBase = (VehicleBase)entity;
					vehicleBase.setRealYaw(l * 90);
					vehicleBase.rotationPitchLast = 0;
				}
				this.worldObj.spawnEntityInWorld(entity);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
