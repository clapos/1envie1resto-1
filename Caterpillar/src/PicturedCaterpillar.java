import java.awt.image.BufferedImage;

public class PicturedCaterpillar extends Caterpillar
{
	public PicturedCaterpillar(BufferedImage buffer, int number, int r, Drawing d, double heading)
	{
		super(number, r, d, heading);
		setHead(new PicturedHead(d.getWidth()/2, d.getHeight()/2, heading, buffer));
	}
}
