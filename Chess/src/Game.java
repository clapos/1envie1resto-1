import java.awt.Color;
import java.util.ArrayList;

public class Game
{
	Chessboard cb;
	Player whitePlayer;
	Player blackPlayer;
	ArrayList<Piece> takenByBlack;
	ArrayList<Piece> takenByWhite;
	ChessGUI gui;
	boolean endGame = false;
	
	Game()
	{
		cb = new Chessboard();
		takenByBlack = new ArrayList<Piece>();
		takenByWhite = new ArrayList<Piece>();
	}
	
	private Player alternate(Player lastPlayer)
	{
		if(lastPlayer.getColor() == Color.WHITE)
		{
			return blackPlayer;
		}
		else
			return whitePlayer;
	}
	
	protected boolean aRound(Player currentPlayer)
	{
		//TODO : check here if it's whether a castle, a promotion or a normal move
		if(currentPlayer.getColor() == Color.WHITE)
		{
		}
		else
		{
			
		}
		return true;
	}
	
	
	
	protected int playGame()
	{
		Player currentPlayer;
		System.out.println("DEBUG : the game begins");
		while(!endGame)
		{
			System.out.println("DEBUG : White begin");
			currentPlayer = whitePlayer;
			
			currentPlayer = alternate(currentPlayer);
		}
		return 0;
	}
	
	protected void play(int bRow, int bCol, int aRow, int aCol)
	{
		cb.playRightMovement(bRow, bCol, aRow, aCol);
	}
	
	protected ArrayList<int[]> showPossibleMoves(int i, int j)
	{
		return cb.getPossibleMoves(i, j);
	}
	
	protected Piece getPieceFrom(int i, int j)
	{
		return cb.getPieceFrom(i, j);
	}
	
	public Position getPositionFrom(int x, int y)
	{
		return cb.getPositionFrom(x, y);
	}

}
