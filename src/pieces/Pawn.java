/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import janus.Board;
import java.util.ArrayList;

/**
 *
 * @author Ismael
 */
public class Pawn extends Piece {
    
    public Pawn(boolean white) {
        this.white = white;
        positionRecord = new ArrayList<int[]>();
        setMoveList();
    }

    @Override
    protected void setMoveList() {
        moveList = new ArrayList<int[]>();
        for(int i = 0; i < 4; i++){
            moveList.add(new int[2]);
        }
        moveList.get(2)[0] = -1;
        moveList.get(3)[0] = 1;
        if(isWhite()){
            moveList.get(0)[1] = 1;
            moveList.get(1)[1] = 2;
            moveList.get(2)[1] = 1;
            moveList.get(3)[1] = 1;
        }else{
            moveList.get(0)[1] = -1;
            moveList.get(1)[1] = -2;
            moveList.get(2)[1] = -1;
            moveList.get(3)[1] = -1;
        }
    }
    //
}
