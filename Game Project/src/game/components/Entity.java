
package game.components;

import game.components.Component.Message;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Entity
{
	private String identity;
	protected Vector2f position;

	//Holds unique singular components
	private Renderable renderComp = null;
	private PhysicsComponent physicsComp = null;
//	private CollisionComponent CollisionComp = null;
	
	//Holds all components anonymously for iteration purposes
	private ArrayList<Component> components = null; 
	
	private ArrayDeque<Message> messageQueue = null;
	
	//Holds reference to container
	//private EntityManager manager = null;
	
	
	public Entity(String identity) //TODO: Add reference for container
	{
		this.identity = identity;
		
		components = new ArrayList<Component>(3);
		messageQueue = new ArrayDeque<Message>();
		position = new Vector2f(0f, 0f);
	}
	
	
	public String getIdentity()
	{
		return identity;
	}
	
	
	public Vector2f getPosition()
	{
		return new Vector2f(position);
	}
	
	public void setPosition(Vector2f position)
	{
		this.position = position;
	}
	
	
	public Renderable getRenderComponent()
	{
		return renderComp;
	}
	
	public PhysicsComponent getPhysicsComponent()
	{
		return physicsComp;
	}
	
	
	public Component getComponent(Object component) //Refactor for separate gets for unique components; Use only for auxillary component list and/or generic get
	{	
		for(Component comp : components)
		{
			if(component.getClass().isInstance(comp));
				return comp;
		}
		
		return null;
	}
	
	public void addComponent(Component comp)
	{
		if(Renderable.class.isInstance(comp))
		{
			if(renderComp != null)
				components.remove(renderComp);
			renderComp = (Renderable)comp;	
		}
		
		if(PhysicsComponent.class.isInstance(comp))
		{
			if(physicsComp != null)
				components.remove(physicsComp);
			physicsComp = (PhysicsComponent)comp;
		}
			
		comp.setOwner(this);
		components.add(comp);
	}
	
	
	public void send(Message msg)
	{
		messageQueue.push(msg);
	}
	
	
	public void update(GameContainer container, int delta)
	{
		for(Component comp : components)
		{
			comp.update(container, delta);
		}
		
		while(!messageQueue.isEmpty())
		{
			Message msg = messageQueue.pollFirst();
			for(Component comp : components)
			{
				comp.receive(msg);
			}
		}
	}
	
	public void render(GameContainer gc, Graphics gr)
	{
		if(renderComp != null)
			renderComp.render(gc, gr);
	}
}

