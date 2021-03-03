package com.parzivail.util.vehicle;

import com.parzivail.pswm.Resources;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.util.math.MathUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class VehicleBase extends EntityLiving
{
	private float[] tiltTable = { 0, 0, 0, 0, 0, 0 };
	public float tilt = 0.0F;
	public float tiltMax = 10;
	public float vehicYOffset = 0.0F;
	public float moveModifier = 1.0F;
	public float frame = 0.0F;
	public float rotationYawLast = 0.0F;
	public float rotationPitchLast = 0.0F;

	public static final int ROT_PLACE_SNAP = 90;

	public static int YAW_DW = 16;
	public static int PITCH_DW = 17;

	public VehicleBase(World p_i1689_1_)
	{
		super(p_i1689_1_);
		this.setSize(1F, 1F);
		this.isImmuneToFire = true;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(YAW_DW, 0f);
		this.dataWatcher.setObjectWatched(YAW_DW);
		this.dataWatcher.addObject(PITCH_DW, 0f);
		this.dataWatcher.setObjectWatched(PITCH_DW);
	}

	@Override
	public void writeToNBT(NBTTagCompound p_70109_1_)
	{
		super.writeToNBT(p_70109_1_);
		p_70109_1_.setTag("Rotation", this.newFloatNBTList(this.getRealYaw(), this.rotationPitch));
		p_70109_1_.setFloat("realYaw", this.getRealYaw());
		p_70109_1_.setFloat("realPitch", this.getRealPitch());
	}

	@SideOnly(Side.CLIENT)
	public void setAngles(float p_70082_1_, float p_70082_2_)
	{
	}

	public void onPlacedBy(EntityPlayer player)
	{
		if (player != null && player.isSneaking())
			this.setRealYaw(MathUtils.roundToNearest(player.rotationYaw, ROT_PLACE_SNAP));
	}

	@Override
	public void readFromNBT(NBTTagCompound p_70020_1_)
	{
		super.readFromNBT(p_70020_1_);
		this.setRealYaw(p_70020_1_.getFloat("realYaw"));
		this.setRealPitch(p_70020_1_.getFloat("realPitch"));
	}

	public float getRealYaw()
	{
		return this.dataWatcher.getWatchableObjectFloat(YAW_DW);
	}

	public void setRealYaw(float realYaw)
	{
		this.dataWatcher.updateObject(YAW_DW, realYaw);
		this.dataWatcher.setObjectWatched(YAW_DW);
	}

	public float getRealPitch()
	{
		return this.dataWatcher.getWatchableObjectFloat(PITCH_DW);
	}

	public void setRealPitch(float realPitch)
	{
		this.dataWatcher.updateObject(PITCH_DW, realPitch);
		this.dataWatcher.setObjectWatched(PITCH_DW);
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public boolean canRenderOnFire()
	{
		return false;
	}

	@Override
	public void fall(float p1)
	{
	}

	@Override
	protected void collideWithEntity(Entity entity)
	{
		if (entity != this.riddenByEntity && (this.motionX > 0.01f || this.motionY > 0.01f || this.motionZ > 0.01f))
			entity.attackEntityFrom(StarWarsMod.roadkillDamageSource, MathHelper.sqrt_float(Math.max(4, 4 * (float)(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ))));
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
		if (!this.worldObj.isRemote && this.riddenByEntity == null)
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
	protected void updateAITasks()
	{
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.moveEntityWithHeading(0.0F, 0.0F);

		this.frame += 0.1F;

		this.setRotation(this.rotationYawLast, this.rotationPitchLast);

		if (this.riddenByEntity == null)
		{
			this.renderYawOffset = this.rotationYawLast = this.rotationYaw = (float)(this.newRotationYaw = getRealYaw());
			this.rotationPitch = this.rotationPitchLast = (float)(this.newRotationPitch = getRealPitch());
		}
		else
		{
			this.setRealYaw(this.riddenByEntity.rotationYaw);
			this.setRealPitch(this.riddenByEntity.rotationPitch);
		}

		if (worldObj.isAirBlock((int)posX, (int)posY - 1, (int)posZ))
		{
			System.arraycopy(this.tiltTable, 1, this.tiltTable, 0, this.tiltTable.length - 1);

			float t = this.rotationYaw - this.prevRotationYaw;

			if (t > this.tiltMax)
				t = this.tiltMax;
			if (t < -this.tiltMax)
				t = -this.tiltMax;

			this.tiltTable[this.tiltTable.length - 1] = t;

			for (float i : this.tiltTable)
				this.tilt += i;

			this.tilt /= this.tiltTable.length;
		}
	}

	@Override
	public void updateRiderPosition()
	{
		if (this.riddenByEntity != null)
		{
			float offset = this.vehicYOffset;
			if (!(this.riddenByEntity instanceof EntityPlayer))
				offset -= 0.5F;
			this.riddenByEntity.setPosition(this.posX, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset() + offset, this.posZ);
		}
	}
}