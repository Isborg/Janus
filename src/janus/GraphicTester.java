/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package janus;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
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
public class GraphicTester extends javax.swing.JFrame {
    
    private SquareButton[] squares = {
        new SquareButton(0, 0, this), new SquareButton(1, 0, this), new SquareButton(2, 0, this), new SquareButton(3, 0, this),
        new SquareButton(4, 0, this), new SquareButton(5, 0, this), new SquareButton(6, 0, this), new SquareButton(7, 0, this),
        new SquareButton(0, 1, this), new SquareButton(1, 1, this), new SquareButton(2, 1, this), new SquareButton(3, 1, this),
        new SquareButton(4, 1, this), new SquareButton(5, 1, this), new SquareButton(6, 1, this), new SquareButton(7, 1, this),
        new SquareButton(0, 2, this), new SquareButton(1, 2, this), new SquareButton(2, 2, this), new SquareButton(3, 2, this),
        new SquareButton(4, 2, this), new SquareButton(5, 2, this), new SquareButton(6, 2, this), new SquareButton(7, 2, this),
        new SquareButton(0, 3, this), new SquareButton(1, 3, this), new SquareButton(2, 3, this), new SquareButton(3, 3, this),
        new SquareButton(4, 3, this), new SquareButton(5, 3, this), new SquareButton(6, 3, this), new SquareButton(7, 3, this),
        new SquareButton(0, 4, this), new SquareButton(1, 4, this), new SquareButton(2, 4, this), new SquareButton(3, 4, this),
        new SquareButton(4, 4, this), new SquareButton(5, 4, this), new SquareButton(6, 4, this), new SquareButton(7, 4, this),
        new SquareButton(0, 5, this), new SquareButton(1, 5, this), new SquareButton(2, 5, this), new SquareButton(3, 5, this),
        new SquareButton(4, 5, this), new SquareButton(5, 5, this), new SquareButton(6, 5, this), new SquareButton(7, 5, this),
        new SquareButton(0, 6, this), new SquareButton(1, 6, this), new SquareButton(2, 6, this), new SquareButton(3, 6, this),
        new SquareButton(4, 6, this), new SquareButton(5, 6, this), new SquareButton(6, 6, this), new SquareButton(7, 6, this),
        new SquareButton(0, 7, this), new SquareButton(1, 7, this), new SquareButton(2, 7, this), new SquareButton(3, 7, this),
        new SquareButton(4, 7, this), new SquareButton(5, 7, this), new SquareButton(6, 7, this), new SquareButton(7, 7, this)
    };

    private class SquareButton extends java.awt.Button {
        private int x;
        private int y;
        private GraphicTester board;
        
        public SquareButton(int x, int y, GraphicTester board){
            this.x = x;
            this.y = y;
            this.board = board;
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Piece p = Janus.fetchPiece(getX(), getY());
                        if(Janus.getSelectedPiece() != null){
                            Janus.getSelectedPiece().move(getX(), getY());
                            Janus.setSelectedPiece(null);
                            updateBoard();
                        }else{
                            Janus.setSelectedPiece(p);
                            p.refreshValidMoves();
                            showValidMoves(p);
                        }
                    }catch(Exception ex){
                        System.out.println(ex.toString());
                    }
                }
            });
        }
        
        public int getX(){
            return x;
        }
        
        public int getY(){
            return y;
        }
        
        public GraphicTester getBoard(){
            return board;
        }
    }
        
    private void updateBoard(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Piece p = Janus.fetchPiece(i, j);
                if(p == null){
                    fetchSquare(i, j).setLabel("");
                }else if(p instanceof Pawn){
                    if(p.isWhite())
                        fetchSquare(i, j).setLabel("wPawn");
                    else
                        fetchSquare(i, j).setLabel("bPawn");
                }else if(p instanceof Knight){
                    if(p.isWhite())
                        fetchSquare(i, j).setLabel("wKnight");
                    else
                        fetchSquare(i, j).setLabel("bKnight");
                }else if(p instanceof Bishop){
                    if(p.isWhite())
                        fetchSquare(i, j).setLabel("wBishop");
                    else
                        fetchSquare(i, j).setLabel("bBishop");
                }else if(p instanceof Rook){
                    if(p.isWhite())
                        fetchSquare(i, j).setLabel("wRook");
                    else
                        fetchSquare(i, j).setLabel("bRook");
                }else if(p instanceof Queen){
                    if(p.isWhite())
                        fetchSquare(i, j).setLabel("wQueen");
                    else
                        fetchSquare(i, j).setLabel("bQueen");
                }else if(p instanceof King){
                    if(p.isWhite())
                        fetchSquare(i, j).setLabel("wKing");
                    else
                        fetchSquare(i, j).setLabel("bKing");
                }
                fetchSquare(i, j).setBackground(Color.LIGHT_GRAY);
            }
        }
    }

    private void showValidMoves(Piece piece){
        for (Position position : piece.getValidMoves()) {
            fetchSquare(position.getX(), position.getY()).setBackground(Color.YELLOW);
        }
    }
    
    private SquareButton fetchSquare(int x, int y){
        return squares[y * 8 + x];
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
    
    public GraphicTester() {
        initComponents();
        add(fetchSquare(0, 7));
        add(fetchSquare(1, 7));
        add(fetchSquare(2, 7));
        add(fetchSquare(3, 7));
        add(fetchSquare(4, 7));
        add(fetchSquare(5, 7));
        add(fetchSquare(6, 7));
        add(fetchSquare(7, 7));
        add(fetchSquare(0, 6));
        add(fetchSquare(1, 6));
        add(fetchSquare(2, 6));
        add(fetchSquare(3, 6));
        add(fetchSquare(4, 6));
        add(fetchSquare(5, 6));
        add(fetchSquare(6, 6));
        add(fetchSquare(7, 6));
        add(fetchSquare(0, 5));
        add(fetchSquare(1, 5));
        add(fetchSquare(2, 5));
        add(fetchSquare(3, 5));
        add(fetchSquare(4, 5));
        add(fetchSquare(5, 5));
        add(fetchSquare(6, 5));
        add(fetchSquare(7, 5));
        add(fetchSquare(0, 4));
        add(fetchSquare(1, 4));
        add(fetchSquare(2, 4));
        add(fetchSquare(3, 4));
        add(fetchSquare(4, 4));
        add(fetchSquare(5, 4));
        add(fetchSquare(6, 4));
        add(fetchSquare(7, 4));
        add(fetchSquare(0, 3));
        add(fetchSquare(1, 3));
        add(fetchSquare(2, 3));
        add(fetchSquare(3, 3));
        add(fetchSquare(4, 3));
        add(fetchSquare(5, 3));
        add(fetchSquare(6, 3));
        add(fetchSquare(7, 3));
        add(fetchSquare(0, 2));
        add(fetchSquare(1, 2));
        add(fetchSquare(2, 2));
        add(fetchSquare(3, 2));
        add(fetchSquare(4, 2));
        add(fetchSquare(5, 2));
        add(fetchSquare(6, 2));
        add(fetchSquare(7, 2));
        add(fetchSquare(0, 1));
        add(fetchSquare(1, 1));
        add(fetchSquare(2, 1));
        add(fetchSquare(3, 1));
        add(fetchSquare(4, 1));
        add(fetchSquare(5, 1));
        add(fetchSquare(6, 1));
        add(fetchSquare(7, 1));
        add(fetchSquare(0, 0));
        add(fetchSquare(1, 0));
        add(fetchSquare(2, 0));
        add(fetchSquare(3, 0));
        add(fetchSquare(4, 0));
        add(fetchSquare(5, 0));
        add(fetchSquare(6, 0));
        add(fetchSquare(7, 0));
        setUpStartingBoard();
        updateBoard();
        setSize(500, 500);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(8, 8));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GraphicTester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphicTester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphicTester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphicTester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphicTester().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}