import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class CaterpillarFactory
{
	private final static int ringNumber = 20;
	private final static int radius = 20;
	private final static double heading = 0.0;
	
	public static ICaterpillar build(Drawing d, TypeOfCaterpillar type)
	{
		switch (type)
		{
			case BLACK:
				return new ColoredCaterpillar(Color.BLACK, ringNumber, radius, d, heading);
			case GREEN:
				return new ColoredCaterpillar(Color.GREEN, ringNumber, radius, d, heading);
			case LEIA:
				return new PicturedCaterpillar(getImage("leila.png"), ringNumber, radius, d, heading);	
			case C3PO:
				return new PicturedCaterpillar(getImage("c3po.png"), ringNumber, radius, d, heading);
			case RED:
				return new ColoredCaterpillar(Color.RED, ringNumber, radius, d, heading);
			case STANDARD:
				return new Caterpillar(ringNumber, radius, d, heading);
			case VADOR:
				return new PicturedCaterpillar(getImage("darthVador.png"), ringNumber, radius, d, heading);
			case STORMTROOPER:
				return new PicturedCaterpillar(getImage("starTrooper.png"), ringNumber, radius, d, heading);
			case PINK:
				return new ColoredCaterpillar(Color.PINK, ringNumber, radius, d, heading);
			case BLUE:
				return new ColoredCaterpillar(Color.BLUE, ringNumber, radius, d, heading);
			default :
				throw new IllegalArgumentException("Unknown caterpillar !");
		}
		
	}

	private static BufferedImage getImage(String str)
	{
		try
		{
			URL url = Caterpillar.class.getResource(str);
			return ImageIO.read(url);
		}
		catch (IOException e) {
			return null;
		}
	}
}
