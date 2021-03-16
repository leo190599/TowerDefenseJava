package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import base.Game;
import world.TileFloor;
import world.TileWall;
import world.World;
import world.Node;

public class Enemy extends Entidade{

	private BufferedImage lSprite=Game.getSpritesheet().getSprite(32, 0, 16, 16);
	private BufferedImage rSprite=Game.getSpritesheet().getSprite(48, 0, 16, 16);
	private List<Node> path;
	private int life=1000;
	Node nextNode;
	
	private boolean goingLeft=true;
	
	public Enemy(int x, int y, List<Node>path) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.path=path;
		maskX=1;
		maskY=5;
		maskWidth=14;
		maskHeight=11;
	}

	private void followpath()
	{
		if(path.size()>0)
		{
			nextNode=path.get(path.size()-1);
			if(x<nextNode.getPosition().getX())
			{
				x++;
			}
			else if(x>nextNode.getPosition().getX())
			{
				x--;
			}
			else if(y<nextNode.getPosition().getY())
			{
				y++;
			}
			else if(y>nextNode.getPosition().getY())
			{
				y--;
			}
			else
			{
				path.remove(path.size()-1);
			}
		}
		else 
		{
			Game.getEntidades().remove(this);
			Game.doDamage(1);
		}
		if(life<=0)
		{
			Game.getEntidades().remove(this);
			Game.setMoney(Game.getMoney()+2);
		}
	}
	
	public void tick()
	{
		followpath();
	}
	
	public void loseLife(int damage)
	{
		life-=damage;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(rSprite, x, y, null);
		g.setColor(Color.red);
		g.fillRect(x-2, y-5, 20, 5);
		g.setColor(Color.green);
		g.fillRect(x-2, y-5, life/50, 5);
	}
}