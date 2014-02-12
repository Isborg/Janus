/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package janus;

import static janus.Janus.checkPosition;
import static janus.Janus.fetchPosition;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.util.Scanner;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

/**
 *
 * @author Ismael
 */
public class Tester {
    
    public static void clearConsole(){
        // Simulate a human pressing CTRL + L to clear
        try {
            Robot rob = new Robot();
            rob.keyPress(17); // Holds CTRL key.
            rob.keyPress(76); // Holds L key.
            rob.keyRelease(17); // Releases CTRL key.
            rob.keyRelease(76); // Releases L key.
        } catch (AWTException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public static void commandListener() throws IOException, InterruptedException {
        Scanner reader = new Scanner(System.in);
        String cmd = reader.nextLine();
        clearConsole();
        Thread.sleep(50);
        /*
         * Commands:
         * board - Prints the current board
         * valid x y - Shows the valid moves for the piece in (x,y)
         * insert color piece x y - Inserts a piece in (x,y)
         * ***select (activate valid moves)
         * ***move
         */
        try{
            if(cmd.substring(0, 5).equals("board") && cmd.length() == 5){
                printBoard();
            }else if(cmd.substring(0, 5).equals("valid")){
                try{
                    printValidMoves(Integer.parseInt(cmd.charAt(6)+""), Integer.parseInt(cmd.charAt(8)+""));
                }catch(Exception e){
                    printBoard();
                    System.out.println("Invalid command");
                }
            }else if(cmd.substring(0, 6).equals("insert")){
                try{
                    Piece piece = null;
                    boolean white = false;
                    if(cmd.substring(7, 12).equals("white")){
                        white = true;
                    }else if(!cmd.substring(7, 12).equals("black")){
                        throw new Exception();
                    }
                    if(cmd.substring(13, 17).equals("pawn")){
                        piece = new Pawn(white);
                        piece.setPosition(Janus.fetchPosition(
                                Integer.parseInt(cmd.charAt(18)+""), Integer.parseInt(cmd.charAt(20)+"")));
                    }else if(cmd.substring(13, 19).equals("knight")){
                        piece = new Knight(white);
                        piece.setPosition(Janus.fetchPosition(
                                Integer.parseInt(cmd.charAt(20)+""), Integer.parseInt(cmd.charAt(22)+"")));
                    }else if(cmd.substring(13, 19).equals("bishop")){
                        piece = new Bishop(white);
                        piece.setPosition(Janus.fetchPosition(
                                Integer.parseInt(cmd.charAt(20)+""), Integer.parseInt(cmd.charAt(22)+"")));
                    }else if(cmd.substring(13, 17).equals("rook")){
                        piece = new Rook(white);
                        piece.setPosition(Janus.fetchPosition(
                                Integer.parseInt(cmd.charAt(18)+""), Integer.parseInt(cmd.charAt(20)+"")));
                    }else if(cmd.substring(13, 17).equals("king")){
                        piece = new King(white);
                        piece.setPosition(Janus.fetchPosition(
                                Integer.parseInt(cmd.charAt(18)+""), Integer.parseInt(cmd.charAt(20)+"")));
                    }else if(cmd.substring(13, 18).equals("queen")){
                        piece = new Queen(white);
                        piece.setPosition(Janus.fetchPosition(
                                Integer.parseInt(cmd.charAt(19)+""), Integer.parseInt(cmd.charAt(21)+"")));
                    }
                    Janus.getBoard().put(piece.getPosition(), piece);
                    printBoard();
                }catch(Exception e){
                    printBoard();
                    System.out.println("Invalid command");
                }
            }else{
                printBoard();
                System.out.println("Invalid command");
            }
        }catch(Exception e){
            printBoard();
            System.out.println("Invalid command");
        }
        commandListener();
    }
    
    public static void printBoard(){
        System.out.println("+---+-------+-------+-------+-------+-------+-------+-------+-------+");
        System.out.println("|   |   A   |   B   |   C   |   D   |   E   |   F   |   G   |   H   |");
        System.out.println("+---+-------+-------+-------+-------+-------+-------+-------+-------+");
        System.out.println(rowString(7));
        System.out.println(rowString(6));
        System.out.println(rowString(5));
        System.out.println(rowString(4));
        System.out.println(rowString(3));
        System.out.println(rowString(2));
        System.out.println(rowString(1));
        System.out.println(rowString(0));
        System.out.println("+---+-------+-------+-------+-------+-------+-------+-------+-------+");
    }
    
    public static String rowString(int i){
        String blank = "       |";
        String wPawn = " wPawn |";
        String wKnight = "wKnight|";
        String wBishop = "wBishop|";
        String wRook = " wRook |";
        String wQueen = " wQueen|";
        String wKing = " wKing |";
        String bPawn = " bPawn |";
        String bKnight = "bKnight|";
        String bBishop = "bBishop|";
        String bRook = " bRook |";
        String bQueen = " bQueen|";
        String bKing = " bKing |";
        String row = "| " + (i + 1) + " |";
        for(int j = 0; j <= 7; j++){
            Piece p = Janus.checkPosition(j, i);
            if(p == null){
                row += blank;
            }else if(p.getClass().equals(Pawn.class)){
                row += (p.isWhite()) ? wPawn : bPawn;
            }else if(p.getClass().equals(Knight.class)){
                row += (p.isWhite()) ? wKnight : bKnight;
            }else if(p.getClass().equals(Bishop.class)){
                row += (p.isWhite()) ? wBishop : bBishop;
            }else if(p.getClass().equals(Rook.class)){
                row += (p.isWhite()) ? wRook : bRook;
            }else if(p.getClass().equals(Queen.class)){
                row += (p.isWhite()) ? wQueen : bQueen;
            }else if(p.getClass().equals(King.class)){
                row += (p.isWhite()) ? wKing : bKing;
            }
        }
        return row;
    }
    
    public static void printValidMoves(int x, int y){
        Piece piece = Janus.checkPosition(x, y);
        piece.refreshValidMoves();
        if(piece != null){
            System.out.println("+---+-------+-------+-------+-------+-------+-------+-------+-------+");
            System.out.println("|   |   A   |   B   |   C   |   D   |   E   |   F   |   G   |   H   |");
            System.out.println("+---+-------+-------+-------+-------+-------+-------+-------+-------+");
            System.out.println(validRowString(7, piece));
            System.out.println(validRowString(6, piece));
            System.out.println(validRowString(5, piece));
            System.out.println(validRowString(4, piece));
            System.out.println(validRowString(3, piece));
            System.out.println(validRowString(2, piece));
            System.out.println(validRowString(1, piece));
            System.out.println(validRowString(0, piece));
            System.out.println("+---+-------+-------+-------+-------+-------+-------+-------+-------+");
            System.out.println("Valid moves for (" + x + "," + y + ")");
        }
    }
    
    public static String validRowString(int i, Piece piece){
        String valid = "   X   |";
        String blank = "       |";
        String wPawn = " wPawn |";
        String wKnight = "wKnight|";
        String wBishop = "wBishop|";
        String wRook = " wRook |";
        String wQueen = " wQueen|";
        String wKing = " wKing |";
        String bPawn = " bPawn |";
        String bKnight = "bKnight|";
        String bBishop = "bBishop|";
        String bRook = " bRook |";
        String bQueen = " bQueen|";
        String bKing = " bKing |";
        String row = "| " + (i + 1) + " |";
        for(int j = 0; j <= 7; j++){
            boolean isValid = false;
            for (Position position : piece.getValidMoves()) {
                if(position.getX() == j && position.getY() == i)
                    isValid = true;
            }
            Piece p = Janus.checkPosition(j, i);
            if(isValid){
                row += valid;
            }else if(p == null){
                row += blank;
            }else if(p.getClass().equals(Pawn.class)){
                row += (p.isWhite()) ? wPawn : bPawn;
            }else if(p.getClass().equals(Knight.class)){
                row += (p.isWhite()) ? wKnight : bKnight;
            }else if(p.getClass().equals(Bishop.class)){
                row += (p.isWhite()) ? wBishop : bBishop;
            }else if(p.getClass().equals(Rook.class)){
                row += (p.isWhite()) ? wRook : bRook;
            }else if(p.getClass().equals(Queen.class)){
                row += (p.isWhite()) ? wQueen : bQueen;
            }else if(p.getClass().equals(King.class)){
                row += (p.isWhite()) ? wKing : bKing;
            }
        }
        return row;
    }
    
    public static void setUpStartingBoard(){
        for(int i = 0; i < 8; i++){
            Pawn wPawn = new Pawn(true);
            wPawn.setPosition(Janus.fetchPosition(i, 1));
            Janus.getBoard().put(wPawn.getPosition(), wPawn);
            Pawn bPawn = new Pawn(false);
            bPawn.setPosition(Janus.fetchPosition(i, 6));
            Janus.getBoard().put(bPawn.getPosition(), bPawn);
        }
        for(int i = 0; i < 8; i++){
            Piece wPiece = null;
            Piece bPiece = null;
            switch(i){
                case 0:
                    wPiece = new Rook(true);
                    bPiece = new Rook(false);
                    break;
                case 1:
                    wPiece = new Knight(true);
                    bPiece = new Knight(false);
                    break;
                case 2:
                    wPiece = new Bishop(true);
                    bPiece = new Bishop(false);
                    break;
                case 3:
                    wPiece = new Queen(true);
                    bPiece = new Queen(false);
                    break;
                case 4:
                    wPiece = new King(true);
                    bPiece = new King(false);
                    break;
                case 5:
                    wPiece = new Bishop(true);
                    bPiece = new Bishop(false);
                    break;
                case 6:
                    wPiece = new Knight(true);
                    bPiece = new Knight(false);
                    break;
                case 7:
                    wPiece = new Rook(true);
                    bPiece = new Rook(false);
                    break;
            }
            wPiece.setPosition(Janus.fetchPosition(i, 0));
            Janus.getBoard().put(wPiece.getPosition(), wPiece);
            bPiece.setPosition(Janus.fetchPosition(i, 7));
            Janus.getBoard().put(bPiece.getPosition(), bPiece);
        }
    }
    
}
