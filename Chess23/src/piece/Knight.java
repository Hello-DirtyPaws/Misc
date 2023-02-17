package piece;

/**
 * @author manoharchitoda 
 * @author surajupadhyay
 * */
public class Knight extends Piece
{
	/**
	 * @param id - the name of the piece
	 * @param row - row position
	 * @param col - column position
	 */
	public Knight(String id, int row, int col)
	{
		super(id, row,col);
	}
	/**
	 * @param newRow - new pos
	 * @param newCol - new pos
	 * @param board - the board
	 * @return -1 if invalid else 0 for valid move
	 */
	@Override
	public int move(int newRow, int newCol, Piece[][] board)
	{	
		if(checkList(newRow, newCol))
		{
			
			//Do not have the same team player at the new location
			if(board[newRow][newCol].getColor() != this.getColor())
			{
				return 0;
			}
		}
			
		return -1;
	}
	
	public boolean checkList(int newRow, int newCol)
	{
		if(Math.abs(this.row - newRow) == 2 && Math.abs(this.col-newCol) == 1)
			return true;
		else if(Math.abs(this.row - newRow) == 1 && Math.abs(this.col-newCol) == 2)
			return true;
		return false;
	}
}
