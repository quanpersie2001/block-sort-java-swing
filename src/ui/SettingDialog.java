package ui;

import utils.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingDialog extends JPanel {

    private JLabel bgSetting;
    private JLabel btnClose;
    private JLabel btnHelp;
    private JLabel btnMute;
    private JLabel btnQuit;
    private JLabel btnSound;


    public SettingDialog() {
        initComponents();
        setBackground(new Color(0,0,0,100));
        setVisible(false);
        closeClickListener();
        soundClickListener();
    }
    private void initComponents() {

        btnQuit = new javax.swing.JLabel();
        btnHelp = new javax.swing.JLabel();
        btnSound = new javax.swing.JLabel();
        btnMute = new javax.swing.JLabel();
        btnClose = new javax.swing.JLabel();
        bgSetting = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1366, 768));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnQuit.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_quit.png"));
        add(btnQuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 530, -1, -1));

        btnHelp.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_help.png"));
        add(btnHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 390, -1, -1));

        btnSound.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_sound.png"));
        add(btnSound, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, -1, -1));
        btnSound.setVisible(false);

        btnMute.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_mute.png"));
        add(btnMute, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, -1, -1));
        btnMute.setVisible(true);

        btnClose.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_x.png"));
        add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 120, -1, -1));

        bgSetting.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "bg_setting.png"));
        add(bgSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 88, -1, -1));
    }

    public void open(){
        setVisible(true);
    }

    public void closeClickListener(){
        this.btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }
        });
    }

    public void soundClickListener(){
        btnSound.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnMute.setVisible(true);
                btnSound.setVisible(false);
            }
        });

        btnMute.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnMute.setVisible(false);
                btnSound.setVisible(true);
            }
        });
        
        if (!btnSound.isVisible()){
            // handle playing music

        } else {
            // handle mute music

        }
    }

    public void quitClickListener(){

    }
    public void helpClickListener(){

    }

}
