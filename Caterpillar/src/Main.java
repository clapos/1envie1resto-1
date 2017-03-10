import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		ArrayList<ICaterpillar> swarm = new ArrayList<ICaterpillar>();
		
		
		// TODO Auto-generated method stub
		JFrame j = new JFrame();
		j.setTitle("Caterpillars");
		j.setSize(512, 512);
		j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Drawing d = new Drawing();
		d.setLayout(null);
		j.getContentPane().add(d);
		j.setVisible(true);
		
	
		swarm.add(CaterpillarFactory.build(d, TypeOfCaterpillar.LEIA));
		swarm.add(CaterpillarFactory.build(d, TypeOfCaterpillar.C3PO));
		swarm.add(CaterpillarFactory.build(d, TypeOfCaterpillar.PINK));
		swarm.add(CaterpillarFactory.build(d, TypeOfCaterpillar.STORMTROOPER));
		swarm.add(CaterpillarFactory.build(d, TypeOfCaterpillar.VADOR));
		swarm.add(CaterpillarFactory.build(d, TypeOfCaterpillar.BLUE));
		
		//for(int i = 0; i <= 1000; i++)
			//swarm.add(CaterpillarFactory.build(d, TypeOfCaterpillar.GREEN));
		
		for(ICaterpillar c : swarm)
			d.setBug(c);
		
		while(true)
		{
			for(ICaterpillar c : swarm)
				c.move();
			d.repaint();
			d.pause(50);
		}
	}

}
