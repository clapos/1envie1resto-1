import java.awt.Graphics;
import java.util.ArrayList;

public class Caterpillar implements ICaterpillar
{
	private Head head; 
	ArrayList<Ring> body; 
	Drawing draw;

	public Caterpillar(int number, int r, Drawing d, double heading)
	{
		draw = d;
		int x0 = draw.getWidth()/2;
		int y0 = draw.getHeight()/2;
		body = new ArrayList<Ring>();

		//Head
		head = new Head(x0, y0, r, heading);
		for(int i = 1; i <= number; i++)
		{
			body.add(new Ring(x0 - i*r, y0, r));
		}
	}

	public void setHead(Head head)
	{
		this.head = head;
	}



	/* (non-Javadoc)
	 * @see ICaterpillar#move()
	 */
	@Override
	public void move()
	{
		if(body.size() > 0)
		{
			for(int i = body.size() - 1; i > 0; i--)
			{
				body.get(i).placeTo(body.get(i-1).getX(), body.get(i-1).getY());
			}
		}
		body.get(0).placeTo(head.getX(), head.getY());
		draw.repaint();
		
		head.modifyHeading(-30 + Math.random()*60);
		while(!head.headingOK(draw.getWidth(), draw.getHeight()))
		{
			head.modifyHeading(10.0);
		}
		head.moveAccordingToHeading();
	}

	/* (non-Javadoc)
	 * @see ICaterpillar#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g)
	{
		for (Ring r : body)
			r.draw(g);
		head.draw(g);
	}
}
