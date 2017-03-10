import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class Chessboard
{
	ArrayList<Position> squareList;
	public Chessboard()
	{
		squareList = new ArrayList<Position>();
		for(int i = 0; i <= 7; i++)
		{
			for (int j = 0; j <= 7; j++)
			{
				Position toBeAdded = new Position(i, j);
				if (i % 2 == j % 2)
				{
					toBeAdded.setColor(Color.WHITE);
				}
				else
					toBeAdded.setColor(Color.BLACK);

				squareList.add(toBeAdded);
			}
		}
		fillIn();
	}

	private void fillIn()
	{
		//WHITE major pieces
		squareList.get(0).setCurrentPiece(new Rook(Color.WHITE));
		squareList.get(1).setCurrentPiece(new Knight(Color.WHITE));
		squareList.get(2).setCurrentPiece(new Bishop(Color.WHITE));
		squareList.get(3).setCurrentPiece(new Queen(Color.WHITE));
		squareList.get(4).setCurrentPiece(new King(Color.WHITE));
		squareList.get(5).setCurrentPiece(new Bishop(Color.WHITE));
		squareList.get(6).setCurrentPiece(new Knight(Color.WHITE));
		squareList.get(7).setCurrentPiece(new Rook(Color.WHITE));

		//WHITE paws
		squareList.get(8).setCurrentPiece(new Pawn(Color.WHITE));
		squareList.get(9).setCurrentPiece(new Pawn(Color.WHITE));
		squareList.get(10).setCurrentPiece(new Pawn(Color.WHITE));
		squareList.get(11).setCurrentPiece(new Pawn(Color.WHITE));
		squareList.get(12).setCurrentPiece(new Pawn(Color.WHITE));
		squareList.get(13).setCurrentPiece(new Pawn(Color.WHITE));
		squareList.get(14).setCurrentPiece(new Pawn(Color.WHITE));
		squareList.get(15).setCurrentPiece(new Pawn(Color.WHITE));

		//BLACK paws
		squareList.get(48).setCurrentPiece(new Pawn(Color.BLACK));
		squareList.get(49).setCurrentPiece(new Pawn(Color.BLACK));
		squareList.get(50).setCurrentPiece(new Pawn(Color.BLACK));
		squareList.get(51).setCurrentPiece(new Pawn(Color.BLACK));
		squareList.get(52).setCurrentPiece(new Pawn(Color.BLACK));
		squareList.get(53).setCurrentPiece(new Pawn(Color.BLACK));
		squareList.get(54).setCurrentPiece(new Pawn(Color.BLACK));
		squareList.get(55).setCurrentPiece(new Pawn(Color.BLACK));

		//BLACK major pieces
		squareList.get(56).setCurrentPiece(new Rook(Color.BLACK));
		squareList.get(57).setCurrentPiece(new Knight(Color.BLACK));
		squareList.get(58).setCurrentPiece(new Bishop(Color.BLACK));
		squareList.get(59).setCurrentPiece(new Queen(Color.BLACK));
		squareList.get(60).setCurrentPiece(new King(Color.BLACK));
		squareList.get(61).setCurrentPiece(new Bishop(Color.BLACK));
		squareList.get(62).setCurrentPiece(new Knight(Color.BLACK));
		squareList.get(63).setCurrentPiece(new Rook(Color.BLACK));
	}

	private ArrayList<Position> listOfPositionBetween(int bRow, int bCol, int aRow, int aCol)
	{
		ArrayList<Position> returnArray = new ArrayList<Position>();
		Move movement = new Move(bRow, bCol, aRow, aCol);
		if(Math.max(movement.getAbsLatitude(), movement.getAbsLongitude()) > 1)
		{
			int index=8*bRow+bCol;

			if(movement.getLatitude() == 0)
			{
				for (int i=1; i<movement.getAbsLongitude(); i++)
				{
					index = index + (int) Math.signum(movement.getLongitude());
					returnArray.add(squareList.get(index));
				}
			}	

			else if(movement.getLongitude() == 0)
			{
				for (int i=1; i<movement.getAbsLatitude(); i++)
				{
					index = index + (int) Math.signum(movement.getLatitude())*8;
					returnArray.add(squareList.get(index));
				}
			}
			else if(movement.getAbsLatitude() == movement.getAbsLongitude())
			{
				for (int i=1; i<movement.getAbsLongitude(); i++)
				{
					index = index + (int) Math.signum(movement.getLatitude())*8 + (int) Math.signum(movement.getLongitude());
					returnArray.add(squareList.get(index));
				}
			}
		}

		return returnArray;
	}

	private Piece normalPlay(int bRow, int bCol, int aRow, int aCol)
	{
		//Get two positions
		Position before = squareList.get(8*bRow+bCol);
		Position after = squareList.get(8*aRow+aCol);

		if(onChessboardValidMove(before, after))
			return before.movePieceTo(after);
		else
			return null;
	}

	//TODO : King sould not be in "Chess" and should go through a "chess" situation
	private boolean queenSideCastle(Color playerColor)
	{
		if(playerColor == Color.WHITE)
		{
			if(squareList.get(4).getCurrentPiece() != null && squareList.get(0).getCurrentPiece() != null)
			{
				Piece king = squareList.get(4).getCurrentPiece();
				Piece tower = squareList.get(0).getCurrentPiece();
				if(!king.hadMoved() && !tower.hadMoved())
				{
					//Check pieces between before and after position
					ArrayList<Position> obstacles = listOfPositionBetween(squareList.get(4).getRow(), squareList.get(4).getColumn(), squareList.get(0).getRow(), squareList.get(0).getColumn());
					Iterator<Position> it = obstacles.iterator();
					while(it.hasNext())
						if(it.next().getCurrentPiece() != null)
							return false;
					squareList.get(4).setCurrentPiece(null);
					squareList.get(0).setCurrentPiece(null);
					squareList.get(2).setCurrentPiece(king);
					squareList.get(3).setCurrentPiece(tower);
					return true;
				}
			}
		}
		else
		{
			if(squareList.get(60).getCurrentPiece() != null && squareList.get(56).getCurrentPiece() != null)
			{
				Piece king = squareList.get(60).getCurrentPiece();
				Piece tower = squareList.get(56).getCurrentPiece();
				if(!king.hadMoved() && !tower.hadMoved())
				{
					//Check pieces between before and after position
					ArrayList<Position> obstacles = listOfPositionBetween(squareList.get(60).getRow(), squareList.get(60).getColumn(), squareList.get(56).getRow(), squareList.get(56).getColumn());
					Iterator<Position> it = obstacles.iterator();
					while(it.hasNext())
						if(it.next().getCurrentPiece() != null)
							return false;
					squareList.get(60).setCurrentPiece(null);
					squareList.get(56).setCurrentPiece(null);
					squareList.get(54).setCurrentPiece(king);
					squareList.get(53).setCurrentPiece(tower);
					return true;
				}
			}
		}
		return false;
	}

	private boolean kingSideCastle(Color playerColor)
	{
		if(playerColor == Color.WHITE)
		{
			if(squareList.get(4).getCurrentPiece() != null && squareList.get(7).getCurrentPiece() != null)
			{
				Piece king = squareList.get(4).getCurrentPiece();
				Piece tower = squareList.get(7).getCurrentPiece();
				if(!king.hadMoved() && !tower.hadMoved())
				{
					//Check pieces between before and after position
					ArrayList<Position> obstacles = listOfPositionBetween(squareList.get(4).getRow(), squareList.get(4).getColumn(), squareList.get(7).getRow(), squareList.get(7).getColumn());
					Iterator<Position> it = obstacles.iterator();
					while(it.hasNext())
						if(it.next().getCurrentPiece() != null)
							return false;
					squareList.get(4).setCurrentPiece(null);
					squareList.get(7).setCurrentPiece(null);
					squareList.get(6).setCurrentPiece(king);
					squareList.get(5).setCurrentPiece(tower);
					return true;
				}
			}
		}
		else
		{
			if(squareList.get(60).getCurrentPiece() != null && squareList.get(63).getCurrentPiece() != null)
			{
				Piece king = squareList.get(60).getCurrentPiece();
				Piece tower = squareList.get(63).getCurrentPiece();
				if(!king.hadMoved() && !tower.hadMoved())
				{
					//Check pieces between before and after position
					ArrayList<Position> obstacles = listOfPositionBetween(squareList.get(60).getRow(), squareList.get(60).getColumn(), squareList.get(63).getRow(), squareList.get(63).getColumn());
					Iterator<Position> it = obstacles.iterator();
					while(it.hasNext())
						if(it.next().getCurrentPiece() != null)
							return false;
					squareList.get(60).setCurrentPiece(null);
					squareList.get(63).setCurrentPiece(null);
					squareList.get(62).setCurrentPiece(king);
					squareList.get(61).setCurrentPiece(tower);
					return true;
				}
			}
		}
		return false;
	}

	private boolean onChessboardValidMove(Position before, Position after)
	{
		boolean checkPawnValidation = false;
		Move movement = new Move(before, after);

		if(before.getCurrentPiece() != null)
		{
			//Diagonal take for pawns
			if(before.getCurrentPiece().whatKindOfPiece() == TypeOfPiece.Pawn && (movement.getAbsLatitude() == movement.getAbsLongitude() && movement.getAbsLatitude() == 1))
			{
				checkPawnValidation = true;
				if(before.getCurrentPiece().getColor() == Color.BLACK)
				{
					if(movement.getLatitude() < 0)
					{
						if(after.getCurrentPiece() != null && after.getCurrentPiece().getColor() == Color.WHITE)
							return true;
					}
				}
				else if (before.getCurrentPiece().getColor() == Color.WHITE)
				{
					if(movement.getLatitude() > 0)
					{
						if(after.getCurrentPiece() != null && after.getCurrentPiece().getColor() == Color.BLACK)
							return true;
					}
				}
			}
			
			//Check for castling
			if(after.getCurrentPiece() != null && before.getCurrentPiece().whatKindOfPiece() == TypeOfPiece.King 
					&& after.getCurrentPiece().whatKindOfPiece() == TypeOfPiece.Rook 
					&& before.getCurrentPiece().getColor().equals(after.getCurrentPiece().getColor())
					&& after.getCurrentPiece().hadMoved() == false && before.getCurrentPiece().hadMoved() == false)
			{	
				ArrayList<Position> obstacles = listOfPositionBetween(before.getRow(), before.getColumn(), after.getRow(), after.getColumn());
				Iterator<Position> it = obstacles.iterator();
				while(it.hasNext())
					if(it.next().getCurrentPiece() != null)
						return false;
				return true;
			}

			if(!checkPawnValidation)
			{		
				if(before.getCurrentPiece().isValid(before.getRow(), before.getColumn(), after.getRow(), after.getColumn()))
				{
					if(!(after.getCurrentPiece() != null && after.getCurrentPiece().getColor() == before.getCurrentPiece().getColor()))
					{
						//Check pieces between before and after position
						ArrayList<Position> obstacles = listOfPositionBetween(before.getRow(), before.getColumn(), after.getRow(), after.getColumn());
						Iterator<Position> it = obstacles.iterator();

						while(it.hasNext())
							if(it.next().getCurrentPiece() != null)
								return false;
						//Prevent pawn from moving straight forward if there's a piece in front of it
						if(before.getCurrentPiece().whatKindOfPiece() == TypeOfPiece.Pawn && after.getCurrentPiece() != null)
							return false;
						return true;
					}
					else
						return false;
				}
			}
		}
		return false;
	}

	public int playRightMovement(int bRow, int bCol, int aRow, int aCol)
	{
		//Get two positions
		Position before = squareList.get(8*bRow+bCol);
		Position after = squareList.get(8*aRow+aCol);
		Move movement = new Move(bRow, bCol, aRow, aCol);

		Piece takenPiece = null;

		if(after.getCurrentPiece() != null && before.getCurrentPiece().whatKindOfPiece() == TypeOfPiece.King && after.getCurrentPiece().whatKindOfPiece() == TypeOfPiece.Rook && before.getCurrentPiece().getColor().equals(after.getColor()))
		{
			if(movement.getAbsLongitude() == 4)
				queenSideCastle(before.getCurrentPiece().getColor());
			else if (movement.getAbsLongitude() == 3)
				kingSideCastle(before.getCurrentPiece().getColor());
		}
		else
		{
			takenPiece = normalPlay(bRow, bCol, aRow, aCol);
			if(takenPiece != null)
			{
			}
		}

		return 0;
	}

	public ArrayList<int[]> getPossibleMoves(int bRow, int bCol)
	{
		ArrayList<int[]> returnList = new ArrayList<int[]>();
		Position before = squareList.get(8*bRow+bCol);
		Piece currentPiece = before.getCurrentPiece();
		if(currentPiece != null)
		{
			for(Position p : squareList)
			{
				if(onChessboardValidMove(before, p))
				{
					int[] tmp = new int[2];
					tmp[0] = p.getRow();
					tmp[1] = p.getColumn();
					returnList.add(tmp);
				}
			}
		}

		return returnList;
	}

	public Piece getPieceFrom(int x, int y)
	{
		return squareList.get(8*x+y).getCurrentPiece();
	}

	public Position getPositionFrom(int x, int y)
	{
		return squareList.get(8*x+y);
	}

}



