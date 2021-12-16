package ui;

import DAO.DataDAO;
import model.Data;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import utils.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class LoadGameDialog extends JPanel {

    private JScrollPane scrollPane;
    private JList<Data> listData;
    private JLabel bgLoadGame;
    private List<Data> dataList;
    private DataDAO dataDAO;
    private JLabel btnClose;
    public LoadGameDialog(){
        this.dataDAO = new DataDAO();
        this.dataList = dataDAO.read();
        initComponents();
        setBackground(new Color(0,0,0,100));
        setVisible(false);
    }

    public void initComponents(){
        scrollPane = new JScrollPane();
        listData = new JList<Data>();
        bgLoadGame = new JLabel();
        btnClose = new JLabel();

        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        listData.setOpaque(false);

        listData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList)e.getSource();
                Data data = (Data) list.getSelectedValue();
                launchGame(data);
            }
        });

        setLayout(new AbsoluteLayout());
        scrollPane.setViewportView(listData);
        add(scrollPane, new AbsoluteConstraints(420, 206, 493, 426));

        btnClose.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_x.png"));
        add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 100, -1, -1));
        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                close();
            }
        });

        bgLoadGame.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "bg_load_game.png"));
        add(bgLoadGame, new AbsoluteConstraints(380, 80, -1, -1));

        DefaultListModel<Data> model = new DefaultListModel<>();
        for(Data data : this.dataList)
            model.addElement(data);
        listData.setModel(model);
        listData.setCellRenderer(new ItemData());
    }

    public void open(){
        setVisible(true);
    }
    public void close(){
        setVisible(false);
    }
    public void launchGame(Data data){
        Container parent = this.getParent().getParent();
        parent.removeAll();
        parent.add(new PuzzlePanel(data), 2);
        this.revalidate();
        this.repaint();
    }
}
