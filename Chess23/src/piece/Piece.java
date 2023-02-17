
package piece;
/**
 * @author manoharchitoda 
 * @author surajupadhyay
 * */
public class Piece 
{
	public String id;
	public int col;
	public int row;
	public boolean active=true;
	
	/**
	 * 
	 * @param id - the name of the piece
	 * @param row - row position
	 * @param col - coloumn position
	 */
	public Piece(String id, int row, int col)
	{
		this.id = id;
		this.row = row;
		this.col = col;
	}
	
	/**
	 * 
	 * @param newRow - new row position
	 * @param newCol - new column position
	 * @param board - the game board
	 * @return int - specifies an invalid move because a piece obj cannot move
	 */
	public int move(int newRow, int newCol, Piece[][] board)
	{	
		//Piece.move() will only be called on empty spaces
		//but will never be called on any valid piece(non-empty Piece)
		return -1;
	}
	
	/**
	 * 
	 * @return 0 - black 1 is white and -1 is empty space
	 */
	public int getColor() 
	{
		if(this.id.charAt(0) == 'b')
			return 0;
		else if(this.id.charAt(0) == 'w')
			return 1;
		else
			return -1;
	}
	
	/**
	 * @return string - representaion of ID
	 */
	public String toString()
	{
		return this.id;
		
	}

	/**
	 * 
	 * @param newRank - the row position
	 * @param newFile - the column position
	 */
	public void setLoc(int newRank, int newFile)
	{
		this.row = newRank;
		this.col = newFile;
	}
}
