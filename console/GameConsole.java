import java.util.*;


public class GameConsole{
	private Player p1;
	private Player p2;
	private String[][] stateBoard;
	private Player currentPlayer;
	private static int BOARD_SIZE = 3;
	private boolean isGameOn = false;


	/**
	 *
	 * Constructor of class GameConsole
	 *
	 */

	public GameConsole(){
	     stateBoard = new String[BOARD_SIZE][BOARD_SIZE];
	     initializeStateBoard();
	}

	private void initializeStateBoard(){
		for(int i = 0 ; i < BOARD_SIZE ; i++){
			for(int j = 0 ; j < BOARD_SIZE ; j++){
			  stateBoard[i][j] = " ";
			}
		}
	}


	public void start(){
		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome to tic tac toe game !!");


		System.out.println("Enter player1's name : ");
		p1 = new Player();
		p1.setPlayerName(sc.nextLine());
		p1.setPlayerSymbol("O");

		System.out.println("Enter player2's name : ");
		p2 = new Player();
		p2.setPlayerName(sc.nextLine());
		p2.setPlayerSymbol("X");

		currentPlayer = getRandomPlayer();
		
		System.out.println("The player " + currentPlayer.getPlayerName() + " with symbol " + currentPlayer.getPlayerSymbol() + " begin the game !!");

		this.isGameOn = true;

		while(isGameOn){
	        makeMovePlayer(this.currentPlayer , sc);
		    this.isGameOn = !isGameWin() && !isBoardFull();
		    if(isGameOn) switchPlayer();
		}

		displayGameResult();

	}

	/**
	 *
	 * get a random player to being game 
	 * return Player
	 *
	 */


	private Player getRandomPlayer(){
		Random random = new Random();
		return random.nextInt(2) == 0 ? p1 : p2;
	}



	
	 private void printBoardState(){
            System.out.println("--------------");
            for(int i = 0 ; i < BOARD_SIZE ; i++){
                for(int j = 0 ; j < BOARD_SIZE ; j++){
                    System.out.print(" " + stateBoard[i][j]);
                    if (j < BOARD_SIZE - 1) {
                        System.out.print("  |");
                    }
                }
                System.out.println();
                
                System.out.println("--------------");
                
            
            }
        }
	


	private void makeMovePlayer(Player p , Scanner sc){
	    boolean isValidMove = false;
	    while(!isValidMove){
		printBoardState();
		System.out.println("It 's player " + p.getPlayerName() + " to play , enter row and column : ");
		System.out.println("Row : ");
		int row = sc.nextInt();
		System.out.println("Column : ");
		int column = sc.nextInt();
		sc.nextLine();

		//Test if row and column are valid 
		if(row >= 0 && row <= BOARD_SIZE && column >= 0 && column <= BOARD_SIZE && stateBoard[row][column] == " "){
		   this.stateBoard[row][column] = p.getPlayerSymbol();
		   isValidMove = true;
		}
		else System.out.println("Please enter a valid entry , try again : ");
		}
	} 

	private void switchPlayer(){currentPlayer = currentPlayer == p1 ? p2 : p1;}

	private boolean isBoardFull (){
		for(int i = 0 ; i < BOARD_SIZE ; i++){
			for(int j = 0 ; j < BOARD_SIZE ; j++){
			   if(stateBoard[i][j] == " ") return false; 
			}	
		}
        	return true;
	}

	private boolean isGameWin (){
        String symbol = currentPlayer.getPlayerSymbol();
	    for(int i = 0 ; i < BOARD_SIZE ; i++){
		//check rows and columns
		if((stateBoard[i][0] == symbol && stateBoard[i][1] == symbol && stateBoard[i][2] == symbol) ||
		    (stateBoard[0][i] == symbol && stateBoard[1][i] == symbol && stateBoard[2][i] == symbol)) return true; 
	    }
            
	    //check diagonals
	    if((stateBoard[0][0] == symbol && stateBoard[1][1] == symbol && stateBoard[2][2] == symbol) ||
              (stateBoard[0][2] == symbol && stateBoard[1][1] == symbol && stateBoard[2][0] == symbol)) return true ;
	   
	   return false;
	    
	}
	
	private void displayGameResult(){
	   //display the last state of board 
	   printBoardState();
		
	   //Win or Null
	   if(isGameWin()){ 
		System.out.println("The player " + currentPlayer.getPlayerName() + " with symbol " + currentPlayer.getPlayerSymbol() + " win the game !!");
	   }
	   else System.out.println("Null !!");
	    
	}
}
