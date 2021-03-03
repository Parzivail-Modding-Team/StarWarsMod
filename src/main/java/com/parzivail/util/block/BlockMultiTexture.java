package com.parzivail.util.block;

import com.parzivail.pswm.Resources;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class BlockMultiTexture extends PBlock
{
	private String[] names;
	private final boolean newStyleName;
	private int subtypes;
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockMultiTexture(String base, String[] postfixes, Material material)
	{
		this(base, postfixes, material, false);
	}

	public BlockMultiTexture(String base, String[] postfixes, Material material, boolean newStyleName)
	{
		super(base, material);
		this.names = postfixes;
		this.newStyleName = newStyleName;
		this.subtypes = this.names.length;
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.icons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List metaTypes)
	{
		for (int i = 0; i < this.subtypes; i++)
			metaTypes.add(new ItemStack(item, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.icons = new IIcon[this.subtypes];
		for (int i = 0; i < this.subtypes; i++)
			if (newStyleName)
				this.icons[i] = par1IconRegister.registerIcon(Resources.MODID + ':' + this.name + '/' + this.names[i]);
			else
				this.icons[i] = par1IconRegister.registerIcon(Resources.MODID + ':' + this.name + this.names[i]);
	}
}