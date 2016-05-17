package com.parzivail.pswm.vehicles;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.network.MessageSFoil;
import com.parzivail.util.vehicle.VehicleAirBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class VehicSkyhopper extends VehicleAirBase
{
	public static int WING_DW = 13;
	public boolean isOpening = false;
	public boolean isClosing = false;

	public VehicSkyhopper(World par1World)
	{
		super(par1World);
		this.setSize(3.0F, 3.0F);
		this.vehicYOffset = 0F;
		this.moveModifier = 1.75F;
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
	}

	@Override
	public void dropFewItems(boolean par1, int par2)
	{
		this.dropItem(StarWarsItems.spawnSkyhopper, 1);
	}

	@Override
	public void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(WING_DW, Float.valueOf(0.8f));
		this.dataWatcher.setObjectWatched(WING_DW);
	}

	@Override
	public String getCommandSenderName()
	{
		if (this.hasCustomNameTag())
			return this.getCustomNameTag();
		return "T-16 Skyhopper";
	}

	@Override
	public String getDeathSound()
	{
		return Resources.MODID + ":" + "vehicle.xwing.die";
	}

	@Override
	public String getMovingSound()
	{
		return "vehicle.xwing.move";
	}

	public float getWing()
	{
		return this.dataWatcher.getWatchableObjectFloat(WING_DW);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.isOpening)
		{
			this.setWing(this.getWing() + 1 / 30f);
			this.isOpening = this.getWing() < 0.8f;
		}

		if (this.isClosing)
		{
			this.setWing(this.getWing() - 1 / 30f);
			this.isClosing = this.getWing() > 0;
		}

		if ((this.isOpening || this.isClosing) && this.riddenByEntity instanceof EntityPlayer)
			StarWarsMod.network.sendToServer(new MessageSFoil((EntityPlayer)this.riddenByEntity, this.getWing()));
	}

	public void setWing(float f)
	{
		this.dataWatcher.updateObject(WING_DW, f);
	}
}
