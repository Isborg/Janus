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
public class Bishop extends Piece {
    
    public Bishop(boolean white) {
        this.white = white;
    }
    
    @Override
    protected void refreshValidMoves() {
        validMoves.clear();
        int x = getPosition().getX();
        int y = getPosition().getY();
        for(int i = 1; i <= 4; i++){
            int alpha = -1;
            int beta = -1;
            for(int j = 1; j <= 7; j++){
                Piece checkedPos = Janus.checkPosition(x + j * alpha, y + j * beta);
                if(checkedPos == null){
                    validMoves.add(Janus.fetchPosition(x + j * alpha, y + j * beta));
                }else{
                    if(checkedPos.isWhite() != isWhite()){
                        validMoves.add(Janus.fetchPosition(x + j * alpha, y + j * beta));
                    }
                    break;
                }
            }
            beta *= -1;
            if(beta == -1){
                alpha = 1;
            }
        }
    }
    
}
