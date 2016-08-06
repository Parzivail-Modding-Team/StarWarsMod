package com.parzivail.pswm.rendering;

import com.parzivail.pswm.Resources;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderHothSign extends TileEntitySignRenderer
{
	public static ResourceLocation texture = new ResourceLocation(Resources.MODID + ":" + "textures/blocks/hothSign.png");
	private final ModelSign modelSign = new ModelSign();

	public void renderTileEntityAt(TileEntitySign p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_)
	{
		Block block = p_147500_1_.getBlockType();
		GL11.glPushMatrix();
		float f1 = 0.6666667F;
		float f3;

		if (block == Blocks.standing_sign)
		{
			GL11.glTranslatef((float)p_147500_2_ + 0.5F, (float)p_147500_4_ + 0.75F * f1, (float)p_147500_6_ + 0.5F);
			float f2 = (float)(p_147500_1_.getBlockMetadata() * 360) / 16.0F;
			GL11.glRotatef(-f2, 0.0F, 1.0F, 0.0F);
			this.modelSign.signStick.showModel = true;
		}
		else
		{
			int j = p_147500_1_.getBlockMetadata();
			f3 = 0.0F;

			if (j == 2)
			{
				f3 = 180.0F;
			}

			if (j == 4)
			{
				f3 = 90.0F;
			}

			if (j == 5)
			{
				f3 = -90.0F;
			}

			GL11.glTranslatef((float)p_147500_2_ + 0.5F, (float)p_147500_4_ + 0.75F * f1, (float)p_147500_6_ + 0.5F);
			GL11.glRotatef(-f3, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(0.0F, -0.3125F, -0.4375F);
			this.modelSign.signStick.showModel = false;
		}

		this.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glScalef(f1, -f1, -f1);
		this.modelSign.renderSign();
		GL11.glPopMatrix();
		FontRenderer fontrenderer = this.func_147498_b();
		f3 = 0.016666668F * f1;
		GL11.glTranslatef(0.0F, 0.5F * f1, 0.07F * f1);
		GL11.glScalef(f3, -f3, f3);
		GL11.glNormal3f(0.0F, 0.0F, -1.0F * f3);
		GL11.glDepthMask(false);
		byte b0 = 0;

		for (int i = 0; i < p_147500_1_.signText.length; ++i)
		{
			String s = p_147500_1_.signText[i];

			if (i == p_147500_1_.lineBeingEdited)
			{
				s = "> " + s + " <";
				fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, i * 10 - p_147500_1_.signText.length * 5, b0);
			}
			else
			{
				fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, i * 10 - p_147500_1_.signText.length * 5, b0);
			}
		}

		GL11.glDepthMask(true);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}
}
