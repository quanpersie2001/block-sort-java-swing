package ui.panel;

import DAO.DataDAO;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import ui.dialog.EditNameDialog;
import ui.dialog.LoadGameDialog;
import utils.Constant;
import utils.Sounds;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private Boolean sound;
    private DataDAO dataDAO;
    public HomePanel(Boolean sound, DataDAO dataDAO) {
        this.dataDAO = dataDAO;
        this.sound = sound;
        initComponents();
    }

    private void initComponents() {

        btnLoadGame = new JLabel();
        btnAbout = new JLabel();
        btnExit = new JLabel();
        btnNewGame = new JLabel();
        bgHome = new JLabel();
        editNameDialog = new EditNameDialog(this.sound, this.dataDAO);
        loadGameDialog = new LoadGameDialog(this.sound, this.dataDAO);

        setLayout(new AbsoluteLayout());

        add(editNameDialog, new AbsoluteConstraints(0, 0, 1366, 768));
        add(loadGameDialog, new AbsoluteConstraints(0, 0, 1366, 768));

        btnLoadGame.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_load_game.png"));
        add(btnLoadGame, new AbsoluteConstraints(1130, 510, -1, -1));
        btnLoadGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sounds.buttonSound(sound);
                openLoadGame();
            }
        });

        btnAbout.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_about.png"));
        add(btnAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 590, -1, -1));
        btnAbout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sounds.buttonSound(sound);
                openAboutDialog();
            }
        });

        btnExit.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_exit.png"));
        add(btnExit, new AbsoluteConstraints(1130, 670, -1, -1));
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sounds.buttonSound(sound);
                exitGame();
            }
        });

        btnNewGame.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_new_game.png"));
        add(btnNewGame, new AbsoluteConstraints(1130, 430, -1, -1));
        btnNewGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sounds.buttonSound(sound);
                openEditName();
            }
        });

        bgHome.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "bg_home.jpg"));
        add(bgHome, new AbsoluteConstraints(0, 0, -1, -1));
    }

    public void openEditName(){
        editNameDialog.open();
    }

    public void openLoadGame(){
        loadGameDialog.open();
    }
    public void exitGame(){
        System.exit(1);
    }
    public void openAboutDialog(){

    }
}
