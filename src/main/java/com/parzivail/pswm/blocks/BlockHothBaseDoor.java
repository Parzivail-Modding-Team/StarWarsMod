package com.parzivail.pswm.blocks;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.util.world.HarvestLevel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockHothBaseDoor extends Block
{
	public String name = "hothDoor";

	public BlockHothBaseDoor()
	{
		super(Material.rock);
		setBlockName(Resources.MODID + "." + name);
		setBlockTextureName(Resources.MODID + ":" + name);
		setCreativeTab(StarWarsMod.StarWarsTabBlocks);
		setHardness(4.0F);
		this.setHarvestLevel("pickaxe", HarvestLevel.IRON);
		setStepSound(soundTypeMetal);
		setLightLevel(1.0F);
	}
}