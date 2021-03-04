package com.parzivail.pswm.vehicles.npc;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.StarWarsMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSpawnNpcXWing extends Item
{
	public String name = "spawnNpcXwing";

	public ItemSpawnNpcXWing()
	{
		this.setUnlocalizedName(Resources.MODID + "." + this.name);
		this.setTextureName(Resources.MODID + ":" + "blank");
		this.setCreativeTab(StarWarsMod.StarWarsTab);
		this.maxStackSize = 1;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par1, float par2, float par3, float par4)
	{
		if (player.capabilities.isCreativeMode || player.inventory.consumeInventoryItem(StarWarsItems.spawnNpcXwing))
			if (!world.isRemote)
			{
				VehicNpcXWing newVehic = new VehicNpcXWing(world);
				world.spawnEntityInWorld(newVehic);
			}
		return true;
	}
}
