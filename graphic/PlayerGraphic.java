import java.awt.*;


class PlayerGraphic extends Player{

    Color _color;

    public PlayerGraphic(){
        super();
    }

    /*
    *   Setter playerColor 
    */

    public void setPlayerColor(Color color) {this._color = color;}  

    /**
     * 
     * Getter playerColor 
     * return Color 
     */

    public Color getPlayerColor() {return this._color;}
}