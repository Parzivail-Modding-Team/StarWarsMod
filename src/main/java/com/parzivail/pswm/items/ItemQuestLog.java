package com.parzivail.pswm.items;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.network.MessageSetQuestLogNbt;
import com.parzivail.pswm.network.MessageSetQuests;
import com.parzivail.pswm.quest.IQuest;
import com.parzivail.pswm.quest.NBTQuestTag;
import com.parzivail.util.ui.LangUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

public class ItemQuestLog extends Item
{
	public String name = "questContainer";

	public ItemQuestLog()
	{
		this.setUnlocalizedName(Resources.MODID + "." + this.name);
		this.setTextureName(Resources.MODID + ":" + this.name);
		this.setCreativeTab(StarWarsMod.StarWarsTab);
		this.maxStackSize = 1;
	}

	public static NBTQuestTag getQuests(ItemStack stack)
	{
		if (!stack.stackTagCompound.hasKey(Resources.nbtQuests))
			stack.stackTagCompound.setTag(Resources.nbtQuests, new NBTTagCompound());
		return new NBTQuestTag(stack.stackTagCompound.getCompoundTag(Resources.nbtQuests));
	}

	public static void setQuestDone(EntityPlayer player, IQuest quest)
	{
		ItemStack stack = getQuestContainer(player);
		if (stack == null)
			return;

		NBTTagCompound co = getQuests(stack).setQuestDone(quest.getID()).getCompound();
		StarWarsMod.network.sendToServer(new MessageSetQuests(player, co));
	}

	public static boolean isQuestDone(EntityPlayer player, IQuest quest)
	{
		ItemStack stack = getQuestContainer(player);
		return stack != null && getQuests(stack).isQuestDone(quest.getID());
	}

	public static void setHasHyperdrive(ItemStack stack, String planet)
	{
		if (!stack.hasTagCompound())
			return;
		stack.stackTagCompound.setBoolean("hyperdrive-" + planet, true);
	}

	public static boolean getHasHyperdrive(ItemStack stack, String planet)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("hyperdrive-" + planet) && stack.stackTagCompound.getBoolean("hyperdrive-" + planet);
	}

	public static void setHasBroughtAtst(ItemStack stack, boolean b)
	{
		if (!stack.hasTagCompound())
			return;
		stack.stackTagCompound.setBoolean("hasBroughtAtst", b);
	}

	public static boolean getHasBroughtAtst(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("hasBroughtAtst") && stack.stackTagCompound.getBoolean("hasBroughtAtst");
	}

	public static void setInHyperspace(ItemStack stack, boolean hyperspace)
	{
		if (!stack.hasTagCompound())
			return;
		stack.stackTagCompound.setBoolean("inHyperspace", hyperspace);
	}

	public static boolean getInHyperspace(ItemStack stack)
	{
		return stack.hasTagCompound() && stack.stackTagCompound.hasKey("inHyperspace") && stack.stackTagCompound.getBoolean("inHyperspace");
	}

	public static String getOwner(ItemStack stack)
	{
		if (!stack.stackTagCompound.hasKey(Resources.nbtOwner))
			return null;
		return stack.stackTagCompound.getString(Resources.nbtOwner);
	}

	public static ItemStack getQuestContainer(EntityPlayer player)
	{
		for (ItemStack i : player.inventory.mainInventory)
		{
			if (i == null)
				continue;
			if (i.getItem() instanceof ItemQuestLog)
				return i;
		}
		return null;
	}

	public String getItemStackDisplayName(ItemStack stack)
	{
		if (stack.hasTagCompound())
		{
			NBTTagCompound nbttagcompound = stack.getTagCompound();
			String s = nbttagcompound.getString(Resources.nbtOwner);

			if (!StringUtils.isNullOrEmpty(s))
			{
				return LangUtils.translate("0.s.quest.log", s);
			}
		}

		return super.getItemStackDisplayName(stack);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int a, boolean b)
	{
		if (!stack.hasTagCompound() && entity instanceof EntityPlayer)
		{
			stack.stackTagCompound = new NBTTagCompound();
			stack.stackTagCompound.setString(Resources.nbtOwner, entity.getCommandSenderName());
			getQuests(stack);
		}
	}

	public static int getTargetKills(ItemStack stack)
	{
		if (stack == null || !stack.hasTagCompound())
			return 0;
		return stack.stackTagCompound.getInteger("targetKills");
	}

	public static void addTargetKill(ItemStack stack)
	{
		if (stack == null || stack == null || !stack.hasTagCompound())
			return;
		stack.stackTagCompound.setInteger("targetKills", getTargetKills(stack) + 1);
	}

	public static int getDimTravel(ItemStack stack, int planetId)
	{
		if (stack == null || !stack.hasTagCompound())
			return 0;
		return stack.stackTagCompound.getInteger("dimTravel" + String.valueOf(planetId));
	}

	public static void addDimTravel(ItemStack stack, int planetId)
	{
		if (stack == null || !stack.hasTagCompound())
			return;
		stack.stackTagCompound.setInteger("dimTravel" + String.valueOf(planetId), getDimTravel(stack, planetId) + 1);
	}

	public static String getSide(EntityPlayer player)
	{
		ItemStack stack = getQuestContainer(player);
		if (stack == null || !stack.hasTagCompound())
			return Resources.allegianceNoneFmt;
		return stack.stackTagCompound.getString("side");
	}

	public static void setSide(EntityPlayer player, String side)
	{
		ItemStack stack = getQuestContainer(player);
		if (stack == null || !stack.hasTagCompound())
			return;
		stack.stackTagCompound.setString("side", side);
		StarWarsMod.network.sendToServer(new MessageSetQuestLogNbt(player, stack.stackTagCompound));
	}

	public static int getStat(EntityPlayer player, String statId)
	{
		ItemStack stack = getQuestContainer(player);
		if (stack == null || !stack.hasTagCompound())
			return 0;
		return stack.stackTagCompound.getInteger(statId);
	}

	public static void addStat(EntityPlayer player, String statId)
	{
		ItemStack stack = getQuestContainer(player);
		if (stack == null || !stack.hasTagCompound())
			return;
		stack.stackTagCompound.setInteger(statId, getStat(player, statId) + 1);
	}
}