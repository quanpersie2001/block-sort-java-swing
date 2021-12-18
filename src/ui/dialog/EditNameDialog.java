package ui;

import DAO.DataDAO;
import model.Data;
import utils.Constant;
import utils.FontStyle;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class EditNameDialog extends JPanel {
    private JLabel bgInputName;
    private JLabel btnOk;
    private JLabel btnX;
    private JTextField edtName;
    private JLabel alertLabel;
    private DataDAO dataDAO;
    private List<Data> dataList;
    public EditNameDialog() {
        initComponents();
        this.dataDAO = new DataDAO();
        dataList = dataDAO.read();
        setBackground(new Color(0, 0, 0, 100));
        setVisible(false);
    }

    private void initComponents() {

        edtName = new JTextField();
        btnOk = new JLabel();
        btnX = new JLabel();
        bgInputName = new JLabel();
        alertLabel = new JLabel();

        setMaximumSize(new Dimension(1366, 768));
        setMinimumSize(new Dimension(1366, 768));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Font semiBold = Utils.getFont(FontStyle.SEMI_BOLD);
        edtName.setFont(semiBold.deriveFont(18f));
        edtName.setForeground(new Color(102, 43, 1));
        edtName.setOpaque(false);
        edtName.setBorder(null);
        add(edtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 360, 390, 70));

        Font mediumItalic = Utils.getFont(FontStyle.MEDIUM_ITALIC);
        alertLabel.setFont(mediumItalic.deriveFont(16f)); // NOI18N
        alertLabel.setForeground(new Color(255, 39, 39));
        add(alertLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, 420, -1));
        alertLabel.setVisible(false);

        btnOk.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_oke.png"));
        add(btnOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 470, -1, -1));
        btnOk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                launchGame();
            }
        });

        btnX.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_x.png"));
        add(btnX, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 220, -1, -1));
        btnX.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                close();
            }
        });

        bgInputName.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "bg_input_name.png"));
        add(bgInputName, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, -1, -1));

    }
    public void open(){
        this.setVisible(true);
    }
    public void close(){
        this.setVisible(false);
    }
    public void launchGame(){
        String dataName =  edtName.getText().trim();

        if (dataName.length()  < 6 || dataName.length() > 12){
            alertLabel.setVisible(true);
            alertLabel.setText("Name must be 6-12 characters!");
        }else {
            if (dataDAO.checkNameExist(dataName)){
                alertLabel.setVisible(true);
                alertLabel.setText("This name already exist!");
            } else {
                alertLabel.setVisible(false);
                Data data = new Data(dataName, 1);
                edtName.setText("");
                close();
                Container parent = this.getParent().getParent();
                parent.removeAll();
                dataList.add(data);
                dataDAO.write(dataList);
                parent.add(new PuzzlePanel(data), 2);
                this.revalidate();
                this.repaint();
            }
        }

    }
}
