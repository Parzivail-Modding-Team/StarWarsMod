package com.parzivail.pswm.blocks.ore;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.util.block.PBlock;
import com.parzivail.util.world.HarvestLevel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.Random;

public class BlockFocusingCrystalOre extends PBlock
{
	public BlockFocusingCrystalOre()
	{
		super("focusingCrystalOre", Material.rock);
		setBlockTextureName(Resources.MODID + ":" + name);
		setCreativeTab(StarWarsMod.StarWarsTabBlocks);
		setHardness(4.0F);
		this.setHarvestLevel("pickaxe", HarvestLevel.IRON);
		setStepSound(soundTypeStone);
	}

	public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target)
	{
		return true;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return StarWarsItems.focusingCrystal;
	}
}
