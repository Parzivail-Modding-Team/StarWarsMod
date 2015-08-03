package com.parzi.starwarsmod.mobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

import com.parzi.starwarsmod.StarWarsMod;
import com.parzi.starwarsmod.utils.TradeUtils;
import com.parzi.starwarsmod.utils.WeightedTradeItem;

public class MobTatooineCommoner extends EntityVillager
{
	private EntityPlayer buyingPlayer;
	private MerchantRecipeList buyingList;
	private String[] types = { "weaponsDealer", "generalMerchant", "corellian" };
	private String[] officialNames = { "Weapons Dealer", "General Merchant", "Corellian" };
	private float baseRarity = 1F;
	private DataWatcher dw;

	public MobTatooineCommoner(World p_i1748_1_)
	{
		super(p_i1748_1_);
		this.dw = super.getDataWatcher();
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.getDataWatcher().addObject(25, this.rand.nextInt(3));
	}

	@Override
	public void setCustomer(EntityPlayer p_70932_1_)
	{
		this.buyingPlayer = p_70932_1_;
	}

	@Override
	public EntityPlayer getCustomer()
	{
		return this.buyingPlayer;
	}

	public MerchantRecipeList getRecipes(EntityPlayer p_70934_1_)
	{
		if (this.buyingList == null)
		{
			this.buyingList = new MerchantRecipeList();
			this.buyingList = this.createTradesByProfession(this.buyingList, this.getType());
		}

		return this.buyingList;
	}

	@Override
	public void useRecipe(MerchantRecipe p_70933_1_)
	{
	}

	private int getType()
	{
		return this.getDataWatcher().getWatchableObjectInt(25);
	}

	@Override
	public EntityVillager createChild(EntityAgeable p_90011_1_)
	{
		return null;
	}

	public MerchantRecipeList createTradesByProfession(MerchantRecipeList list, int type)
	{
		List<WeightedTradeItem> trades = new ArrayList<WeightedTradeItem>();
		MerchantRecipeList fn = new MerchantRecipeList();

		if (type == indexOf(types, "weaponsDealer"))
		{
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, TradeUtils.fluxPrice(this.rand, 40, 3)), StarWarsMod.blasterRifle.getMeta("A280"), baseRarity / 2));
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, TradeUtils.fluxPrice(this.rand, 30, 3)), StarWarsMod.blasterPistol.getMeta("Dl44"), baseRarity));
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, TradeUtils.fluxPrice(this.rand, 25, 2)), StarWarsMod.blasterPistol.getMeta("Dh17"), baseRarity));
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, TradeUtils.fluxPrice(this.rand, 40, 2)), StarWarsMod.blasterPistol.getMeta("Sporting"), baseRarity));
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, TradeUtils.fluxPrice(this.rand, 40, 3)), new ItemStack(StarWarsMod.gaffiStick, 1), baseRarity / 2));

			for (int i = 0; i < this.rand.nextInt(3) + 3; i++)
			{
				WeightedTradeItem item;
				do
				{
					item = TradeUtils.getWeightedItemFromList(trades, this.rand);
				}
				while (this.isAlreadyTrading(item, fn));
				fn.add(new MerchantRecipe(item.cost, item.cost2, item.item));
			}
		}
		else if (type == indexOf(types, "generalMerchant"))
		{
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, 64), new ItemStack(StarWarsMod.spawnLandspeeder, 1), baseRarity));
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, TradeUtils.fluxPrice(this.rand, 25, 3)), new ItemStack(StarWarsMod.spawnAstromech, 1), baseRarity));
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, TradeUtils.fluxPrice(this.rand, 25, 3)), new ItemStack(StarWarsMod.spawnGonk, 1), baseRarity));
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, TradeUtils.fluxPrice(this.rand, 25, 3)), new ItemStack(StarWarsMod.spawnProtocol, 1), baseRarity));
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, TradeUtils.fluxPrice(this.rand, 25, 3)), new ItemStack(StarWarsMod.blockMV, 1), baseRarity));
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, TradeUtils.fluxPrice(this.rand, 5, 1)), new ItemStack(StarWarsMod.banthaHorn, 1), baseRarity));
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, TradeUtils.fluxPrice(this.rand, 5, 1)), new ItemStack(StarWarsMod.banthaChop, 1), baseRarity));
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, TradeUtils.fluxPrice(this.rand, 15, 2)), new ItemStack(Items.saddle, 1), baseRarity));

			for (int i = 0; i < 8; i++)
			{
				WeightedTradeItem item;
				do
				{
					item = TradeUtils.getWeightedItemFromList(trades, this.rand);
				}
				while (this.isAlreadyTrading(item, fn));
				fn.add(new MerchantRecipe(item.cost, item.cost2, item.item));
			}

		}
		else if (type == indexOf(types, "corellian"))
		{
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, 64), new ItemStack(StarWarsMod.imperialCredit, 64), new ItemStack(StarWarsMod.hyperdriveEarth, 1), baseRarity));
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, 64), new ItemStack(StarWarsMod.imperialCredit, 64), new ItemStack(StarWarsMod.hyperdriveEndor, 1), baseRarity));
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, 64), new ItemStack(StarWarsMod.imperialCredit, 64), new ItemStack(StarWarsMod.hyperdriveHoth, 1), baseRarity));
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, 64), new ItemStack(StarWarsMod.imperialCredit, 64), new ItemStack(StarWarsMod.hyperdriveKashyyyk, 1), baseRarity));
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, 64), new ItemStack(StarWarsMod.imperialCredit, 64), new ItemStack(StarWarsMod.hyperdriveTatooine, 1), baseRarity));
			trades.add(new WeightedTradeItem(new ItemStack(StarWarsMod.imperialCredit, 64), new ItemStack(StarWarsMod.imperialCredit, 64), new ItemStack(StarWarsMod.hyperdriveYavin4, 1), baseRarity));

			for (int i = 0; i < 3; i++)
			{
				WeightedTradeItem item;
				do
				{
					item = TradeUtils.getWeightedItemFromList(trades, this.rand);
				}
				while (this.isAlreadyTrading(item, fn));
				fn.add(new MerchantRecipe(item.cost, item.cost2, item.item));
			}
		}

		return fn;
	}

	public boolean isAlreadyTrading(WeightedTradeItem item, MerchantRecipeList trades)
	{
		for (Object x : trades.toArray())
		{
			if (((MerchantRecipe)x).getItemToSell() == item.item) { return true; }
		}
		return false;
	}

	private int indexOf(String[] haystack, String needle)
	{
		return Arrays.asList(haystack).indexOf(needle);
	}
}
