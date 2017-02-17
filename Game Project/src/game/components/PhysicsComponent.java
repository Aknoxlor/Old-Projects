package game.components;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

public abstract class PhysicsComponent extends Component
{
	protected Vector2f velocity = new Vector2f(0,0);
	protected Vector2f acceleration = new Vector2f(0,0);
	
	public Vector2f getVelocity()
	{
		return new Vector2f(velocity);
	}
	
	public Vector2f getAcceleration()
	{
		return new Vector2f(acceleration);
	}

	public void setAcceleration(Vector2f acc)
	{
		acceleration = acc;
	}
	
	
	public void addAcceleration(Vector2f acc) //Override for more complicated physics
	{
		acceleration.add(acc);
	}
	
	
	public void update(GameContainer container, int delta)
	{
		velocity.add(acceleration);

		owner.position.x += velocity.x * (delta/1000.0);
		owner.position.y += velocity.y * (delta/1000.0);
	}
}
