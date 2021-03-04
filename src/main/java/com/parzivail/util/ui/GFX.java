package com.parzivail.util.ui;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsMod;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static com.parzivail.pswm.StarWarsMod.mc;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;

public class GFX
{
	public static ResourceLocation swIcons = new ResourceLocation(Resources.MODID, "textures/gui/icons.png");
	private static ResourceLocation vignetteTexPath = new ResourceLocation("textures/misc/vignette.png");
	private static ResourceLocation icons = new ResourceLocation("textures/gui/icons.png");
	private static float prevVignetteBrightness = 1.0F;
	private static Point[] cursorFrom = { new Point(0, 2), new Point(0, 0), new Point(2, 0), new Point(8, 0), new Point(10, 0), new Point(10, 2), new Point(10, 8), new Point(10, 10), new Point(8, 10), new Point(2, 10), new Point(0, 10), new Point(0, 8) };
	private static Point[] cursorTo = { new Point(5, 4), new Point(5, 2), new Point(5, 0), new Point(6, 5), new Point(8, 5), new Point(10, 5), new Point(5, 6), new Point(5, 8), new Point(5, 10), new Point(4, 5), new Point(2, 5), new Point(0, 5) };

	/**
	 * Draws a rectangle
	 */
	public static void drawRectangle(float x, float y, float w, float h, boolean filled)
	{
		GL11.glPushMatrix();
		if (filled)
			glBegin(GL11.GL_TRIANGLE_FAN);
		else
			glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex3d(x, y, 0);
		GL11.glVertex3d(x, y + h, 0);
		GL11.glVertex3d(x + w, y + h, 0);
		GL11.glVertex3d(x + w, y, 0);
		glEnd();
		GL11.glPopMatrix();
	}

	private static void drawBackdrop(int x, int y, int width, int height, int opacity)
	{
		int color = opacity << 24;
		Gui.drawRect(x + 1, y, x + width - 1, y + height, color);
		Gui.drawRect(x, y + 1, x + 1, y + height - 1, color);
		Gui.drawRect(x + width - 1, y + 1, x + width, y + height - 1, color);

		color = 0x28025C | opacity << 24;
		Gui.drawRect(x + 1, y + 1, x + width - 1, y + 2, color);
		Gui.drawRect(x + 1, y + height - 1, x + width - 1, y + height - 2, color);
		Gui.drawRect(x + 1, y + 1, x + 2, y + height - 1, color);
		Gui.drawRect(x + width - 1, y + 1, x + width - 2, y + height - 1, color);
	}

	public static void drawTooltip(int x, int y, String text)
	{
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		int width = 0;
		String[] _text = TextUtils.splitIntoLine(text, 25);
		for (String line : _text)
			if (StarWarsMod.mc.fontRenderer.getStringWidth(line) > width)
				width = StarWarsMod.mc.fontRenderer.getStringWidth(line);

		int height = (_text.length + 1) * StarWarsMod.mc.fontRenderer.FONT_HEIGHT;
		drawBackdrop(x, y, width + 8, height, 255);

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		for (int i = 0; i < _text.length; i++)
			StarWarsMod.mc.fontRenderer.drawStringWithShadow(_text[i], x + 6, y + i * (StarWarsMod.mc.fontRenderer.FONT_HEIGHT + 1) + 5, GLPalette.WHITE);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(1, 1, 1, 1);
	}

	public static void drawTexture(int x, int y, int textureX, int textureY, int width, int height)
	{
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x, y + height, 1, (textureX) * f, (textureY + height) * f1);
		tessellator.addVertexWithUV(x + width, y + height, 1, (textureX + width) * f, (textureY + height) * f1);
		tessellator.addVertexWithUV(x + width, y, 1, (textureX + width) * f, (textureY) * f1);
		tessellator.addVertexWithUV(x, y, 1, (textureX) * f, (textureY) * f1);
		tessellator.draw();
	}

	public static void renderTileEntityAt(TileEntity tileEntity, float x, float y, float z, float partialTicks)
	{
		TileEntityRendererDispatcher.instance.renderTileEntityAt(tileEntity, x, y, z, partialTicks);
	}

	/**
	 * Draws a line
	 */
	public static void drawLine(float sx, float sy, float ex, float ey)
	{
		GL11.glPushMatrix();
		glBegin(GL11.GL_LINE_STRIP);
		GL11.glVertex2d(sx, sy);
		GL11.glVertex2d(ex, ey);
		glEnd();
		GL11.glPopMatrix();
	}

	/**
	 * Draws a filled circle
	 */
	public static void drawFilledCircle(float x, float y, float radius)
	{
		GL11.glPushMatrix();
		glBegin(GL11.GL_TRIANGLE_FAN);
		for (int i = 0; i <= 360; i++)
		{
			double nx = MathHelper.sin(i * 3.141526f / 180) * radius;
			double ny = MathHelper.cos(i * 3.141526f / 180) * radius;
			GL11.glVertex2d(nx + x, ny + y);
		}
		glEnd();
		GL11.glPopMatrix();
	}

	/**
	 * Draws a circle
	 */
	public static void drawCircle(float x, float y, float radius)
	{
		GL11.glPushMatrix();
		glBegin(GL11.GL_LINE_STRIP);
		for (int i = 0; i <= 360; i++)
		{
			double nx = MathHelper.sin(i * 3.141526f / 180) * radius;
			double ny = MathHelper.cos(i * 3.141526f / 180) * radius;
			GL11.glVertex2d(nx + x, ny + y);
		}
		glEnd();
		GL11.glPopMatrix();
	}

	/**
	 * Draws a dashed circle
	 */
	public static void drawDashedCircle(float x, float y, float radius, int dashLen)
	{
		GL11.glPushMatrix();
		for (int i = 0; i <= 360; i += (3 * dashLen))
		{
			glBegin(GL11.GL_LINE_STRIP);
			for (int j = 0; j <= dashLen; j++)
			{
				double nx = MathHelper.sin((i + j) * 3.141526f / 180) * radius;
				double ny = MathHelper.cos((i + j) * 3.141526f / 180) * radius;
				GL11.glVertex2d(nx + x, ny + y);
			}
			glEnd();
		}
		GL11.glPopMatrix();
	}

	/**
	 * Draws a pie piece circle thing
	 */
	public static void drawPieCircle(float x, float y, float radius, float percent)
	{
		GL11.glPushMatrix();
		glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex2d(x, y);
		for (int i = 0; i <= 360 * percent; i++)
		{
			double nx = MathHelper.sin(i * 3.141526f / 180) * radius;
			double ny = MathHelper.cos(i * 3.141526f / 180) * radius;
			GL11.glVertex2d(nx + x, ny + y);
		}
		glEnd();
		GL11.glPopMatrix();
	}

	/**
	 * Draws a pie piece donut thing
	 */
	public static void drawPieDonut(float x, float y, float radius, float stripSize, float percent)
	{
		GL11.glPushMatrix();
		glBegin(GL11.GL_TRIANGLE_STRIP);
		for (int i = 0; i <= 360 * percent; i++)
		{
			double nx = MathHelper.sin(i * 3.141526f / 180) * (radius - stripSize);
			double ny = MathHelper.cos(i * 3.141526f / 180) * (radius - stripSize);
			GL11.glVertex2d(nx + x, ny + y);

			nx = MathHelper.sin(i * 3.141526f / 180) * radius;
			ny = MathHelper.cos(i * 3.141526f / 180) * radius;
			GL11.glVertex2d(nx + x, ny + y);
		}
		glEnd();
		GL11.glPopMatrix();
	}

	/**
	 * Draws some text
	 */
	public static void drawText(FontRenderer font, String s, float x, float y, float scale, int color)
	{
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_TEXTURE_2D);
		GL11.glScalef(scale, scale, 1);
		GL11.glTranslatef(x / scale, y / scale, 0);
		font.drawString(s, 0, 0, color);
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}

	/**
	 * Draws some text
	 */
	public static void drawTextShadow(FontRenderer font, String s, float x, float y, float scale, int color)
	{
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_TEXTURE_2D);
		GL11.glScalef(scale, scale, 1);
		GL11.glTranslatef(x / scale, y / scale, 0);
		font.drawStringWithShadow(s, 0, 0, color);
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}

	/**
	 * Draws some text
	 */
	public static void drawTextShadowCenter(FontRenderer font, String s, float x, float y, float scale, int color)
	{
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_TEXTURE_2D);
		GL11.glScalef(scale, scale, 1);
		GL11.glTranslatef(x / scale, y / scale, 0);
		font.drawStringWithShadow(s, -font.getStringWidth(s) / 2, 0, color);
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}

	/**
	 * Draws some centered text
	 */
	public static void drawCenteredText(FontRenderer font, String s, float x, float y, float scale, int color)
	{
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glScalef(scale, scale, 1);
		GL11.glTranslatef(x / scale, y / scale, 0);
		font.drawString(s, -font.getStringWidth(s) / 2, 0, color);
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}

	/**
	 * Double wrapper for {@link GFX ::drawLine}
	 */
	public static void drawLine(double x1, double y1, double x2, double y2)
	{
		drawLine((float)x1, (float)y1, (float)x2, (float)y2);
	}

	public static int getRGB(int r, int g, int b)
	{
		int rgb = r;
		rgb = (rgb << 8) + g;
		rgb = (rgb << 8) + b;
		return rgb;
	}

	public static int getRGBA(int r, int g, int b, int a)
	{
		int rgba = a;
		rgba = (rgba << 8) + r;
		rgba = (rgba << 8) + g;
		rgba = (rgba << 8) + b;
		return rgba;
	}

	public static int rainbowColor(int phaseMod)
	{
		float frequency = 0.001F;
		float phase = MathHelper.abs(Minecraft.getSystemTime() % Integer.MAX_VALUE - Integer.MIN_VALUE / 2);
		int r = (int)(MathHelper.sin(frequency * phase + 0 + phaseMod) * 127 + 128);
		int g = (int)(MathHelper.sin(frequency * phase + 2 + phaseMod) * 127 + 128);
		int b = (int)(MathHelper.sin(frequency * phase + 4 + phaseMod) * 127 + 128);
		return getRGB(r, g, b);
	}

	public static void changeCameraDist(int dist)
	{
		try
		{
			ReflectionHelper.setPrivateValue(EntityRenderer.class, StarWarsMod.mc.entityRenderer, dist, "thirdPersonDistance", "field_78490_B", "E");
			ReflectionHelper.setPrivateValue(EntityRenderer.class, StarWarsMod.mc.entityRenderer, dist, "thirdPersonDistanceTemp", "field_78491_C", "F");
		}
		catch (Exception e)
		{
			Lumberjack.warn("Unable to change camera distance!");
			e.printStackTrace();
		}
	}

	/**
	 * Draws center-aligned text
	 *
	 * @param x      The x position
	 * @param y      The y position
	 * @param string The string
	 * @param color  The color
	 */
	public static void drawCenteredString(int x, int y, String string, int color)
	{
		mc.entityRenderer.setupOverlayRendering();
		mc.fontRenderer.drawStringWithShadow(string, x - mc.fontRenderer.getStringWidth(string) / 2, y - mc.fontRenderer.FONT_HEIGHT / 2, color);
	}

	public static void drawFancyCursor(RenderGameOverlayEvent event, float p, int color)
	{
		float centerX = event.resolution.getScaledWidth() / 2f;
		float centerY = event.resolution.getScaledHeight() / 2f;

		GL11.glPushMatrix();
		StarWarsMod.mc.entityRenderer.setupOverlayRendering();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GLPalette.glColorI(color);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(2);

		Vec3 a = lerp(cursorFrom[0], cursorTo[0], p);
		Vec3 b = lerp(cursorFrom[1], cursorTo[1], p);
		Vec3 c = lerp(cursorFrom[2], cursorTo[2], p);

		glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex2d(centerX + a.xCoord - 10, centerY + a.yCoord - 10);
		GL11.glVertex2d(centerX + b.xCoord - 10, centerY + b.yCoord - 10);
		glEnd();
		glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex2d(centerX + c.xCoord - 10, centerY + c.yCoord - 10);
		GL11.glVertex2d(centerX + b.xCoord - 10, centerY + b.yCoord - 10);
		glEnd();

		a = lerp(cursorFrom[3], cursorTo[3], p);
		b = lerp(cursorFrom[4], cursorTo[4], p);
		c = lerp(cursorFrom[5], cursorTo[5], p);

		glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex2d(centerX + a.xCoord - 10, centerY + a.yCoord - 10);
		GL11.glVertex2d(centerX + b.xCoord - 10, centerY + b.yCoord - 10);
		glEnd();
		glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex2d(centerX + c.xCoord - 10, centerY + c.yCoord - 10);
		GL11.glVertex2d(centerX + b.xCoord - 10, centerY + b.yCoord - 10);
		glEnd();

		a = lerp(cursorFrom[6], cursorTo[6], p);
		b = lerp(cursorFrom[7], cursorTo[7], p);
		c = lerp(cursorFrom[8], cursorTo[8], p);

		glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex2d(centerX + a.xCoord - 10, centerY + a.yCoord - 10);
		GL11.glVertex2d(centerX + b.xCoord - 10, centerY + b.yCoord - 10);
		glEnd();
		glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex2d(centerX + c.xCoord - 10, centerY + c.yCoord - 10);
		GL11.glVertex2d(centerX + b.xCoord - 10, centerY + b.yCoord - 10);
		glEnd();

		a = lerp(cursorFrom[9], cursorTo[9], p);
		b = lerp(cursorFrom[10], cursorTo[10], p);
		c = lerp(cursorFrom[11], cursorTo[11], p);

		glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex2d(centerX + a.xCoord - 10, centerY + a.yCoord - 10);
		GL11.glVertex2d(centerX + b.xCoord - 10, centerY + b.yCoord - 10);
		glEnd();
		glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex2d(centerX + c.xCoord - 10, centerY + c.yCoord - 10);
		GL11.glVertex2d(centerX + b.xCoord - 10, centerY + b.yCoord - 10);
		glEnd();

		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glPopMatrix();
	}

	/**
	 * Draws a filled circle
	 *
	 * @param g      The x position
	 * @param h      The y position
	 * @param radius The radius
	 * @param color  The color
	 */
	public static void drawFilledCircle(float g, float h, float radius, int color)
	{
		mc.entityRenderer.setupOverlayRendering();
		float f = (color >> 24 & 0xff) / 255F;
		float f1 = (color >> 16 & 0xff) / 255F;
		float f2 = (color >> 8 & 0xff) / 255F;
		float f3 = (color & 0xff) / 255F;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(f1, f2, f3, f);
		glBegin(GL11.GL_TRIANGLE_FAN);
		for (int i = 0; i <= 360; i++)
		{
			double nx = MathHelper.sin(i * 3.141526f / 180) * radius;
			double ny = MathHelper.cos(i * 3.141526f / 180) * radius;
			GL11.glVertex2d(nx + g, ny + h);
		}
		glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
	}

	/**
	 * Draws a filled isoceles triangle
	 *
	 * @param x     The x position
	 * @param y     The y position
	 * @param scale The scale
	 * @param theta The rotation theta
	 * @param color The color
	 */
	public static void drawFilledTriangle(double x, double y, int scale, float theta, int color)
	{
		mc.entityRenderer.setupOverlayRendering();
		GL11.glTranslated(x, y, 0);
		GL11.glRotatef(180 + theta, 0F, 0F, 1.0F);
		float f = (color >> 24 & 0xff) / 255F;
		float f1 = (color >> 16 & 0xff) / 255F;
		float f2 = (color >> 8 & 0xff) / 255F;
		float f3 = (color & 0xff) / 255F;
		GL11.glColor4f(f1, f2, f3, f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glBlendFunc(770, 771);
		glBegin(GL11.GL_TRIANGLES);

		GL11.glVertex2d(0, scale);
		GL11.glVertex2d(scale, -scale);
		GL11.glVertex2d(-scale, -(scale));

		glEnd();
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glRotatef(-180 - theta, 0F, 0F, 1.0F);
		GL11.glTranslated(-x, -y, 0);
	}

	/**
	 * Draws a rectangle with a vertical gradient between the specified colors.
	 */
	public static void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor)
	{
		mc.entityRenderer.setupOverlayRendering();

		float f = (startColor >> 24 & 255) / 255.0F;
		float f1 = (startColor >> 16 & 255) / 255.0F;
		float f2 = (startColor >> 8 & 255) / 255.0F;
		float f3 = (startColor & 255) / 255.0F;
		float f4 = (endColor >> 24 & 255) / 255.0F;
		float f5 = (endColor >> 16 & 255) / 255.0F;
		float f6 = (endColor >> 8 & 255) / 255.0F;
		float f7 = (endColor & 255) / 255.0F;
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(f1, f2, f3, f);
		tessellator.addVertex(right, top, 0);
		tessellator.addVertex(left, top, 0);
		tessellator.setColorRGBA_F(f5, f6, f7, f4);
		tessellator.addVertex(left, bottom, 0);
		tessellator.addVertex(right, bottom, 0);
		tessellator.draw();
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	/**
	 * Draws a rectangle with a vertical gradient between the specified colors.
	 */
	public static void drawGradientRectHoriz(int left, int top, int right, int bottom, int startColor, int endColor)
	{
		mc.entityRenderer.setupOverlayRendering();

		float f = (startColor >> 24 & 255) / 255.0F;
		float f1 = (startColor >> 16 & 255) / 255.0F;
		float f2 = (startColor >> 8 & 255) / 255.0F;
		float f3 = (startColor & 255) / 255.0F;
		float f4 = (endColor >> 24 & 255) / 255.0F;
		float f5 = (endColor >> 16 & 255) / 255.0F;
		float f6 = (endColor >> 8 & 255) / 255.0F;
		float f7 = (endColor & 255) / 255.0F;
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(f1, f2, f3, f);
		tessellator.addVertex(right, top, 0);
		tessellator.addVertex(right, bottom, 0);
		tessellator.setColorRGBA_F(f5, f6, f7, f4);
		tessellator.addVertex(left, bottom, 0);
		tessellator.addVertex(left, top, 0);
		tessellator.draw();
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	/**
	 * Draws a hollow circle
	 *
	 * @param x         The x position
	 * @param y         The y position
	 * @param radius    The radius
	 * @param segments  The number of segments
	 * @param lineWidth The width of the line
	 * @param color     The color
	 */
	public static void drawHollowCircle(float x, float y, float radius, int segments, float lineWidth, int color)
	{
		mc.entityRenderer.setupOverlayRendering();
		float f = (color >> 24 & 0xff) / 255F;
		float f1 = (color >> 16 & 0xff) / 255F;
		float f2 = (color >> 8 & 0xff) / 255F;
		float f3 = (color & 0xff) / 255F;
		float theta = (float)(2 * 3.1415926 / segments);
		float p = MathHelper.cos(theta);// calculate the sine and cosine
		float s = MathHelper.sin(theta);
		float t;
		GL11.glColor4f(f1, f2, f3, f);
		float nx = radius;
		float ny = 0;// start at angle = 0
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(lineWidth);
		glBegin(GL11.GL_LINE_LOOP);
		for (int ii = 0; ii < segments; ii++)
		{
			GL11.glVertex2f(nx + x, ny + y);// final vertex vertex

			// rotate the stuff
			t = nx;
			nx = p * nx - s * ny;
			ny = s * t + p * ny;
		}
		glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
	}

	/**
	 * Draws a hollow isoceles triangle
	 *
	 * @param x         The x position
	 * @param y         The y position
	 * @param scale     The scale
	 * @param theta     The rotation theta
	 * @param lineWidth The line width
	 * @param color     The color
	 */
	public static void drawHollowTriangle(double x, double y, int scale, float theta, int lineWidth, int color)
	{
		mc.entityRenderer.setupOverlayRendering();
		GL11.glTranslated(x, y, 0);
		GL11.glRotatef(180 + theta, 0F, 0F, 1.0F);
		float f = (color >> 24 & 0xff) / 255F;
		float f1 = (color >> 16 & 0xff) / 255F;
		float f2 = (color >> 8 & 0xff) / 255F;
		float f3 = (color & 0xff) / 255F;
		GL11.glColor4f(f1, f2, f3, f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(lineWidth);
		glBegin(GL11.GL_LINE_LOOP);

		GL11.glVertex2d(0, scale);
		GL11.glVertex2d(scale, -scale);
		GL11.glVertex2d(-scale, -scale);

		glEnd();
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glRotatef(-180 - theta, 0F, 0F, 1.0F);
		GL11.glTranslated(-x, -y, 0);
	}

	/**
	 * Draws a line
	 *
	 * @param x1        The start x
	 * @param y1        The start y
	 * @param x2        The end x
	 * @param y2        The end y
	 * @param lineWidth The line width
	 * @param color     The line color
	 */
	public static void drawLine(double x1, double y1, double x2, double y2, int lineWidth, int color)
	{
		mc.entityRenderer.setupOverlayRendering();
		float f = (color >> 24 & 0xff) / 255F;
		float f1 = (color >> 16 & 0xff) / 255F;
		float f2 = (color >> 8 & 0xff) / 255F;
		float f3 = (color & 0xff) / 255F;
		GL11.glColor4f(f1, f2, f3, f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(lineWidth);
		glBegin(GL11.GL_LINE_LOOP);

		GL11.glVertex2d(x1, y1);
		GL11.glVertex2d(x2, y2);

		glEnd();
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
	}

	public static void drawLine3d(double x1, double y1, double z1, double x2, double y2, double z2, int lineWidth, int color)
	{
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glLineWidth(2.0F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDepthMask(false);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawing(1);
		tessellator.setColorOpaque_I(color);
		tessellator.addVertex(x1, y1, z1);
		tessellator.addVertex(x2, y2, z2);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glPopMatrix();
	}

	/**
	 * Draws a filled circle
	 *
	 * @param x       The x position
	 * @param y       The y position
	 * @param radius  The radius
	 * @param percent The percentage full of the circle
	 * @param color   The color
	 */
	public static void drawLoadingCircle(float x, float y, double radius, float percent, int color)
	{
		mc.entityRenderer.setupOverlayRendering();
		float f = (color >> 24 & 0xff) / 255F;
		float f1 = (color >> 16 & 0xff) / 255F;
		float f2 = (color >> 8 & 0xff) / 255F;
		float f3 = (color & 0xff) / 255F;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(f1, f2, f3, f);
		glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex2d(x, y);
		for (int i = 0; i <= 360 * percent; i++)
		{
			double nx = MathHelper.sin(i * 3.141526f / 180) * radius;
			double ny = MathHelper.cos(i * 3.141526f / 180) * radius;
			GL11.glVertex2d(nx + x, ny + y);
		}
		glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
	}

	/**
	 * Draws a filled circle
	 *
	 * @param x       The x position
	 * @param y       The y position
	 * @param radius  The radius
	 * @param percent The percentage full of the circle
	 * @param color   The color
	 */
	public static void drawLoadingCircleWithoutSetup(float x, float y, double radius, float percent, int color)
	{
		GL11.glPushMatrix();
		float f = (color >> 24 & 0xff) / 255F;
		float f1 = (color >> 16 & 0xff) / 255F;
		float f2 = (color >> 8 & 0xff) / 255F;
		float f3 = (color & 0xff) / 255F;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(f1, f2, f3, f);
		glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex2d(x, y);
		for (int i = 0; i <= 360 * percent; i++)
		{
			double nx = MathHelper.sin(i * 3.141526f / 180) * radius;
			double ny = MathHelper.cos(i * 3.141526f / 180) * radius;
			GL11.glVertex2d(nx + x, ny + y);
		}
		glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glPopMatrix();
		GL11.glColor4f(1, 1, 1, 1);
	}

	public static void drawLoadingDonutWithoutSetup(float x, float y, double radius, float percent, float stripSize, int max, int color)
	{
		GL11.glPushMatrix();
		float f = (color >> 24 & 0xff) / 255F;
		float f1 = (color >> 16 & 0xff) / 255F;
		float f2 = (color >> 8 & 0xff) / 255F;
		float f3 = (color & 0xff) / 255F;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(f1, f2, f3, f);
		GL11.glTranslatef(x, y, 0);
		GL11.glRotatef(max / 2f * percent, 0, 0, 1);
		glBegin(GL11.GL_TRIANGLE_STRIP);
		for (int i = 0; i <= max * percent; i++)
		{
			double nx = MathHelper.sin(i * 3.141526f / 180) * (radius * (1 - stripSize));
			double ny = MathHelper.cos(i * 3.141526f / 180) * (radius * (1 - stripSize));
			GL11.glVertex2d(nx, ny);

			nx = MathHelper.sin(i * 3.141526f / 180) * radius;
			ny = MathHelper.cos(i * 3.141526f / 180) * radius;
			GL11.glVertex2d(nx, ny);
		}
		glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glPopMatrix();
		GL11.glColor4f(1, 1, 1, 1);
	}

	public static void drawModalRectWithCustomSizedText(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight)
	{
		float f4 = 1.0F / textureWidth;
		float f5 = 1.0F / textureHeight;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x, y + height, 0, u * f4, (v + height) * f5);
		tessellator.addVertexWithUV(x + width, y + height, 0, (u + width) * f4, (v + height) * f5);
		tessellator.addVertexWithUV(x + width, y, 0, (u + width) * f4, v * f5);
		tessellator.addVertexWithUV(x, y, 0, u * f4, v * f5);
		tessellator.draw();
	}

	/**
	 * Draws a solid color rectangle with the specified coordinates and color.
	 * Args: x1, y1, x2, y2, color
	 */
	public static void drawRect(int x1, int y1, int x2, int y2, int color)
	{
		mc.entityRenderer.setupOverlayRendering();

		int j1;

		if (x1 < x2)
		{
			j1 = x1;
			x1 = x2;
			x2 = j1;
		}

		if (y1 < y2)
		{
			j1 = y1;
			y1 = y2;
			y2 = j1;
		}
		float f3 = (color >> 24 & 255) / 255.0F;
		float f = (color >> 16 & 255) / 255.0F;
		float f1 = (color >> 8 & 255) / 255.0F;
		float f2 = (color & 255) / 255.0F;
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glColor4f(f, f1, f2, f3);
		tessellator.startDrawingQuads();
		tessellator.addVertex(x1, y2, 0);
		tessellator.addVertex(x2, y2, 0);
		tessellator.addVertex(x2, y1, 0);
		tessellator.addVertex(x1, y1, 0);
		tessellator.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
	}

	/**
	 * Draws a solid color rectangle with the specified coordinates and color.
	 * Args: x1, y1, x2, y2, color
	 */
	public static void drawRect(int x1, int y1, int x2, int y2, int r, int g, int b, int a)
	{
		mc.entityRenderer.setupOverlayRendering();

		int j1;

		if (x1 < x2)
		{
			j1 = x1;
			x1 = x2;
			x2 = j1;
		}

		if (y1 < y2)
		{
			j1 = y1;
			y1 = y2;
			y2 = j1;
		}
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glColor4f(r / 255f, g / 255f, b / 255f, a / 255f);
		tessellator.startDrawingQuads();
		tessellator.addVertex(x1, y2, 0);
		tessellator.addVertex(x2, y2, 0);
		tessellator.addVertex(x2, y1, 0);
		tessellator.addVertex(x1, y1, 0);
		tessellator.draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
	}

	public static void drawScaledCustomSizeModalRect(int x, int y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight)
	{
		float f4 = 1.0F / tileWidth;
		float f5 = 1.0F / tileHeight;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x, y + height, 0, u * f4, (v + vHeight) * f5);
		tessellator.addVertexWithUV(x + width, y + height, 0, (u + uWidth) * f4, (v + vHeight) * f5);
		tessellator.addVertexWithUV(x + width, y, 0, (u + uWidth) * f4, v * f5);
		tessellator.addVertexWithUV(x, y, 0, u * f4, v * f5);
		tessellator.draw();
	}

	/**
	 * Draws a texture on-screen
	 *
	 * @param x        The x position
	 * @param y        The y position
	 * @param textureX The x position of the texture
	 * @param textureY The y position of the texture
	 * @param width    The width on-screen
	 * @param height   The height on-screen
	 */
	public static void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
	{
		mc.entityRenderer.setupOverlayRendering();
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x, y + height, 1, textureX * f, (textureY + height) * f1);
		tessellator.addVertexWithUV(x + width, y + height, 1, (textureX + width) * f, (textureY + height) * f1);
		tessellator.addVertexWithUV(x + width, y, 1, (textureX + width) * f, textureY * f1);
		tessellator.addVertexWithUV(x, y, 1, textureX * f, textureY * f1);
		tessellator.draw();
	}

	/**
	 * Draws a texture on-screen
	 *
	 * @param x        The x position
	 * @param y        The y position
	 * @param textureX The x position of the texture
	 * @param textureY The y position of the texture
	 * @param width    The width on-screen
	 * @param height   The height on-screen
	 */
	public static void drawTexturedModalRectFloat(float x, float y, float textureX, float textureY, float width, float height)
	{
		mc.entityRenderer.setupOverlayRendering();
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 0, y + height, 1, (textureX + 0) * f, (textureY + height) * f1);
		tessellator.addVertexWithUV(x + width, y + height, 1, (textureX + width) * f, (textureY + height) * f1);
		tessellator.addVertexWithUV(x + width, y + 0, 1, (textureX + width) * f, (textureY + 0) * f1);
		tessellator.addVertexWithUV(x + 0, y + 0, 1, (textureX + 0) * f, (textureY + 0) * f1);
		tessellator.draw();
	}

	/**
	 * Draws an IIcon
	 *
	 * @param x      The x position
	 * @param y      The y position
	 * @param icon   The icon to draw
	 * @param width  The width of the icon
	 * @param height The height of the icon
	 */
	public static void drawTexturedModelRectFromIcon(int x, int y, IIcon icon, int width, int height)
	{
		mc.entityRenderer.setupOverlayRendering();
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x, y + height, 1, icon.getMinU(), icon.getMaxV());
		tessellator.addVertexWithUV(x + width, y + height, 1, icon.getMaxU(), icon.getMaxV());
		tessellator.addVertexWithUV(x + width, y, 1, icon.getMaxU(), icon.getMinV());
		tessellator.addVertexWithUV(x, y, 1, icon.getMinU(), icon.getMinV());
		tessellator.draw();
	}

	public static Vec3 lerp(Point a, Point b, float f)
	{
		float x = a.x * 2 + f * ((float)(b.x * 2) - (float)(a.x * 2));
		float y = a.y * 2 + f * ((float)(b.y * 2) - (float)(a.y * 2));
		return Vec3.createVectorHelper(x, y, 0);
	}

	public static ResourceLocation planetTextureFromDim(int dim)
	{
		if (dim == Resources.ConfigOptions.dimEndorId)
			return Resources.endorTexture;
		else if (dim == Resources.ConfigOptions.dimHothId)
			return Resources.hothTexture;
		else if (dim == Resources.ConfigOptions.dimKashyyykId)
			return Resources.kashyyykTexture;
		else if (dim == Resources.ConfigOptions.dimTatooineId)
			return Resources.tatooineTexture;
		else if (dim == Resources.ConfigOptions.dimYavin4Id)
			return Resources.yavinTexture;

		return Resources.earthTexture;
	}

	/**
	 * Renders a progress bar on-screen
	 *
	 * @param caption The string to display above the bar
	 * @param percent The percentage full of the bar (0-1)
	 * @param xpBar   True if you want to draw an XP-style bar instead of a boss bar
	 */
	public static void renderBarOnscreen(String caption, float percent, boolean xpBar)
	{
		mc.entityRenderer.setupOverlayRendering();
		mc.getTextureManager().bindTexture(icons);
		GL11.glEnable(3042);
		FontRenderer fontrenderer = mc.fontRenderer;
		ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int i = scaledresolution.getScaledWidth();
		short short1 = 182;
		int j = i / 2 - short1 / 2;
		int k = (int)(percent * (short1 + 1));
		byte b0 = 12;
		drawTexturedModalRect(j, b0, 0, xpBar ? 64 : 74, short1, 5);
		drawTexturedModalRect(j, b0, 0, xpBar ? 64 : 74, short1, 5);
		if (k > 0)
			drawTexturedModalRect(j, b0, 0, xpBar ? 69 : 79, k, 5);
		fontrenderer.drawStringWithShadow(caption, i / 2 - fontrenderer.getStringWidth(caption) / 2, b0 - 10, 16777215);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(3042);
	}

	/**
	 * Renders some hearts on-screen
	 *
	 * @param x      The x position of the hearts
	 * @param y      The y position of the hearts
	 * @param amount The amount of full hearts, in half hearts
	 * @param max    The maximum number of hearts displayed, in half hearts
	 * @param wrap   The amount of hearts per row, -1 for infinite
	 */
	public static void renderHearts(int x, int y, int amount, int max, int wrap)
	{
		mc.entityRenderer.setupOverlayRendering();
		mc.getTextureManager().bindTexture(icons);
		GL11.glEnable(3042);
		int sx = x;
		ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		scaledresolution.getScaledWidth();
		scaledresolution.getScaledHeight();
		int healthRows = MathHelper.ceiling_float_int(max / 2.0F / 10.0F);
		int rowHeight = Math.max(10 - (healthRows - 2), 3);
		int MARGIN = 16;
		for (int i = 0; i < MathHelper.ceiling_float_int(max / 2.0F); i++)
		{
			drawTexturedModalRect(x, y, 16, 0, 9, 9);
			if (i * 2 + 1 < amount)
				drawTexturedModalRect(x, y, MARGIN + 36, 0, 9, 9);
			else if (i * 2 + 1 == amount)
				drawTexturedModalRect(x, y, MARGIN + 45, 0, 9, 9);
			if ((i + 1) % wrap == 0 && wrap != -1)
			{
				x = sx;
				y += 8;
			}
			else
				x += 8;
		}
		GL11.glDisable(3042);
	}

	/**
	 * Renders an item on-screen
	 *
	 * @param x    The x position of the item
	 * @param y    The y position of the item
	 * @param item The item to render (stackSize == 0 ? don't render text :
	 *             render stack size)
	 */
	public static void renderItem(int x, int y, ItemStack item)
	{
		mc.entityRenderer.setupOverlayRendering();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glDisable(3008);
		GL11.glDisable(2929);
		RenderItem r = RenderItem.getInstance();
		r.renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), item, x, y, true);
		r.renderItemOverlayIntoGUI(mc.fontRenderer, mc.getTextureManager(), item, x, y, item.stackSize > 0 ? String.valueOf(item.stackSize) : "");
		GL11.glEnable(3008);
		GL11.glEnable(32826);
		GL11.glEnable(2929);
		GL11.glDisable(3042);
		net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
	}

	/**
	 * Renders a progress bar on-screen
	 *
	 * @param x       The x position
	 * @param y       The y position
	 * @param percent The percentage full of the bar (0-1)
	 * @param jedi    True if you want to draw an XP-style bar instead of a boss bar
	 */
	public static void renderLightsaberBarOnscreen(int x, int y, float percent, boolean jedi)
	{
		mc.entityRenderer.setupOverlayRendering();
		mc.getTextureManager().bindTexture(swIcons);
		GL11.glEnable(3042);
		int j = x + 25;
		int k = (int)(percent * 100);
		int b0 = y + 1;
		drawTexturedModalRectFloat(j, b0, 0, 19, 99, 3.6f);
		if (k > 0)
			drawTexturedModalRectFloat(j, b0, 0, jedi ? 15.6f : 22.5f, k, 3.6f);

		drawTexturedModalRectFloat(j - (jedi ? 25 : 24), b0 - 0.75f, 0, jedi ? 0 : 6f, 26.5f, 6);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(3042);
	}

	public static void renderOrderLogo(int x, int y, boolean jedi)
	{
		mc.entityRenderer.setupOverlayRendering();
		mc.getTextureManager().bindTexture(swIcons);
		GL11.glEnable(3042);

		drawTexturedModalRectFloat(x, y, jedi ? 0 : 17, 26, 16, 16);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(3042);
	}

	/**
	 * Renders an overlay on-screen, stretching to fit
	 *
	 * @param PGuiTexture The resource to render
	 */
	public static void renderOverlay(ResourceLocation PGuiTexture)
	{
		mc.entityRenderer.setupOverlayRendering();
		ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int k = scaledresolution.getScaledWidth();
		int l = scaledresolution.getScaledHeight();
		GL11.glDisable(2929);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glDepthMask(false);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(3008);
		mc.getTextureManager().bindTexture(PGuiTexture);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0, l, -90, 0, 1);
		tessellator.addVertexWithUV(k, l, -90, 1, 1);
		tessellator.addVertexWithUV(k, 0, -90, 1, 0);
		tessellator.addVertexWithUV(0, 0, -90, 0, 0);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
		GL11.glEnable(2929);
		GL11.glEnable(3008);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	/**
	 * Renders an overlay on-screen, stretching to fit
	 *
	 * @param PGuiTexture The resource to render
	 * @param offsetX     The x offset
	 * @param offsetY     The y offset
	 */
	public static void renderOverlay(ResourceLocation PGuiTexture, float offsetX, float offsetY)
	{
		mc.entityRenderer.setupOverlayRendering();
		ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int k = scaledresolution.getScaledWidth();
		int l = scaledresolution.getScaledHeight();
		GL11.glDisable(2929);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glDepthMask(false);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(3008);
		mc.getTextureManager().bindTexture(PGuiTexture);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0 + offsetX, l + offsetY, -90, 0, 1);
		tessellator.addVertexWithUV(k + offsetX, l + offsetY, -90, 1, 1);
		tessellator.addVertexWithUV(k + offsetX, 0 + offsetY, -90, 1, 0);
		tessellator.addVertexWithUV(0 + offsetX, 0 + offsetY, -90, 0, 0);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
		GL11.glEnable(2929);
		GL11.glEnable(3008);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	/**
	 * Renders an overlay on-screen
	 *
	 * @param PGuiTexture The resource to render
	 * @param x           The x position of the overlay
	 * @param y           The y position of the overlay
	 * @param w           The width of the overlay
	 * @param h           The height of the overlay
	 */
	public static void renderOverlay(ResourceLocation PGuiTexture, float x, float y, float w, float h)
	{
		mc.entityRenderer.setupOverlayRendering();
		GL11.glDisable(2929);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glDepthMask(false);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(3008);
		mc.getTextureManager().bindTexture(PGuiTexture);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x, h + y, -90, 0, 1);
		tessellator.addVertexWithUV(w + x, h + y, -90, 1, 1);
		tessellator.addVertexWithUV(w + x, y, -90, 1, 0);
		tessellator.addVertexWithUV(x, y, -90, 0, 0);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
		GL11.glEnable(2929);
		GL11.glEnable(3008);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	/**
	 * Renders a string on-screen
	 *
	 * @param x      The x position of the string
	 * @param y      The y position of the string
	 * @param string The string to render
	 * @param color  The color of the string
	 */
	public static void renderString(int x, int y, String string, int color)
	{
		mc.entityRenderer.setupOverlayRendering();
		mc.fontRenderer.drawStringWithShadow(string, x, y, color);
	}

	/**
	 * Renders a vignette on-screen
	 *
	 * @param brightness The brightness (0-1)
	 */
	public static void renderVignette(float brightness)
	{
		GL11.glEnable(3042);
		mc.entityRenderer.setupOverlayRendering();
		ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

		int width = scaledresolution.getScaledWidth();
		int height = scaledresolution.getScaledHeight();

		brightness = 1.0F - brightness;
		if (brightness < 0.0F)
			brightness = 0.0F;
		if (brightness > 1.0F)
			brightness = 1.0F;

		prevVignetteBrightness = (float)(prevVignetteBrightness + (brightness - prevVignetteBrightness) * 0.01D);

		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		OpenGlHelper.glBlendFunc(0, 769, 1, 0);
		GL11.glColor4f(prevVignetteBrightness, prevVignetteBrightness, prevVignetteBrightness, 1.0F);

		mc.getTextureManager().bindTexture(vignetteTexPath);

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0, height, -90, 0, 1);
		tessellator.addVertexWithUV(width, height, -90, 1, 1);
		tessellator.addVertexWithUV(width, 0, -90, 1, 0);
		tessellator.addVertexWithUV(0, 0, -90, 0, 0);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(2929);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glDisable(3042);
	}
}
