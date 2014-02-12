/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package janus;

import java.io.Console;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import pieces.*;

/**
 *
 * @author Ismael
 */
public class Janus {

    private static Janus INSTANCE = new Janus();
    private static HashMap<Position,Piece> board = new HashMap<Position, Piece>();
    private static Position[] positions = {
        new Position(0,0), new Position(1,0), new Position(2,0), new Position(3,0),
        new Position(4,0), new Position(5,0), new Position(6,0), new Position(7,0),
        new Position(0,1), new Position(1,1), new Position(2,1), new Position(3,1),
        new Position(4,1), new Position(5,1), new Position(6,1), new Position(7,1),
        new Position(0,2), new Position(1,2), new Position(2,2), new Position(3,2),
        new Position(4,2), new Position(5,2), new Position(6,2), new Position(7,2),
        new Position(0,3), new Position(1,3), new Position(2,3), new Position(3,3),
        new Position(4,3), new Position(5,3), new Position(6,3), new Position(7,3),
        new Position(0,4), new Position(1,4), new Position(2,4), new Position(3,4),
        new Position(4,4), new Position(5,4), new Position(6,4), new Position(7,4),
        new Position(0,5), new Position(1,5), new Position(2,5), new Position(3,5),
        new Position(4,5), new Position(5,5), new Position(6,5), new Position(7,5),
        new Position(0,6), new Position(1,6), new Position(2,6), new Position(3,6),
        new Position(4,6), new Position(5,6), new Position(6,6), new Position(7,6),
        new Position(0,7), new Position(1,7), new Position(2,7), new Position(3,7),
        new Position(4,7), new Position(5,7), new Position(6,7), new Position(7,7)
    };

    public static HashMap<Position, Piece> getBoard() {
        return board;
    }
    
    private Janus() {}
    
    public static Janus getInstance() {
        return INSTANCE;
    }
    
    /* +---+-----+-----+-----+-----+-----+-----+-----+-----+
     * |   |  A  |  B  |  C  |  D  |  E  |  F  |  G  |  H  |
     * +---+-----+-----+-----+-----+-----+-----+-----+-----+
     * | 8 | 0,7 | 1,7 | 2,7 | 3,7 | 4,7 | 5,7 | 6,7 | 7,7 |
     * | 7 | 0,6 | 1,6 | 2,6 | 3,6 | 4,6 | 5,6 | 6,6 | 7,6 |
     * | 6 | 0,5 | 1,5 | 2,5 | 3,5 | 4,5 | 5,5 | 6,5 | 7,5 |
     * | 5 | 0,4 | 1,4 | 2,4 | 3,4 | 4,4 | 5,4 | 6,4 | 7,4 |
     * | 4 | 0,3 | 1,3 | 2,3 | 3,3 | 4,3 | 5,3 | 6,3 | 7,3 |
     * | 3 | 0,2 | 1,2 | 2,2 | 3,2 | 4,2 | 5,2 | 6,2 | 7,2 |
     * | 2 | 0,1 | 1,1 | 2,1 | 3,1 | 4,1 | 5,1 | 6,1 | 7,1 |
     * | 1 | 0,0 | 1,0 | 2,0 | 3,0 | 4,0 | 5,0 | 6,0 | 7,0 |
     * +---+-----+-----+-----+-----+-----+-----+-----+-----+
     * 
     * +---+----+----+----+----+----+----+----+----+
     * |   | A  | B  | C  | D  | E  | F  | G  | H  |
     * +---+----+----+----+----+----+----+----+----+
     * | 8 | 56 | 57 | 58 | 59 | 60 | 61 | 62 | 63 |
     * | 7 | 48 | 49 | 50 | 51 | 52 | 53 | 54 | 55 |
     * | 6 | 40 | 41 | 42 | 43 | 44 | 45 | 46 | 47 |
     * | 5 | 32 | 33 | 34 | 35 | 36 | 37 | 38 | 39 |
     * | 4 | 24 | 25 | 26 | 27 | 28 | 29 | 30 | 31 |
     * | 3 | 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 |
     * | 2 |  8 |  9 | 10 | 11 | 12 | 13 | 14 | 15 |
     * | 1 |  0 |  1 |  2 |  3 |  4 |  5 |  6 |  7 |
     * +---+----+----+----+----+----+----+----+----+
     */
    
    public static Piece checkPosition(int x, int y) {
        return board.get(fetchPosition(x, y));
    }
    
    public static Position fetchPosition(int x, int y) {
        return positions[y * 8 + x];
    }
    
    public static void main(String[] args) throws IOException {
        Tester.setUpStartingBoard();
        Tester.printBoard();
        Tester.commandListener();
    }
    
}
