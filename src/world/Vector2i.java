package world;

public class Vector2i {
	
	private int x;
	private int y;
	
	public Vector2i(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean equals(Vector2i other)
	{
		if(other.getX()==this.x && other.getY()==this.y)
		{
			return true;
		}
		return false;
	}
	
	public double getDistance(Vector2i other)
	{
		//System.out.println(y-other.getY());
		return Math.sqrt(Math.pow(x-other.getX(),2)+Math.pow(y-other.getY(),2));
	}
	
}
