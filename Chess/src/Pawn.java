import java.awt.Color;

public class Pawn extends Piece
{
	Pawn(Color newColor)
	{
		super(newColor);
		// TODO Auto-generated constructor stub
	}

	
	boolean isValid(int bRow, int bCol, int aRow, int aCol)
	{
		Move movement = new Move(bRow, bCol, aRow, aCol);
		
		if(movement.getLongitude() == 0)
		{
			if(movement.getAbsLatitude() == 1 || (!hadMoved() && movement.getAbsLatitude() == 2))
			{
				if(getColor() == Color.WHITE && movement.getLatitude() > 0)
				{
					return true; 
				}
				else if(getColor() == Color.BLACK && movement.getLatitude() < 0)
				{
					return true;
				}
			}
		}
		else if(movement.getAbsLongitude() == 1)
		{
			if(movement.getAbsLatitude() == 1)
			{
				if(getColor() == Color.WHITE && movement.getLatitude() > 0)
				{
					return true; 
				}
				else if(getColor() == Color.BLACK && movement.getLatitude() < 0)
				{
					return true;
				}
			}
		}
		
		return false;
		
	}

	@Override
	public TypeOfPiece whatKindOfPiece()
	{
		return TypeOfPiece.Pawn;
	}



}
