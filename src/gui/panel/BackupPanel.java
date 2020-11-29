package gui.panel;

import utils.ColorUtil;
import utils.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class BackupPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }

    public static BackupPanel instance = new BackupPanel();

    public JButton bBackup = new JButton("备份");

    private BackupPanel() {
        GUIUtil.setColor(ColorUtil.blue, bBackup);

        setLayout(new BorderLayout());

        add(bBackup, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(instance, 0.2);
    }

}
