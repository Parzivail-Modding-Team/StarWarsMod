package com.parzivail.pswm.ai;

import com.parzivail.pswm.mobs.IShootThings;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;

/**
 * @author Colby
 */
public class AiShoot extends EntityAIBase
{
	/**
	 * The entity the AI instance has been applied to
	 */
	private final EntityLiving entityHost;
	/**
	 * The entity (as a RangedAttackMob) the AI instance has been applied to.
	 */
	private final EntityLiving rangedAttackEntityHost;
	private EntityLivingBase attackTarget;
	/**
	 * A decrementing tick that spawns a ranged attack once this value reaches 0. It is then set back to the
	 * maxRangedAttackTime.
	 */
	private int rangedAttackTime;
	private double entityMoveSpeed;
	private int field_75318_f;
	private int minRangeAttackTime;
	/**
	 * The maximum time the AI has to wait before peforming another ranged attack.
	 */
	private int maxRangedAttackTime;
	private float field_96562_i;
	private float field_82642_h;

	public AiShoot(IShootThings entityHost, double entityMoveSpeed, int maxRangeAttackTime, float p_i1649_5_)
	{
		this(entityHost, entityMoveSpeed, maxRangeAttackTime, maxRangeAttackTime, p_i1649_5_);
	}

	public AiShoot(IShootThings entityHost, double entityMoveSpeed, int minRangeAttackTime, int maxRangeAttackTime, float p_i1650_6_)
	{
		this.rangedAttackTime = -1;

		if (!(entityHost instanceof EntityLiving))
		{
			throw new IllegalArgumentException("ArrowAttackGoal requires EntityLiving");
		}
		else
		{
			this.rangedAttackEntityHost = (EntityLiving)entityHost;
			this.entityHost = (EntityLiving)entityHost;
			this.entityMoveSpeed = entityMoveSpeed;
			this.minRangeAttackTime = minRangeAttackTime;
			this.maxRangedAttackTime = maxRangeAttackTime;
			this.field_96562_i = p_i1650_6_;
			this.field_82642_h = p_i1650_6_ * p_i1650_6_;
			this.setMutexBits(3);
		}
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		EntityLivingBase entity = this.entityHost.getAttackTarget();

		if (entity == null)
		{
			return false;
		}
		else if (shouldIAttack(entity))
		{
			this.attackTarget = entity;
			return true;
		}
		return false;
	}

	private boolean shouldIAttack(EntityLivingBase entity)
	{
		return true;
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting()
	{
		return this.shouldExecute() || !this.entityHost.getNavigator().noPath();
	}

	/**
	 * Resets the task
	 */
	public void resetTask()
	{
		this.attackTarget = null;
		this.field_75318_f = 0;
		this.rangedAttackTime = -1;
	}

	/**
	 * Updates the task
	 */
	public void updateTask()
	{
		double d0 = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.boundingBox.minY, this.attackTarget.posZ);
		boolean flag = this.entityHost.getEntitySenses().canSee(this.attackTarget);

		if (flag)
		{
			++this.field_75318_f;
		}
		else
		{
			this.field_75318_f = 0;
		}

		if (d0 <= (double)this.field_82642_h && this.field_75318_f >= 20)
		{
			this.entityHost.getNavigator().clearPathEntity();
		}
		else
		{
			this.entityHost.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.entityMoveSpeed);
		}

		this.entityHost.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);
		float f;

		if (--this.rangedAttackTime == 0)
		{
			if (d0 > (double)this.field_82642_h || !flag)
			{
				return;
			}

			f = MathHelper.sqrt_double(d0) / this.field_96562_i;
			float f1 = Math.max(f, 0.1F);

			if (f1 > 1.0F)
			{
				f1 = 1.0F;
			}

			((IShootThings)this.rangedAttackEntityHost).rangeAttack(this.attackTarget, f1);
			this.rangedAttackTime = MathHelper.floor_float(f * (float)(this.maxRangedAttackTime - this.minRangeAttackTime) + (float)this.minRangeAttackTime);
		}
		else if (this.rangedAttackTime < 0)
		{
			f = MathHelper.sqrt_double(d0) / this.field_96562_i;
			this.rangedAttackTime = MathHelper.floor_float(f * (float)(this.maxRangedAttackTime - this.minRangeAttackTime) + (float)this.minRangeAttackTime);
		}
	}
}

