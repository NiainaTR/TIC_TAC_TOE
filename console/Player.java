package console;


public class Player{
	private String _name;
	private String _symbol;


	public Player(){}
	/**
	 *
	 * Setter playerName
	 */
	
	public void setPlayerName(String name){this._name = name;}

	/**
	 *
	 * Getter playerName
	 * return String
	 */

	public String getPlayerName() {return this._name;} 

	/**
	 *
	 * Setter playerSymbol
	 * 
	 */
	public void setPlayerSymbol(String symbol){this._symbol = symbol;}	

	/**
	 *
	 * Getter playerSymbol
	 * return String
	 *
	 */

	 public String getPlayerSymbol() {return this._symbol;}
}
