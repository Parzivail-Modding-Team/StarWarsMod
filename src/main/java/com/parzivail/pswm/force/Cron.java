package com.parzivail.pswm.force;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.force.powers.*;
import com.parzivail.pswm.utils.EntityCooldownEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Colby on 6/10/2016. Utility class to help with Holocrons
 */
public class Cron
{
	public static final String SIDE_JEDI = "jedi";
	public static final String SIDE_SITH = "sith";
	public static final float POINTS_PER_LEVEL = 10;

	public static float distanceToEntity = -1;
	public static ArrayList<PowerBase> coolingPowers = new ArrayList<>();
	public static ArrayList<EntityCooldownEntry> entitiesWithEffects = new ArrayList<>();
	public static HashMap<String, Class<? extends PowerBase>> powers = new HashMap<>();

	static
	{
		powers.put("jump", PowerJump.class);
		powers.put("push", PowerPush.class);
		powers.put("pull", PowerPull.class);
		//powers.put("lightning", PowerLightning.class);
		powers.put("destruction", PowerDestruction.class);
		powers.put("defend", PowerDefend.class);
		powers.put("deflect", PowerDeflect.class);
		powers.put("naturalAwareness", PowerNaturalAwareness.class);
		powers.put("grab", PowerGrab.class);
		powers.put("disable", PowerDisable.class);
		powers.put("slow", PowerSlow.class);
		powers.put("healing", PowerHeal.class);
		powers.put("drainKnowledge", PowerDrainKnowledge.class);
		powers.put("saberThrow", PowerSaberThrow.class);
	}

	/**
	 * Gets a player's Holocron
	 *
	 * @param player The {@link EntityPlayer} to query
	 * @return The holocron {@link ItemStack}
	 */
	public static ItemStack getHolocron(EntityPlayer player)
	{
		if (player == null)
			return null;
		for (ItemStack i : player.inventory.mainInventory)
		{
			if (i == null)
				continue;
			if (i.getItem() instanceof ItemHolocron)
				return i;
		}
		return null;
	}

	/**
	 * Gets a player's active power
	 *
	 * @param player The {@link EntityPlayer} to query
	 * @return A {@link PowerBase} instance
	 */
	public static PowerBase getActive(EntityPlayer player)
	{
		ItemStack stack = getHolocron(player);
		if (stack == null)
			return null;
		return getActive(stack);
	}

	/**
	 * Gets the side of a player
	 *
	 * @param player The {@link EntityPlayer} to query
	 * @return The side as a string
	 */
	public static String getSide(EntityPlayer player)
	{
		ItemStack stack = getHolocron(player);
		if (stack == null)
			return null;
		return getSide(stack);
	}

	/**
	 * Gets the side of a Holocron
	 *
	 * @param stack The {@link ItemHolocron} stack to query
	 * @return The side as a string
	 */
	public static String getSide(ItemStack stack)
	{
		if (stack == null || !stack.hasTagCompound() || !stack.stackTagCompound.hasKey(Resources.nbtSide))
			return null;

		return stack.stackTagCompound.getString(Resources.nbtSide);
	}

	/**
	 * Gets the level of a player
	 *
	 * @param player The {@link EntityPlayer} to query
	 * @return The level as an int
	 */
	public static int getLevel(EntityPlayer player)
	{
		ItemStack stack = getHolocron(player);
		if (stack == null)
			return 0;
		return getLevel(stack);
	}

	/**
	 * Gets the level of a power
	 *
	 * @param player The {@link EntityPlayer} to query
	 * @param power  The power to query
	 * @return The level as an int
	 */
	public static int getLevelOf(EntityPlayer player, String power)
	{
		ItemStack stack = getHolocron(player);
		if (stack == null)
			return 0;
		return getLevelOf(stack, power);
	}

	/**
	 * Gets the level of a power
	 *
	 * @param stack The {@link ItemHolocron} stack to query
	 * @param power The power to query
	 * @return The level as an int
	 */
	public static int getLevelOf(ItemStack stack, String power)
	{
		if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey(Resources.nbtPowers))
			return new PowerBase(power).deserialize((NBTTagCompound)((NBTTagCompound)stack.stackTagCompound.getTag(Resources.nbtPowers)).getTag(power)).currentLevel;
		return 0;
	}

	/**
	 * Gets the max level of a power
	 *
	 * @param player The {@link EntityPlayer} to query
	 * @param power  The power to query
	 * @return The level as an int
	 */
	public static int getMaxLevelOf(EntityPlayer player, String power)
	{
		ItemStack stack = getHolocron(player);
		if (stack == null)
			return 0;
		return getMaxLevelOf(stack, power);
	}

	/**
	 * Gets the max level of a power
	 *
	 * @param stack The {@link ItemHolocron} stack to query
	 * @param power The power to query
	 * @return The level as an int
	 */
	public static int getMaxLevelOf(ItemStack stack, String power)
	{
		if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey(Resources.nbtPowers))
			return new PowerBase(power).deserialize((NBTTagCompound)((NBTTagCompound)stack.stackTagCompound.getTag(Resources.nbtPowers)).getTag(power)).maxLevel;
		return 0;
	}

	/**
	 * Gets the upgrade points of a player
	 *
	 * @param player The {@link EntityPlayer} to query
	 * @return The upgrade points as an int
	 */
	public static int getPoints(EntityPlayer player)
	{
		ItemStack stack = getHolocron(player);
		if (stack == null)
			return 0;
		return getPoints(stack);
	}

	/**
	 * Gets the upgrade points of a player
	 *
	 * @param stack The {@link ItemHolocron} stack to query
	 * @return The upgrade points as an int
	 */
	public static int getPoints(ItemStack stack)
	{
		if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey(Resources.nbtXp))
			return stack.stackTagCompound.getInteger(Resources.nbtRemainingPts);
		return 0;
	}

	/**
	 * Gets the XP of a player
	 *
	 * @param player The {@link EntityPlayer} to query
	 * @return The XP as an int
	 */
	public static int getXP(EntityPlayer player)
	{
		ItemStack stack = getHolocron(player);
		if (stack == null)
			return 0;
		return getXP(stack);
	}

	/**
	 * Gets the XP of a Holocron
	 *
	 * @param stack The {@link ItemHolocron} stack to query
	 * @return The XP as an int
	 */
	public static int getXP(ItemStack stack)
	{
		if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey(Resources.nbtXp))
			return stack.stackTagCompound.getInteger(Resources.nbtXp);
		return 0;
	}

	/**
	 * Gets the max XP of a Holocron
	 *
	 * @param stack The {@link ItemHolocron} stack to query
	 * @return The max XP as an int
	 */
	public static int getMaxXP(ItemStack stack)
	{
		if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey(Resources.nbtMaxXp))
			return stack.stackTagCompound.getInteger(Resources.nbtMaxXp);
		return 0;
	}

	/**
	 * Gets a stack's active power
	 *
	 * @param stack The {@link ItemHolocron} stack to query
	 * @return A {@link PowerBase} instance
	 */
	public static PowerBase getActive(ItemStack stack)
	{
		if (stack == null || !stack.hasTagCompound() || !stack.stackTagCompound.hasKey(Resources.nbtWield))
			return null;

		NBTTagCompound compound = ((NBTTagCompound)stack.stackTagCompound.getTag(Resources.nbtWield));
		String type = compound.getString("name");

		PowerBase power;
		if ((power = initNewPower(stack, type)) == null)
			return null;

		return power.deserialize(compound);
	}

	/**
	 * Initializes a new copy of a power and populates the values with those of the player
	 *
	 * @param player The {@link EntityPlayer} to copy
	 * @param power  The power to init
	 * @return A {@link PowerBase} instance
	 */
	public static PowerBase initNewPower(EntityPlayer player, String power)
	{
		ItemStack stack = getHolocron(player);
		if (stack == null)
			return null;
		return initNewPower(stack, power);
	}

	/**
	 * Gets the power NBT of a player, creating a new {@link NBTTagCompound} of powers if none was found
	 *
	 * @param player The {@link EntityPlayer} to query
	 * @return The {@link NBTTagCompound} of powers
	 */
	public static NBTTagCompound getPowers(EntityPlayer player)
	{
		ItemStack stack = getHolocron(player);
		if (stack == null)
			return makeNewPowersNBT();
		return getPowers(stack);
	}

	/**
	 * Initializes a new {@link NBTTagCompound} of powers
	 *
	 * @return The {@link NBTTagCompound} of powers
	 */
	public static NBTTagCompound makeNewPowersNBT()
	{
		NBTTagCompound compound = new NBTTagCompound();
		for (String powerName : Cron.powers.keySet())
			compound.setTag(powerName, Cron.initNewPower(powerName).serialize());
		return compound;
	}

	/**
	 * Gets the power NBT of a player, creating a new {@link NBTTagCompound} of powers if none was found
	 *
	 * @param stack The {@link ItemHolocron} stack to query
	 * @return The {@link NBTTagCompound} of powers
	 */
	public static NBTTagCompound getPowers(ItemStack stack)
	{
		if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey(Resources.nbtPowers))
			return (NBTTagCompound)stack.stackTagCompound.getTag(Resources.nbtPowers);
		return makeNewPowersNBT();
	}

	/**
	 * Gets the level of a player
	 *
	 * @param stack The {@link ItemHolocron} stack to query
	 * @return The level as an int
	 */
	public static int getLevel(ItemStack stack)
	{
		if (stack == null)
			return 0;
		if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey(Resources.nbtLevel))
			return stack.stackTagCompound.getInteger(Resources.nbtLevel);
		return 0;
	}

	/**
	 * Initializes a new copy of a power and populates the values with those of the the Holocron
	 *
	 * @param stack The {@link ItemHolocron} stack to copy
	 * @param type  The power to init
	 * @return A {@link PowerBase} instance
	 */
	public static PowerBase initNewPower(ItemStack stack, String type)
	{
		if (stack == null || !stack.hasTagCompound() || !stack.stackTagCompound.hasKey(Resources.nbtWield) || powers.get(type) == null)
			return null;

		Class clazz = powers.get(type);
		try
		{
			return ((PowerBase)clazz.getConstructor(int.class).newInstance(0)).deserialize((NBTTagCompound)((NBTTagCompound)stack.stackTagCompound.getTag(Resources.nbtPowers)).getTag(type));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Initializes a new copy of a power
	 *
	 * @param type The power to init
	 * @return A {@link PowerBase} instance
	 */
	public static PowerBase initNewPower(String type)
	{
		Class clazz = powers.get(type);
		try
		{
			return ((PowerBase)clazz.getConstructor(int.class).newInstance(0));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Gets the percent chance of XP gain vs rejection for a given level
	 *
	 * @param level The level to query
	 * @return The percent chance as a float from 1-100
	 */
	public static float getPercentForLevel(int level)
	{
		int i = 100 - level;
		i = Math.max(i, 10);
		return i;
	}

	/**
	 * Gets the basic powers available to any side
	 *
	 * @return A {@link String} array of power names
	 */
	public static String[] getBasicPowers()
	{
		return new String[] { "jump", "push", "pull", "defend", "disable", "deflect", "grab", "saberThrow" };
	}

	/**
	 * Gets the Jedi powers available. We don't need one for Sith powers because we can just do a
	 * deduction check against this array and {@link #getBasicPowers()} to see if they contain the power
	 *
	 * @return A {@link String} array of power names
	 */
	public static String[] getJediPowers()
	{
		return new String[] { "healing", "naturalAwareness" };
	}

	/**
	 * Gets the powers available to a player at a given level and side
	 * @param side The side to query
	 * @param level The level to query
	 * @return An {@link ArrayList} of powers
	 */
	public static ArrayList<String> getPowersAvailableAtLevel(String side, int level)
	{
		ArrayList<String> r = new ArrayList<>();

		r.add("jump");
		r.add("push");

		if (level > 5)
			r.add("pull");
		if (level > 10)
			r.add("defend");
		if (level > 15)
			r.add("disable");
		if (level > 20)
			r.add("deflect");
		if (level > 25)
			r.add("grab");
		if (level > 30)
			r.add("saberThrow");

		if (side.equals(SIDE_JEDI))
		{
			if (level > 30)
				r.add("healing");
			if (level > 35)
				r.add("naturalAwareness");
		}
		else if (side.equals(SIDE_SITH))
		{
			if (level > 35)
				r.add("slow");
			if (level > 40)
				r.add("drainKnowledge");
			if (level > 50)
				r.add("destruction");
		}

		return r;
	}

	/**
	 * Gets the proper title of a player at a given level and side
	 * @param side The side to query
	 * @param level The level to query
	 * @return The proper title
	 */
	public static String getTitle(String side, int level)
	{
		String s = side.equals(SIDE_JEDI) ? "Jedi " : "Sith ";
		if (side.equals(SIDE_JEDI))
		{
			if (level < 15)
				s += "Padawan";
			else if (level < 35)
				s += "Knight";
			else
				s += "Master";
		}
		else if (level < 45)
			s += "Acolyte";
		else if (level < 55)
			s += "Apprentice";
		else
			s += "Lord";
		return s;
	}

	/**
	 * Performs a basic check to see if a power is cooling
	 * @param power The power name to check
	 * @return True if the power is already cooling, false otherwise
	 */
	public static boolean isCooling(String power)
	{
		for (PowerBase p : coolingPowers)
			if (p.name.equals(power))
				return true;
		return false;
	}
}
