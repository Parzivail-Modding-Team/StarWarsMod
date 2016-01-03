package com.parzi.starwarsmod.rendering.gui;

import java.text.NumberFormat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import org.lwjgl.opengl.GL11;

import com.parzi.starwarsmod.StarWarsMod;
import com.parzi.starwarsmod.jedirobes.ArmorJediRobes;
import com.parzi.starwarsmod.jedirobes.powers.Power;
import com.parzi.starwarsmod.utils.ForceUtils;
import com.parzi.starwarsmod.utils.GlPalette;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GuiPSWMOverlay extends Gui
{
	private Minecraft mc;
	private RenderItem r;
	private NumberFormat format;

	public GuiPSWMOverlay(Minecraft mc)
	{
		this.mc = mc;
		this.r = RenderItem.getInstance();

		format = NumberFormat.getInstance();
	}

	public int countCredits()
	{
		int credits = 0;
		for (ItemStack stack : this.mc.thePlayer.inventory.mainInventory)
			if (stack != null && stack.getItem() != null)
			{
				if (stack.getItem() == StarWarsMod.imperialCredit)
					credits += stack.stackSize;
				if (stack.getItem() == StarWarsMod.silverImperialCredit)
					credits += stack.stackSize * 9;
				if (stack.getItem() == StarWarsMod.goldImperialCredit)
					credits += stack.stackSize * 81;
			}
		return credits;
	}

	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent event)
	{
		if (event.type != RenderGameOverlayEvent.ElementType.HOTBAR || !StarWarsMod.enableCreditsOverlay)
			return;

		RenderHelper.disableStandardItemLighting();

		this.mc.fontRenderer.drawStringWithShadow("PSWM v" + StarWarsMod.VERSION, 5, 5, 16777215);
		StarWarsMod.pgui.renderItem(23, 12, new ItemStack(StarWarsMod.imperialCredit, this.countCredits()));

		if (mc.thePlayer.inventory.armorItemInSlot(2) != null && mc.thePlayer.inventory.armorItemInSlot(2).getItem() == StarWarsMod.jediRobes)
		{
			ScaledResolution r = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

			ItemStack robes = mc.thePlayer.inventory.armorItemInSlot(2);
			NBTTagCompound tags = robes.stackTagCompound;

			int xp = ArmorJediRobes.getXP(robes);
			int maxxp = ArmorJediRobes.getMaxXP(robes);

			boolean isJedi = tags.hasKey("side") && tags.getString("side") != ArmorJediRobes.SIDE_SITH;
			int guiColor = isJedi ? GlPalette.GREEN_APPLE : GlPalette.RED_ORANGE;

			RenderHelper.disableStandardItemLighting();
			StarWarsMod.pgui.renderLightsaberBarOnscreen(2, r.getScaledHeight() - 10, (float)xp / (float)maxxp, isJedi);

			GL11.glPushMatrix();
			GL11.glScalef(0.5f, 0.5f, 0.5f);

			if (ForceUtils.activePower != null)
				this.drawString(mc.fontRenderer, ForceUtils.activePower.getLocalizedName(), r.getScaledWidth() + 3, r.getScaledHeight() - 10, guiColor);

			int y = (r.getScaledHeight() - 25) * 2;
			for (Power cooling : ForceUtils.coolingPowers)
			{
				StarWarsMod.pgui.drawLoadingCircleWithoutSetup(15, y, 10, cooling.recharge / cooling.rechargeTime, guiColor);
				this.drawString(mc.fontRenderer, cooling.getLocalizedName() + ": " + ((int)cooling.recharge) + "s", 30, y - 3, GlPalette.WHITE);
				y -= 22;
			}

			this.drawCenteredString(mc.fontRenderer, "FORCE XP: " + format.format(xp) + "/" + format.format(maxxp), 145, (r.getScaledHeight() - 15) * 2, guiColor);
			GL11.glPopMatrix();
		}

		RenderHelper.disableStandardItemLighting();
	}
}
/*
 * Location: C:\Users\Colby\Downloads\Parzi's Star Wars Mod
 * v1.2.0-dev7.jar!\com\parzi\starwarsmod\rendering\gui\GuiCreditsOverlay.class
 * Java compiler version: 6 (50.0) JD-Core Version: 0.7.1
 */