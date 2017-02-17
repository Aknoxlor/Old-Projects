
package game.components;

//import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class PlayerPhysicsComp extends PhysicsComponent
{
	private int speed = 100;

	public PlayerPhysicsComp()
	{
		// this.identity = Type.PHYSICS;
	}

	public int getSpeed()
	{
		return speed;
	}

	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	@Override
	public void update(GameContainer container, int delta)
	{
		acceleration.set(0, 0);
		velocity.set(0, 0);

		if (container.getInput().isKeyDown(Input.KEY_W))
		{
			acceleration.y = -speed;
		}
		else if (container.getInput().isKeyDown(Input.KEY_A))
		{
			acceleration.x = -speed;
		}
		else if (container.getInput().isKeyDown(Input.KEY_S))
		{
			acceleration.y = speed;
		}
		else if (container.getInput().isKeyDown(Input.KEY_D))
		{
			acceleration.x = speed;
		}

		super.update(container, delta);
	}

	@Override
	public void receive(Message msg)
	{

	}
}
