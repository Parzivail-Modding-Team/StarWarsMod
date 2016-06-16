package com.parzivail.pswm.blocks.ore;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.util.world.HarvestLevel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockFocusingCrystalOre extends Block
{
	public String name = "focusingCrystalOre";

	public BlockFocusingCrystalOre()
	{
		super(Material.rock);
		setBlockName(Resources.MODID + "." + name);
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
}
