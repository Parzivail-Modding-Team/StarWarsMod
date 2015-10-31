package com.parzi.starwarsmod.vehicles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.parzi.starwarsmod.StarWarsMod;

public class ItemSpawnSpeederBike extends net.minecraft.item.Item
{
	private String name = "spawnSpeederBike";

	public ItemSpawnSpeederBike()
	{
		this.setUnlocalizedName(StarWarsMod.MODID + "." + this.name);
		this.setTextureName(StarWarsMod.MODID + ":" + this.name);
		this.setCreativeTab(StarWarsMod.StarWarsTab);
		this.maxStackSize = 1;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par1, float par2, float par3, float par4)
	{
		if (player.capabilities.isCreativeMode || player.inventory.consumeInventoryItem(StarWarsMod.spawnSpeederBike)) if (!world.isRemote)
		{
			VehicSpeederBike newVehic = new VehicSpeederBike(world);
			newVehic.setPosition(x + 0.5D, y + 1, z + 0.5D);
			world.spawnEntityInWorld(newVehic);
		}
		return true;
	}
}
/*
 * Location: C:\Users\Colby\Downloads\Parzi's Star Wars Mod
 * v1.2.0-dev7.jar!\com\parzi\starwarsmod\vehicles\ItemSpawnSpeederBike.class
 * Java compiler version: 6 (50.0) JD-Core Version: 0.7.1
 */