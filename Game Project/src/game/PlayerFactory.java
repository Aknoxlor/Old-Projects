package game;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.SlickException;
import game.components.AnimatorComp;
import game.components.Entity;
import game.components.PlayerPhysicsComp;

public class PlayerFactory
{
	
	public static Entity createPlayer() throws SlickException
	{
		Entity entity = new Entity("Player");
		
		entity.setPosition(new Vector2f(400f, 300f));
		
		entity.addComponent(new PlayerPhysicsComp());
		
		AnimatorComp comp = new AnimatorComp();
		comp.loadAssets("data/PlayerSheet.png");
		
		AnimatorComp.Animation ani = new AnimatorComp.Animation("walk S");
		ani.addFrame(1, 0);
		ani.addFrame(2, 0);
		comp.addAnimation(ani);
		
		ani = new AnimatorComp.Animation("walk W");
		ani.addFrame(1, 1);
		ani.addFrame(2, 1);
		comp.addAnimation(ani);
		
		ani = new AnimatorComp.Animation("walk E");
		ani.addFrame(1, 2);
		ani.addFrame(2, 2);
		comp.addAnimation(ani);
		
		ani = new AnimatorComp.Animation("walk N");
		ani.addFrame(1, 3);
		ani.addFrame(2, 3);
		comp.addAnimation(ani);
		
		ani = new AnimatorComp.Animation("stand S");
		ani.addFrame(0, 0);
		comp.addAnimation(ani);
		
		ani = new AnimatorComp.Animation("stand W");
		ani.addFrame(0, 1);
		comp.addAnimation(ani);
		
		ani = new AnimatorComp.Animation("stand E");
		ani.addFrame(0, 2);
		comp.addAnimation(ani);
		
		ani = new AnimatorComp.Animation("stand N");
		ani.addFrame(0, 3);
		comp.addAnimation(ani);
		
		comp.setAnimation("stand S", true);
		comp.setAnimationSpeed(5);
		comp.setDirectionMovementSync(true);
		entity.addComponent(comp);
		
		return entity;
	}
}
