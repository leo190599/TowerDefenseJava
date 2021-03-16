package world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AS {
	
	private static Comparator compNode=new Comparator()
			{
				@Override
				public int compare(Object o1, Object o2) {
					// TODO Auto-generated method stub
					if(((Node)o1).finalCost<((Node)o2).finalCost)
					{
						return-1;
					}
					else if(((Node)o1).finalCost>((Node)o2).finalCost)
					{
						return 1;
					}
					return 0;
				}
		
			};
	
	public static List<Node> findPath(World world, Vector2i start, Vector2i end)
	{
		List<Node> openList=new ArrayList<Node>();
		List<Node> closedList=new ArrayList<Node>();
		Node curNode=new Node(start, 0, null);
		openList.add(curNode);
		while(openList.size()>0)
		{
			closedList.add(curNode);
			if(curNode.position.equals(end))
			{
				List<Node> path=new ArrayList<Node>();
				while(curNode.father!=null)
				{
					System.out.println(curNode.getPosition().getX()+"                        "+curNode.getPosition().getY());
					path.add(curNode);
					curNode=curNode.father;
				}
				return path;
			}
			for(int i=0;i<9;i++)
			{
				int nextX=i%3-1;
				int nextY=i/3-1;
				if(curNode.getPosition().getX()/16+nextX<0||curNode.getPosition().getX()/16+nextX>=World.getWidth()
					||curNode.getPosition().getY()/16+nextY<0||curNode.getPosition().getY()/16+nextY>=World.getHeight())
				{
					continue;
				}
				Vector2i nextVector=new Vector2i(curNode.getPosition().getX()+nextX*16,curNode.getPosition().getY()+nextY*16);
				Node nextNode=new Node(nextVector,curNode.getRouteCost()+nextVector.getDistance(curNode.getPosition()),curNode);
				if(World.getTile(nextNode.getPosition().getX(), nextNode.getPosition().getY())instanceof TileWall||nextNode.inList(closedList)
						||nextY==-1 && nextX==-1 
						||nextY==-1 && nextX==1
						||nextY==1 && nextX==-1
						||nextY==1 && nextX==1)
				{
					continue;
				}
				openList.add(nextNode);
				
			}
			openList.remove(curNode);
			Collections.sort(openList, compNode);
			curNode=openList.get(0);
		}
		return null;
	}
}
