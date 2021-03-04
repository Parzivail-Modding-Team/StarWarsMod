package com.parzivail.util.ui;

import java.util.StringTokenizer;

public class TextUtils
{
	/**
	 * Adds a text effect to a string, terminating it with a RESET flag
	 *
	 * @param p1     The string to affect
	 * @param effect The effect to use
	 * @return The affected string
	 */
	public static String addEffect(String p1, String effect)
	{
		return effect + p1 + TextEffects.RESE;
	}

	/**
	 * Makes a string bold
	 *
	 * @param p1 The string to bold
	 * @return The bolded string
	 */
	public static String makeBold(String p1)
	{
		return TextEffects.BOLD + p1 + TextEffects.RESE;
	}

	/**
	 * Makes a string italicized
	 *
	 * @param p1 The string to italicize
	 * @return The italicized string
	 */
	public static String makeItalic(String p1)
	{
		return TextEffects.ITAL + p1 + TextEffects.RESE;
	}

	/**
	 * Obfuscates a string
	 *
	 * @param p1 The string to obfuscate
	 * @return The obfuscated string
	 */
	public static String makeObfuscated(String p1)
	{
		return TextEffects.OBFU + p1 + TextEffects.RESE;
	}

	/**
	 * Makes a string strikethrough
	 *
	 * @param p1 The string to strike
	 * @return The struck string
	 */
	public static String makeStrikethrough(String p1)
	{
		return TextEffects.STRI + p1 + TextEffects.RESE;
	}

	/**
	 * Underlines a string
	 *
	 * @param p1 The string to underline
	 * @return The underlined string
	 */
	public static String makeUnderline(String p1)
	{
		return TextEffects.UNDE + p1 + TextEffects.RESE;
	}

	/**
	 * Splits a string into lines not exceeding maxCharInLine, but preserving spaces
	 *
	 * @param input         The string to split
	 * @param maxCharInLine The maximum characters in a line
	 * @return The split string
	 */
	public static String[] splitIntoLine(String input, int maxCharInLine)
	{

		StringTokenizer tok = new StringTokenizer(input, " ");
		StringBuilder output = new StringBuilder(input.length());
		int lineLen = 0;
		while (tok.hasMoreTokens())
		{
			String word = tok.nextToken();

			while (word.length() > maxCharInLine)
			{
				output.append(word, 0, maxCharInLine - lineLen).append("\n");
				word = word.substring(maxCharInLine - lineLen);
				lineLen = 0;
			}

			if (lineLen + word.length() > maxCharInLine)
			{
				output.append('\n');
				lineLen = 0;
			}
			output.append(word).append(' ');

			lineLen += word.length() + 1;
		}
		return output.toString().split("\n");
	}

	/**
	 * Translates some text into Besh
	 *
	 * @param english The string to translate
	 * @return The translated string
	 */
	public static String translateAurebesh(String english)
	{
		if (english.equalsIgnoreCase("T-65B X-Wing Starfighter"))
			return "LOOK OUT, REBEL SCUM!";
		if (english.equalsIgnoreCase("TIE/LN Starfighter"))
			return "LOOK AT MEEEEE VADER";
		if (english.equalsIgnoreCase("TIE/IN Interceptor"))
			return "I GOT POINTY WINGSSSSS";
		if (english.equalsIgnoreCase("RZ-1 A-Wing Interceptor"))
			return "THESE PILOTS ARE FISH!";
		if (english.equalsIgnoreCase("Wampa"))
			return "SWAMPYSWAMP";
		if (english.equalsIgnoreCase("Sandtrooper"))
			return "LOOK SIR, DROIDS!";
		if (english.equalsIgnoreCase("Protocol Droid"))
			return "JUST A BRITISH DUDE";
		if (english.equalsIgnoreCase("Astromech Droid"))
			return "Artoo Detoo, Whaddup?";
		if (english.equalsIgnoreCase("Jawa"))
			return "Utinni!";
		if (english.equalsIgnoreCase("Ewok"))
			return "Yub Yub!";
		if (english.equalsIgnoreCase("Tusken Raider"))
			return "ERGHHHHHHH";
		if (english.equalsIgnoreCase("Bantha"))
			return "Lil' Baby Banthy Banth";
		return english;
	}

	/**
	 * Translates some text into long Besh
	 *
	 * @param english The string to translate
	 * @return The translated string
	 */
	public static String translateAurebeshLong(String english)
	{
		if (english.equalsIgnoreCase("T-65B X-Wing Starfighter"))
			return "BETTER THAN TIE FIGHTERS, ACTUALLY";
		if (english.equalsIgnoreCase("TIE/LN Starfighter"))
			return "AY BREH WHAT IT DO, I SEE YOU ROLLIN IN THAT LN";
		if (english.equalsIgnoreCase("TIE/IN Interceptor"))
			return "YOU AREN'T BETTER THAN US YOU POINTY WING IDIOT";
		if (english.equalsIgnoreCase("RZ-1 A-Wing Interceptor"))
			return "ALL YOU PILOTS LOOK LIKE DRIED ANCHOVIES";
		if (english.equalsIgnoreCase("Wampa"))
			return "THE ILLUSIVE SWAMPA";
		if (english.equalsIgnoreCase("Sandtrooper"))
			return "LOOK AT THESE IDIOTS WITH DIRTY ARMOR";
		if (english.equalsIgnoreCase("Protocol Droid"))
			return "IT'S NOT A LIFE FORM SO DON'T SHOOT IT";
		if (english.equalsIgnoreCase("Astromech Droid"))
			return "YOU RUSTED BUCKET OF BOLTS!";
		if (english.equalsIgnoreCase("Jawa"))
			return "THEIR LITTLE GLOW EYES ARE SUS AS HECK";
		if (english.equalsIgnoreCase("Ewok"))
			return "WATCH OUT THEY'LL EAT US IF THEY CATCH US";
		if (english.equalsIgnoreCase("Tusken Raider"))
			return "LOOK AT THESE IDIOTS WITH THE MASKS";
		if (english.equalsIgnoreCase("Bantha"))
			return "Lil' Baby Banthy Banth";
		return english;
	}

	public static String upperFirst(String s)
	{
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}