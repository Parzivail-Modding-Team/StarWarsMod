package com.parzivail.pswm.blocks.ore;

import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.items.lightsaber.ItemLightsaberCrystal;
import com.parzivail.util.block.BlockMultiTexture;
import com.parzivail.util.world.HarvestLevel;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockCrystalOre extends BlockMultiTexture
{
	public static final String[] colors = { "black", "blue", "cyan", "gray", "green", "pink", "purple", "white", "yellow", "prism" };
	public static int[] crystalColor = new int[BlockCrystalOre.colors.length];

	static
	{
		for (int i = 0; i < colors.length; i++)
			for (int j = 0; j < ItemLightsaberCrystal.colors.length; j++)
				if (colors[i].equals(ItemLightsaberCrystal.colors[j]))
					crystalColor[i] = j;
	}

	public BlockCrystalOre()
	{
		super("crystal_ore", colors, Material.rock, true);
		setCreativeTab(StarWarsMod.StarWarsTabBlocks);
		setHardness(4.0F);
		this.setHarvestLevel("pickaxe", HarvestLevel.IRON);
		setStepSound(soundTypeMetal);
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return StarWarsItems.lightsaberCrystal;
	}

	@Override
	public int damageDropped(int meta)
	{
		return crystalColor[meta];
	}
}