package base;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import entities.Entidade;
import entities.Player;
import entities.Spawner;
import entities.TowerController;
import menus.Menu;
import world.World;

public class Game extends Canvas implements Runnable,KeyListener,MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread thread;
	public final static int width=240;
	public final static int height = 240;
	public final static int scale = 4;
	private boolean isRunning=false;
	private Graphics g;
	private BufferStrategy bs;
	private BufferedImage baseI;
	private static List<Entidade> entidades;
	private static Player player;
	private static World world;
	private static SpriteSheet spriteSheet;
	private static Random rand;
	private Menu menu;
	private static int life=10;
	private static int money=100;
	private static TowerController tc;
	
	public Game()
	{
		rand=new Random();
		spriteSheet=new SpriteSheet();
		menu=new Menu();
		baseI=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.setPreferredSize(new Dimension(width*scale,height*scale));
		this.addKeyListener(this);
		this.addMouseListener(this);
		entidades=new ArrayList<Entidade>();
		player=new Player(0,0);
		world=new World("0",spriteSheet,player);
		tc=new TowerController();
	}
	
	public static void restartGame()
	{
		entidades=new ArrayList<Entidade>();
		player=new Player(0,0);
		world=new World("0",spriteSheet,player);
		life=10;
		money=100;
	}
	
	public static void doDamage(int damage)
	{
		life-=damage;
	}
	
	public static Random getRand()
	{
		return rand;
	}
	
	public static World getWorld()
	{
		return world;
	}
	
	public static Player getPlayer()
	{
		return player;
	}
	
	public static int getLife()
	{
		return life;
	}
	
	public static int getMoney()
	{
		return money;
	}
	
	public static void setMoney(int nMoney)
	{
		money=nMoney;
	}
	
	public void tick()
	{
		for(int i=0;i<entidades.size();i++)
		{
			entidades.get(i).tick();
		}
		if(life<=0)
		{
			System.exit(1);
		}
	}
	
	public void render()
	{
		g=baseI.getGraphics();
		g.setColor(Color.black);
		//g.fillRect(0, 0,GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth(),
				//GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight());
		//g.setColor(Color.blue);
		g.fillRect(0, 0, width, height);
		world.render(g);
		for(int i=0;i<entidades.size();i++)
		{
			entidades.get(i).render(g);
		}
		g=bs.getDrawGraphics();
		g.drawImage(baseI, 0, 0,width*scale,height*scale,null);
		menu.render(g);
		bs.show();
	}
	

	public void start()
	{
		isRunning=true;
		thread =new Thread(this);
		thread.start();
	}
	public void stop()
	{
		isRunning=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static List<Entidade> getEntidades()
	{
		return entidades;
	}
	
	public static SpriteSheet getSpritesheet()
	{
		return spriteSheet;
	}
	
	@Override
	public void run() {
		this.createBufferStrategy(3);
		bs=this.getBufferStrategy();
		//int frames=0;
		
		//double lasTime=System.currentTimeMillis();
		//double curTime;
		
		double lastTime=System.nanoTime();
		final int fps=60;
		final double nsTime=1000000000/fps;
		double currentTime;
		double deltaTime=0;
		this.requestFocus();
		while(isRunning)
		{
			//System.out.println(deltaTime);
			//curTime=System.currentTimeMillis();
			
			currentTime=System.nanoTime();
			deltaTime+=(currentTime-lastTime);
			lastTime=currentTime;
			if(deltaTime>=nsTime)
			{	
				tick();
				render();
				deltaTime=0;
				//frames++;
			}
			
			//if((curTime-lasTime)>=1000)
			//{
				//lasTime=curTime;
				//System.out.println(frames);
				//frames=0;
			//}
		}
		stop();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {

	}
	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int xx=e.getX()/scale;
		int yy=e.getY()/scale;
		tc.addTower(xx, yy);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
