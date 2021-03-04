package com.parzivail.pswm.models.armor;

import com.parzivail.pswm.StarWarsMod;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ModelCompressionArmor extends ModelBiped
{
	ModelBiped _model;
	ItemArmor armor;

	public ResourceLocation a;
	public ResourceLocation b;

	public ModelCompressionArmor(float scale, ItemArmor item)
	{
		this._model = new ModelBiped(scale);
		this.armor = item;

		this.a = new ResourceLocation(armor.getArmorTexture(null, null, 2, ""));
		this.b = new ResourceLocation(armor.getArmorTexture(null, null, 1, ""));
	}

	protected void adjustEP(EntityPlayer entity, ItemStack stack, float p)
	{
		this._model.heldItemRight = stack != null ? 1 : 0;
		this._model.isSneak = entity.isSneaking();
		this._model.bipedHead.showModel = entity.inventory.armorInventory[3] != null && entity.inventory.armorInventory[3].getItem() == armor;
		this._model.bipedHeadwear.showModel = entity.inventory.armorInventory[3] != null && entity.inventory.armorInventory[3].getItem() == armor;
		this._model.bipedBody.showModel = entity.inventory.armorInventory[2] != null && entity.inventory.armorInventory[2].getItem() == armor;
		this._model.bipedRightArm.showModel = entity.inventory.armorInventory[2] != null && entity.inventory.armorInventory[2].getItem() == armor;
		this._model.bipedLeftArm.showModel = entity.inventory.armorInventory[2] != null && entity.inventory.armorInventory[2].getItem() == armor;
		this._model.bipedRightLeg.showModel = (entity.inventory.armorInventory[1] != null && entity.inventory.armorInventory[1].getItem() == armor) || (entity.inventory.armorInventory[0] != null && entity.inventory.armorInventory[0].getItem() == armor);
		this._model.bipedLeftLeg.showModel = (entity.inventory.armorInventory[1] != null && entity.inventory.armorInventory[1].getItem() == armor) || (entity.inventory.armorInventory[0] != null && entity.inventory.armorInventory[0].getItem() == armor);
		this._model.isRiding = entity.isRiding();
		this._model.isChild = entity.isChild();
		this._model.onGround = entity.getSwingProgress(p);

		if (stack != null && entity.getItemInUseCount() > 0)
		{
			EnumAction enumaction = stack.getItemUseAction();

			if (enumaction == EnumAction.block)
				this._model.heldItemRight = 3;
			else if (enumaction == EnumAction.bow)
				this._model.aimedBow = true;
		}
	}

	protected void adjustEL(EntityLivingBase entity, ItemStack stack, float p)
	{
		this._model.heldItemRight = stack != null ? 1 : 0;
		this._model.isSneak = entity.isSneaking();
		this._model.bipedHead.showModel = entity.getEquipmentInSlot(3) != null && entity.getEquipmentInSlot(3).getItem() == armor;
		this._model.bipedHeadwear.showModel = true;
		this._model.bipedBody.showModel = entity.getEquipmentInSlot(2) != null && entity.getEquipmentInSlot(2).getItem() == armor;
		this._model.bipedRightArm.showModel = entity.getEquipmentInSlot(2) != null && entity.getEquipmentInSlot(2).getItem() == armor;
		this._model.bipedLeftArm.showModel = entity.getEquipmentInSlot(2) != null && entity.getEquipmentInSlot(2).getItem() == armor;
		this._model.bipedRightLeg.showModel = (entity.getEquipmentInSlot(1) != null && entity.getEquipmentInSlot(1).getItem() == armor) || (entity.getEquipmentInSlot(0) != null && entity.getEquipmentInSlot(0).getItem() == armor);
		this._model.bipedLeftLeg.showModel = (entity.getEquipmentInSlot(1) != null && entity.getEquipmentInSlot(1).getItem() == armor) || (entity.getEquipmentInSlot(0) != null && entity.getEquipmentInSlot(0).getItem() == armor);
		this._model.isRiding = entity.isRiding();
		this._model.isChild = entity.isChild();
		this._model.onGround = entity.getSwingProgress(p);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;
			this.adjustEP(player, player.getHeldItem(), f5);
			StarWarsMod.mc.renderEngine.bindTexture(a);
			this._model.render(entity, f, f1, f2, f3, f4, f5);
			if (player.inventory.armorInventory[1] != null && player.inventory.armorInventory[1].getItem() == armor)
			{
				StarWarsMod.mc.renderEngine.bindTexture(b);
				this._model.render(entity, f, f1, f2, f3, f4, f5);
			}
		}
		else if (entity instanceof EntityLivingBase)
		{
			EntityLivingBase el = (EntityLivingBase)entity;
			this.adjustEL(el, el.getEquipmentInSlot(0), f5);
			StarWarsMod.mc.renderEngine.bindTexture(a);
			this._model.render(entity, f, f1, f2, f3, f4, f5);
			if (el.getEquipmentInSlot(1) != null && el.getEquipmentInSlot(1).getItem() == armor)
			{
				StarWarsMod.mc.renderEngine.bindTexture(b);
				this._model.render(entity, f, f1, f2, f3, f4, f5);
			}
		}
	}

	public void setModel(ModelBiped model)
	{
		this._model = model;
	}
}
