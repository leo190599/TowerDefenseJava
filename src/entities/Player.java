package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import base.Game;
import world.World;

public class Player extends Entidade{
	
	private final BufferedImage[] leftSprite= {Game.getSpritesheet().getSprite(0, 0, 16, 16),Game.getSpritesheet().getSprite(0, 16, 16, 16)};
	private final BufferedImage[] rightSprite= {Game.getSpritesheet().getSprite(16, 0, 16, 16),Game.getSpritesheet().getSprite(16, 16, 16, 16)};
	private int curFrameIndex=0;
	private int curFrameTime=0;
	private final int maxFrameTime=15;
	private boolean mRight=false;
	private boolean mLeft=false;
	private boolean lookingLeft=false;

	public Player(int x, int y) {
		super(x, y);
		gravitySpeed=4;
		speed=2;
		maskY=2;
		maskHeight=14;
		maskX=4;
		maskWidth=8;
	}

	private void initializeFrames(int iFSize,int dFSize,int uFSize,int rFSize,int lFSize)
	{
		
	}

	public void tick()
	{
		
	}
	
	
	public void setMRight(boolean mRight)
	{
		this.mRight=mRight;
	}
	public void setMLeft(boolean mLeft)
	{
		this.mLeft=mLeft;
	}
	
	private void checkCollisionEntities()
	{
		Rectangle thisColMask=new Rectangle(x+maskX,y+maskY,maskWidth,maskHeight);
		for(int i=0;i<Game.getEntidades().size();i++)
		{
			if(Game.getEntidades().get(i).equals(this))
				continue;
			Entidade otherEntity=Game.getEntidades().get(i);
			Rectangle otherColMask=new Rectangle(otherEntity.getX()+otherEntity.getMaskX(),
					otherEntity.getY()+otherEntity.getMaskY(),
					otherEntity.getMaskWidth(),
					otherEntity.getMaskHeight());
			if(thisColMask.intersects(otherColMask))
			{
				
			}
		}
	}
	
	public void render(Graphics g)
	{
		//super.render(g);
		if(curFrameTime>=maxFrameTime)
		{
			curFrameIndex++;
			curFrameTime=0;
			if(curFrameIndex>=leftSprite.length)
			{
				curFrameIndex=0;
			}
		}
		curFrameTime++;
	}
	public void setPosition(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	

}
