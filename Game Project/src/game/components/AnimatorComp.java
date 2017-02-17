
package game.components;

import game.RollingTimer;
//import game.components.Component.Message;
import java.util.ArrayList;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AnimatorComp extends Component implements Renderable
{
	//private float rotation;
	private float scale = 2;
	private boolean isRepeating = true;
	private RollingTimer timer = null;
	private boolean directionMovementSync = false;

	private Animation curAnimation = null;
	private ArrayList<Animation> animations = null;

	private SpriteSheet masterSheet = null;

	private PhysicsComponent physicsRef = null; //For movement direction sync. May get rid of depending on performance vs encapsulation
	
	public AnimatorComp()
	{
		//this.identity = Type.RENDER;
		timer = new RollingTimer(1000);
		animations = new ArrayList<Animation>();
	}

	public void loadAssets(String filename) throws SlickException
	{
		masterSheet = new SpriteSheet(filename, 16, 16);
		masterSheet.setFilter(Image.FILTER_NEAREST);
	}

	public void addAnimation(Animation ani)
	{
		animations.add(ani);
	}
	
	public void setAnimation(String name, boolean repeat)
	{
		for(Animation ani : animations)
		{
			if(ani.getName().contentEquals(name))
				curAnimation = ani;
		}
		isRepeating = repeat;
	}

	@Override
	public void render(GameContainer gc, Graphics gr)
	{
		float x = owner.position.x;
		float y = owner.position.y;
		
		if (curAnimation == null)
			masterSheet.getSprite(0, 0).draw(x, y, scale);
		else
		{
			masterSheet.getSprite(curAnimation.getX(), curAnimation.getY()).draw(x, y, scale);
		}
	}

	@Override
	public void update(GameContainer container, int delta)
	{
		if(directionMovementSync)
		{
			if (physicsRef == null)
				physicsRef = (PhysicsComponent) owner.getComponent(PhysicsComponent.class);
			assert (physicsRef != null) : "Null Physics Reference";

			String aniName = curAnimation.getName();
			String state = aniName.substring(0, aniName.indexOf(" "));
			char dir = aniName.charAt(aniName.length() - 1);
			Vector2f vel = physicsRef.getVelocity();

			if (vel.x > 0)
				dir = 'E';
			else if (vel.x < 0)
				dir = 'W';
			else if (vel.y > 0)
				dir = 'S';
			else if (vel.y < 0)
				dir = 'N';

			if (vel.x == 0 && vel.y == 0)
				state = ("stand");
			else
				state = ("walk");

			aniName = (state + " " + dir);
			if (!aniName.contentEquals(curAnimation.getName()))
				setAnimation(aniName, true);
		}
		
		timer.update(delta);
		int frameNumber = -1;
		
		if (timer.check() && curAnimation != null)
			frameNumber = curAnimation.increment();
			
		if(frameNumber == 0 && !isRepeating)
			curAnimation = animations.get(0);
	}

	@Override
	public void receive(Message msg)
	{
		
	}
	
	public float getScale()
	{
		return scale;
	}
	
	public void setScale(float scale)
	{
		this.scale = scale;
	}
	
	/*
	*	public int getAnimationSpeed()
	*	{
	*		return 1000/(timer.getLimit());
	*	}
	*/
	
	public void setAnimationSpeed(int fps)
	{
		timer.setLimit(1000/fps);
	}
	
	public void setDirectionMovementSync(boolean sync)
	{
		this.directionMovementSync = sync;
	}

	/////Animation Class/////
	
	static public class Animation
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
}
