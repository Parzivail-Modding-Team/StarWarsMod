package com.parzivail.pswm.vehicles.npc;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.mobs.trooper.MobSandtrooper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSpawnNpcLandspeeder extends Item
{
	public String name = "spawnNpcLandspeeder";

	public ItemSpawnNpcLandspeeder()
	{
		this.setUnlocalizedName(Resources.MODID + "." + this.name);
		this.setTextureName(Resources.MODID + ":" + "blank");
		this.setCreativeTab(StarWarsMod.StarWarsTab);
		this.maxStackSize = 1;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par1, float par2, float par3, float par4)
	{
		if (player.capabilities.isCreativeMode || player.inventory.consumeInventoryItem(StarWarsItems.spawnNpcLandspeeder))
			if (!world.isRemote)
			{
				MobSandtrooper sandtrooper = new MobSandtrooper(world);

				VehicNpcLandspeeder newVehic = new VehicNpcLandspeeder(world);

				sandtrooper.mountEntity(newVehic);
				newVehic.riddenByEntity = sandtrooper;

				world.spawnEntityInWorld(newVehic);
				world.spawnEntityInWorld(sandtrooper);
			}
		return true;
	}
}
