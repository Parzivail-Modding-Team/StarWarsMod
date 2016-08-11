package com.parzivail.pswm.rendering;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.items.weapons.ItemBlasterHeavy;
import com.parzivail.pswm.items.weapons.ItemBlasterRifle;
import com.parzivail.pswm.models.blocks.ModelGunRack;
import com.parzivail.pswm.tileentities.TileEntityGunRack;
import com.parzivail.util.block.TileEntityRotate;
import com.parzivail.util.ui.P3D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderGunRack extends TileEntitySpecialRenderer
{
	public static ResourceLocation texture = new ResourceLocation(Resources.MODID + ":" + "textures/models/gunRack.png");

	private final ModelBase model;

	public RenderGunRack()
	{
		this.model = new ModelGunRack();
	}

	private void adjustRotatePivotViaMeta(World world, int x, int y, int z)
	{
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float tickTime)
	{
		GL11.glPushMatrix();

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glTranslated(x + 0.5f, y + 0.95f, z + 0.5f);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		P3D.glScalef(0.8f);
		if (te instanceof TileEntityRotate)
			GL11.glRotatef(90 * ((TileEntityRotate)te).getFacing(), 0, 1, 0);

		this.model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.05F);

		if (te instanceof TileEntityGunRack)
		{
			TileEntityGunRack gunRack = (TileEntityGunRack)te;

			int i = 0;
			for (ItemStack gun : gunRack.getGuns())
			{
				renderGun(gunRack, gun, i++);
			}
		}

		GL11.glPopMatrix();
	}

	private void renderGun(TileEntityGunRack rack, ItemStack gun, int index)
	{
		if (gun == null)
			return;

		Item type = gun.getItem();

		GL11.glPushMatrix();
		if (type instanceof ItemBlasterRifle)
		{
			ItemBlasterRifle rifle = (ItemBlasterRifle)type;
			if (gun.getItemDamage() == rifle.indexOfMeta("A280"))
			{
				if (index >= 6)
				{
					GL11.glRotatef(180, 0, 1, 0);
					GL11.glTranslatef(-2.28f, 0, 0);
				}
				GL11.glTranslatef(-0.6f + (index * 0.382f), -0.27f, -0.065f);
				GL11.glRotatef(80, 1, 0, 0);
				P3D.glScalef(0.65);
			}
			else if (gun.getItemDamage() == rifle.indexOfMeta("Ee3"))
			{
				if (index >= 6)
				{
					GL11.glRotatef(180, 0, 1, 0);
					GL11.glTranslatef(-2.28f, 0, 0);
				}
				GL11.glTranslatef(-1.21f + (index * 0.382f), 0.05f, -0.06f);
				GL11.glRotatef(180, 1, 0, 0);
				GL11.glRotatef(180, 0, 1, 0);
				GL11.glRotatef(93, 1, 0, 0);
				P3D.glScalef(0.65);
			}
			else if (gun.getItemDamage() == rifle.indexOfMeta("Cycler"))
			{
				if (index >= 6)
				{
					GL11.glRotatef(180, 0, 1, 0);
					GL11.glTranslatef(-2.28f, 0, 0);
				}
				GL11.glTranslatef(-1.33f + (index * 0.382f), -0.21f, -0.28f);
				GL11.glRotatef(90, 0, 0, 1);
				GL11.glRotatef(180, 1, 0, 0);
				GL11.glRotatef(180, 0, 1, 0);
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glRotatef(3, 0, 0, 1);
				P3D.glScalef(0.65);
			}
			else if (gun.getItemDamage() == rifle.indexOfMeta("Stormtrooper"))
			{
				if (index >= 6)
				{
					GL11.glRotatef(180, 0, 1, 0);
					GL11.glTranslatef(-2.28f, 0, 0);
				}
				GL11.glTranslatef(-0.67f + (index * 0.382f), 0.23f, -0.13f);
				GL11.glRotatef(80, 1, 0, 0);
				P3D.glScalef(0.72);
			}
			RenderManager.instance.itemRenderer.renderItem(null, gun, 0, IItemRenderer.ItemRenderType.ENTITY);
		}
		else if (type instanceof ItemBlasterHeavy)
		{
			if (gun.getItemDamage() == 0)
			{
				if (index >= 6)
				{
					GL11.glRotatef(180, 0, 1, 0);
					GL11.glTranslatef(-2.28f, 0, 0);
				}
				GL11.glTranslatef(-0.555f + (index * 0.382f), -0.17f, -0.18f);
				GL11.glRotatef(90, 0, 0, 1);
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glRotatef(-4, 0, 0, 1);
				P3D.glScalef(0.65);
			}
			else if (gun.getItemDamage() == 1)
			{
				if (index >= 6)
				{
					GL11.glRotatef(180, 0, 1, 0);
					GL11.glTranslatef(-2.28f, 0, 0);
				}
				GL11.glTranslatef(-1.22f + (index * 0.382f), -0.1f, -0.2f);
				GL11.glRotatef(180, 0, 1, 0);
				GL11.glRotatef(-90, 1, 0, 0);
				P3D.glScalef(0.65);
			}
			else if (gun.getItemDamage() == 2)
			{
				if (index >= 6)
				{
					GL11.glRotatef(180, 0, 1, 0);
					GL11.glTranslatef(-2.28f, 0, 0);
				}
				GL11.glTranslatef(-1.22f + (index * 0.382f), 0.07f, -0.14f);
				GL11.glRotatef(180, 0, 1, 0);
				GL11.glRotatef(-86, 1, 0, 0);
				P3D.glScalef(0.65);
			}
			RenderManager.instance.itemRenderer.renderItem(null, gun, 0, IItemRenderer.ItemRenderType.ENTITY);
		}
		GL11.glPopMatrix();
	}
}
