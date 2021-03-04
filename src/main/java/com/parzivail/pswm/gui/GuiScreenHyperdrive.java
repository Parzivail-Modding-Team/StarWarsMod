package com.parzivail.pswm.gui;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.dimension.PlanetInformation;
import com.parzivail.pswm.dimension.TradeRoute;
import com.parzivail.pswm.items.ItemQuestLog;
import com.parzivail.pswm.models.ModelPlanetCube;
import com.parzivail.pswm.models.vehicles.*;
import com.parzivail.pswm.network.MessageHyperdrive;
import com.parzivail.pswm.network.MessageTransferHyperdrive;
import com.parzivail.pswm.rendering.vehicles.*;
import com.parzivail.pswm.vehicles.VehicSnowspeeder;
import com.parzivail.util.math.Animation;
import com.parzivail.util.math.FPoint;
import com.parzivail.util.math.MathUtils;
import com.parzivail.util.ui.*;
import com.parzivail.util.vehicle.VehicleAirBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

@SideOnly(Side.CLIENT)
public class GuiScreenHyperdrive extends GuiScreen
{
	private static class MovingShip
	{
		FPoint position;
		TradeRoute route;
		Animation animation;
		int model;
		float angle;

		MovingShip(TradeRoute route, int model, boolean reverse)
		{
			this.position = route.getPointAlongPath(0);
			this.route = route;
			this.animation = new Animation((int)(100 * route.getRoute().getLength()), false, false);
			this.animation.setReverse(reverse);
			this.model = model;
		}

		public void start()
		{
			this.animation.start();
		}

		public void tick()
		{
			this.position = route.getPointAlongPath(animation.getTick() / (float)animation.getLength());
			FPoint p = route.getPointAlongPath((animation.getTick() + (animation.isReverse() ? -1 : 1)) / (float)animation.getLength());
			this.angle = (float)Math.toDegrees(Math.atan2(p.x - this.position.x, this.position.y - p.y));
		}
	}

	private EntityPlayer player;
	private ItemStack qlog;

	private static final ResourceLocation background = new ResourceLocation(Resources.MODID, "textures/gui/space.png");
	private static final ResourceLocation galaxy = new ResourceLocation(Resources.MODID, "textures/gui/galaxy.png");
	private static ScaledResolution r;

	private boolean isZoom = false;
	private PlanetInformation zoomPlanet;
	private PlanetInformation oldZoomPlanet;

	private boolean canJump;
	public boolean didComeFromBlock = false;

	private AnimationZoom animationZoom;

	private OutlineButton buttonClose;
	private OutlineButton buttonEatHyperdrive;
	private OutlineButton buttonTravel;

	private TradeRoute perlemian = new TradeRoute(new FPoint[] { new FPoint(6.1f, 4.9f), new FPoint(8, 4), new FPoint(10, 3.7f), new FPoint(11.8f, 3), new FPoint(13, 1), new FPoint(13.4f, 0.2f) });
	private TradeRoute corellianRun = new TradeRoute(new FPoint[] { new FPoint(6.1f, 4.9f), new FPoint(6.5f, 5.7f), new FPoint(7.8f, 8.4f), new FPoint(10, 10), new FPoint(12.75f, 12.7f), new FPoint(14.3f, 13.5f) });
	private TradeRoute corellianSpine = new TradeRoute(new FPoint[] { new FPoint(7.3f, 9), new FPoint(6, 11.65f), new FPoint(5.5f, 13), new FPoint(5, 15.5f) });
	private TradeRoute rimma = new TradeRoute(new FPoint[] { new FPoint(5.8f, 8.2f), new FPoint(6.5f, 9.8f), new FPoint(7, 12f), new FPoint(6.8f, 12.5f), new FPoint(6.4f, 14.7f) });
	private TradeRoute hydian = new TradeRoute(new FPoint[] { new FPoint(11.8f, 0.5f), new FPoint(10.2f, 1), new FPoint(9.5f, 2), new FPoint(7.8f, 3), new FPoint(6.7f, 5.2f), new FPoint(7.6f, 6), new FPoint(9, 8.7f), new FPoint(8, 11), new FPoint(4.8f, 14.7f), new FPoint(4, 15) });

	private TradeRoute[] routes = new TradeRoute[] { perlemian, corellianRun, corellianSpine, rimma, hydian };

	private ArrayList<MovingShip> ships = new ArrayList<>();

	private ModelXWingNew modelXWing = new ModelXWingNew();
	private ModelSnowspeeder modelSnowspeeder = new ModelSnowspeeder();
	private ModelAWing modelAWing = new ModelAWing();
	private ModelYWing modelYWing = new ModelYWing();
	private ModelTIE modelTIE = new ModelTIE();
	private ModelTIEAdvanced modelTIEAdvanced = new ModelTIEAdvanced();
	private ModelTIEInterceptor modelTIEInterceptor = new ModelTIEInterceptor();
	private ModelTIEBomber modelTIEBomber = new ModelTIEBomber();
	private ModelSkyhopper modelSkyhopper = new ModelSkyhopper();

	private final static int XWING = 0;
	private final static int YWING = 1;
	private final static int TIE = 2;
	private final static int TIEADV = 3;
	private final static int TIEINT = 4;
	private final static int AWING = 5;
	private final static int TIEBMB = 6;

	public GuiScreenHyperdrive(EntityPlayer player)
	{
		this.mc = Minecraft.getMinecraft();
		this.player = player;

		canJump = player.ridingEntity instanceof VehicleAirBase && !(player.ridingEntity instanceof VehicSnowspeeder);
	}

	@Override
	public void initGui()
	{
		r = new ScaledResolution(StarWarsMod.mc, StarWarsMod.mc.displayWidth, StarWarsMod.mc.displayHeight);

		Consumer<OutlineButton> transform = outlineButton -> {
			GL11.glTranslatef(1.9f, -1f, 180);
			GL11.glScalef(0.3f, 0.3f, 0.3f);
			if ((zoomPlanet != null && PlanetInformation.getPlanet(outlineButton.displayString).getName().equalsIgnoreCase(zoomPlanet.getName())) || (oldZoomPlanet != null && PlanetInformation.getPlanet(outlineButton.displayString).getName().equalsIgnoreCase(oldZoomPlanet.getName())))
				P3D.glScalef((animationZoom.getTick() + 2) / 2f);
			GL11.glRotatef(-30, 1, 0, 0);
			GL11.glRotatef(((System.currentTimeMillis() + PlanetInformation.getPlanet(outlineButton.displayString).getName().hashCode()) / 50) % 360, 0, 1, 0);
		};

		Consumer<OutlineButton> postRender = outlineButton -> {
			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glRotatef(((System.currentTimeMillis() + PlanetInformation.getPlanet(outlineButton.displayString).getName().hashCode()) / 50) % 360 * -2, 0, 1, 0);
			GL11.glScalef(0.12f, 0.12f, 0.12f);
			GL11.glScalef(-1, 1, -1);
			GL11.glTranslatef(-80, 70, 0);
			switch (outlineButton.displayString)
			{
				case "Hoth":
				case "Dagobah":
				case "Yavin4":
					StarWarsMod.mc.renderEngine.bindTexture(RenderXWing.texture);
					this.modelXWing.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
					break;
				case "Endor":
				case "DeathStar":
					StarWarsMod.mc.renderEngine.bindTexture(RenderTIE.texture);
					this.modelTIE.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
					break;
				case "Tatooine":
					GL11.glTranslatef(-20, 0, 0);
					StarWarsMod.mc.renderEngine.bindTexture(RenderSkyhopper.texture);
					this.modelSkyhopper.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
					break;
				default:
					break;
			}
			GL11.glTranslatef(160, 0, 0);
			GL11.glScalef(1, 1, -1);
			switch (outlineButton.displayString)
			{
				case "Hoth":
					StarWarsMod.mc.renderEngine.bindTexture(RenderSnowspeeder.texture);
					this.modelSnowspeeder.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
					break;
				case "Endor":
					StarWarsMod.mc.renderEngine.bindTexture(RenderTIEInterceptor.texture);
					this.modelTIEInterceptor.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
					break;
				case "DeathStar":
					StarWarsMod.mc.renderEngine.bindTexture(RenderTIEAdvanced.texture);
					this.modelTIEAdvanced.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
					break;
				case "Yavin4":
					StarWarsMod.mc.renderEngine.bindTexture(RenderYWing.texture);
					this.modelYWing.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
					break;
				default:
					break;
			}
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glPopMatrix();
		};

		animationZoom = new AnimationZoom();

		int id = 0;

		for (PlanetInformation info : Resources.planetInformation)
		{
			FPoint p = galaxyCoordsToXy(info.getPosition().x, info.getPosition().y);
			OutlineButtonModel planetButton = new OutlineButtonModel(id++, (int)p.x, (int)p.y, 4, 4);
			planetButton.displayString = info.getInternalName();
			planetButton.setup(new ModelPlanetCube(), info.getCubeTexture(), transform, postRender);
			planetButton.enabled = canMoveToPlanet(info.getInternalName());
			buttonList.add(planetButton);
		}

		buttonClose = new OutlineButton(id++, 5, 5, 10, 10, "X", false);
		buttonList.add(buttonClose);

		qlog = ItemQuestLog.getQuestContainer(player);

		buttonEatHyperdrive = new OutlineButton(id++, 15, 215, 100, 20, "Program NavCom", false);
		buttonEatHyperdrive.enabled = !didComeFromBlock;
		buttonList.add(buttonEatHyperdrive);

		buttonTravel = new OutlineButton(id, 125, 215, 100, 20, "Jump to Lightspeed", false);
		buttonTravel.enabled = canJump && !didComeFromBlock;
		buttonList.add(buttonTravel);
	}

	private boolean canMoveToPlanet(String planet)
	{
		switch (planet)
		{
			case "AhchTo":
			case "Alderaan":
			case "Bespin":
			case "Coruscant":
			case "Dathomir":
			case "DeathStar":
			case "DQar":
			case "Geonosis":
			case "Jakku":
			case "Kamino":
			case "Kessel":
			case "Mandalore":
			case "MonCalamari":
			case "Mustafar":
			case "Naboo":
			case "Ryloth":
			case "Sullust":
			case "Takodana":
			case "Utapau":
				return false;
			default:
				return true;
		}
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button.enabled && button.visible)
		{
			if (!isZoom && PlanetInformation.getPlanet(button.displayString) != null)
			{
				this.oldZoomPlanet = null;
				this.zoomPlanet = PlanetInformation.getPlanet(button.displayString);
				this.animationZoom = new AnimationZoom();
				this.animationZoom.start();
				this.isZoom = true;
			}
			else if (button.id == buttonClose.id)
			{
				this.oldZoomPlanet = PlanetInformation.getPlanet(zoomPlanet.getInternalName());
				this.zoomPlanet = null;
				this.animationZoom = new AnimationZoom();
				this.animationZoom.setReverse(true);
				this.animationZoom.start();
				this.isZoom = false;
			}
			else if (button.id == buttonEatHyperdrive.id && zoomPlanet != null && qlog != null)
			{
				if (button instanceof OutlineButton)
				{
					OutlineButton outlineButton = (OutlineButton)button;
					if (!outlineButton.selected)
					{
						StarWarsMod.network.sendToServer(new MessageTransferHyperdrive(player, zoomPlanet.getInternalName()));
						if (player.inventory.consumeInventoryItem(zoomPlanet.getHyperdrive()) && qlog != null)
							ItemQuestLog.setHasHyperdrive(qlog, zoomPlanet.getInternalName());
					}
				}
			}
			else if (button.id == buttonTravel.id && ItemQuestLog.getHasHyperdrive(qlog, zoomPlanet.getInternalName()) && player.dimension != zoomPlanet.getDimensionId())
			{
				AnimationHyperspace animationHyperspace = new AnimationHyperspace(3500, false);
				animationHyperspace.setOnAnimationEnd(animation -> {
					if (player.dimension != zoomPlanet.getDimensionId())
					{
						player.timeUntilPortal = 20;
						StarWarsMod.network.sendToServer(new MessageHyperdrive(player, zoomPlanet.getDimensionId()));
						Lumberjack.debug("move");
					}
				});
				StarWarsMod.mc.displayGuiScreen(null);
				animationHyperspace.start();
			}

			if (qlog != null)
				if (zoomPlanet != null && ItemQuestLog.getHasHyperdrive(qlog, zoomPlanet.getInternalName()))
				{
					buttonEatHyperdrive.displayString = LangUtils.translate("course.plotted");
					buttonEatHyperdrive.selected = true;
				}
				else
				{
					buttonEatHyperdrive.displayString = LangUtils.translate("program.navcom");
					buttonEatHyperdrive.selected = false;
				}
			else
			{
				buttonEatHyperdrive.selected = false;
				buttonEatHyperdrive.enabled = false;
				buttonTravel.selected = false;
				buttonTravel.enabled = false;
			}
		}
	}

	private void drawStars()
	{
		Tessellator tessellator = Tessellator.instance;
		this.mc.getTextureManager().bindTexture(background);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		float f = 200.0F;
		tessellator.startDrawingQuads();
		tessellator.setColorOpaque_I(0xFFFFFFFF);
		tessellator.addVertexWithUV(0.0D, this.height, 0.0D, 0.0D, this.height / f + 1);
		tessellator.addVertexWithUV(this.width, this.height, 0.0D, this.width / f, this.height / f + 1);
		tessellator.addVertexWithUV(this.width, 0.0D, 0.0D, this.width / f, 1);
		tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 1);
		tessellator.draw();
	}

	private void drawGalaxy()
	{
		Tessellator tessellator = Tessellator.instance;
		this.mc.getTextureManager().bindTexture(galaxy);
		tessellator.startDrawingQuads();
		tessellator.setColorOpaque_I(GLPalette.LIGHT_GREY);
		tessellator.addVertexWithUV(0.0D, 256, 0.0D, 0.0D, 2);
		tessellator.addVertexWithUV(256, 256, 0.0D, 1, 2);
		tessellator.addVertexWithUV(256, 0.0D, 0.0D, 1, 1);
		tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 1);
		tessellator.draw();
	}

	@Override
	public void drawScreen(int mX, int mY, float p)
	{
		this.drawStars();

		float cX = r.getScaledWidth() / 2f;
		float cY = r.getScaledHeight() / 2f;

		if (zoomPlanet == null && animationZoom.getTick() == 0)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef(cX - 170, cY - 165, 0);
			P3D.glScalef(1.3f);
			this.drawGalaxy();
			GL11.glPopMatrix();
		}

		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		if (zoomPlanet == null && animationZoom.getTick() == 0)
		{
			GL11.glLineWidth(2);

			GLPalette.glColorI(GLPalette.BLACK);
			GFX.drawDashedCircle(cX - 50, cY - 22, 20, 5);

			GLPalette.glColorI(GLPalette.DARK_GREY);
			GFX.drawDashedCircle(cX - 50, cY - 22, 40, 3);

			GLPalette.glColorI(GLPalette.GREY);
			GFX.drawDashedCircle(cX - 50, cY - 22, 55, 3);

			GL11.glPushMatrix();
			GL11.glScalef(1, 1.15f, 1);
			GLPalette.glColorI(GLPalette.LIGHT_GREY);
			GFX.drawDashedCircle(cX - 50, cY / 1.15f - 12, 60, 3);
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			GL11.glScalef(1.25f, 1.15f, 1);
			GLPalette.glColorI(GLPalette.OFF_WHITE);
			GFX.drawDashedCircle(cX / 1.25f - 30, cY / 1.15f - 10, 70, 3);
			GL11.glPopMatrix();
		}

		GL11.glEnable(GL11.GL_TEXTURE_2D);

		if (zoomPlanet == null && animationZoom.getTick() == 0)
		{
			GL11.glPushMatrix();
			GL11.glScalef(0.5f, 0.5f, 1);
			StarWarsMod.mc.fontRenderer.drawString(LangUtils.translate("deep.core"), (int)cX * 2 - 125, (int)cY * 2 - 44, GLPalette.BLACK);
			StarWarsMod.mc.fontRenderer.drawString(LangUtils.translate("core"), (int)cX * 2 - 111, (int)cY * 2 + 12, GLPalette.DARK_GREY);
			StarWarsMod.mc.fontRenderer.drawString(LangUtils.translate("colonies"), (int)cX * 2 - 105, (int)cY * 2 + 45, GLPalette.GREY);
			StarWarsMod.mc.fontRenderer.drawString(LangUtils.translate("inner.rim"), (int)cX * 2 - 98, (int)cY * 2 + 85, GLPalette.GREY);
			StarWarsMod.mc.fontRenderer.drawString(LangUtils.translate("mid.rim"), (int)cX * 2 - 88, (int)cY * 2 + 125, GLPalette.OFF_WHITE);
			StarWarsMod.mc.fontRenderer.drawString(LangUtils.translate("outer.rim"), (int)cX * 2 - 78, (int)cY * 2 + 165, GLPalette.WHITE);
			GL11.glPopMatrix();
		}

		this.buttonList.stream().filter(b -> PlanetInformation.getPlanet(((GuiButton)b).displayString) != null).forEach(b -> {
			OutlineButtonModel buttonModel = (OutlineButtonModel)b;
			PlanetInformation info = PlanetInformation.getPlanet(buttonModel.displayString);
			FPoint pt = galaxyCoordsToXy(info.getPosition().x, info.getPosition().y);

			if (info == zoomPlanet || info == oldZoomPlanet)
			{
				buttonModel.xPosition = (int)MathUtils.lerp(pt.x, (float)(r.getScaledWidth_double() / 2f) - 120, (float)animationZoom.getTick() / animationZoom.getLength());
				buttonModel.yPosition = (int)MathUtils.lerp(pt.y, (float)(r.getScaledHeight_double() / 2f) - 70, (float)animationZoom.getTick() / animationZoom.getLength());
			}

			buttonModel.visible = info == zoomPlanet || info == oldZoomPlanet || (zoomPlanet == null && animationZoom.getTick() == 0);

			if (buttonModel.visible && buttonModel.isHover() && canMoveToPlanet(info.getInternalName()))
			{
				StarWarsMod.mc.fontRenderer.drawString(info.getName(), 4, 4, GLPalette.BRIGHT_YELLOW);
				int color = GLPalette.BRIGHT_YELLOW;
				if (info.getAffiliation().equals(Resources.allegianceImperial) || info.getAffiliation().equals(Resources.allegianceSith))
					color = GLPalette.BRIGHT_RED;
				else if (info.getAffiliation().equals(Resources.allegianceRebel) || info.getAffiliation().equals(Resources.allegianceJedi))
					color = GLPalette.BRIGHT_BLUE;
				String aff = ("Affiliation: " + info.getAffiliation());
				GL11.glPushMatrix();
				GL11.glScalef(0.5f, 0.5f, 1);
				StarWarsMod.mc.fontRenderer.drawString(aff, 8, 32, color);
				GL11.glPopMatrix();
			}
		});

		GL11.glEnable(GL11.GL_LIGHTING);
		RenderHelper.enableStandardItemLighting();

		buttonClose.visible = buttonEatHyperdrive.visible = buttonTravel.visible = zoomPlanet != null && animationZoom.isDone();

		PlanetInformation planet = zoomPlanet;
		if (planet == null)
			planet = oldZoomPlanet;

		if (planet != null)
		{
			GL11.glDisable(GL11.GL_LIGHTING);
			GLPalette.glColorI(GLPalette.WHITE);
			GL11.glPushMatrix();
			GL11.glScalef(2, 2, 1);
			StarWarsMod.mc.fontRenderer.drawString(planet.getName().substring(0, (int)MathUtils.lerp(0, planet.getName().length(), (float)animationZoom.getTick() / animationZoom.getLength())), 160, 6, GLPalette.BRIGHT_YELLOW);
			GL11.glPopMatrix();
			int y = 32;
			String[] d = TextUtils.splitIntoLine(planet.getDescription(), 40);
			for (String d1 : d)
				StarWarsMod.mc.fontRenderer.drawString(d1.substring(0, (int)MathUtils.lerp(0, d1.length(), (float)animationZoom.getTick() / animationZoom.getLength())), 250, y += StarWarsMod.mc.fontRenderer.FONT_HEIGHT, GLPalette.BRIGHT_YELLOW);

			y += StarWarsMod.mc.fontRenderer.FONT_HEIGHT;

			int color = GLPalette.BRIGHT_YELLOW;
			if (planet.getAffiliation().equals(Resources.allegianceImperial) || planet.getAffiliation().equals(Resources.allegianceSith))
				color = GLPalette.BRIGHT_RED;
			else if (planet.getAffiliation().equals(Resources.allegianceRebel) || planet.getAffiliation().equals(Resources.allegianceJedi))
				color = GLPalette.BRIGHT_BLUE;
			String aff = (LangUtils.translate("affiliation.0", planet.getAffiliation()));
			StarWarsMod.mc.fontRenderer.drawString(aff.substring(0, (int)MathUtils.lerp(0, aff.length(), (float)animationZoom.getTick() / animationZoom.getLength())), 250, y += StarWarsMod.mc.fontRenderer.FONT_HEIGHT, color);

			y += StarWarsMod.mc.fontRenderer.FONT_HEIGHT;

			int ny = y;
			String headerTerrain = LangUtils.translate("primary.terrain");
			StarWarsMod.mc.fontRenderer.drawString(headerTerrain.substring(0, (int)MathUtils.lerp(0, headerTerrain.length(), (float)animationZoom.getTick() / animationZoom.getLength())), 250, y += StarWarsMod.mc.fontRenderer.FONT_HEIGHT, GLPalette.BRIGHT_YELLOW);
			for (String t : planet.getTerrain())
				StarWarsMod.mc.fontRenderer.drawString(t.substring(0, (int)MathUtils.lerp(0, t.length(), (float)animationZoom.getTick() / animationZoom.getLength())), 260, y += StarWarsMod.mc.fontRenderer.FONT_HEIGHT, GLPalette.BRIGHT_YELLOW);
			y += StarWarsMod.mc.fontRenderer.FONT_HEIGHT;

			String suns = LangUtils.translate("suns.0", planet.getSuns());
			StarWarsMod.mc.fontRenderer.drawString(suns.substring(0, (int)MathUtils.lerp(0, suns.length(), (float)animationZoom.getTick() / animationZoom.getLength())), 380, ny += StarWarsMod.mc.fontRenderer.FONT_HEIGHT, GLPalette.BRIGHT_YELLOW);

			ny += StarWarsMod.mc.fontRenderer.FONT_HEIGHT;

			String moons = LangUtils.translate("moons.0", planet.getMoons());
			StarWarsMod.mc.fontRenderer.drawString(moons.substring(0, (int)MathUtils.lerp(0, moons.length(), (float)animationZoom.getTick() / animationZoom.getLength())), 380, ny + StarWarsMod.mc.fontRenderer.FONT_HEIGHT, GLPalette.BRIGHT_YELLOW);

			ny = y;

			String mineralHeader = LangUtils.translate("minerals");
			StarWarsMod.mc.fontRenderer.drawString(mineralHeader.substring(0, (int)MathUtils.lerp(0, mineralHeader.length(), (float)animationZoom.getTick() / animationZoom.getLength())), 380, ny += StarWarsMod.mc.fontRenderer.FONT_HEIGHT, GLPalette.BRIGHT_YELLOW);
			for (String t : planet.getResources())
				StarWarsMod.mc.fontRenderer.drawString(t.substring(0, (int)MathUtils.lerp(0, t.length(), (float)animationZoom.getTick() / animationZoom.getLength())), 390, ny += StarWarsMod.mc.fontRenderer.FONT_HEIGHT, GLPalette.BRIGHT_YELLOW);

			String nativeSpecies = LangUtils.translate("inhabiting.species");
			StarWarsMod.mc.fontRenderer.drawString(nativeSpecies.substring(0, (int)MathUtils.lerp(0, nativeSpecies.length(), (float)animationZoom.getTick() / animationZoom.getLength())), 250, y += StarWarsMod.mc.fontRenderer.FONT_HEIGHT, GLPalette.BRIGHT_YELLOW);
			for (String t : planet.getNativeSpecies())
				StarWarsMod.mc.fontRenderer.drawString(t.substring(0, (int)MathUtils.lerp(0, t.length(), (float)animationZoom.getTick() / animationZoom.getLength())), 260, y += StarWarsMod.mc.fontRenderer.FONT_HEIGHT, GLPalette.BRIGHT_YELLOW);

			GL11.glEnable(GL11.GL_LIGHTING);
		}

		if (MathUtils.oneIn(275))
		{
			TradeRoute r = MathUtils.getRandomElement(routes);
			int type = StarWarsMod.rngGeneral.nextInt(7);
			if (r == hydian)
				type = MathUtils.getRandomElement(new int[] { TIE, TIEADV, TIEBMB, TIEINT });
			else if (r == corellianRun)
				type = MathUtils.getRandomElement(new int[] { TIEADV, XWING, YWING });
			else if (r == corellianSpine)
				type = MathUtils.getRandomElement(new int[] { AWING, XWING, YWING });
			else if (r == rimma)
				type = MathUtils.getRandomElement(new int[] { TIEBMB, TIEINT });
			else if (r == perlemian)
				type = MathUtils.getRandomElement(new int[] { AWING, TIEINT, XWING });
			MovingShip s = new MovingShip(r, type, StarWarsMod.rngGeneral.nextBoolean());
			s.start();
			ships.add(s);
		}

		Iterator<MovingShip> iterator = ships.iterator();

		while (iterator.hasNext())
		{
			GL11.glPushMatrix();

			GLPalette.glColorI(GLPalette.WHITE);

			MovingShip ship = iterator.next();

			ship.tick();

			if (ship.animation.isDone())
				iterator.remove();

			FPoint pos = galaxyCoordsToXy(ship.position.x, ship.position.y);
			GL11.glTranslatef(pos.x, pos.y, 150);
			GL11.glTranslatef(2 * (ship.animation.isReverse() ? -1 : 1), 0, 0);

			if (zoomPlanet == null && animationZoom.getTick() == 0)
			{
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_CULL_FACE);
				P3D.glScalef(0.15f);
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glRotatef(ship.angle / 10f, 0, 0, 1);
				GL11.glRotatef(ship.angle, 0, 1, 0);
				GL11.glScalef(1, 1, -1);
				switch (ship.model)
				{
					case XWING:
						StarWarsMod.mc.renderEngine.bindTexture(RenderXWing.texture);
						P3D.glScalef(0.8f);
						this.modelXWing.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
						break;
					case YWING:
						StarWarsMod.mc.renderEngine.bindTexture(RenderYWing.texture);
						P3D.glScalef(0.8f);
						this.modelYWing.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
						break;
					case TIE:
						StarWarsMod.mc.renderEngine.bindTexture(RenderTIE.texture);
						this.modelTIE.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
						break;
					case TIEADV:
						StarWarsMod.mc.renderEngine.bindTexture(RenderTIEAdvanced.texture);
						this.modelTIEAdvanced.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
						break;
					case TIEINT:
						StarWarsMod.mc.renderEngine.bindTexture(RenderTIEInterceptor.texture);
						this.modelTIEInterceptor.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
						break;
					case AWING:
						StarWarsMod.mc.renderEngine.bindTexture(RenderAWing.texture);
						this.modelAWing.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
						break;
					case TIEBMB:
						StarWarsMod.mc.renderEngine.bindTexture(RenderTIEBomber.texture);
						this.modelTIEBomber.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.625F);
						break;
					default:
						break;
				}
				GL11.glEnable(GL11.GL_CULL_FACE);
				GL11.glPopMatrix();
			}

			GL11.glPopMatrix();
		}

		super.drawScreen(mX, mY, p);
	}

	private FPoint galaxyCoordsToXy(float x, float y, float scale)
	{
		float pX = r.getScaledWidth() / 2f - 144;
		float pY = r.getScaledHeight() / 2f - 144 + 16;
		return new FPoint(pX + scale * x, pY + scale * y);
	}

	private FPoint galaxyCoordsToXy(float x, float y)
	{
		return galaxyCoordsToXy(x, y, 16);
	}
}