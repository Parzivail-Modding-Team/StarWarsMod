package com.parzivail.pswm.blocks;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.tileentities.TileEntityMV;
import com.parzivail.util.IDebugProvider;
import com.parzivail.util.block.PBlockContainer;
import com.parzivail.util.ui.LangUtils;
import com.parzivail.util.world.HarvestLevel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public class BlockMV extends PBlockContainer implements IDebugProvider
{
	public BlockMV()
	{
		super("moistureVaporator", Material.iron);
		setCreativeTab(StarWarsMod.StarWarsTabBlocks);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F);
		setHardness(50.0F);
		this.setHarvestLevel("pickaxe", HarvestLevel.IRON);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int wut)
	{
		TileEntityMV moistureVap = (TileEntityMV)world.getTileEntity(x, y, z);
		if (moistureVap != null)
		{
			ItemStack itemstack = moistureVap.getStackInSlot(0);
			if (itemstack != null)
			{
				EntityItem entityitem = new EntityItem(world, x, y, z, itemstack);
				if (itemstack.hasTagCompound())
					entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
				world.spawnEntityInWorld(entityitem);
			}
			world.func_147453_f(x, y, z, block);
		}
		super.breakBlock(world, x, y, z, block, wut);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityMV();
	}

	@Override
	public List<String> getDebugText(List<String> list, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileEntityMV)
		{
			TileEntityMV vap = (TileEntityMV)tile;
			float l = vap.progressTicks / (float)vap.totalTicks;
			list.add((int)(l * 100f) + "%");
			if (vap.getStackInSlot(0) != null)
				list.add(LangUtils.translate("0.droplets", vap.getStackInSlot(0).stackSize));
		}

		return list;
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float e, float f, float g)
	{
		if (world.isRemote)
			player.openGui(StarWarsMod.instance, Resources.GUI_MV, world, x, y, z);
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack item)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileEntityMV)
		{
			TileEntityMV vap = (TileEntityMV)tile;
			int l = MathHelper.floor_double(player.rotationYaw * 8.0F / 360.0F + 0.5D) & 0x3;
			vap.setFacing(l);
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister icon)
	{
		blockIcon = icon.registerIcon(Resources.MODID + ":" + "iconMoistureVaporator");
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z)
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F);
	}
}
