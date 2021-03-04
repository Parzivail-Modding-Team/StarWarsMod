package com.parzivail.pswm.weaponry;

import com.parzivail.pswm.Resources;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class WeaponSwivelBase extends EntityLiving
{
	public float vehicXOffset = 0.0F;
	public float vehicYOffset = 0.0F;
	public float vehicZOffset = 0.0F;
	public float rotationLast = 0.0F;
	public float rotationPitchLast = 0.0F;

	public WeaponSwivelBase(World p_i1689_1_)
	{
		super(p_i1689_1_);
		this.setSize(0.9F, 0.9F);
	}

	@Override
	public void fall(float p1)
	{
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block blockIn)
	{
		this.playSound(Resources.MODID + ":" + this.getMovingSound(), 0.15F, 1.0F);
	}

	public String getMovingSound()
	{
		return "vehicle.default.move";
	}

	@Override
	public boolean interact(EntityPlayer p_70085_1_)
	{
		if (!this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == p_70085_1_))
		{
			p_70085_1_.mountEntity(this);
			return true;
		}
		return false;
	}

	@Override
	protected boolean isAIEnabled()
	{
		return false;
	}

	@Override
	public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_)
	{
		if (this.riddenByEntity != null)
		{
			this.rotationLast = this.rotationYaw += this.riddenByEntity.rotationYaw - this.rotationLast;
			this.rotationPitchLast = this.rotationPitch += this.riddenByEntity.rotationPitch - this.rotationPitchLast;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.moveEntityWithHeading(0.0F, 0.0F);
		this.setRotation(0, 0);
	}

	@Override
	public void updateRiderPosition()
	{
		if (this.riddenByEntity != null)
			this.riddenByEntity.setPosition(this.posX + this.vehicXOffset, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset() + this.vehicYOffset, this.posZ + this.vehicZOffset);
	}
}
