
package game.components;

import org.newdawn.slick.GameContainer;

public abstract class Component
{
	//protected Type identity = null;
	protected Entity owner = null;
	
	public abstract void update(GameContainer container, int delta);
	public abstract void receive(Message msg);

	public void setOwner(Entity owner)
	{
		this.owner = owner;
	}
	
	public enum Message
	{
		//Basic Messages
		CREATED, 
		DESTROYED,
		UPDATED,
		RENDERED
	}
}