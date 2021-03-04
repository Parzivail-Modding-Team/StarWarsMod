package com.parzivail.pswm.armor;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.models.armor.ModelJetpack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ArmorBobaJetpack extends ItemArmor
{
	private String[] names = { "Helmet", "JetpackChestplate", "Leggings", "Boots" };
	@SideOnly(Side.CLIENT)
	ModelJetpack h;

	public ArmorBobaJetpack(ItemArmor.ArmorMaterial par2EnumArmorMaterial, int par3, int par4)
	{
		super(par2EnumArmorMaterial, par3, par4);
		this.setUnlocalizedName(Resources.MODID + "." + "boba" + this.names[par4]);
		this.setTextureName(Resources.MODID + ":" + "boba" + this.names[par4]);
		this.setCreativeTab(StarWarsMod.StarWarsTab);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return Resources.MODID + ":" + "textures/models/jetpack.png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemstack, int armorSlot)
	{
		if (itemstack.getItem() == StarWarsItems.bobaJetpackChest)
		{
			if (h == null)
				h = new ModelJetpack();
			return h;
		}
		return null;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		player.capabilities.allowFlying = true;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int i, boolean b)
	{
		if (player instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer)player;
			p.capabilities.allowFlying = p.capabilities.isCreativeMode || p.inventory.armorInventory[2] != null && p.inventory.armorInventory[2].getItem() instanceof ArmorBobaJetpack;
			if (!p.capabilities.allowFlying)
				p.capabilities.isFlying = false;
		}
	}
}
