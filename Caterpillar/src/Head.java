import java.awt.Graphics;

public class Head extends Ring
{
	double heading;

	public Head(int x, int y, int r, double heading)
	{
		super(x, y, r);
		this.heading = heading;
	}

	public Head(int x, int y)
	{
		super(x, y);
	}
	
	private double normalize(double heading)
	{
		double tmp = Math.abs(heading) % 360;
		if( heading < 0)
		{
			if(tmp > 180)
			{
				tmp = 360 - tmp;
			}
			else
			{
				tmp = - tmp;
			}
		}
		else
		{
			if(tmp > 180)
				tmp = -(360 - tmp);
		}
		
		return tmp;
	}
	
	Boolean headingOK(int xMax, int yMax)
	{
		int xTmp = (int) (x + radius*Math.cos(Math.PI*heading/180));
		int yTmp = (int) (y + radius*Math.sin(Math.PI*heading/180));
		return (xTmp <= xMax-radius && yTmp <= yMax-radius && xTmp > radius && yTmp > radius);
	}
	
	void modifyHeading(double deltaHeading)
	{
		heading += deltaHeading;
		heading = normalize(heading);
	}
	
	void moveAccordingToHeading()
	{
		x += (int) (radius * Math.cos(Math.PI * heading/180)); 
		y += (int) (radius * Math.sin(Math.PI * heading/180));
	}
	
	void draw(Graphics g)
	{
		g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
	}
	
	
}
