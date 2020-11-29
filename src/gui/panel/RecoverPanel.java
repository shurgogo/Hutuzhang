package gui.panel;

import utils.ColorUtil;
import utils.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class RecoverPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }

    public static RecoverPanel instance = new RecoverPanel();

    public JButton bBackup = new JButton("恢复");

    private RecoverPanel() {
        GUIUtil.setColor(ColorUtil.blue, bBackup);

        setLayout(new BorderLayout());

        add(bBackup, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(instance, 0.2);
    }

}
