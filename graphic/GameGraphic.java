import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class GameGraphic extends JFrame{
    private static int BOARD_SIZE = 7;
    private Case[][] boardInterface; 
    private String[][] boardLogic;
    private PlayerGraphic p1;
    private PlayerGraphic p2;
    private PlayerGraphic currentPlayer;
    private JPanel gameInterface;
    private JPanel gameInterfacePlayer;
    private JPanel gameInterfaceGrid;
    private boolean isGameOn;

    public GameGraphic(){
        this.isGameOn = false;
        // Initialisation du boardLogic
        boardLogic = new String[BOARD_SIZE][BOARD_SIZE];
        boardInterface = new Case[BOARD_SIZE][BOARD_SIZE];
        gameInterface = new JPanel();
        gameInterfacePlayer = new JPanel(new GridLayout(2 , 1));
        gameInterfaceGrid = new JPanel();
        gameInterface.setLayout(new BorderLayout());

        gameInterfacePlayer.setPreferredSize(new Dimension(500, 50));
        gameInterfaceGrid.setLayout(new GridLayout(BOARD_SIZE , BOARD_SIZE));
    }

    public void start(){
        this.isGameOn = true;
        //Initialize window
        this.setSize(500,500);
        this.setTitle("Tic Tac Toe");
        this.setResizable(false);
        
        // Initialize tic tac toe game 
        
        //PlayerGraphic 
        p1 = new PlayerGraphic();
        p1.setPlayerName("Joueur 1");
        p1.setPlayerSymbol("O");
        p1.setPlayerColor(Color.BLACK);

        p2 = new PlayerGraphic();
        p2.setPlayerName("Joueur 2");
        p2.setPlayerSymbol("X");
        p2.setPlayerColor(Color.RED);

        currentPlayer = getRandomPlayer();  

        
        //Game

        //Logic side
        for(int i = 0 ; i < BOARD_SIZE ; i++){
            for(int j = 0 ; j < BOARD_SIZE ; j++){
                boardLogic[i][j] = " ";
            }
        }

        //Interface side
        for(int i = 0 ; i < BOARD_SIZE ; i++){
            for(int j = 0 ; j < BOARD_SIZE ; j++){
                boardInterface[i][j] = new Case();
                boardInterface[i][j].row = i;
                boardInterface[i][j].column = j;
                boardInterface[i][j].setText("<html><h1>"+" "+"</h1></html>");
                boardInterface[i][j].setOpaque(true);
                boardInterface[i][j].setBackground(Color.WHITE);
                boardInterface[i][j].addActionListener(new ButtonClick());
                gameInterfaceGrid.add(boardInterface[i][j]);
            }
        }

        JLabel label1 = new JLabel(p1.getPlayerName() + " : " + p1.getPlayerSymbol());
        JLabel label2 = new JLabel(p2.getPlayerName() + " : " + p2.getPlayerSymbol());

        gameInterfacePlayer.add(label1);
        gameInterfacePlayer.add(label2);


        gameInterface.add(gameInterfacePlayer, BorderLayout.NORTH);
        gameInterface.add(gameInterfaceGrid, BorderLayout.CENTER);

        this.getContentPane().add(gameInterface);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        JOptionPane.showMessageDialog(null , "Le joueur " + currentPlayer.getPlayerName() + " : " + currentPlayer.getPlayerSymbol() + " commence le jeu ");
        
    }

    class ButtonClick implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Case currentCase = (Case) e.getSource();
            if(boardLogic[currentCase.row][currentCase.column] == " "){
                currentCase.setForeground(currentPlayer.getPlayerColor());  
                currentCase.setText("<html><h1>"+currentPlayer.getPlayerSymbol()+"</h1></html>");
                boardLogic[currentCase.row][currentCase.column] = currentPlayer.getPlayerSymbol();
                isGameOn = !isGameWin() && !isBoardFull();
                if(isGameOn) switchPlayer();            
                else displayGameResult();
            }   
        }   
    }

    private void switchPlayer(){currentPlayer = currentPlayer == p1 ? p2 : p1;}
	
    private boolean isBoardFull (){
		for(int i = 0 ; i < BOARD_SIZE ; i++){
			for(int j = 0 ; j < BOARD_SIZE ; j++){
			   if(boardLogic[i][j] == " ") return false; 
			}	
		}
        return true;
	}

    private boolean isGameWin() {
        String symbol = currentPlayer.getPlayerSymbol();

        for (int i = 0; i < BOARD_SIZE; i++) {
            boolean rowWin = true;
            boolean colWin = true;
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (!boardLogic[i][j].equals(symbol)) {
                    rowWin = false;
                }
                if (!boardLogic[j][i].equals(symbol)) {
                    colWin = false;
                }
            }
            if (rowWin || colWin) {
                return true;
            }
        }
        boolean mainDiagonalWin = true;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (!boardLogic[i][i].equals(symbol)) {
                mainDiagonalWin = false;
                break;
            }
        }
        if (mainDiagonalWin) {
            return true;
        }

        boolean secondaryDiagonalWin = true;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (!boardLogic[i][BOARD_SIZE - 1 - i].equals(symbol)) {
                secondaryDiagonalWin = false;
                break;
            }
        }
        if (secondaryDiagonalWin) {
            return true;
        }

        return false;
    }

    private PlayerGraphic getRandomPlayer(){
		Random random = new Random();
		return random.nextInt(2) == 0 ? p1 : p2;
	}
    
    private void displayGameResult() {
        if(isGameWin()){
            JOptionPane.showMessageDialog(null , "Le joueur " + currentPlayer.getPlayerName() + " : " + currentPlayer.getPlayerSymbol() + " a gagné !! ");
            restartGame();
        }
        else{
            JOptionPane.showMessageDialog(null , "Partie null !! ");
            restartGame();
        }
    }

    private void restartGame(){
        
        //Reinitialize game
        
        //Logic side
        for(int i = 0 ; i < BOARD_SIZE ; i++){
            for(int j = 0 ; j < BOARD_SIZE ; j++){
                boardLogic[i][j] = " ";
            }
        }

        //Interface side
        for(int i = 0 ; i < BOARD_SIZE ; i++){
            for(int j = 0 ; j < BOARD_SIZE ; j++){
                boardInterface[i][j].setText("<html><h1>"+" "+"</h1></html>");
            }
        }

        currentPlayer = getRandomPlayer();  

        JOptionPane.showMessageDialog(null , "Le joueur " + currentPlayer.getPlayerName() + " : " + currentPlayer.getPlayerSymbol() + " commence le jeu ");
        
    }
}
