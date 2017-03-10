import java.awt.Color;

public class Rook extends Piece
{
	Rook(Color newColor)
	{
		super(newColor);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	boolean isValid(int bRow, int bCol, int aRow, int aCol)
	{
		Move movement = new Move(bRow, bCol, aRow, aCol);
		if (Math.abs(movement.getLongitude()) > 0 && movement.getLatitude() == 0 || movement.getLongitude() == 0 && Math.abs(movement.getLatitude()) > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public TypeOfPiece whatKindOfPiece()
	{
		return TypeOfPiece.Rook;
	}

}
