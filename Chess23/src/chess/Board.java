
/**
 * @author manoharchitoda 
 * @author surajupadhyay*/
package chess;

import java.util.Scanner;

import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;

public class Board
{	
	Scanner keyboard = new Scanner(System.in);
	//Black and White
	Piece [][]blackPieces = new Piece[2][8];
	Piece [][]whitePieces = new Piece[2][8];
	
	//Flags
	boolean white = true;
	boolean draw = false;
	boolean resign = false;
	boolean invalid = false;
	boolean check = false;
	boolean checkMate = false;
		
	//Flags for resignation
	boolean blackR = false;
	boolean whiteR = false;
	
	//Game Board
	public Piece board [][];
	
	//Global Vars
	String input = "";
	int rank = 0;
	int file = 0;
	int newRank = 0;
	int newFile = 0;
	
	/**
	 * This creates an instance of Board
	 * 
	 * */
	public Board()
	{
		this.board = new Piece[8][8];
		generatePieces();
		resetBoard();
	}
	
	/**
	 * This will initialize the two arrays that hold black
	 * and white pieces
	 * No parameters needed
	 * */
	public void generatePieces()
	{
		//Black pieces
		this.blackPieces[0][0] = new Rook("bR",0,0);
		this.blackPieces[0][1] = new Knight("bN",0,1);
		this.blackPieces[0][2] = new Bishop("bB",0,2);
		this.blackPieces[0][3] = new Queen("bQ",0,3);
		this.blackPieces[0][4] = new King("bK",0,4);
		this.blackPieces[0][5] = new Bishop("bB",0,5);
		this.blackPieces[0][6] = new Knight("bN",0,6);
		this.blackPieces[0][7] = new Rook("bR",0,7);
		
		for (int i = 1, j=0; j < blackPieces[i].length; j++)
		{
			this.blackPieces[i][j] = new Pawn("bP",i,j);
		}
		
		//White pawn pieces
		for (int j=0; j < whitePieces[0].length; j++)
		{
			this.whitePieces[0][j] = new Pawn("wP",6,j);
		}
		
		
		this.whitePieces[1][0] = new Rook("wR",7,0);
		this.whitePieces[1][1] = new Knight("wN",7,1);
		this.whitePieces[1][2] = new Bishop("wB",7,2);
		this.whitePieces[1][3] = new Queen("wQ",7,3);
		this.whitePieces[1][4] = new King("wK",7,4);
		this.whitePieces[1][5] = new Bishop("wB",7,5);
		this.whitePieces[1][6] = new Knight("wN",7,6);
		this.whitePieces[1][7] = new Rook("wR",7,7);
	}

	/**
	 * This method will reset the board upon invocation
	 * and reset the pieces to their starting position
	 * 
	 * */
	public void resetBoard()
	{
		for(int i = 0; i < blackPieces.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				this.board[i][j] = this.blackPieces[i][j];
			}
		}
		
		//// Initializing rest of the board
		for (int i = 2; i < 6; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				if((i+j) % 2 == 0) 
				{
					this.board[i][j] = new Piece("  ",i,j);
				}
				else 
				{
					this.board[i][j] = new Piece("##",i,j);
				}
			}
		}
		
		for (int i = 6, k = 0; i < whitePieces[0].length && k < whitePieces.length; i++,k++)
		{
			for (int j = 0; j < whitePieces[k].length; j++)
			{
				this.board[i][j] = this.whitePieces[k][j];
			}
		}
	}
	
	
	/**
	 * This method will draw the board upon invocation
	 * 
	 * */
	public void drawBoard()
	{
		int count = 8;
		int i;
		for (i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				System.out.print(this.board[i][j] + " ");
			}
			System.out.println(count--);
		}
		System.out.println(" a  b  c  d  e  f  g  h");
		if(invalid)
			System.out.println("\n***Illegal move, try again**");
	}
	
	/**
	 * This method will check if there is a possibility of 
	 * check-mate, if there exists one, it will return true
	 * and the game will enter the EndGame mode
	 * @return boolean-if check-mate is found
	 * */
	public boolean isCheckMate() 
	{
		return this.checkMate;
	}
	
	/**
	 * This method will prompt the player for input
	 * and pass the input accordingly
	 * 
	 * */
	public void promptInput()
	{
		invalid = true;
		while(invalid)
		{
			if(white)
			{
				cleanUp();
				System.out.print("\nWhite's move: ");
				input = keyboard.nextLine();
				parseIO(1, input);
				System.out.println();
				if(!invalid)
				{
					this.white = false;
					break;
				}
			}
			else 
			{
				cleanUp();
				System.out.print("\nBlack's move: ");
				input = keyboard.nextLine();
				parseIO(0, input);
				System.out.println();
				if(!invalid)
				{
					this.white = true;
					break;
				}
			}
		}
	}
	
	/**
	 * This method will parse what the user enters 
	 * @param i - white = 1 and black = 0
	 * @param io string that has been input by the user
	 * */
	public void parseIO(int i, String io)
	{
		String []in = io.split(" ");
		int confirmation;
		switch(in.length)
		{
			//Resign, draw, or invalid
			case 1:
				if(in[0].equalsIgnoreCase("resign"))
				{
					System.out.println();
					this.resign = true;
					if(i == 1)
						this.whiteR = true;
					else
						this.blackR = true;
				}
				else if(in[0].equalsIgnoreCase("draw"))
				{
					draw(i);
				}
				else
					this.invalid = true;
				break;
			// Correct move or an invalid move
			case 2:
				confirmation = getFileRank(in[0], in[1]);
				int move = this.board[rank][file].move(newRank, newFile, this.board);
				if(confirmation == 0)
				{
					switch(move) 
					{
					case 0:
						movePiece();
						break;
					case 1:
						movePiece();
						break;
					case 2:
						movePiece();
						promotion("Q");
						break;
					case 3:
						movePiece();
						break;
					default:
						this.invalid = true;
						System.out.println("Illegal Move. try again!");
					}
				}
				else
					this.invalid = true;
				break;
			// Draw request, promotion, or invalid
			case 3:
				confirmation = getFileRank(in[0], in[1]);
				move = this.board[rank][file].move(newRank, newFile, this.board);
				if(confirmation == 0 && (in[2].equalsIgnoreCase("draw?") || 
						(in[2].equalsIgnoreCase("draw")))) 
				{
					if(move >= 0)
					{
						movePiece();
						draw(i);
					}
				}
				else if(confirmation == 0 && (in[2].equalsIgnoreCase("Q") ||
						in[2].equalsIgnoreCase("B") ||
						in[2].equalsIgnoreCase("N") ||
						in[2].equalsIgnoreCase("R")))
				{
					movePiece();
					promotion(in[2]);
				}
				else
					this.invalid = true;
				break;
			default:
				System.out.println("Invalid Input!");
				break;
		}
			
	}
	
	/**
	 * This method will check the draw status 
	 * @param i - white = 1 and black = 0
	 * */
	private void draw(int i)
	{
		if(i == 1)
		{
			System.out.println("White accepted draw request.");
			draw = true;
		}
		else
		{
			System.out.println("Black accepted draw request.");
			draw = true;
		}
	}
	
	/**
	 * This resets all the necessary flag variable to default values
	 * */
	private void cleanUp()
	{
		this.rank = 0;
		this.file = 0;
		this.newRank = 0;
		this.newFile = 0;
		
		this.draw = false;
		this.resign = false;
		this.invalid = false;
	}
	
	/**
	 * This method will initialize the file and rank of the 
	 * pieces entered by the players
	 * @param fromHere -string that contains the departing coordinates
	 * @param moveHere -string that contains the destination coordinates
	 * @return int-if input is right the global variables will be initialized
	 * */
	public int getFileRank(String fromHere, String moveHere) 
	{
		int tempRank0 = Integer.parseInt(""+fromHere.charAt(1));
		int tempRank = Integer.parseInt(""+moveHere.charAt(1));
		this.file = Character.getNumericValue(fromHere.charAt(0)) - 10;
		this.newFile = Character.getNumericValue(moveHere.charAt(0)) - 10;
		
		if(tempRank <= 8 || tempRank > 0 && tempRank0 > 0 || tempRank0 <= 8)
		{
			this.newRank = 8 - tempRank;
			this.rank = 8 - tempRank0;
		}
		if(this.file >= 0 && this.file <= 7 
						&& this.newFile >= 0 && this.newFile <= 7)
			return 0;
		else
			return -1;
	}
	
	/**
	 * This performs the move that is verified
	 * */
	public void movePiece()
	{
			this.board[this.newRank][this.newFile] = this.board[this.rank][this.file];
			this.board[this.newRank][this.newFile].setLoc(newRank,newFile);
			if((file+rank) % 2 == 0)
				this.board[this.rank][this.file] = new Piece("  ", rank,file);
			else
				this.board[this.rank][this.file] = new Piece("##",rank,file);
	}
	
	/**
	 * Promotes the current pawn to a specified piece
	 * @param s is the specified piece to be promoted to
	 * */
	public void promotion(String s)
	{
		String str = "w";
		if(!white)
			str = "b";
		switch(s)
		{
			case "R":
				this.board[newRank][newFile] = new Rook(str +"R", newRank, newFile);
				break;
			case "Q":
				this.board[newRank][newFile] = new Queen(str +"Q", newRank, newFile);
				break;
			case "N":
				this.board[newRank][newFile] = new Knight(str +"N", newRank, newFile);
				break;
			case "B":
				this.board[newRank][newFile] = new Bishop(str +"B", newRank, newFile);
				break;
			default:
				break;
		}
	}
	
	/**
	 * @return true if there is check
	 * */
	public boolean isCheck() 
	{
		boolean check = false;
		int kRow = 0;
		int kCol = 0;
		if(white)
		{
			kRow = whitePieces[1][3].row;
			kCol = whitePieces[1][3].col;
			
			for (int i = 0; i < blackPieces.length; i++)
			{
				for (int j = 0; j < blackPieces[i].length; j++)
				{
					if(blackPieces[i][j].move(kRow, kCol,this.board) >= 0)
					{
						check = true;
						break;
					}
				}
			}
			
		}
		else
		{
			kRow = blackPieces[0][4].row;
			kCol = blackPieces[0][4].col;
			
			for (int i = 0; i < whitePieces.length; i++)
			{
				for (int j = 0; j < whitePieces[i].length; j++)
				{
					if(whitePieces[i][j].move(kRow, kCol,this.board) >= 0)
					{
						check = true;
						break;
					}
				}
			}
		}
		if(check)
			return true;
		else
			return false;
	}
}
