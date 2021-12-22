package ui.dialog;

import DAO.SolverDAO;
import model.Step;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import utils.Constant;
import utils.FontStyle;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class SuggestDialog extends JPanel {

    private JLabel bg;
    private JLabel btnClose;
    private JScrollPane scrollPane;
    private JTextArea jTextArea1;
    private Boolean sound;
    private int level;
    private SolverDAO solverDAO;

    public SuggestDialog(Boolean sound, int level){
        this.level = level;
        this.solverDAO = new SolverDAO(level);
        initComponents();
        setVisible(false);
        setBackground(new Color(0,0,0,100));
    }

    private void initComponents() {
        btnClose = new JLabel();
        scrollPane = new JScrollPane();
        jTextArea1 = new JTextArea();
        bg = new JLabel();

        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        setLayout(new AbsoluteLayout());

        btnClose.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_x.png"));
        add(btnClose, new AbsoluteConstraints(920, 120, -1, -1));
        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                close();
            }
        });

        Font semiBold = Utils.getFont(FontStyle.SEMI_BOLD);

        String str = getSuggest();

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(semiBold.deriveFont(26f));
        jTextArea1.setForeground(new Color(182, 99, 50));
        jTextArea1.setRows(5);
        jTextArea1.setText(str);
        jTextArea1.setOpaque(false);
        scrollPane.setViewportView(jTextArea1);

        add(scrollPane, new AbsoluteConstraints(480, 240, 420, 380));

        bg.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "bg_suggest.png"));
        add(bg, new AbsoluteConstraints(400, 90, -1, -1));
    }

    private String getSuggest(){
        Stack<Step> steps = solverDAO.getStepStack();
        String str = "";

        while (!steps.isEmpty()){
            Step step = steps.pop();
            str += step.toString() + "\n";
        }

        return str;
    }

    public void close(){
        setVisible(false);
    }

    public void open(){
        setVisible(true);
        repaint();
    }
}
