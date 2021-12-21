package ui.dialog;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import utils.Constant;
import utils.Sounds;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HelpDialog extends JPanel {
    private JLabel bg;
    private JLabel btnClose;
    private Boolean sound;

    public HelpDialog(Boolean sound){
        this.sound = sound;
        initComponents();
        setBackground(new Color(0, 0, 0, 100));
        setVisible(false);
    }

    private void initComponents() {

        bg = new JLabel();
        btnClose = new JLabel();

        setLayout(new AbsoluteLayout());

        btnClose.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_x.png"));
        add(btnClose, new AbsoluteConstraints(1150, 90, -1, -1));
        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sounds.buttonSound(sound);
                close();
            }
        });

        bg.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "help.png"));
        add(bg, new AbsoluteConstraints(160, 70, -1, -1));
    }

    public void open() {
        setVisible(true);
    }

    public void close(){
        setVisible(false);
    }
}
