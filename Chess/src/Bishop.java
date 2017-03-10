import java.awt.Color;

public class Bishop extends Piece
{
	Bishop(Color newColor)
	{
		super(newColor);
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean isValid(int bRow, int bCol, int aRow, int aCol)
	{
		Move movement = new Move(bRow, bCol, aRow, aCol);

		//TODO : check that !
		if ((Math.abs(movement.getLatitude()) == Math.abs(movement.getLongitude())) && Math.abs(movement.getLatitude()) > 0) 
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
		return TypeOfPiece.Bishop;
	}


}
