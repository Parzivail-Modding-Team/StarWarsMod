package com.parzivail.pswm.quest.imperial;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.network.MessageSetPlayerHolding;
import com.parzivail.pswm.quest.DialogTree;
import com.parzivail.pswm.quest.Quest;
import com.parzivail.util.world.ItemUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import static com.parzivail.pswm.items.ItemQuestLog.isQuestDone;
import static com.parzivail.pswm.items.ItemQuestLog.setQuestDone;
import static com.parzivail.pswm.quest.QuestBank.imperial10_1;

/**
 * Created by Colby on 5/8/2016.
 */
public class Imperial10_2 extends Quest
{
	public Imperial10_2()
	{
		this.tree = new DialogTree();
		this.tree.npcHeader = "Well Trooper, you've successfully completed your mission and we thank you. It looks like you're going to be on leave for a while, there isn't much happening in the galaxy right now and I'm sure you'd like a reprieve from all your assignments. You're dismissed for now, but you'll be back in action soon enough. Keep up the good work Trooper.";
		this.tree.response1 = "Of course Sir! I'll make the most of the downtime.";
		this.tree.response1DT = new DialogTree();
		this.tree.response2 = "No missions huh? Well I guess I could use some time off anyway.";
		this.tree.response2DT = new DialogTree();
		this.tree.response3 = "Sir Yes Sir! I thought the missions would never end. Thank you Sir!";
		this.tree.response3DT = new DialogTree();
	}

	@Override
	public String getQuestgiverName()
	{
		return "Daala";
	}

	@Override
	public boolean canBeGivenQuest(EntityPlayer player)
	{
		return !isQuestDone(player, this) && imperial10_1.isQuestComplete(player) && ItemUtils.hasItems(player, new ItemStack(StarWarsItems.xwingSchematics, 1));
	}

	@Override
	public void begin(EntityPlayer player)
	{

	}

	@Override
	public boolean isQuestComplete(EntityPlayer player)
	{
		return isQuestDone(player, this);
	}

	@Override
	public void end(EntityPlayer player)
	{
		player.playSound(Resources.MODID + ":" + "quest.complete", 1, 1);
		StarWarsMod.network.sendToServer(new MessageSetPlayerHolding(player, new ItemStack(StarWarsItems.silverImperialCredit, 10), true));
		setQuestDone(player, this);
	}

	@Override
	public DialogTree getDialog(EntityPlayer player)
	{
		return tree;
	}

	@Override
	public String getID()
	{
		return " A Real Mission ";
	}
}
