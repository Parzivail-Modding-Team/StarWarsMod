package com.parzivail.pswm.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

public class TileEntityBactaTank extends TileEntity
{
	AxisAlignedBB bb;
	int ticksInside = 0;
	String playerInsideId = "";

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared()
	{
		return 262144;
	}

	public AxisAlignedBB getAABB()
	{
		if (this.bb == null)
			this.bb = AxisAlignedBB.getBoundingBox(this.xCoord + 0.4f, this.yCoord, this.zCoord + 0.4f, this.xCoord + 0.6f, this.yCoord + 3.8f, this.zCoord + 0.6f);
		return this.bb;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox()
	{
		return this.getAABB();
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 64537, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
		super.onDataPacket(net, packet);
		this.readFromNBT(packet.func_148857_g());
	}

	@Override
	public void writeToNBT(NBTTagCompound p_145841_1_)
	{
		super.writeToNBT(p_145841_1_);
		p_145841_1_.setString("playerInside", playerInsideId);
		p_145841_1_.setInteger("ticksInside", ticksInside);
	}

	@Override
	public void readFromNBT(NBTTagCompound p_145839_1_)
	{
		super.readFromNBT(p_145839_1_);
		this.playerInsideId = p_145839_1_.getString("playerInside");
		this.ticksInside = p_145839_1_.getInteger("ticksInside");
	}

	public String getPlayerInside()
	{
		return this.playerInsideId;
	}

	public void setPlayerInsideId(String playerInsideId)
	{
		this.playerInsideId = playerInsideId;
	}

	public int getTicksInside()
	{
		return ticksInside;
	}

	@Override
	public void updateEntity()
	{
		if (getPlayerInside().isEmpty())
		{
			List<EntityPlayerMP> playersInRange = this.worldObj.getEntitiesWithinAABB(EntityPlayerMP.class, getAABB());

			if (playersInRange.size() > 0)
			{
				setPlayerInsideId(playersInRange.get(0).getCommandSenderName());
				ticksInside = 0;
			}
		}
		else
		{
			EntityPlayer playerInside = this.worldObj.getPlayerEntityByName(this.playerInsideId);
			if (playerInside instanceof EntityPlayerMP)
			{
				EntityPlayerMP netPlayer = (EntityPlayerMP)playerInside;

				if (netPlayer.isSneaking())
				{
					setPlayerInsideId("");
					spitOutPlayer(netPlayer);
				}
				else if (ticksInside >= 720)
				{
					setPlayerInsideId("");
					spitOutPlayer(netPlayer);
					netPlayer.setHealth(20);
					netPlayer.curePotionEffects(new ItemStack(Items.milk_bucket));
					ticksInside = 0;
				}
				else
				{
					netPlayer.playerNetServerHandler.setPlayerLocation(this.xCoord + 0.5f, this.yCoord + 1f, this.zCoord + 0.5f, ticksInside, 0);
					ticksInside++;
				}
			}
			else
				setPlayerInsideId("");
		}
	}

	private void spitOutPlayer(EntityPlayerMP netPlayer)
	{
		if (netPlayer.worldObj.getBlock(this.xCoord - 1, this.yCoord, this.zCoord + 1) == Blocks.air)
			netPlayer.playerNetServerHandler.setPlayerLocation(this.xCoord - 0.5f, this.yCoord, this.zCoord + 0.5f, 0, 0);
		else if (netPlayer.worldObj.getBlock(this.xCoord + 1, this.yCoord, this.zCoord + 1) == Blocks.air)
			netPlayer.playerNetServerHandler.setPlayerLocation(this.xCoord + 0.5f, this.yCoord, this.zCoord + 0.5f, 0, 0);
		else if (netPlayer.worldObj.getBlock(this.xCoord - 1, this.yCoord, this.zCoord - 1) == Blocks.air)
			netPlayer.playerNetServerHandler.setPlayerLocation(this.xCoord - 0.5f, this.yCoord, this.zCoord - 0.5f, 0, 0);
		netPlayer.playerNetServerHandler.setPlayerLocation(this.xCoord + 0.5f, this.yCoord, this.zCoord - 0.5f, 0, 0);
	}
}
