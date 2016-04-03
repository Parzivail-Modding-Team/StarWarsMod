package com.parzivail.pswm.armor;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.models.armor.ModelCompressionArmor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ArmorSequelStormtrooperSilver extends ItemArmor
{
	private String[] names = { "Helmet", "Chestplate", "Leggings", "Boots" };
	@SideOnly(Side.CLIENT)
	ModelCompressionArmor c = new ModelCompressionArmor(0.4f, this);

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemstack, int armorSlot)
	{
		return c;
	}

	public ArmorSequelStormtrooperSilver(ItemArmor.ArmorMaterial par2EnumArmorMaterial, int par3, int par4)
	{
		super(par2EnumArmorMaterial, par3, par4);
		this.setUnlocalizedName(Resources.MODID + "." + "stormtrooperSilver" + this.names[par4] + "_new");
		this.setTextureName(Resources.MODID + ":" + "stormtrooperSilver" + this.names[par4] + "_new");
		this.setCreativeTab(StarWarsMod.SequelStarWarsTab);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if (stack.getItem() == StarWarsMod.stormtrooperSilverNewHelmet || stack.getItem() == StarWarsMod.stormtrooperSilverNewChest || stack.getItem() == StarWarsMod.stormtrooperSilverNewBoots)
			return Resources.MODID + ":" + "textures/models/sequelStormtrooperSilverArmorLayer1.png";
		if (stack.getItem() == StarWarsMod.stormtrooperSilverNewLegs)
			return Resources.MODID + ":" + "textures/models/sequelStormtrooperSilverArmorLayer2.png";
		return "";
	}
}
/*
 * Location: C:\Users\Colby\Downloads\Parzi's Star Wars Mod
 * v1.2.0-dev7.jar!\com\parzi\starwarsmod\armor\ArmorStormtrooper.class Java
 * compiler version: 6 (50.0) JD-Core Version: 0.7.1
 */