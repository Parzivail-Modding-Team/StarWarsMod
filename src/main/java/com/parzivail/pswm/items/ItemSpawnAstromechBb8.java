package com.parzivail.pswm.items;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.mobs.MobDroidAstromechBb8;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSpawnAstromechBb8 extends Item
{
	public String name = "spawnAstromechBb8";

	public ItemSpawnAstromechBb8()
	{
		this.setUnlocalizedName(Resources.MODID + "." + this.name);
		this.setTextureName(Resources.MODID + ":" + this.name);
		this.setCreativeTab(StarWarsMod.SequelStarWarsTab);
		this.maxStackSize = 1;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par1, float par2, float par3, float par4)
	{
		if (player.capabilities.isCreativeMode || player.inventory.consumeInventoryItem(StarWarsItems.spawnAstromechBb8))
			if (!world.isRemote)
			{
				MobDroidAstromechBb8 newDroid = new MobDroidAstromechBb8(world);
				newDroid.setPosition(x + 0.5D, y + 1, z + 0.5D);
				world.spawnEntityInWorld(newDroid);
			}
		return true;
	}
}
