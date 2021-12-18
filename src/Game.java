import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import ui.panel.HomePanel;
import utils.Constant;
import utils.Utils;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    private JLayeredPane layeredPane;
    private HomePanel homePanel;

    public Game(){
        initComponent();
        setTitle("Block Sort Puzzle by QTT");
    }

    private void initComponent(){
        layeredPane = new JLayeredPane();
        homePanel = new HomePanel();

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

}
