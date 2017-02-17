package game.components;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class PlayerBehavior extends Component
{
	//private PhysicsComponent physicsRef = null;
	boolean isGrabbing = false;

	@Override
	public void update(GameContainer container, int delta)
	{
		if(container.getInput().isKeyPressed(Input.KEY_G))
		{
			if(isGrabbing = false)
			{
				//Entity wall = WallFactory.createWall(owner.position);
				//container.g
				//wall.grab(this);
				//inventory = wall;
			}
			else
			{
				//inventory.ungrab();
				//inventory = null;
			}

		}
	}

	@Override
	public void receive(Message msg)
	{

	}

}
