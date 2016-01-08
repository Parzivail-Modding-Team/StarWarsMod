package com.parzi.starwarsmod.items.hyperdrive;

import java.util.List;

import com.parzi.starwarsmod.Resources;
import com.parzi.starwarsmod.StarWarsMod;
import com.parzi.starwarsmod.network.PacketTeleportPlayerNetwork;
import com.parzi.util.ui.Lumberjack;
import com.parzi.util.ui.TextUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHyperdriveDagobah extends Item
{
	public String name = "hyperdriveDagobah";

	public ItemHyperdriveDagobah()
	{
		this.setUnlocalizedName(Resources.MODID + "." + this.name);
		this.setTextureName(Resources.MODID + ":" + this.name);
		this.setCreativeTab(StarWarsMod.StarWarsTab);
		this.maxStackSize = 1;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool)
	{
		list.add(TextUtils.makeItalic("Your ticket to the galaxy"));
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		try
		{
			if (player.isSneaking() && player.dimension != StarWarsMod.dimDagobahId)
			{
				player.timeUntilPortal = 20;
				StarWarsMod.network.sendToServer(new PacketTeleportPlayerNetwork(player.getCommandSenderName(), player.dimension, StarWarsMod.dimDagobahId));
			}
		}
		catch (Exception e)
		{
			Lumberjack.log("Something went wrong @ hyperdrive.java:34");
			e.printStackTrace();
		}
		return stack;
	}
}
/*
 * Location: C:\Users\Colby\Downloads\Parzi's Star Wars Mod
 * v1.2.0-dev7.jar!\com\
 * parzi\starwarsmod\items\hyperdrive\ItemHyperdriveEndor.class Java compiler
 * version: 6 (50.0) JD-Core Version: 0.7.1
 */