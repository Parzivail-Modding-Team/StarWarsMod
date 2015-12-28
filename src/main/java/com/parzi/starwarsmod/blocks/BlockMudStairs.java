package com.parzi.starwarsmod.blocks;

import net.minecraft.block.BlockStairs;

import com.parzi.starwarsmod.StarWarsMod;
import com.parzi.starwarsmod.utils.HarvestLevel;

public class BlockMudStairs extends BlockStairs
{
	public String name = "mudStairs";

	public BlockMudStairs()
	{
		super(StarWarsMod.blockDagobahMud, 0);
		this.setBlockName(StarWarsMod.MODID + "." + this.name);
		this.setCreativeTab(StarWarsMod.StarWarsTab);
		this.setHarvestLevel("pickaxe", HarvestLevel.IRON);
	}
}
/*
 * Location: C:\Users\Colby\Downloads\Parzi's Star Wars Mod
 * v1.2.0-dev7.jar!\com\parzi\starwarsmod\blocks\BlockEndorBaseWallStairs.class
 * Java compiler version: 6 (50.0) JD-Core Version: 0.7.1
 */