package com.parzivail.pswm.quest.sith;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.items.weapons.ItemLightsaber;
import com.parzivail.pswm.quest.DialogTree;
import com.parzivail.pswm.quest.Quest;
import com.parzivail.util.world.ItemUtils;
import net.minecraft.entity.player.EntityPlayer;

import static com.parzivail.pswm.items.ItemQuestLog.isQuestDone;
import static com.parzivail.pswm.items.ItemQuestLog.setQuestDone;
import static com.parzivail.pswm.quest.QuestBank.sith3;
import static com.parzivail.util.ui.TextUtils.makeObfuscated;

/**
 * Created by Colby on 5/8/2016.
 */
public class Sith3_PostSaber extends Quest
{
	public Sith3_PostSaber()
	{
		this.tree = new DialogTree();
		this.tree.npcHeader = "";
		this.tree.response1 = "Master, I have returned with my lightsaber.";
		this.tree.response1DT = new DialogTree();
		this.tree.response1DT.npcHeader = "Ah yes, good, good. I sense much power in your crystal, you have truly become one with The Dark Side. You are becoming a fine Apprentice indeed.";
		this.tree.response1DT.response1 = "Thank you Master, what is next for me?";
		this.tree.response1DT.response1DT = new DialogTree();
		this.tree.response1DT.response1DT.npcHeader = "I have nothing left for you Apprentice. I will find use for you soon enough. For now, train. Become more and more powerful and drown yourself in The Dark Side.";
		this.tree.response1DT.response1DT.response1 = "Yes Master, I will look to The Dark Side for guidance.";
		this.tree.response1DT.response1DT.response1DT = new DialogTree();
		this.tree.response1DT.response2 = "Of course Master, your guidance has been invaluable.";
		this.tree.response1DT.response2DT = new DialogTree();
		this.tree.response1DT.response2DT.npcHeader = "It takes much darkness in a heart to flourish as you have. Go now and train, I have no further use for you at this time. Return to me when you have become more powerful.";
		this.tree.response1DT.response2DT.response1 = "Yes Master, I will look to The Dark Side for guidance.";
		this.tree.response1DT.response2DT.response1DT = new DialogTree();
		this.tree.response1DT.response3 = "And perhaps a fine Master soon enough.";
		this.tree.response1DT.response3DT = new DialogTree();
		this.tree.response1DT.response3DT.npcHeader = "You would be foolish to challenge me young Apprentice. I also have no further use for you at this time. I suggest you go and train, perhaps become more powerful than you are now, for you still have much to learn.";
		this.tree.response1DT.response3DT.response1 = "Yes Master, I will look to The Dark Side for guidance.";
		this.tree.response1DT.response3DT.response1DT = new DialogTree();
	}

	@Override
	public String getQuestgiverName()
	{
		return makeObfuscated("Zannah");
	}

	@Override
	public boolean canBeGivenQuest(EntityPlayer player)
	{
		return !isQuestDone(player, this) && sith3.isQuestComplete(player) && ItemUtils.hasAnyOfType(player, ItemLightsaber.class);
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
		return " The Weapon of a Sith ";
	}
}
