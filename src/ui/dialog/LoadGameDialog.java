package ui.dialog;

import DAO.DataDAO;
import model.Data;
import ui.popup.DeletePopUp;
import ui.panel.PuzzlePanel;
import utils.Constant;
import utils.Sounds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.List;


public class LoadGameDialog extends JPanel {

    private JScrollPane scrollPane;
    private JList<Data> listData;
    private JLabel bgLoadGame;
    private List<Data> dataList;
    private DataDAO dataDAO;
    private JLabel btnClose;
    private DefaultListModel<Data> model;
    private DeletePopUp deletePopUp;
    private Boolean sound;

    public LoadGameDialog(Boolean sound, DataDAO dataDAO){
        this.sound = sound;
        this.dataDAO = dataDAO;
        this.dataList = dataDAO.dataList;
        model = new DefaultListModel<>();
        initComponents();
        setBackground(new Color(0,0,0,100));
        setVisible(false);
        setOpaque(false);
        listDataClickListener();
    }

    public void initComponents(){
        scrollPane = new JScrollPane();
        listData = new JList<Data>();
        bgLoadGame = new JLabel();
        btnClose = new JLabel();
        deletePopUp = new DeletePopUp();

        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        listData.setOpaque(false);

        setLayout(null);
        add(deletePopUp);

        scrollPane.setViewportView(listData);
        scrollPane.setBounds(420, 206, 493, 426);
        add(scrollPane);

        btnClose.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_x.png"));
        btnClose.setBounds(910, 100, 65, 70);
        add(btnClose);
        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sounds.buttonSound(sound);
                deletePopUp.close();
                close();
            }
        });


        bgLoadGame.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "bg_load_game.png"));
        bgLoadGame.setBounds(380, 80, 573, 592);
        add(bgLoadGame);

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
        parent.add(new PuzzlePanel(data, this.sound, this.dataDAO), 2);
        this.revalidate();
        this.repaint();
    }

    public void listDataClickListener(){
        listData.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Rectangle2D.Double r = new Rectangle2D.Double();

                JList list = (JList)e.getSource();
                int index = list.locationToIndex(e.getPoint());

                if (index != -1){
                    Data data = (Data) list.getModel().getElementAt(index);
                    Point selectedPos = list.indexToLocation(index);

                    r.setFrame(selectedPos.getX(), selectedPos.getY(), 470, 84);

                    if (r.contains(e.getPoint())) {
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            Sounds.buttonSound(sound);
                            launchGame(data);
                        }else if (SwingUtilities.isRightMouseButton(e)){
                            deletePopUp.setBounds(e.getX() + 420, e.getY() + 206, 123, 50);
                            deletePopUp.open();
                            deletePopUp.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    dataList.remove(data);
                                    dataDAO.write(dataList);
                                    model.removeElement(data);
                                    deletePopUp.close();
                                }
                            });
                        }else {
                            //
                        }
                    }else {
                        if (deletePopUp != null) {
                            deletePopUp.close();
                        }
                    }
                }
            }
        });
    }

}
