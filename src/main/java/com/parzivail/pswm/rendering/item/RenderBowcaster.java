package com.parzivail.pswm.rendering.item;

import org.lwjgl.opengl.GL11;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.models.weapons.ModelBowcaster;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class RenderBowcaster implements IItemRenderer
{
    private static ResourceLocation texture = new ResourceLocation(Resources.MODID, "textures/models/weapons/bowcaster.png");
    
	private ModelBowcaster model;

	public RenderBowcaster()
	{
		this.model = new ModelBowcaster();
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
				GL11.glScalef(0.055F, -0.055F, 0.055F);
				GL11.glTranslatef(-1, -4f, 0f);
				GL11.glRotatef(25, 0, 0, 1);
				GL11.glRotatef(180, 0, 1, 0);
				GL11.glTranslatef(5, -2, 0);
				GL11.glScalef(-1, 1, -1);
				this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
				GL11.glEnable(GL11.GL_CULL_FACE);
				GL11.glPopMatrix();
				break;
			case EQUIPPED:
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glScalef(0.08F, -0.08F, 0.08F);
				GL11.glRotatef(-40, 0, 1, 0);
				GL11.glRotatef(22, 0, 0, 1);
				GL11.glTranslatef(25, 1, 0);
				GL11.glRotatef(90, 0, 0, -1);
				GL11.glTranslatef(0, -22, 0);
				this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
				GL11.glEnable(GL11.GL_CULL_FACE);
				GL11.glPopMatrix();
				break;
			case EQUIPPED_FIRST_PERSON:
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glScalef(0.055F, -0.055F, 0.055F);
				GL11.glTranslatef(8, -23, 9);
				GL11.glRotatef(90, 0, 0, 1);
				GL11.glRotatef(20, 1, 0, 0);
				GL11.glTranslatef(0, 0, -4.5f);
				GL11.glRotatef(90, 0, 0, -1);
				GL11.glRotatef(30, 0, 1, 0);
				GL11.glScalef(-1, 1, -1);
				this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
				GL11.glEnable(GL11.GL_CULL_FACE);
				GL11.glPopMatrix();
				break;
			default:
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glScalef(0.06F, -0.06F, 0.06F);
				GL11.glTranslatef(0, -8, 0);
				GL11.glScalef(1, 1, -1);
				GL11.glRotatef(90, 0, 1, 0);
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