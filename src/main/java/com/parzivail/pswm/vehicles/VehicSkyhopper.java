package com.parzivail.pswm.vehicles;

import com.parzivail.pswm.Resources;
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
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50);
		this.setHealth((float)this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue());
	}

	@Override
	public void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(WING_DW, 0.8f);
		this.dataWatcher.setObjectWatched(WING_DW);
	}

	@Override
	public String getCommandSenderName()
	{
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
	public boolean interact(EntityPlayer player)
	{
		if (player.isSneaking())
		{
			if (!worldObj.isRemote)
				player.openGui(StarWarsMod.instance, Resources.GUI_HYPERDRIVE, this.worldObj, 0, 0, 0);
			return true;
		}

		return super.interact(player);
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
