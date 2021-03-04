package com.parzivail.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class PNBTSerial
{
	private static final HashMap<Class, Pair<Reader, Writer>> handlers = new HashMap();
	private static final HashMap<Class, Field[]> fieldCache = new HashMap();

	static
	{
		map(byte.class, PNBTSerial::readByte, PNBTSerial::writeByte);
		map(short.class, PNBTSerial::readShort, PNBTSerial::writeShort);
		map(int.class, PNBTSerial::readInt, PNBTSerial::writeInt);
		map(long.class, PNBTSerial::readLong, PNBTSerial::writeLong);
		map(float.class, PNBTSerial::readFloat, PNBTSerial::writeFloat);
		map(double.class, PNBTSerial::readDouble, PNBTSerial::writeDouble);
		map(boolean.class, PNBTSerial::readBoolean, PNBTSerial::writeBoolean);
		map(char.class, PNBTSerial::readChar, PNBTSerial::writeChar);
		map(String.class, PNBTSerial::readString, PNBTSerial::writeString);
		map(NBTTagCompound.class, PNBTSerial::readNBT, PNBTSerial::writeNBT);
		map(ItemStack.class, PNBTSerial::readItemStack, PNBTSerial::writeItemStack);

		//map(EntityPlayer.class, PNBTSerial::readPlayer, PNBTSerial::writePlayer);
		//map(Entity.class, PNBTSerial::readEntity, PNBTSerial::writeEntity);
		//map(Vec3.class, PNBTSerial::readVec3, PNBTSerial::writeVec3);
		//map(EntityCooldownEntry.class, PNBTSerial::readEntityCooldownEntry, PNBTSerial::writeEntityCooldownEntry);
		//map(Color.class, PNBTSerial::readColor, PNBTSerial::writeColor);
		//map(World.class, PNBTSerial::readWorld, PNBTSerial::writeWorld);
		//map(ItemStack[].class, PNBTSerial::readItemStacks, PNBTSerial::writeItemStacks);
	}
	
	/*
	 * Reader and Writer methods below
	 */

	private static byte readByte(String field, NBTTagCompound nbt)
	{
		return nbt.getByte(field);
	}

	private static void writeByte(String field, byte a, NBTTagCompound nbt)
	{
		nbt.setByte(field, a);
	}

	private static boolean readBoolean(String field, NBTTagCompound nbt)
	{
		return nbt.getBoolean(field);
	}

	private static void writeBoolean(String field, boolean a, NBTTagCompound nbt)
	{
		nbt.setBoolean(field, a);
	}

	private static short readShort(String field, NBTTagCompound nbt)
	{
		return nbt.getShort(field);
	}

	private static void writeShort(String field, short a, NBTTagCompound nbt)
	{
		nbt.setShort(field, a);
	}

	private static int readInt(String field, NBTTagCompound nbt)
	{
		return nbt.getInteger(field);
	}

	private static void writeInt(String field, int a, NBTTagCompound nbt)
	{
		nbt.setInteger(field, a);
	}

	private static long readLong(String field, NBTTagCompound nbt)
	{
		return nbt.getLong(field);
	}

	private static void writeLong(String field, long a, NBTTagCompound nbt)
	{
		nbt.setLong(field, a);
	}

	private static float readFloat(String field, NBTTagCompound nbt)
	{
		return nbt.getFloat(field);
	}

	private static void writeFloat(String field, float a, NBTTagCompound nbt)
	{
		nbt.setFloat(field, a);
	}

	private static double readDouble(String field, NBTTagCompound nbt)
	{
		return nbt.getDouble(field);
	}

	private static void writeDouble(String field, double a, NBTTagCompound nbt)
	{
		nbt.setDouble(field, a);
	}

	private static char readChar(String field, NBTTagCompound nbt)
	{
		return (char)nbt.getInteger(field);
	}

	private static void writeChar(String field, char a, NBTTagCompound nbt)
	{
		nbt.setInteger(field, a);
	}

	private static String readString(String field, NBTTagCompound nbt)
	{
		return nbt.getString(field);
	}

	private static void writeString(String field, String a, NBTTagCompound nbt)
	{
		nbt.setString(field, a);
	}

	private static NBTTagCompound readNBT(String field, NBTTagCompound nbt)
	{
		return nbt.getCompoundTag(field);
	}

	private static void writeNBT(String field, NBTTagCompound a, NBTTagCompound nbt)
	{
		nbt.setTag(field, a);
	}

	private static ItemStack readItemStack(String field, NBTTagCompound nbt)
	{
		ItemStack stack = new ItemStack(Item.getItemById(0));
		stack.readFromNBT(readNBT(field, nbt));
		return stack;
	}

	private static void writeItemStack(String field, ItemStack a, NBTTagCompound nbt)
	{
		writeNBT(field, a.writeToNBT(new NBTTagCompound()), nbt);
	}
	
	/*
	 * I/O methods below
	 */

	private static boolean acceptField(Field f, Class<?> type)
	{
		int mods = f.getModifiers();
		return !(Modifier.isFinal(mods) || Modifier.isStatic(mods) || Modifier.isTransient(mods)) && handlers.containsKey(type);
	}

	private static Field[] getClassFields(Class<?> clazz)
	{
		if (fieldCache.containsKey(clazz))
			return fieldCache.get(clazz);
		else
		{
			Field[] fields = clazz.getFields();
			Arrays.sort(fields, Comparator.comparing(Field::getName));
			fieldCache.put(clazz, fields);
			return fields;
		}
	}

	private static Pair<Reader, Writer> getHandler(Class<?> clazz)
	{
		Pair<Reader, Writer> pair = handlers.get(clazz);
		if (pair == null)
			throw new RuntimeException("No R/W handler for  " + clazz);
		return pair;
	}

	private static <T> void map(Class<T> type, Reader<T> reader, Writer<T> writer)
	{
		handlers.put(type, Pair.of(reader, writer));
	}

	public final void deserialize(NBTTagCompound nbt)
	{
		try
		{
			Class<?> clazz = this.getClass();
			Field[] clFields = getClassFields(clazz);
			for (Field f : clFields)
			{
				Class<?> type = f.getType();
				if (acceptField(f, type))
					this.readField(f, type, nbt);
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException("Error at reading NBT " + this, e);
		}
	}

	private void readField(Field f, Class clazz, NBTTagCompound nbt) throws IllegalArgumentException, IllegalAccessException
	{
		Pair<Reader, Writer> handler = getHandler(clazz);
		f.set(this, handler.getLeft().read(f.getName(), nbt));
	}

	public final NBTTagCompound serialize()
	{
		NBTTagCompound compound = new NBTTagCompound();
		this.serialize(compound);
		return compound;
	}

	public final void serialize(NBTTagCompound nbt)
	{
		try
		{
			Class<?> clazz = this.getClass();
			Field[] clFields = getClassFields(clazz);
			for (Field f : clFields)
			{
				Class<?> type = f.getType();
				if (acceptField(f, type))
					this.writeField(f, type, nbt);
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException("Error at writing NBT " + this, e);
		}
	}

	private void writeField(Field f, Class clazz, NBTTagCompound nbt) throws IllegalArgumentException, IllegalAccessException
	{
		Pair<Reader, Writer> handler = getHandler(clazz);
		handler.getRight().write(f.getName(), f.get(this), nbt);
	}

	interface Reader<T>
	{
		T read(String name, NBTTagCompound nbt);
	}

	interface Writer<T>
	{
		void write(String name, T t, NBTTagCompound nbt);
	}

}