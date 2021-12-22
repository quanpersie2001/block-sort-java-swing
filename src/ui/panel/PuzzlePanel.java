package ui.panel;

import DAO.DataDAO;
import model.Data;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import ui.dialog.SettingDialog;
import ui.dialog.SuggestDialog;
import ui.dialog.VictoryDialog;
import utils.Constant;
import utils.CustomizeColor;
import utils.FontStyle;
import utils.Sounds;
import utils.Utils;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PuzzlePanel extends JPanel{

    private JLabel background;
    private JLabel btnHome;
    private JLabel btnReset;
    private JLabel btnSetting;
    private JLabel btnUndo;
    private JLabel btnNotUndo;
    private JLabel jLabel3;
    private JLabel txtLevel;
    private JLabel btnPreLevel;
    private JLabel btnNextLevel;
    private JLabel btnSuggest;
    private VictoryDialog victoryDialog;
    private SuggestDialog suggestDialog;
    public GamePanel gamePanel;
    private SettingDialog settingDialog;
    private Data data;
    private DataDAO dataDAO;
    private Boolean sound;


    public PuzzlePanel(Data data, Boolean sound, DataDAO dataDAO){
        this.sound = sound;
        this.dataDAO = dataDAO;
        this.data = data;
        initComponents();
        //Listener
        gamePanelListener();
        onChangeLevelListener();
        dialogVictoryListener();
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
        btnNotUndo = new JLabel();
        btnPreLevel = new JLabel();
        btnNextLevel = new JLabel();
        btnSuggest = new JLabel();
        jLabel3 = new JLabel();
        txtLevel = new JLabel();
        background = new JLabel();
        gamePanel = new GamePanel(this.data, this.sound);
        victoryDialog = new VictoryDialog();
        settingDialog = new SettingDialog(this.sound);
        suggestDialog = new SuggestDialog(this.sound, this.gamePanel.getLevel());

        setMaximumSize(new java.awt.Dimension(1366, 768));

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        this.add(victoryDialog, new AbsoluteConstraints(0, 0, 1366, 768));
        this.add(settingDialog, new AbsoluteConstraints(0, 0, 1366, 768));
        this.add(suggestDialog, new AbsoluteConstraints(0, 0, 1366, 768));
        this.add(gamePanel, new AbsoluteConstraints(0, 100, 1366, 670));

        btnHome.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_home.png"));
        this.add(btnHome, new AbsoluteConstraints(20, 20, -1, -1));
        btnHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnHomeMouseClick(e);
            }
        });

        btnSetting.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_setting.png"));
        this.add(btnSetting, new AbsoluteConstraints(1260, 20, -1, -1));
        btnSetting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnSettingMouseClick(e);
            }
        });

        btnSuggest.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_suggest.png"));
        this.add(btnSuggest, new AbsoluteConstraints(960, 20, -1, -1));
        btnSuggest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sounds.buttonSound(sound);
                suggestDialog.open();
            }
        });

        btnReset.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_reset.png"));
        this.add(btnReset, new AbsoluteConstraints(1120, 20, -1, -1));
        btnReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnResetMouseClick(e);
            }
        });

        btnNotUndo.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_cant_undo.png"));
        this.add(btnNotUndo, new AbsoluteConstraints(160, 20, -1, -1));

        btnUndo.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_undo.png"));
        this.add(btnUndo, new AbsoluteConstraints(160, 20, -1, -1));
        btnUndo.setVisible(false);
        btnUndo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnUndoMouseClick(e);
            }
        });

        btnPreLevel.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_pre_level.png"));
        this.add(btnPreLevel, new AbsoluteConstraints(460, 40, -1, -1));
        btnPreLevel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnPreLevelMouseClick(e);
            }
        });

        btnNextLevel.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "btn_next_level.png"));
        this.add(btnNextLevel, new AbsoluteConstraints(860, 40, -1, -1));
        btnNextLevel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnNextLevelMouseClick(e);
            }
        });

        Font montserrat = Utils.getFont(FontStyle.EXTRA_BOLD);

        jLabel3.setFont(montserrat.deriveFont(55f));
        jLabel3.setForeground(CustomizeColor.WHITE);
        jLabel3.setText("LEVEL ");
        jLabel3.setToolTipText("");
        this.add(jLabel3, new AbsoluteConstraints(565, 20, -1, 80));

        txtLevel.setFont(montserrat.deriveFont(55f));
        txtLevel.setForeground(CustomizeColor.WHITE);
        txtLevel.setText(String.valueOf(this.gamePanel.getLevel()));
        this.add(txtLevel, new AbsoluteConstraints(773, 20, -1, 80));

        background.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "background.png"));
        this.add(background, new AbsoluteConstraints(0, 0, -1, -1));
    }

    private void gamePanelListener(){
        this.gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent ev) {
                if (gamePanel.isCompleteGame()) {
                    Sounds.victorySound(sound);
                    victoryDialog.showDialog();
                    data.setLevel(gamePanel.getLevel());
                    dataDAO.update(data);
                }
                if (gamePanel.isUndo()){
                    btnUndo.setVisible(true);
                    btnNotUndo.setVisible(false);
                }else {
                    btnNotUndo.setVisible(true);
                    btnUndo.setVisible(false);

                }
            }
        });
    }

    private void dialogVictoryListener(){
        this.victoryDialog.btnContinue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                Sounds.buttonSound(sound);
                btnNextLevelMouseClick(ev);
                victoryDialog.closeDialog();
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
                if (gamePanel.getLevel() > data.getLevel() || gamePanel.getLevel() == gamePanel.levelQuantity) {
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
        Sounds.buttonSound(sound);
        this.gamePanel.reset();
    }

    private void btnNextLevelMouseClick(MouseEvent ev) {
        Sounds.buttonSound(sound);
        this.gamePanel.nextLevel();
        this.txtLevel.setText(String.valueOf(this.gamePanel.getLevel()));
    }

    private void btnPreLevelMouseClick(MouseEvent ev) {
        Sounds.buttonSound(sound);
        this.gamePanel.preLevel();
        this.txtLevel.setText(String.valueOf(this.gamePanel.getLevel()));
    }

    private void btnUndoMouseClick(MouseEvent ev) {
        btnUndo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sounds.buttonSound(sound);
                if (gamePanel.isUndo()){
                    btnUndo.setVisible(true);
                    btnNotUndo.setVisible(false);
                }else {
                    btnNotUndo.setVisible(true);
                    btnUndo.setVisible(false);

                }
            }
        });
        gamePanel.undo();
    }

    private void btnHomeMouseClick(MouseEvent ev) {
        Sounds.buttonSound(sound);
        Container parent =this.getParent();
        parent.remove(this);
        parent.add(new HomePanel(this.sound, this.dataDAO));
        parent.repaint();
        parent.revalidate();
    }

    private void btnSettingMouseClick(MouseEvent ev) {
        Sounds.buttonSound(sound);
        settingDialog.open();
    }

    public void setSound(Boolean sound) {
        this.sound = sound;
    }
}
