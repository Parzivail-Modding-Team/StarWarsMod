package com.parzivail.pswm.mobs.trooper;

import com.parzivail.pswm.mobs.MobDroidAstromech;
import com.parzivail.pswm.mobs.MobDroidProtocol;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.UUID;

import static com.parzivail.pswm.Resources.MODID;
import static com.parzivail.pswm.StarWarsItems.*;
import static java.util.UUID.fromString;

public class MobAtstPilot extends MobTrooper
{
	private static final UUID field_110189_bq = fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");

	public MobAtstPilot(World par1World)
	{
		super(par1World);
		setCurrentItemOrArmor(4, new ItemStack(atstPilotHelmet, 1));
		setCurrentItemOrArmor(3, new ItemStack(atstPilotChest, 1));
		setCurrentItemOrArmor(2, new ItemStack(atstPilotLegs, 1));
		setCurrentItemOrArmor(1, new ItemStack(atstPilotBoots, 1));
	}

	@Override
	protected String getDeathSound()
	{
		return MODID + ":" + "mob.stormtrooper.die";
	}

	@Override
	protected String getHurtSound()
	{
		return MODID + ":" + "mob.stormtrooper.hit";
	}

	@Override
	protected String getLivingSound()
	{
		EntityTameable e = (EntityTameable)worldObj.findNearestEntityWithinAABB(EntityTameable.class, boundingBox.expand(10.0D, 10.0D, 10.0D), this);
		if (e instanceof MobDroidAstromech || e instanceof MobDroidProtocol)
			return MODID + ":" + "mob.sandtrooper.droid";
		return MODID + ":" + "mob.stormtrooper.say";
	}
}