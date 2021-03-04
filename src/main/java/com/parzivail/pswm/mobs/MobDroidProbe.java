package com.parzivail.pswm.mobs;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.ai.AiFreqMove;
import com.parzivail.pswm.ai.AiTrooperAttack;
import com.parzivail.pswm.entities.EntityBlasterProbeBolt;
import com.parzivail.pswm.items.ItemQuestLog;
import com.parzivail.pswm.mobs.trooper.MobTrooper;
import com.parzivail.util.entity.EntityUtils;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class MobDroidProbe extends EntityDroidBase implements IShootThings
{
	private AiTrooperAttack aiArrow;

	private EntityAITempt aiTempt;

	public MobDroidProbe(World par1World)
	{
		super(par1World);
		setSize(1.0F, 2.0F);
		tasks.addTask(0, aiArrow = new AiTrooperAttack(this, 1.0D, 20, 60, 15.0F));
		tasks.addTask(1, aiSit);
		tasks.addTask(2, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
		tasks.addTask(3, aiTempt = new EntityAITempt(this, 0.6D, StarWarsItems.droidCaller, true));
		tasks.addTask(4, new AiFreqMove(this, 1, 0));
		targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, MobTrooper.class, 0, true));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.255D);
	}

	@Override
	public void rangeAttack(EntityLivingBase p_82196_1_, float p_82196_2_)
	{
		if (p_82196_1_ instanceof EntityPlayer && ItemQuestLog.getSide((EntityPlayer)p_82196_1_).equals(Resources.allegianceImperialFmt))
			return;
		playSound(Resources.MODID + ":" + "item.blasterRifle.use", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(rand, -0.2D, 0.2D));
		worldObj.spawnEntityInWorld(new EntityBlasterProbeBolt(worldObj, this, p_82196_1_));
	}

	@Override
	public boolean canDespawn()
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
		dropItem(StarWarsItems.spawnProbe, 1);
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
		return Resources.MODID + ":" + "mob.probe.die";
	}

	@Override
	public List<String> getDebugText(List<String> list, EntityPlayer player, World world, int x, int y, int z)
	{
		return EntityUtils.getImperialDroidDebugText(this, list, player, world, x, y, z);
	}

	@Override
	protected String getHurtSound()
	{
		return Resources.MODID + ":" + "mob.probe.hit";
	}

	@Override
	protected String getLivingSound()
	{
		return Resources.MODID + ":" + "mob.probe.say";
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
		if (itemstack == null)
			itemstack = new ItemStack(Blocks.air);
		if (isTamed())
		{
			if (par1EntityPlayer.getUniqueID().equals(getOwner().getUniqueID()) && !worldObj.isRemote && !isBreedingItem(itemstack) && itemstack.getItem() == StarWarsItems.droidHacker)
			{
				aiSit.setSitting(!isSitting());
				par1EntityPlayer.addChatMessage(new ChatComponentText(EntityUtils.getDroidSittingMessage(!isSitting())));
				isJumping = false;
			}
		}
		else if (itemstack.getItem() == StarWarsItems.droidHacker && par1EntityPlayer.getDistanceSqToEntity(this) < 9.0D)
		{
			if (!worldObj.isRemote)
				if (rand.nextInt(3) == 0)
				{
					setTamed(true);
					func_152115_b(par1EntityPlayer.getUniqueID().toString());
					playTameEffect(true);
					aiSit.setSitting(true);
					worldObj.setEntityState(this, (byte)7);
					entityToAttack = null;
					targetTasks.taskEntries.clear();
					targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true));
					tasks.taskEntries.clear();
					tasks.addTask(2, aiSit);
					tasks.addTask(3, aiTempt = new EntityAITempt(this, 0.6D, StarWarsItems.droidHacker, true));
					tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
					tasks.addTask(1, aiArrow = new AiTrooperAttack(this, 1.0D, 20, 60, 15.0F));
					tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
					tasks.addTask(3, new EntityAILookIdle(this));
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
}
