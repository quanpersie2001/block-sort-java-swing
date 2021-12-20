package ui.dialog;

import ui.frame.Game;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import ui.panel.PuzzlePanel;
import utils.Constant;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingDialog extends JPanel {

    private JLabel bgSetting;
    private JLabel btnClose;
    private JLabel btnHelp;
    private JLabel btnMute;
    private JLabel btnQuit;
    private JLabel btnSound;
    private Game gameParent;
    private Boolean sound;


    public SettingDialog(Boolean sound) {
        this.sound = sound;
        initComponents();
        setBackground(new Color(0,0,0,100));
        setVisible(false);
        soundClickListener();
    }
    private void initComponents() {

        btnQuit = new JLabel();
        btnHelp = new JLabel();
        btnSound = new JLabel();
        btnMute = new JLabel();
        btnClose = new JLabel();
        bgSetting = new JLabel();

        setPreferredSize(new java.awt.Dimension(1366, 768));
        setLayout(new AbsoluteLayout());

        btnQuit.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_quit.png"));
        add(btnQuit, new AbsoluteConstraints(550, 530, -1, -1));
        btnQuit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                quitClickListener();
            }
        });

        btnHelp.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_help.png"));
        add(btnHelp, new AbsoluteConstraints(550, 390, -1, -1));

        btnSound.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_sound.png"));
        add(btnSound, new AbsoluteConstraints(550, 250, -1, -1));
        btnSound.setVisible(false);

        btnMute.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_mute.png"));
        add(btnMute, new AbsoluteConstraints(550, 250, -1, -1));
        btnMute.setVisible(true);

        btnClose.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_x.png"));
        add(btnClose, new AbsoluteConstraints(920, 120, -1, -1));
        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                close();
            }
        });

        bgSetting.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "bg_setting.png"));
        add(bgSetting, new AbsoluteConstraints(396, 88, -1, -1));

        if (this.sound) {
            this.btnMute.setVisible(true);
            this.btnSound.setVisible(false);
        }else {
            btnMute.setVisible(false);
            btnSound.setVisible(true);
        }
    }

    public void open(){
        setVisible(true);
        repaint();
    }

    public void close(){
        setVisible(false);
    }

    public void soundClickListener(){
        btnSound.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnMute.setVisible(true);
                btnSound.setVisible(false);
                onSound();
            }
        });

        btnMute.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                offSound();
                btnMute.setVisible(false);
                btnSound.setVisible(true);
            }
        });

    }

    public void quitClickListener(){
        System.exit(1);
    }
    public void helpClickListener(){

    }

    public void onSound(){
        PuzzlePanel parent = (PuzzlePanel) this.getParent();
        this.sound = true;
        parent.setSound(true);
        parent.gamePanel.setSound(true);
        gameParent = (Game) SwingUtilities.getWindowAncestor(this.getParent().getParent());
        this.gameParent.onSound();
    }
    public void offSound(){
        PuzzlePanel parent = (PuzzlePanel) this.getParent();
        this.sound = false;
        parent.setSound(false);
        parent.gamePanel.setSound(false);
        gameParent = (Game) SwingUtilities.getWindowAncestor(this.getParent().getParent());
        this.gameParent.offSound();
    }
}
