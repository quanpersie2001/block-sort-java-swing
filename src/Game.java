import ui.HomePanel;
import ui.PuzzlePanel;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    private JLayeredPane layeredPane;
    private HomePanel homePanel;
    private PuzzlePanel puzzlePanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Game frame = new Game();
                    frame.setSize(1370,800);
                    frame.setVisible(true);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Game(){
        initComponent();
//        setUndecorated(true);
//        setContentPane(new HomePanel());

    }

    private void initComponent(){
        layeredPane = new JLayeredPane();
        homePanel = new HomePanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1370, 800));
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        layeredPane.setPreferredSize(new java.awt.Dimension(1366, 768));
        layeredPane.setLayout(new CardLayout());
        layeredPane.add(homePanel, 1);

        getContentPane().add(layeredPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        pack();
    }
}
