package com.parzivail.pswm.mobs;

import com.parzivail.pswm.Resources;
import com.parzivail.util.IParziNPC;
import com.parzivail.util.entity.trade.WeightedTradeItem;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

import java.util.Arrays;

public class MobBith extends EntityCreature implements IParziNPC
{
	private EntityPlayer buyingPlayer;
	private MerchantRecipeList buyingList;
	private String[] types = { "bith" };
	private String[] officialNames = { "Bith" };
	private float baseRarity = 1.0F;
	private DataWatcher dw;
	private int bithsNear = 0;

	// public boolean playing = false;

	public MobBith(World p_i1748_1_)
	{
		super(p_i1748_1_);
		getNavigator().setCanSwim(true);
		dw = super.getDataWatcher();
	}

	public MerchantRecipeList createTradesByProfession(MerchantRecipeList list, int type)
	{
		MerchantRecipeList fn = new MerchantRecipeList();
		//fn.add(new MerchantRecipe(new ItemStack(StarWarsItems.goldImperialCredit, 10), new ItemStack(StarWarsItems.recordCantina, 1)));
		return fn;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		getDataWatcher().addObject(25, 0);
		getDataWatcher().addObject(26, 0);
	}

	@Override
	public String getCommandSenderName()
	{
		return officialNames[getType()];
	}

	@Override
	public String getCustomNameTag()
	{
		return getCommandSenderName();
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
		return Resources.MODID + ":" + "mob.bith.say";
	}

	public boolean getPlaying()
	{
		return dw.getWatchableObjectInt(26) == 1;
	}

	@Override
	public int getTalkInterval()
	{
		return 400;
	}

	private int getType()
	{
		return getDataWatcher().getWatchableObjectInt(25);
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
	public void onUpdate()
	{
		super.onUpdate();

		/*
		 * bithsNear = 0; boolean shouldPlay = true;
		 *
		 * for (Object oEntity :
		 * this.worldObj.getEntitiesWithinAABB(this.getClass(),
		 * this.boundingBox.expand(8, 8, 8))) if (oEntity instanceof MobBith) {
		 * bithsNear++; shouldPlay = !((MobBith)oEntity).getPlaying() &&
		 * shouldPlay; }
		 *
		 * if (bithsNear >= 5) if (!this.getPlaying() && shouldPlay)
		 *
		 * { this.playSound(StarWarsMod.MODID + ":" + "item.records.Cantina", 1,
		 * 1); this.setPlaying(true); } else if (this.getPlaying() &&
		 * !shouldPlay)
		 *
		 * { Minecraft.getMinecraft().getSoundHandler().stopSound(song);
		 * this.setPlaying(false); }
		 */

	}

	@Override
	public String getName()
	{
		return this.getCommandSenderName();
	}

	@Override
	public String getSpecies()
	{
		return Resources.speciesBith;
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
