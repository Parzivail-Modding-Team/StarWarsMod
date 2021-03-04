package com.parzivail.pswm.vehicles;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.registry.KeybindRegistry;
import com.parzivail.util.ui.LangUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemSpawnXWing extends Item
{
	public String name = "spawnXwing";

	public ItemSpawnXWing()
	{
		this.setUnlocalizedName(Resources.MODID + "." + this.name);
		this.setTextureName(Resources.MODID + ":" + "blank");
		this.setCreativeTab(StarWarsMod.StarWarsTab);
		this.maxStackSize = 1;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		String s = KeybindRegistry.keyShootVehicle.getKeyCode() <= 0 ? "UNKNOWN" : Keyboard.getKeyName(KeybindRegistry.keyShootVehicle.getKeyCode());
		list.add(String.format(LangUtils.translate("press.s.to.fire.lasers"), s));
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par1, float par2, float par3, float par4)
	{
		if (player.capabilities.isCreativeMode || player.inventory.consumeInventoryItem(StarWarsItems.spawnXwing))
			if (!world.isRemote)
			{
				VehicXWing newVehic = new VehicXWing(world);
				newVehic.onPlacedBy(player);
				newVehic.setPosition(x + 0.5D, y + 1, z + 0.5D);
				world.spawnEntityInWorld(newVehic);
			}
		return true;
	}
}
