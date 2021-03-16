package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import base.Game;

public class Tower extends Entidade{

	private int xTarget,yTarget;
	private boolean attacking=false;
	
	private BufferedImage sprite=Game.getSpritesheet().getSprite(0, 0, 16, 16);
	
	public Tower(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public void tick()
	{
		attacking=false;
		for(int i=0;i<Game.getEntidades().size();i++)
		{
			Entidade e = Game.getEntidades().get(i);
			if(e instanceof Enemy)
			{
				if(calculateDistance(e.getX()+8,e.getY()+8))
				{
					((Enemy)e).loseLife(4);
					xTarget=e.getX();
					yTarget=e.getY();
					attacking=true;
					break;
				}
			}
		}
	}
	
	private boolean calculateDistance(int x, int y)
	{
		if(Math.sqrt(Math.pow(this.x+8-x, 2)+Math.pow(this.y+8-y, 2))<40)
		{
			return true;
		}
		return false;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(sprite, x, y, null);
		if(attacking)
		{
			g.setColor(Color.red);
			g.drawLine(x+8,y+8,xTarget+8, yTarget+8);
		}
	}

}
