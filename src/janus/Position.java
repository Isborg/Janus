/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package janus;

import java.util.ArrayList;
import pieces.Piece;

/**
 *
 * @author Ismael
 */
public class Position {
    
    private int x;
    private int y;
    private ArrayList<Piece> valids = new ArrayList<Piece>();
    private ArrayList<Piece> threats = new ArrayList<Piece>();

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Piece> getValids() {
        return valids;
    }

    public ArrayList<Piece> getThreats() {
        return threats;
    }
    
}
