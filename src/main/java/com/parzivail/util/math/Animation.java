package com.parzivail.util.math;

import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.util.function.Consumer;

public class Animation
{
	protected int tick = 0;
	protected int length = 0;
	protected boolean loop = false;
	protected boolean isRenderable = false;
	protected boolean reverse;
	protected Consumer<Animation> onAnimationEnd;

	/**
	 * Creates a new animation
	 *
	 * @param length       The length (in ticks) of the animation
	 * @param loop         Whether or not the animation restarts when it's done indefinitely
	 * @param isRenderable Whether or not the animation should be rendered
	 */
	public Animation(int length, boolean loop, boolean isRenderable)
	{
		this.length = length;
		this.loop = loop;
		this.isRenderable = isRenderable;
	}

	/**
	 * A consumer to be called each time the animation ends
	 *
	 * @param onAnimationEnd
	 */
	public void setOnAnimationEnd(Consumer<Animation> onAnimationEnd)
	{
		this.onAnimationEnd = onAnimationEnd;
	}

	/**
	 * Gets whether or not the animation is reversed
	 *
	 * @return
	 */
	public boolean isReverse()
	{
		return reverse;
	}

	/**
	 * Sets the animation's trackSender property
	 *
	 * @param reverse Whether or not the animation should run backwards
	 */
	public void setReverse(boolean reverse)
	{
		if (reverse)
		{
			this.reverse = true;
			this.tick = this.length;
		}
		else
		{
			this.reverse = false;
			this.tick = 0;
		}
	}

	/**
	 * Gets the current tick of the animation
	 *
	 * @return
	 */
	public int getTick()
	{
		return this.tick;
	}

	/**
	 * Gets whether or not the animation loops
	 *
	 * @return
	 */
	public boolean getLoop()
	{
		return this.loop;
	}

	/**
	 * Gets the length of the animation
	 *
	 * @return
	 */
	public int getLength()
	{
		return this.length;
	}

	/**
	 * Gets whether or not the animation is renderable
	 *
	 * @return
	 */
	public boolean getRenderable()
	{
		return this.isRenderable;
	}

	/**
	 * Gets whether or not the animation is done
	 *
	 * @return
	 */
	public boolean isDone()
	{
		return reverse ? this.tick == 0 : this.tick == this.length;
	}

	/**
	 * Renders the animation, provided it is renderable
	 *
	 * @param event The parent RenderOverlay event
	 */
	public void render(RenderGameOverlayEvent event)
	{
	}

	/**
	 * Activates the animation
	 */
	public void start()
	{
		AnimationManager.animations.add(this);
	}

	/**
	 * Deactivated the animation
	 */
	public void stop()
	{
		AnimationManager.animations.remove(this);
	}

	/**
	 * Sends the animation one tick further in time
	 */
	public void tick()
	{
		if (reverse)
			this.tick--;
		else
			this.tick++;
	}
}
