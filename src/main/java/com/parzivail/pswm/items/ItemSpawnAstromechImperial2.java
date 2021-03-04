package com.parzivail.pswm.items;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.mobs.MobDroidAstromechImperial2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSpawnAstromechImperial2 extends Item
{
	public String name = "spawnAstromechImperial2";

	public ItemSpawnAstromechImperial2()
	{
		this.setUnlocalizedName(Resources.MODID + "." + this.name);
		this.setTextureName(Resources.MODID + ":" + this.name);
		this.setCreativeTab(StarWarsMod.StarWarsTab);
		this.maxStackSize = 1;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par1, float par2, float par3, float par4)
	{
		if (player.capabilities.isCreativeMode || player.inventory.consumeInventoryItem(StarWarsItems.spawnAstromechImperial2))
			if (!world.isRemote)
			{
				MobDroidAstromechImperial2 newDroid = new MobDroidAstromechImperial2(world);
				newDroid.setPosition(x + 0.5D, y + 1, z + 0.5D);
				world.spawnEntityInWorld(newDroid);
			}
		return true;
	}
}
