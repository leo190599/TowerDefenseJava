package entities;

import base.Game;

public class TowerController {
	private boolean canPutTower=true;
	
	public void addTower(int x, int y)
	{
		x=x/16*16;
		y=y/16*16;
		for(int i=0;i<Game.getEntidades().size();i++)
		{
			Entidade entidade = Game.getEntidades().get(i);
			if(entidade instanceof Tower)
			{
				if(entidade.getX()==x && entidade.getY()==y)
				{
					canPutTower=false;
					System.out.println("Já existe uma torre aqui");
				}
			}
		}
		if(!Game.getWorld().isFree(x, y,0, 0, 16, 16, 0, 0) && canPutTower && Game.getMoney()>=5)
		{
			Game.getEntidades().add(new Tower(x,y));
			Game.setMoney(Game.getMoney()-5);
		}
		canPutTower=true;
	}
}
