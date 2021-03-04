package com.parzivail.pswm.force.powers;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.network.MessageEntityAlterMotion;
import com.parzivail.util.entity.EntityUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

public class PowerPull extends PowerBase
{
	public PowerPull(int currentLevel)
	{
		super("pull");
		this.costBase = 240;
		this.costMult = 40;
		this.currentLevel = currentLevel;
		this.maxLevel = 5;
		this.rechargeTime = 5 * 20; // 40 ticks/second
		this.recharge = 0;
	}

	@Override
	public boolean run(EntityPlayer player)
	{
		if (this.recharge == 0)
		{
			Entity e = EntityUtils.rayTrace(this.currentLevel * 3, player, new Entity[0]);

			if (e != null)
			{
				Vec3 lookVec = player.getLookVec();

				float mult = 1 + 0.5f * this.currentLevel;

				lookVec.xCoord *= -mult;
				lookVec.yCoord *= -mult;
				lookVec.zCoord *= -mult;

				e.motionX += lookVec.xCoord;
				e.motionY += lookVec.yCoord;
				e.motionZ += lookVec.zCoord;
				e.isAirBorne = true;

				if (e instanceof EntityPlayer)
					e.velocityChanged = true;

				StarWarsMod.network.sendToServer(new MessageEntityAlterMotion(e, lookVec));

				player.worldObj.playSound(player.posX, player.posY, player.posZ, Resources.MODID + ":" + "force.pull", 1, 1, true);
			}

			return true;
		}
		return false;
	}
}
