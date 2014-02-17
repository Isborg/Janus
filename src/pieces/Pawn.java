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
    
    public Pawn(boolean white) {
        this.white = white;
    }
    
    // Para comer en passant hay que revisar que el doble avance enemigo fuera LA ÚLTIMA jugada hecha en la partida
    @Override
    public void refreshValidMoves() {
        validMoves.clear();
        int x = getPosition().getX();
        int y = getPosition().getY();
        int alpha = (white) ? 1 : -1;
        if(Janus.fetchPiece(x, y + alpha) == null){
            addValidMove(Janus.fetchPosition(x, y + alpha));
            if(history.size() == 1 && Janus.fetchPiece(x, y + 2 * alpha) == null){
                addValidMove(Janus.fetchPosition(x, y + 2 * alpha));
            }
        }
        for(int i = -1; i <= 1; i += 2){
            Piece sidePiece = Janus.fetchPiece(x + i, y);
            Piece diagonalPiece = Janus.fetchPiece(x + i, y + alpha);
            if(!isOutOfBounds(x + i, y + alpha) && 
                    ((diagonalPiece != null && diagonalPiece.isWhite() != white) ||
                    (sidePiece != null && sidePiece.getClass() == Pawn.class &&
                    ((isWhite() && y == 4) || (!isWhite() && y == 3)) &&
                    sidePiece.getHistory().size() == 2))){
                addValidMove(Janus.fetchPosition(x + i, y + alpha));
            }
        }
    }

    @Override
    public void refreshThreats() {
        threats.clear();
        int x = getPosition().getX();
        int y = getPosition().getY();
        int alpha = (white) ? 1 : -1;
        addThreat(Janus.fetchPosition(x - 1, y + alpha));
        addThreat(Janus.fetchPosition(x + 1, y + alpha));
    }
    
}
