/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import janus.Position;
import java.util.ArrayList;

/**
 *
 * @author Ismael
 */
public abstract class Piece {
    
    protected boolean white;
    protected boolean selected;
    protected ArrayList<Position> history = new ArrayList<Position>();
    protected ArrayList<Position> validMoves = new ArrayList<Position>();
    
    protected abstract void refreshValidMoves();

    public ArrayList<Position> getHistory() {
        return history;
    }
    
    public Position getPosition() {
        return history.get(0);
    }
    
    public void setPosition(Position pos) {
        history.add(0, pos);
    }
    
    public boolean isWhite() {
        return white;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
}
