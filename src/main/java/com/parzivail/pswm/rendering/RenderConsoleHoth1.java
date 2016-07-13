package com.parzivail.pswm.rendering;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.entities.EntityTilePassthrough;
import com.parzivail.pswm.models.blocks.hoth.ModelConsoleHothCurved1;
import com.parzivail.pswm.tileentities.TileEntityConsoleHoth1;
import com.parzivail.util.ui.P3D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class RenderConsoleHoth1 extends TileEntitySpecialRenderer
{
	public static ResourceLocation texture = new ResourceLocation(Resources.MODID + ":" + "textures/models/consoleHothCurved1.png");

	private final ModelBase model;

	public RenderConsoleHoth1()
	{
		this.model = new ModelConsoleHothCurved1();
	}

	private void adjustRotatePivotViaMeta(World world, int x, int y, int z)
	{
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float tickTime)
	{
		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glTranslated(x + 0.5f, y + 1.5f, z + 0.5f);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		P3D.glScalef(1.25f);
		GL11.glRotatef(90 * ((TileEntityConsoleHoth1)te).getFacing(), 0, 1, 0);
		this.model.render(new EntityTilePassthrough(te), 0, 0, 0, 0.0F, 0.0F, 0.05F);
		GL11.glPopMatrix();
	}
}
