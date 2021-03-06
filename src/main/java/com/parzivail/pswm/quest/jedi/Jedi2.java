package com.parzivail.pswm.quest.jedi;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.force.Cron;
import com.parzivail.pswm.network.MessageSetPlayerHolding;
import com.parzivail.pswm.quest.DialogTree;
import com.parzivail.pswm.quest.Quest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import static com.parzivail.pswm.items.ItemQuestLog.isQuestDone;
import static com.parzivail.pswm.items.ItemQuestLog.setQuestDone;
import static com.parzivail.pswm.quest.QuestBank.jedi1;

/**
 * Created by Colby on 5/8/2016.
 */
public class Jedi2 extends Quest
{
	public Jedi2()
	{
		this.tree = new DialogTree();
		this.tree.npcHeader = "Time it is, to make your Lightsaber.";
		this.tree.response3 = "Lightsaber?";
		this.tree.response3DT = new DialogTree();
		this.tree.response3DT.npcHeader = "Hmm, yes. A powerful weapon it is, but even stronger in The Force you now are. Ilum Hyperdrive you will take, there you will find an Ancient Jedi Temple. All tools you need for your weapon there are. In the caves, find Kyber Crystals you will. Your choice it is what your Lightsaber looks like, fit you it will. After it is built, back to me you will come, show me your weapon you must.";
		this.tree.response3DT.response1 = "Thank you Master, I'll return as soon as it's built!";
		this.tree.response3DT.response1DT = new DialogTree();
	}

	@Override
	public String getQuestgiverName()
	{
		return "Yoda";
	}

	@Override
	public boolean canBeGivenQuest(EntityPlayer player)
	{
		return !isQuestDone(player, this) && jedi1.isQuestComplete(player) && Cron.getLevelOf(player, "grab") > 0;
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
		StarWarsMod.network.sendToServer(new MessageSetPlayerHolding(player, new ItemStack(StarWarsItems.hyperdriveIlum, 1), true));
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
		return "The Lightsaber";
	}
}
