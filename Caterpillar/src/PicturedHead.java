import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PicturedHead extends Head
{
	private final BufferedImage buffer;
	
	public PicturedHead(int x, int y, double heading, BufferedImage img)
	{
		super(x, y, img.getWidth()/2, heading);
		this.buffer = img;
		// TODO Auto-generated constructor stub
	}

	@Override
	void draw(Graphics g)
	{
		g.drawImage(buffer, x - radius, y - radius, x + radius,  y + radius, 0, 0, 63, 63, null);
	}
	
	

}
