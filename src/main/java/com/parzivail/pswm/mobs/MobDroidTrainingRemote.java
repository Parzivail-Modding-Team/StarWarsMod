package com.parzivail.pswm.mobs;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.ai.AiFreqMove;
import com.parzivail.pswm.ai.AiMouseScare;
import com.parzivail.util.entity.EntityUtils;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class MobDroidTrainingRemote extends EntityDroidBase
{
	public MobDroidTrainingRemote(World par1World)
	{
		super(par1World);
		setSize(0.5F, 2);
		tasks.addTask(1, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
		tasks.addTask(3, new AiMouseScare(this, MobWookiee.class, 16.0F, 0.8D, 1.33D));
		tasks.addTask(4, new AiFreqMove(this, 1, 0));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.255D);
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_)
	{
		return null;
	}

	@Override
	public void dropFewItems(boolean par1, int par2)
	{
		dropItem(StarWarsItems.spawnTrainingRemote, 1);
	}

	@Override
	protected void dropRareDrop(int par1)
	{
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(18, (byte)0);
	}

	@Override
	protected void fall(float par1)
	{
	}

	@Override
	public boolean getCanSpawnHere()
	{
		return true;
	}

	@Override
	protected String getDeathSound()
	{
		return Resources.MODID + ":" + "mob.mouse.die";
	}

	@Override
	public List<String> getDebugText(List<String> list, EntityPlayer player, World world, int x, int y, int z)
	{
		return EntityUtils.getRebelDroidDebugText(this, list, player, world, x, y, z);
	}

	@Override
	protected String getHurtSound()
	{
		return Resources.MODID + ":" + "mob.mouse.hit";
	}

	@Override
	protected String getLivingSound()
	{
		return Resources.MODID + ":" + "mob.mouse.say";
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void updateAITick()
	{
		if (getMoveHelper().isUpdating())
		{
			double d0 = getMoveHelper().getSpeed();
			if (d0 == 0.6D)
			{
				setSneaking(true);
				setSprinting(false);
			}
			else if (d0 == 1.33D)
			{
				setSneaking(false);
				setSprinting(true);
			}
			else
			{
				setSneaking(false);
				setSprinting(false);
			}
		}
		else
		{
			setSneaking(false);
			setSprinting(false);
		}
	}
}
