package com.parzivail.util.entity;

import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.mobs.EntityDroidBase;
import com.parzivail.util.ui.LangUtils;
import com.parzivail.util.ui.Lumberjack;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class EntityUtils
{
	private static int mobId = 300;

	/**
	 * Gets the Droid sitting message
	 *
	 * @param isSitting Whether or not the target is sitting
	 * @return The appropriate string based on isSitting
	 */
	public static String getDroidSittingMessage(boolean isSitting)
	{
		return isSitting ? "Restrained" : "Unrestrained";
	}

	/**
	 * Gets the Debug text of a Rebel droid based on the given parameters about the droid
	 *
	 * @param droid
	 * @param list
	 * @param player
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return The List<String> of debug text
	 */
	public static List<String> getRebelDroidDebugText(EntityDroidBase droid, List<String> list, EntityPlayer player, World world, int x, int y, int z)
	{
		list.add(droid.isTamed() ? LangUtils.translate("owned") : LangUtils.translate("for.sale"));
		if (droid.getOwner() != null)
			list.add(LangUtils.translate("owner.0", droid.getOwner().getCommandSenderName()));
		list.add(droid.isSitting() ? LangUtils.translate("restrained") : LangUtils.translate("unrestrained"));

		return list;
	}

	/**
	 * Gets the Debug text of a Rebel droid based on the given parameters about the droid
	 *
	 * @param droid
	 * @param list
	 * @param player
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return The List<String> of debug text
	 */
	public static List<String> getImperialDroidDebugText(EntityDroidBase droid, List<String> list, EntityPlayer player, World world, int x, int y, int z)
	{
		list.add(droid.isTamed() ? LangUtils.translate("hacked") : LangUtils.translate("employed"));
		if (droid.getOwner() != null)
			list.add("Hacker: " + droid.getOwner().getCommandSenderName());
		list.add(droid.isSitting() ? LangUtils.translate("restrained") : LangUtils.translate("unrestrained"));

		return list;
	}

	/**
	 * Gets the last used Mob ID
	 *
	 * @return
	 */
	public static int getLastUsedId()
	{
		return mobId;
	}

	/**
	 * Ray-traces from the given entity's POV
	 *
	 * @param distance   The range of the trace
	 * @param fromEntity The POV entity
	 * @param exclude    The entity references to exclude (Note: not classes, but
	 *                   inequality between two entity pointers)
	 * @return Returns the entity the trace hit, or null if none is hit
	 */
	public static Entity rayTrace(int distance, EntityLivingBase fromEntity, Entity[] exclude)
	{
		if (fromEntity != null)
			if (fromEntity.worldObj != null)
			{
				Entity pointedEntity = null;
				double d0 = distance;
				MovingObjectPosition objectMouseOver = fromEntity.rayTrace(d0, 1);
				double d1 = d0;
				Vec3 vec3 = fromEntity.getPosition(0);

				if (objectMouseOver != null)
					d1 = objectMouseOver.hitVec.distanceTo(vec3);

				Vec3 vec31 = fromEntity.getLook(0);
				Vec3 vec32 = vec3.addVector(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0);
				pointedEntity = null;
				float f1 = 1.0F;
				List list = fromEntity.worldObj.getEntitiesWithinAABBExcludingEntity(fromEntity, fromEntity.boundingBox.addCoord(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0).expand(f1, f1, f1));
				double d2 = d1;

				for (int i = 0; i < list.size(); ++i)
				{
					Entity entity = (Entity)list.get(i);

					if (entity.canBeCollidedWith())
					{
						float f2 = entity.getCollisionBorderSize();
						AxisAlignedBB axisalignedbb = entity.boundingBox.expand(f2, f2, f2);
						MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);

						if (axisalignedbb.isVecInside(vec3))
						{
							if (0.0D < d2 || d2 == 0.0D)
								if (!Arrays.asList(exclude).contains(entity))
								{
									pointedEntity = entity;
									d2 = 0.0D;
								}
						}
						else if (movingobjectposition != null)
						{
							double d3 = vec3.distanceTo(movingobjectposition.hitVec);

							if (d3 < d2 || d2 == 0.0D)
								if (!Arrays.asList(exclude).contains(entity))
								{
									pointedEntity = entity;
									d2 = d3;
								}
						}
					}
				}
				return pointedEntity;
			}
		return null;
	}

	/**
	 * Registers an Entity with FML
	 *
	 * @param entityClass The class to register
	 * @param entityName  The name of the entity
	 */
	public static void registerEntity(Class<? extends Entity> entityClass, String entityName)
	{
		while (EntityList.getClassFromID(mobId) != null)
			mobId += 1;
		EntityRegistry.registerModEntity(entityClass, entityName, mobId, StarWarsMod.instance, 80, 1, true);
		EntityList.IDtoClassMapping.put(Integer.valueOf(mobId), entityClass);
		Lumberjack.debug("Registered entity \"" + entityName + "\" as ID " + String.valueOf(mobId));
	}

	/**
	 * Registers an Entity with FML, and creates a genComposite egg
	 *
	 * @param mobClass The class to register
	 * @param mobName  The name of the entity
	 * @param bgColor  The background color of the egg
	 * @param fgColor  The foreground color of the egg
	 */
	public static void registerWithSpawnEgg(Class<? extends Entity> mobClass, String mobName, int bgColor, int fgColor)
	{
		while (EntityList.getClassFromID(mobId) != null)
			mobId += 1;
		EntityRegistry.registerModEntity(mobClass, mobName, mobId, StarWarsMod.instance, 80, 1, true);
		EntityList.IDtoClassMapping.put(Integer.valueOf(mobId), mobClass);
		EntityList.entityEggs.put(Integer.valueOf(mobId), new EntityList.EntityEggInfo(mobId, bgColor, fgColor));
		Lumberjack.debug("Registered entity (and egg) \"" + mobName + "\" as ID " + String.valueOf(mobId));
	}
}