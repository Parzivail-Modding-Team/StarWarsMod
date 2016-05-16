package com.parzivail.pswm.rendering.vehicles;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.models.vehicles.ModelJakkuSpeeder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderJakkuSpeeder extends RenderLandBase
{
	public static ResourceLocation texture = new ResourceLocation(Resources.MODID, "textures/models/jakkuSpeeder.png");

	public RenderJakkuSpeeder()
	{
		super(new ModelJakkuSpeeder(), 0.5f);
		this.scale = 3;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
}
/*
 * Location: C:\Users\Colby\Downloads\Parzi's Star Wars Mod
 * v1.2.0-dev7.jar!\com\
 * parzi\starwarsmod\rendering\vehicles\RenderSpeederBike.class Java compiler
 * version: 6 (50.0) JD-Core Version: 0.7.1
 */