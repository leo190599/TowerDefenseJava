package world;

import java.util.List;

public class Node {
	
	protected double routeCost;
	protected double estimatedCost;
	protected double finalCost;
	protected Node father;
	protected Vector2i position;
	
	public Node(Vector2i position, double routeCost, Node father)
	{
		this.position=position;
		this.routeCost=routeCost;
		this.father=father;
		estimatedCost=position.getDistance(World.getFinalPoint());
		finalCost=estimatedCost+routeCost;
	}
	
	public Vector2i getPosition()
	{
		return position;
	}
	
	public double getRouteCost()
	{
		return routeCost;
	}
	
	public double getfinalCost()
	{
		return finalCost;
	}
	
	public double getEstimatedCost()
	{
		return estimatedCost;
	}
	
	
	public Node getNode(List<Node> list)
	{
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getPosition().equals(position))
			{
				return list.get(i);
			}
		}
		return null;
	}
	
	public boolean inList(List<Node> list)
	{
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getPosition().equals(position))
			{
				return true;
			}
		}
		return false;
	}
}
