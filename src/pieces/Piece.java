/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import java.util.ArrayList;

/**
 *
 * @author Ismael
 */
public abstract class Piece {
    
    protected boolean white;
    protected boolean selected;
    protected ArrayList<int[]> positionRecord;
    protected ArrayList<int[]> moveList;
    
    protected abstract void setMoveList();
    
    public ArrayList<int[]> getMoveList() {
        return moveList;
    }
    
    public void swapSelected() {
        selected = !selected;
    }

    public int[] getPosition() {
        return positionRecord.get(0);
    }

    public void setPosition(int[] pos) {
        positionRecord.add(0, pos);
    }

    public boolean isWhite() {
        return white;
    }

    public boolean isSelected() {
        return selected;
    }
    
}
