

/**
 * @author manoharchitoda 
 * @author surajupadhyay
 * */
package piece;

public class Queen extends Piece
{
	/**
	 * @param id - the name of the piece
	 * @param row - row position
	 * @param col - column position
	 */
	public Queen(String id, int row, int col)
	{
		super(id, row,col);
	}
	
	/**
	 * @param newRow - new pos
	 * @param newCol - new pos
	 * @param board - the board
	 * @return -1 if invalid else 0 for valid move
	 */
	public int move(int newRow, int newCol, Piece[][] board)
	{		
		if(this.row == newRow && this.col == newCol)
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
		
		//------checked for horizontal and vertical possibilities(Rook-Like move);
		//------checking for diagonal up and down possibilities(Bishop-Like move);
		
		else 
		{
			int sum1 = this.row + this.col;
			int diff1 = this.row - this.col;
			int sum2 = newRow + newCol;
			int diff2 = newRow - newCol;
			
			if(sum1 == sum2)
			{
				if(!validSpaceDiagUP(newCol, board))
				{
					return -1;
				}
			}
				
			else if(diff1 == diff2)
			{
				if(!validSpaceDiagDN(newCol, board))
				{
					return -1;
				}
			}
				
			//New location did not pass any tests for possibility of being in valid space;
			//did not pass: vertical, horizontal, diagonal up and diagonal down tests;
			else
				return -1;
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
	 * @param newRow - new row
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
	 * @param newCol - new pos
	 * @param board - new pos
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

	/**
	 * 
	 * @param newCol - new pos
	 * @param board - board
	 * @return true if in valid diagonal UP from left-down cornor
	 */
	public boolean validSpaceDiagUP(int newCol, Piece[][] board) 
	{
		if(this.col < newCol)
		{
			for(int i = 1; this.col+i < newCol; i++)
			{
				char id = board[this.row-i][this.col+i].id.charAt(0);
				if(id != ' ' && id != '#')
				{
					return false;
				}
			}
			return true;
		}
		else
		{
			for(int i = 1; this.col-i > newCol; i++)
			{
				char id = board[this.row+i][this.col-i].id.charAt(0);
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
	 * @param newCol - new col
	 * @param board - board game
	 * @return true if in valid diagonal Down from left-up cornor
	 */
	public boolean validSpaceDiagDN(int newCol, Piece[][] board) 
	{
		if(this.col < newCol)
		{
			for(int i = 1; this.col+i < newCol; i++)
			{
				char id = board[this.row+i][this.col+i].id.charAt(0);
				if(id != ' ' && id != '#')
				{
					return false;
				}
			}
			return true;
		}
		else
		{
			for(int i = 1; this.col-i > newCol; i++)
			{
				char id = board[this.row-i][this.col-i].id.charAt(0);
				if(id != ' ' && id != '#')
				{
					return false;
				}
			}
			return true;
		}
	}

}
