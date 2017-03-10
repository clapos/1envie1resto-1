import java.awt.Color;

public class King extends Queen
{
	King(Color newColor)
	{
		super(newColor);
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean isValid(int bRow, int bCol, int aRow, int aCol)
	{
		Move movement = new Move(bRow, bCol, aRow, aCol);
		if(Math.abs(movement.getLatitude()) == 1 || Math.abs(movement.getLongitude()) == 1)
		{
			return super.isValid(bCol, bRow, aCol, aRow);
		}
		else
			return false;
	}
	public TypeOfPiece whatKindOfPiece()
	{
		return TypeOfPiece.King;
	}

}
