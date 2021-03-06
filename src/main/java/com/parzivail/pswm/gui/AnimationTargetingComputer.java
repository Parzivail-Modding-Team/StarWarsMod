package com.parzivail.pswm.gui;

import com.parzivail.pswm.StarWarsMod;
import com.parzivail.util.math.Animation;
import com.parzivail.util.math.MathUtils;
import com.parzivail.util.ui.GFX;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class AnimationTargetingComputer extends Animation
{
	private ScaledResolution r;

	public AnimationTargetingComputer()
	{
		super(50, true, true);

		this.r = new ScaledResolution(StarWarsMod.mc, StarWarsMod.mc.displayWidth, StarWarsMod.mc.displayHeight);
	}

	@Override
	public void render(RenderGameOverlayEvent event)
	{
		super.render(event);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		GL11.glLineWidth(5);

		GL11.glColor3f(0, 0, 0);
		int x = 0;
		int y = 50;
		int width = 250;
		int height = 125;
		GFX.drawRectangle(x, y, width, height, true);

		GL11.glColor3f(1, 1, 0);
		GFX.drawLine(x + 60, y + 10, x + width - 60, y + height - 10);
		GFX.drawLine(x + 60, y + height - 10, x + width - 60, y + 10);

		GFX.drawLine(x + 20, y + 10, x + width - 20, y + height - 10);
		GFX.drawLine(x + 20, y + height - 10, x + width - 20, y + 10);

		GFX.drawLine(x + 10, y + 40, x + width - 10, y + height - 40);
		GFX.drawLine(x + 10, y + height - 40, x + width - 10, y + 40);

		int x1 = (int)MathUtils.lerp(x + 60, x + (height / 2f), this.tick / (float)this.getLength());
		int y1 = (int)MathUtils.lerp(y + height - 10, y + 10, this.tick / (float)this.getLength());

		GFX.drawLine(x1, y1 + 10, x1, y1 - 10);

		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
}
