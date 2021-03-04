package com.parzivail.pswm.rendering;

import com.mojang.authlib.GameProfile;
import com.parzivail.pswm.Resources;
import com.parzivail.pswm.mobs.MobEwok;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

public class RenderEwok extends RenderLiving
{
	public static ResourceLocation texture1 = new ResourceLocation(Resources.MODID, "textures/models/ewok.png");
	public static ResourceLocation texture2 = new ResourceLocation(Resources.MODID, "textures/models/ewok2.png");
	public static ResourceLocation texture3 = new ResourceLocation(Resources.MODID, "textures/models/ewok3.png");
	public static ResourceLocation texture = new ResourceLocation(Resources.MODID, "textures/models/ewok.png");

	public RenderEwok(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		if (entity instanceof MobEwok)
		{
			MobEwok ewok = (MobEwok)entity;
			switch (ewok.getDataWatcher().getWatchableObjectInt(25))
			{
				case 0:
					return texture1;
				case 1:
					return texture2;
				case 2:
					return texture3;
			}
		}
		return texture;
	}

	@Override
	protected void renderEquippedItems(EntityLivingBase p_77029_1_, float p_77029_2_)
	{
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		super.renderEquippedItems(p_77029_1_, p_77029_2_);
		ItemStack heldItem = p_77029_1_.getHeldItem();
		ItemStack equippedItem = p_77029_1_.getEquipmentInSlot(3);
		if (equippedItem != null)
		{
			GL11.glPushMatrix();
			Item item = equippedItem.getItem();
			IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(equippedItem, IItemRenderer.ItemRenderType.EQUIPPED);
			boolean is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, equippedItem, IItemRenderer.ItemRendererHelper.BLOCK_3D);
			if (item instanceof ItemBlock)
			{
				if (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(item).getRenderType()))
				{
					float f1 = 0.625F;
					GL11.glTranslatef(0.0F, -0.25F, 0.0F);
					GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(f1, -f1, -f1);
				}
				this.renderManager.itemRenderer.renderItem(p_77029_1_, equippedItem, 0);
			}
			else if (item == Items.skull)
			{
				float f1 = 1.0625F;
				GL11.glScalef(f1, -f1, -f1);
				GameProfile gameprofile = null;
				if (equippedItem.hasTagCompound())
				{
					NBTTagCompound nbttagcompound = equippedItem.getTagCompound();
					if (nbttagcompound.hasKey("SkullOwner", 10))
						gameprofile = NBTUtil.func_152459_a(nbttagcompound.getCompoundTag("SkullOwner"));
					else if (nbttagcompound.hasKey("SkullOwner", 8) && !StringUtils.isNullOrEmpty(nbttagcompound.getString("SkullOwner")))
						gameprofile = new GameProfile(null, nbttagcompound.getString("SkullOwner"));
				}
				TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, equippedItem.getItemDamage(), gameprofile);
			}
			GL11.glPopMatrix();
		}
		if (heldItem != null && heldItem.getItem() != null)
		{
			Item item = heldItem.getItem();
			GL11.glPushMatrix();
			if (this.mainModel.isChild)
			{
				float f1 = 0.5F;
				GL11.glTranslatef(0.0F, 0.625F, 0.0F);
				GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
				GL11.glScalef(f1, f1, f1);
			}
			GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
			IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(heldItem, IItemRenderer.ItemRenderType.EQUIPPED);
			boolean is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, heldItem, IItemRenderer.ItemRendererHelper.BLOCK_3D);
			if (item instanceof ItemBlock && (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(item).getRenderType())))
			{
				float f1 = 0.5F;
				GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
				f1 *= 0.75F;
				GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(-f1, -f1, f1);
			}
			else if (item == Items.bow)
			{
				float f1 = 0.625F;
				GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
				GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(f1, -f1, f1);
				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			}
			else if (item.isFull3D())
			{
				float f1 = 0.625F;
				if (item.shouldRotateAroundWhenRendering())
				{
					GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef(0.0F, -0.125F, 0.0F);
				}
				GL11.glScalef(f1, -f1, f1);
				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			}
			else
			{
				float f1 = 0.375F;
				GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
				GL11.glScalef(f1, f1, f1);
				GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
			}
			GL11.glTranslatef(0.5F, 0.25F, -1.0F);
			if (heldItem.getItem().requiresMultipleRenderPasses())
				for (int i = 0; i < heldItem.getItem().getRenderPasses(heldItem.getItemDamage()); i++)
				{
					int j = heldItem.getItem().getColorFromItemStack(heldItem, i);
					float f5 = (j >> 16 & 0xFF) / 255.0F;
					float f2 = (j >> 8 & 0xFF) / 255.0F;
					float f3 = (j & 0xFF) / 255.0F;
					GL11.glColor4f(f5, f2, f3, 1.0F);
					this.renderManager.itemRenderer.renderItem(p_77029_1_, heldItem, i);
				}
			int i = heldItem.getItem().getColorFromItemStack(heldItem, 0);
			float f4 = (i >> 16 & 0xFF) / 255.0F;
			float f5 = (i >> 8 & 0xFF) / 255.0F;
			float f2 = (i & 0xFF) / 255.0F;
			GL11.glColor4f(f4, f5, f2, 1.0F);
			this.renderManager.itemRenderer.renderItem(p_77029_1_, heldItem, 0);
			GL11.glPopMatrix();
		}
	}
}
