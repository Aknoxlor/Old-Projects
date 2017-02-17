
package game;

import java.util.ArrayList;

public class Animation
{
	private String name;
	private int currentFrame;
	private ArrayList<Integer> animationSequenceX = null;
	private ArrayList<Integer> animationSequenceY = null;

	public Animation(String name)
	{
		this.name = name;
		animationSequenceX = new ArrayList<Integer>();
		animationSequenceY = new ArrayList<Integer>();
	}

	public void addFrame(int x, int y)
	{
		animationSequenceX.add(x);
		animationSequenceY.add(y);
	}

	public int increment()
	{
		currentFrame++;
		if (currentFrame >= animationSequenceX.size())
			currentFrame = 0;
		return currentFrame;
	}

	/*
	 * public void Decrement() { currentFrame--; }
	 */

	public int getX()
	{
		return animationSequenceX.get(currentFrame);
	}

	public int getY()
	{
		return animationSequenceY.get(currentFrame);
	}
	
	public String getName()
	{
		return name;
	}
}
