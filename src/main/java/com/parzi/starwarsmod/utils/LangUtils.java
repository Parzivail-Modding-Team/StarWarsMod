package com.parzi.starwarsmod.utils;

import com.parzi.starwarsmod.StarWarsMod;

import net.minecraft.client.resources.I18n;

public class LangUtils
{
	public static String translate(String input)
	{
		return I18n.format(input, new Object[0]);
	}
}
