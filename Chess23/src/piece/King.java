
/**
 * @author manoharchitoda 
 * @author surajupadhyay
 * */
package piece;

public class King extends Piece
{
	/**
	 * @param id - the name of the piece
	 * @param row - row position
	 * @param col - column position
	 */
	public King(String id, int row, int col)
	{
		super(id, row,col);
	}
	
	/**
	 * @param newRow - new pos
	 * @param newCol - new pos
	 * @param board - the board
	 * @return -1 if invalid else 3 for valid move
	 */
	public int move(int newRow, int newCol, Piece[][] board)
	{
		
		if(Math.abs(this.row-newRow) > 1 || Math.abs(this.col-newCol) > 1 || 
				(this.row == newRow && this.col == newCol) )
		{
			return -1;
		}
		
		//Do not have the same team player at the new location
		else if(board[newRow][newCol].getColor() != this.getColor())
		{
			return 3;
		}
		
		return -1;
	}

}
