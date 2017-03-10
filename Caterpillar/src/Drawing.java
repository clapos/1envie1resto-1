import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Drawing extends JPanel
{
	private static final long serialVersionUID = 5635559890826468215L;
	ArrayList<ICaterpillar> swarm;
	
	public Drawing()
	{
		super();
		swarm = new ArrayList<ICaterpillar>();
	}

	public void setBug(ICaterpillar bug)
	{
		swarm.add(bug);
	}
	
	public void pause (int duration)
	{
		try
		{
			Thread.sleep(duration);
		}
		catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(ICaterpillar c : swarm)
			c.draw(g);
	}
	
	
	
}
