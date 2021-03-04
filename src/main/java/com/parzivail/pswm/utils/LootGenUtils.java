package com.parzivail.pswm.utils;

import com.parzivail.pswm.StarWarsItems;
import com.parzivail.util.entity.trade.WeightedLoot;
import com.parzivail.util.math.MathUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LootGenUtils
{
	public static float baseRarity = 1.0F;

	public static void fillLootChest(int dimId, Random rand, TileEntityChest tile)
	{
		if (tile != null)
		{
			for (int i = 0; i < rand.nextInt(2) + 1; i++)
				tile.setInventorySlotContents(rand.nextInt(tile.getSizeInventory()), getWeightedItemForDim(dimId, rand));
			tile.setInventorySlotContents(rand.nextInt(tile.getSizeInventory()), new ItemStack(StarWarsItems.imperialCredit, MathUtils.randomRange(1, 15)));
		}
	}

	public static List<WeightedLoot> getLootForDim(int dimId)
	{
		List<WeightedLoot> loot = new ArrayList();
		loot.add(new WeightedLoot(new ItemStack(StarWarsItems.imperialCredit, MathUtils.randomRange(1, 4)), baseRarity));
		return loot;
	}

	public static ItemStack getWeightedItemForDim(int dimId, Random rand)
	{
		List<WeightedLoot> loot = getLootForDim(dimId);
		int length = loot.toArray().length;
		double totalWeight = 0.0D;
		for (int i = 0; i < length; i++)
			totalWeight += loot.get(i).weight;
		int randomIndex = -1;
		double random = rand.nextDouble() * totalWeight;
		for (int i = 0; i < length; i++)
		{
			random -= loot.get(i).weight;
			if (random <= 0.0D)
			{
				randomIndex = i;
				break;
			}
		}
		return loot.get(randomIndex).item;
	}

	public static ItemStack getWeightedItemFromList(List<WeightedLoot> items, Random rand)
	{
		int length = items.toArray().length;
		double totalWeight = 0.0D;
		for (int i = 0; i < length; i++)
			totalWeight += items.get(i).weight;
		int randomIndex = -1;
		double random = rand.nextDouble() * totalWeight;
		for (int i = 0; i < length; i++)
		{
			random -= items.get(i).weight;
			if (random <= 0.0D)
			{
				randomIndex = i;
				break;
			}
		}
		return items.get(randomIndex).item;
	}

	private static int indexOf(String[] haystack, String needle)
	{
		return Arrays.asList(haystack).indexOf(needle);
	}
}