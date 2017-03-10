import java.awt.Color;
import java.awt.Graphics;

public class ColoredCaterpillar extends Caterpillar
{
	private final Color color;
	
	public ColoredCaterpillar(Color c, int number, int r, Drawing d, double heading)
	{
		super(number, r, d, heading);
		color = c;
	}

	public void draw(Graphics g)
	{
		Graphics gc = g.create();
		gc.setColor(color);
		super.draw(gc);
	}
	
	
	
}
