import java.awt.Color;

// A chess piece
public abstract class Piece
{
	private boolean initialPosition = true;
	private Color color;
	int value;

	abstract boolean isValid(int bRow, int bCol, int aRow, int aCol);
	abstract public TypeOfPiece whatKindOfPiece();

	
	public void setMoved()
	{
		initialPosition = false;
	}
	
	public boolean hadMoved()
	{
		return !initialPosition;
	}

	public Color getColor()
	{
		return color;
	}
	
	Piece(Color newColor)
	{
		color = newColor;
	}
	

}
