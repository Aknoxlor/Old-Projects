package game.components;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
//import org.newdawn.slick.geom.Vector2f;

public class SpriteComponent extends Component implements Renderable
{
	private float scale = 2;
	//private boolean hasDirection = false;
	
	private Image masterSprite = null;
	
	public void loadAssets(String filename) throws SlickException
	{
		masterSprite = new Image(filename);
		masterSprite.setFilter(Image.FILTER_NEAREST);
	}
	
	@Override
	public void render(GameContainer gc, Graphics gr)
	{
		masterSprite.draw(owner.position.x, owner.position.y, scale);
	}

	@Override
	public void update(GameContainer container, int delta)
	{
	/*
		
	*/
	}

	@Override
	public void receive(Message msg)
	{
		// TODO Auto-generated method stub

	}
	
	public float getScale()
	{
		return scale;
	}
	
	public void setScale(float scale)
	{
		this.scale = scale;
	}

}
