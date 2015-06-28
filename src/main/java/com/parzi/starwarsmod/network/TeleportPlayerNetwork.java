package com.parzi.starwarsmod.network;

import com.parzi.starwarsmod.StarWarsMod;
import com.parzi.starwarsmod.world.TransferDim;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class TeleportPlayerNetwork implements IMessage
{

	private String player;
	private int oldDim;
	private int newDim;

	public TeleportPlayerNetwork()
	{
	}

	public TeleportPlayerNetwork(String player, int oldDim, int newDim)
	{
		this.player = player;
		this.oldDim = oldDim;
		this.newDim = newDim;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		player = ByteBufUtils.readUTF8String(buf);
		oldDim = ByteBufUtils.readVarInt(buf, 5);
		newDim = ByteBufUtils.readVarInt(buf, 5);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, player);
		ByteBufUtils.writeVarInt(buf, oldDim, 5);
		ByteBufUtils.writeVarInt(buf, newDim, 5);
	}

	public static class Handler implements IMessageHandler<TeleportPlayerNetwork, IMessage>
	{

		@Override
		public IMessage onMessage(TeleportPlayerNetwork message, MessageContext ctx)
		{
			System.out.println("Teleporting player " + message.player + " to dimension ID " + message.newDim);
			new TransferDim(MinecraftServer.getServer().worldServerForDimension(message.newDim)).teleport(MinecraftServer.getServer().worldServerForDimension(message.oldDim).getPlayerEntityByName(message.player));
			return null; // no response in this case
		}
	}
}
