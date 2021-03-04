package com.parzivail.pswm.mobs;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.ai.AiFreqMove;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MobTauntaun extends EntityHorse
{
	private int field_110285_bP = 0;

	public MobTauntaun(World par1World)
	{
		super(par1World);
		setSize(1.0F, 3.0F);
		getNavigator().setCanSwim(true);
		tasks.addTask(0, new AiFreqMove(this, 1.25f, 0));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.1D);
	}

	@Override
	public boolean canMateWith(EntityAnimal p_70878_1_)
	{
		return false;
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block blockIn)
	{
		Block.SoundType soundtype = blockIn.stepSound;
		if (worldObj.getBlock(x, y + 1, z) == Blocks.snow_layer)
			soundtype = Blocks.snow_layer.stepSound;
		if (!blockIn.getMaterial().isLiquid())
		{
			int l = getHorseType();
			if (riddenByEntity != null && l != 1 && l != 2)
			{
				field_110285_bP += 1;
				if (field_110285_bP > 5 && field_110285_bP % 3 == 0)
					playSound(Resources.MODID + ":" + "mob.tauntaun.move", soundtype.getVolume() * 0.15F, soundtype.getPitch());
				else if (field_110285_bP <= 5)
					playSound(Resources.MODID + ":" + "mob.tauntaun.move", soundtype.getVolume() * 0.15F, soundtype.getPitch());
			}
			else if (soundtype == Block.soundTypeWood)
				playSound(Resources.MODID + ":" + "mob.tauntaun.move", soundtype.getVolume() * 0.15F, soundtype.getPitch());
			else
				playSound(Resources.MODID + ":" + "mob.tauntaun.move", soundtype.getVolume() * 0.15F, soundtype.getPitch());
		}
	}

	@Override
	protected String getAngrySoundName()
	{
		return Resources.MODID + ":" + "mob.tauntaun.hit";
	}

	@Override
	public boolean getCanSpawnHere()
	{
		return isValidLightLevel() && rand.nextInt(20) == 0;
	}

	@Override
	public String getCommandSenderName()
	{
		if (isChested())
			return "Pack-Tauntaun";
		return "Tauntaun";
	}

	@Override
	protected String getDeathSound()
	{
		return Resources.MODID + ":" + "mob.tauntaun.die";
	}

	@Override
	public int getHorseType()
	{
		return 2;
	}

	@Override
	protected String getHurtSound()
	{
		return Resources.MODID + ":" + "mob.tauntaun.hit";
	}

	@Override
	protected String getLivingSound()
	{
		return Resources.MODID + ":" + "mob.tauntaun.say";
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

	@Override
	public boolean interact(EntityPlayer p_70085_1_)
	{
		ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();
		if (itemstack != null && itemstack.getItem() == Items.spawn_egg)
			return false;
		if (!isTame())
		{
			setHorseTamed(true);
			playLivingSound();
			if (p_70085_1_.worldObj.isRemote)
				spawnHorseParticles(true);
		}
		return super.interact(p_70085_1_);
	}

	protected boolean isValidLightLevel()
	{
		return true;
	}

	@Override
	public void updateRiderPosition()
	{
		if (riddenByEntity != null)
			riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset() - 0.6f, posZ);
	}
}
