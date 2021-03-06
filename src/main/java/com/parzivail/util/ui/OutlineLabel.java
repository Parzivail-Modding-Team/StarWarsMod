package com.parzivail.util.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;

public class OutlineLabel extends OutlineButton
{
	public OutlineLabel(int id, int x, int y, String text)
	{
		super(id, x, y, 0, 0, text, false);
	}

	@Override
	public void drawButton(Minecraft mc, int mX, int mY)
	{
		GL11.glPushMatrix();
		if (this.visible)
		{
			FontRenderer fontrenderer = mc.fontRenderer;

			int textColor = GLPalette.WHITE;

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			this.drawString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, textColor);
		}
		GL11.glPopMatrix();
	}
}
