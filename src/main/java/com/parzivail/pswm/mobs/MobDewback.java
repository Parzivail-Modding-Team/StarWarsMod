package com.parzivail.pswm.mobs;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.ai.AiFreqMove;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class MobDewback extends EntityHorse
{
	private int field_110285_bP = 0;

	public MobDewback(World par1World)
	{
		super(par1World);
		setSize(2.0F, 2.0F);
		getNavigator().setCanSwim(true);
		tasks.addTask(0, new AiFreqMove(this, 1, 0));
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
					playSound("mob.horse.gallop", soundtype.getVolume() * 0.15F, soundtype.getPitch());
				else if (field_110285_bP <= 5)
					playSound("mob.horse.wood", soundtype.getVolume() * 0.15F, soundtype.getPitch());
			}
			else if (soundtype == Block.soundTypeWood)
				playSound("mob.horse.wood", soundtype.getVolume() * 0.15F, soundtype.getPitch());
			else
				playSound("mob.horse.soft", soundtype.getVolume() * 0.15F, soundtype.getPitch());
		}
	}

	protected Item func_146068_u()
	{
		return null;
	}

	@Override
	protected String getAngrySoundName()
	{
		return Resources.MODID + ":" + "mob.dewback.say";
	}

	@Override
	public boolean getCanSpawnHere()
	{
		return true;
	}

	@Override
	public String getCommandSenderName()
	{
		if (isChested())
			return "Pack-Dewback";
		return "Dewback";
	}

	@Override
	protected String getDeathSound()
	{
		return Resources.MODID + ":" + "mob.dewback.die";
	}

	@Override
	public int getHorseType()
	{
		return 2;
	}

	@Override
	protected String getHurtSound()
	{
		return Resources.MODID + ":" + "mob.dewback.hit";
	}

	@Override
	protected String getLivingSound()
	{
		return Resources.MODID + ":" + "mob.dewback.say";
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
		return super.interact(p_70085_1_);
	}

	@Override
	public void updateRiderPosition()
	{
		super.updateRiderPosition();
		float f = MathHelper.sin(renderYawOffset * 3.1415927F / 180.0F);
		float f1 = MathHelper.cos(renderYawOffset * 3.1415927F / 180.0F);
		float f2 = 0.5F;
		float f3 = 1.0F;
		riddenByEntity.setPosition(posX + f2 * f, posY + getMountedYOffset() + riddenByEntity.getYOffset() + f3 - 0.3499999940395355D, posZ - f2 * f1);
		if (riddenByEntity instanceof EntityLivingBase)
			((EntityLivingBase)riddenByEntity).renderYawOffset = renderYawOffset;
	}
}
