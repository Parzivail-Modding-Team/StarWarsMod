package com.parzivail.pswm.models.blocks.hoth;

import com.parzivail.util.ui.GFX;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * ConsoleHoth1 - Weaston
 * Created using Tabula 4.1.1
 */
public class ModelConsoleHoth1 extends ModelBase
{
	public ModelRenderer shape1;
	public ModelRenderer shape1_1;
	public ModelRenderer shape1_2;
	public ModelRenderer shape1_3;
	public ModelRenderer shape1_4;
	public ModelRenderer shape1_5;
	public ModelRenderer shape1_6;
	public ModelRenderer shape1_7;
	public ModelRenderer shape1_8;
	public ModelRenderer shape1_9;
	public ModelRenderer shape1_10;
	public ModelRenderer shape1_11;
	public ModelRenderer shape1_12;

	public ModelConsoleHoth1()
	{
		this.textureWidth = 512;
		this.textureHeight = 512;
		this.shape1_7 = new ModelRenderer(this, 0, 148);
		this.shape1_7.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_7.addBox(-12.0F, -12.16F, 1.01F, 4, 1, 4, 0.0F);
		this.setRotateAngle(shape1_7, 0.5410520681182421F, 0.0F, 0.0F);
		this.shape1 = new ModelRenderer(this, 0, 0);
		this.shape1.setRotationPoint(0.0F, 17.0F, 0.0F);
		this.shape1.addBox(-16.0F, -7.0F, -2.0F, 7, 14, 4, 0.0F);
		this.shape1_2 = new ModelRenderer(this, 0, 61);
		this.shape1_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_2.addBox(-16.5F, -10.0F, -6.0F, 33, 3, 9, 0.0F);
		this.shape1_9 = new ModelRenderer(this, 91, 0);
		this.shape1_9.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_9.addBox(-4.0F, -12.16F, 1.01F, 2, 1, 2, 0.0F);
		this.setRotateAngle(shape1_9, 0.5410520681182421F, 0.0F, 0.0F);
		this.shape1_4 = new ModelRenderer(this, 0, 103);
		this.shape1_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_4.addBox(-16.5F, -11.66F, 0.01F, 33, 3, 5, 0.0F);
		this.setRotateAngle(shape1_4, 0.5410520681182421F, 0.0F, 0.0F);
		this.shape1_8 = new ModelRenderer(this, 0, 164);
		this.shape1_8.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_8.addBox(-7.0F, -12.16F, 1.01F, 2, 1, 4, 0.0F);
		this.setRotateAngle(shape1_8, 0.5410520681182421F, 0.0F, 0.0F);
		this.shape1_1 = new ModelRenderer(this, 0, 34);
		this.shape1_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_1.addBox(9.0F, -7.0F, -2.0F, 7, 14, 4, 0.0F);
		this.shape1_10 = new ModelRenderer(this, 116, 0);
		this.shape1_10.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_10.addBox(-4.0F, -12.16F, 4.01F, 2, 1, 1, 0.0F);
		this.setRotateAngle(shape1_10, 0.5410520681182421F, 0.0F, 0.0F);
		this.shape1_12 = new ModelRenderer(this, 35, 26);
		this.shape1_12.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_12.addBox(-16.0F, -40.0F, -0.5F, 32, 3, 3, 0.0F);
		this.shape1_3 = new ModelRenderer(this, 0, 84);
		this.shape1_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_3.addBox(-16.5F, -13.0F, -1.0F, 33, 3, 4, 0.0F);
		this.shape1_6 = new ModelRenderer(this, 0, 132);
		this.shape1_6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_6.addBox(4.0F, -12.16F, 1.01F, 9, 1, 4, 0.0F);
		this.setRotateAngle(shape1_6, 0.5410520681182421F, 0.0F, 0.0F);
		this.shape1_11 = new ModelRenderer(this, 174, 0);
		this.shape1_11.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_11.addBox(-15.5F, -37.0F, 0.0F, 31, 24, 2, 0.0F);
		this.shape1_5 = new ModelRenderer(this, 0, 120);
		this.shape1_5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_5.addBox(-16.5F, -11.66F, 4.83F, 33, 3, 1, 0.0F);
		this.setRotateAngle(shape1_5, 0.5410520681182421F, 0.0F, 0.0F);
		this.shape1.addChild(this.shape1_7);
		this.shape1.addChild(this.shape1_2);
		this.shape1.addChild(this.shape1_9);
		this.shape1.addChild(this.shape1_4);
		this.shape1.addChild(this.shape1_8);
		this.shape1.addChild(this.shape1_1);
		this.shape1.addChild(this.shape1_10);
		this.shape1.addChild(this.shape1_12);
		this.shape1.addChild(this.shape1_3);
		this.shape1.addChild(this.shape1_6);
		this.shape1.addChild(this.shape1_11);
		this.shape1.addChild(this.shape1_5);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		this.shape1.render(f5);

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glTranslatef(0, 0, -0.01f);
		drawGlass();
		GL11.glTranslatef(0, 0, 0.12f);
		drawGlass();

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public void drawGlass()
	{
		GL11.glPushMatrix();
		GL11.glColor3f(0.8f, 0.8f, 0.8f);
		GL11.glLineWidth(2);
		GFX.drawLine(-0.6f, 0.2f, -0.6f, -1f);
		GFX.drawLine(-0.4f, 0.2f, -0.4f, -1f);
		GFX.drawLine(-0.2f, 0.2f, -0.2f, -1f);
		GFX.drawLine(-0f, 0.2f, -0f, -1f);
		GFX.drawLine(0.15f, 0.2f, 0.15f, -1f);
		GFX.drawLine(0.27f, 0.2f, 0.27f, -1f);
		GFX.drawLine(0.36f, 0.2f, 0.36f, -1f);
		GFX.drawLine(0.46f, 0.2f, 0.46f, -1f);
		GFX.drawLine(0.56f, 0.2f, 0.56f, -1f);
		GFX.drawLine(0.65f, 0.2f, 0.65f, -1f);

		GL11.glColor3f(1, 1, 1);

		GL11.glLineWidth(2);
		GFX.drawLine(-0.7f, -0.9f, 0.46f, -0.07f);

		GL11.glLineWidth(3.5f);
		GFX.drawCircle(0, -0.25f, 0.65f);

		GL11.glLineWidth(2);
		GL11.glScalef(1.2f, 1.2f, 0);
		GL11.glTranslatef(-0.1f, 0, 0);
		GFX.drawCircle(0.46f, -0.075f, 0.1f);
		GFX.drawCircle(0.33f, -0.15f, 0.3f);
		GFX.drawCircle(0.36f, -0.12f, 0.26f);
		GFX.drawCircle(0.37f, -0.12f, 0.2f);
		GFX.drawCircle(0.47f, -0.08f, 0.145f);
		GFX.drawCircle(0.42f, -0.1f, 0.17f);
		GL11.glPopMatrix();
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
