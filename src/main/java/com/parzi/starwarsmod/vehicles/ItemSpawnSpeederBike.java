package com.parzi.starwarsmod.vehicles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.parzi.starwarsmod.StarWarsMod;

public class ItemSpawnSpeederBike extends Item
{
	private String name = "spawnSpeederBike";

	public ItemSpawnSpeederBike()
	{
		setUnlocalizedName(StarWarsMod.MODID + "." + name);
		setTextureName(StarWarsMod.MODID + ":" + name);
		setCreativeTab(StarWarsMod.StarWarsTab);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par1, float par2, float par3, float par4)
	{
		if (player.capabilities.isCreativeMode || player.inventory.consumeInventoryItem(StarWarsMod.spawnSpeederBike))
		{
			if (!world.isRemote)
			{
				VehicSpeederBike newVehic = new VehicSpeederBike(world);
				newVehic.setPosition(player.posX, player.posY, player.posZ);
				world.spawnEntityInWorld(newVehic);
			}
		}
		return true;
	}
}
