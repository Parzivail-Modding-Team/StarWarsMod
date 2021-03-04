package com.parzivail.pswm.rendering;

import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.items.weapons.ItemLightsaber;
import com.parzivail.pswm.models.lightsabers.*;
import com.parzivail.pswm.models.lightsabers.blades.*;
import com.parzivail.util.ui.ShaderHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

public class RenderLightsaber implements IItemRenderer
{
	public static final HashMap<String, IHandlesRender> models = new HashMap<>();
	public static final HashMap<String, IHandlesRender[]> blades = new HashMap<>();

	public static RenderLightsaber instance;

	static
	{
		models.put("dooku", new ModelDookuHilt());
		models.put("ezra", new ModelEzraHilt());
		models.put("kanan", new ModelKananHilt());
		models.put("maul", new ModelMaulHilt());
		models.put("padawan", new ModelPadawanHilt());
		models.put("shoto", new ModelShotoHilt());
		models.put("doubleSith", new ModelAncientSithHilt());
		models.put("vader2", new ModelVader2Hilt());
		models.put("luke1", new ModelLuke1Hilt());
		models.put("luke2", new ModelLukeNewHilt());
		models.put("crossguard", new ModelCrossbarHilt());
		models.put("malgus", new ModelMalgusHilt());
		models.put("obiwan", new ModelObiwanHilt());
		models.put("quigon", new ModelQuigonHilt());
		models.put("revan", new ModelRevanHilt());
		models.put("starkiller", new ModelStarkillerHilt());
		models.put("plokoon", new ModelPloKoonHilt());
		models.put("inquisitor", new ModelInquisitorHilt());
		models.put("mace", new ModelMaceHilt());
		models.put("yoda", new ModelYodaHilt());
		models.put("ahsoka", new ModelAhsokaHilt());
		models.put("darksaber", new ModelDarksaberHilt());
		models.put("pike", new ModelPikeHilt());
		models.put("revan2", new ModelRevan2Hilt());
		models.put("ventress", new ModelVentressHilt());

		blades.put("dooku", new IHandlesRender[] { new ModelDookuBladeShort(), new ModelDookuBladeMedium(), new ModelDookuBladeLong() });
		blades.put("ezra", new IHandlesRender[] { new ModelEzraBladeShort(), new ModelEzraBladeMedium(), new ModelEzraBladeLong() });
		blades.put("kanan", new IHandlesRender[] { new ModelKananBladeShort(), new ModelKananBladeMedium(), new ModelKananBladeLong() });
		blades.put("maul", new IHandlesRender[] { new ModelMaulBladeShort(), new ModelMaulBladeMedium(), new ModelMaulBladeLong() });
		blades.put("padawan", new IHandlesRender[] { new ModelPadawanBladeShort(), new ModelPadawanBladeMedium(), new ModelPadawanBladeLong() });
		blades.put("shoto", new IHandlesRender[] { new ModelShotoBladeShort(), new ModelShotoBladeMedium(), new ModelShotoBladeLong() });
		blades.put("doubleSith", new IHandlesRender[] { new ModelSithDoubleBladeShort(), new ModelSithDoubleBladeMedium(), new ModelSithDoubleBladeLong() });
		blades.put("vader2", new IHandlesRender[] { new ModelVaderBladeShort(), new ModelVaderBladeMedium(), new ModelVaderBladeLong() });
		blades.put("luke1", new IHandlesRender[] { new ModelLuke1BladeShort(), new ModelLuke1BladeMedium(), new ModelLuke1BladeLong() });
		blades.put("luke2", new IHandlesRender[] { new ModelLukeNewBladeS(), new ModelLukeNewBladeM(), new ModelLukeNewBladeL() });
		blades.put("crossguard", new IHandlesRender[] { new ModelCrossguardBladeShort(), new ModelCrossguardBladeMedium(), new ModelCrossguardBladeLong() });
		blades.put("malgus", new IHandlesRender[] { new ModelMalgusBladeS(), new ModelMalgusBladeM(), new ModelMalgusBladeL() });
		blades.put("obiwan", new IHandlesRender[] { new ModelObiwanBladeS(), new ModelObiwanBladeM(), new ModelObiwanBladeL() });
		blades.put("quigon", new IHandlesRender[] { new ModelQuigonBladeS(), new ModelQuigonBladeM(), new ModelQuigonBladeL() });
		blades.put("revan", new IHandlesRender[] { new ModelRevanBladeS(), new ModelRevanBladeM(), new ModelRevanBladeL() });
		blades.put("starkiller", new IHandlesRender[] { new ModelStarkillerBladeS(), new ModelStarkillerBladeM(), new ModelStarkillerBladeL() });
		blades.put("plokoon", new IHandlesRender[] { new ModelPloKoonBladeShort(), new ModelPloKoonBladeMedium(), new ModelPloKoonBladeLong() });
		blades.put("inquisitor", new IHandlesRender[] { new ModelInquisitorBladeShort(), new ModelInquisitorBladeMedium(), new ModelInquisitorBladeLong() });
		blades.put("mace", new IHandlesRender[] { new ModelMaceBladeShort(), new ModelMaceBladeMedium(), new ModelMaceBladeLong() });
		blades.put("yoda", new IHandlesRender[] { new ModelYodaBladeShort(), new ModelYodaBladeMedium(), new ModelYodaBladeLong() });
		blades.put("ahsoka", new IHandlesRender[] { new ModelAhsokaBladeShort(), new ModelAhsokaBladeMedium(), new ModelAhsokaBladeLong() });
		blades.put("darksaber", new IHandlesRender[] { new ModelDarksaberBladeShort(), new ModelDarksaberBladeMedium(), new ModelDarksaberBladeLong() });
		blades.put("pike", new IHandlesRender[] { new ModelPikeBladeShort(), new ModelPikeBladeMedium(), new ModelPikeBladeLong() });
		blades.put("revan2", new IHandlesRender[] { new ModelRevan2BladeShort(), new ModelRevan2BladeMedium(), new ModelRevan2BladeLong() });
		blades.put("ventress", new IHandlesRender[] { new ModelVentressBladeShort(), new ModelVentressBladeMedium(), new ModelVentressBladeLong() });

	}

	public RenderLightsaber()
	{
		if (instance == null)
			instance = this;
	}

	@Override
	public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
	{
		IHandlesRender r = getHiltRendererForStack(item);
		if (r != null)
			return r.handleRenderType(item, type);
		return true;
	}

	@Override
	public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
	{
		IHandlesRender r = getHiltRendererForStack(item);
		if (r != null)
		{
			GL11.glPushMatrix();
			StarWarsMod.mc.renderEngine.bindTexture(r.getResourceLocation(item.stackTagCompound != null && item.stackTagCompound.getBoolean(ItemLightsaber.nbtHiltSkin)));
			r.renderItem(type, item, data);
			GL11.glPopMatrix();
			IHandlesRender rB = getBladeRendererForStack(item);
			if (rB != null && item.stackTagCompound.getBoolean(ItemLightsaber.nbtBladeOn) && type != ItemRenderType.INVENTORY)
			{
				GL11.glPushMatrix();
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				if (item.stackTagCompound.getBoolean(ItemLightsaber.nbtBladeDistortion))
					ShaderHelper.setLightsaberColorDistort(item.stackTagCompound.getInteger(ItemLightsaber.nbtBladeColor));
				else
					ShaderHelper.setLightsaberColor(item.stackTagCompound.getInteger(ItemLightsaber.nbtBladeColor));
				ShaderHelper.useShader(ShaderHelper.glowSolid);
				float shakeAmount = 0.0015f;
				GL11.glTranslatef((float)(StarWarsMod.rngGeneral.nextGaussian() * shakeAmount), 0, (float)(StarWarsMod.rngGeneral.nextGaussian() * shakeAmount));
				rB.renderItem(type, item, data);
				ShaderHelper.releaseShader();
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glPopMatrix();
			}
		}
		else
		{
			r = models.get("luke2");
			StarWarsMod.mc.renderEngine.bindTexture(r.getResourceLocation(false));
			r.renderItem(type, item, data);
		}
	}

	public void renderHiltItem(IHandlesRender item, boolean alt)
	{
		IHandlesRender r = item;
		GL11.glPushMatrix();
		StarWarsMod.mc.renderEngine.bindTexture(r.getResourceLocation(alt));
		r.renderItem(ItemRenderType.ENTITY, null);
		GL11.glPopMatrix();
	}

	@Override
	public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
	{
		IHandlesRender r = getHiltRendererForStack(item);
		if (r != null)
			return r.shouldUseRenderHelper(type, item, helper);
		return true;
	}

	public static IHandlesRender getHiltRendererForStack(ItemStack stack)
	{
		if (stack.stackTagCompound != null && models.containsKey(stack.stackTagCompound.getString(ItemLightsaber.nbtHilt)))
			return models.get(stack.stackTagCompound.getString(ItemLightsaber.nbtHilt));
		else
			return models.get(ItemLightsaber.hilts[((ItemLightsaber)stack.getItem()).hiltIndex]);
	}

	public static IHandlesRender getBladeRendererForStack(ItemStack stack)
	{
		if (stack.stackTagCompound != null && blades.containsKey(stack.stackTagCompound.getString(ItemLightsaber.nbtHilt)) && blades.get(stack.stackTagCompound.getString(ItemLightsaber.nbtHilt)).length == 3)
			return blades.get(stack.stackTagCompound.getString(ItemLightsaber.nbtHilt))[stack.stackTagCompound.getInteger(ItemLightsaber.nbtBladeLength)];
		else
			return null;
	}

	public static void applyTransformFix(String s)
	{
		switch (s)
		{
			case "dooku":
				GL11.glTranslatef(0, 0.075f, 0);
				break;
			case "ezra":
				GL11.glTranslatef(0, 0.075f, 0);
				break;
			case "kanan":
				GL11.glTranslatef(0, 0.16f, 0);
				break;
			case "maul":
				GL11.glScalef(0.55f, 0.55f, 0.55f);
				GL11.glTranslatef(0, 0.15f, 0);
				break;
			case "padawan":
				GL11.glTranslatef(0, 0.4f, 0);
				break;
			case "shoto":
				GL11.glScalef(0.9f, 0.9f, 0.9f);
				GL11.glTranslatef(0, 0.27f, 0);
				break;
			case "doubleSith":
				GL11.glScalef(0.8f, 0.8f, 0.8f);
				GL11.glTranslatef(0, -0.27f, 0);
				break;
			case "vader2":
				GL11.glTranslatef(0, 0.17f, 0);
				GL11.glScalef(1.4f, 1.4f, 1.4f);
				break;
			case "luke1":
				GL11.glTranslatef(0, 0.28f, 0);
				GL11.glScalef(1.4f, 1.4f, 1.4f);
				break;
			case "luke2":
				GL11.glTranslatef(0, 0.25f, 0);
				break;
			case "crossguard":
				GL11.glTranslatef(0, 0.24f, 0);
				GL11.glScalef(1.2f, 1.2f, 1.2f);
				break;
			case "malgus":
				GL11.glScalef(0.85f, 0.85f, 0.85f);
				GL11.glTranslatef(0, 0.29f, 0);
				break;
			case "obiwan":
				GL11.glScalef(0.9f, 0.9f, 0.9f);
				GL11.glTranslatef(0, 0.27f, 0);
				break;
			case "quigon":
				GL11.glScalef(0.9f, 0.9f, 0.9f);
				GL11.glTranslatef(0, 0.34f, 0);
				break;
			case "revan":
				GL11.glScalef(0.9f, 0.9f, 0.9f);
				GL11.glTranslatef(0, 0.31f, 0);
				break;
			case "starkiller":
				GL11.glTranslatef(0, 0.3f, 0);
				GL11.glScalef(1.1f, 1.1f, 1.1f);
				break;
			case "plokoon":
				GL11.glTranslatef(0, 0.225f, 0);
				GL11.glScalef(1.1f, 1.1f, 1.1f);
				break;
			case "inquisitor":
				GL11.glTranslatef(0, -0.55f, 0);
				GL11.glScalef(1.1f, 1.1f, 1.1f);
				break;
			case "mace":
				GL11.glTranslatef(0, .045f, 0);
				GL11.glScalef(1.23f, 1.23f, 1.23f);
				break;
			case "yoda":
				GL11.glTranslatef(0, -0.35f, 0);
				GL11.glScalef(1.45f, 1.45f, 1.45f);
				break;
			case "ahsoka":
				GL11.glTranslatef(0, 0.475f, 0);
				GL11.glScalef(1.2f, 1.2f, 1.2f);
				break;
			case "darksaber":
				GL11.glTranslatef(0, 0.36f, 0);
				GL11.glScalef(1.1f, 1.1f, 1.1f);
				break;
			case "pike":
				GL11.glTranslatef(0, 0.2f, 0);
				GL11.glScalef(0.58f, 0.58f, 0.58f);
				break;
			case "revan2":
				GL11.glTranslatef(0, 0.02f, 0);
				GL11.glScalef(1.05f, 1.05f, 1.05f);
				break;
			case "ventress":
				GL11.glTranslatef(0, 0.2f, 0);
				GL11.glScalef(1.3f, 1.3f, 1.3f);
				break;
		}
	}
}