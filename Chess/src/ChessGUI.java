import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ChessGUI extends Game
{
	// Graphical
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private Image[][] chessPieceImages = new Image[2][6];
	private JPanel chessBoard;
	private final JLabel message = new JLabel("Chess Champ is ready to play!");
	private static final String COLS = "ABCDEFGH";
	public static final int QUEEN = 0, KING = 1, ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
	public static final int[] STARTING_ROW =
		{ ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK };
	public static final int BLACK = 0, WHITE = 1;

	// Logical
	private static boolean hasBeenClicked = false;
	private static int[] lastClickedCoordinates = {-1,-1};
	private static ArrayList<int[]> moves;

	ChessGUI()
	{
		initializeGui();
	}

	public final void initializeGui()
	{
		// create the images for the chess pieces
		createImages();

		// set up the main GUI
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
		JToolBar tools = new JToolBar();
		tools.setFloatable(false);
		gui.add(tools, BorderLayout.PAGE_START);
		Action newGameAction = new AbstractAction("New")
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setupNewGame();
			}
		};
		tools.add(newGameAction);
		tools.add(new JButton("Save")); // TODO - add functionality!
		tools.add(new JButton("Restore")); // TODO - add functionality!
		tools.addSeparator();
		tools.add(new JButton("Resign")); // TODO - add functionality!
		tools.addSeparator();
		tools.add(message);

		gui.add(new JLabel("?"), BorderLayout.LINE_START);

		chessBoard = new JPanel(new GridLayout(0, 9))
		{

			/**
			 * Override the preferred size to return the largest it can, in a
			 * square shape. Must (must, must) be added to a GridBagLayout as
			 * the only component (it uses the parent as a guide to size) with
			 * no GridBagConstaint (so it is centered).
			 */
			@Override
			public final Dimension getPreferredSize()
			{
				Dimension d = super.getPreferredSize();
				Dimension prefSize = null;
				Component c = getParent();
				if (c == null)
				{
					prefSize = new Dimension((int) d.getWidth(), (int) d.getHeight());
				}
				else if (c != null && c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight())
				{
					prefSize = c.getSize();
				}
				else
				{
					prefSize = d;
				}
				int w = (int) prefSize.getWidth();
				int h = (int) prefSize.getHeight();
				// the smaller of the two sizes
				int s = (w > h ? h : w);
				return new Dimension(s, s);
			}
		};
		chessBoard.setBorder(new CompoundBorder(new EmptyBorder(8, 8, 8, 8), new LineBorder(Color.BLACK)));
		// Set the BG to be ochre
		Color ochre = new Color(204, 119, 34);
		chessBoard.setBackground(ochre);
		JPanel boardConstrain = new JPanel(new GridBagLayout());
		boardConstrain.setBackground(ochre);
		boardConstrain.add(chessBoard);
		gui.add(boardConstrain);

		// create the chess board squares
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int ii = 0; ii < chessBoardSquares.length; ii++)
		{
			for (int jj = 0; jj < chessBoardSquares[ii].length; jj++)
			{
				int x = ii;
				int y = jj;

				JButton b = new JButton();

				// Adding an action listener

				b.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						// TODO : enter here the method
						if (!hasBeenClicked)
						{
							if(chessBoardSquares[y][x].getIcon() != null)
							{
								moves = printPossibleMoves(x, y);
								if(!moves.isEmpty())
								{
									hasBeenClicked = true;
									lastClickedCoordinates[0] = x;
									lastClickedCoordinates[1] = y;
								}
							}
						}
						else
						{
							if(isPresent(x, y))
							{
								hasBeenClicked = false;
								movePieceTo(lastClickedCoordinates[0], lastClickedCoordinates[1], x, y);
								moves.clear();
							}
							//TODO : redraw possible moves if needed...
							else
							{
								hasBeenClicked = false;
								moves.clear();
								drawFromChessboard();
							}
						}

					}

				});

				b.setMargin(buttonMargin);
				// our chess pieces are 64x64 px in size, so we'll
				// 'fill this in' using a transparent icon..
				ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
				b.setIcon(icon);
				if ((jj % 2 == 1 && ii % 2 == 1)
						// ) {
						|| (jj % 2 == 0 && ii % 2 == 0))
				{
					b.setBackground(Color.WHITE);
				}
				else
				{
					b.setBackground(Color.BLACK);
				}
				chessBoardSquares[jj][ii] = b;
			}
		}

		/*
		 * fill the chess board
		 */
		chessBoard.add(new JLabel(""));
		// fill the top row
		for (int ii = 0; ii < 8; ii++)
		{
			chessBoard.add(new JLabel(COLS.substring(ii, ii + 1), SwingConstants.CENTER));
		}
		// fill the black non-pawn piece row
		for (int ii = 0; ii < 8; ii++)
		{
			for (int jj = 0; jj < 8; jj++)
			{
				switch (jj)
				{
				case 0:
					chessBoard.add(new JLabel("" + (9 - (ii + 1)), SwingConstants.CENTER));
				default:
					chessBoard.add(chessBoardSquares[jj][ii]);
				}
			}
		}
	}

	public final JComponent getGui()
	{
		return gui;
	}

	private final void createImages()
	{
		try
		{
			// URL url = new URL("http://i.stack.imgur.com/memI0.png");
			URL url = this.getClass().getResource("/Ressources/memI0.png");
			BufferedImage bi = ImageIO.read(url);
			for (int ii = 0; ii < 2; ii++)
			{
				for (int jj = 0; jj < 6; jj++)
				{
					chessPieceImages[ii][jj] = bi.getSubimage(jj * 64, ii * 64, 64, 64);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Initializes the icons of the initial chess board piece places
	 */
	private final void setupNewGame()
	{
		message.setText("Make your move!");
		// set up the black pieces
		//		for (int ii = 0; ii < STARTING_ROW.length; ii++)
		//		{
		//			chessBoardSquares[ii][0].setIcon(new ImageIcon(chessPieceImages[BLACK][STARTING_ROW[ii]]));
		//		}
		//		for (int ii = 0; ii < STARTING_ROW.length; ii++)
		//		{
		//			chessBoardSquares[ii][1].setIcon(new ImageIcon(chessPieceImages[BLACK][PAWN]));
		//		}
		//		// set up the white pieces
		//		for (int ii = 0; ii < STARTING_ROW.length; ii++)
		//		{
		//			chessBoardSquares[ii][6].setIcon(new ImageIcon(chessPieceImages[WHITE][PAWN]));
		//		}
		//		for (int ii = 0; ii < STARTING_ROW.length; ii++)
		//		{
		//			chessBoardSquares[ii][7].setIcon(new ImageIcon(chessPieceImages[WHITE][STARTING_ROW[ii]]));
		//		}
		drawFromChessboard();
	}

	private ArrayList<int[]> printPossibleMoves(int i, int j)
	{
		//JOptionPane.showMessageDialog(null, "Je suis en " + i + " " + j);
		ArrayList<int[]> possibleMoves = showPossibleMoves(i, j);
		for (int[] pos : possibleMoves)
		{
			// Col - Row...
			chessBoardSquares[pos[1]][pos[0]].setBackground(Color.GREEN);
		}
		return possibleMoves;
	}

	private void movePieceTo(int bRow, int bCol, int aRow, int aCol)
	{
		play(bRow, bCol, aRow, aCol);
		drawFromChessboard();

	}

	private boolean isPresent(int x, int y)
	{
		for(int[] p : moves)
		{
			if(x == p[0] && y == p[1])
				return true;
		}

		return false;
	}

	private void drawFromChessboard()
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				chessBoardSquares[j][i].setBackground(getPositionFrom(i,j).getColor());
				Piece p = getPieceFrom(i, j);
				if(p != null)
				{
					if(p.getColor() == Color.WHITE)
					{
						switch (p.whatKindOfPiece())
						{
						case Pawn :
							chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[WHITE][PAWN]));
							break;
						case Knight :
							chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[WHITE][KNIGHT]));
							break;
						case Bishop :
							chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[WHITE][BISHOP]));
							break;
						case Rook :
							chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[WHITE][ROOK]));
							break;
						case Queen :
							chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[WHITE][QUEEN]));
							break;
						case King :
							chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[WHITE][KING]));
							break;

						}
					}
					else
					{
						switch (p.whatKindOfPiece())
						{
						case Pawn :
							chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[BLACK][PAWN]));
							break;
						case Knight :
							chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[BLACK][KNIGHT]));
							break;
						case Bishop :
							chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[BLACK][BISHOP]));
							break;
						case Rook :
							chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[BLACK][ROOK]));
							break;
						case Queen :
							chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[BLACK][QUEEN]));
							break;
						case King :
							chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[BLACK][KING]));
							break;

						}
					}

				}
				else
					chessBoardSquares[j][i].setIcon(null);
			}
		}
	}

	public static void main(String[] args)
	{
		Runnable r = new Runnable()
		{
			@Override
			public void run()
			{
				ChessGUI cg = new ChessGUI();

				JFrame f = new JFrame("Chess");
				f.add(cg.getGui());
				// Ensures JVM closes after frame(s) closed and
				// all non-daemon threads are finished
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				// See http://stackoverflow.com/a/7143398/418556 for demo.
				f.setLocationByPlatform(true);

				// ensures the frame is the minimum size it needs to be
				// in order display the components within it
				f.pack();
				// ensures the minimum size is enforced.
				f.setMinimumSize(f.getSize());
				f.setVisible(true);
			}
		};
		// Swing GUIs should be created and updated on the EDT
		// http://docs.oracle.com/javase/tutorial/uiswing/concurrency
		SwingUtilities.invokeLater(r);
	}
}