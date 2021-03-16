package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import base.Game;
import world.Node;
import world.Vector2i;
import world.World;

public class Spawner extends Entidade{

	private final int timer=60;
	private int curTime=45;
	private List<Node> path;
	
	public Spawner(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public void tick()
	{
		curTime++;
		if(curTime>=timer && path !=null)
		{
			List<Node> path2=new ArrayList<Node>();
			path2.addAll(path);
			Game.getEntidades().add(new Enemy(x,y,path2));
			curTime=Game.getRand().nextInt(20)+20;
		}
	}
	public void render(Graphics g)
	{
		//super.render(g);
	}
	
	public void setPath(List<Node> lista)
	{
		this.path=lista;
	}
}
