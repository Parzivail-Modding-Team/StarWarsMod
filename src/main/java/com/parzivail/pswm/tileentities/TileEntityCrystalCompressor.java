package com.parzivail.pswm.tileentities;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.util.world.ItemUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCrystalCompressor extends TileEntity implements ISidedInventory
{
	private static final int[] field_145941_a = new int[] { 3 };
	private static final int[] field_145947_i = new int[] { 0, 1, 2 };

	private ItemStack[] itemStacks = new ItemStack[4];
	private int compressTime;
	private int compressTimeMax = 6000;

	public TileEntityCrystalCompressor()
	{

	}

	/**
	 * Returns the name of the inventory
	 */
	public String getInventoryName()
	{
		return Resources.MODID + ":" + "container.crystalcompressor";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	public int getCompressTime()
	{
		return compressTime;
	}

	public int getCompressTimeMax()
	{
		return compressTimeMax;
	}

	public void setCompressTime(int time)
	{
		this.compressTime = time;
	}

	/**
	 * Returns the number of slots in the inventory.
	 */
	public int getSizeInventory()
	{
		return this.itemStacks.length;
	}

	public void updateEntity()
	{
		if (this.compressTime > 0)
		{
			--this.compressTime;

			if (this.compressTime == 0)
			{
				this.compress();
				this.markDirty();
			}
		}
		else if (this.canCompress() && initCompress())
		{
			this.compressTime = compressTimeMax;
			this.markDirty();
		}

		super.updateEntity();
	}

	private boolean canCompress()
	{
		Item ingredient = StarWarsItems.lightsaberCrystal;
		return ItemUtils.is(itemStacks[0], ingredient, 11) && ItemUtils.is(itemStacks[1], ingredient, 11) && ItemUtils.is(itemStacks[2], ingredient, 11) && (itemStacks[3] == null || ItemUtils.is(itemStacks[3], ingredient));
	}

	private void compress()
	{
		if (itemStacks[3] == null)
			itemStacks[3] = new ItemStack(StarWarsItems.lightsaberCrystal, 1);
		else
			ItemUtils.inc(itemStacks[3], 1);
	}

	private boolean initCompress()
	{
		if (this.canCompress() && ItemUtils.canDeinc(itemStacks[0], 1) && ItemUtils.canDeinc(itemStacks[1], 1) && ItemUtils.canDeinc(itemStacks[2], 1))
		{
			ItemUtils.deinc(itemStacks[0], 1);
			ItemUtils.deinc(itemStacks[1], 1);
			ItemUtils.deinc(itemStacks[2], 1);
			if (itemStacks[0].stackSize == 0)
				itemStacks[0] = null;
			if (itemStacks[1].stackSize == 0)
				itemStacks[1] = null;
			if (itemStacks[2].stackSize == 0)
				itemStacks[2] = null;
			return true;
		}
		return false;
	}

	public void readFromNBT(NBTTagCompound p_145839_1_)
	{
		super.readFromNBT(p_145839_1_);
		NBTTagList nbttaglist = p_145839_1_.getTagList("Items", 10);
		this.itemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.itemStacks.length)
			{
				this.itemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.compressTime = p_145839_1_.getShort("BrewTime");
	}

	public void writeToNBT(NBTTagCompound p_145841_1_)
	{
		super.writeToNBT(p_145841_1_);
		p_145841_1_.setShort("BrewTime", (short)this.compressTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.itemStacks.length; ++i)
		{
			if (this.itemStacks[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.itemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		p_145841_1_.setTag("Items", nbttaglist);
	}

	/**
	 * Returns the stack in slot i
	 */
	public ItemStack getStackInSlot(int p_70301_1_)
	{
		return p_70301_1_ >= 0 && p_70301_1_ < this.itemStacks.length ? this.itemStacks[p_70301_1_] : null;
	}

	/**
	 * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
	 * new stack.
	 */
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_)
	{
		if (p_70298_1_ >= 0 && p_70298_1_ < this.itemStacks.length)
		{
			ItemStack itemstack = this.itemStacks[p_70298_1_];
			this.itemStacks[p_70298_1_] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	/**
	 * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
	 * like when you close a workbench GUI.
	 */
	public ItemStack getStackInSlotOnClosing(int p_70304_1_)
	{
		if (p_70304_1_ >= 0 && p_70304_1_ < this.itemStacks.length)
		{
			ItemStack itemstack = this.itemStacks[p_70304_1_];
			this.itemStacks[p_70304_1_] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	 */
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_)
	{
		if (p_70299_1_ >= 0 && p_70299_1_ < this.itemStacks.length)
		{
			this.itemStacks[p_70299_1_] = p_70299_2_;
		}
	}

	/**
	 * Returns the maximum stack size for a inventory slot.
	 */
	public int getInventoryStackLimit()
	{
		return 64;
	}

	/**
	 * Do not make give this method the name canInteractWith because it clashes with Container
	 */
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && p_70300_1_.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	public void openInventory()
	{
	}

	public void closeInventory()
	{
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
	 */
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	{
		return p_94041_1_ == 3 ? p_94041_2_.getItem().isPotionIngredient(p_94041_2_) : p_94041_2_.getItem() instanceof ItemPotion || p_94041_2_.getItem() == Items.glass_bottle;
	}

	/**
	 * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
	 * block.
	 */
	public int[] getAccessibleSlotsFromSide(int p_94128_1_)
	{
		return p_94128_1_ == 1 ? field_145941_a : field_145947_i;
	}

	/**
	 * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
	 * side
	 */
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_)
	{
		return this.isItemValidForSlot(p_102007_1_, p_102007_2_);
	}

	/**
	 * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
	 * side
	 */
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
	{
		return true;
	}

}
