package com.parzi.starwarsmod.rendering.models.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelWampa extends ModelBase
{
	// fields
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer HornL1;
	ModelRenderer HornL3;
	ModelRenderer HornL2;
	ModelRenderer HornL4;
	ModelRenderer HornR1;
	ModelRenderer HornR2;
	ModelRenderer HornR3;
	ModelRenderer HornR4;
	private float animScale = 0.5F;

	public ModelWampa()
	{
		textureWidth = 128;
		textureHeight = 128;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 6, 8);
		head.setRotationPoint(0F, -2F, 0F);
		head.setTextureSize(128, 128);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 16);
		body.addBox(-4F, 0F, -2F, 11, 16, 6);
		body.setRotationPoint(-1.5F, -4F, -1F);
		body.setTextureSize(128, 128);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		rightarm = new ModelRenderer(this, 0, 39);
		rightarm.addBox(-3F, -2F, -2F, 4, 15, 4);
		rightarm.setRotationPoint(-6.5F, -2F, 0F);
		rightarm.setTextureSize(128, 128);
		rightarm.mirror = true;
		setRotation(rightarm, 0F, 0F, 0F);
		leftarm = new ModelRenderer(this, 17, 39);
		leftarm.addBox(-1F, -2F, -2F, 4, 15, 4);
		leftarm.setRotationPoint(6.5F, -2F, 0F);
		leftarm.setTextureSize(128, 128);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		rightleg = new ModelRenderer(this, 0, 59);
		rightleg.addBox(-2F, 0F, -2F, 5, 12, 5);
		rightleg.setRotationPoint(-3.5F, 12F, -0.5F);
		rightleg.setTextureSize(128, 128);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new ModelRenderer(this, 0, 77);
		leftleg.addBox(-2F, 0F, -2F, 5, 12, 5);
		leftleg.setRotationPoint(2.5F, 12F, -0.5F);
		leftleg.setTextureSize(128, 128);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);
		HornL1 = new ModelRenderer(this, 0, 95);
		HornL1.addBox(0F, 0F, 0F, 2, 2, 6);
		HornL1.setRotationPoint(4F, -7.5F, -2.5F);
		HornL1.setTextureSize(128, 128);
		HornL1.mirror = true;
		setRotation(HornL1, 0F, 0F, 0F);
		HornL3 = new ModelRenderer(this, 0, 104);
		HornL3.addBox(0F, 0F, 0F, 2, 2, 2);
		HornL3.setRotationPoint(3.6F, -7.5F, -4.5F);
		HornL3.setTextureSize(128, 128);
		HornL3.mirror = true;
		setRotation(HornL3, 0F, 0.3141593F, 0F);
		HornL2 = new ModelRenderer(this, 0, 109);
		HornL2.addBox(0F, 0F, 0F, 2, 2, 2);
		HornL2.setRotationPoint(4F, -7.5F, -3F);
		HornL2.setTextureSize(128, 128);
		HornL2.mirror = true;
		setRotation(HornL2, 0F, 0.1570796F, 0F);
		HornL4 = new ModelRenderer(this, 0, 114);
		HornL4.addBox(0F, 0F, 0F, 2, 2, 2);
		HornL4.setRotationPoint(3F, -7.5F, -6F);
		HornL4.setTextureSize(128, 128);
		HornL4.mirror = true;
		setRotation(HornL4, 0F, 0.4712389F, 0F);
		HornR1 = new ModelRenderer(this, 0, 119);
		HornR1.addBox(0F, 0F, 0F, 2, 2, 6);
		HornR1.setRotationPoint(-6F, -7.5F, -3F);
		HornR1.setTextureSize(128, 128);
		HornR1.mirror = true;
		setRotation(HornR1, 0F, 0F, 0F);
		HornR2 = new ModelRenderer(this, 9, 104);
		HornR2.addBox(0F, 0F, 0F, 2, 2, 2);
		HornR2.setRotationPoint(-6F, -7.5F, -3.5F);
		HornR2.setTextureSize(128, 128);
		HornR2.mirror = true;
		setRotation(HornR2, 0F, -0.1570796F, 0F);
		HornR3 = new ModelRenderer(this, 9, 109);
		HornR3.addBox(0F, 0F, 0F, 2, 2, 2);
		HornR3.setRotationPoint(-5.5F, -7.5F, -5F);
		HornR3.setTextureSize(128, 128);
		HornR3.mirror = true;
		setRotation(HornR3, 0F, -0.3141593F, 0F);
		HornR4 = new ModelRenderer(this, 9, 114);
		HornR4.addBox(0F, 0F, 0F, 2, 2, 2);
		HornR4.setRotationPoint(-4.9F, -7.5F, -6.7F);
		HornR4.setTextureSize(128, 128);
		HornR4.mirror = true;
		setRotation(HornR4, 0F, -0.4712389F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		HornL1.render(f5);
		HornL3.render(f5);
		HornL2.render(f5);
		HornL4.render(f5);
		HornR1.render(f5);
		HornR2.render(f5);
		HornR3.render(f5);
		HornR4.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.head.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float)Math.PI);

		this.HornL1.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.HornL1.rotateAngleX = f4 / (180F / (float)Math.PI);

		this.HornL2.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.HornL2.rotateAngleX = f4 / (180F / (float)Math.PI);

		this.HornL3.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.HornL3.rotateAngleX = f4 / (180F / (float)Math.PI);

		this.HornL4.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.HornL4.rotateAngleX = f4 / (180F / (float)Math.PI);

		this.HornR1.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.HornR1.rotateAngleX = f4 / (180F / (float)Math.PI);

		this.HornR2.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.HornR2.rotateAngleX = f4 / (180F / (float)Math.PI);

		this.HornR3.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.HornR3.rotateAngleX = f4 / (180F / (float)Math.PI);

		this.HornR4.rotateAngleY = f3 / (180F / (float)Math.PI);
		this.HornR4.rotateAngleX = f4 / (180F / (float)Math.PI);

		this.rightarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * animScale * f1 * 0.5F;
		this.leftarm.rotateAngleX = MathHelper.cos(f * 0.6662F) * animScale * f1 * 0.5F;
		this.rightarm.rotateAngleZ = 0.0F;
		this.leftarm.rotateAngleZ = 0.0F;

		this.rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * animScale * f1;
		this.leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * animScale * f1;
		this.rightleg.rotateAngleY = 0.0F;
		this.leftleg.rotateAngleY = 0.0F;
	}

}
