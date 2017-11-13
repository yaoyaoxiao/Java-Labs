import java.util.Scanner;

public class TicTacToe{
	
	private char[][] board;
	private char player; // 'X' or 'O'
	
	/* 
	 * Instantiate board to be a 3 by 3 char array of spaces.
	 * Set player to be 'X'.
	 */
	public TicTacToe() {
		/*
		* Step 1: create an empty board, with an initial value
		* of a space (' ')
		*/
		board = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
		player = 'X';
	}
	
	/* 
	 * If s represents a valid move, add the current player's symbol to the board and return true.
	 * Otherwise return false.
	 */
	public boolean play(String s) {
		/* Step 2: Fill in here with your own
		* play logic, and replace the return with you
		* own.
		*/ 
		int row = Character.getNumericValue(s.charAt(0)) - 10;
		int col = Character.getNumericValue(s.charAt(1)) - 1;

		if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != ' ') 
		{
			return false;
		}
		board[row][col] = player;
		return true; 
	}
	
	/*
	 * Switches the current player from X to O, or O to X.
	 */
	public void switchTurn() {
		// Step 3: Fill in with your code to toggle between
		// 'X' and 'O'
		player = player == 'X'? 'O':'X';
	}
	
	/*
	 * Returns true if the current player has won the game.
	 * Three in a row, column or either diagonal.
	 * Otherwise, return false.
	 */

	// make this suitable for a larger grid.
	public boolean won() {
		/* Step 5: Fill in the code for the won method. This method
        * should return true if the current player has 3 in-a-row 
		* in any row, column or diagonal. Otherwise, return false.
		*/
		char currPlayer = getPlayer();
		int count = 0, n = board.length, m = board[0].length;
		int winLen = 3;

		// check each row
		for (int i  = 0; i < n; i++)
		{
			count = 0;
			int j = 0;
			while (j < m)
			{
				if (board[i][j] == currPlayer)
				{
					count++;
					if (count == winLen) 
					{
						return true;
					}
				}
				else
				{
					count = 0;
					if (j > m-winLen)
					{
						break;
					}
				}
				j++;
			}
		}

		//check each column
		for (int i  = 0; i < m; i++)
		{
			count = 0;
			int j = 0;
			while (j < n)
			{
				if (board[j][i] == currPlayer)
				{
					count++;
					if (count == winLen) 
					{
						return true;
					}
				}
				else
				{
					count = 0;
					if (j > n-winLen)
					{
						break;
					}
				}
				j++;
			}
		}

		//check each diagonal (lower left part)
		for (int i = 0; i <= n - winLen; i++)
		{
			int r = i, c = 0;
			while (r < n && c < m)
			{
				if (board[r][c] == currPlayer)
				{
					count++;
					if(count == winLen)
					{
						return true;
					}
				}
				else
				{
					count = 0;
					if (r > n-winLen || c > m -winLen)
					{
						break;
					}
				}
				r++; 
				c++;
			}

		}

		//check each diagonal (upper right part)
		for (int i = 1; i <= m - winLen; i++)
		{
			int c = i, r = 0;
			while (r < n && c < m)
			{
				if (board[r][c] == currPlayer)
				{
					count++;
					if(count == winLen)
					{
						return true;
					}
				}
				else
				{
					count = 0;
					if (r > n-winLen || c > m -winLen)
					{
						break;
					}
				}
				r++; 
				c++;
			}

		}

		//check each diagonal (upper left part)
		for (int i = n-1; i >= winLen-1; i--)
		{
			int r = i, c = 0;
			while (r >= 0 && c < m)
			{
				if (board[r][c] == currPlayer)
				{
					count++;
					if(count == winLen)
					{
						return true;
					}
				}
				else
				{
					count = 0;
					if (r < winLen-1 || c > m-winLen)
					{
						break;
					}
				}
				r--; 
				c++;
			}

		}

		//check each diagonal (lower right part)
		for (int i = 1; i <= m - winLen; i++)
		{
			int r = n-1, c = i;
			while (r >= 0 && c < m)
			{
				if (board[r][c] == currPlayer)
				{
					count++;
					if(count == winLen)
					{
						return true;
					}
				}
				else
				{
					count = 0;
					if (r < winLen-1 || c > m -winLen)
					{
						break;
					}
				}
				r--; 
				c++;
			}

		}

		return false;
	}
	
	/*
	 * Returns true if there are no places left to move
	 */
	public boolean stalemate() {
	    /*
		 * Step 4: Fill in the code for the stalemate method. It
         * should return true if there are no more places to move 
		 * on the board. Otherwise, return false return false; 
		 */
	    for (int i = 0; i < board.length; i++)
	    {
	    	for (int j = 0; j < board[0].length; j++)
	    	{
	    		if (board[i][j] == ' ')
	    		{
	    			return false;
	    		}
	    	}
	    }
		return true; 
	}

	public char getPlayer() {
		return player;
	}

	public void print() {
		System.out.println();
		System.out.println("\t  1 2 3");
		System.out.println();
		System.out.println("\tA "+board[0][0]+"|"+board[0][1]+"|"+board[0][2]);
		System.out.println("\t  -----");
		System.out.println("\tB "+board[1][0]+"|"+board[1][1]+"|"+board[1][2]);
		System.out.println("\t  "+"-----");
		System.out.println("\tC "+board[2][0]+"|"+board[2][1]+"|"+board[2][2]);
		System.out.println();
	}
	
	/* 
	 * Step 6: Main Method for Final Step - Delete your main method 
	 * and uncomment this one. 
	 * Runs the game by getting input from the user, making the 
	 * appropriate moves, and prints who won or if it was a stalemate. 
	*/ 

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		TicTacToe game = new TicTacToe();
		System.out.println("Welcome to tic-tac-toe");
		System.out.println("Enter coordinates for your move following the X and O prompts");

		boolean gameWon = false;
		
		while(!game.stalemate()) {
			//Print the game
			game.print();
			
			char currPlayer = game.getPlayer();
			String pos = "";
			//Prompt player for their move
			System.out.print(currPlayer + ": ");
			pos = in.next();

			//Loop while the method play does not return true when given their move.
			//Body of loop should ask for a different move
			while(!game.play(pos))
			{
				System.out.println("\ninvalid coordinates: Out of bounds OR position occupied. Enter a different one.");
				System.out.print(currPlayer + ": ");
				pos = in.next();
			}
					
			//If the game is won, call break; 
			if (game.won())
			{
				gameWon = true;
				break;
			}
						
			//Switch the turn
			game.switchTurn();
			
		}
		game.print();
		if(gameWon){
			System.out.println("Player "+game.getPlayer()+" Wins!!!!");
		} else {
			System.out.println("Stalemate");
		}
	} 
	
}
