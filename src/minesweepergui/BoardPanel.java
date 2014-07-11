package minesweepergui;

import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    private int sizeVir;
    private int sizeHrz;
    public CellButton[][] bordaCell;
    private Testopen myTestopen;
    private int numberOfBomb;
    public int numbVisibleCell;
    private int[][] indexBomb;
    public String playerName;

    public BoardPanel(Testopen openfarm) {

        this.myTestopen = openfarm;
        this.sizeHrz = this.myTestopen.getStorIndexHrz();
        this.sizeVir = this.myTestopen.getStorIndexVir();
        this.setLayout(new GridLayout(this.sizeHrz, this.sizeVir));
        this.numbVisibleCell = 0;
        this.drawBoard();
        this.insertBomb();
        this.assinNumbBomb();



    }

//    to bilde firset board
    // initialize
    public void drawBoard() {
        this.bordaCell = new CellButton[this.sizeHrz][this.sizeVir];
        for (int i = 0; i < this.sizeHrz; i++) {
            for (int j = 0; j < this.sizeVir; j++) {
                this.bordaCell[i][j] = new CellButton(i, j, this);
                this.bordaCell[i][j].addActionListener(new ActionButton());
                this.bordaCell[i][j].addMouseListener(new RightClicker());
                this.bordaCell[i][j].setEnabled(true);
     
                this.add(this.bordaCell[i][j]);
                this.setNumberOfBomb();
                this.indexBomb(this.numberOfBomb);
            }
        }

    }

//   
//    that will call after insert bomb to every cell now how match bomb insed thim 
    public void assinNumbBomb() {
        for (int i = 0; i < this.sizeHrz; i++) {
            for (int j = 0; j < this.sizeVir; j++) {
                this.bordaCell[i][j].searchCell();
                this.bordaCell[i][j].countingBomb();
            }

        }
    }
//    to cariet one object 

private void toEndGame (){
     for (int i = 0; i < this.sizeHrz; i++) {
            for (int j = 0; j < this.sizeVir; j++) {
                if (this.bordaCell[i][j].bomb) {
                    this.bordaCell[i][j].setBackground(Color.red);
                    this.bordaCell[i][j].revealed();
                }
                 this.bordaCell[i][j].setEnabled(false);
            }

        }
}

    public int getSizeVir() {
        return this.sizeVir;
    }

    public void setSizeVir(int a) {
        this.sizeVir = a;
    }

    public int getSizeHrz() {
        return this.sizeHrz;
    }

    public void setSizeHrz(int a) {
        this.sizeHrz = a;
    }

    public boolean playerwon() {
       
        if (this.numbVisibleCell == ((this.sizeHrz * this.sizeVir) - this.numberOfBomb)) {
            this.toEndGame();

            return true;
        }
        return false;
    }
//   to criat index of bomb

    private boolean testbol(int a, int b, int[][] ary) {
        for (int i = 0; i < ary.length; i++) {
            if (a == ary[i][0] && b == ary[i][1]) {
                return false;
            }
        }
        return true;
    }

    private void indexBomb(int numOfBomb) {
        Random x = new Random();
        indexBomb = new int[numOfBomb][2];
        for (int i = 0; i < numOfBomb; i++) {
            int a, b;
            while (true) {
                a = x.nextInt(this.sizeHrz);// broblem
                b = x.nextInt(this.sizeVir);
                if (testbol(a, b, indexBomb)) {
                    break;
                } else {
                    continue;
                }
            }
            indexBomb[i][0] = a;
            indexBomb[i][1] = b;
        }

    }

    private void setNumberOfBomb() {
        switch (this.sizeVir) {
            case 9:
                this.numberOfBomb = 10;

                break;
            case 16:
                this.numberOfBomb = 40;
                break;
            case 30:
                this.numberOfBomb = 120;
                break;

            default:
                break;
        }

    }
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//    insert Bomb in board

    private void insertBomb() {
        for (int i = 0; i < this.numberOfBomb; i++) {
            int a = this.indexBomb[i][0];
            int b = this.indexBomb[i][1];
            this.bordaCell[a][b].addBomb();

        }
    }
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   

    class ActionButton implements ActionListener {
        private String[] args;
        private MianGame mygame;
        @Override
        public void actionPerformed(ActionEvent e) {
            this.mygame = MianGame.getmyMianGame();
            CellButton a;
            a = (CellButton) e.getSource();
            a.revealed();
            if(a.bomb){
                toEndGame();
                EndGame.main(args,"sorry you are lost",this.mygame);
            }
            if(playerwon()){
               toEndGame();
                EndGame.main(args,"congratulation you are won",this.mygame);  
            }

        }

    }
//    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private class RightClicker extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            CellButton b;
            b = (CellButton) e.getSource();
            if (e.isMetaDown()) {
                b.flagged();
            }
        }
    }
}
