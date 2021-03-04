package com.parzivail.pswm.mobs;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsItems;
import com.parzivail.pswm.ai.AiFreqMove;
import com.parzivail.pswm.utils.LootGenUtils;
import com.parzivail.util.entity.trade.WeightedLoot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import java.util.ArrayList;
import java.util.List;

public class MobBantha extends EntityHorse implements IShearable
{
	private int field_110285_bP = 0;
	private int sheepTimer;

	public MobBantha(World par1World)
	{
		super(par1World);
		setSize(3.0F, 3.0F);
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
	public void dropFewItems(boolean par1, int par2)
	{
		List<WeightedLoot> drop = new ArrayList<>();
		drop.add(new WeightedLoot(new ItemStack(StarWarsItems.banthaHorn, 1), LootGenUtils.baseRarity / 1.5F));
		if (isBurning())
			drop.add(new WeightedLoot(new ItemStack(StarWarsItems.banthaChopCooked, 1), LootGenUtils.baseRarity));
		else
			drop.add(new WeightedLoot(new ItemStack(StarWarsItems.banthaChop, 1), LootGenUtils.baseRarity));
		entityDropItem(LootGenUtils.getWeightedItemFromList(drop, rand), 0.0F);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(17, (byte)0);
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

	@SideOnly(Side.CLIENT)
	public float func_70890_k(float p_70890_1_)
	{
		if (sheepTimer > 4 && sheepTimer <= 36)
		{
			float f1 = (sheepTimer - 4 - p_70890_1_) / 32.0F;
			return (float)Math.PI / 5F + (float)Math.PI * 7F / 100F * MathHelper.sin(f1 * 28.7F);
		}
		else
			return sheepTimer > 0 ? (float)Math.PI / 5F : rotationPitch / (180F / (float)Math.PI);
	}

	@SideOnly(Side.CLIENT)
	public float func_70894_j(float p_70894_1_)
	{
		return sheepTimer <= 0 ? 0.0F : sheepTimer >= 4 && sheepTimer <= 36 ? 1.0F : sheepTimer < 4 ? (sheepTimer - p_70894_1_) / 4.0F : -(sheepTimer - 40 - p_70894_1_) / 4.0F;
	}

	@Override
	protected String getAngrySoundName()
	{
		return Resources.MODID + ":" + "mob.bantha.say";
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
			return "Pack-Bantha";
		return "Bantha";
	}

	@Override
	protected String getDeathSound()
	{
		return Resources.MODID + ":" + "mob.bantha.die";
	}

	@Override
	public int getHorseType()
	{
		return 2;
	}

	@Override
	protected String getHurtSound()
	{
		return Resources.MODID + ":" + "mob.bantha.hit";
	}

	@Override
	protected String getLivingSound()
	{
		return Resources.MODID + ":" + "mob.bantha.say";
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 1;
	}

	/**
	 * returns true if a sheeps wool has been sheared
	 */
	public boolean getSheared()
	{
		return (dataWatcher.getWatchableObjectByte(17) & 16) != 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte p_70103_1_)
	{
		if (p_70103_1_ == 10)
			sheepTimer = 40;
		else
			super.handleHealthUpdate(p_70103_1_);
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
			if (worldObj.isRemote)
				spawnHorseParticles(true);
		}
		else if (itemstack != null && itemstack.getItem() == Items.bucket)
		{
			if (itemstack.stackSize-- == 1)
				p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, new ItemStack(StarWarsItems.banthaMilk));
			else if (!p_70085_1_.inventory.addItemStackToInventory(new ItemStack(StarWarsItems.banthaMilk)))
				p_70085_1_.dropPlayerItemWithRandomChoice(new ItemStack(StarWarsItems.banthaMilk, 1, 0), false);
			return true;
		}
		return super.interact(p_70085_1_);
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
	{
		return !getSheared() && !isChild();
	}

	@Override
	public void onLivingUpdate()
	{
		if (worldObj.isRemote)
			sheepTimer = Math.max(0, sheepTimer - 1);

		super.onLivingUpdate();

		if (this.isChild())
			setSize(2, 2);
		else
			setSize(4, 4);
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<>();
		setSheared(true);
		int i = 1 + rand.nextInt(3);
		for (int j = 0; j < i; j++)
			ret.add(new ItemStack(Blocks.wool, 1, 12));
		playSound("mob.sheep.shear", 1.0F, 1.0F);
		return ret;
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
	{
		IEntityLivingData p_110161_1_1 = super.onSpawnWithEgg(p_110161_1_);
		return p_110161_1_1;
	}

	/**
	 * make a sheep sheared if set to true
	 */
	public void setSheared(boolean p_70893_1_)
	{
		byte b0 = dataWatcher.getWatchableObjectByte(17);

		if (p_70893_1_)
			dataWatcher.updateObject(17, (byte)(b0 | 16));
		else
			dataWatcher.updateObject(17, (byte)(b0 & -17));
	}

	@Override
	public void updateRiderPosition()
	{
		if (this.riddenByEntity != null)
		{
			float offset = 0.7f;
			if (!(this.riddenByEntity instanceof EntityPlayer))
				offset -= 0.5F;

			float mu = 0.1f;
			float ox = MathHelper.cos((float)Math.toRadians(this.rotationYaw + 90)) * mu;
			float oz = MathHelper.sin((float)Math.toRadians(this.rotationYaw + 90)) * mu;
			this.riddenByEntity.setPosition(this.posX + ox, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset() + offset, this.posZ + oz);
		}
	}
}
