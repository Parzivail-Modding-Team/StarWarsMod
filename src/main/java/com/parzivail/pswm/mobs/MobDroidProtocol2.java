package com.parzivail.pswm.mobs;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.ai.AiFreqMove;
import com.parzivail.util.entity.EntityUtils;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.List;

public class MobDroidProtocol2 extends EntityDroidBase
{
	private EntityAITempt aiTempt;

	public MobDroidProtocol2(World par1World)
	{
		super(par1World);
		setSize(0.75F, 2.0F);
		getNavigator().setEnterDoors(true);
		tasks.addTask(0, aiSit);
		tasks.addTask(1, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
		tasks.addTask(2, aiTempt = new EntityAITempt(this, 0.6D, StarWarsItems.droidCaller, true));
		tasks.addTask(3, new AiFreqMove(this, 1, 0));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(5.0D);
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
		dropItem(StarWarsItems.spawnProtocol2, 1);
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
	public boolean getCanSpawnHere()
	{
		return true;
	}

	@Override
	protected String getDeathSound()
	{
		return Resources.MODID + ":" + "mob.protocol.die";
	}

	@Override
	public List<String> getDebugText(List<String> list, EntityPlayer player, World world, int x, int y, int z)
	{
		return EntityUtils.getRebelDroidDebugText(this, list, player, world, x, y, z);
	}

	@Override
	protected String getHurtSound()
	{
		return Resources.MODID + ":" + "mob.protocol.hit";
	}

	@Override
	protected String getLivingSound()
	{
		return Resources.MODID + ":" + "mob.protocol.say";
	}

	@Override
	protected float getSoundPitch()
	{
		return 1.0F;
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
		if (itemstack == null)
			itemstack = new ItemStack(Blocks.air);
		if (isTamed())
		{
			if (par1EntityPlayer.getUniqueID().equals(getOwner().getUniqueID()) && !worldObj.isRemote && !isBreedingItem(itemstack) && itemstack.getItem() == StarWarsItems.droidCaller)
			{
				aiSit.setSitting(!isSitting());
				par1EntityPlayer.addChatMessage(new ChatComponentText(EntityUtils.getDroidSittingMessage(!isSitting())));
				isJumping = false;
			}
		}
		else if (itemstack.getItem() == StarWarsItems.droidCaller && par1EntityPlayer.getDistanceSqToEntity(this) < 9.0D)
		{
			if (!worldObj.isRemote)
				if (rand.nextInt(3) == 0)
				{
					setTamed(true);
					func_152115_b(par1EntityPlayer.getUniqueID().toString());
					playTameEffect(true);
					aiSit.setSitting(true);
					worldObj.setEntityState(this, (byte)7);
					par1EntityPlayer.addChatMessage(new ChatComponentText(EntityUtils.getDroidSittingMessage(!isSitting())));
				}
				else
				{
					playTameEffect(false);
					worldObj.setEntityState(this, (byte)6);
				}
			return true;
		}
		return super.interact(par1EntityPlayer);
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (rand.nextInt(1000) == 0 && worldObj.findNearestEntityWithinAABB(MobDroidAstromech.class, boundingBox.expand(5.0D, 5.0D, 5.0D), this) instanceof MobDroidAstromech)
			playSound(Resources.MODID + ":" + "mob.protocol.r2d2rare", 1.0F, 1.0F);
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
