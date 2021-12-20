package ui.frame;

import DAO.DataDAO;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import ui.panel.HomePanel;
import utils.Constant;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.File;

public class Game extends JFrame {

    private JLayeredPane layeredPane;
    private HomePanel homePanel;
    private boolean sound;
    private Clip clip;
    private AudioInputStream audioInputStream;
    private DataDAO dataDAO;

    public Game(Boolean sound){
        this.dataDAO = new DataDAO();
        this.sound = sound;
        initComponent();
        setTitle("Block Sort Puzzle by QTT");
        if (this.sound){
            onSound();
        }else {
            offSound();
        }
    }

    private void initComponent(){
        layeredPane = new JLayeredPane();
        homePanel = new HomePanel(this.sound, this.dataDAO);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1370, 800));
        setLocationRelativeTo(null);
        setResizable(false);
        ImageIcon icon = new ImageIcon(Constant.DRAWABLE_PATH + "ic_game.png");
        setIconImage(icon.getImage());
        getContentPane().setLayout(new AbsoluteLayout());

        layeredPane.setPreferredSize(new Dimension(1366, 768));
        layeredPane.setLayout(new CardLayout());
        layeredPane.add(homePanel, 1);

        getContentPane().add(layeredPane, new AbsoluteConstraints(0, 0, -1, -1));
        pack();
    }

    public void onSound(){
        this.sound = true;

        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(Constant.SOUND_PATH + "backgroundMusic.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            fc.setValue(-15);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }

    }
    public void offSound(){
        this.sound = false;
        this.clip.stop();
    }
}
