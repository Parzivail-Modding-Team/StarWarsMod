package com.parzivail.pswm.quest.jedi;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.items.weapons.ItemLightsaber;
import com.parzivail.pswm.quest.DialogTree;
import com.parzivail.pswm.quest.Quest;
import com.parzivail.util.world.ItemUtils;
import net.minecraft.entity.player.EntityPlayer;

import static com.parzivail.pswm.items.ItemQuestLog.isQuestDone;
import static com.parzivail.pswm.items.ItemQuestLog.setQuestDone;
import static com.parzivail.pswm.quest.QuestBank.jedi2;

/**
 * Created by Colby on 5/8/2016.
 */
public class Jedi3 extends Quest
{
	public Jedi3()
	{
		this.tree = new DialogTree();
		this.tree.npcHeader = "Now that you have crafted the weapon of a Jedi, know about The Dark Side, you must.";
		this.tree.response1 = "The Dark Side?";
		this.tree.response1DT = new DialogTree();
		this.tree.response1DT.npcHeader = "Yes, it is from where all negative feelings come. Anger, revenge, violence, things that cloud your judgement and taint your actions. A dangerous thing, The Dark Side is. Stay away from it, you must. Only through mercy and compassion can you truly become one with The Force.";
		this.tree.response1DT.response1 = "But Master, why do I need to know about The Dark Side?";
		this.tree.response1DT.response1DT = new DialogTree();
		this.tree.response1DT.response1DT.npcHeader = "Tempt you, it will. Still there are those who would corrupt you, evil spread across the galaxy, whispering to you through The Force. Listen, you must not. Only pain and suffering will The Dark Side bring. Many Jedi have fallen to it, never to return. Why The Empire exists, sprung from The Dark Side it has.";
		this.tree.response1DT.response1DT.response1 = "But Master, how will I know I'm being tempted?";
		this.tree.response1DT.response1DT.response1DT = new DialogTree();
		this.tree.response1DT.response1DT.response1DT.npcHeader = "The Force will tell you, make a decision you will, guide you I cannot. Go you must, I have taught you all I can for now. Return to me do not, if consumed by The Dark Side, you become.";
		this.tree.response1DT.response1DT.response1DT.response1 = "Don't worry Master, the ways of the Jedi guide me.";
		this.tree.response1DT.response1DT.response1DT.response1DT = new DialogTree();
		this.tree.response1DT.response1DT.response1DT.response2 = "I'll do what I want Master, whatever makes me powerful.";
		this.tree.response1DT.response1DT.response1DT.response2DT = new DialogTree();
		this.tree.response1DT.response1DT.response1DT.response3 = "I'll look towards The Force for guidance Master.";
		this.tree.response1DT.response1DT.response1DT.response3DT = new DialogTree();
		this.tree.response2 = "Sounds like a club.";
		this.tree.response2DT = new DialogTree();
		this.tree.response2DT.npcHeader = "A club it is not, dangerous, it is. It is from where all negative feelings come. Anger, revenge, violence, things that cloud your judgement and taint your actions. A dangerous thing, The Dark Side is. Stay away from it, you must. Only through mercy and compassion can you truly become one with The Force.";
		this.tree.response2DT.response1 = "But Master, why do I need to know about The Dark Side?";
		this.tree.response2DT.response1DT = new DialogTree();
		this.tree.response2DT.response1DT.npcHeader = "Tempt you, it will. Still there are those who would corrupt you, evil spread across the galaxy, whispering to you through The Force. Listen, you must not. Only pain and suffering will The Dark Side bring. Many Jedi have fallen to it, never to return. Why The Empire exists, sprung from The Dark Side it has.";
		this.tree.response2DT.response1DT.response1 = "But Master, how will I know I'm being tempted?";
		this.tree.response2DT.response1DT.response1DT = new DialogTree();
		this.tree.response2DT.response1DT.response1DT.npcHeader = "The Force will tell you, make a decision you will, guide you I cannot. Go you must, I have taught you all I can for now. Return to me do not, if consumed by The Dark Side, you become.";
		this.tree.response2DT.response1DT.response1DT.response1 = "Don't worry Master, the ways of the Jedi guide me.";
		this.tree.response2DT.response1DT.response1DT.response1DT = new DialogTree();
		this.tree.response2DT.response1DT.response1DT.response2 = "I'll do what I want Master, whatever makes me powerful.";
		this.tree.response2DT.response1DT.response1DT.response2DT = new DialogTree();
		this.tree.response2DT.response1DT.response1DT.response3 = "I'll look towards The Force for guidance Master.";
		this.tree.response2DT.response1DT.response1DT.response3DT = new DialogTree();
		this.tree.response3 = "What do I need to know?";
		this.tree.response3DT = new DialogTree();
		this.tree.response3DT.npcHeader = "It is from where all negative feelings come. Anger, revenge, violence, things that cloud your judgement and taint your actions. A dangerous thing, The Dark Side is. Stay away from it, you must. Only through mercy and compassion can you truly become one with The Force.";
		this.tree.response3DT.response1 = "But Master, why do I need to know about The Dark Side?";
		this.tree.response3DT.response1DT = new DialogTree();
		this.tree.response3DT.response1DT.npcHeader = "Tempt you, it will. Still there are those who would corrupt you, evil spread across the galaxy, whispering to you through The Force. Listen, you must not. Only pain and suffering will The Dark Side bring. Many Jedi have fallen to it, never to return. Why The Empire exists, sprung from The Dark Side it has.";
		this.tree.response3DT.response1DT.response1 = "But Master, how will I know I'm being tempted?";
		this.tree.response3DT.response1DT.response1DT = new DialogTree();
		this.tree.response3DT.response1DT.response1DT.npcHeader = "The Force will tell you, make a decision you will, guide you I cannot. Go you must, I have taught you all I can for now. Return to me do not, if consumed by The Dark Side, you become.";
		this.tree.response3DT.response1DT.response1DT.response1 = "Don't worry Master, the ways of the Jedi guide me.";
		this.tree.response3DT.response1DT.response1DT.response1DT = new DialogTree();
		this.tree.response3DT.response1DT.response1DT.response2 = "I'll do what I want Master, whatever makes me powerful.";
		this.tree.response3DT.response1DT.response1DT.response2DT = new DialogTree();
		this.tree.response3DT.response1DT.response1DT.response3 = "I'll look towards The Force for guidance Master.";
		this.tree.response3DT.response1DT.response1DT.response3DT = new DialogTree();
	}

	@Override
	public String getQuestgiverName()
	{
		return "Yoda";
	}

	@Override
	public boolean canBeGivenQuest(EntityPlayer player)
	{
		return !isQuestDone(player, this) && jedi2.isQuestComplete(player) && ItemUtils.hasAnyOfType(player, ItemLightsaber.class);
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
		return "The Dark Side";
	}
}
