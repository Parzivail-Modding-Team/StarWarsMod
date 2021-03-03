package com.parzivail.pswm.gui;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.force.Cron;
import com.parzivail.pswm.network.MessageRobesStringNBT;
import com.parzivail.util.ui.GLPalette;
import com.parzivail.util.ui.LangUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiJediSith extends GuiScreen
{
	private static final ResourceLocation guiTexture = new ResourceLocation(Resources.MODID, "textures/gui/icons.png");

	private GuiButtonJedi jediButton;
	private GuiButtonSith sithButton;

	private ItemStack stack;
	private EntityPlayer player;

	public GuiJediSith(EntityPlayer player)
	{
		this.mc = Minecraft.getMinecraft();
		this.stack = Cron.getHolocron(player);
		this.player = player;
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button.enabled)
			if (button.id == this.jediButton.id)
			{
				StarWarsMod.network.sendToServer(new MessageRobesStringNBT(this.player, Resources.nbtSide, Cron.SIDE_JEDI));
				this.stack.stackTagCompound.setString(Resources.nbtSide, Cron.SIDE_JEDI);
				StarWarsMod.mc.currentScreen = null;
				StarWarsMod.mc.setIngameFocus();
			}
			else if (button.id == this.sithButton.id)
			{
				StarWarsMod.network.sendToServer(new MessageRobesStringNBT(this.player, Resources.nbtSide, Cron.SIDE_SITH));
				this.stack.stackTagCompound.setString(Resources.nbtSide, Cron.SIDE_SITH);
				StarWarsMod.mc.currentScreen = null;
				StarWarsMod.mc.setIngameFocus();
			}
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int p_571_1_, int p_571_2_, float p_571_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		ScaledResolution r = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);

		this.mc.getTextureManager().bindTexture(guiTexture);
		this.drawTexturedModalRect((r.getScaledWidth() - 248) / 2, (r.getScaledHeight() - 166) / 2, 0, 60, 248, 166);

		int x = r.getScaledWidth() / 2;
		int y = r.getScaledHeight() / 2;
		int dy = y - 80;
		this.drawCenteredString(this.mc.fontRenderer, LangUtils.translate("you.have.a.nightmare"), x, dy += 10, GLPalette.WHITE);
		this.drawCenteredString(this.mc.fontRenderer, LangUtils.translate("a.dark.man.speaks.to.you"), x, dy += 10, GLPalette.WHITE);
		dy += 10;
		this.drawCenteredString(this.mc.fontRenderer, LangUtils.translate("he.wants.to.share.his.secrets"), x, dy += 10, GLPalette.LIGHT_RED);
		this.drawCenteredString(this.mc.fontRenderer, LangUtils.translate("his.dark.knowledge"), x, dy += 10, GLPalette.RED);
		dy += 10;
		this.drawCenteredString(this.mc.fontRenderer, LangUtils.translate("do.you.accept"), x, dy += 10, GLPalette.WHITE);

		this.drawString(this.mc.fontRenderer, LangUtils.translate("never"), x - 73, y + 19, GLPalette.WHITE);
		this.drawString(this.mc.fontRenderer, LangUtils.translate("show.me"), x + 38, y + 19, GLPalette.WHITE);

		super.drawScreen(p_571_1_, p_571_2_, p_571_3_);
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	@Override
	public void initGui()
	{
		ScaledResolution r = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
		int x = r.getScaledWidth() / 2;
		int y = r.getScaledHeight() / 2;

		this.jediButton = new GuiButtonJedi(1, x - 37, y + 15);
		this.buttonList.add(this.jediButton);

		this.sithButton = new GuiButtonSith(2, x + 20, y + 15);
		this.buttonList.add(this.sithButton);
		/*
		 * if (keyCode == 1) { this.mc.displayGuiScreen((GuiScreen)null);
		 * this.mc.setIngameFocus(); }
		 */
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	/**
	 * Fired when a key is typed. This is the equivalent of
	 * KeyListener.keyTyped(KeyEvent e).
	 */
	@Override
	protected void keyTyped(char typedChar, int keyCode)
	{
		// don't exit on escape, force ship to choose
	}
}
