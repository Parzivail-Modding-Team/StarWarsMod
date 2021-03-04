package com.parzivail.util.ui;

import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.quest.QuestUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class OutlineButtonCreditCounter extends OutlineButton
{
	private EntityPlayer player;
	int currentCost = -1;

	public OutlineButtonCreditCounter(int id, int x, int y, int w, int h, EntityPlayer player)
	{
		super(id, x, y, w, h, "", false);
		this.player = player;
	}

	public boolean isHover()
	{
		return this.field_146123_n;
	}

	public void setCurrentCost(int currentCost)
	{
		this.currentCost = currentCost;
	}

	public int getCurrentCost()
	{
		return currentCost;
	}

	@Override
	public void drawButton(Minecraft mc, int mX, int mY)
	{
		GL11.glPushMatrix();
		if (this.visible)
		{
			FontRenderer fontrenderer = mc.fontRenderer;
			this.field_146123_n = mX >= this.xPosition && mY >= this.yPosition && mX < this.xPosition + this.width && mY < this.yPosition + this.height;
			int k = this.getHoverState(this.field_146123_n);
			this.mouseDragged(mc, mX, mY);

			int textColor = GLPalette.WHITE;

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glLineWidth(3);
			switch (k)
			{
				case 0:
					textColor = GLPalette.GREY;
					GLPalette.glColorI(GLPalette.DARK_GREY);
					GFX.drawRectangle(this.xPosition, this.yPosition, this.width, this.height, false);
					break;
				case 1:
					GLPalette.glColorI(GLPalette.DARK_SW_YELLOW);
					GFX.drawRectangle(this.xPosition, this.yPosition, this.width, this.height, false);
					break;
				case 2:
					GLPalette.glColorI(GLPalette.SW_YELLOW);
					GFX.drawRectangle(this.xPosition, this.yPosition, this.width, this.height, false);
					break;
				default:
					break;
			}
			GL11.glEnable(GL11.GL_TEXTURE_2D);

			int c = QuestUtils.getPlayerBronzeCredits(player);

			if (c != Integer.MAX_VALUE)
			{
				GFX.drawTextShadow(fontrenderer, c + (currentCost != -1 ? " - " + currentCost : ""), this.xPosition + 15, this.yPosition + 6, 1, textColor);
				GFX.renderItem(this.xPosition, this.yPosition + 2, new ItemStack(StarWarsItems.imperialCredit, 0));

				if (k == 2 && currentCost != -1)
					GFX.drawTooltip(mX + 2, mY + 2, "Buy");
			}
			else
			{
				GFX.drawTextShadowCenter(fontrenderer, "Get Item", this.xPosition + (this.width / 2), this.yPosition + 6, 1, textColor);

				if (k == 2 && currentCost != -1)
					GFX.drawTooltip(mX + 2, mY + 2, currentCost + " Credits");
			}
		}
		GL11.glPopMatrix();
	}
}
