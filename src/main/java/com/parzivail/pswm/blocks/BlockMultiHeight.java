package com.parzivail.pswm.blocks;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockMultiHeight extends Block
{
	public String name = "multiHeight";

	public BlockMultiHeight()
	{
		super(Material.rock);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		this.setCreativeTab(StarWarsMod.StarWarsTabBlocks);
		setBlockName(Resources.MODID + "." + name);
		this.setBlockBoundsFromHeight(0);

		// TODO: make this have a helper item to specify two places and make a slope between them
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		this.blockIcon = register.registerIcon(Resources.MODID + ":" + "templeStone");
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int l = world.getBlockMetadata(x, y, z) & 15;
		float f = 1 / 16f;
		return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)((float)y + (float)l * f), (double)z + this.maxZ);
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public void setBlockBoundsForItemRender()
	{
		this.setBlockBoundsFromHeight(0);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
	{
		this.setBlockBoundsFromHeight(blockAccess.getBlockMetadata(x, y, z));
	}

	private void setBlockBoundsFromHeight(int height)
	{
		int j = height & 15;
		float f = (float)(1 + j) / 16.0F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
	}

	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		super.harvestBlock(world, player, x, y, z, meta);
		world.setBlockToAir(x, y, z);
	}

	public int quantityDropped(Random random)
	{
		return 1;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		return side == 1 || super.shouldSideBeRendered(blockAccess, x, y, z, side);
	}

	public int quantityDropped(int meta, int fortune, Random random)
	{
		return (meta & 15) + 1;
	}

	public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z) < 15;
	}
}