package piece;

/**
 * @author manoharchitoda 
 * @author surajupadhyay
 * */
public class Bishop extends Piece
{
	/**
	 * @param id - the name of the piece
	 * @param row - row position
	 * @param col - column position
	 */
	public Bishop(String id, int row, int col)
	{
		super(id, row,col);
	}
	
	/**
	 * @param newRow new row number to move to
	 * @param newCol new column number to move to
	 * @return -1 if invalid else 0 for valid move
	 */
	@Override
	public int move(int newRow, int newCol, Piece[][] board) 
	{
		int sum1 = this.row + this.col;
		int diff1 = this.row - this.col;
		int sum2 = newRow + newCol;
		int diff2 = newRow - newCol;
		
		
		if(sum1 == sum2 && diff1 == diff2)
		{
			return -1;
		}
		
		else if(sum1 != sum2 && diff1 != diff2)
		{
			return -1;
		}
		
		else if(sum1 == sum2)
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
		
		//Do not have the same team player at the new location
		if(board[newRow][newCol].getColor() != this.getColor())
		{
			return 0;
		}
						
		return -1;
	}

	/**
	 * Will check the diagonal that goes UP from left-down cornor
	 * 
	 * @param newCol - new pos
	 * @param board - new pos
	 * @return true if in valid diagonal UP from left-down cornor else flase
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
	 * @param newCol - new pos
	 * @param board - new pos
	 * @return true if in valid diagonal Down form left-up cornor else flase
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
