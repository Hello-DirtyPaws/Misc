/**
 * 
 */
// manoharchitoda
package piece;
/**
 * @author manoharchitoda 
 * @author surajupadhyay
 * */
public class Rook extends Piece
{
	/**
	 * @param id - the name of the piece
	 * @param row - row position
	 * @param col - column position
	 */
	public Rook(String id, int row, int col)
	{
		super(id, row,col);
	}
	
	/**
	 * @param newRow - new pos
	 * @param newCol - new pos
	 * @param board - the game board
	 * @return -1 if invalid else 0 for valid move
	 */
	public int move(int newRow, int newCol, Piece[][] board)
	{
		if(this.col == newCol && this.row == newRow)
		{
			return -1;
		}
		else if(this.col != newCol && this.row != newRow)
		{
			return -1;
		}		
		else if(this.col == newCol)
		{
			if(!validSpaceVertical(newRow, board))
			{
				return -1;
			}
		}			
		else if(this.row == newRow)
		{
			if(!validSpaceHorizon(newCol, board))
			{
				return -1;
			}
		}
		
		//Do not have the same team player at the new location
		if(board[newRow][newCol].getColor() != this.getColor())
		{
			return 0;
		}
		
		return -1;
	}
	
	/**
	 * 
	 * @param newRow - new row pos
	 * @param board - game board
	 * @return true if valid vertical space
	 */
	public boolean validSpaceVertical(int newRow, Piece[][] board)
	{
		if(this.row < newRow)
		{
			for(int i = this.row+1; i < newRow; i++)
			{
				char id = board[i][this.col].id.charAt(0);
				if(id != ' ' && id != '#')
				{
					return false;
				}
			}
			return true;
		}
		else
		{
			for(int i = this.row-1; i > newRow; i--)
			{
				char id = board[i][this.col].id.charAt(0);
				if(id != ' ' && id != '#')
				{
					return false;
				}
			}
			return true;
		}
	}
	
	/**
	 * 
	 * @param newCol - new col pos
	 * @param board - game board pos
	 * @return true if valid horizontal space
	 */
	public boolean validSpaceHorizon(int newCol, Piece[][] board)
	{
		if(this.col < newCol)
		{
			for(int i = this.col+1; i < newCol; i++)
			{
				char id = board[this.row][i].id.charAt(0);
				if(id != ' ' && id != '#')
				{
					return false;
				}
			}
			return true;
		}
		else
		{
			for(int i = this.col-1; i > newCol; i--)
			{
				char id = board[this.row][i].id.charAt(0);
				if(id != ' ' && id != '#')
				{
					return false;
				}
			}
			return true;
		}
	}
}
