package com.parzivail.pswm.items.weapons;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.armor.ArmorShadowtrooper;
import com.parzivail.pswm.network.MessageSetPlayerHolding;
import com.parzivail.pswm.quest.QuestUtils;
import com.parzivail.pswm.registry.KeybindRegistry;
import com.parzivail.util.math.IntColorComparator;
import com.parzivail.util.math.MathUtils;
import com.parzivail.util.ui.GLPalette;
import com.parzivail.util.ui.LangUtils;
import com.parzivail.util.ui.TextEffects;
import com.parzivail.util.ui.TextUtils;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemLightsaber extends ItemSword
{
	public static final String nbtHilt = "hilt";
	public static final String nbtBladeLength = "bladeLength";
	public static final String nbtBladeColor = "bladeColor";
	public static final String nbtHiltSkin = "skin";
	public static final String nbtBladeWaterproof = "waterproof";
	public static final String nbtBladeDistortion = "distortion";
	public static final String nbtBladeOn = "on";
	public static final String nbtBladeTimeout = "timeout";
	public static final String nbtBlasterTimeout = "blasterTimeout";

	// 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
	public static final String[] hilts = { "obiwan", "quigon", "maul", "padawan", "dooku", "luke1", "vader2", "luke2", "crossguard", "kanan", "ezra", "revan", "malgus", "doubleSith", "starkiller", "shoto", "plokoon", "inquisitor", "mace", "yoda", "ahsoka", "darksaber", "pike", "revan2", "ventress" };
	public static Integer[] colorHex;
	// public static final String[] colorName = { "yellow", "pink", "purple",
	// "white", "gray", "orange", "teal", "black", "red", "green", "blue" };
	private static Integer[] colorHexNew = { 0xFFFF2B, 0xFF00A4, 0xD100FF, 0xFFFFFF, 0xA0A0A0, 0x202020, 0xFF6A00, 0x2FD0F6, 0xFF0000, 0x00FF30, 0x3010DA, 0xFF5200 };
	//                                          0  1  2  3  4  5  6  7  8  9 10 11
	public static HashMap<Integer, Integer> metaTable = new HashMap<Integer, Integer>()
	{
		{
			put(0xFFFF2B | 0xFF000000, 9);
			put(0xFF00A4 | 0xFF000000, 6);
			put(0xD100FF | 0xFF000000, 7);
			put(0xFFFFFF | 0xFF000000, 8);
			put(0xA0A0A0 | 0xFF000000, 5);
			put(0x202020 | 0xFF000000, 3);
			put(0xFF6A00 | 0xFF000000, 0);
			put(0x2FD0F6 | 0xFF000000, 4);
			put(0xFF0000 | 0xFF000000, 0);
			put(0x00FF30 | 0xFF000000, 1);
			put(0x3010DA | 0xFF000000, 2);
			put(0xFF5200 | 0xFF000000, 0);
		}
	};

	static
	{
		ArrayList<Integer> n = new ArrayList<>();
		for (Integer aColorHex : colorHexNew)
			n.add(GLPalette.makeOpaque(aColorHex));
		n.sort(new IntColorComparator());
		colorHex = MathUtils.toIntArray(n);
	}

	public int hiltIndex;

	private String name = "lightsaberNew";

	public ItemLightsaber(int hiltIndex)
	{
		super(StarWarsMod.materialNoDamage);
		this.setUnlocalizedName(Resources.MODID + "." + this.name);
		this.setCreativeTab(StarWarsMod.StarWarsTab);
		this.setTextureName(Resources.MODID + ":" + "blank");

		this.hiltIndex = hiltIndex;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity holder, int a, boolean b)
	{
		super.onUpdate(stack, world, holder, a, b);

		if (!stack.hasTagCompound())
		{
			setupNBT(this.hiltIndex, stack);
		}

		if (stack.stackTagCompound.getInteger(nbtBladeTimeout) > 0)
			stack.stackTagCompound.setInteger(nbtBladeTimeout, stack.stackTagCompound.getInteger(nbtBladeTimeout) - 1);

		if (stack.stackTagCompound.getInteger(nbtBlasterTimeout) > 0)
			stack.stackTagCompound.setInteger(nbtBlasterTimeout, stack.stackTagCompound.getInteger(nbtBlasterTimeout) - 1);

		if (world.isRemote && !stack.stackTagCompound.getBoolean(nbtBladeWaterproof) && holder.isInsideOfMaterial(Material.water) && stack.stackTagCompound.getBoolean(nbtBladeOn))
		{
			stack.stackTagCompound.setBoolean(nbtBladeOn, false);
			stack.stackTagCompound.setInteger(nbtBladeTimeout, 10);
			if (holder instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)holder;
				player.playSound(Resources.MODID + ":" + "item.lightsaber.fizz", 1.0F, 1.0F);
				StarWarsMod.network.sendToServer(new MessageSetPlayerHolding(player, stack));
			}
		}
	}

	public static ItemStack[] getItemsForSingleBlade()
	{
		return new ItemStack[] { new ItemStack(StarWarsItems.magneticStabilizingRing, 1), new ItemStack(StarWarsItems.energyModulationCircuit, 1), new ItemStack(StarWarsItems.cyclingFieldEnergizer, 1), new ItemStack(StarWarsItems.focusingCrystal, 1), new ItemStack(StarWarsItems.diatiumPowerCell, 1), new ItemStack(StarWarsItems.energyGate, 1), new ItemStack(StarWarsItems.powerVortexRing, 1), new ItemStack(StarWarsItems.inertPowerInsulator, 1), new ItemStack(StarWarsItems.hiltMetelAlloy, 4), };
	}

	public static ItemStack[] getItemsForSingleBladeBlaster()
	{
		return new ItemStack[] { new ItemStack(StarWarsItems.magneticStabilizingRing, 1), new ItemStack(StarWarsItems.energyModulationCircuit, 2), new ItemStack(StarWarsItems.cyclingFieldEnergizer, 1), new ItemStack(StarWarsItems.focusingCrystal, 1), new ItemStack(StarWarsItems.diatiumPowerCell, 2), new ItemStack(StarWarsItems.energyGate, 2), new ItemStack(StarWarsItems.powerVortexRing, 1), new ItemStack(StarWarsItems.inertPowerInsulator, 1), new ItemStack(StarWarsItems.hiltMetelAlloy, 6), };
	}

	public static ItemStack[] getItemsForSingleBladeShoto()
	{
		return new ItemStack[] { new ItemStack(StarWarsItems.magneticStabilizingRing, 1), new ItemStack(StarWarsItems.energyModulationCircuit, 1), new ItemStack(StarWarsItems.cyclingFieldEnergizer, 1), new ItemStack(StarWarsItems.focusingCrystal, 1), new ItemStack(StarWarsItems.diatiumPowerCell, 1), new ItemStack(StarWarsItems.energyGate, 1), new ItemStack(StarWarsItems.powerVortexRing, 1), new ItemStack(StarWarsItems.inertPowerInsulator, 1), new ItemStack(StarWarsItems.hiltMetelAlloy, 8), };
	}

	public static ItemStack[] getItemsForDoubleBlade()
	{
		return new ItemStack[] { new ItemStack(StarWarsItems.magneticStabilizingRing, 2), new ItemStack(StarWarsItems.energyModulationCircuit, 1), new ItemStack(StarWarsItems.cyclingFieldEnergizer, 1), new ItemStack(StarWarsItems.focusingCrystal, 2), new ItemStack(StarWarsItems.diatiumPowerCell, 1), new ItemStack(StarWarsItems.energyGate, 1), new ItemStack(StarWarsItems.powerVortexRing, 1), new ItemStack(StarWarsItems.inertPowerInsulator, 2), new ItemStack(StarWarsItems.hiltMetelAlloy, 8), };
	}

	public static ItemStack[] getItemsForCrossguard()
	{
		return new ItemStack[] { new ItemStack(StarWarsItems.magneticStabilizingRing, 1), new ItemStack(StarWarsItems.energyModulationCircuit, 1), new ItemStack(StarWarsItems.cyclingFieldEnergizer, 1), new ItemStack(StarWarsItems.focusingCrystal, 2), new ItemStack(StarWarsItems.diatiumPowerCell, 1), new ItemStack(StarWarsItems.energyGate, 1), new ItemStack(StarWarsItems.powerVortexRing, 1), new ItemStack(StarWarsItems.inertPowerInsulator, 1), new ItemStack(StarWarsItems.hiltMetelAlloy, 6), };
	}

	public static ItemStack[] getItemsForPike()
	{
		return new ItemStack[] { new ItemStack(StarWarsItems.magneticStabilizingRing, 1), new ItemStack(StarWarsItems.energyModulationCircuit, 1), new ItemStack(StarWarsItems.cyclingFieldEnergizer, 1), new ItemStack(StarWarsItems.focusingCrystal, 1), new ItemStack(StarWarsItems.diatiumPowerCell, 1), new ItemStack(StarWarsItems.energyGate, 1), new ItemStack(StarWarsItems.powerVortexRing, 1), new ItemStack(StarWarsItems.inertPowerInsulator, 1), new ItemStack(StarWarsItems.hiltMetelAlloy, 10), };
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		list.add(TextUtils.makeItalic(LangUtils.translate("saber.tooltip1")));
		list.add(LangUtils.translate("saber.tooltipToggle", TextEffects.COLOR_YELLOW + Keyboard.getKeyName(KeybindRegistry.keyLSToggle.getKeyCode()) + TextEffects.COLOR_GRAY));
		list.add(LangUtils.translate("saber.tooltipDeflect"));
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
	{
		if (stack.stackTagCompound != null && stack.stackTagCompound.getBoolean(nbtBladeOn))
			entityLiving.playSound(Resources.MODID + ":" + "item.lightsaber.swing", 1.0F, 1.0F + (float)MathHelper.getRandomDoubleInRange(Item.itemRand, -0.2D, 0.2D));
		return super.onEntitySwing(entityLiving, stack);

	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World p_77659_2_, EntityPlayer player)
	{
		if (stack.stackTagCompound != null && stack.stackTagCompound.getInteger(nbtBladeTimeout) <= 0 && player.isSneaking())
		{
			if (!stack.stackTagCompound.getBoolean(nbtBladeWaterproof) && player.isInsideOfMaterial(Material.water))
			{
				stack.stackTagCompound.setInteger(nbtBladeTimeout, 10);
				player.playSound(Resources.MODID + ":" + "item.lightsaber.fizz", 1.0F, 1.0F);
			}
			else
			{
				if (stack.stackTagCompound.getBoolean(nbtBladeOn))
					player.playSound(Resources.MODID + ":" + "item.lightsaber.close", 1.0F, 1.0F);
				else
					player.playSound(Resources.MODID + ":" + "item.lightsaber.open", 1.0F, 1.0F);
				stack.stackTagCompound.setBoolean(nbtBladeOn, !stack.stackTagCompound.getBoolean(nbtBladeOn));
				stack.stackTagCompound.setInteger(nbtBladeTimeout, 10);
				if (player.worldObj.isRemote)
					StarWarsMod.network.sendToServer(new MessageSetPlayerHolding(player, stack));
			}
		}
		return super.onItemRightClick(stack, p_77659_2_, player);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase victim, EntityLivingBase attacker)
	{
		if (victim instanceof EntityPlayer && attacker instanceof EntityPlayer)
		{
			EntityPlayer vPlayer = (EntityPlayer)victim;
			EntityPlayer aPlayer = (EntityPlayer)attacker;
			ItemStack vHolding = vPlayer.inventory.getCurrentItem();
			ItemStack aHolding = aPlayer.inventory.getCurrentItem();
			if (vHolding != null && vHolding.getItem() instanceof ItemLightsaber && vPlayer.isBlocking() && aHolding != null && aHolding.getItem() instanceof ItemLightsaber)
			{
				vPlayer.worldObj.playSound(aPlayer.posX, aPlayer.posY, aPlayer.posZ, Resources.MODID + ":" + "item.lightsaber.crash", 1, 1, true);
			}
			if (QuestUtils.hasOnArmor((EntityPlayer)victim, ArmorShadowtrooper.class))
			{

				stack.stackTagCompound.setBoolean(nbtBladeOn, false);
				stack.stackTagCompound.setInteger(nbtBladeTimeout, 10);
				if (attacker instanceof EntityPlayer)
				{
					EntityPlayer player = (EntityPlayer)attacker;
					player.playSound(Resources.MODID + ":" + "item.lightsaber.fizz", 1.0F, 1.0F);
					StarWarsMod.network.sendToServer(new MessageSetPlayerHolding(player, stack));
					return false;
				}
			}
		}

		if (stack.stackTagCompound != null)
		{
			float damage = stack.stackTagCompound.getBoolean(nbtBladeOn) ? 30 : 0;
			victim.attackEntityFrom(StarWarsMod.saberDamageSource, damage);
		}
		else
		{
			victim.attackEntityFrom(StarWarsMod.saberDamageSource, 0);
		}
		return true;
	}

	public static void setupNBT(int hilt, ItemStack stack)
	{
		NBTTagCompound nbt = new NBTTagCompound();

		nbt.setString(nbtHilt, hilts[hilt]);
		String s = hilts[hilt];

		/*
		 * Blade Length (int - 0: short, 1: medium, 2: long)
		 */
		if (s.equals("padawan") || s.equals("yoda"))
			nbt.setInteger(nbtBladeLength, 1);
		else
			nbt.setInteger(nbtBladeLength, 2);

		/*
		 * Blade Color (int - 0xRRGGBB)
		 */
		switch (s)
		{
			case "maul":
			case "dooku":
			case "vader2":
			case "malgus":
			case "doubleSith":
			case "starkiller":
			case "shoto":
			case "inquisitor":
			case "pike":
			case "revan":
			case "ventress":
				nbt.setInteger(nbtBladeColor, 0xFF0000); // red
				break;
			case "quigon":
			case "padawan":
			case "luke2":
			case "yoda":
			case "ahsoka":
				nbt.setInteger(nbtBladeColor, 0x00FF30); // green
				break;
			case "obiwan":
			case "luke1":
			case "kanan":
			case "ezra":
			case "revan2":
			case "plokoon":
				nbt.setInteger(nbtBladeColor, 0x2448DA); // blue
				break;
			case "crossguard":
				nbt.setInteger(nbtBladeColor, 0xFF5200); // red-orange
				break;
			case "darksaber":
				nbt.setInteger(nbtBladeColor, 0x202020); // black
				break;
			case "mace":
				nbt.setInteger(nbtBladeColor, 0xD100FF); // purple
				break;
			default:
				nbt.setInteger(nbtBladeColor, 0xFFFFFF); // blue
				break;
		}

		/*
		 * Blade Skin (int - 0: normal, 1: alternate)
		 */
		nbt.setInteger(nbtHiltSkin, 0);

		/*
		 * Blade Waterproofing (boolean - true: on, false: off)
		 */
		nbt.setBoolean(nbtBladeWaterproof, false);

		/*
		 * Blade Distortion ["kylo rendering"] (boolean)
		 */
		nbt.setBoolean(nbtBladeDistortion, s.equals("crossguard"));

		nbt.setBoolean(nbtBladeOn, false);

		nbt.setInteger(nbtBladeTimeout, 0);

		nbt.setInteger(nbtBlasterTimeout, 0);

		stack.stackTagCompound = nbt;
	}

	public static boolean isOn(ItemStack stack)
	{
		return !(stack == null || !stack.hasTagCompound()) && stack.stackTagCompound.getBoolean(nbtBladeOn);
	}

	public static boolean hasCrystalFor(EntityPlayer player, int color)
	{
		return metaTable.get(color) != null && player.inventory.hasItemStack(new ItemStack(StarWarsItems.lightsaberCrystal, 1, metaTable.get(color)));
	}
}