package com.parzivail.pswm.items;

import com.parzivail.pswm.Resources;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemMusicDisc extends ItemRecord
{
	public String name = "musicDisc";
	public final String field_150929_a;

	public ItemMusicDisc(String songName)
	{
		super(songName);
		this.field_150929_a = songName;
		this.maxStackSize = 1;
		this.setUnlocalizedName(Resources.MODID + "." + this.name);
		this.setCreativeTab(null);
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add(this.func_150927_i());
	}

	public String func_150927_i()
	{
		return StatCollector.translateToLocal(this.getUnlocalizedName() + this.field_150929_a + ".desc");
	}

	@Override
	public EnumRarity getRarity(ItemStack itemStack)
	{
		return EnumRarity.rare;
	}

	@Override
	public ResourceLocation getRecordResource(String name)
	{
		return new ResourceLocation(Resources.MODID + ":" + "item." + name);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon(Resources.MODID + ":" + "record" + this.field_150929_a);
	}
}