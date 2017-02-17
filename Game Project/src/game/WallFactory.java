package game;

import game.components.Entity;
import game.components.SpriteComponent;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class WallFactory
{
	public static Entity createWall(Vector2f position) throws SlickException
	{
		Entity entity = new Entity("Wall");
		
		entity.setPosition(position);
		
		//entity.addComponent(new WallBehavior());
		
		SpriteComponent comp = new SpriteComponent();
		comp.loadAssets("data/Wall.png");
		entity.addComponent(comp);
		
		return entity;
	}
}
