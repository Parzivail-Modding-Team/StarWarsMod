package com.parzi.starwarsmod.rendering;

import org.lwjgl.opengl.GL11;

import com.parzi.starwarsmod.Resources;
import com.parzi.starwarsmod.rendering.models.ModelMV;
import com.parzi.starwarsmod.tileentities.TileEntityMV;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RenderMV extends TileEntitySpecialRenderer
{
	public static ResourceLocation texture = new ResourceLocation(Resources.MODID + ":" + "textures/blocks/moistureVaporator.png");

	private final ModelMV model;

	public RenderMV()
	{
		this.model = new ModelMV();
	}

	private void adjustRotatePivotViaMeta(World world, int x, int y, int z)
	{
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float tickTime)
	{
		GL11.glPushMatrix();
		TileEntityMV mv = (TileEntityMV)te;
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.8F, (float)z + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glScalef(1.5F, 1.5F, 1.5F);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(mv.getFacing() * 90, 0.0F, 1.0F, 0.0F);
		this.model.windVane1.rotateAngleY = mv.frame / 10;
		this.model.windVane2.rotateAngleY = mv.frame / 10;
		this.model.windVaneRod1.rotateAngleY = mv.frame / 10;
		this.model.windVaneRod2.rotateAngleY = mv.frame / 10;
		this.model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.05F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
/*
 * Location: C:\Users\Colby\Downloads\Parzi's Star Wars Mod
 * v1.2.0-dev7.jar!\com\parzi\starwarsmod\rendering\RenderMV.class Java compiler
 * version: 6 (50.0) JD-Core Version: 0.7.1
 */