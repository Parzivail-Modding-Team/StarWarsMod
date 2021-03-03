package com.parzivail.pswm.quest.rebel;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.network.MessageSetPlayerHolding;
import com.parzivail.pswm.quest.DialogTree;
import com.parzivail.pswm.quest.Quest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import static com.parzivail.pswm.StarWarsItems.silverImperialCredit;
import static com.parzivail.pswm.StarWarsItems.tieSchematics;
import static com.parzivail.pswm.StarWarsMod.network;
import static com.parzivail.pswm.items.ItemQuestLog.isQuestDone;
import static com.parzivail.pswm.items.ItemQuestLog.setQuestDone;
import static com.parzivail.pswm.quest.QuestBank.rebel10;
import static com.parzivail.util.world.ItemUtils.hasItems;

/**
 * Created by Colby on 5/8/2016.
 */
public class Rebel10_Over extends Quest
{
	public Rebel10_Over()
	{
		this.tree = new DialogTree();
		this.tree.npcHeader = "Well rookie, i've got to congratulate you on a mission well done, these plans are going to be a great help to the Rebel Alliance.";
		this.tree.response1 = "It wasn't too much trouble sir!";
		this.tree.response1DT = new DialogTree();
		this.tree.response1DT.npcHeader = "That's good to hear soldier. I have some news though, intel came down and it looks like you're going to be inactive for a while. The Empire is being quieter than usual and we don't have any missions that need your attention. I'll let you know when we've got anything else for you, but for now, you're free to go. At least try to stay out of trouble, and try not to get mixed up with an unsavory people, rookie.";
		this.tree.response2 = "You owe me one for that mission old man, I almost got captured by the Empire.";
		this.tree.response2DT = new DialogTree();
		this.tree.response2DT.npcHeader = "Oh well, you made it back here fine didn't you? So quit your whining. I have some news too, intel came down and it looks like you're going to be inactive for a while. The Empire is being quieter than usual and we don't have any missions that need your attention. I'll let you know when we've got anything else for you, but for now, you're free to go. At least try to stay out of trouble, and try not to get mixed up with an unsavory people, rookie.";
		this.tree.response3 = "I'm just glad I came back in one piece sir.";
		this.tree.response3DT = new DialogTree();
		this.tree.response3DT.npcHeader = "That's good to hear soldier. I have some news though, intel came down and it looks like you're going to be inactive for a while. The Empire is being quieter than usual and we don't have any missions that need your attention. I'll let you know when we've got anything else for you, but for now, you're free to go. At least try to stay out of trouble, and try not to get mixed up with an unsavory people, rookie.";
	}

	@Override
	public String getQuestgiverName()
	{
		return "Dreis";
	}

	@Override
	public boolean canBeGivenQuest(EntityPlayer player)
	{
		return !isQuestDone(player, this) && rebel10.isQuestComplete(player) && hasItems(player, new ItemStack(tieSchematics, 1));
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
		network.sendToServer(new MessageSetPlayerHolding(player, new ItemStack(silverImperialCredit, 10), true));
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
		return " Infiltration ";
	}
}
