import ui.PuzzlePanel;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Game frame = new Game();
                    frame.setSize(1366,768);
                    frame.setVisible(true);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Game(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        setContentPane(new PuzzlePanel());
        setSize(new java.awt.Dimension(1366, 768));
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
