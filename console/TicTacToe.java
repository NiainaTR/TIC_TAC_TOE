package console;
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

            
            //boolean isGameOn = true;

            printBoardState();

            /* 
            while (isGameOn) {
                printBoardState();
                makeMovePlayer();
                isGameOn = !isGameWin && !isGameNull;
                if(isGameOn) switchPlayer();
            }

            displayGameResult();
            */
            

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

    }




        /*
        private void printBoard() {
            System.out.println("  0 1 2");
            for (int i = 0; i < BOARD_SIZE; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < BOARD_SIZE; j++) {
                    System.out.print(board[i][j]);
                    if (j < BOARD_SIZE - 1) {
                        System.out.print("|");
                    }
                }
                System.out.println();
                if (i < BOARD_SIZE - 1) {
                    System.out.println("  -----");
                }
            }
        }

        
        private void playerMove(Player player, Scanner sc) {
            boolean validMove = false;
            while (!validMove) {
                System.out.println(player.getName() + " (" + player.getMarker() + "), enter your move (row and column): ");
                int row = sc.nextInt();
                int col = sc.nextInt();
                sc.nextLine(); // consume the newline character

                if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == ' ') {
                    board[row][col] = player.getMarker();
                    validMove = true;
                } else {
                    System.out.println("This move is not valid. Try again.");
                }
            }
        }
        private boolean checkWin() {
            char marker = currentPlayer.getMarker();
            // Check rows and columns
            for (int i = 0; i < BOARD_SIZE; i++) {
                if ((board[i][0] == marker && board[i][1] == marker && board[i][2] == marker) ||
                    (board[0][i] == marker && board[1][i] == marker && board[2][i] == marker)) {
                    return true;
                }
            }
            // Check diagonals
            if ((board[0][0] == marker && board[1][1] == marker && board[2][2] == marker) ||
                (board[0][2] == marker && board[1][1] == marker && board[2][0] == marker)) {
                return true;
            }
            return false;
        }

        private boolean isBoardFull() {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (board[i][j] == ' ') {
                        return false;
                    }
                }
            }
            return true;
        }

        private void switchPlayer() {
            currentPlayer = (currentPlayer == p1) ? p2 : p1;
        }
    }
    */
    
}
