package com.parzi.starwarsmod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import com.parzi.starwarsmod.StarWarsMod;
import com.parzi.starwarsmod.utils.HarvestLevel;

public class BlockDagobahMud extends Block
{
	private String name = "dagobahMud";

	public BlockDagobahMud()
	{
		super(Material.ground);
		this.setBlockName(StarWarsMod.MODID + "." + this.name);
		this.setBlockTextureName(StarWarsMod.MODID + ":" + this.name);
		this.setCreativeTab(StarWarsMod.StarWarsTab);
		this.setHardness(4.0F);
		this.setStepSound(soundTypeSand);
	}
}
/*
 * Location: C:\Users\Colby\Downloads\Parzi's Star Wars Mod
 * v1.2.0-dev7.jar!\com\parzi\starwarsmod\blocks\BlockChromiumOre.class Java
 * compiler version: 6 (50.0) JD-Core Version: 0.7.1
 */