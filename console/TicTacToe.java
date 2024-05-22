import java.util.*;

/**
 * TicTacToe game implementation.
 */
public class TicTacToe {

    public static void main(String[] args) {
        new Game().start();
    }

    /**
     * Represents a single game of Tic Tac Toe.
     */
    public static class Game {
        private Player p1;
        private Player p2;
        private char[][] board;
        private Player currentPlayer;
        private static int BOARD_SIZE = 3;
        
        public Game() {
            board = new char[BOARD_SIZE][BOARD_SIZE];
            initializeBoard();
        }

        private class Player {
            private String _name;
            private char _symbol;
    
            
            public Player(){}
    
            /**
             * Getter PlayerName
             * @return _name
             */
            public String getPlayerName(){return _name;}
            
            /**
             * Getter PlayerSymbol
             * @return _symbol
             */
            public char getPlayerSymbol(){return _symbol;}
    
            /**
             * 
             * @param name
             */
            public void setPlayerName(String name){_name = name;}
    
            /**
             * 
             * @param symbol
             */
            public void setPlayerSymbol(char symbol){_symbol = symbol;}
        }

        private void initializeBoard(){
            for(int i = 0 ; i < BOARD_SIZE ; i++){
                for(int j = 0 ; j < BOARD_SIZE ; j++){
                    board[i][j] = ' ';
                }
            }
        }

        public void start() {
            Scanner sc = new Scanner(System.in);

            System.out.println("Welcome to TIC TAC TOE game");
            
            System.out.println("Enter Player1's name : ");
            p1 = new Player();
            p1.setPlayerName(sc.nextLine());
            p1.setPlayerSymbol('X');

            System.out.println("Enter Player2's name : ");
            p2 = new Player();
            p2.setPlayerName(sc.nextLine());
            p2.setPlayerSymbol('O');

            currentPlayer = getRandomPlayer();

            System.out.println("The player " + currentPlayer.getPlayerName() + " with symbol " + currentPlayer.getPlayerSymbol() + " begin !!");

            
            boolean isGameOn = true;

            while (isGameOn) {
                printBoardState();
                makeMovePlayer(currentPlayer , sc);
                isGameOn = !isGameWin() && !isBoardFull();
                if(isGameOn) switchPlayer();
            }

            displayGameResult();
            

            sc.close();
            
        }


        private Player getRandomPlayer(){
            Random random = new Random();
            return random.nextInt(2) == 0 ? p1 : p2;
        }

        private void printBoardState(){
            System.out.println("--------------");
            for(int i = 0 ; i < BOARD_SIZE ; i++){
                for(int j = 0 ; j < BOARD_SIZE ; j++){
                    System.out.print(" " + board[i][j]);
                    if (j < BOARD_SIZE - 1) {
                        System.out.print("  |");
                    }
                }
                System.out.println();
                
                System.out.println("--------------");
                
            
            }
        }


        private void makeMovePlayer(Player currentPlayer , Scanner sc){
            boolean isValidMove = false;
            while(!isValidMove){
                System.out.println("It's Player " + currentPlayer._name + " to play , enter the row and columns : ");
                System.out.println("Row : ");
                int row = sc.nextInt();
                System.out.println("Column : ");
                int column = sc.nextInt();
                sc.nextLine();
                
                //Test if row and column are valid 
                if(row >= 0 && row <= BOARD_SIZE && column >= 0 && column <= BOARD_SIZE && board[row][column] == ' '){
                    board[row][column] = currentPlayer.getPlayerSymbol();
                    isValidMove = true;
                }
                else{
                    System.out.println("This is not a valid move. Please try another move !!");
                }
            }
        }

        private boolean isGameWin(){
            char symbol = currentPlayer._symbol;

            for (int i = 0; i < BOARD_SIZE; i++) {
                //Check for rows and columns
                if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                    (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                    return true;
                }
            }
            // Check diagonals
            if ((board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
                return true;
            }
            return false;
        }

        private boolean isBoardFull(){
            for(int i = 0 ; i < BOARD_SIZE ; i++){
                for(int j = 0 ; j < BOARD_SIZE ; j++){
                    if(board[i][j] == ' ') return false;
                }
            }
            return true;
        }

        private void switchPlayer(){
            currentPlayer = currentPlayer == p1 ? p2 : p1;   
        }

        private void displayGameResult(){
            //display the last state of board
            printBoardState();
            //Win or Null
            if(isGameWin()) System.out.println("The player " + currentPlayer._name + " with "+ currentPlayer._symbol + "symbol is winner !!!"); 
            else System.out.println("Null !!");
        }
        
    }
    
}
