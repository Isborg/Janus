/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import janus.Janus;
import janus.Position;
import java.util.ArrayList;

/**
 *
 * @author Ismael
 */
public class Pawn extends Piece {
    
    private int[][] regularMoves;
    
    public Pawn(boolean white) {
        this.white = white;
        prepRegularMoves();
    }

    protected void prepRegularMoves() {
        if(white){
            int[][] moveArray = {
                {0,1}, {0,2}, {-1,1}, {1,1}
            };
            regularMoves = moveArray;
        }else{
            int[][] moveArray = {
                {0,-1}, {0,-2}, {-1,-1}, {1,-1}
            };
            regularMoves = moveArray;
        }
    }
    
    // ARREGLAR, TERMINAR
    @Override
    protected void refreshValidMoves() {
        validMoves.clear();
        if(white){
            for(int i = 0; i < regularMoves.length; i++){
                int x = getPosition().getX();
                int y = getPosition().getY();
                int dX = regularMoves[i][0];
                int dY = regularMoves[i][1];
                if(dY == 2 && Janus.checkPosition(x, y + dY) == null
                        && Janus.checkPosition(x, y + dY) == null){
                    validMoves.add(Janus.fetchPosition(x, y + dY));
                }else{
                    switch(dX){
                        case 0:
                            if(Janus.checkPosition(x, y + dY) == null){
                                validMoves.add(Janus.fetchPosition(x, y + dY));
                            }
                            break;
                        case -1:
                            if(!Janus.checkPosition(x + dX, y + dY).isWhite()){
                                validMoves.add(Janus.fetchPosition(x + dX, y + dY));
                            }
                            //en passant
                            break;
                        case 1:
                            break;
                    }
                }
            }
        }
    }
    
}
