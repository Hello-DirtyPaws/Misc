
package piece;
/**
 * @author manoharchitoda 
 * @author surajupadhyay
 * */
public class Pawn extends Piece
{
	/**
	 * @param id - the name of the piece
	 * @param row - row position
	 * @param col - column position
	 */
	public Pawn(String id, int row, int col)
	{
		super(id, row,col);
	}
	
	/**
	 * @param newRow - new pos
	 * @param newCol - new pos
	 * @param board - the board
	 * @return -1 for Invalid move, 0 for Valid move, 1 for En passant 2 for Promotion
	 */
	public int move(int newRow, int newCol, Piece[][] board)
	{
		int i;
		//black pawn
		if(this.getColor() == 0)
			i = 1;
		else
			i = -1;
		
		int rowChange = i * (newRow - this.row);
		
		if(rowChange < 1 || Math.abs(this.col - newCol) > 1)
		{
			return -1;
		}
		
		//col difference = 1 --> going cross
		else if(Math.abs(this.col - newCol) == 1)
		{
			if(rowChange != 1)
			{
				return -1;
			}
			else
			{
				////////////EMPASANT MOVE MAY USE THIS TO SEE EMPASANT FLAG
				////////////EMPASANT FLAG = TRUE --> can move to an empy space;
				
				//Moving to an empty space in CROSS --> not allowed (color = -1);
				//Killing an own team player --> not allowed (color == own's color);
				int color = board[newRow][newCol].getColor();
				if(color == -1 || (color == this.getColor()))
				{
					return -1;
				}
				else
				{
					return 0;
				}
			}
		}
		//Same col --> going straight
		else
		{
			//Moving more than 2 rows OR moving to an NON-EMPTY cell --> Invalid;
			if(rowChange > 2 || board[newRow][newCol].getColor() != -1)
			{
				return -1;
			}
			else if(rowChange == 1)
			{
				if(newRow == 0 || newRow == 7)
				{
					return 2;
				}
				return 0;
			}
			
			//Empasant move --> 2 step forward move.
			else if(rowChange == 2)
			{
				//check for the middle step to be empty
				if(board[newRow - i][newCol].getColor() == -1 && (this.row == 6 || this.row == 1))
				{
					return 1;
				}
				else
				{
					return -1;
				}					
			}
			else 
			{
				return -1;
			}
		}			
	}
}
