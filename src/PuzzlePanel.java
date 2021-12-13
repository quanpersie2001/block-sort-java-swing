import ui.VictoryDialog;
import utils.Constant;
import utils.MyColor;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PuzzlePanel extends JPanel{
    // Variables declaration - do not modify
    private JLabel background;
    private JLabel btnHome;
    private JLabel btnReset;
    private JLabel btnSetting;
    private JLabel btnUndo;
    private JLabel jLabel3;
    private JLabel txtLevel;
    private JLabel btnPreLevel;
    private JLabel btnNextLevel;
    private GamePanel gamePanel;
    // End of variables declaration

    PuzzlePanel(){
        initComponents();
        //Listener
        gamePanelListener();
        onChangeLevelListener();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void initComponents() {

        btnHome = new JLabel();
        btnSetting = new JLabel();
        btnReset = new JLabel();
        btnUndo = new JLabel();
        btnPreLevel = new JLabel();
        btnNextLevel = new JLabel();
        jLabel3 = new JLabel();
        txtLevel = new JLabel();
        background = new JLabel();
        gamePanel = new GamePanel();

        setMaximumSize(new java.awt.Dimension(1366, 768));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        this.add(gamePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1366, 670));

        btnHome.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_home.png"));
        this.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        btnHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnHomeMouseClick(e);
            }
        });

        btnSetting.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_setting.png"));
        this.add(btnSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 20, -1, -1));
        btnSetting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnSettingMouseClick(e);
            }
        });

        btnReset.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_reset.png"));
        this.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 20, -1, -1));
        btnReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnResetMouseClick(e);
            }
        });

        btnUndo.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_undo.png"));
        this.add(btnUndo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));
        btnUndo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnUndoMouseClick(e);
            }
        });

        btnPreLevel.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_pre_level.png"));
        this.add(btnPreLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, -1, -1));
        btnPreLevel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnPreLevelMouseClick(e);
            }
        });
        if (this.gamePanel.getLevel() == 1){
            btnPreLevel.setVisible(false);
        }

        btnNextLevel.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_next_level.png"));
        this.add(btnNextLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 40, -1, -1));
        btnNextLevel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnNextLevelMouseClick(e);
            }
        });

        Font montserrat = Utils.getMontserratFont();

        jLabel3.setFont(montserrat.deriveFont(55f));
        jLabel3.setForeground(MyColor.WHITE);
        jLabel3.setText("LEVEL ");
        jLabel3.setToolTipText("");
        this.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 20, -1, 80));

        txtLevel.setFont(montserrat.deriveFont(55f));
        txtLevel.setForeground(MyColor.WHITE);
        txtLevel.setText(String.valueOf(this.gamePanel.getLevel()));
        this.add(txtLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(773, 20, -1, 80));

        background.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "background.png"));
        this.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }

    private void gamePanelListener(){
        this.gamePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                txtLevel.setText(String.valueOf(gamePanel.getLevel()));
                if (gamePanel.isCompleteGame()) {
                    VictoryDialog dialog = new VictoryDialog();
                    dialog.showDialog();
                    dialog.btnContinue.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            gamePanel.nextLevel();
                            dialog.closeDialog();
                            txtLevel.setText(String.valueOf(gamePanel.getLevel()));
                            repaint();
                        }
                    });
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void onChangeLevelListener() {
        this.txtLevel.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {

            }

            @Override
            public void componentMoved(ComponentEvent e) {
                if (gamePanel.getLevel() == 1){
                    btnPreLevel.setVisible(false);
                }else {
                    btnPreLevel.setVisible(true);
                }
                int y = txtLevel.getY();
                int distance = (360 - (jLabel3.getWidth() + txtLevel.getWidth())) / 2;
                int levelTextX = distance + 500;
                int levelNumX = 860 - (distance + txtLevel.getWidth());

                jLabel3.setLocation(levelTextX,y);
                txtLevel.setLocation(levelNumX,y);
                if (gamePanel.getLevel() == gamePanel.levelQuantity) {
                    btnNextLevel.setVisible(false);
                }else {
                    btnNextLevel.setVisible(true);
                }
            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }

    private void btnResetMouseClick(MouseEvent ev) {
        this.gamePanel.reset();
    }
    private void btnNextLevelMouseClick(MouseEvent ev) {
        /*if ( this.gamePanel.getLevel() > this.gamePanel.levelQuantity){
            btnNextLevel.setVisible(true);
        }*/

        this.gamePanel.nextLevel();
        this.txtLevel.setText(String.valueOf(this.gamePanel.getLevel()));
    }

    private void btnPreLevelMouseClick(MouseEvent ev) {
        this.gamePanel.preLevel();
        this.txtLevel.setText(String.valueOf(this.gamePanel.getLevel()));
    }

    private void btnUndoMouseClick(MouseEvent ev) {

    }

    private void btnHomeMouseClick(MouseEvent ev) {

    }

    private void btnSettingMouseClick(MouseEvent ev) {

    }
}
