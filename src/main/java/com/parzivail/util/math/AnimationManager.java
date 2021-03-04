package com.parzivail.util.math;

import com.parzivail.util.ui.Lumberjack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class AnimationManager
{
	public static ArrayList<Animation> animations = new ArrayList<>();

	/**
	 * Renders all active (renderable) animations
	 *
	 * @param event The game overlay event that is the base of the render
	 */
	public static void render(RenderGameOverlayEvent event)
	{
		try
		{
			for (Animation t : animations)
			{
				if (t.getRenderable())
					t.render(event);
			}
		}
		catch (ConcurrentModificationException e)
		{
			Lumberjack.err("ConcurrentModificationException@AnimationManager.class:23");
		}
	}

	/**
	 * Ticks all active animations forwards in time
	 */
	public static void tick()
	{
		Iterator<Animation> i = animations.iterator();

		while (i.hasNext())
		{
			Animation t = i.next();
			t.tick();
			if (t.reverse && t.getTick() <= 0)
			{
				if (t.onAnimationEnd != null)
					t.onAnimationEnd.accept(t);
				if (t.getLoop())
					t.tick = t.getLength();
				else
					i.remove();
			}
			else if (!t.reverse && t.getTick() >= t.getLength())
			{
				if (t.onAnimationEnd != null)
					t.onAnimationEnd.accept(t);
				if (t.getLoop())
					t.tick = 0;
				else
					i.remove();
			}

		}
	}
}
