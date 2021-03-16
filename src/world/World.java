package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import base.Game;
import base.SpriteSheet;
import entities.Enemy;
import entities.Player;
import entities.Spawner;

public class World {
	
	private static int width;
	private static int height;
	private static Tile[] tiles;
	private static Vector2i finalPoint;
	private Spawner spawner;
	
	public static int getWidth()
	{
		return width;
	}
	
	public static int getHeight()
	{
		return height;
	}
	
	public static Vector2i getFinalPoint()
	{
		return finalPoint;
	}
	
	public World(String level, SpriteSheet sprite,Player player)
	{
			
		try {
			BufferedImage imagemLevel=ImageIO.read(new File("./res/sala"+level+".png"));
			width=imagemLevel.getWidth();
			height=imagemLevel.getHeight();
			tiles=new Tile[width*height];
			int pixels[]=new int[tiles.length];
			imagemLevel.getRGB(0, 0, width, height, pixels, 0, width);
			for(int i=0;i<width;i++)
			{
				for(int j=0;j<height;j++)
				{
					switch(pixels[i+j*width])
					{
						case 0xFF000000:
							tiles[i+j*width]=new TileFloor(i*16,j*16,16,16,sprite.getSprite(208, 0, 16, 16));
						break;
						case 0xFFFFFFFF:
							tiles[i+j*width]=new TileWall(i*16,j*16,16,16,sprite.getSprite(192, 0, 16, 16));
							
						break;
						case 0xFFFF0000:
							tiles[i+j*width]=new TileFloor(i*16,j*16,16,16,sprite.getSprite(208, 0, 16, 16));
							spawner=new Spawner(i*16,j*16);
						break;
						case 0xFF0000FF:
							tiles[i+j*width]=new TileFloor(i*16,j*16,16,16,sprite.getSprite(208, 0, 16, 16));
							finalPoint=new Vector2i(i*16,j*16);
						break;
					}
				}
				
			}
			spawner.setPath(AS.findPath(this, new Vector2i(spawner.getX(),spawner.getY()),finalPoint));
			Game.getEntidades().add(spawner);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Tile getTile(int x,int y)
	{
		return tiles[(x/16)+(y/16)*width];
	}
	
	public void render(Graphics g)
	{

		for(int xx=0;xx<width;xx++)
		{
			for(int yy=0;yy<height;yy++)
			{
				tiles[xx+(yy*width)].render(g);
			}
		}
	}
	public Tile[] getTiles()
	{
		return tiles;
	}
	public static boolean isFree(int x,int y,int maskX,int maskY,int maskWidth, int maskHeight, int pretendedMovimentX,int pretendedMovimentY)
	{
		if(tiles[(x+maskX+pretendedMovimentX)/16+(y+maskY+pretendedMovimentY)/16*width] instanceof TileWall||
		   tiles[(x+maskX+maskWidth-1+pretendedMovimentX)/16+(y+maskY+pretendedMovimentY)/16*width] instanceof TileWall||
		   tiles[(x+maskX+pretendedMovimentX)/16+(y+maskY+maskHeight-1+pretendedMovimentY)/16*width] instanceof TileWall||
		   tiles[(x+maskX+maskWidth-1+pretendedMovimentX)/16+(y+maskY+maskHeight-1+pretendedMovimentY)/16*width] instanceof TileWall)
		{
			return false;
		}
		return true;
	}
	public static boolean moveDownCollider(int x,int y,int maskX,int maskY,int maskWidth, int maskHeight,int pretendMovimentY)
	{
		if(tiles[(x+maskX)/16+(y+maskY+maskHeight-1+pretendMovimentY)/16*width] instanceof TileWall||
			tiles[(x+maskX+maskWidth-1)/16+(y+maskY+maskHeight-1+pretendMovimentY)/16*width] instanceof TileWall)
			{
				return false;
			}
		return true;
	}

	public static boolean moveColliderRight(int x,int y,int maskX,int maskY,int maskWidth, int maskHeight, int pretendedMovimentX)
	{
		if(tiles[(x+maskX+maskWidth-1+pretendedMovimentX)/16+(y+maskY)/16*width] instanceof TileWall||
			tiles[(x+maskX+maskWidth-1+pretendedMovimentX)/16+(y+maskY+maskHeight-1)/16*width] instanceof TileWall)
			{
				return false;
			}
		return true;
	}
	public static boolean moveColliderLeft(int x,int y,int maskX,int maskY, int maskHeight, int pretendedMovimentX)
	{
		if(tiles[(x+maskX+pretendedMovimentX)/16+(y+maskY)/16*width] instanceof TileWall||
			tiles[(x+maskX+pretendedMovimentX)/16+(y+maskY+maskHeight-1)/16*width] instanceof TileWall)
			{
				return false;
			}
		return true;
	}
	public static boolean moveUpCollider(int x,int y,int maskX,int maskY,int maskWidth, int pretendMovimentY)
	{
		if(tiles[(x+maskX)/16+(y+maskY-pretendMovimentY)/16*width] instanceof TileWall||
			tiles[(x+maskX+maskWidth-1)/16+(y+maskY-pretendMovimentY)/16*width] instanceof TileWall)
			{
				return false;
			}
		return true;
	}
}
