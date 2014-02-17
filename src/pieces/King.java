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
public class King extends Piece {
    
    public King(boolean white) {
        this.white = white;
    }
    
    // ARREGLAR, TERMINAR, ENROQUES
    @Override
    public void refreshValidMoves() {
        validMoves.clear();
        int x = getPosition().getX();
        int y = getPosition().getY();
        int alpha = -1;
        for(int i = 1; i <= 2; i++){
            checkValidMove(x + alpha, y);
            checkValidMove(x, y + alpha);
            checkValidMove(x + alpha, y - 1);
            checkValidMove(x + alpha, y + 1);
            alpha = 1;
        }
        if(history.size() == 1 && Janus.fetchPiece(0, y).getHistory().size() == 1 &&
                Janus.fetchPiece(1, y) == null && Janus.fetchPiece(2, y) == null &&
                Janus.fetchPiece(3, y) == null /*1,y 2,y 3,y no amenazados, no en jaque*/){
            addValidMove(Janus.fetchPosition(2, y));
        }
        if(history.size() == 1 && Janus.fetchPiece(7, y).getHistory().size() == 1 &&
                Janus.fetchPiece(6, y) == null && Janus.fetchPiece(5, y) == null
                /*6,y 5,y no amenazados, no en jaque*/){
            addValidMove(Janus.fetchPosition(6, y));
        }
    }
    
    private void checkValidMove(int x, int y){
        Piece checkedPos = Janus.fetchPiece(x, y);
        if(!isOutOfBounds(x, y) &&
                (checkedPos == null || checkedPos.isWhite() != white)){
            addValidMove(Janus.fetchPosition(x, y));
        }
    }

    @Override
    public void refreshThreats() {
        threats.clear();
        int x = getPosition().getX();
        int y = getPosition().getY();
        int alpha = -1;
        for(int i = 1; i <= 2; i++){
            checkThreat(x + alpha, y);
            checkThreat(x, y + alpha);
            checkThreat(x + alpha, y - 1);
            checkThreat(x + alpha, y + 1);
            alpha = 1;
        }
    }
    
    private void checkThreat(int x, int y){
        if(!isOutOfBounds(x, y))
            addThreat(Janus.fetchPosition(x, y));
    }
    
}
