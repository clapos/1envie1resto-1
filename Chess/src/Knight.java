import java.awt.Color;

public class Knight extends Piece
{
	Knight(Color newColor)
	{
		super(newColor);
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean isValid(int bRow, int bCol, int aRow, int aCol)
	{
		Move movement = new Move(bRow, bCol, aRow, aCol);

		if ((movement.getAbsLongitude() == 2 && movement.getAbsLatitude() == 1) || (movement.getAbsLongitude() == 1 && movement.getAbsLatitude()== 2)) 
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
		return TypeOfPiece.Knight;
	}

}
