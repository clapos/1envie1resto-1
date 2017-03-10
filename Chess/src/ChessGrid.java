import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class ChessGrid extends JPanel 
{

	public ChessGrid() 
	{
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		int i = 0;
		for (int row = 0; row < 8; row++) 
		{
			for (int col = 0; col < 8; col++) 
			{
				gbc.gridx = col;
				gbc.gridy = row;

				CellPane cellPane = new CellPane();
				Border border = null;
				if (row < 7)
				{
					if (col < 7)
					{
						border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
					} 
					else 
					{
						border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
					}
				} 
				else 
				{
					if (col < 7)
					{
						border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
					} 
					else
					{
						border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
					}
				}
				cellPane.setBorder(border);
				if(i%2 == 0)
				{
					cellPane.setBackground(Color.BLACK);
				}
				else
					cellPane.setBackground(Color.WHITE);
				add(cellPane, gbc);
				i++;
			}
			i++;
		}
	}
}
