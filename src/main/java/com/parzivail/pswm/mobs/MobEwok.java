package com.parzivail.pswm.mobs;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.ai.AiFollowType;
import com.parzivail.pswm.ai.AiFreqMove;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MobEwok extends EntityAnimal implements IAnimals
{
	public MobEwok(World par1World)
	{
		super(par1World);
		setSize(0.5F, 1.5F);
		getNavigator().setCanSwim(true);
		tasks.addTask(0, new AiFollowType(this, MobDroidProtocol.class, 0.5f, 10));
		tasks.addTask(1, new AiFreqMove(this, 0.5f, 0));
		setCurrentItemOrArmor(0, new ItemStack(StarWarsItems.ewokSpear, 1));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(5.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.55D);
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_)
	{
		return null;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		getDataWatcher().addObject(25, rand.nextInt(3));
	}

	protected Item dropItem()
	{
		if (rand.nextInt(60) == 36)
		{
			dropItem(StarWarsItems.ewokSpear, 1);
		}
		return Item.getItemFromBlock(Blocks.leaves);
	}

	@Override
	protected String getDeathSound()
	{
		return Resources.MODID + ":" + "mob.ewok.die";
	}

	@Override
	protected String getHurtSound()
	{
		return Resources.MODID + ":" + "mob.ewok.hit";
	}

	@Override
	protected String getLivingSound()
	{
		return Resources.MODID + ":" + "mob.ewok.say";
	}

	private int getType()
	{
		return getDataWatcher().getWatchableObjectInt(25);
	}
}
