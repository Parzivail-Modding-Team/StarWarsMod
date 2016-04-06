package com.parzivail.pswm.network;

import com.parzivail.pswm.vehicles.VehicSkyhopper;
import com.parzivail.pswm.vehicles.VehicXWing;
import com.parzivail.util.network.PMessage;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;

public class MessageSFoil extends PMessage<MessageSFoil>
{
	public EntityPlayer player;
	public float foil;

	public MessageSFoil()
	{
	}

	public MessageSFoil(EntityPlayer player, float foil)
	{
		this.player = player;
		this.foil = foil;
	}

	@Override
	public IMessage handleMessage(MessageContext context)
	{
		if (this.player.ridingEntity instanceof VehicXWing)
			((VehicXWing)this.player.ridingEntity).setSFoil(this.foil);
		else if (this.player.ridingEntity instanceof VehicSkyhopper)
			((VehicSkyhopper)this.player.ridingEntity).setWing(this.foil);
		return null;
	}

}