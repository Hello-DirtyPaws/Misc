 package chess;

 /**
  * @author manoharchitoda 
  * @author surajupadhyay
  * */
public class Chess
{
  public static void main(String [] args)
  {
	  Board b = new Board();
	  b.drawBoard();
	  while(!b.isCheckMate() && !b.draw && !b.resign)
	  {
		  if(b.isCheck())
			  System.out.println("check");
		  b.promptInput();
		  if(!b.draw && !b.resign)
			  b.drawBoard();
	  }
	  
	  // Handle after game ends
	  if(b.draw){
		  System.out.println("\nDraw");
	  }
	  if(b.whiteR){
		  System.out.println("Black Wins!");
	  }if(b.blackR){
		  System.out.println("White Wins!");
	  }
  }
}
