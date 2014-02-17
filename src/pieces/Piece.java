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
public abstract class Piece {
    
    protected boolean white;
    protected ArrayList<Position> history = new ArrayList<Position>();
    protected ArrayList<Position> validMoves = new ArrayList<Position>();
    protected ArrayList<Position> threats = new ArrayList<Position>();
    
    public abstract void refreshValidMoves();
    public abstract void refreshThreats();
    
    public void move(int x, int y){
        Position pos = Janus.fetchPosition(x, y);
        if(validMoves.contains(pos)){
            Janus.getBoard().put(getPosition(), null);
            setPosition(pos);
            Janus.getBoard().put(pos, this);
            refreshValidMoves();
            refreshThreats();
            for (Piece piece : pos.getValids()) {
                piece.refreshValidMoves();
                piece.refreshThreats();
            }
            for (Piece piece : pos.getThreats()) {
                piece.refreshValidMoves();
                piece.refreshThreats();
            }
        }
    }
    
    protected void addValidMove(Position position){
        validMoves.add(position);
        position.getValids().add(this);
    }
    
    protected void addThreat(Position position){
        threats.add(position);
        position.getThreats().add(this);
        Piece piece = Janus.fetchPiece(position.getX(), position.getY());
        if(piece != null && piece instanceof King){
            if(piece.isWhite())
                Janus.setWhiteCheck(true);
            else
                Janus.setBlackCheck(true);
        }
    }
    
    protected void clearValids(){
        for (Position position : getValidMoves()) {
            position.getValids().remove(this);
        }
        validMoves.clear();
    }
    
    protected void clearThreats(){
        for (Position position : getThreats()) {
            position.getThreats().remove(this);
        }
        threats.clear();
    }
    
    protected boolean isOutOfBounds(int x, int y){
        if(x < 0 || x > 7 || y < 0 || y > 7)
            return true;
        return false;
    }
    
    public ArrayList<Position> getThreats() {
        return threats;
    }

    public ArrayList<Position> getValidMoves() {
        return validMoves;
    }

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
    
}
