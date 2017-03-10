import java.awt.Color;

public class Position
{
	private Color color;
	private int row;
	private int column;
	private Piece currentPiece = null;

	public Color getColor()
	{
		return color;
	}
	public void setColor(Color color)
	{
		this.color = color;
	}
	public int getRow()
	{
		return row;
	}
	public void setRow(int row)
	{
		this.row = row;
	}
	public int getColumn()
	{
		return column;
	}
	public void setColumn(int column)
	{
		this.column = column;
	}
	public Piece getCurrentPiece()
	{
		return currentPiece;
	}
	public void setCurrentPiece(Piece currentPiece)
	{
		this.currentPiece = currentPiece;
	}

	Position(int newRow, int newColumn)
	{
		row = newRow;
		column = newColumn;
	}

	Position(int newRow, int newColumn, Piece newPiece)
	{
		row = newRow;
		column = newColumn;
		currentPiece = newPiece;
	}

	Piece movePieceTo(Position newPosition)
	{
		Piece returnPiece = null;
		if(getCurrentPiece() != null)
		{
			if(getCurrentPiece().isValid(this.getRow(), this.getColumn(), newPosition.getRow(), newPosition.getColumn()))
			{
				if(newPosition.getCurrentPiece() == null)
				{
					getCurrentPiece().setMoved();
					newPosition.setCurrentPiece(getCurrentPiece());
					setCurrentPiece(null);
					return returnPiece;

				}
				// TODO : remove taken piece from chessboard
				else if(newPosition.getCurrentPiece().getColor() != getCurrentPiece().getColor())
				{
					getCurrentPiece().setMoved();
					newPosition.setCurrentPiece(getCurrentPiece());
					returnPiece = getCurrentPiece();
					setCurrentPiece(null);
					return returnPiece;
				}
				else
				{
					System.out.println("DEBUG : same color piece already here...");
				}
			}

			System.out.println("DEBUG : Invalid movement");
			
		}
		
		System.out.println("DEBUG : no piece to move !");


		return returnPiece;
	}
	


}
