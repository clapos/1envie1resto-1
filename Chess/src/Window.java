import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Window
{

	private JFrame mainFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Window window = new Window();
					window.mainFrame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		mainFrame = new JFrame();
		mainFrame.setTitle("Chess");
		mainFrame.setBounds(100, 100, 610, 471);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel utilityPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) utilityPanel.getLayout();
		flowLayout.setVgap(50);
		flowLayout.setHgap(10);
		mainFrame.getContentPane().add(utilityPanel, BorderLayout.SOUTH);
		
		JLabel labelPlayer1 = new JLabel("Player 1");
		labelPlayer1.setToolTipText("Name of the first player\r\n");
		utilityPanel.add(labelPlayer1);
		
		JLabel labelPlayer2 = new JLabel("Player 2");
		labelPlayer2.setToolTipText("Name of the second player");
		utilityPanel.add(labelPlayer2);
		
		ChessGrid grid = new ChessGrid();
		mainFrame.getContentPane().add(grid, BorderLayout.CENTER);
	
	}

}
