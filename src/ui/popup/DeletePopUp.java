package ui.popup;

import utils.Constant;

import javax.swing.*;


public class DeletePopUp extends JPanel {

    private JLabel bg;

    public DeletePopUp(){
        initComponents();
        setVisible(false);
        setOpaque(false);
    }

    private void initComponents() {
        bg = new javax.swing.JLabel();
        setLayout(null);
        bg.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_delete_data.png"));
        add(bg);
        bg.setBounds(0, 0, 123, 50);
    }

    public void open(){
        setVisible(true);
    }
    public void close(){
        setVisible(false);
    }

}
