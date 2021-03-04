package com.parzivail.pswm.ai;

import com.parzivail.pswm.Resources;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;

import java.util.List;

public class AiMouseScare extends EntityAIBase
{
	private final IEntitySelector field_98218_a = p_82704_1_ ->
			p_82704_1_.isEntityAlive() && AiMouseScare.this.theEntity.getEntitySenses().canSee(p_82704_1_);
	private EntityCreature theEntity;
	private double farSpeed;
	private double nearSpeed;
	private Entity closestLivingEntity;
	private float distanceFromEntity;
	private PathEntity entityPathEntity;
	private PathNavigate entityPathNavigate;
	private Class targetEntityClass;

	public AiMouseScare(EntityCreature p_i1616_1_, Class p_i1616_2_, float p_i1616_3_, double p_i1616_4_, double p_i1616_6_)
	{
		this.theEntity = p_i1616_1_;
		this.targetEntityClass = p_i1616_2_;
		this.distanceFromEntity = p_i1616_3_;
		this.farSpeed = p_i1616_4_;
		this.nearSpeed = p_i1616_6_;
		this.entityPathNavigate = p_i1616_1_.getNavigator();
		this.setMutexBits(1);
	}

	@Override
	public boolean continueExecuting()
	{
		return !this.entityPathNavigate.noPath();
	}

	@Override
	public void resetTask()
	{
		this.closestLivingEntity = null;
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.targetEntityClass == EntityPlayer.class)
		{
			if (this.theEntity instanceof EntityTameable && ((EntityTameable)this.theEntity).isTamed())
				return false;
			this.closestLivingEntity = this.theEntity.worldObj.getClosestPlayerToEntity(this.theEntity, this.distanceFromEntity);
			if (this.closestLivingEntity == null)
				return false;
		}
		else
		{
			List list = this.theEntity.worldObj.selectEntitiesWithinAABB(this.targetEntityClass, this.theEntity.boundingBox.expand(this.distanceFromEntity, 3.0D, this.distanceFromEntity), this.field_98218_a);
			if (list.isEmpty())
				return false;
			this.closestLivingEntity = (Entity)list.get(0);
		}
		Vec3 vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.theEntity, 16, 7, Vec3.createVectorHelper(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));
		if (vec3 == null)
			return false;
		if (this.closestLivingEntity.getDistanceSq(vec3.xCoord, vec3.yCoord, vec3.zCoord) < this.closestLivingEntity.getDistanceSqToEntity(this.theEntity))
			return false;
		this.entityPathEntity = this.entityPathNavigate.getPathToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord);
		return this.entityPathEntity != null && this.entityPathEntity.isDestinationSame(vec3);
	}

	@Override
	public void startExecuting()
	{
		this.theEntity.playSound(Resources.MODID + ":" + "mob.mouse.scare", 1.0F, 1.0F);
		this.entityPathNavigate.setPath(this.entityPathEntity, this.farSpeed);
	}

	@Override
	public void updateTask()
	{
		if (this.theEntity.getDistanceSqToEntity(this.closestLivingEntity) < 49.0D)
			this.theEntity.getNavigator().setSpeed(this.nearSpeed);
		else
			this.theEntity.getNavigator().setSpeed(this.farSpeed);
	}
}
