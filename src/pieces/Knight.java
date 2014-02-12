/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import janus.Janus;

/**
 *
 * @author Ismael
 */
public class Knight extends Piece {
    
    private int[][] regularMoves = {
        {-2,-1}, {-2,1}, {2,-1}, {2,1}, {-1,-2}, {-1,2}, {1,-2}, {1,2}
    };
    
    public Knight(boolean white) {
        this.white = white;
    }
    
    @Override
    public void refreshValidMoves() {
        validMoves.clear();
        int x = getPosition().getX();
        int y = getPosition().getY();
        for(int i = 0; i < regularMoves.length; i++){
            int dX = regularMoves[i][0];
            int dY = regularMoves[i][1];
            if(!isOutOfBounds(x + dX, y + dY)){
                Piece checkedPos = Janus.checkPosition(x + dX, y + dY);
                if(checkedPos == null || checkedPos.isWhite() != isWhite()){
                    validMoves.add(Janus.fetchPosition(x + dX, y + dY));
                }
            }
        }
    }
    
}
