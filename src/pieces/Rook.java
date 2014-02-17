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
public class Rook extends Piece {
    
    public Rook(boolean white) {
        this.white = white;
    }
    
    @Override
    public void refreshValidMoves() {
        validMoves.clear();
        int x = getPosition().getX();
        int y = getPosition().getY();
        int alpha = -1;
        int beta = 0;
        for(int i = 1; i <= 4; i++){
            for(int j = 1; j <= 7; j++){
                if(!isOutOfBounds(x + j * alpha, y + j * beta)){
                    Piece checkedPos = Janus.fetchPiece(x + j * alpha, y + j * beta);
                    if(checkedPos == null){
                        addValidMove(Janus.fetchPosition(x + j * alpha, y + j * beta));
                    }else{
                        if(checkedPos.isWhite() != white){
                            addValidMove(Janus.fetchPosition(x + j * alpha, y + j * beta));
                        }
                        break;
                    }
                }else{
                    break;
                }
            }
            beta = alpha;
            alpha = (alpha != 1) ? (alpha + 1) : (alpha - 1);
            /* +-------+------+
             * | alpha | beta |
             * +-------+------+
             * |    -1 |    0 |
             * |     0 |   -1 |
             * |     1 |    0 |
             * |     0 |    1 |
             * +-------+------+
             */
        }
    }

    @Override
    public void refreshThreats() {
        threats.clear();
        int x = getPosition().getX();
        int y = getPosition().getY();
        int alpha = -1;
        int beta = 0;
        for(int i = 1; i <= 4; i++){
            for(int j = 1; j <= 7; j++){
                if(!isOutOfBounds(x + j * alpha, y + j * beta)){
                    Piece checkedPos = Janus.fetchPiece(x + j * alpha, y + j * beta);
                    if(checkedPos == null){
                        addThreat(Janus.fetchPosition(x + j * alpha, y + j * beta));
                    }else{
                        if(checkedPos.isWhite() != white){
                            addThreat(Janus.fetchPosition(x + j * alpha, y + j * beta));
                        }
                        break;
                    }
                }else{
                    break;
                }
            }
            beta = alpha;
            alpha = (alpha != 1) ? (alpha + 1) : (alpha - 1);
            /* +-------+------+
             * | alpha | beta |
             * +-------+------+
             * |    -1 |    0 |
             * |     0 |   -1 |
             * |     1 |    0 |
             * |     0 |    1 |
             * +-------+------+
             */
        }
    }
    
}