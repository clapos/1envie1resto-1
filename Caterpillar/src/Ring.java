import java.awt.Graphics;

public class Ring
{
	protected int x;
	protected int y;
	protected int radius;
	
	public int getX()
	{
		return x;
	}
	public Ring(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
	}
	public Ring(int x, int y, int r)
	{
		super();
		this.x = x;
		this.y = y;
		this.radius = r;
	}
	
	public int getY()
	{
		return y;
	}
	
	void placeTo(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	void draw(Graphics g)
	{
		g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
	}
	
	
}
