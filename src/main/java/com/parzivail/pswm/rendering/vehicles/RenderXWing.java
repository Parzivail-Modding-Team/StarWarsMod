package com.parzivail.pswm.rendering.vehicles;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.models.vehicles.ModelXWingNew;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderXWing extends RenderVehicBase
{
	public static ResourceLocation texture = new ResourceLocation(Resources.MODID, "textures/models/xwingNew.png");

	public RenderXWing()
	{
		super(new ModelXWingNew(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
}
