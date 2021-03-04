package com.parzivail.pswm.items;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsMod;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemSlopeWizard extends Item
{
	public String name = "slopeWizard";

	public ItemSlopeWizard()
	{
		this.setUnlocalizedName(Resources.MODID + "." + this.name);
		this.setTextureName(Resources.MODID + ":" + this.name);
		this.setCreativeTab(StarWarsMod.StarWarsTab);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float sX, float sY, float sZ)
	{
		if (stack.stackTagCompound == null)
			stack.stackTagCompound = new NBTTagCompound();

		if (player.isSneaking())
		{
			Block block = world.getBlock(x, y, z);
			if (block == StarWarsMod.blockTempleStone || block == StarWarsMod.blockTempleStoneLit || block == StarWarsMod.blockTempleStoneMH)
			{
				stack.stackTagCompound.setString("blockUsing", Resources.MODID + ":" + "blockTempleStoneMH");
				if (!world.isRemote)
					player.addChatComponentMessage(new ChatComponentText(String.format("Changed block to %s", stack.stackTagCompound.getString("blockUsing"))));
				return super.onItemUse(stack, player, world, x, y, z, side, sX, sY, sZ);
			}
		}

		if (stack.stackTagCompound.getIntArray("firstPos").length == 0)
		{
			stack.stackTagCompound.setIntArray("firstPos", new int[] { x, y, z });

			if (!world.isRemote)
				player.addChatComponentMessage(new ChatComponentText(String.format("Set first position: %s,%s,%s", x, y, z)));
		}
		else
		{
			int[] posA = stack.stackTagCompound.getIntArray("firstPos");
			int aX = posA[0];
			int aY = posA[1];
			int aZ = posA[2];

			if (aX == x)
			{
				// going in x direction
				double rise = Math.max(aY, y) - Math.min(aY, y);
				double run = Math.max(z, aZ) - Math.min(z, aZ);
				double slope = rise / run;

				if (!world.isRemote && (slope > 1 || slope < -1))
				{
					player.addChatComponentMessage(new ChatComponentText("Slopes over 45 deg. don't work!"));

					return super.onItemUse(stack, player, world, x, y, z, side, sX, sY, sZ);
				}

				if (z > aZ)
				{
					for (int zz = aZ; zz <= z; zz++)
					{
						int nY = getBaseBlockY(-slope, zz - aZ);
						int rY = Math.max(aY, y) + nY;
						world.setBlock(x, rY, zz, StarWarsMod.blockTempleStone, 0, 2);
						world.setBlock(x, rY + 1, zz, StarWarsMod.blockTempleStoneMH, getVarHeightY(-slope, zz - aZ), 2);
						world.setBlockMetadataWithNotify(x, rY + 1, zz, getVarHeightY(-slope, zz - aZ), 2);
					}
				}
				else
				{
					for (int zz = aZ; zz >= z; zz--)
					{
						int nY = getBaseBlockY(slope, zz - z);
						int rY = Math.min(aY, y) + nY;
						world.setBlock(x, rY, zz, StarWarsMod.blockTempleStone, 0, 2);
						world.setBlock(x, rY + 1, zz, StarWarsMod.blockTempleStoneMH, getVarHeightY(slope, zz - aZ), 2);
						world.setBlockMetadataWithNotify(x, rY + 1, zz, getVarHeightY(slope, zz - aZ), 2);
					}
				}

				if (!world.isRemote)
					player.addChatComponentMessage(new ChatComponentText(String.format("Created Z-dir slope from Y=%s to Y=%s", y, aY)));
			}
			else if (aZ == z)
			{
				// going in x direction
				double rise = Math.max(aY, y) - Math.min(aY, y);
				double run = Math.max(x, aX) - Math.min(x, aX);
				double slope = rise / run;

				if (!world.isRemote && (slope > 1 || slope < -1))
				{
					player.addChatComponentMessage(new ChatComponentText("Slopes over 45 deg. don't work!"));

					return super.onItemUse(stack, player, world, x, y, z, side, sX, sY, sZ);
				}

				if (x > aX)
				{
					for (int xx = aX; xx <= x; xx++)
					{
						int nY = getBaseBlockY(-slope, xx - aX);
						int rY = Math.max(aY, y) + nY;
						world.setBlock(xx, rY, z, StarWarsMod.blockTempleStone, 0, 2);
						world.setBlock(xx, rY + 1, z, StarWarsMod.blockTempleStoneMH, getVarHeightY(-slope, xx - aX), 2);
						world.setBlockMetadataWithNotify(xx, rY + 1, z, getVarHeightY(-slope, xx - aX), 2);
					}
				}
				else
				{
					for (int xx = aX; xx >= x; xx--)
					{
						int nY = getBaseBlockY(slope, xx - x);
						int rY = Math.min(aY, y) + nY;
						world.setBlock(xx, rY, z, StarWarsMod.blockTempleStone, 0, 2);
						world.setBlock(xx, rY + 1, z, StarWarsMod.blockTempleStoneMH, getVarHeightY(slope, xx - aX), 2);
						world.setBlockMetadataWithNotify(xx, rY + 1, z, getVarHeightY(slope, xx - aX), 2);
					}
				}

				if (!world.isRemote)
					player.addChatComponentMessage(new ChatComponentText(String.format("Created X-dir slope from Y=%s to Y=%s", y, aY)));
			}
			else
			{
				if (!world.isRemote)
					player.addChatComponentMessage(new ChatComponentText("Cannot create diagonal slopes!"));
			}
			stack.stackTagCompound.setIntArray("firstPos", new int[0]);

		}

		return super.onItemUse(stack, player, world, x, y, z, side, sX, sY, sZ);
	}

	private int getBaseBlockY(double slope, double x)
	{
		return (int)Math.floor(slope * Math.floor(x));
	}

	private int getVarHeightY(double slope, double x)
	{
		return (int)(Math.floor(16 * slope * Math.floor(x)) % 16);
	}
}