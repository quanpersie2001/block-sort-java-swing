package ui;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import utils.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VictoryDialog extends JPanel {

    boolean show;
    private JLabel jLabel1;
    public JLabel btnContinue;



    public VictoryDialog(){
        init();
        setBackground(new Color(0,0,0,100));
        setVisible(false);
    }

    public void init(){
        btnContinue = new JLabel();
        jLabel1 = new JLabel();

//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setUndecorated(true);
        setPreferredSize(new Dimension(1366, 768));
//        setResizable(false);
        this.setLayout(new AbsoluteLayout());

        btnContinue.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_continues.png"));
        this.add(btnContinue, new AbsoluteConstraints(610, 500, -1, -1));

        jLabel1.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "ic_win.png"));
        this.add(jLabel1, new AbsoluteConstraints(220, 0, 950, 770));

        setSize(new Dimension(1366, 768));
//        setLocationRelativeTo(null);
    }

    public void showDialog() {
//        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        show = true;
        setVisible(true);
    }

    public boolean isShow() {
        return show;
    }

    public void closeDialog(){
        setVisible(false);
    }
}
