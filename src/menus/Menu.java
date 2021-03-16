package menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import base.Game;

public class Menu {
	
	private static BufferedImage hearths=Game.getSpritesheet().getSprite(64, 0, 16, 16);
	
	public void render(Graphics g)
	{
		for(int i=0;i<Game.getLife();i++)
		{
			g.drawImage(hearths, 10+i*34, 10,32,32, null);
		}
		g.setColor(Color.yellow);
		g.setFont(new Font("arial",Font.PLAIN,24));
		g.drawString("$: "+Game.getMoney(), Game.width*Game.scale-100, 40);
	}
}
