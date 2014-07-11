/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweepergui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Fujitsju
 */
public class Openning extends JFrame implements ActionListener {

    private JLabel welcomeLabel;
    private JLabel nameUserLabel;
    private JLabel levllabel;
    private JLabel boardSizeLabel;
    private JTextField nameuserTxtF;
    private JComboBox levlCombx;
    private JComboBox boardSizeCombx;
    private JButton joinButn;
    private JButton exitButn;
    private BoardPanel myBoard;
    private String[] listSapjectLevl = {"Beginner", "Intrmediate", "Advanced"};
    private String[] listBoardSize = {"9*9", "16*16", "16*30"};
    private Panel  northPanal ;
    private Panel southPanal ;
    private Panel cinterPanal ;

    @SuppressWarnings("empty-statement")
    public Openning() {
//        this.myBoard = BoardPanel.getmyBoardPanel();
        this.welcomeLabel = new JLabel("welcome");
        this.nameUserLabel = new JLabel("Name Player");
        this.levllabel = new JLabel("levl Game");
        this.boardSizeLabel = new JLabel("The Boarde");
        this.nameuserTxtF = new JTextField("enter your name her");
        this.levlCombx = new JComboBox(this.listSapjectLevl);
        this.boardSizeCombx = new JComboBox(listBoardSize);
        this.joinButn = new JButton("Play Now");
        this.exitButn = new JButton("Exit");
        this.setTitle("Minesweeper Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setVisible(true);
         this.setLayout(new FlowLayout());
        this.add(northPanal);
//        this.add(cinterPanal);
//        this.add(southPanal);
       
        this.northPanal.setLayout(new GridLayout(2, 1));
    
        northPanal.setVisible(true);
        this.welcomeLabel.setText("welcome");
         this.northPanal.setBackground(Color.red);
        
        this.add(this.welcomeLabel);
        this.add(this.northPanal);
        
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
