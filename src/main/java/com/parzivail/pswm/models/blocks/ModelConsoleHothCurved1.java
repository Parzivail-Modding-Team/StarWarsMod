package com.parzivail.pswm.models.blocks;

import com.parzivail.pswm.entities.EntityTilePassthrough;
import com.parzivail.pswm.tileentities.TileEntityConsoleHoth1;
import com.parzivail.util.ui.ShaderHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ConsoleHothCurved1 - Weaston
 * Created using Tabula 4.1.1
 */
public class ModelConsoleHothCurved1 extends ModelBase
{
	public ModelRenderer shape1;
	public ModelRenderer Light;
	public ModelRenderer Light_1;
	public ModelRenderer Light_2;
	public ModelRenderer Light_3;
	public ModelRenderer Light_4;
	public ModelRenderer Light_5;
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
	public ModelRenderer shape1_13;
	public ModelRenderer shape1_14;
	public ModelRenderer shape1_15;
	public ModelRenderer shape1_16;

	public ModelConsoleHothCurved1()
	{
		this.textureWidth = 512;
		this.textureHeight = 512;
		this.Light_2 = new ModelRenderer(this, 90, 59);
		this.Light_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Light_2.addBox(3.7F, -2.0F, 1.6F, 1, 1, 1, 0.0F);
		this.Light_3 = new ModelRenderer(this, 110, 59);
		this.Light_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Light_3.addBox(5.5F, -2.0F, 1.6F, 1, 1, 1, 0.0F);
		this.shape1_15 = new ModelRenderer(this, 52, 24);
		this.shape1_15.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_15.addBox(-7.0F, 2.0F, 2.0F, 14, 2, 1, 0.0F);
		this.shape1_10 = new ModelRenderer(this, 110, 0);
		this.shape1_10.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_10.addBox(-7.0F, -9.0F, 2.0F, 3, 10, 1, 0.0F);
		this.shape1_1 = new ModelRenderer(this, 0, 52);
		this.shape1_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_1.addBox(-8.0F, 7.0F, -6.0F, 3, 14, 9, 0.0F);
		this.shape1_3 = new ModelRenderer(this, 0, 122);
		this.shape1_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_3.addBox(-8.0F, 5.6F, -7.32F, 16, 3, 10, 0.0F);
		this.setRotateAngle(shape1_3, 0.20943951023931953F, 0.0F, 0.0F);
		this.shape1_16 = new ModelRenderer(this, 50, 36);
		this.shape1_16.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_16.addBox(-7.0F, -9.71F, 8.6F, 14, 4, 4, 0.0F);
		this.setRotateAngle(shape1_16, 0.7853981633974483F, 0.0F, 0.0F);
		this.shape1_5 = new ModelRenderer(this, 0, 175);
		this.shape1_5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_5.addBox(-8.0F, -21.0F, -1.0F, 16, 7, 4, 0.0F);
		this.shape1_8 = new ModelRenderer(this, 50, 0);
		this.shape1_8.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_8.addBox(3.5F, 5.1F, -6.82F, 4, 1, 8, 0.0F);
		this.setRotateAngle(shape1_8, 0.20943951023931953F, 0.0F, 0.0F);
		this.shape1_11 = new ModelRenderer(this, 125, 0);
		this.shape1_11.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_11.addBox(-2.0F, -8.0F, 1.6F, 5, 8, 1, 0.0F);
		this.shape1_12 = new ModelRenderer(this, 146, 0);
		this.shape1_12.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_12.addBox(-1.5F, -7.0F, 1.1F, 4, 1, 1, 0.0F);
		this.shape1_2 = new ModelRenderer(this, 0, 86);
		this.shape1_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_2.addBox(5.0F, 7.0F, -6.0F, 3, 14, 9, 0.0F);
		this.shape1_6 = new ModelRenderer(this, 0, 200);
		this.shape1_6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_6.addBox(-8.0F, -10.61F, 9.2F, 16, 6, 4, 0.0F);
		this.setRotateAngle(shape1_6, 0.7853981633974483F, 0.0F, 0.0F);
		this.shape1_4 = new ModelRenderer(this, 0, 150);
		this.shape1_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_4.addBox(-5.0F, 7.0F, -6.0F, 10, 3, 9, 0.0F);
		this.shape1_13 = new ModelRenderer(this, 165, 0);
		this.shape1_13.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_13.addBox(-1.5F, -6.0F, 1.1F, 1, 4, 1, 0.0F);
		this.shape1_14 = new ModelRenderer(this, 180, 0);
		this.shape1_14.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_14.addBox(1.5F, -6.0F, 1.1F, 1, 4, 1, 0.0F);
		this.Light_1 = new ModelRenderer(this, 70, 59);
		this.Light_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Light_1.addBox(5.5F, -5.0F, 1.6F, 1, 2, 1, 0.0F);
		this.Light = new ModelRenderer(this, 53, 59);
		this.Light.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Light.addBox(3.7F, -5.0F, 1.6F, 1, 2, 1, 0.0F);
		this.shape1_7 = new ModelRenderer(this, 0, 220);
		this.shape1_7.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_7.addBox(-7.5F, 5.1F, -6.82F, 10, 1, 8, 0.0F);
		this.setRotateAngle(shape1_7, 0.20943951023931953F, 0.0F, 0.0F);
		this.Light_4 = new ModelRenderer(this, 130, 59);
		this.Light_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Light_4.addBox(5.5F, 0.0F, 1.6F, 1, 1, 1, 0.0F);
		this.shape1_9 = new ModelRenderer(this, 82, 0);
		this.shape1_9.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1_9.addBox(-3.0F, -9.0F, 2.0F, 10, 10, 1, 0.0F);
		this.shape1 = new ModelRenderer(this, 0, 0);
		this.shape1.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.shape1.addBox(-8.0F, -21.0F, 3.0F, 16, 42, 5, 0.0F);
		this.Light_5 = new ModelRenderer(this, 150, 59);
		this.Light_5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Light_5.addBox(3.7F, 0.0F, 1.6F, 1, 1, 1, 0.0F);
		this.shape1.addChild(this.shape1_15);
		this.shape1.addChild(this.shape1_10);
		this.shape1.addChild(this.shape1_1);
		this.shape1.addChild(this.shape1_3);
		this.shape1.addChild(this.shape1_16);
		this.shape1.addChild(this.shape1_5);
		this.shape1.addChild(this.shape1_8);
		this.shape1.addChild(this.shape1_11);
		this.shape1.addChild(this.shape1_12);
		this.shape1.addChild(this.shape1_2);
		this.shape1.addChild(this.shape1_6);
		this.shape1.addChild(this.shape1_4);
		this.shape1.addChild(this.shape1_13);
		this.shape1.addChild(this.shape1_14);
		this.shape1.addChild(this.shape1_7);
		this.shape1.addChild(this.shape1_9);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		if (entity instanceof EntityTilePassthrough && ((EntityTilePassthrough)entity).tileEntity instanceof TileEntityConsoleHoth1)
		{
			TileEntityConsoleHoth1 console = (TileEntityConsoleHoth1)((EntityTilePassthrough)entity).tileEntity;

			ShaderHelper.setColor(console.color1);
			ShaderHelper.useShader(ShaderHelper.glowSolid);
			this.Light.render(f5);
			ShaderHelper.releaseShader();

			ShaderHelper.setColor(console.color2);
			ShaderHelper.useShader(ShaderHelper.glowSolid);
			this.Light_1.render(f5);
			ShaderHelper.releaseShader();

			ShaderHelper.setColor(console.color3);
			ShaderHelper.useShader(ShaderHelper.glowSolid);
			this.Light_2.render(f5);
			ShaderHelper.releaseShader();

			ShaderHelper.setColor(console.color4);
			ShaderHelper.useShader(ShaderHelper.glowSolid);
			this.Light_3.render(f5);
			ShaderHelper.releaseShader();

			ShaderHelper.setColor(console.color5);
			ShaderHelper.useShader(ShaderHelper.glowSolid);
			this.Light_4.render(f5);
			ShaderHelper.releaseShader();

			ShaderHelper.setColor(console.color6);
			ShaderHelper.useShader(ShaderHelper.glowSolid);
			this.Light_5.render(f5);
			ShaderHelper.releaseShader();
		}

		this.shape1.render(f5);
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
