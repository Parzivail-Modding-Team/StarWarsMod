package com.parzivail.pswm.blocks;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.tileentities.TileEntityDoorHoth;
import com.parzivail.util.IDebugProvider;
import com.parzivail.util.block.PBlockContainer;
import com.parzivail.util.block.TileEntityRotate;
import com.parzivail.util.ui.LangUtils;
import com.parzivail.util.world.HarvestLevel;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public class BlockDoorHoth extends PBlockContainer implements IDebugProvider
{
	public BlockDoorHoth()
	{
		super("doorHoth", Material.iron);
		setCreativeTab(StarWarsMod.StarWarsTabBlocks);
		setBlockBounds(0, 0, 0, 1, 0.1f, 1);
		setHardness(50.0F);
		this.setHarvestLevel("pickaxe", HarvestLevel.IRON);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityDoorHoth();
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public List<String> getDebugText(List<String> list, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileEntityDoorHoth)
		{
			TileEntityDoorHoth t = (TileEntityDoorHoth)tile;
			list.add(LangUtils.translate("moving.0", t.isMoving));
			list.add(t.isOpening ? LangUtils.translate("opening") : LangUtils.translate("closing"));
			float l = t.progressTicks / (float)t.totalTicks;
			list.add((int)(l * 100f) + "%");
		}

		return list;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack item)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileEntityRotate)
		{
			TileEntityRotate te = (TileEntityRotate)tile;
			int l = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;
			te.setFacing(l);
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
	{
		TileEntity te = p_149719_1_.getTileEntity(p_149719_2_, p_149719_3_, p_149719_4_);
		if (te instanceof TileEntityDoorHoth && ((TileEntityDoorHoth)te).progressTicks == 0)
		{
			setBlockBounds(-0.5f, 0, -0.5f, 1.5f, 3, 1.5f);
		}
		else
			setBlockBounds(0, 0, 0, 1, 0.1f, 1);
	}

	@Override
	public void registerBlockIcons(IIconRegister icon)
	{
		blockIcon = icon.registerIcon(Resources.MODID + ":" + "blank");
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
}
