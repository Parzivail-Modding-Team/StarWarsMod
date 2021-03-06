package com.parzivail.pswm.models.weapons.melee;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLightsaberLuke2Blade extends ModelBase
{
	ModelRenderer blade;

	public ModelLightsaberLuke2Blade()
	{
		this.textureWidth = 256;
		this.textureHeight = 128;
		blade = new ModelRenderer(this, 0, 0);
		blade.addBox(0, 1, 1, 124, 4, 4);
		blade.setRotationPoint(-124F, 1F, 1F);
		blade.setTextureSize(64, 32);
		blade.mirror = true;
		setRotation(blade, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		blade.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
