package com.parzivail.pswm.rendering;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.models.blocks.ModelLadder;
import com.parzivail.pswm.models.blocks.ModelLadderTop;
import com.parzivail.pswm.tileentities.TileEntityLadder;
import com.parzivail.util.ui.GLPZ;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class RenderLadder extends TileEntitySpecialRenderer
{
	public static ResourceLocation texture = new ResourceLocation(Resources.MODID + ":" + "textures/models/ladder.png");
	public static ResourceLocation textureTop = new ResourceLocation(Resources.MODID + ":" + "textures/models/ladderTop.png");

	private final ModelBase model;
	private final ModelBase modelTop;

	public RenderLadder()
	{
		this.model = new ModelLadder();
		this.modelTop = new ModelLadderTop();
	}

	private void adjustRotatePivotViaMeta(World world, int x, int y, int z)
	{
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float tickTime)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5f, y + 1.5f, z + 0.5f);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(90 * ((TileEntityLadder)te).getFacing(), 0, 1, 0);
		GLPZ.glScalef(1.25f);
		if (te.getWorldObj().getBlock(te.xCoord, te.yCoord + 1, te.zCoord) == Blocks.air)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(textureTop);
			this.modelTop.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.05F);
		}
		else
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.05F);
		}
		GL11.glPopMatrix();
	}
}
