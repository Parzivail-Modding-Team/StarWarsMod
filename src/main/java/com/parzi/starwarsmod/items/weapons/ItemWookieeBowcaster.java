package com.parzi.starwarsmod.items.weapons;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.parzi.starwarsmod.StarWarsMod;
import com.parzi.starwarsmod.entities.EntityBlasterRifleBolt;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWookieeBowcaster extends Item
{
	private String name = "bowcaster";
	private int timeSinceLastShot = 0;
	private int timeToRecharge = 8;

	public ItemWookieeBowcaster()
	{
		setCreativeTab(StarWarsMod.StarWarsTab);
		setHasSubtypes(true);
		setUnlocalizedName(StarWarsMod.MODID + "." + name);
		setTextureName(StarWarsMod.MODID + ":" + name);
		this.maxStackSize = 1;
	}

	@Override
	public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_)
	{
		if (!p_77663_1_.hasTagCompound())
		{
			p_77663_1_.stackTagCompound = new NBTTagCompound();
		}

		if (!p_77663_1_.stackTagCompound.hasKey("timeout"))
		{
			p_77663_1_.stackTagCompound.setInteger("timeout", 0);
		}

		if (p_77663_1_.stackTagCompound.getInteger("timeout") > 0)
		{
			p_77663_1_.stackTagCompound.setInteger("timeout", p_77663_1_.stackTagCompound.getInteger("timeout") - 1);
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
	{
		p_77615_1_.stackTagCompound.setInteger("timeout", 0);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer entityPlayer)
	{
		if (!par2World.isRemote && par1ItemStack.stackTagCompound.getInteger("timeout") == 0)
		{
			par2World.spawnEntityInWorld(new EntityBlasterRifleBolt(par2World, entityPlayer));
			par1ItemStack.stackTagCompound.setInteger("timeout", timeToRecharge);
		}

		entityPlayer.playSound(StarWarsMod.MODID + ":" + "item.blasterRifle.use", 1f, 1f + (float)MathHelper.getRandomDoubleInRange(Item.itemRand, -0.2D, 0.2D));

		return par1ItemStack;
	}
}
