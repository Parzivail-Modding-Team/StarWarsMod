package com.parzivail.pswm.rendering.item;

import org.lwjgl.opengl.GL11;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.models.weapons.ModelGamS;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class RenderGamS implements IItemRenderer
{
	private static ResourceLocation texture = new ResourceLocation(Resources.MODID, "textures/models/weapons/gamS.png");

	private ModelGamS model;

	public RenderGamS()
	{
		this.model = new ModelGamS();
	}

	@Override
	public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
	{
		return true;
	}

	@Override
	public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		switch (type)
		{
			case INVENTORY:
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glScalef(0.07F, -0.07F, 0.07F);
				GL11.glTranslatef(-18.5f, -7f, -4f);
				GL11.glRotatef(25, 0, 0, 1);
				GL11.glRotatef(180, 0, 1, 0);
				GL11.glTranslatef(-10, 0, 0);
				GL11.glScalef(-1, 1, -1);
				this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
				GL11.glEnable(GL11.GL_CULL_FACE);
				GL11.glPopMatrix();
				break;
			case EQUIPPED:
				if (data[1] instanceof EntityPlayer)
				{
					GL11.glPushMatrix();
					GL11.glDisable(GL11.GL_CULL_FACE);
					GL11.glScalef(0.12F, -0.12F, 0.12F);
					GL11.glRotatef(-40, 0, 1, 0);
					GL11.glRotatef(22, 0, 0, 1);
					if (data[1] instanceof EntityPlayer && ((EntityPlayer)data[1]).isBlocking())
					{
						GL11.glRotatef(30, 0, 1, 0);
						GL11.glTranslatef(-4, 0, 3);
					}
					GL11.glTranslatef(8, 6, 2);
					GL11.glRotatef(90, 0, 0, -1);
					GL11.glTranslatef(15, -11, 0);
					GL11.glRotatef(90, 0, 0, 1);
					GL11.glRotatef(-90, 1, 0, 0);
					this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
					GL11.glEnable(GL11.GL_CULL_FACE);
					GL11.glPopMatrix();
				}
				else
				{
					GL11.glPushMatrix();
					GL11.glDisable(GL11.GL_CULL_FACE);
					GL11.glScalef(0.08F, -0.08F, 0.08F);
					GL11.glRotatef(90, 1, 0, 0);
					GL11.glRotatef(-100, 0, 1, 0);
					GL11.glRotatef(10, 0, 0, 1);
					GL11.glRotatef(135, 1, 0, 0);
					GL11.glScalef(-1, 1, 1);
					GL11.glTranslatef(-18, -5, -9);
					this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
					GL11.glEnable(GL11.GL_CULL_FACE);
					GL11.glPopMatrix();
				}
				break;
			case EQUIPPED_FIRST_PERSON:
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glScalef(0.055F, -0.055F, 0.055F);
				GL11.glTranslatef(8, -23, 9);
				GL11.glRotatef(90, 0, 0, 1);
				GL11.glTranslatef(-10, 10, 4f);
				GL11.glRotatef(130, 1, 0, 0);
				if (data[1] instanceof EntityPlayer && ((EntityPlayer)data[1]).isBlocking())
				{
					GL11.glRotatef(-10, 0, 1, 0);
					GL11.glTranslatef(8, 0, -15);
					GL11.glRotatef(-40, 0, 1, 0);
					GL11.glTranslatef(8, 0, 0);
					GL11.glRotatef(-45, 1, 0, 0);
				}
				this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
				GL11.glEnable(GL11.GL_CULL_FACE);
				GL11.glPopMatrix();
				break;
			default:
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glScalef(0.07F, -0.07F, 0.07F);
				GL11.glRotatef(90, 0, 0, 1);
				GL11.glTranslatef(-25, -2, -2);
				this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
				GL11.glEnable(GL11.GL_CULL_FACE);
				GL11.glPopMatrix();
				break;
		}
		GL11.glPopMatrix();
	}

	@Override
	public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
	{
		return true;
	}
}