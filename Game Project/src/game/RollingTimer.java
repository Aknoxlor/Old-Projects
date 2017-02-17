package game;

/**A millisecond timer that rolls over on trigger
 * @author Andrew
 */
public class RollingTimer
{
	private int timeElapsed = 0;
	private int timeLimit = 0;
	
	public RollingTimer(int limit)
	{
		timeLimit = limit;
	}
	
	public void update(int delta)
	{
		timeElapsed += delta;
	}

	public boolean check()
	{
		if (timeElapsed >= timeLimit)
		{
			timeElapsed -= timeLimit;
			return true;
		}
		return false;
	}
	
	public void reset()
	{
		timeElapsed = 0;
	}
	
	public void setLimit(int limit)
	{
		timeLimit = limit;
	}

}
