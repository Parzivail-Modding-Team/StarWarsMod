package com.parzi.starwarsmod.jedirobes.powers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import com.parzi.starwarsmod.StarWarsMod;
import com.parzi.starwarsmod.network.MessageAddEffectTo;
import com.parzi.starwarsmod.utils.ForceUtils.EntityCooldownEntry;
import com.parzi.util.entity.EntityUtils;
import com.parzi.util.ui.GuiToast;

public class PowerSlow extends Power
{
	public PowerSlow(int currentLevel)
	{
		super("slow");
		this.costBase = 2700;
		this.costMult = 75;
		this.currentLevel = currentLevel;
		this.maxLevel = 5;
		this.rechargeTime = 3 * 40; // 40 ticks/second
		this.recharge = 0;
	}

	@Override
	public boolean run(EntityPlayer player)
	{
		if (this.recharge == 0)
		{
			Entity e = EntityUtils.rayTrace(this.currentLevel * 2, player, new Entity[0]);

			if (e != null)
			{
				StarWarsMod.network.sendToServer(new MessageAddEffectTo(new EntityCooldownEntry(e, "slow", this.currentLevel * 40)));
				GuiToast.makeText(e, 40).show();
			}

			return true;
		}
		return false;
	}
}
