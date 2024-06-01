import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TicTacToe extends JFrame {
      private JButton[][] cases = new JButton[3][3];
      private String[][] contenuCases= {{"-1","-1","-1","-1"},{"-1","-1","-1","-1"},{"-1","-1","-1","-1"}};
      private boolean joueur1=true;
      public TicTacToe(){
    	  this.setSize(400,400);
    	  this.setTitle("Tic-Tac-Toe!");
    	  this.setResizable(false);
    	  JMenuBar M = new JMenuBar();
    	  JMenu m1 = new JMenu("Partie");
    	  JMenuItem nouvelle=new JMenuItem("Nouvelle Partie");
    	  nouvelle.addActionListener(new nouvellePartie());
    	  JMenuItem abondonner = new JMenuItem("Abondonner");
    	  abondonner.addActionListener(new abondon());
    	  m1.add(nouvelle);
    	  m1.add(abondonner);
    	  M.add(m1);
    	  JMenuItem m2 = new JMenuItem("?");
    	  m2.addActionListener(new help());
    	  M.add(m2);
    	  this.setJMenuBar(M);
    	  JPanel jeu = new JPanel();
    	  jeu.setLayout(new GridLayout(3,3));
    	  for(int i=0;i<3;i++) {
    		  for(int j=0;j<3;j++) {
    			  cases[i][j] = new JButton();
    			  cases[i][j].setOpaque(true);
    			  cases[i][j].setBackground(Color.YELLOW);
    			  cases[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
    			  jeu.add(cases[i][j]);
    			  cases[i][j].addActionListener(new clic());
    		  }
    	  }
    	  this.getContentPane().add(jeu);
    	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	  this.setLocationRelativeTo(null);
    	  this.setVisible(true);
      }
      //action listener de la grille de jeu
      class clic implements ActionListener{
    	  public void actionPerformed(ActionEvent e) {
    	
    		  JButton currentCases= (JButton)e.getSource();
    		  if(joueur1 && currentCases.getText()=="") {
    			 currentCases.setForeground(Color.BLACK);
    			 currentCases.setText("<html><b><h1>O<h1></b></html>");
    			 contenuCases[getRow(currentCases,cases)][getColumn(currentCases,cases)]="O";
    		  }
    		  else if(!joueur1 && currentCases.getText()==""){
     			 currentCases.setForeground(Color.RED);
     			 currentCases.setText("<html><b><h1>X</h1></b></html>");
     			  //joueur1=true;
     			 contenuCases[getRow(currentCases,cases)][getColumn(currentCases,cases)]="X";
    		  }
  //a chaque changement dans la grille,nous verifions si l'une des conditions de victoire est remplie
    		  if(diagonale(contenuCases) || antiDiagonale(contenuCases) || ligne(contenuCases,getRow(currentCases,cases)) || colonne(contenuCases,getColumn(currentCases,cases))) {
        		  fin();
        	  }
    		  else{
    			  joueur1=!joueur1;
    		  }
    	  }
      }
      //action listener des menuItems
      class nouvellePartie implements ActionListener{
    	  public void actionPerformed(ActionEvent e) {
    		  JOptionPane confirmer = new JOptionPane();
    		  int option=confirmer.showConfirmDialog(null,"Voulez-vous commencer une nouvelle partie? La partie en cours sera perdue","Nouvelle Partie",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
    		  if(option==JOptionPane.YES_OPTION) {
    			  joueur1=true;
    			  for(int i=0;i<3;i++) {
    				  for(int j=0;j<3;j++) {
    					  cases[i][j].setText("");
    					  contenuCases[i][j]="-1";
    				  }
    			  }
    		  }
    	  }
      }

      class abondon implements ActionListener{
    	  public void actionPerformed(ActionEvent e) {
    		  JOptionPane confirmer= new JOptionPane();
    		  int option = confirmer.showConfirmDialog(null,"Voulez-vous abondonner la partie?","Abondonner",JOptionPane.YES_NO_OPTION,JOptionPane.
    				  QUESTION_MESSAGE);
    		  if(option==JOptionPane.YES_OPTION) {
    			  fin();
    		  } 
    	  }
      }
      class help implements ActionListener{
    	  public void actionPerformed(ActionEvent e) {
    		  JOptionPane regle = new JOptionPane();
    		  regle.showMessageDialog(null,"<html>Les règles du Tic-Tac-Toe sont simples: Les 2 joueurs rempliront une case par tour <b><h1>O</h1></b> pour le joueur 1<b><h1>X</h1></b> pour le joueur 2.\nLa victoire revient au premier à aligner 3 des symboles lui correspondant sur une ligne , une colonne , une diagonale ou une anti-diagonale.","Aide: Règles du jeu",JOptionPane.INFORMATION_MESSAGE);
    	  }
      }
  //pour obtenir la position de la case que l on a cliqué (ligne et colonne)
      private static int getRow(Object obj,Object[][] o) {
    	  int l=-1;
    	  for(int i=0;i<3;i++) {
    		  for(int j=0;j<3;j++) {
    			  if(obj.equals(o[i][j])) {
    				  l=i;
    				  break;
    			  }
    		  }
    	  }
    	  return l;
      }
      private static int getColumn(Object obj,Object[][] o) {
    	  int l=-1;
    	  for(int i=0;i<3;i++) {
    		  for(int j=0;j<3;j++) {
    			  if(obj.equals(o[i][j])) {
    				  l=j;
    				  break;
    			  }
    		  }
    	  }
    	  return l;
      }
    //Les conditions de victoire a verifier
      private static boolean diagonale(String[][] c){
         //retourne vrai si les elements de la diagonale sont tous les 3 pareils
    	  boolean b=false;
    	  if(c[0][0]==c[1][1] && c[2][2]==c[0][0] && c[0][0]!="-1") {
    		  b=true;
    	  }
    	  return b;
      }
      private static boolean antiDiagonale(String[][] c) {
    	  //retourne vrai si les elements de l'anti-diagonale sont tous les 3 pareils
    	  boolean b=false;
    	  if(c[2][0]==c[1][1] && c[0][2]==c[2][0] && c[2][0]!="-1") {
    		  b=true;
    	  }
    	  return b;
      }
      private static boolean ligne(String[][] c,int l) {
    	     boolean b=false;
    	     if(c[l][0]==c[l][1] && c[l][2]==c[l][0] && c[l][0]!="-1") {
    	    	 b=true;
    	     }
    	     return b;
      }
      private static boolean colonne(String[][] cs,int c) {
 	     boolean b=false;
 	     if(cs[0][c]==cs[1][c] && cs[2][c]==cs[0][c] && cs[0][c]!="-1") {
 	    	 b=true;
 	     }
 	     return b;
   }
  //message afficher dès qu'une des conditions ci-dessus est remplie, representant la fin de la partie
      public void fin() {
    	  if(joueur1) {
			  JOptionPane op1 = new JOptionPane();
			  op1.showMessageDialog(null,"Victoire du joueur 1!","Fin de la partie!",JOptionPane.INFORMATION_MESSAGE);
		  }
		  else {
			  JOptionPane op2 = new JOptionPane();
			  op2.showMessageDialog(null, "Victoire du joueur 2!","Fin de la partie!",JOptionPane.INFORMATION_MESSAGE);
		  }
      }
      public static void main(String[] args) {
    	  new TicTacToe();
      }
 }