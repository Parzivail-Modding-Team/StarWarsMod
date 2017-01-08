package com.parzivail.util;

import com.parzivail.pswm.Resources;
import net.minecraft.util.ResourceLocation;

import java.util.Locale;

/**
 * Created by colby on 12/20/2016.
 */
public class Util
{
	public static String moddot(String name)
	{
		return String.format("%s.%s", Resources.MODID, name.toLowerCase(Locale.US));
	}

	public static String modcolon(String name)
	{
		return String.format("%s:%s", Resources.MODID, name.toLowerCase(Locale.US));
	}

	public static ResourceLocation getResource(String res)
	{
		return new ResourceLocation(Resources.MODID, res);
	}

	public static Integer[] toIntegerArray(int[] b)
	{
		Integer[] a = new Integer[b.length];
		for (int i = 0; i < a.length; i++)
			a[i] = b[i];
		return a;
	}
}