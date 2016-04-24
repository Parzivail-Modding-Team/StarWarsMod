package com.parzivail.pswm.blocks.ore;

import java.util.Random;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.util.world.HarvestLevel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockHeliciteOre extends Block
{
	public String name = "heliciteOre";

	public BlockHeliciteOre()
	{
		super(Material.rock);
		setBlockName(Resources.MODID + "." + name);
		setBlockTextureName(Resources.MODID + ":" + name);
		setCreativeTab(StarWarsMod.StarWarsTabBlocks);
		setHardness(4.0F);
		this.setHarvestLevel("pickaxe", HarvestLevel.IRON);
		setStepSound(soundTypeStone);
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return StarWarsMod.ingotHelicite;
	}
}
/*
 * Location: C:\Users\Colby\Downloads\Parzi's Star Wars Mod
 * v1.2.0-dev7.jar!\com\parzi\starwarsmod\blocks\BlockChromiumOre.class Java
 * compiler version: 6 (50.0) JD-Core Version: 0.7.1
 */