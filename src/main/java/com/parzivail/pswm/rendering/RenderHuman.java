package com.parzivail.pswm.rendering;

import com.mojang.authlib.GameProfile;
import com.parzivail.pswm.Resources;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

public class RenderHuman extends RendererLivingEntity
{
	public static ResourceLocation texture = new ResourceLocation(Resources.MODID + ":" + "textures/models/npc/parzi.png");
	public static ResourceLocation textureSteve = new ResourceLocation(Resources.MODID + ":" + "textures/models/npc/steve.png");

	public static ResourceLocation textureBartender = new ResourceLocation(Resources.MODID + ":" + "textures/models/npc/bartender.png");
	public static ResourceLocation textureCorellian = new ResourceLocation(Resources.MODID + ":" + "textures/models/npc/corellian.png");
	public static ResourceLocation textureMerchant = new ResourceLocation(Resources.MODID + ":" + "textures/models/npc/generalMerchant.png");
	public static ResourceLocation textureWeaponsDealer = new ResourceLocation(Resources.MODID + ":" + "textures/models/npc/weaponsDealer.png");

	public static ResourceLocation textureSith = new ResourceLocation(Resources.MODID + ":" + "textures/models/npc/sith.png");

	public static ResourceLocation textureRebelWorker = new ResourceLocation(Resources.MODID + ":" + "textures/models/npc/rebelWorker.png");
	public static ResourceLocation textureRebelTechnician = new ResourceLocation(Resources.MODID + ":" + "textures/models/npc/rebelTechnician.png");

	public static ResourceLocation textureImperialOfficer = new ResourceLocation(Resources.MODID + ":" + "textures/models/npc/imperialOfficer.png");

	public ResourceLocation overrideTexture = null;

	public ModelBiped modelBipedMain;
	public ModelBiped modelArmor;

	public RenderHuman()
	{
		super(new ModelBiped(), 0.5f);
		this.modelBipedMain = (ModelBiped)this.mainModel;
		this.modelArmor = new ModelBiped();
	}

	public RenderHuman(ResourceLocation overrideTexture)
	{
		this();
		this.overrideTexture = overrideTexture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		if (overrideTexture != null)
			return overrideTexture;
		return RenderStaticNpc.texture;
	}

	public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
	{
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		ItemStack itemstack = p_76986_1_.getEquipmentInSlot(0);
		this.modelBipedMain.heldItemRight = itemstack != null ? 1 : 0;

		this.modelBipedMain.isSneak = p_76986_1_.isSneaking();
		double d3 = p_76986_4_ - (double)p_76986_1_.yOffset;

		super.doRender(p_76986_1_, p_76986_2_, d3, p_76986_6_, p_76986_8_, p_76986_9_);
		this.modelBipedMain.aimedBow = false;
		this.modelBipedMain.isSneak = false;
		this.modelBipedMain.heldItemRight = 0;
	}

	@Override
	protected void func_147906_a(Entity p_147906_1_, String p_147906_2_, double p_147906_3_, double p_147906_5_, double p_147906_7_, int p_147906_9_)
	{

	}

	@Override
	protected void renderEquippedItems(EntityLivingBase p_77029_1_, float p_77029_2_)
	{
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		super.renderEquippedItems(p_77029_1_, p_77029_2_);
		super.renderArrowsStuckInEntity(p_77029_1_, p_77029_2_);
		ItemStack itemstack = p_77029_1_.getEquipmentInSlot(3);

		if (itemstack != null)
		{
			GL11.glPushMatrix();
			this.modelBipedMain.bipedHead.postRender(0.0625F);
			float f1;

			if (itemstack.getItem() instanceof ItemBlock)
			{
				IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
				boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D));

				if (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType()))
				{
					f1 = 0.625F;
					GL11.glTranslatef(0.0F, -0.25F, 0.0F);
					GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
					GL11.glScalef(f1, -f1, -f1);
				}

				this.renderManager.itemRenderer.renderItem(p_77029_1_, itemstack, 0);
			}
			else if (itemstack.getItem() == Items.skull)
			{
				f1 = 1.0625F;
				GL11.glScalef(f1, -f1, -f1);
				GameProfile gameprofile = null;

				if (itemstack.hasTagCompound())
				{
					NBTTagCompound nbttagcompound = itemstack.getTagCompound();

					if (nbttagcompound.hasKey("SkullOwner", 10))
					{
						gameprofile = NBTUtil.func_152459_a(nbttagcompound.getCompoundTag("SkullOwner"));
					}
					else if (nbttagcompound.hasKey("SkullOwner", 8) && !StringUtils.isNullOrEmpty(nbttagcompound.getString("SkullOwner")))
					{
						gameprofile = new GameProfile(null, nbttagcompound.getString("SkullOwner"));
					}
				}

				TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, itemstack.getItemDamage(), gameprofile);
			}

			GL11.glPopMatrix();
		}

		float f2;
		float f4;

		ItemStack itemstack1 = p_77029_1_.getEquipmentInSlot(0);

		if (itemstack1 != null)
		{
			GL11.glPushMatrix();
			this.modelBipedMain.bipedRightArm.postRender(0.0625F);
			GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

			IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, IItemRenderer.ItemRenderType.EQUIPPED);
			boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack1, IItemRenderer.ItemRendererHelper.BLOCK_3D));

			if (is3D || itemstack1.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack1.getItem()).getRenderType()))
			{
				f2 = 0.5F;
				GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
				f2 *= 0.75F;
				GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(-f2, -f2, f2);
			}
			else if (itemstack1.getItem() == Items.bow)
			{
				f2 = 0.625F;
				GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
				GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(f2, -f2, f2);
				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			}
			else if (itemstack1.getItem().isFull3D())
			{
				f2 = 0.625F;

				if (itemstack1.getItem().shouldRotateAroundWhenRendering())
				{
					GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef(0.0F, -0.125F, 0.0F);
				}

				GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
				GL11.glScalef(f2, -f2, f2);
				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			}
			else
			{
				f2 = 0.375F;
				GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
				GL11.glScalef(f2, f2, f2);
				GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
			}

			float f3;
			int k;
			float f12;

			if (itemstack1.getItem().requiresMultipleRenderPasses())
			{
				for (k = 0; k < itemstack1.getItem().getRenderPasses(itemstack1.getItemDamage()); ++k)
				{
					int i = itemstack1.getItem().getColorFromItemStack(itemstack1, k);
					f12 = (float)(i >> 16 & 255) / 255.0F;
					f3 = (float)(i >> 8 & 255) / 255.0F;
					f4 = (float)(i & 255) / 255.0F;
					GL11.glColor4f(f12, f3, f4, 1.0F);
					this.renderManager.itemRenderer.renderItem(p_77029_1_, itemstack1, k);
				}
			}
			else
			{
				k = itemstack1.getItem().getColorFromItemStack(itemstack1, 0);
				float f11 = (float)(k >> 16 & 255) / 255.0F;
				f12 = (float)(k >> 8 & 255) / 255.0F;
				f3 = (float)(k & 255) / 255.0F;
				GL11.glColor4f(f11, f12, f3, 1.0F);
				this.renderManager.itemRenderer.renderItem(p_77029_1_, itemstack1, 0);
			}

			GL11.glPopMatrix();
		}
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
	{
		this.doRender((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
	{
		this.doRender((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
	{
		ItemStack itemstack = p_77032_1_.getEquipmentInSlot(4 - p_77032_2_);

		if (itemstack != null)
		{
			Item item = itemstack.getItem();

			if (item instanceof ItemArmor)
			{
				ItemArmor itemarmor = (ItemArmor)item;
				this.bindTexture(RenderBiped.getArmorResource(p_77032_1_, itemstack, p_77032_2_, null));
				ModelBiped modelbiped = this.modelArmor;
				modelbiped.bipedHead.showModel = true;
				modelbiped.bipedHeadwear.showModel = p_77032_2_ == 0;
				modelbiped.bipedBody.showModel = p_77032_2_ == 1 || p_77032_2_ == 2;
				modelbiped.bipedRightArm.showModel = p_77032_2_ == 1;
				modelbiped.bipedLeftArm.showModel = p_77032_2_ == 1;
				modelbiped.bipedRightLeg.showModel = p_77032_2_ == 2 || p_77032_2_ == 3;
				modelbiped.bipedLeftLeg.showModel = p_77032_2_ == 2 || p_77032_2_ == 3;
				modelbiped = ForgeHooksClient.getArmorModel(p_77032_1_, itemstack, p_77032_2_, modelbiped);
				this.setRenderPassModel(modelbiped);
				modelbiped.onGround = this.mainModel.onGround;
				modelbiped.isRiding = this.mainModel.isRiding;
				modelbiped.isChild = this.mainModel.isChild;

				//Move outside if to allow for more then just CLOTH
				int j = itemarmor.getColor(itemstack);
				if (j != -1)
				{
					float f1 = (float)(j >> 16 & 255) / 255.0F;
					float f2 = (float)(j >> 8 & 255) / 255.0F;
					float f3 = (float)(j & 255) / 255.0F;
					GL11.glColor3f(f1, f2, f3);

					if (itemstack.isItemEnchanted())
					{
						return 31;
					}

					return 16;
				}

				GL11.glColor3f(1.0F, 1.0F, 1.0F);

				if (itemstack.isItemEnchanted())
				{
					return 15;
				}

				return 1;
			}
		}

		return -1;
	}
}
