
public class Move
{
	private int latitude;
	private int longitude;
		
	Move(int bRow, int bCol, int aRow, int aCol)
	{
		 longitude = aCol - bCol;
		 latitude = aRow - bRow;
	}
	
	Move(Position before, Position after)
	{
		 longitude = after.getColumn() - before.getColumn();
		 latitude = after.getRow() - before.getRow();
	}
	
	public int getLatitude()
	{
		return latitude;
	}

	public int getLongitude()
	{
		return longitude;
	}
	
	public int getAbsLatitude()
	{
		return Math.abs(latitude);
	}

	public int getAbsLongitude()
	{
		return Math.abs(longitude);
	}
	
	
}
