package com.parzivail.pswm.items;

import com.parzivail.pswm.blocks.ore.BlockCrystalOre;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemCrystalOreBlock extends ItemBlock
{
	public ItemCrystalOreBlock(Block block)
	{
		super(block);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		return getUnlocalizedName() + '.' + BlockCrystalOre.colors[itemstack.getItemDamage()];
	}
}