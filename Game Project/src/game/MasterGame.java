
package game;

import java.util.ArrayList;
import game.components.Entity;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Color;

public class MasterGame extends BasicGame
	{

	public ArrayList<Entity> entityList = null;
	private Color col = new Color(100,155,100);
	
		public MasterGame()
			{
				super("RPG");
			}

		@Override
		public void render(GameContainer container, Graphics g) throws SlickException
			{
				g.setColor(col);
				g.fillRect(0, 0, 800, 600);
				for(Entity entity : entityList)
					entity.render(container, g);
			}

		@Override
		public void init(GameContainer container) throws SlickException
			{
				entityList = new ArrayList<Entity>();
				entityList.add(PlayerFactory.createPlayer());
			}

		@Override
		public void update(GameContainer container, int delta) throws SlickException
			{
				for(Entity entity : entityList)
				{
					entity.update(container, delta);
				}
				
			}

		public static void main(String[] args) throws SlickException
		{
			AppGameContainer app = new AppGameContainer(new MasterGame());
			app.setDisplayMode(800, 600, false);
			app.start();
		}
	}
