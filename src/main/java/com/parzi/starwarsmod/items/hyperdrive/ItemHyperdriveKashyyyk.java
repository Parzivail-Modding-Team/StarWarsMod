package com.parzi.starwarsmod.items.hyperdrive;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.parzi.starwarsmod.StarWarsMod;
import com.parzi.starwarsmod.network.TeleportPlayerNetwork;
import com.parzi.starwarsmod.utils.TextUtils;

public class ItemHyperdriveKashyyyk extends Item
{
	private String name = "hyperdriveKashyyyk";

	public ItemHyperdriveKashyyyk()
	{
		setUnlocalizedName(StarWarsMod.MODID + "." + name);
		setTextureName(StarWarsMod.MODID + ":" + name);
		setCreativeTab(StarWarsMod.StarWarsTab);
		this.maxStackSize = 1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		System.out.println(player.getPortalCooldown());
		if (player.isSneaking() && player.dimension != StarWarsMod.dimKashyyykId)
		{
			player.timeUntilPortal = 20;
			StarWarsMod.network.sendToServer(new TeleportPlayerNetwork(player.getCommandSenderName(), player.dimension, StarWarsMod.dimKashyyykId));
		}
		return stack;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool)
	{
		list.add(TextUtils.makeItalic("Your ticket to the galaxy"));
	}
}
