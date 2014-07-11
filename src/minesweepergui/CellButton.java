/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweepergui;


import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Fujitsju
 */
public class CellButton extends JButton {
    // Atributs

    private int indexH;
    private int indexV;
    public boolean bomb;
    private char[] visibleText;
    public int numOfBombInside;
    public boolean visible;
    private CellButton[] insideCell;
    private BoardPanel myBoard;
    private  boolean flagged ;

    public CellButton(int H, int V,BoardPanel b) {
//        super();
        this.setBackground(Color.BLUE);
//        this.setSize(null);
        this.myBoard = b ;
        this.visibleText = new char[2];
        this.indexH = H;
        this.indexV = V;
        this.visibleText[0] = '-';
        this.visibleText[1] = '0';
        this.bomb = false;
        this.visible = false;
        this.insideCell = new CellButton[8];
        this.flagged = false ;
//        this.addActionListener(new ActionListener();
        

    }

    public void addBomb() {
        this.bomb = true;
        this.visibleText[1] = 'B';

    }

    public void searchCell() {
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    this.testAvailabe(-1, 0, i);
                    break;
                case 1:
                    this.testAvailabe(1, 0, i);
                    break;
                case 2:
                    this.testAvailabe(0, 1, i);
                    break;
                case 3:
                    this.testAvailabe(0, -1, i);
                    break;
                case 4:
                    this.testAvailabe(-1, -1, i);
                    break;
                case 5:
                    this.testAvailabe(1, 1, i);
                    break;
                case 6:
                    this.testAvailabe(1, -1, i);
                    break;
                case 7:
                    this.testAvailabe(-1, 1, i);
                    break;

                default:
                    break;
            }

        }
    }

    public void testAvailabe(int H, int V, int A) {
        int sizeH = this.myBoard.getSizeHrz();
        int sizeV = this.myBoard.getSizeVir();
        int newHrz = this.indexH + H;
        int newVir = this.indexV + V;
//        if this cell out bord or this a visible  i don't need thim
        if ((newHrz) >= sizeH || (newVir) >= sizeV
                || (newVir) < 0 || (newHrz) < 0) {
            this.insideCell[A] = null;

        } else if (this.myBoard.bordaCell[newHrz][newVir].visible == true) {
            this.insideCell[A] = null;
        } else {
            this.insideCell[A] = this.myBoard.bordaCell[newHrz][newVir];
        }
    }

    public void countingBomb() {
        if (bomb) {
            return;
        }
        for (int i = 0; i < 8; i++) {
            if (this.insideCell[i] == null) {
                continue;
            } else if (this.insideCell[i].bomb) {
                this.numOfBombInside++;
            }

        }
         String s = (numOfBombInside + "");
        if(this.numOfBombInside==0){
            s = " ";
        }
       

        this.visibleText[1] = s.charAt(0);
    }

//    public void foundBomb(){
//        this.visible = true ;
//        
//    }
    public void revealed() {
        if(this.visible== true){
            return;
        }
        this.visible = true;
        this.setBackground(Color.WHITE);
         this.setText(this.visibleText[1]+"");
        if (this.bomb) {
//            this.visibleText
            this.setBackground(Color.red);
            return;
        } else {
            myBoard.numbVisibleCell++;
        }
        if (this.numOfBombInside == 0) {
            for (int i = 0; i < 8; i++) {
                if (this.insideCell[i] == null) {
                    continue;
                }
                if (this.insideCell[i].visible) {
                    continue;
                }
                this.insideCell[i].revealed();
            }
        }

    }

    public void flagged() {
        if (this.visible){
            return;
        }
        if(this.flagged== false){
        this.visibleText[0] = 'F';
         this.setText(this.visibleText[0]+"");
         this.setBackground(Color.ORANGE);
         this.flagged = true ;
        }else{
             this.visibleText[0] = ' ';
         this.setText(this.visibleText[0]+"");
         this.setBackground(Color.BLUE);
         this.flagged = false ;
        }
    }

    public char getVisibleText() {
        if (this.visible) {
          
            return this.visibleText[1];
        }
        
        return this.visibleText[0];
    }

}
