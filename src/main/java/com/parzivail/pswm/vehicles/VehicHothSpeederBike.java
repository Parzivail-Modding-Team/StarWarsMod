package com.parzivail.pswm.vehicles;

import com.parzivail.pswm.quest.QuestUtils;
import com.parzivail.util.vehicle.VehicleLandBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class VehicHothSpeederBike extends VehicleLandBase
{
	public VehicHothSpeederBike(World par1World)
	{
		super(par1World);
		this.setSize(1.0F, 2.0F);
		this.vehicYOffset = -0.4F;
		this.moveModifier = 4.5F;
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
		this.setHealth((float)this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue());
	}

	@Override
	public boolean interact(EntityPlayer p_70085_1_)
	{
		return QuestUtils.canRideInShip(p_70085_1_, this.getClass()) && super.interact(p_70085_1_);
	}

	@Override
	public String getCommandSenderName()
	{
		return "74-Z Speeder Bike";
	}

	@Override
	public String getMovingSound()
	{
		return "vehicle.speeder.move";
	}
}
