package com.parzivail.pswm.mobs;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.ai.AiFollowEntity;
import com.parzivail.pswm.ai.AiFreqMove;
import com.parzivail.pswm.items.ItemQuestLog;
import com.parzivail.pswm.network.MessageSetQuestLogNbt;
import com.parzivail.pswm.quest.QuestStats;
import com.parzivail.util.IParziNPC;
import com.parzivail.util.entity.trade.WeightedTradeItem;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

import java.util.Arrays;

public class MobTatooineCommoner extends EntityCreature implements IParziNPC
{
	public AiFollowEntity aiFollowEntity;
	private EntityPlayer buyingPlayer;
	private MerchantRecipeList buyingList;
	private String[] types = { "weaponsDealer", "generalMerchant", "corellian", "bartender", "shipDealer" };
	private float baseRarity = 1.0F;
	private DataWatcher dw;

	public MobTatooineCommoner(World p_i1748_1_)
	{
		super(p_i1748_1_);
		setSize(0.5F, 1.5F);
		getNavigator().setCanSwim(true);
		dw = super.getDataWatcher();
		this.tasks.addTask(0, aiFollowEntity = new AiFollowEntity(this, null, 0.5f));
		getNavigator().setEnterDoors(true);
		this.tasks.addTask(1, new AiFreqMove(this, 0.3f, 20));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.25D, false));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		getDataWatcher().addObject(25, rand.nextInt(5));
		getDataWatcher().addObject(26, rand.nextInt(10)); // fraud
		getDataWatcher().addObject(27, 0); // arrested
	}

	@Override
	public String getCommandSenderName()
	{
		return "Tatooine Commoner";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound()
	{
		return Resources.MODID + ":" + "mob.commoner.die";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound()
	{
		return Resources.MODID + ":" + "mob.commoner.hit";
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound()
	{
		return Resources.MODID + ":" + "mob.commoner.say";
	}

	@Override
	public int getTalkInterval()
	{
		return 400;
	}

	@Override
	public boolean interact(EntityPlayer entityPlayer)
	{
		if (entityPlayer.isSneaking() && this.getFraud())
		{
			this.aiFollowEntity.targetEntity = this.aiFollowEntity.targetEntity == null ? entityPlayer : null;
			setArrested(true);
			if (worldObj.isRemote)
			{
				ItemQuestLog.addStat(entityPlayer, QuestStats.ARRESTED_NPCS);
				StarWarsMod.network.sendToServer(new MessageSetQuestLogNbt(entityPlayer, ItemQuestLog.getQuestContainer(entityPlayer).stackTagCompound));
			}
		}
		return super.interact(entityPlayer);
	}

	private int getType()
	{
		return getDataWatcher().getWatchableObjectInt(25);
	}

	private void setType(int t)
	{
		getDataWatcher().updateObject(25, t);
	}

	public boolean getFraud()
	{
		return getDataWatcher().getWatchableObjectInt(26) == 1;
	}

	public void setFraud(boolean t)
	{
		getDataWatcher().updateObject(26, t ? 1 : 0);
	}

	public boolean getArrested()
	{
		return getDataWatcher().getWatchableObjectInt(27) == 1;
	}

	public void setArrested(boolean t)
	{
		getDataWatcher().updateObject(27, t ? 1 : 0);
	}

	private int indexOf(String[] haystack, String needle)
	{
		return Arrays.asList(haystack).indexOf(needle);
	}

	public boolean isAlreadyTrading(WeightedTradeItem item, MerchantRecipeList trades)
	{
		for (Object x : trades.toArray())
			if (((MerchantRecipe)x).getItemToSell() == item.item)
				return true;
		return false;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompund)
	{
		super.readEntityFromNBT(tagCompund);
		setType(tagCompund.getInteger("type"));
		setArrested(tagCompund.getBoolean("arrested"));
		setFraud(tagCompund.getBoolean("fraud"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		super.writeEntityToNBT(tagCompound);
		tagCompound.setInteger("type", getType());
		tagCompound.setBoolean("arrested", getArrested());
		tagCompound.setBoolean("fraud", getFraud());
	}

	@Override
	public String getName()
	{
		return this.getCommandSenderName();
	}

	@Override
	public String getSpecies()
	{
		return Resources.speciesHuman;
	}

	@Override
	public String getAllegiance()
	{
		return Resources.allegianceNone;
	}

	@Override
	public String getJob()
	{
		return this.getCommandSenderName();
	}
}
