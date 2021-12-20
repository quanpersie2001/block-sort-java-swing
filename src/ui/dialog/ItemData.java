package ui.dialog;

import model.Data;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import utils.Constant;
import utils.FontStyle;
import utils.Utils;

import javax.swing.*;
import java.awt.*;

public class ItemData extends JPanel implements ListCellRenderer<Data> {

    private JLabel bgItem;
    private JLabel nameLabel;
    private JLabel levelLabel;

    public ItemData() {
        initComponents();
        setOpaque(false);
    }

    public void initComponents() {
        bgItem = new JLabel();
        nameLabel = new JLabel();
        levelLabel = new JLabel();
        setLayout(new AbsoluteLayout());

        Font extraBold = Utils.getFont(FontStyle.EXTRA_BOLD);

        levelLabel.setFont(extraBold.deriveFont(24f));
        levelLabel.setForeground(new Color(69, 35, 17));
        add(levelLabel, new AbsoluteConstraints(380, 0, 90, 80));

        nameLabel.setFont(extraBold.deriveFont(24f));
        nameLabel.setForeground(new Color(69, 35, 17));
        add(nameLabel, new AbsoluteConstraints(30, 0, -1, 80));

        bgItem.setIcon(new ImageIcon(Constant.DRAWABLE_PATH + "bg_item_data.png"));
        add(bgItem, new AbsoluteConstraints(0, 0, -1, -1));

    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Data> list, Data value, int index, boolean isSelected, boolean cellHasFocus) {
        this.nameLabel.setText(value.getName());
        this.levelLabel.setText("LV." + String.valueOf(value.getLevel() + 1));
        return this;
    }
}
