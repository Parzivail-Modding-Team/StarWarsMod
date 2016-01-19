package com.parzivail.pswm.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMV extends ModelBase
{
	ModelRenderer mainBody;
	ModelRenderer base;
	ModelRenderer bodyPart1;
	ModelRenderer controlRod1;
	ModelRenderer controlRod2;
	ModelRenderer controlRod3;
	ModelRenderer controlRod4;
	ModelRenderer bodyRod1;
	public ModelRenderer windVaneRod1;
	public ModelRenderer windVane1;
	public ModelRenderer windVaneRod2;
	public ModelRenderer windVane2;
	ModelRenderer bodyRodHolder1;
	ModelRenderer bodyRodHolder2;
	ModelRenderer bodyRodHolder3;
	ModelRenderer bodyRodHolder4;
	ModelRenderer bodyPart2;
	ModelRenderer bodyRodHolder5;
	ModelRenderer bodyRodHolder6;
	ModelRenderer bodyRodHolder7;
	ModelRenderer bodyRodHolder8;
	ModelRenderer bodyBase;
	ModelRenderer bodyPart3;
	ModelRenderer bodyPart4;
	ModelRenderer top1;
	ModelRenderer top2;
	ModelRenderer top3;

	public ModelMV()
	{
		this.textureWidth = 64;
		this.textureHeight = 128;
		this.mainBody = new ModelRenderer(this, 0, 0);
		this.mainBody.addBox(0.0F, 0.0F, 0.0F, 6, 28, 6);
		this.mainBody.setRotationPoint(-3.0F, -5.0F, -3.0F);
		this.mainBody.setTextureSize(64, 32);
		this.mainBody.mirror = true;
		this.setRotation(this.mainBody, 0.0F, 0.0F, 0.0F);
		this.base = new ModelRenderer(this, 0, 43);
		this.base.addBox(0.0F, 0.0F, 0.0F, 16, 2, 16);
		this.base.setRotationPoint(-8.0F, 22.0F, -8.0F);
		this.base.setTextureSize(64, 32);
		this.base.mirror = true;
		this.setRotation(this.base, 0.0F, 0.0F, 0.0F);
		this.bodyPart1 = new ModelRenderer(this, 0, 61);
		this.bodyPart1.addBox(0.0F, 0.0F, 0.0F, 10, 4, 10);
		this.bodyPart1.setRotationPoint(-5.0F, -4.0F, -5.0F);
		this.bodyPart1.setTextureSize(64, 32);
		this.bodyPart1.mirror = true;
		this.setRotation(this.bodyPart1, 0.0F, 0.0F, 0.0F);
		this.controlRod1 = new ModelRenderer(this, 32, 0);
		this.controlRod1.addBox(0.0F, 0.0F, 0.0F, 2, 34, 2);
		this.controlRod1.setRotationPoint(6.0F, -14.0F, -8.0F);
		this.controlRod1.setTextureSize(64, 32);
		this.controlRod1.mirror = true;
		this.setRotation(this.controlRod1, 0.0F, 0.0F, 0.0F);
		this.controlRod2 = new ModelRenderer(this, 40, 0);
		this.controlRod2.addBox(0.0F, 0.0F, 0.0F, 2, 34, 2);
		this.controlRod2.setRotationPoint(-8.0F, -14.0F, -8.0F);
		this.controlRod2.setTextureSize(64, 32);
		this.controlRod2.mirror = true;
		this.setRotation(this.controlRod2, 0.0F, 0.0F, 0.0F);
		this.controlRod3 = new ModelRenderer(this, 48, 0);
		this.controlRod3.addBox(0.0F, 0.0F, 0.0F, 2, 34, 2);
		this.controlRod3.setRotationPoint(6.0F, -14.0F, 6.0F);
		this.controlRod3.setTextureSize(64, 32);
		this.controlRod3.mirror = true;
		this.setRotation(this.controlRod3, 0.0F, 0.0F, 0.0F);
		this.controlRod4 = new ModelRenderer(this, 56, 0);
		this.controlRod4.addBox(0.0F, 0.0F, 0.0F, 2, 34, 2);
		this.controlRod4.setRotationPoint(-8.0F, -14.0F, 6.0F);
		this.controlRod4.setTextureSize(64, 32);
		this.controlRod4.mirror = true;
		this.setRotation(this.controlRod4, 0.0F, 0.0F, 0.0F);
		this.bodyRod1 = new ModelRenderer(this, 0, 87);
		this.bodyRod1.addBox(0.0F, 0.0F, 0.0F, 2, 39, 2);
		this.bodyRod1.setRotationPoint(-1.0F, -44.0F, -1.0F);
		this.bodyRod1.setTextureSize(64, 32);
		this.bodyRod1.mirror = true;
		this.setRotation(this.bodyRod1, 0.0F, 0.0F, 0.0F);
		this.windVaneRod1 = new ModelRenderer(this, 32, 39);
		this.windVaneRod1.addBox(1.0F, 0.0F, 0.0F, 1, 1, 1);
		this.windVaneRod1.setRotationPoint(0.0F, -44.0F, 0.0F);
		this.windVaneRod1.setTextureSize(64, 32);
		this.windVaneRod1.mirror = true;
		this.setRotation(this.windVaneRod1, 0.0F, 0.0F, 0.0F);
		this.windVane1 = new ModelRenderer(this, 36, 39);
		this.windVane1.addBox(2.0F, -1.0F, 0.0F, 3, 3, 1);
		this.windVane1.setRotationPoint(0.0F, -44.0F, 0.0F);
		this.windVane1.setTextureSize(64, 32);
		this.windVane1.mirror = true;
		this.setRotation(this.windVane1, 0.0F, 0.0F, 0.0F);
		this.windVaneRod2 = new ModelRenderer(this, 32, 41);
		this.windVaneRod2.addBox(-2.0F, 0.0F, -1.0F, 1, 1, 1);
		this.windVaneRod2.setRotationPoint(0.0F, -44.0F, 0.0F);
		this.windVaneRod2.setTextureSize(64, 32);
		this.windVaneRod2.mirror = true;
		this.setRotation(this.windVaneRod2, 0.0F, 0.0F, 0.0F);
		this.windVane2 = new ModelRenderer(this, 44, 39);
		this.windVane2.addBox(-5.0F, -1.0F, -1.0F, 3, 3, 1);
		this.windVane2.setRotationPoint(0.0F, -44.0F, 0.0F);
		this.windVane2.setTextureSize(64, 32);
		this.windVane2.mirror = true;
		this.setRotation(this.windVane2, 0.0F, 0.0F, 0.0F);
		this.bodyRodHolder1 = new ModelRenderer(this, 8, 89);
		this.bodyRodHolder1.addBox(1.0F, 0.0F, 1.0F, 2, 2, 2);
		this.bodyRodHolder1.setRotationPoint(-7.0F, -3.0F, -7.0F);
		this.bodyRodHolder1.setTextureSize(64, 32);
		this.bodyRodHolder1.mirror = true;
		this.setRotation(this.bodyRodHolder1, 0.0F, 0.0F, 0.0F);
		this.bodyRodHolder2 = new ModelRenderer(this, 8, 93);
		this.bodyRodHolder2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
		this.bodyRodHolder2.setRotationPoint(4.0F, -3.0F, -6.0F);
		this.bodyRodHolder2.setTextureSize(64, 32);
		this.bodyRodHolder2.mirror = true;
		this.setRotation(this.bodyRodHolder2, 0.0F, 0.0F, 0.0F);
		this.bodyRodHolder3 = new ModelRenderer(this, 8, 97);
		this.bodyRodHolder3.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
		this.bodyRodHolder3.setRotationPoint(4.0F, -3.0F, 4.0F);
		this.bodyRodHolder3.setTextureSize(64, 32);
		this.bodyRodHolder3.mirror = true;
		this.setRotation(this.bodyRodHolder3, 0.0F, 0.0F, 0.0F);
		this.bodyRodHolder4 = new ModelRenderer(this, 8, 101);
		this.bodyRodHolder4.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
		this.bodyRodHolder4.setRotationPoint(-6.0F, -3.0F, 4.0F);
		this.bodyRodHolder4.setTextureSize(64, 32);
		this.bodyRodHolder4.mirror = true;
		this.setRotation(this.bodyRodHolder4, 0.0F, 0.0F, 0.0F);
		this.bodyPart2 = new ModelRenderer(this, 0, 61);
		this.bodyPart2.addBox(0.0F, 0.0F, 0.0F, 10, 4, 10);
		this.bodyPart2.setRotationPoint(-5.0F, 14.0F, -5.0F);
		this.bodyPart2.setTextureSize(64, 32);
		this.bodyPart2.mirror = true;
		this.setRotation(this.bodyPart2, 0.0F, 0.0F, 0.0F);
		this.bodyRodHolder5 = new ModelRenderer(this, 41, 62);
		this.bodyRodHolder5.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
		this.bodyRodHolder5.setRotationPoint(4.0F, 15.0F, -6.0F);
		this.bodyRodHolder5.setTextureSize(64, 32);
		this.bodyRodHolder5.mirror = true;
		this.setRotation(this.bodyRodHolder5, 0.0F, 0.0F, 0.0F);
		this.bodyRodHolder6 = new ModelRenderer(this, 50, 62);
		this.bodyRodHolder6.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
		this.bodyRodHolder6.setRotationPoint(-6.0F, 15.0F, -6.0F);
		this.bodyRodHolder6.setTextureSize(64, 32);
		this.bodyRodHolder6.mirror = true;
		this.setRotation(this.bodyRodHolder6, 0.0F, 0.0F, 0.0F);
		this.bodyRodHolder7 = new ModelRenderer(this, 41, 67);
		this.bodyRodHolder7.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
		this.bodyRodHolder7.setRotationPoint(4.0F, 15.0F, 4.0F);
		this.bodyRodHolder7.setTextureSize(64, 32);
		this.bodyRodHolder7.mirror = true;
		this.setRotation(this.bodyRodHolder7, 0.0F, 0.0F, 0.0F);
		this.bodyRodHolder8 = new ModelRenderer(this, 50, 67);
		this.bodyRodHolder8.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
		this.bodyRodHolder8.setRotationPoint(-6.0F, 15.0F, 4.0F);
		this.bodyRodHolder8.setTextureSize(64, 32);
		this.bodyRodHolder8.mirror = true;
		this.setRotation(this.bodyRodHolder8, 0.0F, 0.0F, 0.0F);
		this.bodyBase = new ModelRenderer(this, 0, 75);
		this.bodyBase.addBox(0.0F, 0.0F, 0.0F, 8, 4, 8);
		this.bodyBase.setRotationPoint(-4.0F, 19.0F, -4.0F);
		this.bodyBase.setTextureSize(64, 32);
		this.bodyBase.mirror = true;
		this.setRotation(this.bodyBase, 0.0F, 0.0F, 0.0F);
		this.bodyPart3 = new ModelRenderer(this, 8, 105);
		this.bodyPart3.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8);
		this.bodyPart3.setRotationPoint(-4.0F, 5.0F, -4.0F);
		this.bodyPart3.setTextureSize(64, 32);
		this.bodyPart3.mirror = true;
		this.setRotation(this.bodyPart3, 0.0F, 0.0F, 0.0F);
		this.bodyPart4 = new ModelRenderer(this, 17, 89);
		this.bodyPart4.addBox(0.0F, 0.0F, 0.0F, 8, 3, 8);
		this.bodyPart4.setRotationPoint(-4.0F, 1.0F, -4.0F);
		this.bodyPart4.setTextureSize(64, 32);
		this.bodyPart4.mirror = true;
		this.setRotation(this.bodyPart4, 0.0F, 0.0F, 0.0F);
		this.top1 = new ModelRenderer(this, 0, 35);
		this.top1.addBox(0.0F, 0.0F, 0.0F, 4, 3, 4);
		this.top1.setRotationPoint(-2.0F, -9.0F, -2.0F);
		this.top1.setTextureSize(64, 32);
		this.top1.mirror = true;
		this.setRotation(this.top1, 0.0F, 0.0F, 0.0F);
		this.top2 = new ModelRenderer(this, 41, 72);
		this.top2.addBox(0.0F, 0.0F, 0.0F, 4, 3, 4);
		this.top2.setRotationPoint(-2.0F, -13.0F, -2.0F);
		this.top2.setTextureSize(64, 32);
		this.top2.mirror = true;
		this.setRotation(this.top2, 0.0F, 0.0F, 0.0F);
		this.top3 = new ModelRenderer(this, 41, 101);
		this.top3.addBox(0.0F, 0.0F, 0.0F, 4, 7, 4);
		this.top3.setRotationPoint(-2.0F, -21.0F, -2.0F);
		this.top3.setTextureSize(64, 32);
		this.top3.mirror = true;
		this.setRotation(this.top3, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.mainBody.render(f5);
		this.base.render(f5);
		this.bodyPart1.render(f5);
		this.controlRod1.render(f5);
		this.controlRod2.render(f5);
		this.controlRod3.render(f5);
		this.controlRod4.render(f5);
		this.bodyRod1.render(f5);
		this.windVaneRod1.render(f5);
		this.windVane1.render(f5);
		this.windVaneRod2.render(f5);
		this.windVane2.render(f5);
		this.bodyRodHolder1.render(f5);
		this.bodyRodHolder2.render(f5);
		this.bodyRodHolder3.render(f5);
		this.bodyRodHolder4.render(f5);
		this.bodyPart2.render(f5);
		this.bodyRodHolder5.render(f5);
		this.bodyRodHolder6.render(f5);
		this.bodyRodHolder7.render(f5);
		this.bodyRodHolder8.render(f5);
		this.bodyBase.render(f5);
		this.bodyPart3.render(f5);
		this.bodyPart4.render(f5);
		this.top1.render(f5);
		this.top2.render(f5);
		this.top3.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles()
	{
	}
}
/*
 * Location: C:\Users\Colby\Downloads\Parzi's Star Wars Mod
 * v1.2.0-dev7.jar!\com\parzi\starwarsmod\rendering\models\ModelMV.class Java
 * compiler version: 6 (50.0) JD-Core Version: 0.7.1
 */