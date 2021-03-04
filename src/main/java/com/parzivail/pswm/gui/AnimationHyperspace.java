package com.parzivail.pswm.gui;

import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.handlers.ClientEventHandler;
import com.parzivail.util.math.Animation;
import com.parzivail.util.ui.GFX;
import com.parzivail.util.ui.GLPalette;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

public class AnimationHyperspace extends Animation
{
	private static class Star
	{
		Point pos;
		int dist;
		float scale;
		float r;
		float g;
		float b;

		public Star(Point pos, int dist, float scale, int color)
		{
			this.dist = dist;
			this.pos = pos;
			this.scale = scale;
			Color c = GLPalette.intToColor(color);
			this.r = (c.getRed() / 255f) * (dist / 255f);
			this.g = (c.getGreen() / 255f) * (dist / 255f);
			this.b = (c.getBlue() / 255f) * (dist / 255f);
		}
	}

	private final ArrayList<Star> stars;
	private final boolean reverse;
	private final ScaledResolution r;

	public AnimationHyperspace(int amtStars, boolean reverse)
	{
		super(150, false, true);

		this.r = new ScaledResolution(StarWarsMod.mc, StarWarsMod.mc.displayWidth, StarWarsMod.mc.displayHeight);
		this.stars = new ArrayList<>();
		this.reverse = reverse;

		//if (trackSender)
		//	ship.playSound(Resources.MODID + ":" + "lightspeed.exit", 1, 1);
		//else
		//	ship.playSound(Resources.MODID + ":" + "lightspeed.enter", 1, 1);

		for (int i = 0; i < amtStars; i++)
			stars.add(new Star(new Point(StarWarsMod.rngGeneral.nextInt(r.getScaledWidth()), StarWarsMod.rngGeneral.nextInt(r.getScaledHeight())), StarWarsMod.rngGeneral.nextInt(255), StarWarsMod.rngGeneral.nextFloat(), 0xEEEEFF));
	}

	@Override
	public void render(RenderGameOverlayEvent event)
	{
		super.render(event);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		GL11.glColor3f(0, 0, 0);
		GFX.drawRectangle(0, 0, r.getScaledWidth(), r.getScaledHeight(), true);

		int mX = r.getScaledWidth() / 2;
		int mY = r.getScaledHeight() / 2;

		for (Star star : stars)
		{
			GL11.glColor3f(star.r, star.g, star.b);

			GL11.glLineWidth(2); //star.dist / 255f * 2 + 0.1f

			float angle = (float)Math.atan2(star.pos.x - mX, star.pos.y - mY);

			double t;
			int nTick = tick;
			if (reverse)
				nTick = 100 - tick;

			t = 1 / (Math.pow(2, nTick) * Math.pow(2, -1.2 * nTick + 5));

			t += 1;
			t *= star.scale;

			float mod = (float)(t / (float)(255 - star.dist));

			if (t < 5)
				mod = 1;

			GFX.drawLine(star.pos.x + MathHelper.sin(angle) * mod, star.pos.y + MathHelper.cos(angle) * mod, star.pos.x + MathHelper.sin(angle) * t, star.pos.y + MathHelper.cos(angle) * t);
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		RenderGameOverlayEvent.Pre event1 = new RenderGameOverlayEvent.Pre(event, RenderGameOverlayEvent.ElementType.HOTBAR);
		ClientEventHandler.guiVehicle.onRenderGui(event1);
	}

	@Override
	public void tick()
	{
		super.tick();
		//if (isDone())
		//	GuiToast.makeText("Done!", GuiToast.TIME_SHORT).show();
	}

}
