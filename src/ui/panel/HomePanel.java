package ui;

import utils.Constant;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class HomePanel extends JPanel {

    private JLabel bgHome;
    private JLabel btnAbout;
    private JLabel btnExit;
    private JLabel btnLoadGame;
    private JLabel btnNewGame;
    private EditNameDialog editNameDialog;
    private LoadGameDialog loadGameDialog;

    public HomePanel() {
        initComponents();
    }

    private void initComponents() {

        btnLoadGame = new JLabel();
        btnAbout = new JLabel();
        btnExit = new JLabel();
        btnNewGame = new JLabel();
        bgHome = new JLabel();
        editNameDialog = new EditNameDialog();
        loadGameDialog = new LoadGameDialog();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add(editNameDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));
        add(loadGameDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));

        btnLoadGame.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_load_game.png"));
        add(btnLoadGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 510, -1, -1));
        btnLoadGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadGameDialog.open();
            }
        });

        btnAbout.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_about.png"));
        add(btnAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 590, -1, -1));

        btnExit.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_exit.png"));
        add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 670, -1, -1));

        btnNewGame.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_new_game.png"));
        add(btnNewGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 430, -1, -1));
        btnNewGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditName();
            }
        });

        bgHome.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "bg_home.jpg"));
        add(bgHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }
    public void openEditName(){
        editNameDialog.open();
    }
    public void openLoadGame(){

    }
}
